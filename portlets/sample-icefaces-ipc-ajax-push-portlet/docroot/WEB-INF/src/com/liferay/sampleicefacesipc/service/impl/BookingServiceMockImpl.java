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
import com.liferay.sampleicefacesipc.transfer.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="BookingServiceMockImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
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