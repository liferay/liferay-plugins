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

import com.liferay.sync.engine.documentlibrary.event.AddFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.AddFolderEvent;
import com.liferay.sync.engine.documentlibrary.event.CancelCheckOutEvent;
import com.liferay.sync.engine.documentlibrary.event.CheckInFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.CheckOutFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFileEntryToTrashEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFolderEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFolderToTrashEvent;
import com.liferay.sync.engine.documentlibrary.event.PatchFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.UpdateFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.UpdateFolderEvent;
import com.liferay.sync.engine.model.ModelListener;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncFileModelListener;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.persistence.SyncFilePersistence;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.IODeltaUtil;
import com.liferay.sync.engine.util.OSDetector;
import com.liferay.sync.engine.util.PropsValues;

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

		if (Files.notExists(filePath)) {
			return null;
		}

		String checksum = FileUtil.getChecksum(filePath);
		String name = String.valueOf(filePath.getFileName());
		String mimeType = Files.probeContentType(filePath);

		SyncFile syncFile = addSyncFile(
			null, checksum, null, filePath.toString(), mimeType, name, folderId,
			repositoryId, syncAccountId, SyncFile.TYPE_FILE);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("changeLog", "");
		parameters.put("checksum", checksum);
		parameters.put("description", "");
		parameters.put("filePath", filePath);
		parameters.put("folderId", folderId);
		parameters.put("mimeType", mimeType);
		parameters.put("repositoryId", repositoryId);

		SyncSite syncSite = SyncSiteService.fetchSyncSite(
			repositoryId, syncAccountId);

		if (syncSite.getType() != SyncSite.TYPE_SYSTEM) {
			parameters.put("serviceContext.addGroupPermissions", true);
		}

		if (syncSite.getType() == SyncSite.TYPE_OPEN) {
			parameters.put("serviceContext.addGuestPermissions", true);
		}

		parameters.put("serviceContext.attributes.overwrite", true);
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

		if (Files.notExists(filePath)) {
			return null;
		}

		String name = String.valueOf(filePath.getFileName());

		SyncFile syncFile = addSyncFile(
			null, null, null, filePath.toString(),
			Files.probeContentType(filePath), name, parentFolderId,
			repositoryId, syncAccountId, SyncFile.TYPE_FOLDER);

		// Remote sync file

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("description", "");
		parameters.put("name", name);
		parameters.put("parentFolderId", parentFolderId);
		parameters.put("repositoryId", repositoryId);

		SyncSite syncSite = SyncSiteService.fetchSyncSite(
			repositoryId, syncAccountId);

		if (syncSite.getType() != SyncSite.TYPE_SYSTEM) {
			parameters.put("serviceContext.addGroupPermissions", true);
		}

		if (syncSite.getType() == SyncSite.TYPE_OPEN) {
			parameters.put("serviceContext.addGuestPermissions", true);
		}

		parameters.put("serviceContext.attributes.overwrite", true);
		parameters.put("syncFile", syncFile);

		AddFolderEvent addFolderEvent = new AddFolderEvent(
			syncAccountId, parameters);

		addFolderEvent.run();

		return syncFile;
	}

	public static SyncFile addSyncFile(
			String changeLog, String checksum, String description,
			String filePathName, String mimeType, String name,
			long parentFolderId, long repositoryId, long syncAccountId,
			String type)
		throws Exception {

		SyncFile syncFile = new SyncFile();

		syncFile.setChangeLog(changeLog);
		syncFile.setChecksum(checksum);
		syncFile.setDescription(description);
		syncFile.setFilePathName(filePathName);
		syncFile.setLocalSyncTime(System.currentTimeMillis());
		syncFile.setMimeType(mimeType);
		syncFile.setName(name);
		syncFile.setParentFolderId(parentFolderId);
		syncFile.setRepositoryId(repositoryId);
		syncFile.setSyncAccountId(syncAccountId);
		syncFile.setType(type);
		syncFile.setUiEvent(SyncFile.UI_EVENT_ADDED_LOCAL);

		_syncFilePersistence.create(syncFile);

		updateFileKeySyncFile(syncFile);

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

		parameters.put("changeLog", syncFile.getChangeLog());
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

		if (Files.exists(Paths.get(syncFile.getFilePathName()))) {
			return syncFile;
		}

		syncFile.setUiEvent(SyncFile.UI_EVENT_DELETED_LOCAL);

		deleteSyncFile(syncFile);

		// Remote sync file

		if (syncFile.getState() == SyncFile.STATE_ERROR) {
			return syncFile;
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("syncFile", syncFile);

		MoveFileEntryToTrashEvent moveFileEntryToTrashEvent =
			new MoveFileEntryToTrashEvent(syncAccountId, parameters);

		moveFileEntryToTrashEvent.run();

		return syncFile;
	}

	public static SyncFile deleteFolderSyncFile(
			long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		if (Files.exists(Paths.get(syncFile.getFilePathName()))) {
			return syncFile;
		}

		syncFile.setUiEvent(SyncFile.UI_EVENT_DELETED_LOCAL);

		deleteSyncFile(syncFile);

		// Remote sync file

		if (syncFile.getState() == SyncFile.STATE_ERROR) {
			return syncFile;
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("folderId", syncFile.getTypePK());
		parameters.put("syncFile", syncFile);

		MoveFolderToTrashEvent moveFolderToTrashEvent =
			new MoveFolderToTrashEvent(syncAccountId, parameters);

		moveFolderToTrashEvent.run();

		return syncFile;
	}

	public static void deleteSyncFile(SyncFile syncFile) {
		deleteSyncFile(syncFile, true);
	}

	public static void deleteSyncFile(SyncFile syncFile, boolean notify) {
		try {

			// Sync file

			_syncFilePersistence.delete(syncFile, notify);

			if (!syncFile.isFolder()) {
				return;
			}

			// Sync files

			List<SyncFile> childSyncFiles = _syncFilePersistence.queryForEq(
				"parentFolderId", syncFile.getTypePK());

			for (SyncFile childSyncFile : childSyncFiles) {
				if (childSyncFile.isFolder()) {
					deleteSyncFile(childSyncFile);
				}
				else {
					childSyncFile.setUiEvent(syncFile.getUiEvent());

					_syncFilePersistence.delete(childSyncFile);
				}
			}
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}
		}
	}

	public static SyncFile fetchSyncFile(long syncFileId) {
		try {
			return _syncFilePersistence.queryForId(syncFileId);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
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

	public static SyncFile fetchSyncFile(String filePathName) {
		try {
			return _syncFilePersistence.fetchByFilePathName(filePathName);
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

		if ((fileKey == null) || fileKey.equals("")) {
			return null;
		}

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
		long syncAccountId, int uiEvent) {

		try {
			return _syncFilePersistence.findByS_U(syncAccountId, uiEvent);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return Collections.emptyList();
		}
	}

	public static List<SyncFile> findSyncFiles(
		String filePathName, long localSyncTime) {

		try {
			return _syncFilePersistence.findByF_L(filePathName, localSyncTime);
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

			registerModelListener(new SyncFileModelListener());

			return _syncFilePersistence;
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return null;
		}
	}

	public static long getSyncFilesCount(int uiEvent) {
		try {
			return _syncFilePersistence.countByUIEvent(uiEvent);
		}
		catch (SQLException sqle) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(sqle.getMessage(), sqle);
			}

			return 0;
		}
	}

	public static SyncFile moveFileSyncFile(
			Path filePath, long folderId, long syncAccountId, SyncFile syncFile)
		throws Exception {

		// Local sync file

		syncFile.setFilePathName(filePath.toString());
		syncFile.setParentFolderId(folderId);
		syncFile.setUiEvent(SyncFile.UI_EVENT_MOVED_LOCAL);

		update(syncFile);

		// Remote sync file

		if (syncFile.getState() == SyncFile.STATE_ERROR) {
			return syncFile;
		}

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

		if (syncFile.getState() == SyncFile.STATE_ERROR) {
			return syncFile;
		}

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

	public static SyncFile updateFileKeySyncFile(SyncFile syncFile) {
		if (OSDetector.isWindows()) {
			Path filePath = Paths.get(syncFile.getFilePathName());

			FileUtil.writeFileKey(
				filePath, String.valueOf(syncFile.getSyncFileId()));

			syncFile.setFileKey(String.valueOf(syncFile.getSyncFileId()));
		}
		else {
			syncFile.setFileKey(
				FileUtil.getFileKey(syncFile.getFilePathName()));
		}

		return update(syncFile);
	}

	public static SyncFile updateFileSyncFile(
			Path filePath, long syncAccountId, SyncFile syncFile, boolean force)
		throws Exception {

		// Local sync file

		if (OSDetector.isWindows()) {
			FileUtil.writeFileKey(
				filePath, String.valueOf(syncFile.getSyncFileId()));
		}

		Path deltaFilePath = null;

		String name = _getName(filePath, syncFile);
		String sourceChecksum = syncFile.getChecksum();
		String sourceFileName = syncFile.getName();
		String sourceVersion = syncFile.getVersion();
		String targetChecksum = FileUtil.getChecksum(filePath);

		if (!sourceChecksum.equals(targetChecksum) &&
			!IODeltaUtil.isIgnoredFilePatchingExtension(syncFile)) {

			deltaFilePath = Files.createTempFile(
				String.valueOf(filePath.getFileName()), ".tmp");

			deltaFilePath = IODeltaUtil.delta(
				filePath, IODeltaUtil.getChecksumsFilePath(syncFile),
				deltaFilePath);
		}

		syncFile.setChecksum(targetChecksum);
		syncFile.setFilePathName(filePath.toString());
		syncFile.setName(name);
		syncFile.setUiEvent(SyncFile.UI_EVENT_UPDATED_LOCAL);

		update(syncFile);

		// Remote sync file

		if (syncFile.getState() == SyncFile.STATE_ERROR) {
			return syncFile;
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("changeLog", syncFile.getChangeLog());
		parameters.put("checksum", targetChecksum);
		parameters.put("description", syncFile.getDescription());
		parameters.put("fileEntryId", syncFile.getTypePK());
		parameters.put("majorVersion", false);
		parameters.put("mimeType", syncFile.getMimeType());
		parameters.put("sourceFileName", name);
		parameters.put("syncFile", syncFile);
		parameters.put("title", name);

		if (sourceChecksum.equals(targetChecksum) && !force) {
			parameters.put("-file", null);
		}
		else {
			if ((deltaFilePath != null) &&
				(Files.size(filePath) / Files.size(deltaFilePath)) >=
					PropsValues.SYNC_FILE_PATCHING_THRESHOLD_SIZE_RATIO) {

				parameters.put("deltaFilePath", deltaFilePath);
				parameters.put("sourceFileName", sourceFileName);
				parameters.put("sourceVersion", sourceVersion);

				PatchFileEntryEvent patchFileEntryEvent =
					new PatchFileEntryEvent(syncAccountId, parameters);

				patchFileEntryEvent.run();

				return syncFile;
			}

			parameters.put("filePath", filePath);
		}

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

		if (syncFile.getState() == SyncFile.STATE_ERROR) {
			return syncFile;
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("description", syncFile.getDescription());
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

			String sourceFilePathName = syncFile.getFilePathName();
			String targetFilePathName = filePath.toString();

			syncFile.setFilePathName(targetFilePathName);
			syncFile.setLocalSyncTime(System.currentTimeMillis());
			syncFile.setName(String.valueOf(filePath.getFileName()));
			syncFile.setParentFolderId(parentFolderId);

			if (!syncFile.isFolder()) {
				return update(syncFile);
			}

			update(syncFile);

			// Sync files

			List<SyncFile> childSyncFiles = _syncFilePersistence.queryForEq(
				"parentFolderId", syncFile.getTypePK());

			for (SyncFile childSyncFile : childSyncFiles) {
				String childFilePathName = childSyncFile.getFilePathName();

				childFilePathName = childFilePathName.replace(
					sourceFilePathName, targetFilePathName);

				if (childSyncFile.isFolder()) {
					updateSyncFile(
						Paths.get(childFilePathName),
						childSyncFile.getParentFolderId(), childSyncFile);
				}
				else {
					childSyncFile.setFilePathName(childFilePathName);

					update(childSyncFile);
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

	private static String _getName(Path filePath, SyncFile syncFile) {
		String name = String.valueOf(filePath.getFileName());

		String sanitizedFileName = FileUtil.getSanitizedFileName(
			syncFile.getName(), syncFile.getExtension());

		if (sanitizedFileName.equals(
				FileUtil.getSanitizedFileName(name, syncFile.getExtension()))) {

			name = syncFile.getName();
		}

		return name;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncFileService.class);

	private static SyncFilePersistence _syncFilePersistence =
		getSyncFilePersistence();

}