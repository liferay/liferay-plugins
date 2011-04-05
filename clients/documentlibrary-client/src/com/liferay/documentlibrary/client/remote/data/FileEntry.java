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

package com.liferay.documentlibrary.client.remote.data;

import java.util.UUID;

import org.json.JSONObject;

/**
 * @author Gail Hernandez
 */
public class FileEntry {

	public FileEntry() {
	}

	public FileEntry(JSONObject file) {
		_fileEntryId = file.optLong("fileEntryId");
		_folderId = file.optLong("folderId");
		_groupId = file.optLong("groupId");
		_repositoryId = file.optLong("repositoryId");
		_title = file.optString("title");
		_uuid = UUID.fromString(file.optString("uuid"));
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public long getFolderId() {
		return _folderId;
	}

	public String getFullName() {
		return _title;
	}

	public long getGroupId() {
		return _groupId;
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	public String getTitle() {
		return _title;
	}

	public UUID getUuid() {
		return _uuid;
	}

	public double getVersion() {
		return _version;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUuid(UUID uuid) {
		this._uuid = uuid;
	}

	public void setVersion(double version) {
		_version = version;
	}

	private long _fileEntryId;
	private long _folderId;
	private long _groupId;
	private long _repositoryId;
	private String _title;
	private double _version;
	private UUID _uuid;

}