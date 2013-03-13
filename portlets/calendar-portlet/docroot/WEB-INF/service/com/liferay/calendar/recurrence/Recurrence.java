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

package com.liferay.calendar.recurrence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class Recurrence {

	public void addExceptionDate(Calendar calendar) {
		_exceptionJCalendars.add(calendar);
	}

	public int getCount() {
		return _count;
	}

	public List<Calendar> getExceptionJCalendars() {
		return _exceptionJCalendars;
	}

	public Frequency getFrequency() {
		return _frequency;
	}

	public int getInterval() {
		return _interval;
	}

	public Calendar getUntilJCalendar() {
		return _untilJCalendar;
	}

	public List<Weekday> getWeekdays() {
		return _weekdays;
	}

	public void setCount(int count) {
		_count = count;
	}

	public void setExceptionJCalendars(List<Calendar> exceptionJCalendars) {
		_exceptionJCalendars = exceptionJCalendars;
	}

	public void setFrequency(Frequency frequency) {
		_frequency = frequency;
	}

	public void setInterval(int interval) {
		_interval = interval;
	}

	public void setUntilJCalendar(Calendar untilJCalendar) {
		_untilJCalendar = untilJCalendar;
	}

	public void setWeekdays(List<Weekday> weekdays) {
		_weekdays = weekdays;
	}

	private int _count;
	private List<Calendar> _exceptionJCalendars = new ArrayList<Calendar>();
	private Frequency _frequency;
	private int _interval;
	private Calendar _untilJCalendar;
	private List<Weekday> _weekdays;

}