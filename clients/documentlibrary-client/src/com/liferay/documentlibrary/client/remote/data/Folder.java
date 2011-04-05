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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import org.json.JSONObject;

/**
 * @author Gail Hernandez
 */
public class Folder {

	public Folder() {
	}

	public Folder(JSONObject folder) {
		if (folder == null) {
			return;
		}

		_parentFolderId = folder.optLong("parentFolderId");
		_folderId = folder.optLong("folderId");
		_name = folder.optString("name");
	}

	public long getFolderId() {
		return _folderId;
	}

	public String getFullName() {
		String fullName = StringPool.BLANK;

		if (getParentFolderId() > 0 && getParentFolder() != null) {
			fullName = getParentFolder().getFullName();
		}

		StringBundler sb = new StringBundler(3);

		sb.append(fullName);
		sb.append(File.separator);
		sb.append(getName());

		return sb.toString();
	}

	public String getName() {
		return _name;
	}

	public Folder getParentFolder() {
		return _parentFolder;
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setParentFolder(Folder parentFolder) {
		_parentFolder = parentFolder;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;
	}

	private long _folderId;
	private String _name;
	private Folder _parentFolder;
	private long _parentFolderId;

}