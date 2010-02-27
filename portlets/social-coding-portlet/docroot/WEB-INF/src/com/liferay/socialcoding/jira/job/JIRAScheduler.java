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

package com.liferay.socialcoding.jira.job;

import com.liferay.portal.kernel.job.JobSchedulerUtil;
import com.liferay.portal.kernel.job.Scheduler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * <a href="JIRAScheduler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAScheduler implements Scheduler {

	public void schedule() {
		if (SynchronizeJIRAJob.INTERVAL <= 0) {
			if (_log.isDebugEnabled()) {
				_log.debug("Synchronization of JIRA is disabled");
			}

			_synchronizeJIRAJob = null;
		}

		JobSchedulerUtil.schedule(_synchronizeJIRAJob);
	}

	public void unschedule() {
		JobSchedulerUtil.unschedule(_synchronizeJIRAJob);
	}

	private static Log _log = LogFactoryUtil.getLog(JIRAScheduler.class);

	private SynchronizeJIRAJob _synchronizeJIRAJob = new SynchronizeJIRAJob();

}