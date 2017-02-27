package com.alibaba.study.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
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



public class QuartzCrinDemo {
	 public void run() throws Exception {
	        Logger log = LoggerFactory.getLogger(QuartzCrinDemo.class);

	        log.info("------- Initializing ----------------------");

	        // 定义调度器
	        SchedulerFactory sf = new StdSchedulerFactory();
	        Scheduler sched = sf.getScheduler();

	        log.info("------- Initialization Complete -----------");

	        // 获取当前时间的下一分钟
	        Date runTime = evenMinuteDate(new Date());

	        log.info("------- Scheduling Job  -------------------");

	        // 定义job
	        JobDetail job = newJob(WtJob.class).withIdentity("job1", "group1").build();

	        // 定义触发器，每2秒执行一次
	        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
	                .withSchedule(cronSchedule("0/5 * * * * ?")).build();

	        // 将job注册到调度器
	        sched.scheduleJob(job, trigger);
	        log.info(job.getKey() + " will run at: " + runTime);

	        // 启动调度器
	        sched.start();

	        log.info("------- Started Scheduler -----------------");

	        // 等待1分钟
	        log.info("------- Waiting 60 seconds... -------------");
	        try {
	            Thread.sleep(60L * 1000L);
	        } catch (Exception e) {
	            //
	        }

	        // 关闭调度器
	        log.info("------- Shutting Down ---------------------");
	        sched.shutdown(true);
	        log.info("------- Shutdown Complete -----------------");
	    }

	    public static void main(String[] args) throws Exception {

	    	QuartzCrinDemo example = new QuartzCrinDemo();
	        example.run();

	    }
}
