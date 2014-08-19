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

package com.liferay.sync.engine.filesystem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class WatcherRegistry {

	public static Watcher getWatcher(long syncAccountId) {
		return _watchers.get(syncAccountId);
	}

	public static void register(long syncAccountId, Watcher watcher) {
		_watchers.put(syncAccountId, watcher);
	}

	public static void unregister(long syncAccountId) {
		_watchers.remove(syncAccountId);
	}

	private static Map<Long, Watcher> _watchers = new HashMap<Long, Watcher>();

}