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

import java.util.Calendar;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class Recurrence {

	public int getCount() {
		return _count;
	}

	public Frequency getFrequency() {
		return _frequency;
	}

	public int getInterval() {
		return _interval;
	}

	public Calendar getUntil() {
		return _until;
	}

	public List<Weekday> getWeekdays() {
		return _weekdays;
	}

	public void setCount(int count) {
		_count = count;
	}

	public void setFrequency(Frequency frequency) {
		_frequency = frequency;
	}

	public void setInterval(int interval) {
		_interval = interval;
	}

	public void setUntil(Calendar until) {
		_until = until;
	}

	public void setWeekdays(List<Weekday> weekdays) {
		_weekdays = weekdays;
	}

	private int _count;
	private Frequency _frequency;
	private int _interval;
	private Calendar _until;
	private List<Weekday> _weekdays;

}