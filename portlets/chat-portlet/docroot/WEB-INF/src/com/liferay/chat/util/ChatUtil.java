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

package com.liferay.chat.util;

import com.liferay.chat.buddies.BuddiesFinder;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Time;

import java.util.List;


/**
 * @author Brian Wing Shun Chan
 */
public class ChatUtil {

	public static final long MAX_POLL_LATENCY = Time.SECOND * 15;

	public static final long ONLINE_DELTA = Time.MINUTE;

	
	public static BuddiesFinder getBuddiesFinder() {

		return _buddiesFinder;
	}
	

	public static List<Object[]> getBuddies(long companyId, long userId)
		throws SystemException {
		
		return getBuddiesFinder().getBuddies(companyId, userId);
		
	}
	
	public void setBuddiesFinder(BuddiesFinder buddiesFinder) {

		_buddiesFinder = buddiesFinder;
	}

	private static BuddiesFinder _buddiesFinder;

}