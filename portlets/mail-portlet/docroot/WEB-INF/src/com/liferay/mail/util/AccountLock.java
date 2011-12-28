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

package com.liferay.mail.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Scott Lee
 * @author Alexander Chow
 */
public class AccountLock {

	public static boolean acquireLock(String key) {
		synchronized (key) {
			long nowTime = System.currentTimeMillis();

			if (_locks.containsKey(key)) {
				long timeLocked = _locks.get(key);
				long expireTime = timeLocked + _EXPIRY_TIME;

				if (nowTime < expireTime) {
					if (_log.isDebugEnabled()) {
						_log.debug("Lock has not expired for " + key);
					}

					return false;
				}
			}

			_locks.put(key, nowTime);
		}

		return true;
	}

	public static String getKey(long userId, long accountEntryId) {
		StringBundler sb = new StringBundler(7);

		sb.append(userId);
		sb.append(StringPool.UNDERLINE);
		sb.append(accountEntryId);

		return sb.toString();
	}

	public static void releaseLock(String key) {
		synchronized (key) {
			_locks.remove(key);
		}
	}

	private static final long _EXPIRY_TIME = Time.MINUTE * 15;

	private static Log _log = LogFactoryUtil.getLog(AccountLock.class);

	private static ConcurrentHashMap<String, Long> _locks =
		new ConcurrentHashMap<String, Long>();

}