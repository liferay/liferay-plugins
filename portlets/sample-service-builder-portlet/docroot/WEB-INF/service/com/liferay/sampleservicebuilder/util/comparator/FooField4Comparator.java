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

package com.liferay.sampleservicebuilder.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.sampleservicebuilder.model.Foo;

/**
 * @author Alexander Chow
 */
public class FooField4Comparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "field4 ASC";

	public static final String ORDER_BY_DESC = "field4 DESC";

	public FooField4Comparator() {
		this(false);
	}

	public FooField4Comparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Foo foo1 = (Foo)obj1;
		Foo foo2 = (Foo)obj2;

		int value = DateUtil.compareTo(foo1.getField4(), foo2.getField4());

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_asc) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	private boolean _asc;

}