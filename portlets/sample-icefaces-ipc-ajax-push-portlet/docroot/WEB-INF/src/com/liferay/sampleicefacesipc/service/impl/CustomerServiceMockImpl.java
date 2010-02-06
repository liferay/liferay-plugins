/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleicefacesipc.service.impl;

import com.liferay.sampleicefacesipc.service.BookingService;
import com.liferay.sampleicefacesipc.service.CustomerService;
import com.liferay.sampleicefacesipc.transfer.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="CustomerServiceMockImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
 */
public class CustomerServiceMockImpl implements CustomerService {

	public static final long ID_BRIAN_GREEN = 1;

	public static final long ID_LIZ_KESSLER = 2;

	public static final long ID_RICH_SHEARER = 3;

	public List<Customer> getAllCustomers() {
		if (_allCustomers == null) {
			_allCustomers = new ArrayList<Customer>();

			BookingService bookingService = getBookingService();

			Customer customer = new Customer(ID_BRIAN_GREEN, "Brian", "Green");

			customer.setBookings(
				bookingService.getBookingsByCustomerId(ID_BRIAN_GREEN));

			_allCustomers.add(customer);

			customer = new Customer(ID_LIZ_KESSLER, "Liz", "Kessler");

			customer.setBookings(
				bookingService.getBookingsByCustomerId(ID_LIZ_KESSLER));

			_allCustomers.add(customer);

			customer = new Customer(ID_RICH_SHEARER, "Rich", "Shearer");

			customer.setBookings(
				bookingService.getBookingsByCustomerId(ID_RICH_SHEARER));

			_allCustomers.add(customer);
		}

		return _allCustomers;
	}

	public BookingService getBookingService() {
		return _bookingService;
	}

	public void setBookingService(BookingService bookingService) {
		_bookingService = bookingService;
	}

	private ArrayList<Customer> _allCustomers;
	private BookingService _bookingService;

}