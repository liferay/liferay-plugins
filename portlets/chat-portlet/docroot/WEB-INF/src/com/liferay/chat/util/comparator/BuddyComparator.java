/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.util.comparator;

import com.liferay.portal.model.ContactConstants;

import java.util.Comparator;

/**
 * <a href="BuddyComparator.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 *
 */
public class BuddyComparator implements Comparator<Object[]> {

	public BuddyComparator() {
		this(false);
	}

	public BuddyComparator(boolean asc) {
		_asc = asc;
	}

	public int compare(Object[] buddy1, Object[] buddy2) {
		long userId1 = (Long)buddy1[0];
		String firstName1 = (String)buddy1[1];
		String middleName1 = (String)buddy1[2];
		String lastName1 = (String)buddy1[3];
		boolean awake1 = (Boolean)buddy1[5];

		long userId2 = (Long)buddy2[0];
		String firstName2 = (String)buddy2[1];
		String middleName2 = (String)buddy2[2];
		String lastName2 = (String)buddy2[3];
		boolean awake2 = (Boolean)buddy2[5];

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