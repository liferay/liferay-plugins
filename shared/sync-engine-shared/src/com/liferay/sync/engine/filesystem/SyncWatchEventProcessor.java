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
					syncWatchEvent.getFilePath(), syncWatchEvent.getFileType(),
					syncWatchEvent.getKindName(),
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
		Path path = Paths.get(syncWatchEvent.getFilePath());

		Path parentPath = path.getParent();

		SyncFile parentSyncFile = SyncFileService.fetchSyncFile(
			parentPath.toString(), syncWatchEvent.getSyncAccountId());

		List<SyncFile> syncFiles = SyncFileService.findSyncFiles(
			FileUtil.getChecksum(path), syncWatchEvent.getSyncAccountId());

		for (SyncFile syncFile : syncFiles) {
			SyncWatchEvent relatedSyncWatchEvent =
				SyncWatchEventService.fetchSyncWatchEvent(
					syncFile.getFilePath(), SyncWatchEvent.ENTRY_DELETE,
					syncWatchEvent.getTimestamp());

			if (relatedSyncWatchEvent != null) {
				Path oldPath = Paths.get(syncWatchEvent.getFilePath());

				if (parentPath.equals(oldPath.getParent())) {
					Map<String, Object> map = new HashMap<String, Object>();

					map.put("changeLog", syncFile.getVersion() + .1);
					map.put("checksum", FileUtil.getChecksum(path));
					map.put("description", syncFile.getDescription());
					map.put("file", path.toFile());
					map.put("fileEntryId", syncFile.getTypePK());
					map.put("mimeType", syncFile.getMimeType());
					map.put("sourceFileName", path.getFileName());
					map.put("title", path.getFileName());

					UpdateFileEntryEvent updateFileEntryEvent =
						new UpdateFileEntryEvent(
							syncWatchEvent.getSyncAccountId(), map);

					updateFileEntryEvent.run();
				}
				else {
					Map<String, Object> map = new HashMap<String, Object>();

					map.put("fileEntryId", syncFile.getTypePK());
					map.put("newFolderId", parentSyncFile.getTypePK());

					MoveFileEntryEvent moveFileEntryEvent =
						new MoveFileEntryEvent(
							syncWatchEvent.getSyncAccountId(), map);

					moveFileEntryEvent.run();
				}

				_processedSyncWatchEvents.add(relatedSyncWatchEvent);

				return;
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("changeLog", "1.0");
		map.put("checksum", FileUtil.getChecksum(path));
		map.put("description", null);
		map.put("file", path.toFile());
		map.put("folderId", parentSyncFile.getTypePK());
		map.put("mimeType", Files.probeContentType(path));
		map.put("repositoryId", parentSyncFile.getRepositoryId());
		map.put("sourceFileName", path.getFileName());
		map.put("title", path.getFileName());

		AddFileEntryEvent addFileEntryEvent = new AddFileEntryEvent(
			syncWatchEvent.getSyncAccountId(), map);

		addFileEntryEvent.run();
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncWatchEventProcessor.class);

	private Set<SyncWatchEvent> _processedSyncWatchEvents =
		new HashSet<SyncWatchEvent>();
	private ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

}