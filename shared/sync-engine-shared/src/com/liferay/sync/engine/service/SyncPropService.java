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

import com.liferay.sync.engine.model.SyncProp;
import com.liferay.sync.engine.service.persistence.SyncPropPersistence;

import java.sql.SQLException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncPropService {

	public static SyncProp addSyncProp(String key, Object value)
		throws Exception {

		SyncProp syncProp = new SyncProp();

		syncProp.setKey(key);
		syncProp.setValue(String.valueOf(value));

		_syncPropPersistence.create(syncProp);

		return syncProp;
	}

	public static int getInteger(String key) {
		try {
			List<SyncProp> syncProps = _syncPropPersistence.queryForEq(
				"key", key);

			SyncProp syncProp = syncProps.get(0);

			if (syncProp == null) {
				return 0;
			}

			return Integer.parseInt(syncProp.getValue());
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return 0;
		}
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

	private static Logger _logger = LoggerFactory.getLogger(
		SyncPropService.class);

	private static SyncPropPersistence _syncPropPersistence =
		getSyncPropPersistence();

}