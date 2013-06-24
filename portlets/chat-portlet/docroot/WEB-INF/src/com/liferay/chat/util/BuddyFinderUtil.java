/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.util;

import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Ankit Srivastava
 */
public class BuddyFinderUtil {

	public static List<Object[]> getBuddies(long companyId, long userId)
		throws SystemException {

		return getBuddyFinder().getBuddies(companyId, userId);
	}

	public static BuddyFinder getBuddyFinder() {
		return _buddyFinder;
	}

	public void setBuddyFinder(BuddyFinder buddyFinder) {
		_buddyFinder = buddyFinder;
	}

	private static BuddyFinder _buddyFinder;

}