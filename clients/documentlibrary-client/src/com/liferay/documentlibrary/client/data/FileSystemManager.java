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

/**
 * @author Gail Hernandez
 */
public interface FileSystemManager {

	public void addFile(FileData fileData);

	public void addFolder(FolderData folderData);

	public boolean containsFile(long fileId);

	public boolean containsFolder(long folderId);

	public void deleteFolder(FolderData folderData);

	public FileData getFileData(long fileId);

	public FileData getFileData(String fullName);

	public FolderData getFolderData(long folderId);

	public FolderData getFolderData(String fullName);

	public String getFullFileName(long fileId);

	public String getFullFolderName(long folderId);

	public boolean isDirectory(String fullName);

}