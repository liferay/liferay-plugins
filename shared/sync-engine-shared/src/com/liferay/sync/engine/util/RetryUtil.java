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

package com.liferay.sync.engine.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class RetryUtil {

	public static int getRetryCount(long syncAccountId) {
		return _counts.get(syncAccountId);
	}

	public static long incrementRetryDelay(long syncAccountId) {
		int count = 0;

		if (_counts.containsKey(syncAccountId)) {
			count = _counts.get(syncAccountId);
		}

		count++;

		_counts.put(syncAccountId, count);

		Long delay = _delays.get(syncAccountId);

		if (delay == null) {
			_delays.put(syncAccountId, _INITIAL_INTERVAL);

			return _INITIAL_INTERVAL;
		}
		else if (delay == _MAX_INTERVAL) {
			return delay;
		}

		delay = (long)(delay * _MULTIPLIER);

		if (delay > _MAX_INTERVAL) {
			delay = _MAX_INTERVAL;
		}

		_delays.put(syncAccountId, delay);

		return delay;
	}

	public static void resetRetryDelay(long syncAccountId) {
		_delays.remove(syncAccountId);
	}

	private static final long _INITIAL_INTERVAL = 100;

	private static final long _MAX_INTERVAL = 30000;

	private static final double _MULTIPLIER = 2.0;

	private static Map<Long, Integer> _counts = new HashMap<Long, Integer>();
	private static Map<Long, Long> _delays = new HashMap<Long, Long>();

}