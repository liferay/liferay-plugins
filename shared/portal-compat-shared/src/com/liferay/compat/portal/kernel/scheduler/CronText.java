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

package com.liferay.compat.portal.kernel.scheduler;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Calendar;

/**
 * @author Thiago Moreira
 */
public class CronText {

	public static final int DAILY_FREQUENCY = 4;

	public static final int HOURLY_FREQUENCY = 3;

	public static final int MINUTELY_FREQUENCY = 2;

	public static final int MONTHLY_FREQUENCY = 6;

	public static final int NO_FREQUENCY = 0;

	public static final int SECONDLY_FREQUENCY = 1;

	public static final int WEEKLY_FREQUENCY = 5;

	public static final int YEARLY_FREQUENCY = 7;

	public CronText(Calendar startDate) {
		this(startDate, CronText.NO_FREQUENCY, 0);
	}

	public CronText(Calendar startDate, int frequency, int interval) {
		_startDate = startDate;
		setFrequency(frequency);
		_interval = interval;
	}

	public int getFrequency() {
		return _frequency;
	}

	public int getInterval() {
		return _interval;
	}

	public Calendar getStartDate() {
		return _startDate;
	}

	public void setFrequency(int frequency) {
		if ((frequency != CronText.DAILY_FREQUENCY) &&
			(frequency != CronText.HOURLY_FREQUENCY) &&
			(frequency != CronText.MINUTELY_FREQUENCY) &&
			(frequency != CronText.MONTHLY_FREQUENCY) &&
			(frequency != CronText.NO_FREQUENCY) &&
			(frequency != CronText.SECONDLY_FREQUENCY) &&
			(frequency != CronText.WEEKLY_FREQUENCY) &&
			(frequency != CronText.YEARLY_FREQUENCY)) {

			throw new IllegalArgumentException(String.valueOf(frequency));
		}

		_frequency = frequency;
	}

	public void setInterval(int interval) {
		_interval = interval;
	}

	public void setStartDate(Calendar startDate) {
		_startDate = startDate;
	}

	@Override
	public String toString() {
		String second = String.valueOf(_startDate.get(Calendar.SECOND));
		String minute = String.valueOf(_startDate.get(Calendar.MINUTE));
		String hour = String.valueOf(_startDate.get(Calendar.HOUR_OF_DAY));
		String dayOfMonth = String.valueOf(
			_startDate.get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(_startDate.get(Calendar.MONTH) + 1);
		String dayOfWeek = String.valueOf(_startDate.get(Calendar.DAY_OF_WEEK));
		String year = String.valueOf(_startDate.get(Calendar.YEAR));

		if (_frequency == CronText.NO_FREQUENCY) {
			dayOfWeek = StringPool.QUESTION;
		}
		else if (_frequency == CronText.SECONDLY_FREQUENCY) {
			second = StringPool.STAR + StringPool.FORWARD_SLASH + _interval;
			minute = StringPool.STAR;
			hour = StringPool.STAR;
			dayOfMonth = StringPool.STAR;
			month = StringPool.STAR;
			dayOfWeek = StringPool.QUESTION;
			year = StringPool.STAR;
		}
		else if (_frequency == CronText.MINUTELY_FREQUENCY) {
			minute = StringPool.STAR + StringPool.FORWARD_SLASH + _interval;
			hour = StringPool.STAR;
			dayOfMonth = StringPool.STAR;
			month = StringPool.STAR;
			dayOfWeek = StringPool.QUESTION;
			year = StringPool.STAR;
		}
		else if (_frequency == CronText.HOURLY_FREQUENCY) {
			minute = StringPool.STAR;
			hour = StringPool.STAR + StringPool.FORWARD_SLASH + _interval;
			dayOfMonth = StringPool.STAR;
			month = StringPool.STAR;
			dayOfWeek = StringPool.QUESTION;
			year = StringPool.STAR;
		}
		else if (_frequency == CronText.DAILY_FREQUENCY) {
			dayOfMonth += StringPool.FORWARD_SLASH + _interval;
			month = StringPool.STAR;
			dayOfWeek = StringPool.QUESTION;
			year = StringPool.STAR;
		}
		else if (_frequency == CronText.WEEKLY_FREQUENCY) {
			dayOfMonth += StringPool.FORWARD_SLASH + (_interval * 7);
			month = StringPool.STAR;
			dayOfWeek = StringPool.QUESTION;
			year = StringPool.STAR;
		}
		else if (_frequency == CronText.MONTHLY_FREQUENCY) {
			month += StringPool.FORWARD_SLASH + _interval;
			dayOfWeek = StringPool.QUESTION;
			year = StringPool.STAR;
		}
		else if (_frequency == CronText.YEARLY_FREQUENCY) {
			dayOfWeek = StringPool.QUESTION;
			year += StringPool.FORWARD_SLASH + _interval;
		}

		StringBundler sb = new StringBundler(13);

		sb.append(second);
		sb.append(StringPool.SPACE);
		sb.append(minute);
		sb.append(StringPool.SPACE);
		sb.append(hour);
		sb.append(StringPool.SPACE);
		sb.append(dayOfMonth);
		sb.append(StringPool.SPACE);
		sb.append(month);
		sb.append(StringPool.SPACE);
		sb.append(dayOfWeek);
		sb.append(StringPool.SPACE);
		sb.append(year);

		return sb.toString();
	}

	private int _frequency;
	private int _interval;
	private Calendar _startDate;

}