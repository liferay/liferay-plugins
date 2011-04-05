/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.documentlibrary.client.data;

import com.liferay.documentlibrary.client.remote.data.FileEntry;

import java.util.UUID;

/**
 * @author Gail Hernandez
 */
public class FileData extends FolderData {

	public FileData() {
	}

	public FileData(FileEntry newFile) {
		setId(newFile.getFileEntryId());
		setParentFolderId(newFile.getFolderId());
		setRepositoryId(newFile.getRepositoryId());
		setTitle(newFile.getTitle());
		setVersion(newFile.getVersion());
		setUuid(newFile.getUuid());
	}

	public UUID getUuid() {
		return _uuid;
	}

	public double getVersion() {
		return _version;
	}

	public boolean isDownloadCompleted() {
		return _downloadCompleted;
	}

	public void setDownloadCompleted(boolean downloadCompleted) {
		this._downloadCompleted = downloadCompleted;
	}

	public void setVersion(double version) {
		_version = version;
	}

	public void setUuid(UUID uuid) {
		_uuid = uuid;
	}

	private boolean _downloadCompleted;
	private double _version;
	private UUID _uuid;

}