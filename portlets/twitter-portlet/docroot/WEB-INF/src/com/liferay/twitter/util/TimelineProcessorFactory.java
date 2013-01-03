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

package com.liferay.twitter.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;

/**
 * @author Shinn Lok
 */
public class TimelineProcessorFactory {

	public static TimelineProcessor getInstance() {
		if (_timelineProcessor == null) {
			try {
				_timelineProcessor =
					(TimelineProcessor)InstanceFactory.newInstance(
						PortletPropsValues.USERS_TIMELINE_PROCESSOR);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return _timelineProcessor;
	}

	public static void setInstance(TimelineProcessor timelineProcessor) {
		_timelineProcessor = timelineProcessor;
	}

	private static Log _log = LogFactoryUtil.getLog(
		TimelineProcessorFactory.class);

	private static TimelineProcessor _timelineProcessor;

}