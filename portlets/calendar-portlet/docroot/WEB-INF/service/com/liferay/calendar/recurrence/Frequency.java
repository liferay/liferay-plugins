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

package com.liferay.calendar.recurrence;

/**
 * @author Marcellus Tavares
 */
public enum Frequency {

	DAILY("DAILY"), MONTHLY("MONTHLY"), WEEKLY("WEEKLY"), YEARLY("YEARLY");

	public static Frequency parse(String value) {
		if (DAILY.getValue().equals(value)) {
			return DAILY;
		}
		else if (MONTHLY.getValue().equals(value)) {
			return MONTHLY;
		}
		else if (WEEKLY.getValue().equals(value)) {
			return WEEKLY;
		}
		else if (YEARLY.getValue().equals(value)) {
			return YEARLY;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private Frequency(String value) {
		_value = value;
	}

	private String _value;

}