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

package com.liferay.google.drive.repository;

import com.google.api.services.drive.model.File;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergio González
 */
public class GoogleDriveCache implements Cloneable {

	public static GoogleDriveCache getInstance() {
		return _googleDriveThreadLocal.get();
	}

	@Override
	public GoogleDriveCache clone() {
		if (_log.isInfoEnabled()) {
			Thread currentThread = Thread.currentThread();

			_log.info("Create " + currentThread.getName());
		}

		try {
			return (GoogleDriveCache)super.clone();
		}
		catch (CloneNotSupportedException cnse) {
			throw new RuntimeException(cnse);
		}
	}

	public File get(String googleDriveFileId) {
		Map<String, File> googleDriveFiles = _getGoogleDriveFiles();

		File googleDriveFile = googleDriveFiles.get(googleDriveFileId);

		if (googleDriveFile != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Hit " + googleDriveFileId);
			}
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug("Miss " + googleDriveFileId);
			}
		}

		return googleDriveFile;
	}

	public void put(File googleDriveFile) {
		Map<String, File> googleDriveFiles = _getGoogleDriveFiles();

		String googleDriveFileId = googleDriveFile.getId();

		if (_log.isInfoEnabled()) {
			_log.info("Put " + googleDriveFileId);
		}

		googleDriveFiles.put(googleDriveFileId, googleDriveFile);
	}

	public void remove(String googleDriveFileId) {
		Map<String, File> googleDriveFiles = _getGoogleDriveFiles();

		if (_log.isInfoEnabled()) {
			_log.info("Remove " + googleDriveFileId);
		}

		googleDriveFiles.remove(googleDriveFileId);
	}

	private Map<String, File> _getGoogleDriveFiles() {
		if (_googleDriveFiles == null) {
			_googleDriveFiles = new HashMap<>();
		}

		return _googleDriveFiles;
	}

	private static Log _log = LogFactoryUtil.getLog(GoogleDriveCache.class);

	private static ThreadLocal<GoogleDriveCache> _googleDriveThreadLocal =
		new AutoResetThreadLocal<>(
			GoogleDriveCache.class.getName(), new GoogleDriveCache());

	private Map<String, File> _googleDriveFiles;

}