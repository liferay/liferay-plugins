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

import com.liferay.google.apps.connector.GUser;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class GUserLastNameComparator implements Comparator<GUser> {

	public GUserLastNameComparator() {
		this(true);
	}

	public GUserLastNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(GUser user1, GUser user2) {
		String lastName1 = user1.getLastName();
		String lastName2 = user2.getLastName();

		int value = lastName1.compareTo(lastName2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}