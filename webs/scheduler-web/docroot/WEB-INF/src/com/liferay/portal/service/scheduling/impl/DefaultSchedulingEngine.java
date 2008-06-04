/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.service.scheduling.impl;

import com.liferay.portal.kernel.job.SchedulingRequest;
import com.liferay.portal.service.scheduling.SchedulingEngine;
import com.liferay.portal.service.scheduling.SchedulingException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * <a href="DefaultSchedulingEngine.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 *
 */
public class DefaultSchedulingEngine implements SchedulingEngine {

	public DefaultSchedulingEngine(String quartzConfigLocation)
    	throws SchedulingException {

    	try {
	    	Properties props = new Properties();
	    	props.load(getClass().getResourceAsStream(quartzConfigLocation));

	    	StdSchedulerFactory factory = new StdSchedulerFactory();
	    	factory.initialize(props);

	    	_scheduler = factory.getScheduler();
    	}
    	catch (Exception e) {
    		throw new SchedulingException("Unable to initialize engine", e);
    	}
    }

	public Collection<SchedulingRequest> retrieveScheduledJobs(String groupName)
		throws SchedulingException {

		try {
			String names[] = _scheduler.getJobNames(groupName);

			List<SchedulingRequest> requests =
				new ArrayList<SchedulingRequest>();

			for (int i = 0; i < names.length; i++) {
				JobDetail detail = _scheduler.getJobDetail(names[i], groupName);

				String messageBody = detail.getJobDataMap().getString(
			    	SchedulingEngine.MESSAGE_BODY);

				CronTrigger trigger = (CronTrigger)_scheduler.getTrigger(
					names[i], groupName);

				SchedulingRequest sr = new SchedulingRequest(
					trigger.getCronExpression(), groupName, names[i],
					messageBody, trigger.getStartTime(), trigger.getEndTime());

				requests.add(sr);
			}

			return requests;
		}
    	catch (SchedulerException se) {
    		throw new SchedulingException("Unable to retrieve job", se);
    	}
	}

    public void schedule(
    		String jobName, String groupName, String cronText,
    		String destinationName, String messageBody, Date startDate,
    		Date endDate)
    	throws SchedulingException {

    	try {
    		CronTrigger trigger = new CronTrigger(jobName, groupName, cronText);

    		if (startDate != null) {
    			trigger.setStartTime(startDate);
    		}

    		if (endDate != null) {
    			trigger.setEndTime(endDate);
    		}

    		JobDetail detail = new JobDetail(
    			jobName, groupName, MessageSenderJob.class);

    		detail.getJobDataMap().put(DESTINATION_NAME, destinationName);
    		detail.getJobDataMap().put(MESSAGE_BODY, messageBody);

    		_scheduler.scheduleJob(detail, trigger);
    	}
    	catch (ObjectAlreadyExistsException oare) {
    		if (_log.isInfoEnabled()) {
    			_log.info("Message is already scheduled");
    		}
    	}
    	catch (ParseException pe) {
    		throw new SchedulingException("Unable to parse cron text", pe);
    	}
    	catch (SchedulerException se) {
    		throw new SchedulingException("Unable to scheduled job", se);
    	}
    }

    public void shutdown() throws SchedulingException {
    	try {
    		_scheduler.shutdown(false);
    	}
    	catch (SchedulerException se) {
    		throw new SchedulingException("Unable to shutdown scheduler", se);
    	}
    }

    public void start() throws SchedulingException {
        try {
            _scheduler.start();
        }
        catch (SchedulerException se) {
            throw new SchedulingException("Unable to start scheduler", se);
        }
    }

    public void unschedule(String jobName, String groupName)
	    throws SchedulingException {

	    try {
	        _scheduler.unscheduleJob(jobName, groupName);
	    }
	    catch (SchedulerException se) {
	        throw new SchedulingException(
	        	"Unable to unschedule job: " + jobName + "," + groupName , se);
	    }
	}

    private Log _log = LogFactory.getLog(DefaultSchedulingEngine.class);

    private Scheduler _scheduler;

}