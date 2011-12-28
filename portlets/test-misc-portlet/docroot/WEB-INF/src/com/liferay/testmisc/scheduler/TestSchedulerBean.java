/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testmisc.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.TriggerState;
import com.liferay.testmisc.messaging.TestSchedulerMessageListener;

/**
 * @author Shuyang Zhou
 */
public class TestSchedulerBean {

	public void afterPropertiesSet() {
		try {
			Thread.sleep(1000);

			TriggerState triggerState = SchedulerEngineUtil.getJobState(
				TestSchedulerMessageListener.class.getName(),
				TestSchedulerMessageListener.class.getName(),
				StorageType.MEMORY);

			if (triggerState == null) {
				TestSchedulerUtil.setScheduledBeforeSpringInitialized(false);
			}
			else {
				TestSchedulerUtil.setScheduledBeforeSpringInitialized(true);
			}

			TestSchedulerUtil.setReceivedBeforeSpringInitialzed(
				TestSchedulerMessageListener.isReceived());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TestSchedulerBean.class);

}