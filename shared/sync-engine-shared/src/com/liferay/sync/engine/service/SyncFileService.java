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

import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.persistence.SyncFilePersistence;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncFileService {

	public static SyncFile addSyncFile(
			String filePath, String name, long parentFolderId,
			long repositoryId, long syncAccountId, String type, long typePK)
		throws Exception {

		SyncFile syncFile = new SyncFile();

		syncFile.setFilePath(filePath);
		syncFile.setName(name);
		syncFile.setRepositoryId(repositoryId);
		syncFile.setParentFolderId(parentFolderId);
		syncFile.setSyncAccountId(syncAccountId);
		syncFile.setType(type);
		syncFile.setTypePK(typePK);

		_syncFilePersistence.create(syncFile);

		return syncFile;
	}

	public static void deleteSyncFile(long syncFileId) {
		try {
			_syncFilePersistence.deleteById(syncFileId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static SyncFile fetchSyncFile(
		long parentFolderId, long repositoryId, long syncAccountId) {

		try {
			return _syncFilePersistence.fetchSyncFile(
				parentFolderId, repositoryId, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncFile fetchSyncFile(String filePath, long syncAccountId) {
		try {
			return _syncFilePersistence.fetchSyncFile(filePath, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncFilePersistence getSyncFilePersistence() {
		if (_syncFilePersistence != null) {
			return _syncFilePersistence;
		}

		try {
			_syncFilePersistence = new SyncFilePersistence();
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}

		return _syncFilePersistence;
	}

	public static SyncFile update(SyncFile syncFile) {
		try {
			_syncFilePersistence.createOrUpdate(syncFile);

			return syncFile;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncFileService.class);

	private static SyncFilePersistence _syncFilePersistence =
		getSyncFilePersistence();

}