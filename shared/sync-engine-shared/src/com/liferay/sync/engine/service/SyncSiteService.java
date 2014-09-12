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

import com.liferay.sync.engine.documentlibrary.event.GetUserSitesGroupsEvent;
import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.model.SyncSiteModelListener;
import com.liferay.sync.engine.service.persistence.SyncSitePersistence;

import java.sql.SQLException;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncSiteService {

	public static SyncSite activateSyncSite(long syncAccountId, boolean reset) {
		SyncSite syncSite = fetchSyncSite(syncAccountId);

		syncSite.setActive(true);

		if (reset) {
			syncSite.setRemoteSyncTime(0);
		}

		update(syncSite);

		return syncSite;
	}

	public static void deleteSyncSite(long syncSiteId) {
		try {
			_syncSitePersistence.deleteById(syncSiteId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static SyncSite fetchSyncSite(long syncSiteId) {
		try {
			return _syncSitePersistence.queryForId(syncSiteId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncSite fetchSyncSite(long groupId, long syncAccountId) {
		try {
			return _syncSitePersistence.fetchByG_S(groupId, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncSite fetchSyncSite(
		String filePathName, long syncAccountId) {

		try {
			return _syncSitePersistence.fetchByF_S(filePathName, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static List<SyncSite> findSyncSites(long syncAccountId) {
		try {
			return _syncSitePersistence.findBySyncAccountId(syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptyList();
		}
	}

	public static Set<Long> getActiveSyncSiteIds(long syncAccountId) {
		try {
			Set<Long> activeSyncSiteIds = _activeSyncSiteIds.get(syncAccountId);

			if (activeSyncSiteIds != null) {
				return activeSyncSiteIds;
			}

			activeSyncSiteIds = new HashSet<Long>(
				_syncSitePersistence.findByA_S(true, syncAccountId));

			_activeSyncSiteIds.put(syncAccountId, activeSyncSiteIds);

			return activeSyncSiteIds;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptySet();
		}
	}

	public static SyncSitePersistence getSyncSitePersistence() {
		if (_syncSitePersistence != null) {
			return _syncSitePersistence;
		}

		try {
			_syncSitePersistence = new SyncSitePersistence();

			registerModelListener(new SyncSiteModelListener());

			return _syncSitePersistence;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static void registerModelListener(
		ModelListener<SyncSite> modelListener) {

		_syncSitePersistence.registerModelListener(modelListener);
	}

	public static void synchronizeSyncSites(long syncAccountId) {
		GetUserSitesGroupsEvent getUserSitesGroupsEvent =
			new GetUserSitesGroupsEvent(
				syncAccountId, Collections.<String, Object>emptyMap());

		getUserSitesGroupsEvent.run();
	}

	public static void unregisterModelListener(
		ModelListener<SyncSite> modelListener) {

		_syncSitePersistence.unregisterModelListener(modelListener);
	}

	public static SyncSite update(SyncSite syncSite) {
		try {
			_syncSitePersistence.createOrUpdate(syncSite);

			return syncSite;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncSiteService.class);

	private static Map<Long, Set<Long>> _activeSyncSiteIds =
		new HashMap<Long, Set<Long>>();
	private static SyncSitePersistence _syncSitePersistence =
		getSyncSitePersistence();

}