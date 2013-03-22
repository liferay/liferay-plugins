/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.activities.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.text.Format;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Evan Thibodeau
 */
public class Time extends com.liferay.portal.kernel.util.Time {

	public static String getRelativeTimeSpan(
		long milliseconds, Locale locale, TimeZone tz) {

		Format dateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"EEEE, MMMMM dd, yyyy", locale, tz);

		Format timeFormatDate = FastDateFormatFactoryUtil.getTime(locale, tz);

		int curDaysBetween = DateUtil.getDaysBetween(
			new Date(milliseconds), new Date(), tz);

		long millisAgo = System.currentTimeMillis() - milliseconds;

		String relativeTime = StringPool.BLANK;

		if (millisAgo <= MINUTE) {
			relativeTime = "about-a-minute-ago";
		}
		else if (millisAgo < HOUR) {
			relativeTime = LanguageUtil.format(
				locale, "x-minutes-ago", (millisAgo / MINUTE));
		}
		else if ((millisAgo / HOUR) == 1) {
			relativeTime = "about-an-hour-ago";
		}
		else if ((millisAgo < DAY) || (curDaysBetween == 0)) {
			relativeTime = LanguageUtil.format(
				locale, "x-hours-ago", (millisAgo / HOUR));
		}
		else if (curDaysBetween == 1) {
			relativeTime =
				LanguageUtil.format(
					locale, "yesterday-at-x",
					timeFormatDate.format(milliseconds));
		}
		else {
			relativeTime = dateFormatDate.format(milliseconds);
		}

		return relativeTime;
	}

}