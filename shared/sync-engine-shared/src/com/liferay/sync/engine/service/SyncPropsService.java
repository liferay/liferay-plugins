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

import com.liferay.sync.engine.model.SyncProps;
import com.liferay.sync.engine.service.persistence.SyncPropsPersistence;

import java.sql.SQLException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncPropsService {

	public static SyncProps addSyncProps(String key, Object value)
		throws Exception {

		SyncProps syncProps = new SyncProps();

		syncProps.setKey(key);
		syncProps.setValue(String.valueOf(value));

		_syncPropsPersistence.create(syncProps);

		return syncProps;
	}

	public static int getInteger(String key) {
		try {
			List<SyncProps> syncPropsList = _syncPropsPersistence.queryForEq(
				"key", key);

			SyncProps syncProps = syncPropsList.get(0);

			if (syncProps == null) {
				return 0;
			}

			return Integer.parseInt(syncProps.getValue());
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return 0;
		}
	}

	public static SyncPropsPersistence getSyncPropsPersistence() {
		if (_syncPropsPersistence != null) {
			return _syncPropsPersistence;
		}

		try {
			_syncPropsPersistence = new SyncPropsPersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncPropsPersistence;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncPropsService.class);

	private static SyncPropsPersistence _syncPropsPersistence =
		getSyncPropsPersistence();

}