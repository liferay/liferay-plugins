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

package com.liferay.calendar.util;

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Eduardo Lundgren
 * @author Peter Shin
 * @author Fabio Pezzutto
 */
public class JCalendarUtil {

	public static Date getDate(Date date, TimeZone timeZone) {
		Calendar jCalendar = getJCalendar(date, timeZone);

		return jCalendar.getTime();
	}

	public static Calendar getJCalendar(Calendar jCalendar, TimeZone timeZone) {
		return getJCalendar(
			jCalendar.get(Calendar.YEAR), jCalendar.get(Calendar.MONTH),
			jCalendar.get(Calendar.DATE), jCalendar.get(Calendar.HOUR_OF_DAY),
			jCalendar.get(Calendar.MINUTE), jCalendar.get(Calendar.SECOND),
			jCalendar.get(Calendar.MILLISECOND), timeZone);
	}

	public static Calendar getJCalendar(Date date, TimeZone timeZone) {
		Calendar jCalendar = CalendarFactoryUtil.getCalendar(_utcTimeZone);

		jCalendar.setTime(date);

		return getJCalendar(jCalendar, timeZone);
	}

	public static Calendar getJCalendar(
		int year, int month, int day, int hour, int minutes, int seconds,
		int milliseconds, TimeZone timeZone) {

		Calendar jCalendar = CalendarFactoryUtil.getCalendar(timeZone);

		jCalendar.set(Calendar.YEAR, year);
		jCalendar.set(Calendar.MONTH, month);
		jCalendar.set(Calendar.DATE, day);
		jCalendar.set(Calendar.HOUR_OF_DAY, hour);
		jCalendar.set(Calendar.MINUTE, minutes);
		jCalendar.set(Calendar.SECOND, seconds);
		jCalendar.set(Calendar.MILLISECOND, milliseconds);

		return jCalendar;
	}

	public static Calendar getJCalendar(long time) {
		Calendar jCalendar = CalendarFactoryUtil.getCalendar(_utcTimeZone);

		jCalendar.setTimeInMillis(time);

		return jCalendar;
	}

	public static Calendar getJCalendar(long time, TimeZone timeZone) {
		return getJCalendar(new Date(time), timeZone);
	}

	public static int getTimeZoneOffset(TimeZone timeZone) {
		return timeZone.getRawOffset();
	}

	public static Calendar toLastHourJCalendar(Calendar jCalendar) {
		Calendar lastHourJCalendar = (Calendar)jCalendar.clone();

		lastHourJCalendar.set(Calendar.HOUR_OF_DAY, 23);
		lastHourJCalendar.set(Calendar.MINUTE, 59);
		lastHourJCalendar.set(Calendar.SECOND, 59);
		lastHourJCalendar.set(Calendar.MILLISECOND, 999);

		return lastHourJCalendar;
	}

	public static Calendar toMidnightJCalendar(Calendar jCalendar) {
		Calendar midnightJCalendar = (Calendar)jCalendar.clone();

		midnightJCalendar.set(Calendar.HOUR_OF_DAY, 0);
		midnightJCalendar.set(Calendar.MINUTE, 0);
		midnightJCalendar.set(Calendar.SECOND, 0);
		midnightJCalendar.set(Calendar.MILLISECOND, 0);

		return midnightJCalendar;
	}

	private static TimeZone _utcTimeZone = TimeZone.getTimeZone(StringPool.UTC);

}