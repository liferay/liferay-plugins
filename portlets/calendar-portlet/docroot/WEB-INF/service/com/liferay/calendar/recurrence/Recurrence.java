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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Marcellus Tavares
 */
public class Recurrence {

	public void addExceptionDate(Calendar calendar) {
		_exceptionJCalendars.add(calendar);
	}

	public Recurrence clone() {
		Recurrence recurrence = new Recurrence();

		recurrence.setCount(_count);
		recurrence.setExceptionJCalendars(
			new ArrayList<Calendar>(_exceptionJCalendars));
		recurrence.setFrequency(_frequency);
		recurrence.setInterval(_interval);
		recurrence.setMonths(new ArrayList<Integer>(_months));
		recurrence.setPositionalWeekdays(
			new ArrayList<PositionalWeekday>(_positionalWeekdays));
		recurrence.setTimeZone(_timeZone);

		Calendar untilJCalendar = null;

		if (_untilJCalendar != null) {
			untilJCalendar = (Calendar)_untilJCalendar.clone();
		}

		recurrence.setUntilJCalendar(untilJCalendar);

		return recurrence;
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

	public List<Integer> getMonths() {
		return _months;
	}

	public PositionalWeekday getPositionalWeekday() {
		if (_positionalWeekdays.isEmpty()) {
			return null;
		}

		return _positionalWeekdays.get(0);
	}

	public List<PositionalWeekday> getPositionalWeekdays() {
		return _positionalWeekdays;
	}

	public TimeZone getTimeZone() {
		return _timeZone;
	}

	public Calendar getUntilJCalendar() {
		return _untilJCalendar;
	}

	public List<Weekday> getWeekdays() {
		List<Weekday> weekdays = new ArrayList<Weekday>();

		for (PositionalWeekday positionalWeekday : _positionalWeekdays) {
			weekdays.add(positionalWeekday.getWeekday());
		}

		return weekdays;
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

	public void setMonths(List<Integer> months) {
		_months = months;
	}

	public void setPositionalWeekdays(
		List<PositionalWeekday> positionalWeekdays) {

		_positionalWeekdays = positionalWeekdays;
	}

	public void setTimeZone(TimeZone timeZone) {
		_timeZone = timeZone;
	}

	public void setUntilJCalendar(Calendar untilJCalendar) {
		_untilJCalendar = untilJCalendar;
	}

	private int _count;
	private List<Calendar> _exceptionJCalendars = new ArrayList<Calendar>();
	private Frequency _frequency;
	private int _interval;
	private List<Integer> _months = Collections.emptyList();
	private List<PositionalWeekday> _positionalWeekdays =
		Collections.emptyList();
	private TimeZone _timeZone;
	private Calendar _untilJCalendar;

}