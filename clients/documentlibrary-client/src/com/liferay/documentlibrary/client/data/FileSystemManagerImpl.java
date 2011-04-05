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

import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gail Hernandez
 */
public class FileSystemManagerImpl implements FileSystemManager {

	public FileSystemManagerImpl() {
		_files = new HashMap<Long, FileData>();
		_folders = new HashMap<Long, FolderData>();
	}

	public void addFile(FileData fileData) {
		_files.put(fileData.getId(), fileData);
	}

	public void addFolder(FolderData folderData) {
		_folders.put(folderData.getId(), folderData);
	}

	public boolean containsFile(long fileId) {
		return _files.containsKey(fileId);
	}

	public boolean containsFolder(long folderId) {
		return _folders.containsKey(folderId);
	}

	public void deleteFolder(FolderData folderData) {
		_folders.remove(folderData.getId());
	}

	public FileData getFileData(long fileId) {
		if (containsFile(fileId)) {
			return _files.get(fileId);
		}
		else {
			return null;
		}
	}

	public FileData getFileData(String fullName) {
		for (long fileId : _files.keySet()) {
			FileData fileData = _files.get(fileId);

			StringBundler sb = new StringBundler(3);

			sb.append(AppPropsValues.ROOT_FOLDER);
			sb.append(File.separator);
			sb.append(getFullFileName(fileData));

			String name = sb.toString();

			if (fullName.equals(name)) {
				return fileData;
			}
		}

		return null;
	}

	public FolderData getFolderData(long folderId) {
		if (containsFolder(folderId)) {
			return _folders.get(folderId);
		}
		else {
			return null;
		}
	}

	public FolderData getFolderData(String fullName) {
		for (long folderId : _folders.keySet()) {
			FolderData folderData = _folders.get(folderId);

			StringBundler sb = new StringBundler(3);

			sb.append(AppPropsValues.ROOT_FOLDER);
			sb.append(File.separator);
			sb.append(getFullFolderName(folderData));

			String name = sb.toString();

			if (fullName.equals(name)) {
				return folderData;
			}
		}

		return null;
	}

	public String getFullFileName(FileData fileData) {
		if (fileData == null)
			return null;

		StringBundler sb = new StringBundler(3);

		sb.append(getFullFolderName(fileData.getParentFolderId()));
		sb.append(File.separator);
		sb.append(fileData.getTitle());

		String fullFileName = sb.toString();

		return fullFileName;
	}

	public String getFullFileName(long fileId) {
		FileData file = getFileData(fileId);

		return getFullFileName(file);
	}

	public String getFullFolderName(FolderData folderData) {
		if (folderData == null)
			return null;

		String fullName = StringPool.BLANK;

		StringBundler sb = new StringBundler(3);

		if (folderData.getParentFolderId() > 0) {
			sb.append(getFullFolderName(folderData.getParentFolderId()));
			sb.append(File.separator);
		}

		sb.append(folderData.getTitle());

		return sb.toString();
	}

	public String getFullFolderName(long folderId) {
		FolderData folder = getFolderData(folderId);

		return getFullFolderName(folder);
	}

	public boolean isDirectory(String fullName) {
		if (getFolderData(fullName) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	private Map<Long, FileData> _files;
	private Map<Long, FolderData> _folders;

}