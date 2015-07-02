/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.sync.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.sync.model.SyncDLObject;

/**
 * @author Shinn Lok
 */
public class SyncDLObjectTypeComparator
	extends OrderByComparator<SyncDLObject> {

	public static final String ORDER_BY_ASC =
		"SyncDLObject.type_ ASC, SyncDLObject.modifiedTime ASC";

	public static final String ORDER_BY_DESC =
		"SyncDLObject.type_ DESC, SyncDLObject.modifiedTime ASC";

	public static final String[] ORDER_BY_FIELDS = {"type", "modifiedTime"};

	public SyncDLObjectTypeComparator() {
		this(false);
	}

	public SyncDLObjectTypeComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(SyncDLObject syncDLObject1, SyncDLObject syncDLObject2) {
		String type1 = syncDLObject1.getType();
		String type2 = syncDLObject2.getType();

		int value = type1.compareTo(type2);

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

	@Override
	public boolean isAscending(String field) {
		if (field.equals("type")) {
			return isAscending();
		}
		else {
			return true;
		}
	}

	private boolean _ascending;

}