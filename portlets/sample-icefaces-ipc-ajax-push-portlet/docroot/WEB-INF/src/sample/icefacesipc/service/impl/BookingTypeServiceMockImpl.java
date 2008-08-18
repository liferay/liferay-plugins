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

package sample.icefacesipc.service.impl;

import java.util.ArrayList;
import java.util.List;

import sample.icefacesipc.service.BookingTypeService;
import sample.icefacesipc.transfer.BookingType;

/**
 * <a href="BookingTypeServiceMockImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
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