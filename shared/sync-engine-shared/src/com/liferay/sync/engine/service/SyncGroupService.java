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

import com.liferay.sync.engine.model.SyncGroup;
import com.liferay.sync.engine.service.persistence.SyncGroupPersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncGroupService {

	public static SyncGroup addSyncGroup(
			long syncAccountId, long groupId, String filePath)
		throws Exception {

		SyncGroup syncGroup = new SyncGroup();

		syncGroup.setGroupId(groupId);
		syncGroup.setFilePath(filePath);
		syncGroup.setSyncAccountId(syncAccountId);

		_syncGroupPersistence.create(syncGroup);

		return syncGroup;
	}

	public static SyncGroup getSyncGroup(long syncAccountId, long groupId) {
		SyncGroup syncGroup = null;

		try {
			syncGroup = _syncGroupPersistence.getSyncGroup(
				syncAccountId, groupId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return syncGroup;
	}

	public static SyncGroupPersistence getSyncGroupPersistence() {
		if (_syncGroupPersistence != null) {
			return _syncGroupPersistence;
		}

		try {
			_syncGroupPersistence = new SyncGroupPersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncGroupPersistence;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncGroupService.class);
	private static SyncGroupPersistence _syncGroupPersistence =
		getSyncGroupPersistence();

}