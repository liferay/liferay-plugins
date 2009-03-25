/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.sn.jira.util;

import com.liferay.portal.kernel.util.Time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * <a href="JIRAUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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

		int jiraTimeZoneUTCOffset = jiraTimeZone.getRawOffset();

		if (jiraTimeZone.inDaylightTime(new Date())) {
			jiraTimeZoneUTCOffset += Time.HOUR;
		}

		return jiraTimeZoneUTCOffset;
	}

}