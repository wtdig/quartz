package com.alibaba.study.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WtJob implements Job{
	
	
	public WtJob() {
		 
	}

	private static Logger logger = LoggerFactory.getLogger(WtJob.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("我的任务"+new Date());
	}

}
