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
public class GGroupEmailAddressComparator implements Comparator<GGroup> {

	public GGroupEmailAddressComparator() {
		this(true);
	}

	public GGroupEmailAddressComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(GGroup user1, GGroup user2) {
		String emailAddress1 = user1.getEmailAddress();
		String emailAddress2 = user2.getEmailAddress();

		int value = emailAddress1.compareTo(emailAddress2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}