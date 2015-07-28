package org.tzyangtang.scheduler.model;

import java.util.Date;

public class QuartzJob {
	private String jobName;
	private String groupName;
	private Date nextFireTime;
	
	public QuartzJob(String jobName, String groupName, Date nextFireTime) {
		super();
		this.jobName = jobName;
		this.groupName = groupName;
		this.nextFireTime = nextFireTime;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Date getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	
	
}
