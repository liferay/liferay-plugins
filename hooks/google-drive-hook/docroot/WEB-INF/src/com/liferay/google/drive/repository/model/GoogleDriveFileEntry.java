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

package com.liferay.google.drive.repository.model;

import com.google.api.services.drive.model.File;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.repository.external.ExtRepositoryFileEntry;

/**
 * @author Sergio González
 */
public class GoogleDriveFileEntry
	extends GoogleDriveObject implements ExtRepositoryFileEntry {

	public GoogleDriveFileEntry(File file) {
		super(file);

		_downloadURL = GetterUtil.getString(file.getDownloadUrl());
		_mimeType = GetterUtil.getString(file.getMimeType());
		_title = GetterUtil.getString(file.getTitle());
	}

	@Override
	public String getCheckedOutBy() {
		return StringPool.BLANK;
	}

	public String getDownloadURL() {
		return _downloadURL;
	}

	@Override
	public String getMimeType() {
		return _mimeType;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	private String _downloadURL;
	private String _mimeType;
	private String _title;

}