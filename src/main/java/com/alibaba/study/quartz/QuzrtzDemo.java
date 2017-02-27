package com.alibaba.study.quartz;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QuzrtzDemo {
	public void run() throws Exception {
        Logger log = LoggerFactory.getLogger(QuzrtzDemo.class);

        log.info("------- Initializing ----------------------");

        // 定义调度器
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete -----------");

        // 获取当前时间的下一分钟
        Date runTime = evenMinuteDate(new Date());

        log.info("------- Scheduling Job  -------------------");

        // 定义job
        // 在quartz中，有组的概念，组+job名称 唯一的
        JobDetail job = newJob(WtJob.class).withIdentity("job1", "group1").build();

        // 定义触发器，在下一分钟启动
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        // 将job注册到调度器
        sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + runTime);

        // 启动调度器
        sched.start();

        log.info("------- Started Scheduler -----------------");

        // 等待65秒
        log.info("------- Waiting 65 seconds... -------------");
        try {
            // wait 65 seconds to show job
            Thread.sleep(65L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        // 关闭调度器
        log.info("------- Shutting Down ---------------------");
        sched.shutdown(true);
        log.info("------- Shutdown Complete -----------------");
    }

    public static void main(String[] args) throws Exception {

    	QuzrtzDemo qd = new QuzrtzDemo();
    	qd.run();

    }
}
