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

package com.liferay.sync.engine.documentlibrary.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.documentlibrary.event.DownloadFileEvent;
import com.liferay.sync.engine.documentlibrary.event.Event;
import com.liferay.sync.engine.documentlibrary.model.SyncDLObjectUpdate;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FilePathNameUtil;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.IODeltaUtil;

import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class GetSyncDLObjectUpdateHandler extends BaseJSONHandler {

	public GetSyncDLObjectUpdateHandler(Event event) {
		super(event);
	}

	protected void addFile(SyncFile syncFile, String filePathName)
		throws Exception {

		Path filePath = Paths.get(filePathName);

		if (Files.exists(filePath)) {
			if (syncFile.isFolder()) {
				return;
			}

			String checksum = FileUtil.getChecksum(filePath);

			if (checksum.equals(syncFile.getChecksum())) {
				return;
			}
		}

		syncFile.setFilePathName(filePathName);
		syncFile.setSyncAccountId(getSyncAccountId());
		syncFile.setUiEvent(SyncFile.UI_EVENT_ADDED_REMOTE);

		if (syncFile.isFolder()) {
			Files.createDirectories(filePath);

			syncFile.setFileKey(FileUtil.getFileKey(filePath));

			SyncFileService.update(syncFile);
		}
		else {
			SyncFileService.update(syncFile);

			downloadFile(syncFile, null, false);
		}
	}

	protected void deleteFile(SyncFile targetSyncFile, boolean trashed)
		throws Exception {

		SyncFile sourceSyncFile = SyncFileService.fetchSyncFile(
			targetSyncFile.getRepositoryId(), getSyncAccountId(),
			targetSyncFile.getTypePK());

		if (sourceSyncFile == null) {
			return;
		}

		if (trashed) {
			sourceSyncFile.setUiEvent(SyncFile.UI_EVENT_TRASHED_REMOTE);
		}
		else {
			sourceSyncFile.setUiEvent(SyncFile.UI_EVENT_DELETED_REMOTE);
		}

		Files.walkFileTree(
			Paths.get(sourceSyncFile.getFilePathName()),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(
						Path filePath, IOException ioe)
					throws IOException {

					Files.delete(filePath);

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
						Path filePath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Files.delete(filePath);

					return FileVisitResult.CONTINUE;
				}

			});

		SyncFileService.deleteSyncFile(sourceSyncFile);
	}

	protected void downloadFile(
		SyncFile syncFile, String sourceVersion, boolean patch) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("syncFile", syncFile);

		String targetVersion = syncFile.getVersion();

		if (patch && !sourceVersion.equals(targetVersion)) {
			parameters.put("patch", true);
			parameters.put("sourceVersion", sourceVersion);
			parameters.put("targetVersion", targetVersion);
		}
		else {
			parameters.put("patch", false);
		}

		DownloadFileEvent downloadFileEvent = new DownloadFileEvent(
			getSyncAccountId(), parameters);

		downloadFileEvent.run();
	}

	protected void moveFile(SyncFile targetSyncFile, String targetFilePathName)
		throws Exception {

		SyncFile sourceSyncFile = SyncFileService.fetchSyncFile(
			targetSyncFile.getRepositoryId(), getSyncAccountId(),
			targetSyncFile.getTypePK());

		Path sourceFilePath = Paths.get(sourceSyncFile.getFilePathName());

		Path targetFilePath = Paths.get(targetFilePathName);

		Files.move(sourceFilePath, targetFilePath);

		sourceSyncFile.setFilePathName(targetFilePathName);
		sourceSyncFile.setModifiedTime(targetSyncFile.getModifiedTime());
		sourceSyncFile.setParentFolderId(targetSyncFile.getParentFolderId());
		sourceSyncFile.setUiEvent(SyncFile.UI_EVENT_MOVED_REMOTE);

		SyncFileService.update(sourceSyncFile);
	}

	@Override
	protected void processResponse(String response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		SyncDLObjectUpdate syncDLObjectUpdate = objectMapper.readValue(
			response, new TypeReference<SyncDLObjectUpdate>() {});

		for (SyncFile syncFile : syncDLObjectUpdate.getSyncDLObjects()) {
			SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
				syncFile.getRepositoryId(), getSyncAccountId(),
				syncFile.getParentFolderId());

			String filePathName = null;

			if (parentSyncFile != null) {
				filePathName = FilePathNameUtil.getFilePathName(
					parentSyncFile.getFilePathName(), syncFile.getName());
			}

			String event = syncFile.getEvent();

			if (event.equals(SyncFile.EVENT_ADD) ||
				event.equals(SyncFile.EVENT_GET) ||
				event.equals(SyncFile.EVENT_RESTORE)) {

				addFile(syncFile, filePathName);
			}
			else if (event.equals(SyncFile.EVENT_DELETE)) {
				deleteFile(syncFile, false);
			}
			else if (event.equals(SyncFile.EVENT_MOVE)) {
				moveFile(syncFile, filePathName);
			}
			else if (event.equals(SyncFile.EVENT_TRASH)) {
				deleteFile(syncFile, true);
			}
			else if (event.equals(SyncFile.EVENT_UPDATE)) {
				updateFile(syncFile);
			}
		}

		SyncSite syncSite = SyncSiteService.fetchSyncSite(
			(Long)getParameterValue("repositoryId"), getSyncAccountId());

		syncSite.setRemoteSyncTime(syncDLObjectUpdate.getLastAccessTime());

		SyncSiteService.update(syncSite);
	}

	protected void updateFile(SyncFile targetSyncFile) throws Exception {
		SyncFile sourceSyncFile = SyncFileService.fetchSyncFile(
			targetSyncFile.getRepositoryId(), getSyncAccountId(),
			targetSyncFile.getTypePK());

		String sourceVersion = sourceSyncFile.getVersion();

		Path sourceFilePath = Paths.get(sourceSyncFile.getFilePathName());

		String sourceFileName = String.valueOf(sourceFilePath.getFileName());

		if (!sourceFileName.equals(targetSyncFile.getName())) {
			Path targetFilePath = sourceFilePath.resolveSibling(
				targetSyncFile.getName());

			Files.move(sourceFilePath, targetFilePath);

			sourceSyncFile.setFilePathName(
				FilePathNameUtil.getFilePathName(targetFilePath));
			sourceSyncFile.setName(targetSyncFile.getName());
		}

		sourceSyncFile.setChangeLog(targetSyncFile.getChangeLog());
		sourceSyncFile.setChecksum(targetSyncFile.getChecksum());
		sourceSyncFile.setDescription(targetSyncFile.getDescription());
		sourceSyncFile.setExtension(targetSyncFile.getExtension());
		sourceSyncFile.setExtraSettings(targetSyncFile.getExtraSettings());
		sourceSyncFile.setLockExpirationDate(
			targetSyncFile.getLockExpirationDate());
		sourceSyncFile.setLockUserId(targetSyncFile.getLockUserId());
		sourceSyncFile.setLockUserName(targetSyncFile.getLockUserName());
		sourceSyncFile.setModifiedTime(targetSyncFile.getModifiedTime());
		sourceSyncFile.setSize(targetSyncFile.getSize());
		sourceSyncFile.setUiEvent(SyncFile.UI_EVENT_UPDATED_REMOTE);
		sourceSyncFile.setVersion(targetSyncFile.getVersion());

		SyncFileService.update(sourceSyncFile);

		if (Files.exists(sourceFilePath) && !targetSyncFile.isFolder()) {
			String checksum = FileUtil.getChecksum(sourceFilePath);

			if (checksum.equals(targetSyncFile.getChecksum())) {
				return;
			}

			downloadFile(
				sourceSyncFile, sourceVersion,
				!IODeltaUtil.isIgnoredFilePatchingExtension(targetSyncFile));
		}
	}

}