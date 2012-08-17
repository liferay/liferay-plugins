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
public enum Weekday {

	SUNDAY("SU"), MONDAY("MO"), TUESDAY("TU"), WEDNESDAY("WE"), THURSDAY("TH"),
	FRIDAY("FR"), SATURDAY("SA");

	public static Weekday parse(String value) {
		if (SUNDAY.getValue().equals(value)) {
			return SUNDAY;
		}
		else if (MONDAY.getValue().equals(value)) {
			return MONDAY;
		}
		else if (TUESDAY.getValue().equals(value)) {
			return TUESDAY;
		}
		else if (WEDNESDAY.getValue().equals(value)) {
			return WEDNESDAY;
		}
		else if (THURSDAY.getValue().equals(value)) {
			return THURSDAY;
		}
		else if (FRIDAY.getValue().equals(value)) {
			return FRIDAY;
		}
		else if (SATURDAY.getValue().equals(value)) {
			return SATURDAY;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public int getPosition() {
		return _position;
	}

	public String getValue() {
		return _value;
	}

	public void setPosition(int position) {
		if ((position < -53) || (position > 53)) {
			throw new IllegalArgumentException();
		}

		_position = position;
	}

	@Override
	public String toString() {
		return _value;
	}

	private Weekday(String value) {
		_value = value;
	}

	private int _position;
	private String _value;

}