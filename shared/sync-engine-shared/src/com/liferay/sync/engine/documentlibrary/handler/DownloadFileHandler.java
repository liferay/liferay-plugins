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
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncAccountService;
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
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class DownloadFileHandler extends BaseHandler {

	public DownloadFileHandler(Event event) {
		super(event);
	}

	@Override
	public void handleException(Exception e) {
		_logger.error(e.getMessage(), e);

		if (!(e instanceof HttpResponseException)) {
			super.handleException(e);

			return;
		}

		HttpResponseException hre = (HttpResponseException)e;

		int statusCode = hre.getStatusCode();

		if (statusCode != HttpStatus.SC_NOT_FOUND) {
			super.handleException(e);

			return;
		}

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		if (syncAccount.getState() != SyncAccount.STATE_CONNECTED) {
			super.handleException(e);

			return;
		}

		SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

		SyncFileService.deleteSyncFile(syncFile, false);
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
				String.valueOf(syncFile.getSyncFileId()), ".tmp");

			if (Files.exists(filePath)) {
				Files.copy(
					filePath, tempFilePath,
					StandardCopyOption.REPLACE_EXISTING);
			}

			if ((Boolean)getParameterValue("patch")) {
				IODeltaUtil.patch(tempFilePath, inputStream);
			}
			else {
				Files.copy(
					inputStream, tempFilePath,
					StandardCopyOption.REPLACE_EXISTING);
			}

			if (syncFile.getFileKey() == null) {
				syncFile.setUiEvent(SyncFile.UI_EVENT_DOWNLOADED_NEW);
			}
			else {
				syncFile.setUiEvent(SyncFile.UI_EVENT_DOWNLOADED_UPDATE);
			}

			syncFile.setFileKey(FileUtil.getFileKey(tempFilePath));
			syncFile.setState(SyncFile.STATE_SYNCED);

			SyncFileService.update(syncFile);

			Files.move(
				tempFilePath, filePath, StandardCopyOption.ATOMIC_MOVE,
				StandardCopyOption.REPLACE_EXISTING);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		DownloadFileHandler.class);

}