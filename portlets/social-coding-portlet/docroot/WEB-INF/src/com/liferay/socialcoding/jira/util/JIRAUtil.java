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

package com.liferay.socialcoding.jira.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAUtil {

	public static Date getJIRADate(Date liferayDate) {
		Calendar cal = new GregorianCalendar(_getTimeZone());

		cal.setTime(liferayDate);
		cal.add(Calendar.MILLISECOND, _getUTCOffset());

		return cal.getTime();
	}

	public static Date getJIRADate(int weeksFromNow) {
		Calendar cal = new GregorianCalendar(_getTimeZone());

		cal.add(Calendar.WEEK_OF_YEAR, weeksFromNow);
		cal.add(Calendar.MILLISECOND, _getUTCOffset());

		return cal.getTime();
	}

	public static Date getLiferayDate(Date jiraDate) {
		Calendar cal = new GregorianCalendar(_getTimeZone());

		cal.setTime(jiraDate);
		cal.add(Calendar.MILLISECOND, _getUTCOffset() * -1);

		return cal.getTime();
	}

	private static TimeZone _getTimeZone() {
		return TimeZone.getTimeZone("GMT");
	}

	private static int _getUTCOffset() {
		TimeZone jiraTimeZone = TimeZone.getTimeZone("America/Los_Angeles");

		return jiraTimeZone.getOffset(System.currentTimeMillis());
	}

}