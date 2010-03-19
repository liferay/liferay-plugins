/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

/**
 * <a href="DurationScale.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public enum DurationScale {
	DAYS("days"), HOURS("hours"), MINUTES("minutes"),
	MONTHS("months"), SECONDS("seconds"), YEARS("years");

	public static DurationScale getValue(String value) {
		if (DAYS._value.equals(value)) {
			return DAYS;
		}
		if (HOURS._value.equals(value)) {
			return HOURS;
		}
		if (MINUTES._value.equals(value)) {
			return MINUTES;
		}
		if (MONTHS._value.equals(value)) {
			return MONTHS;
		}
		if (SECONDS._value.equals(value)) {
			return SECONDS;
		}
		if (YEARS._value.equals(value)) {
			return YEARS;
		}

		throw new IllegalArgumentException("Invalid scale value: " + value);
	}

	public String getValue() {
		return _value;
	}

	private DurationScale(String value) {
		_value = value;
	}

	private String _value;
}
