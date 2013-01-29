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

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

import java.text.Format;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Bruno Basto
 */
public class RSSUtil extends com.liferay.util.RSSUtil {

	public static final long TIME_INTERVAL_DEFAULT = Time.WEEK;

	public static String getContent(
		CalendarBooking calendarBooking, String displayStyle,
		ThemeDisplay themeDisplay) {

		Locale locale = themeDisplay.getLocale();

		String content = null;

		if (displayStyle.equals(DISPLAY_STYLE_ABSTRACT)) {
			content = StringUtil.shorten(
				calendarBooking.getDescription(locale), 200);
		}
		else if (displayStyle.equals(DISPLAY_STYLE_TITLE)) {
			content = calendarBooking.getTitle(locale);
		}
		else {
			TimeZone timeZone = themeDisplay.getTimeZone();

			String rssEntryTemplate = ContentUtil.get(
				PortletProps.get(PortletPropsKeys.CALENDAR_RSS_TEMPLATE));

			Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
				locale, timeZone);

			content = StringUtil.replace(
				rssEntryTemplate,
				new String[] {
					"[$BOOKING_LOCATION$]", "[$BOOKING_START_DATE$]",
					"[$BOOKING_END_DATE$]", "[$BOOKING_TITLE$]",
					"[$BOOKING_DESCRIPTION$]"
				},
				new String[] {
					calendarBooking.getLocation(),
					dateFormatDateTime.format(calendarBooking.getStartTime()),
					dateFormatDateTime.format(calendarBooking.getEndTime()),
					calendarBooking.getTitle(locale),
					calendarBooking.getDescription(locale),
				});
		}

		return content;
	}

}