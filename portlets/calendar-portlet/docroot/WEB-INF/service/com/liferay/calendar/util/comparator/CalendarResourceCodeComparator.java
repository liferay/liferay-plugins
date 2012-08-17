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

package com.liferay.calendar.util.comparator;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarResourceCodeComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"CalendarResource.code_, CalendarResource.name ASC";

	public static final String ORDER_BY_DESC =
		"CalendarResource.code_, CalendarResource.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"code_", "name"};

	public CalendarResourceCodeComparator() {
		this(false);
	}

	public CalendarResourceCodeComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		CalendarResource calendarResource1 = (CalendarResource)obj1;
		CalendarResource calendarResource2 = (CalendarResource)obj2;

		String code1 = calendarResource1.getCode();
		String code2 = calendarResource2.getCode();

		int value = code1.compareTo(code2);

		if (value == 0) {
			String name1 = calendarResource1.getName();
			String name2 = calendarResource2.getName();

			value = name1.compareTo(name2);
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}