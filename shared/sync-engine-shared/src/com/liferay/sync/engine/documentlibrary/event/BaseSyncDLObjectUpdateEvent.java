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

package com.liferay.sync.engine.documentlibrary.event;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.documentlibrary.model.SyncDLObjectUpdate;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FilePathNameUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class BaseSyncDLObjectUpdateEvent extends BaseEvent {

	public BaseSyncDLObjectUpdateEvent(
		long syncAccountId, String urlPath, Map<String, Object> parameters) {

		super(syncAccountId, urlPath, parameters);
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

			syncFile.setFilePathName(filePathName);

			syncFile.setSyncAccountId(getSyncAccountId());

			SyncFileService.update(syncFile);

			String type = syncFile.getType();

			if (type.equals(SyncFile.TYPE_FOLDER)) {
				Path filePath = Paths.get(filePathName);

				if (Files.notExists(filePath)) {
					Files.createDirectory(filePath);
				}

				continue;
			}

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("patch", false);
			parameters.put("syncFile", syncFile);

			DownloadFileEvent downloadFileEvent = new DownloadFileEvent(
				getSyncAccountId(), parameters);

			downloadFileEvent.run();
		}

		SyncSite syncSite = SyncSiteService.fetchSyncSite(
			(Long)getParameterValue("repositoryId"), getSyncAccountId());

		syncSite.setLastRemoteSyncTime(syncDLObjectUpdate.getLastAccessTime());

		SyncSiteService.update(syncSite);
	}

}