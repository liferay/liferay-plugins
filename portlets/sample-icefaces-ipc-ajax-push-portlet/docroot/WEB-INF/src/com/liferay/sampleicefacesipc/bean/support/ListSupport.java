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

package com.liferay.sampleicefacesipc.bean.support;

import com.liferay.sampleicefacesipc.service.BookingTypeService;
import com.liferay.sampleicefacesipc.transfer.BookingType;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Neil Griffin
 */
public class ListSupport {

	public BookingTypeService getBookingTypeService() {
		return _bookingTypeService;
	}

	public List<SelectItem> getSelectItems() {
		if (_selectItems == null) {
			_selectItems = new ArrayList<SelectItem>();

			List<BookingType> bookingTypes =
				_bookingTypeService.getAllBookingTypes();

			for (BookingType bookingType : bookingTypes) {
				_selectItems.add(
					new SelectItem(
						bookingType.getBookingTypeId(),
						bookingType.getBookingTypeName()));
			}
		}

		return _selectItems;
	}

	public void setBookingTypeService(BookingTypeService bookingTypeService) {
		_bookingTypeService = bookingTypeService;
	}

	private BookingTypeService _bookingTypeService;
	private List<SelectItem> _selectItems;

}