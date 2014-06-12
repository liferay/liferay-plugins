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

import com.liferay.sync.engine.documentlibrary.event.GetSyncContextEvent;
import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.persistence.SyncAccountPersistence;
import com.liferay.sync.engine.util.Encryptor;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Paths;

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
public class SyncAccountService {

	public static SyncAccount activateSyncAccount(
		long syncAccountId, boolean reset) {

		SyncAccount syncAccount = fetchSyncAccount(syncAccountId);

		syncAccount.setActive(true);

		update(syncAccount);

		if (reset) {
			List<SyncSite> syncSites = SyncSiteService.findSyncSites(
				syncAccountId);

			for (SyncSite syncSite : syncSites) {
				syncSite.setRemoteSyncTime(0);

				SyncSiteService.update(syncSite);
			}
		}

		return syncAccount;
	}

	public static SyncAccount addSyncAccount(
			String filePathName, String login, int maxConnections, String name,
			String password, int pollInterval, SyncSite[] syncSites,
			boolean trustSelfSigned, String url)
		throws Exception {

		// Sync account

		SyncAccount syncAccount = new SyncAccount();

		syncAccount.setFilePathName(filePathName);
		syncAccount.setLogin(login);
		syncAccount.setMaxConnections(maxConnections);
		syncAccount.setName(name);
		syncAccount.setPassword(Encryptor.encrypt(password));
		syncAccount.setPollInterval(pollInterval);
		syncAccount.setTrustSelfSigned(trustSelfSigned);
		syncAccount.setUrl(url);

		_syncAccountPersistence.create(syncAccount);

		// Sync file

		Files.createDirectories(Paths.get(filePathName));

		SyncFileService.addSyncFile(
			null, null, filePathName, FileUtil.getFileKey(filePathName),
			filePathName, null, filePathName, 0, 0,
			syncAccount.getSyncAccountId(), SyncFile.TYPE_SYSTEM);

		// Sync sites

		if (syncSites != null) {
			for (SyncSite syncSite : syncSites) {
				String syncSiteName = syncSite.getName();

				if (!FileUtil.isValidFileName(syncSiteName)) {
					syncSiteName = String.valueOf(syncSite.getGroupId());
				}

				syncSite.setFilePathName(
					syncAccount.getFilePathName() + "/" + syncSiteName);

				syncSite.setSyncAccountId(syncAccount.getSyncAccountId());

				SyncSiteService.update(syncSite);
			}
		}

		return syncAccount;
	}

	public static void deleteSyncAccount(long syncAccountId) {
		try {
			_syncAccountPersistence.deleteById(syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static SyncAccount fetchSyncAccount(long syncAccountId) {
		try {
			return _syncAccountPersistence.queryForId(syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static List<SyncAccount> findAll() {
		try {
			return _syncAccountPersistence.queryForAll();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptyList();
		}
	}

	public static Set<Long> getActiveSyncAccountIds() {
		try {
			if (_activeSyncAccountIds != null) {
				return _activeSyncAccountIds;
			}

			_activeSyncAccountIds = new HashSet<Long>(
				_syncAccountPersistence.findByActive(true));

			return _activeSyncAccountIds;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptySet();
		}
	}

	public static SyncAccountPersistence getSyncAccountPersistence() {
		if (_syncAccountPersistence != null) {
			return _syncAccountPersistence;
		}

		try {
			_syncAccountPersistence = new SyncAccountPersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncAccountPersistence;
	}

	public static void registerModelListener(
		ModelListener<SyncAccount> modelListener) {

		_syncAccountPersistence.registerModelListener(modelListener);
	}

	public static void resetActiveSyncAccountIds() {
		_activeSyncAccountIds = null;
	}

	public static void setFilePathName(
		long syncAccountId, String targetFilePathName) {

		// Sync account

		SyncAccount syncAccount = fetchSyncAccount(syncAccountId);

		String sourceFilePathName = syncAccount.getFilePathName();

		syncAccount.setFilePathName(targetFilePathName);

		update(syncAccount);

		// Sync files

		List<SyncFile> syncFiles = SyncFileService.findSyncFiles(syncAccountId);

		for (SyncFile syncFile : syncFiles) {
			String syncFileFilePathName = syncFile.getFilePathName();

			syncFileFilePathName = syncFileFilePathName.replace(
				sourceFilePathName, targetFilePathName);

			syncFile.setFilePathName(syncFileFilePathName);

			SyncFileService.update(syncFile);
		}

		// Sync sites

		List<SyncSite> syncSites = SyncSiteService.findSyncSites(syncAccountId);

		for (SyncSite syncSite : syncSites) {
			String syncSiteFilePathName = syncSite.getFilePathName();

			syncSiteFilePathName = syncSiteFilePathName.replace(
				sourceFilePathName, targetFilePathName);

			syncSite.setFilePathName(syncSiteFilePathName);

			SyncSiteService.update(syncSite);
		}
	}

	public static SyncAccount synchronizeSyncAccount(long syncAccountId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("uuid", null);

		GetSyncContextEvent getSyncContextEvent = new GetSyncContextEvent(
			syncAccountId, parameters);

		getSyncContextEvent.run();

		return SyncAccountService.fetchSyncAccount(syncAccountId);
	}

	public static void unregisterModelListener(
		ModelListener<SyncAccount> modelListener) {

		_syncAccountPersistence.unregisterModelListener(modelListener);
	}

	public static SyncAccount update(SyncAccount syncAccount) {
		try {
			_syncAccountPersistence.createOrUpdate(syncAccount);

			return syncAccount;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncAccountService.class);

	private static Set<Long> _activeSyncAccountIds;
	private static SyncAccountPersistence _syncAccountPersistence =
		getSyncAccountPersistence();

}