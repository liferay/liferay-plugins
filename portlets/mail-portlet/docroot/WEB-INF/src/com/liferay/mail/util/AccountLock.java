/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <a href="AccountLock.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 * @author Alexander Chow
 *
 */
public class AccountLock {

	public static boolean acquireLock(String key) {
		synchronized (key) {
			long nowTime = System.currentTimeMillis();

			if (_lockMaps.containsKey(key)) {
				long timeLocked = _lockMaps.get(key);
				long expireTime = timeLocked + _EXPIRY_TIME;

				if (nowTime < expireTime) {
					if (_log.isDebugEnabled()) {
						_log.debug("Lock has not expired for " + key);
					}

					return false;
				}
			}

			_lockMaps.put(key, nowTime);
		}

		return true;
	}

	public static String getKey(long userId, String emailAddress) {
		return "" + userId + "_LOCK_" + emailAddress;
	}

	public static void releaseLock(String key) {
		synchronized (key) {
			_lockMaps.remove(key);
		}
	}

	private static final long _EXPIRY_TIME = Time.MINUTE * 5;

	private static Log _log = LogFactoryUtil.getLog(AccountLock.class);

	private static ConcurrentHashMap<String, Long> _lockMaps =
		new ConcurrentHashMap<String, Long>();

}