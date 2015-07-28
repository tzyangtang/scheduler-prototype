package org.tzyangtang.scheduler.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

	private Logger log = Logger.getLogger(TestJob.class);

	public void execute(final JobExecutionContext ctx)
			throws JobExecutionException {

		log.info("TestJob run successfully");

		System.out.println("Executing Job");

	}
}
