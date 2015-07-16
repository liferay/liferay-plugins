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
public class SyncDLObjectModifiedTimeComparator
	extends OrderByComparator<SyncDLObject> {

	public static final String ORDER_BY_ASC = "SyncDLObject.modifiedTime ASC";

	public static final String ORDER_BY_DESC = "SyncDLObject.modifiedTime DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedTime"};

	public SyncDLObjectModifiedTimeComparator() {
		this(true);
	}

	public SyncDLObjectModifiedTimeComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(SyncDLObject syncDLObject1, SyncDLObject syncDLObject2) {
		long modifiedTime1 = syncDLObject1.getModifiedTime();
		long modifiedTime2 = syncDLObject2.getModifiedTime();

		int value = 0;

		if (modifiedTime1 < modifiedTime2) {
			value = -1;
		}
		else if (modifiedTime1 > modifiedTime2) {
			value = 1;
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