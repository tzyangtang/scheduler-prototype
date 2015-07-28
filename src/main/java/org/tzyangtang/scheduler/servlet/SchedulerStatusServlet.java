package org.tzyangtang.scheduler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.tzyangtang.scheduler.model.QuartzJob;

public class SchedulerStatusServlet extends HttpServlet {
	Scheduler scheduler;
	private Logger log = Logger.getLogger(SchedulerStatusServlet.class);

	@Override
	public void init(ServletConfig cfg) throws ServletException {
		String key = "org.quartz.impl.StdSchedulerFactory.KEY";
		ServletContext servletContext = cfg.getServletContext();
		StdSchedulerFactory factory = (StdSchedulerFactory) servletContext
				.getAttribute(key);
		try {
			scheduler = factory.getScheduler("MyQuartzScheduler");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO use quartzScheduler here.
		super.init(cfg);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// PrintWriter out = resp.getWriter();
		try {
			List<QuartzJob> jobs = new ArrayList<QuartzJob>();
			for (String groupName : scheduler.getJobGroupNames()) {

				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher
						.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					// get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler
							.getTriggersOfJob(jobKey);
					Date nextFireTime = triggers.get(0).getNextFireTime();

					QuartzJob j = new QuartzJob(jobName, jobGroup, nextFireTime);
					jobs.add(j);
				}

			}

			req.setAttribute("jobs", jobs);
			getServletContext().getRequestDispatcher("/status.jsp").forward(
					req, resp);

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher
						.jobGroupEquals(groupName))) {
					String jobName = jobKey.getName();
					log.debug("req.getParameter(" + jobName + ")="
							+ req.getParameter(jobName));
					if (req.getParameter(jobName) != null) {
						scheduler.triggerJob(jobKey);
						break;
					}
				}
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.sendRedirect("status");
	}

}
