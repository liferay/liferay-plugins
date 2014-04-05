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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.compat.portal.kernel.scheduler.CronText;

/**
 * @author Michael C. Han
 * @author Peter Borkuti
 */
public enum DurationScale {

	DAY("day", CronText.DAILY_FREQUENCY),
	HOUR("hour", CronText.HOURLY_FREQUENCY),
	MINUTE("minute", CronText.MINUTELY_FREQUENCY),
	MONTH("month", CronText.MONTHLY_FREQUENCY),
	SECOND("second", CronText.SECONDLY_FREQUENCY),
	WEEK("week", CronText.WEEKLY_FREQUENCY),
	YEAR("year", CronText.YEARLY_FREQUENCY);

	public static DurationScale parse(String value) {
		for (DurationScale durationScale : DurationScale.values()) {
			if (durationScale.getValue().equals(value)) {
				return durationScale;
			}
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public int getIntegerValue() {
		return _frequency;
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private DurationScale(String value, int frequency) {
		_value = value;
		_frequency = frequency;
	}

	private int _frequency;
	private String _value;

}