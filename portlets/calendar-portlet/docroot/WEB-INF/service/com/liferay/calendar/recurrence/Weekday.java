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

package com.liferay.calendar.recurrence;

import java.util.Calendar;

/**
 * @author Marcellus Tavares
 */
public enum Weekday {

	SUNDAY("SU", Calendar.SUNDAY), MONDAY("MO", Calendar.MONDAY),
	TUESDAY("TU", Calendar.TUESDAY), WEDNESDAY("WE", Calendar.WEDNESDAY),
	THURSDAY("TH", Calendar.THURSDAY), FRIDAY("FR", Calendar.FRIDAY),
	SATURDAY("SA", Calendar.SATURDAY);

	public static Weekday getWeekday(Calendar jCalendar) {
		return getWeekday(jCalendar.get(Calendar.DAY_OF_WEEK));
	}

	public static Weekday getWeekday(int dayOfTheWeek) {
		if (dayOfTheWeek == Calendar.SUNDAY) {
			return SUNDAY;
		}
		else if (dayOfTheWeek == Calendar.MONDAY) {
			return MONDAY;
		}
		else if (dayOfTheWeek == Calendar.TUESDAY) {
			return TUESDAY;
		}
		else if (dayOfTheWeek == Calendar.WEDNESDAY) {
			return WEDNESDAY;
		}
		else if (dayOfTheWeek == Calendar.THURSDAY) {
			return THURSDAY;
		}
		else if (dayOfTheWeek == Calendar.FRIDAY) {
			return FRIDAY;
		}
		else if (dayOfTheWeek == Calendar.SATURDAY) {
			return SATURDAY;
		}

		throw new IllegalArgumentException("Invalid value " + dayOfTheWeek);
	}

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

	public int getCalendarWeekday() {
		return _calendarWeekday;
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private Weekday(String value, int calendarWeekday) {
		_value = value;
		_calendarWeekday = calendarWeekday;
	}

	private int _calendarWeekday;
	private String _value;

}