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

package com.liferay.sampleicefacesipc.service.impl;

import com.liferay.sampleicefacesipc.service.BookingService;
import com.liferay.sampleicefacesipc.service.CustomerService;
import com.liferay.sampleicefacesipc.transfer.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Griffin
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