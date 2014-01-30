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

import com.liferay.sync.engine.documentlibrary.event.AddFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.MoveFileEntryEvent;
import com.liferay.sync.engine.documentlibrary.event.UpdateFileEntryEvent;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncWatchEvent;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncWatchEventService;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
			if (_processedSyncWatchEvents.contains(syncWatchEvent)) {
				continue;
			}

			if (_logger.isDebugEnabled()) {
				_logger.debug(
					"Event file path {} file type {} kind name {} timestamp {}",
					syncWatchEvent.getFilePathName(),
					syncWatchEvent.getFileType(), syncWatchEvent.getKindName(),
					syncWatchEvent.getTimestamp());
			}

			String kindName = syncWatchEvent.getKindName();

			if (kindName.equals(SyncWatchEvent.ENTRY_CREATE)) {
				String fileType = syncWatchEvent.getFileType();

				if (fileType.equals(SyncFile.TYPE_FILE)) {
					try {
						addFile(syncWatchEvent);
					}
					catch (Exception e) {
						_logger.error(e.getMessage(), e);
					}
				}
			}

			SyncWatchEventService.deleteSyncWatchEvent(
				syncWatchEvent.getSyncWatchEventId());
		}

		_processedSyncWatchEvents.clear();
	}

	protected void addFile(SyncWatchEvent syncWatchEvent) throws Exception {
		Path path = Paths.get(syncWatchEvent.getFilePathName());

		Path parentPath = path.getParent();

		SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
			parentPath.toString(), syncWatchEvent.getSyncAccountId());

		List<SyncFile> syncFiles = SyncFileService.findSyncFiles(
			FileUtil.getChecksum(path), syncWatchEvent.getSyncAccountId());

		for (SyncFile syncFile : syncFiles) {
			SyncWatchEvent relatedSyncWatchEvent =
				SyncWatchEventService.fetchSyncWatchEvent(
					syncFile.getFilePathName(), SyncWatchEvent.ENTRY_DELETE,
					syncWatchEvent.getTimestamp());

			if (relatedSyncWatchEvent != null) {
				Path srcFilePath = Paths.get(syncWatchEvent.getFilePathName());

				if (parentPath.equals(srcFilePath.getParent())) {
					Map<String, Object> parameters =
						new HashMap<String, Object>();

					parameters.put("changeLog", syncFile.getVersion() + .1);
					parameters.put("checksum", FileUtil.getChecksum(path));
					parameters.put("description", syncFile.getDescription());
					parameters.put("file", path.toFile());
					parameters.put("fileEntryId", syncFile.getTypePK());
					parameters.put("mimeType", syncFile.getMimeType());
					parameters.put("sourceFileName", path.getFileName());
					parameters.put("title", path.getFileName());

					UpdateFileEntryEvent updateFileEntryEvent =
						new UpdateFileEntryEvent(
							syncWatchEvent.getSyncAccountId(), parameters);

					updateFileEntryEvent.run();
				}
				else {
					Map<String, Object> parameters =
						new HashMap<String, Object>();

					parameters.put("fileEntryId", syncFile.getTypePK());
					parameters.put("newFolderId", parentSyncFile.getTypePK());

					MoveFileEntryEvent moveFileEntryEvent =
						new MoveFileEntryEvent(
							syncWatchEvent.getSyncAccountId(), parameters);

					moveFileEntryEvent.run();
				}

				_processedSyncWatchEvents.add(relatedSyncWatchEvent);

				return;
			}
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("changeLog", "1.0");
		parameters.put("checksum", FileUtil.getChecksum(path));
		parameters.put("description", null);
		parameters.put("file", path.toFile());
		parameters.put("folderId", parentSyncFile.getTypePK());
		parameters.put("mimeType", Files.probeContentType(path));
		parameters.put("repositoryId", parentSyncFile.getRepositoryId());
		parameters.put("sourceFileName", path.getFileName());
		parameters.put("title", path.getFileName());

		AddFileEntryEvent addFileEntryEvent = new AddFileEntryEvent(
			syncWatchEvent.getSyncAccountId(), parameters);

		addFileEntryEvent.run();
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncWatchEventProcessor.class);

	private Set<SyncWatchEvent> _processedSyncWatchEvents =
		new HashSet<SyncWatchEvent>();
	private ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

}