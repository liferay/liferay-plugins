/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.sampleicefacesipc.transfer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <a href="Booking.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
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