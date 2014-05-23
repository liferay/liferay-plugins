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

package com.liferay.sync.engine.filesystem;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncWatchEvent;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncWatchEventService;
import com.liferay.sync.engine.util.FilePathNameUtil;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Young
 */
public class SyncWatchEventProcessor implements Runnable {

	@Override
	public void run() {
		if (_logger.isTraceEnabled()) {
			_logger.trace("Processing sync watch events");
		}

		List<SyncWatchEvent> syncWatchEvents = SyncWatchEventService.findAll(
			"eventType", true);

		for (SyncWatchEvent syncWatchEvent : syncWatchEvents) {
			SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
				syncWatchEvent.getSyncAccountId());

			if (syncAccount.getState() == SyncAccount.STATE_DISCONNECTED) {
				continue;
			}

			if (_processedSyncWatchEventIds.contains(
					syncWatchEvent.getSyncWatchEventId())) {

				SyncWatchEventService.deleteSyncWatchEvent(
					syncWatchEvent.getSyncWatchEventId());

				continue;
			}

			if (_logger.isDebugEnabled()) {
				_logger.debug(
					"Event type {} file path {} file type {} timestamp {}",
					syncWatchEvent.getEventType(),
					syncWatchEvent.getFilePathName(),
					syncWatchEvent.getFileType(),
					syncWatchEvent.getTimestamp());
			}

			String fileType = syncWatchEvent.getFileType();

			String eventType = syncWatchEvent.getEventType();

			try {
				if (eventType.equals(SyncWatchEvent.EVENT_TYPE_CREATE)) {
					if (fileType.equals(SyncFile.TYPE_FILE)) {
						addFile(syncWatchEvent);
					}
					else {
						addFolder(syncWatchEvent);
					}
				}
				else if (eventType.equals(SyncWatchEvent.EVENT_TYPE_DELETE)) {
					if (fileType.equals(SyncFile.TYPE_FILE)) {
						deleteFile(syncWatchEvent);
					}
					else {
						deleteFolder(syncWatchEvent);
					}
				}
				else if (eventType.equals(SyncWatchEvent.EVENT_TYPE_MODIFY)) {
					if (fileType.equals(SyncFile.TYPE_FILE)) {
						modifyFile(syncWatchEvent);
					}
				}
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);
			}

			syncAccount = SyncAccountService.fetchSyncAccount(
				syncWatchEvent.getSyncAccountId());

			if (syncAccount.getState() != SyncAccount.STATE_DISCONNECTED) {
				SyncWatchEventService.deleteSyncWatchEvent(
					syncWatchEvent.getSyncWatchEventId());
			}
		}

		_processedSyncWatchEventIds.clear();
	}

	protected void addFile(SyncWatchEvent syncWatchEvent) throws Exception {
		Path targetFilePath = Paths.get(syncWatchEvent.getFilePathName());

		if (Files.notExists(targetFilePath)) {
			return;
		}

		Path parentTargetFilePath = targetFilePath.getParent();

		SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(parentTargetFilePath),
			syncWatchEvent.getSyncAccountId());

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(targetFilePath),
			syncWatchEvent.getSyncAccountId());

		if (syncFile == null) {
			syncFile = SyncFileService.fetchSyncFileByFileKey(
				FileUtil.getFileKey(targetFilePath),
				syncWatchEvent.getSyncAccountId());
		}

		if (syncFile == null) {
			SyncFileService.addFileSyncFile(
				targetFilePath, parentSyncFile.getTypePK(),
				parentSyncFile.getRepositoryId(),
				syncWatchEvent.getSyncAccountId());

			return;
		}

		SyncWatchEvent relatedSyncWatchEvent =
			SyncWatchEventService.fetchSyncWatchEvent(
				SyncWatchEvent.EVENT_TYPE_DELETE, syncFile.getFilePathName(),
				syncWatchEvent.getTimestamp());

		if (relatedSyncWatchEvent == null) {
			return;
		}

		Path sourceFilePath = Paths.get(
			relatedSyncWatchEvent.getFilePathName());

		if (parentTargetFilePath.equals(sourceFilePath.getParent())) {
			SyncFileService.updateFileSyncFile(
				targetFilePath, syncWatchEvent.getSyncAccountId(), syncFile,
				false);
		}
		else {
			SyncFileService.moveFileSyncFile(
				targetFilePath, parentSyncFile.getTypePK(),
				syncWatchEvent.getSyncAccountId(), syncFile);

			Path sourceFileNameFilePath = sourceFilePath.getFileName();

			if (!sourceFileNameFilePath.equals(targetFilePath.getFileName())) {
				SyncFileService.updateFileSyncFile(
					targetFilePath, syncWatchEvent.getSyncAccountId(), syncFile,
					false);
			}
		}

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncWatchEvent.getSyncAccountId());

		if (syncAccount.getState() != SyncAccount.STATE_DISCONNECTED) {
			_processedSyncWatchEventIds.add(
				relatedSyncWatchEvent.getSyncWatchEventId());
		}
	}

	protected void addFolder(SyncWatchEvent syncWatchEvent) throws Exception {
		Path targetFilePath = Paths.get(syncWatchEvent.getFilePathName());

		Path parentTargetFilePath = targetFilePath.getParent();

		SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(parentTargetFilePath),
			syncWatchEvent.getSyncAccountId());

		SyncFile syncFile = SyncFileService.fetchSyncFileByFileKey(
			FileUtil.getFileKey(targetFilePath),
			syncWatchEvent.getSyncAccountId());

		if (syncFile == null) {
			SyncFileService.addFolderSyncFile(
				targetFilePath, parentSyncFile.getTypePK(),
				parentSyncFile.getRepositoryId(),
				syncWatchEvent.getSyncAccountId());

			return;
		}

		SyncWatchEvent relatedSyncWatchEvent =
			SyncWatchEventService.fetchSyncWatchEvent(
				SyncWatchEvent.EVENT_TYPE_DELETE, syncFile.getFilePathName(),
				syncWatchEvent.getTimestamp());

		if (relatedSyncWatchEvent == null) {
			return;
		}

		Path sourceFilePath = Paths.get(
			relatedSyncWatchEvent.getFilePathName());

		if (parentTargetFilePath.equals(sourceFilePath.getParent())) {
			SyncFileService.updateFolderSyncFile(
				targetFilePath, syncWatchEvent.getSyncAccountId(), syncFile);
		}
		else {
			SyncFileService.moveFolderSyncFile(
				targetFilePath, parentSyncFile.getTypePK(),
				syncWatchEvent.getSyncAccountId(), syncFile);

			Path sourceFileNameFilePath = sourceFilePath.getFileName();

			if (!sourceFileNameFilePath.equals(targetFilePath.getFileName())) {
				SyncFileService.updateFolderSyncFile(
					targetFilePath, syncWatchEvent.getSyncAccountId(),
					syncFile);
			}
		}

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncWatchEvent.getSyncAccountId());

		if (syncAccount.getState() != SyncAccount.STATE_DISCONNECTED) {
			_processedSyncWatchEventIds.add(
				relatedSyncWatchEvent.getSyncWatchEventId());
		}
	}

	protected void deleteFile(SyncWatchEvent syncWatchEvent) throws Exception {
		Path filePath = Paths.get(syncWatchEvent.getFilePathName());

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(filePath),
			syncWatchEvent.getSyncAccountId());

		if (syncFile == null) {
			return;
		}

		SyncFileService.deleteFileSyncFile(
			syncWatchEvent.getSyncAccountId(), syncFile);
	}

	protected void deleteFolder(SyncWatchEvent syncWatchEvent)
		throws Exception {

		Path filePath = Paths.get(syncWatchEvent.getFilePathName());

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(filePath),
			syncWatchEvent.getSyncAccountId());

		if (syncFile == null) {
			return;
		}

		SyncFileService.deleteFolderSyncFile(
			syncWatchEvent.getSyncAccountId(), syncFile);
	}

	protected void modifyFile(SyncWatchEvent syncWatchEvent) throws Exception {
		Path filePath = Paths.get(syncWatchEvent.getFilePathName());

		SyncFile syncFile = SyncFileService.fetchSyncFileByFileKey(
			FileUtil.getFileKey(filePath), syncWatchEvent.getSyncAccountId());

		if (syncFile == null) {
			return;
		}

		String checksum = syncFile.getChecksum();

		if (checksum.equals(FileUtil.getChecksum(filePath))) {
			return;
		}

		SyncFileService.updateFileSyncFile(
			filePath, syncWatchEvent.getSyncAccountId(), syncFile, false);
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncWatchEventProcessor.class);

	private Set<Long> _processedSyncWatchEventIds = new HashSet<Long>();

}