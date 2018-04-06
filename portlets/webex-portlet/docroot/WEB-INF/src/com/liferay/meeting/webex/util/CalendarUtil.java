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

package com.liferay.meeting.webex.util;

import com.liferay.meeting.MeetingException;

import com.webex.schemas.x2002.x06.common.TimeZoneType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class CalendarUtil {

	public static Calendar getCalendar(String timeZoneString, String dateString)
		throws MeetingException {

		TimeZoneType.Enum timeZoneTypeEnum = TimeZoneType.Enum.forString(
			timeZoneString);

		if (timeZoneTypeEnum == null) {
			timeZoneTypeEnum = TimeZoneUtil.convert(timeZoneString);
		}

		return getCalendar(timeZoneTypeEnum, dateString);
	}

	public static Calendar getCalendar(
			TimeZoneType.Enum timeZoneTypeEnum, String dateString)
		throws MeetingException {

		TimeZone timeZone = TimeZoneUtil.convert(timeZoneTypeEnum);

		Calendar calendar = Calendar.getInstance(timeZone);

		DateFormat dateFormat = new SimpleDateFormat(
			WebExConstants.DATE_PATTERN);

		Date date = null;

		try {
			date = dateFormat.parse(dateString);
		}
		catch (ParseException pe) {
			throw new MeetingException("Unable to parse " + dateString, pe);
		}

		calendar.setTime(date);

		return calendar;
	}

}