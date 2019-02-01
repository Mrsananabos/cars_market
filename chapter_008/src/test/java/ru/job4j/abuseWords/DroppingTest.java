package ru.job4j.abuseWords;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DroppingTest {

    @Test
    public void dropAbuseTest() throws IOException {
        Dropping dropping = new Dropping();
        final String fileInname = "src/main/java/ru/job4j/abuseWords/sourceAbuses.txt";
        final String fileOutname = "src/main/java/ru/job4j/abuseWords/targetAbuses.txt";
        String[] abuse = {"Snow", "one", "too"};
        try (InputStream in = new FileInputStream(new File(fileInname).getAbsolutePath());
             OutputStream out = new FileOutputStream(new File(fileOutname).getAbsolutePath());
             InputStream resultStream = new FileInputStream(new File(fileOutname).getAbsolutePath())) {
            StringBuilder resultStringBuilder = new StringBuilder();
            int resultReadInt;
            String actualTrue = "flakes falling\r\n"
                    + "One by ,\r\n"
                    + "Time to play,\r\n"
                    + "And have some fun.\r\n"
                    + "Build a snowman\r\n"
                    + "balls, ,\r\n"
                    + "Come and see what you can do.\r\n";
            dropping.dropAbuses(in, out, abuse);
            try {
                while ((resultReadInt = resultStream.read()) != -1) {
                    resultStringBuilder.append((char) resultReadInt);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            assertThat(resultStringBuilder.toString(), is(actualTrue));
        }


    }

}