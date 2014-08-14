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

import com.liferay.sync.engine.documentlibrary.event.Event;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Shinn Lok
 */
public class BaseSyncDLObjectHandler extends BaseJSONHandler {

	public BaseSyncDLObjectHandler(Event event) {
		super(event);
	}

	protected boolean processFilePathChange(
			SyncFile sourceSyncFile, SyncFile targetSyncFile)
		throws Exception {

		String targetSyncFileName = targetSyncFile.getName();

		Path sourceFilePath = Paths.get(sourceSyncFile.getFilePathName());

		if (sourceSyncFile.getParentFolderId() !=
				targetSyncFile.getParentFolderId()) {

			SyncFile targetParentSyncFile = SyncFileService.fetchSyncFile(
				targetSyncFile.getRepositoryId(), getSyncAccountId(),
				targetSyncFile.getParentFolderId());

			Path targetParentFilePath = Paths.get(
				targetParentSyncFile.getFilePathName());

			Path targetFilePath = targetParentFilePath.resolve(
				targetSyncFileName);

			if (Files.exists(sourceFilePath)) {
				Files.move(sourceFilePath, targetFilePath);
			}

			sourceSyncFile.setFilePathName(targetFilePath.toString());
			sourceSyncFile.setName(targetSyncFileName);

			return true;
		}
		else if (!targetSyncFileName.equals(sourceSyncFile.getName())) {
			Path sourceSyncFilePath = Paths.get(
				sourceSyncFile.getFilePathName());

			Path targetFilePath = sourceSyncFilePath.resolveSibling(
				targetSyncFileName);

			if (Files.exists(sourceFilePath)) {
				Files.move(sourceFilePath, targetFilePath);
			}

			sourceSyncFile.setFilePathName(targetFilePath.toString());
			sourceSyncFile.setName(targetSyncFileName);

			return true;
		}

		return false;
	}

}