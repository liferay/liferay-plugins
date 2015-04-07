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

package com.liferay.calendar.util;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

import java.text.Format;

/**
 * @author Bruno Basto
 */
public class RSSUtil extends com.liferay.util.RSSUtil {

	public static final long TIME_INTERVAL_DEFAULT = Time.WEEK;

	public static String getContent(
		CalendarBooking calendarBooking, String displayStyle,
		ThemeDisplay themeDisplay) {

		if (displayStyle.equals(DISPLAY_STYLE_ABSTRACT)) {
			return StringUtil.shorten(
				calendarBooking.getDescription(themeDisplay.getLocale()), 200);
		}

		if (displayStyle.equals(DISPLAY_STYLE_TITLE)) {
			return calendarBooking.getTitle(themeDisplay.getLocale());
		}

		String content = ContentUtil.get(
			PortletProps.get(PortletPropsKeys.CALENDAR_RSS_TEMPLATE));

		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			themeDisplay.getLocale(),
			CalendarUtil.getCalendarBookingDisplayTimeZone(
				calendarBooking, themeDisplay.getTimeZone()));

		content = StringUtil.replace(
			content,
			new String[] {
				"[$EVENT_DESCRIPTION$]", "[$EVENT_END_DATE$]",
				"[$EVENT_LOCATION$]", "[$EVENT_START_DATE$]", "[$EVENT_TITLE$]"
			},
			new String[] {
				calendarBooking.getDescription(themeDisplay.getLocale()),
				dateFormatDateTime.format(calendarBooking.getEndTime()),
				calendarBooking.getLocation(),
				dateFormatDateTime.format(calendarBooking.getStartTime()),
				calendarBooking.getTitle(themeDisplay.getLocale())
			});

		return content;
	}

}