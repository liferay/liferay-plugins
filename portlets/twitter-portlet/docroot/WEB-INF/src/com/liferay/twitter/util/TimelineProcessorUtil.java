/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.InstanceFactory;

/**
 * @author Shinn Lok
 */
public class TimelineProcessorUtil {

	public static TimelineProcessor getInstance() {
		return _timelineProcessor;
	}

	public static JSONArray getUserTimelineJSONArray(
		String twitterScreenName, long sinceId) {

		return getInstance().getUserTimelineJSONArray(
			twitterScreenName, sinceId);
	}

	private static TimelineProcessor _timelineProcessor;

	static {
		try {
			_timelineProcessor = (TimelineProcessor)InstanceFactory.newInstance(
				PortletPropsValues.TWITTER_USERS_TIMELINE_PROCESSOR);
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

}