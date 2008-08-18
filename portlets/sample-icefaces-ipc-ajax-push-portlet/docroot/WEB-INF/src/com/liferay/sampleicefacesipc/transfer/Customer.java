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

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="Customer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
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