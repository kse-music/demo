package com.hiekn.demo.test.quartz;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class HelloQuartz implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail detail = context.getJobDetail();
		Object name = detail.getJobDataMap().get("name");
		System.out.println("say hello to " + name + " at " + new Date());
	}

}
