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

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.util.HttpUtil;

import java.io.OutputStream;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class DownloadFileEvent extends BaseEvent {

	public DownloadFileEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, _URL_PATH, parameters);
	}

	@Override
	protected String processRequest() throws Exception {
		SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

		StringBuilder sb = new StringBuilder(7);

		sb.append(replaceUrlPath(getSyncAccountId()));
		sb.append("/");
		sb.append(syncFile.getRepositoryId());
		sb.append("/");
		sb.append(syncFile.getTypeUuid());
		sb.append("/");
		sb.append(getParameterValue("patch"));

		return HttpUtil.executeGet(getSyncAccountId(), sb.toString());
	}

	@Override
	protected void processResponse(String response) throws Exception {
		OutputStream outputStream = null;

		try {
			SyncFile syncFile = (SyncFile)getParameterValue("syncFile");

			Path filePath = Paths.get(syncFile.getFilePathName());

			outputStream = Files.newOutputStream(filePath);

			outputStream.write(response.getBytes());
		}
		finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	protected String replaceUrlPath(long syncAccountId) throws Exception {
		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncAccountId);

		String url = syncAccount.getUrl();

		URL urlObj = new URL(url);

		return url.replace(urlObj.getPath(), _URL_PATH);
	}

	private static final String _URL_PATH = "/sync-web/download";

}