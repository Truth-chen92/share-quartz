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

public class _08_QuartzProperties {

    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            System.out.println("SchedulerName  "+scheduler.getSchedulerName());
            System.out.println("线程数  "+scheduler.getMetaData().getThreadPoolSize());

            scheduler.shutdown();

        } catch (SchedulerException  se) {
            se.printStackTrace();
        }
    }
}