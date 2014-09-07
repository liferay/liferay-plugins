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

package com.liferay.google.apps.connector.comparator;

import com.liferay.google.apps.connector.GGroup;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class GGroupNameComparator implements Comparator<GGroup> {

	public GGroupNameComparator() {
		this(true);
	}

	public GGroupNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(GGroup user1, GGroup user2) {
		String name1 = user1.getName();
		String name2 = user2.getName();

		int value = name1.compareTo(name2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}