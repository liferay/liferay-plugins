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

import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.persistence.SyncSitePersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncSiteService {

	public static SyncSite addSyncSite(
			long syncAccountId, long groupId, String filePath)
		throws Exception {

		SyncSite syncSite = new SyncSite();

		syncSite.setFilePath(filePath);
		syncSite.setGroupId(groupId);
		syncSite.setSyncAccountId(syncAccountId);

		_syncSitePersistence.create(syncSite);

		return syncSite;
	}

	public static SyncSite fetchSyncSite(long syncAccountId, long groupId) {
		try {
			return _syncSitePersistence.fetchSyncSite(syncAccountId, groupId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncSitePersistence getSyncSitePersistence() {
		if (_syncSitePersistence != null) {
			return _syncSitePersistence;
		}

		try {
			_syncSitePersistence = new SyncSitePersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncSitePersistence;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncSiteService.class);

	private static SyncSitePersistence _syncSitePersistence =
		getSyncSitePersistence();

}