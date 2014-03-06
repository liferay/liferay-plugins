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

import com.liferay.sync.engine.documentlibrary.event.AddFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.AddFolderEvent;
import com.liferay.sync.engine.documentlibrary.event.CancelCheckOutEvent;
import com.liferay.sync.engine.documentlibrary.event.CheckInFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.CheckOutFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFileEntryToTrashEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFolderEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFolderToTrashEvent;
import com.liferay.sync.engine.documentlibrary.event.UpdateFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.UpdateFolderEvent;
import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.persistence.SyncFilePersistence;
import com.liferay.sync.engine.util.FilePathNameUtil;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.SQLException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncFileService {

	public static SyncFile addFileSyncFile(
			Path filePath, long folderId, long repositoryId, long syncAccountId)
		throws Exception {

		// Local sync file

		String checksum = FileUtil.getChecksum(filePath);
		String name = String.valueOf(filePath.getFileName());
		String mimeType = Files.probeContentType(filePath);

		SyncFile syncFile = addSyncFile(
			VERSION_DEFAULT, checksum, name, FileUtil.getFileKey(filePath),
			FilePathNameUtil.getFilePathName(filePath), mimeType, name,
			folderId, repositoryId, syncAccountId, SyncFile.TYPE_FILE);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("changeLog", VERSION_DEFAULT);
		parameters.put("checksum", checksum);
		parameters.put("description", name);
		parameters.put("filePath", filePath);
		parameters.put("folderId", folderId);
		parameters.put("mimeType", mimeType);
		parameters.put("repositoryId", repositoryId);
		parameters.put("sourceFileName", name);
		parameters.put("syncFile", syncFile);
		parameters.put("title", name);

		AddFileEntryEvent addFileEntryEvent = new AddFileEntryEvent(
			syncAccountId, parameters);

		addFileEntryEvent.run();

		return syncFile;
	}

	public static SyncFile addFolderSyncFile(
			Path filePath, long parentFolderId, long repositoryId,
			long syncAccountId)
		throws Exception {

		// Local sync file

		String name = String.valueOf(filePath.getFileName());

		SyncFile syncFile = addSyncFile(
			null, null, name, FileUtil.getFileKey(filePath),
			FilePathNameUtil.getFilePathName(filePath),
			Files.probeContentType(filePath), name, parentFolderId,
			repositoryId, syncAccountId, SyncFile.TYPE_FOLDER);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("description", name);
		parameters.put("name", name);
		parameters.put("parentFolderId", parentFolderId);
		parameters.put("repositoryId", repositoryId);
		parameters.put("syncFile", syncFile);

		AddFolderEvent addFolderEvent = new AddFolderEvent(
			syncAccountId, parameters);

		addFolderEvent.run();

		return syncFile;
	}

	public static SyncFile addSyncFile(
			String changeLog, String checksum, String description,
			String fileKey, String filePathName, String mimeType, String name,
			long parentFolderId, long repositoryId, long syncAccountId,
			String type)
		throws Exception {

		SyncFile syncFile = new SyncFile();

		syncFile.setChangeLog(changeLog);
		syncFile.setChecksum(checksum);
		syncFile.setDescription(description);
		syncFile.setFileKey(fileKey);
		syncFile.setFilePathName(filePathName);
		syncFile.setMimeType(mimeType);
		syncFile.setName(name);
		syncFile.setParentFolderId(parentFolderId);
		syncFile.setRepositoryId(repositoryId);
		syncFile.setSyncAccountId(syncAccountId);
		syncFile.setType(type);
		syncFile.setUiEvent(SyncFile.UI_EVENT_ADDED_LOCAL);

		_syncFilePersistence.create(syncFile);

		return syncFile;
	}

	public static SyncFile cancelCheckOutSyncFile(
			long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		syncFile.setUiEvent(SyncFile.UI_EVENT_UPDATED_LOCAL);

		update(syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("syncFile", syncFile);

		CancelCheckOutEvent cancelCheckOutEvent = new CancelCheckOutEvent(
			syncAccountId, parameters);

		cancelCheckOutEvent.run();

		return syncFile;
	}

	public static SyncFile checkInSyncFile(
			long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		syncFile.setUiEvent(SyncFile.UI_EVENT_UPDATED_LOCAL);

		update(syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("changeLog", null);
		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("majorVersion", false);
		parameters.put("syncFile", syncFile);

		CheckInFileEntryEvent checkInFileEntryEvent = new CheckInFileEntryEvent(
			syncAccountId, parameters);

		checkInFileEntryEvent.run();

		return syncFile;
	}

	public static SyncFile checkOutSyncFile(
			long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		syncFile.setUiEvent(SyncFile.UI_EVENT_UPDATED_LOCAL);

		update(syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("syncFile", syncFile);

		CheckOutFileEntryEvent checkOutFileEntryEvent =
			new CheckOutFileEntryEvent(syncAccountId, parameters);

		checkOutFileEntryEvent.run();

		return syncFile;
	}

	public static SyncFile deleteFileSyncFile(
			long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		syncFile.setUiEvent(SyncFile.UI_EVENT_DELETED_LOCAL);

		deleteSyncFile(syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("fileEntryId", syncFile.getTypePK());

		MoveFileEntryToTrashEvent moveFileEntryToTrashEvent =
			new MoveFileEntryToTrashEvent(syncAccountId, parameters);

		moveFileEntryToTrashEvent.run();

		return syncFile;
	}

	public static SyncFile deleteFolderSyncFile(
			long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		syncFile.setUiEvent(SyncFile.UI_EVENT_DELETED_LOCAL);

		deleteSyncFile(syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("folderId", syncFile.getTypePK());

		MoveFolderToTrashEvent moveFolderToTrashEvent =
			new MoveFolderToTrashEvent(syncAccountId, parameters);

		moveFolderToTrashEvent.run();

		return syncFile;
	}

	public static void deleteSyncFile(SyncFile syncFile) {
		try {

			// Sync file

			_syncFilePersistence.delete(syncFile);

			String type = syncFile.getType();

			if (type.equals(SyncFile.TYPE_FILE)) {
				return;
			}

			// Sync files

			List<SyncFile> childSyncFiles = _syncFilePersistence.queryForEq(
				"parentFolderId", syncFile.getSyncFileId());

			for (SyncFile childSyncFile : childSyncFiles) {
				type = childSyncFile.getType();

				if (type.equals(SyncFile.TYPE_FILE)) {
					_syncFilePersistence.deleteById(
						childSyncFile.getSyncFileId());
				}
				else {
					deleteSyncFile(childSyncFile);
				}
			}
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static SyncFile fetchSyncFile(
		long repositoryId, long syncAccountId, long typePK) {

		try {
			return _syncFilePersistence.fetchByR_S_T(
				repositoryId, syncAccountId, typePK);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncFile fetchSyncFile(
		String filePathName, long syncAccountId) {

		try {
			return _syncFilePersistence.fetchByFPN_S(
				filePathName, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static SyncFile fetchSyncFileByFileKey(
		String fileKey, long syncAccountId) {

		try {
			return _syncFilePersistence.fetchByFK_S(fileKey, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static List<SyncFile> findSyncFiles(long syncAccountId) {
		try {
			return _syncFilePersistence.findBySyncAccountId(syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptyList();
		}
	}

	public static List<SyncFile> findSyncFiles(
		String checksum, long syncAccountId) {

		try {
			return _syncFilePersistence.findByC_S(checksum, syncAccountId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptyList();
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

	public static SyncFile moveFileSyncFile(
			Path filePath, long folderId, long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		syncFile.setFilePathName(FilePathNameUtil.getFilePathName(filePath));
		syncFile.setParentFolderId(folderId);
		syncFile.setUiEvent(SyncFile.UI_EVENT_MOVED_LOCAL);

		update(syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("newFolderId", folderId);
		parameters.put(
			"serviceContext.scopeGroupId", syncFile.getRepositoryId());
		parameters.put("syncFile", syncFile);

		MoveFileEntryEvent moveFileEntryEvent = new MoveFileEntryEvent(
			syncAccountId, parameters);

		moveFileEntryEvent.run();

		return syncFile;
	}

	public static SyncFile moveFolderSyncFile(
			Path filePath, long parentFolderId, long syncAccountId,
			SyncFile syncFile)
		throws Exception {

		// Local sync file

		syncFile.setUiEvent(SyncFile.UI_EVENT_MOVED_LOCAL);

		updateSyncFile(filePath, parentFolderId, syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("folderId", syncFile.getTypePK());
		parameters.put("parentFolderId", parentFolderId);
		parameters.put(
			"serviceContext.scopeGroupId", syncFile.getRepositoryId());
		parameters.put("syncFile", syncFile);

		MoveFolderEvent moveFolderEvent = new MoveFolderEvent(
			syncAccountId, parameters);

		moveFolderEvent.run();

		return syncFile;
	}

	public static void registerModelListener(
		ModelListener<SyncFile> modelListener) {

		_syncFilePersistence.registerModelListener(modelListener);
	}

	public static void unregisterModelListener(
		ModelListener<SyncFile> modelListener) {

		_syncFilePersistence.unregisterModelListener(modelListener);
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

	public static SyncFile updateFileSyncFile(
			Path filePath, long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		String changeLog = String.valueOf(
			Double.valueOf(syncFile.getVersion()) + .1);
		String checksum = FileUtil.getChecksum(filePath);
		String name = String.valueOf(filePath.getFileName());

		syncFile.setChangeLog(changeLog);
		syncFile.setChecksum(checksum);
		syncFile.setFilePathName(FilePathNameUtil.getFilePathName(filePath));
		syncFile.setName(name);
		syncFile.setUiEvent(SyncFile.UI_EVENT_UPDATED_LOCAL);

		update(syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("changeLog", changeLog);
		parameters.put("checksum", checksum);
		parameters.put("description", syncFile.getDescription());
		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("filePath", filePath);
		parameters.put("majorVersion", false);
		parameters.put("mimeType", syncFile.getMimeType());
		parameters.put("sourceFileName", name);
		parameters.put("syncFile", syncFile);
		parameters.put("title", name);

		UpdateFileEntryEvent updateFileEntryEvent = new UpdateFileEntryEvent(
			syncAccountId, parameters);

		updateFileEntryEvent.run();

		return syncFile;
	}

	public static SyncFile updateFolderSyncFile(
			Path filePath, long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		updateSyncFile(filePath, syncFile.getParentFolderId(), syncFile);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("folderId", syncFile.getTypePK());
		parameters.put("name", filePath.getFileName());
		parameters.put("syncFile", syncFile);

		UpdateFolderEvent updateFolderEvent = new UpdateFolderEvent(
			syncAccountId, parameters);

		updateFolderEvent.run();

		return syncFile;
	}

	public static SyncFile updateSyncFile(
		Path filePath, long parentFolderId, SyncFile syncFile) {

		try {

			// Sync file

			String type = syncFile.getType();

			if (type.equals(SyncFile.TYPE_FILE)) {
				return update(syncFile);
			}

			String oldFilePathName = syncFile.getFilePathName();
			String newFilePathName = FilePathNameUtil.getFilePathName(filePath);

			syncFile.setFilePathName(newFilePathName);
			syncFile.setName(String.valueOf(filePath.getFileName()));
			syncFile.setParentFolderId(parentFolderId);

			update(syncFile);

			// Sync files

			List<SyncFile> childSyncFiles = _syncFilePersistence.queryForEq(
				"parentFolderId", syncFile.getTypePK());

			for (SyncFile childSyncFile : childSyncFiles) {
				String childFilePathName = childSyncFile.getFilePathName();

				childFilePathName = childFilePathName.replace(
					oldFilePathName, newFilePathName);

				type = childSyncFile.getType();

				if (type.equals(SyncFile.TYPE_FILE)) {
					childSyncFile.setFilePathName(childFilePathName);

					update(childSyncFile);
				}
				else {
					updateSyncFile(
						Paths.get(childFilePathName),
						childSyncFile.getParentFolderId(), childSyncFile);
				}
			}

			return syncFile;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	private static final String VERSION_DEFAULT = "1.0";

	private static Logger _logger = LoggerFactory.getLogger(
		SyncFileService.class);

	private static SyncFilePersistence _syncFilePersistence =
		getSyncFilePersistence();

}