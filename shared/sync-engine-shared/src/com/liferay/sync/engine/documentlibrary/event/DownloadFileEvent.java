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

import java.io.File;
import java.io.FileOutputStream;

import java.net.URL;

/**
 * @author Shinn Lok
 */
public class DownloadFileEvent extends BaseEvent {

	public DownloadFileEvent(long accountId, SyncFile syncFile, boolean patch) {
		super(accountId, _URL_PATH, null);

		_syncFile = syncFile;
		_patch = patch;
	}

	@Override
	protected String processRequest() throws Exception {
		StringBuilder sb = new StringBuilder(7);

		sb.append(replaceUrlPath(getSyncAccountId()));
		sb.append("/");
		sb.append(_syncFile.getRepositoryId());
		sb.append("/");
		sb.append(_syncFile.getTypeUuid());
		sb.append("/");
		sb.append(_patch);

		return HttpUtil.executeGet(getSyncAccountId(), sb.toString());
	}

	@Override
	protected void processResponse(String response) throws Exception {
		FileOutputStream fileOutputStream = null;

		try {
			File file = new File(_syncFile.getFilePath());

			File dir = file.getParentFile();

			if (!dir.exists()) {
				dir.mkdirs();
			}

			fileOutputStream = new FileOutputStream(file);

			fileOutputStream.write(response.getBytes());
		}
		finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
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

	private static boolean _patch;
	private static SyncFile _syncFile;

}