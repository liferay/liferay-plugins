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

package sample.icefacesipc.bean.support;

import sample.icefacesipc.service.BookingTypeService;

import sample.icefacesipc.transfer.BookingType;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * <a href="BookingTypeSupport.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * This class is a support managed-bean for the bookingList.xhtml view
 * (Bookings Portlet). It provides a convenient way for the view to
 * retrieve lists of JSF SelectItem entries. If the bean is kept in
 * application scope, then it will maintain a cache of list items
 * that will be shared across multiple users.
 * </p>
 * 
 * @author Neil Griffin
 *
 */
public class ListSupport {

	public BookingTypeService getBookingTypeService() {
		return _bookingTypeService;
	}

	public void setBookingTypeService(BookingTypeService bookingTypeService) {
		_bookingTypeService = bookingTypeService;
	}

	public List<SelectItem> getSelectItems() {

		if (_selectItems == null) {
			_selectItems = new ArrayList<SelectItem>();

			for (
				BookingType bookingType :
				getBookingTypeService().getAllBookingTypes()) {
				_selectItems.add(
					new SelectItem(
						bookingType.getBookingTypeId(),
						bookingType.getBookingTypeName()));
			}
		}

		return _selectItems;
	}

	private BookingTypeService _bookingTypeService;

	private List<SelectItem> _selectItems;
}
