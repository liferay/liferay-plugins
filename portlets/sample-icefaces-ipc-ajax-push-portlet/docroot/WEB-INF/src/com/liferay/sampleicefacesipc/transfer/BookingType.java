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

/**
 * @author Neil Griffin
 */
public class BookingType {

	public BookingType(long bookingTypeId, String bookingTypeName) {
		_bookingTypeId = bookingTypeId;
		_bookingTypeName = bookingTypeName;
	}

	public String getBookingTypeName() {
		return _bookingTypeName;
	}

	public long getBookingTypeId() {
		return _bookingTypeId;
	}

	public void setBookingTypeName(String bookingTypeName) {
		_bookingTypeName = bookingTypeName;
	}

	public void setBookingTypeId(long bookingTypeId) {
		_bookingTypeId = bookingTypeId;
	}

	private String _bookingTypeName;
	private long _bookingTypeId;

}