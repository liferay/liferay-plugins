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

import com.liferay.sync.engine.listener.SyncEngineListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shinn Lok
 */
public class SyncEngineUtil {

	public static final int SYNC_ENGINE_NOT_CONFIGURED = 0;

	public static final int SYNC_ENGINE_STATE_STARTED = 1;

	public static final int SYNC_ENGINE_STATE_STARTING = 2;

	public static final int SYNC_ENGINE_STATE_STOPPED = 3;

	public static final int SYNC_ENGINE_STATE_STOPPING = 4;

	public static final int SYNC_ENGINE_UPDATE_AVAILABLE = 5;

	public static void fireSyncEngineStateChanged(final int syncEngineState) {
		for (final SyncEngineListener syncEngineListener :
				_syncEngineListeners) {

			_executorService.submit(
				new Runnable() {

					@Override
					public void run() {
						syncEngineListener.syncEngineStateChanged(
							syncEngineState);
					}

				}
			);
		}
	}

	public static void registerSyncEngineListener(
		SyncEngineListener syncEngineListener) {

		_syncEngineListeners.add(syncEngineListener);
	}

	public static void unregisterSyncEngineListener(
		SyncEngineListener syncEngineListener) {

		_syncEngineListeners.remove(syncEngineListener);
	}

	private static ExecutorService _executorService =
		Executors.newSingleThreadScheduledExecutor();
	private static List<SyncEngineListener> _syncEngineListeners =
		new ArrayList<SyncEngineListener>();

}