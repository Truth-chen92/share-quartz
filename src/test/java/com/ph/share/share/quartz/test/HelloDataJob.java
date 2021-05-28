package com.ph.share.share.quartz.test;

import com.ph.share.share.quartz.service.HelloSpringService;
import com.ph.share.share.quartz.tools.DFUtil;
import com.ph.share.share.quartz.tools.SpringContextUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.StringJoiner;

public class HelloDataJob implements Job {
    @Autowired
    private HelloSpringService helloSpringService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();
        StringJoiner outer = new StringJoiner("  ")
                .add("HelloDataJob.execute")
                .add(DFUtil.format(new Date()))
                .add(Thread.currentThread().getName())
                .add(context.getTrigger().getKey().getName());
        System.out.println(outer);

//        System.out.println(jobDetail.getJobDataMap().get("key-1"));
//        System.out.println(trigger.getJobDataMap().get("key-2"));
        helloSpringService= (HelloSpringService) SpringContextUtil.applicationContext
                .getBean(StringUtils.uncapitalize(HelloSpringService.class.getSimpleName()));
        System.out.println(helloSpringService);
    }
}
