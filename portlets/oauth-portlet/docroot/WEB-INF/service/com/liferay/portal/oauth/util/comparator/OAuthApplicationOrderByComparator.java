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

package com.liferay.portal.oauth.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.oauth.model.Application;
import com.liferay.portal.oauth.util.OAuthConstants;

/**
 * @author Raymond Augé
 */
public class OAuthApplicationOrderByComparator extends OrderByComparator {

	public static final String[] ORDER_BY_FIELDS = {
		OAuthConstants.NAME
	};

	public OAuthApplicationOrderByComparator(
		boolean ascending, String orderByColumn) {

		_ascending = ascending;
		_orderByColumn = orderByColumn;
	}

	@Override
	public int compare(Object obj1, Object obj2) {

		Application app1 = (Application) obj1;
		Application app2 = (Application) obj2;

		int value = app1.getName().compareTo(app2.getName());

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {

		if (_ascending) {
			return _orderByColumn.concat(" ASC");
		}

		return _orderByColumn.concat(" DESC");
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

	private String _orderByColumn;

}