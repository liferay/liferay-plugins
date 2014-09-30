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

package com.liferay.compat.portal.kernel.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;

import java.text.Format;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class Time extends com.liferay.portal.kernel.util.Time {

	public static final String DURATION_FORMAT = "HH:mm:ss.SSS";

	public static String getDuration(long milliseconds) {
		return getSimpleDate(new Date(milliseconds), DURATION_FORMAT);
	}

	public static String getRelativeTimeDescription(
		Date date, Locale locale, TimeZone timeZone) {

		return getRelativeTimeDescription(date.getTime(), locale, timeZone);
	}

	public static String getRelativeTimeDescription(
		long milliseconds, Locale locale, TimeZone timeZone) {

		Format timeFormat = FastDateFormatFactoryUtil.getTime(locale, timeZone);

		int daysBetween = DateUtil.getDaysBetween(
			new Date(milliseconds), new Date(), timeZone);

		long millisAgo = System.currentTimeMillis() - milliseconds;

		if (millisAgo < Time.HOUR) {
			long minutes = millisAgo / Time.MINUTE;

			if (minutes <= 1) {
				return LanguageUtil.get(locale, "about-a-minute-ago");
			}

			return LanguageUtil.format(locale, "x-minutes-ago", minutes);
		}
		else if ((millisAgo / Time.HOUR) == 1) {
			return LanguageUtil.get(locale, "about-an-hour-ago");
		}
		else if ((millisAgo < Time.DAY) || (daysBetween == 0)) {
			return LanguageUtil.format(
				locale, "x-hours-ago", (millisAgo / Time.HOUR));
		}
		else if (daysBetween == 1) {
			return LanguageUtil.format(
				locale, "yesterday-at-x", timeFormat.format(milliseconds));
		}

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"EEEE, MMMMM dd, yyyy", locale, timeZone);

		return dateFormat.format(milliseconds);
	}

}