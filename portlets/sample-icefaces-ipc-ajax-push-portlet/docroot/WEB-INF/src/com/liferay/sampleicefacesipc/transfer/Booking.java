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

package com.liferay.sampleicefacesipc.transfer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Neil Griffin
 */
public class Booking {

	public Booking(long bookingTypeId, long customerId) {
		Calendar today = GregorianCalendar.getInstance();

		Calendar weekFromToday = (Calendar)today.clone();

		weekFromToday.add(Calendar.DATE, 7);

		_bookingTypeId = bookingTypeId;
		_customerId = customerId;
		_startDate = new Date(today.getTimeInMillis());
		_finishDate = new Date(weekFromToday.getTimeInMillis());
	}

	public Booking(
		long bookingTypeId, long customerId, Date startDate, Date finishDate) {

		_bookingTypeId = bookingTypeId;
		_customerId = customerId;
		_startDate = startDate;
		_finishDate = finishDate;
	}

	public long getBookingTypeId() {
		return _bookingTypeId;
	}

	public long getCustomerId() {
		return _customerId;
	}

	public Date getFinishDate() {
		return _finishDate;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setBookingTypeId(long bookingTypeId) {
		_bookingTypeId = bookingTypeId;
	}

	public void setCustomerId(long customerId) {
		_customerId = customerId;
	}

	public void setFinishDate(Date finishDate) {
		_finishDate = finishDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	private long _bookingTypeId;
	private long _customerId;
	private Date _startDate;
	private Date _finishDate;

}