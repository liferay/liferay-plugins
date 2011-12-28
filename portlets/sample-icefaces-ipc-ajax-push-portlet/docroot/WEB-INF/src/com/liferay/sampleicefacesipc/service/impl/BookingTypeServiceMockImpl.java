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

import com.liferay.sampleicefacesipc.service.BookingTypeService;
import com.liferay.sampleicefacesipc.transfer.BookingType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Griffin
 */
public class BookingTypeServiceMockImpl implements BookingTypeService {

	public static final long TYPE_ID_AIRFARE = 1;

	public static final long TYPE_ID_CRUISE = 2;

	public static final long TYPE_ID_HOTEL = 3;

	public static final long TYPE_ID_PLAY = 4;

	public static final long TYPE_ID_RENTAL_CAR = 5;

	public static final long TYPE_ID_THEME_PARK = 6;

	public static final long TYPE_ID_TRAIN = 7;

	public List<BookingType> getAllBookingTypes() {
		if (_allBookingTypes == null) {
			_allBookingTypes = new ArrayList<BookingType>();

			_allBookingTypes.add(new BookingType(TYPE_ID_AIRFARE, "Airfare"));
			_allBookingTypes.add(new BookingType(TYPE_ID_CRUISE, "Cruise"));
			_allBookingTypes.add(new BookingType(TYPE_ID_HOTEL, "Hotel"));
			_allBookingTypes.add(new BookingType(TYPE_ID_PLAY, "Play/Theatre"));
			_allBookingTypes.add(
				new BookingType(TYPE_ID_RENTAL_CAR, "Rental Car"));
			_allBookingTypes.add(
				new BookingType(TYPE_ID_THEME_PARK, "Theme Park"));
			_allBookingTypes.add(new BookingType(TYPE_ID_TRAIN, "Train"));
		}

		return _allBookingTypes;
	}

	private List<BookingType> _allBookingTypes;

}