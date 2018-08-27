package ru.job4j.parsing;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class JobParser implements Job {
    private static Settings settings = Settings.getInstance();
    private static Logger logger = Logger.getLogger(JobParser.class);
    private final String url;
    private JdbkStorage jdbkStorage = new JdbkStorage();
    private List<Vacancy> newVacancies = new ArrayList<>();

    public JobParser() {
        settings = Settings.getInstance();
        url = settings.value("jsoup.url");
        settings.setValue("firstPassDone", "false");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        int countLines = this.jdbkStorage.countLinesOfTable();
        StartPoint startFrom;
        if (countLines != 0) {
            Timestamp fromTime = jdbkStorage.findTimestampById(countLines);
            Long fromTimeLong = fromTime.getTime();
            startFrom = this.findStartPointByTime(fromTimeLong);
            this.parsingFromPoint(startFrom);
            logger.info("Site has been parsed again");
        } else {
            Calendar now = Calendar.getInstance();
            now.set(Calendar.DAY_OF_MONTH, 1);
            now.set(Calendar.MONTH, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.HOUR_OF_DAY, 0);
            startFrom = this.findStartPointByTime(now.getTimeInMillis());
            this.parsingFromPoint(startFrom);
            logger.info("Site has been parsed firstly");
        }
    }

    private StartPoint findStartPointByTime(long timeLong) {
        StartPoint rsl = new StartPoint();
        int nEl = 1;
        int nPage = 1;
        boolean exit = false;
        int numOfPages = numOfPages();
        for (int i = 1; i <= numOfPages; i++) {
            Document document = null;
            try {
                document = Jsoup.connect(this.url + "/" + i).get();
            } catch (IOException e) {
               logger.error(e.getMessage(), e);
            }
            Elements elements = document.select("tr:has(.postslisttopic)");
            int numOfElem = elements.size();
            for (int y = 3; y < numOfElem; y++) {
                Element currentElement = elements.get(y);
                Elements data = currentElement.select("td");
                String rowData = data.get(5).text();
                Timestamp time = getTime(rowData);
                if (timeLong > time.getTime()) {
                    if (y == 3) {
                        if (i != 1) {
                            nEl = numOfElem;
                            nPage = i - 1;
                        }
                    } else {
                        nEl = y - 1;
                        nPage = i;
                    }
                    exit = true;
                    break;
                }
            }
            if (exit) {
                break;
            }
        }
        rsl.setFromElementNumber(nEl);
        rsl.setFromPageNumber(nPage);
        return rsl;
    }

    private void parsingFromPoint(StartPoint fromPoint) {
        int fromElement = fromPoint.getFromElementNumber();
        int fromPage = fromPoint.getFromPageNumber();
        String fromPageString = String.valueOf(fromPage);
        parsePage(this.url + "/" + fromPageString, fromElement);
        int numOfElement = numOfElements();
        for (int i = fromPage - 1; i > 0; i--) {
            parsePage(this.url + "/" + String.valueOf(i), numOfElement - 1);
        }
        Date currentDate = new Date();
        String dateLastPars = String.valueOf(currentDate.getTime());
        settings.setValue("dateLastVacancy", dateLastPars);
    }

    private void parsePage(String url, int fromElement) {

            Document document = null;
            try {
                document = Jsoup.connect(url).get();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            Elements elements = document.select("tr:has(.postslisttopic)");
            for (int y = fromElement; y > 2; y--) {
                Element currentElement = elements.get(y);
                Elements links = currentElement.select(".postslisttopic");
                String name = links.text();
                String lowerName = name.toLowerCase();
                if (lowerName.contains("java") && (!lowerName.contains("script"))) {
                    Elements links1 = links.select("a[href]");
                    String linkOfVacansy = links1.attr("href");
                    Document document1 = null;
                    try {
                        document1 = Jsoup.connect(linkOfVacansy).get();
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                    }
                    String textOfVac = document1.getElementsByClass("msgBody").get(1).text(); //текст вакансии
                    Elements data = currentElement.select("td");
                    String rowData = data.get(5).text();
                    Timestamp time = getTime(rowData);
                    logger.info("Vacancy is found : " + name + " " + time);
                    Vacancy vacancy = new Vacancy(name, textOfVac, time);
                    this.newVacancies.add(vacancy);
                    if (this.newVacancies.size() == 100) {
                        jdbkStorage.listVacanciesWriteIn(this.newVacancies);
                        this.newVacancies.clear();
                    }
                }
            }
            jdbkStorage.listVacanciesWriteIn(this.newVacancies);
            this.newVacancies.clear();
        }

        private int numOfElements() {
            Document document = null;
            try {
                document = Jsoup.connect(this.url).get();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            Elements elements = document.select("tr:has(.postslisttopic)");
           int result = elements.size();
           return result;
        }


    private int numOfPages() {
        Document document = null;
        try {
            document = Jsoup.connect(this.url).get();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        Element element = document.body().getElementsByClass("sort_options").last();
        Element lastLink = element.select("a[href]").last();
        int result = Integer.parseInt(lastLink.text());
        return result;
    }

    private java.sql.Timestamp getTime(String string) {
        java.sql.Timestamp result = null;
        string = string.trim();
        try {
            Calendar calendar = Calendar.getInstance();
            if (string.contains("сегодня")) {
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(string.substring(9, 11)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(string.substring(12, 14)));
                result = new java.sql.Timestamp(calendar.getTimeInMillis());
            } else if (string.contains("вчера")) {
                calendar.add(Calendar.DATE, -1);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(string.substring(7, 9)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(string.substring(10, 12)));
                result = new java.sql.Timestamp(calendar.getTimeInMillis());
            } else {
                SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm", new Locale("ru", "RU"));
                calendar.setTime(format.parse(string));
                result = new java.sql.Timestamp(calendar.getTimeInMillis());
            }
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

}




