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

package com.liferay.sync.engine.filesystem;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncWatchEvent;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.service.SyncWatchEventService;
import com.liferay.sync.engine.util.FilePathNameUtil;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Young
 */
public class SyncWatchEventProcessor implements Runnable {

	public void process() {
		_scheduledExecutorService.scheduleAtFixedRate(
			this, 0, 3, TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		if (_logger.isTraceEnabled()) {
			_logger.trace("Processing sync watch events");
		}

		List<SyncWatchEvent> syncWatchEvents = SyncWatchEventService.findAll(
			"kindName", true);

		for (SyncWatchEvent syncWatchEvent : syncWatchEvents) {
			if (_processedSyncWatchEventIds.contains(
					syncWatchEvent.getSyncWatchEventId())) {

				SyncWatchEventService.deleteSyncWatchEvent(
					syncWatchEvent.getSyncWatchEventId());

				continue;
			}

			if (_logger.isDebugEnabled()) {
				_logger.debug(
					"Event file path {} file type {} kind name {} timestamp {}",
					syncWatchEvent.getFilePathName(),
					syncWatchEvent.getFileType(), syncWatchEvent.getKindName(),
					syncWatchEvent.getTimestamp());
			}

			String fileType = syncWatchEvent.getFileType();

			String kindName = syncWatchEvent.getKindName();

			try {
				if (kindName.equals(SyncWatchEvent.ENTRY_CREATE)) {
					if (fileType.equals(SyncFile.TYPE_FILE)) {
						addFile(syncWatchEvent);
					}
					else {
						addFolder(syncWatchEvent);
					}
				}
				else if (kindName.equals(SyncWatchEvent.ENTRY_DELETE)) {
					if (fileType.equals(SyncFile.TYPE_FILE)) {
						deleteFile(syncWatchEvent);
					}
					else {
						deleteFolder(syncWatchEvent);
					}
				}
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);
			}

			SyncWatchEventService.deleteSyncWatchEvent(
				syncWatchEvent.getSyncWatchEventId());
		}

		_processedSyncWatchEventIds.clear();
	}

	protected void addFile(SyncWatchEvent syncWatchEvent) throws Exception {
		Path filePath = Paths.get(syncWatchEvent.getFilePathName());

		Path parentFilePath = filePath.getParent();

		SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(parentFilePath),
			syncWatchEvent.getSyncAccountId());

		SyncFile syncFile = SyncFileService.fetchSyncFileByFileKey(
			FileUtil.getFileKey(filePath), syncWatchEvent.getSyncAccountId());

		if (syncFile == null) {
			SyncFileService.addFileSyncFile(
				filePath, parentSyncFile.getTypePK(),
				parentSyncFile.getRepositoryId(),
				syncWatchEvent.getSyncAccountId());

			return;
		}

		SyncWatchEvent relatedSyncWatchEvent =
			SyncWatchEventService.fetchSyncWatchEvent(
				syncFile.getFilePathName(), SyncWatchEvent.ENTRY_DELETE,
				syncWatchEvent.getTimestamp());

		if (relatedSyncWatchEvent == null) {
			return;
		}

		Path srcFilePath = Paths.get(relatedSyncWatchEvent.getFilePathName());

		if (parentFilePath.equals(srcFilePath.getParent())) {
			SyncFileService.updateFileSyncFile(
				filePath, syncWatchEvent.getSyncAccountId(), syncFile);
		}
		else {
			SyncFileService.moveFileSyncFile(
				filePath, parentSyncFile.getTypePK(),
				syncWatchEvent.getSyncAccountId(), syncFile);
		}

		_processedSyncWatchEventIds.add(
			relatedSyncWatchEvent.getSyncWatchEventId());
	}

	protected void addFolder(SyncWatchEvent syncWatchEvent) throws Exception {
		Path filePath = Paths.get(syncWatchEvent.getFilePathName());

		String filePathName = FilePathNameUtil.getFilePathName(filePath);

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncWatchEvent.getSyncAccountId());

		if (filePathName.equals(syncAccount.getFilePathName()) ||
			(SyncSiteService.fetchSyncSite(
				filePathName, syncWatchEvent.getSyncAccountId()) != null)) {

			return;
		}

		Path parentFilePath = filePath.getParent();

		SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(parentFilePath),
			syncWatchEvent.getSyncAccountId());

		SyncFile syncFile = SyncFileService.fetchSyncFileByFileKey(
			FileUtil.getFileKey(filePath), syncWatchEvent.getSyncAccountId());

		if (syncFile == null) {
			SyncFileService.addFolderSyncFile(
				filePath, parentSyncFile.getTypePK(),
				parentSyncFile.getRepositoryId(),
				syncWatchEvent.getSyncAccountId());

			return;
		}

		SyncWatchEvent relatedSyncWatchEvent =
			SyncWatchEventService.fetchSyncWatchEvent(
				syncFile.getFilePathName(), SyncWatchEvent.ENTRY_DELETE,
				syncWatchEvent.getTimestamp());

		if (relatedSyncWatchEvent == null) {
			return;
		}

		Path srcFilePath = Paths.get(relatedSyncWatchEvent.getFilePathName());

		if (parentFilePath.equals(srcFilePath.getParent())) {
			SyncFileService.updateFolderSyncFile(
				filePath, syncWatchEvent.getSyncAccountId(), syncFile);
		}
		else {
			SyncFileService.moveFolderSyncFile(
				filePath, parentSyncFile.getTypePK(),
				syncWatchEvent.getSyncAccountId(), syncFile);
		}

		_processedSyncWatchEventIds.add(
			relatedSyncWatchEvent.getSyncWatchEventId());
	}

	protected void deleteFile(SyncWatchEvent syncWatchEvent) throws Exception {
		Path filePath = Paths.get(syncWatchEvent.getFilePathName());

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(filePath),
			syncWatchEvent.getSyncAccountId());

		SyncFileService.deleteFileSyncFile(
			syncWatchEvent.getSyncAccountId(), syncFile);
	}

	protected void deleteFolder(SyncWatchEvent syncWatchEvent)
		throws Exception {

		Path filePath = Paths.get(syncWatchEvent.getFilePathName());

		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(filePath),
			syncWatchEvent.getSyncAccountId());

		SyncFileService.deleteFolderSyncFile(
			syncWatchEvent.getSyncAccountId(), syncFile);
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncWatchEventProcessor.class);

	private Set<Long> _processedSyncWatchEventIds = new HashSet<Long>();
	private ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

}