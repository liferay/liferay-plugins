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

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.model.SyncWatchEvent;
import com.liferay.sync.engine.service.persistence.SyncWatchEventPersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Young
 */
public class SyncWatchEventService {

	public static SyncWatchEvent addSyncWatchEvent(String filePath, String kind)
		throws Exception {

		SyncWatchEvent syncWatchEvent = new SyncWatchEvent();

		syncWatchEvent.setFilePath(filePath);
		syncWatchEvent.setKind(kind);

		_syncWatchEventPersistence.create(syncWatchEvent);

		return syncWatchEvent;
	}

	public static SyncWatchEvent fetchSyncWatchEvent(long syncWatchEventId) {
		try {
			return _syncWatchEventPersistence.queryForId(syncWatchEventId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncWatchEventPersistence getSyncWatchEventPersistence() {
		if (_syncWatchEventPersistence != null) {
			return _syncWatchEventPersistence;
		}

		try {
			_syncWatchEventPersistence = new SyncWatchEventPersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncWatchEventPersistence;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncWatchEventService.class);

	private static SyncWatchEventPersistence _syncWatchEventPersistence =
		getSyncWatchEventPersistence();

}