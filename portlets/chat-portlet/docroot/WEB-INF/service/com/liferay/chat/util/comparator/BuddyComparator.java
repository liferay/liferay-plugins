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

package com.liferay.chat.util.comparator;

import com.liferay.portal.kernel.model.ContactConstants;

import java.util.Comparator;

/**
 * @author Ryan Park
 */
public class BuddyComparator implements Comparator<Object[]> {

	public BuddyComparator() {
		this(false);
	}

	public BuddyComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object[] buddy1, Object[] buddy2) {
		long userId1 = (Long)buddy1[0];
		String firstName1 = (String)buddy1[2];
		String middleName1 = (String)buddy1[3];
		String lastName1 = (String)buddy1[4];
		boolean awake1 = (Boolean)buddy1[6];

		long userId2 = (Long)buddy2[0];
		String firstName2 = (String)buddy2[2];
		String middleName2 = (String)buddy2[3];
		String lastName2 = (String)buddy2[4];
		boolean awake2 = (Boolean)buddy2[6];

		int value = 0;

		if (userId1 == userId2) {
			return value;
		}

		if (awake1 && !awake2) {
			value = 1;
		}
		else if (!awake1 && awake2) {
			value = -1;
		}

		if (value == 0) {
			String fullName1 = ContactConstants.getFullName(
				firstName1, middleName1, lastName1);
			String fullName2 = ContactConstants.getFullName(
				firstName2, middleName2, lastName2);

			value = fullName1.compareTo(fullName2);
		}

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _asc;

}