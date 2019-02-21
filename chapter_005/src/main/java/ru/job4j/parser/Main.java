package ru.job4j.parser;


import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {
    private static Settings settings = Settings.getInstance();

    public static void main(String[] args) throws IOException, SchedulerException {
        JobDetail job = newJob(JobParser.class)
                .withIdentity("myJob", "group1")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule(settings.value("cron.time")))
                .build();
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }


}
