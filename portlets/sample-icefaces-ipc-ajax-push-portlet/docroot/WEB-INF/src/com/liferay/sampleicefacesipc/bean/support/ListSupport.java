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

package com.liferay.sampleicefacesipc.bean.support;

import com.liferay.sampleicefacesipc.service.BookingTypeService;
import com.liferay.sampleicefacesipc.transfer.BookingType;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * <a href="ListSupport.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
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