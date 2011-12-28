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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Griffin
 */
public class Customer {

	public Customer(long customerId, String firstName, String lastName) {
		_customerId = customerId;
		_firstName = firstName;
		_lastName = lastName;
	}

	public List<Booking> getBookings() {
		if (_bookings == null) {
			_bookings = new ArrayList<Booking>();
		}

		return _bookings;
	}

	public long getCustomerId() {
		return _customerId;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setBookings(List<Booking> bookings) {
		_bookings = bookings;
	}

	public void setCustomerId(long customerId) {
		_customerId = customerId;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setSelected(boolean selected) {
		_selected = selected;
	}

	private List<Booking> _bookings;
	private long _customerId;
	private String _firstName;
	private String _lastName;
	private boolean _selected;

}