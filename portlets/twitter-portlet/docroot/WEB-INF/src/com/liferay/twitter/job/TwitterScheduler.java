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

package com.liferay.twitter.job;

import com.liferay.portal.kernel.job.JobSchedulerUtil;
import com.liferay.portal.kernel.job.Scheduler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * <a href="TwitterScheduler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TwitterScheduler implements Scheduler {

	public void schedule() {
		if (SynchronizeTwitterJob.INTERVAL <= 0) {
			if (_log.isDebugEnabled()) {
				_log.debug("Synchronization of Twitter is disabled");
			}

			_synchronizeTwitterJob = null;
		}

		JobSchedulerUtil.schedule(_synchronizeTwitterJob);
	}

	public void unschedule() {
		JobSchedulerUtil.unschedule(_synchronizeTwitterJob);
	}

	private static Log _log = LogFactoryUtil.getLog(TwitterScheduler.class);

	private SynchronizeTwitterJob _synchronizeTwitterJob =
		new SynchronizeTwitterJob();

}