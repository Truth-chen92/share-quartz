package com.ph.share.share.quartz.test;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
  import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.*;
  import static org.quartz.TriggerBuilder.*;
  import static org.quartz.SimpleScheduleBuilder.*;

  public class _01_QuartzTest {

      public static void main(String[] args) {

          try {
              // Grab the Scheduler instance from the Factory
              Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

              // and start it off
              scheduler.start();
// define the job and tie it to our HelloJob class
              JobDetail job = newJob(HelloJob.class)
                      .withIdentity("job1", "group1")
                      .build();

              // Trigger the job to run now, and then repeat every 40 seconds
              Trigger trigger = newTrigger()
                      .withIdentity("trigger1", "group1")
                      .startNow()
                      .withSchedule(simpleSchedule()
                              .withIntervalInSeconds(5)
                              .repeatForever())
                      .build();

              // Tell quartz to schedule the job using our trigger
              scheduler.scheduleJob(job, trigger);
              TimeUnit.SECONDS.sleep(20);
              scheduler.shutdown();

          } catch (SchedulerException | InterruptedException se) {
              se.printStackTrace();
          }
      }
  }