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

package com.liferay.sync.engine.documentlibrary.handler;

import com.liferay.sync.engine.documentlibrary.event.Event;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.IODeltaUtil;
import com.liferay.sync.engine.util.StreamUtil;

import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

/**
 * @author Shinn Lok
 */
public class DownloadFileHandler extends BaseHandler {

	public DownloadFileHandler(Event event) {
		super(event);
	}

	@Override
	protected void doHandleResponse(HttpResponse httpResponse)
		throws Exception {

		InputStream inputStream = null;

		try {
			SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

			Path filePath = Paths.get(syncFile.getFilePathName());

			HttpEntity httpEntity = httpResponse.getEntity();

			inputStream = httpEntity.getContent();

			Path tempFilePath = Files.createTempFile(
				String.valueOf(filePath.getFileName()), ".tmp");

			if (Files.exists(filePath)) {
				Files.copy(filePath, tempFilePath);
			}

			if ((Boolean)getParameterValue("patch")) {
				IODeltaUtil.patch(tempFilePath, inputStream);

				Files.move(
					tempFilePath, filePath, StandardCopyOption.ATOMIC_MOVE,
					StandardCopyOption.REPLACE_EXISTING);
			}
			else {
				Files.copy(
					inputStream, tempFilePath,
					StandardCopyOption.REPLACE_EXISTING);

				Files.move(
					tempFilePath, filePath, StandardCopyOption.ATOMIC_MOVE,
					StandardCopyOption.REPLACE_EXISTING);
			}

			syncFile.setFileKey(FileUtil.getFileKey(filePath));
			syncFile.setState(SyncFile.STATE_SYNCED);
			syncFile.setUiEvent(SyncFile.UI_EVENT_DOWNLOADED);

			SyncFileService.update(syncFile);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

}