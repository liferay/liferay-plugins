/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.scheduler.CronText;

/**
 * @author Michael C. Han
 */
public enum DurationScale {

	DAY("day"), HOUR("hour"), MINUTE("minute"), MONTH("month"),
	SECOND("second"), WEEK("week"), YEAR("year");

	public static DurationScale parse(String value) {
		if (DAY.getValue().equals(value)) {
			return DAY;
		}
		else if (HOUR.getValue().equals(value)) {
			return HOUR;
		}
		else if (MINUTE.getValue().equals(value)) {
			return MINUTE;
		}
		else if (MONTH.getValue().equals(value)) {
			return MONTH;
		}
		else if (SECOND.getValue().equals(value)) {
			return SECOND;
		}
		else if (YEAR.getValue().equals(value)) {
			return YEAR;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public int getIntegerValue() {
		if (equals(DAY)) {
			return CronText.DAILY_FREQUENCY;
		}
		else if (equals(HOUR)) {
			return CronText.HOURLY_FREQUENCY;
		}
		else if (equals(MINUTE)) {
			return CronText.MINUTELY_FREQUENCY;
		}
		else if (equals(MONTH)) {
			return CronText.MONTHLY_FREQUENCY;
		}
		else if (equals(WEEK)) {
			return CronText.WEEKLY_FREQUENCY;
		}
		else {
			return CronText.YEARLY_FREQUENCY;
		}
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private DurationScale(String value) {
		_value = value;
	}

	private String _value;

}