package com.ph.share.share.quartz.test;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class _09_QuartzTest_ScheduleTrigger {

    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job", "group1")
                    .storeDurably()
                    .build();


            Trigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .forJob("job", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(3)
                            .repeatForever())
                    .build();

            scheduler.addJob(job,false);
            scheduler.scheduleJob(trigger2);
            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(trigger2);

            TimeUnit.SECONDS.sleep(3);
            scheduler.shutdown();

        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }
}