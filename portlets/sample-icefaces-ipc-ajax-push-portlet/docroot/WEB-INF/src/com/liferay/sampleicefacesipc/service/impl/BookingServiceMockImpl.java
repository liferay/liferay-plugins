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
import com.liferay.sampleicefacesipc.transfer.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Griffin
 */
public class BookingServiceMockImpl implements BookingService {

	public List<Booking> getAllBookings() {
		if (_allBookings == null) {
			_allBookings = new ArrayList<Booking>();

			// Bookings for Brian Green

			long customerId = CustomerServiceMockImpl.ID_BRIAN_GREEN;

			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_RENTAL_CAR, customerId));
			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_HOTEL, customerId));
			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_AIRFARE, customerId));
			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_THEME_PARK, customerId));
			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_PLAY, customerId));

			// Bookings for Elizabeth Kessler

			customerId = CustomerServiceMockImpl.ID_LIZ_KESSLER;

			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_HOTEL, customerId));
			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_AIRFARE, customerId));
			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_THEME_PARK, customerId));

			// Bookings for Rich Shearer

			customerId = CustomerServiceMockImpl.ID_RICH_SHEARER;

			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_AIRFARE, customerId));
			_allBookings.add(
				new Booking(
				BookingTypeServiceMockImpl.TYPE_ID_RENTAL_CAR, customerId));
			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_PLAY, customerId));
			_allBookings.add(
				new Booking(
					BookingTypeServiceMockImpl.TYPE_ID_HOTEL, customerId));
		}

		return _allBookings;
	}

	public List<Booking> getBookingsByCustomerId(long customerId) {
		List<Booking> bookings = new ArrayList<Booking>();

		for (Booking booking : getAllBookings()) {
			if (booking.getCustomerId() == customerId) {
				bookings.add(booking);
			}
		}

		return bookings;
	}

	private List<Booking> _allBookings;

}