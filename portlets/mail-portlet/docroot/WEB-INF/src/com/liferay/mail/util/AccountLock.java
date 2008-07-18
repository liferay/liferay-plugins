/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.util;

import com.liferay.portal.kernel.util.Time;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	private static ConcurrentHashMap<String, Long> _lockMaps =
		new ConcurrentHashMap<String, Long>();

	private static final long _EXPIRY_TIME = Time.MINUTE * 5;

	private static Log _log = LogFactory.getLog(AccountLock.class);

}