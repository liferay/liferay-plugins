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

import com.liferay.documentlibrary.client.remote.data.Folder;

/**
 * @author Gail Hernandez
 */
public class FolderData {

	public FolderData() {
	}

	public FolderData(Folder folder) {
		setId(folder.getFolderId());
		setParentFolderId(folder.getParentFolderId());
		setTitle(folder.getName());
	}

	public long getId() {
		return _id;
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public String getTitle() {
		return _title;
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;
	}

	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private long _id;
	private long _parentFolderId;
	private long _repositoryId;
	private String _title;

}