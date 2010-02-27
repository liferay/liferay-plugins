/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.testmisc.job;

import com.liferay.portal.kernel.job.JobSchedulerUtil;
import com.liferay.portal.kernel.job.Scheduler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * <a href="TestScheduler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TestScheduler implements Scheduler {

	public void schedule() {
		if (_log.isInfoEnabled()) {
			_log.info("Schedule");
		}

		JobSchedulerUtil.schedule(_testIntervalJob);
	}

	public void unschedule() {
		if (_log.isInfoEnabled()) {
			_log.info("Unschedule");
		}

		JobSchedulerUtil.unschedule(_testIntervalJob);
	}

	private static Log _log = LogFactoryUtil.getLog(TestScheduler.class);

	private TestIntervalJob _testIntervalJob = new TestIntervalJob();

}