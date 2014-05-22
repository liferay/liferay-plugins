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

package com.liferay.sync.engine.service;

import com.liferay.sync.engine.model.SyncProp;
import com.liferay.sync.engine.service.persistence.SyncPropPersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncPropService {

	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = getValue(key);

		if (value == null) {
			return defaultValue;
		}

		return Boolean.parseBoolean(value);
	}

	public static int getInteger(String key) {
		return getInteger(key, 0);
	}

	public static int getInteger(String key, int defaultValue) {
		String value = getValue(key);

		if (value == null) {
			return defaultValue;
		}

		return Integer.parseInt(value);
	}

	public static SyncPropPersistence getSyncPropPersistence() {
		if (_syncPropPersistence != null) {
			return _syncPropPersistence;
		}

		try {
			_syncPropPersistence = new SyncPropPersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncPropPersistence;
	}

	public static String getValue(String key) {
		try {
			SyncProp syncProp = _syncPropPersistence.queryForId(key);

			if (syncProp == null) {
				return null;
			}

			return syncProp.getValue();
		}
		catch (SQLException sqle) {
			return null;
		}
	}

	public static SyncProp updateSyncProp(String key, Object value)
		throws Exception {

		SyncProp syncProp = new SyncProp();

		syncProp.setKey(key);
		syncProp.setValue(String.valueOf(value));

		_syncPropPersistence.createOrUpdate(syncProp);

		return syncProp;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncPropService.class);

	private static SyncPropPersistence _syncPropPersistence =
		getSyncPropPersistence();

}