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

package com.liferay.documentlibrary.client.remote;

import com.liferay.documentlibrary.client.data.FileData;
import com.liferay.documentlibrary.client.data.FolderData;
import com.liferay.documentlibrary.client.remote.data.Folder;
import com.liferay.documentlibrary.client.remote.listener.FileEntryListener;
import com.liferay.documentlibrary.client.remote.listener.FolderListener;
import com.liferay.documentlibrary.client.remote.listener.RepositoryListener;

/**
 * @author Gail Hernandez
 */
public interface RemoteFileSystem {

	public void addFolder(FolderData folderData);

	public void bootstrapFiles(
		Folder parentFolder, FileEntryListener fileEntryListener);

	public void bootstrapFolders(
		Folder parentFolder, FolderListener folderListener);

	public void deleteFolder(FolderData folderData);

	public void getFileContent(FileData newFile);

	public void syncRepositories(RepositoryListener repositoryListener);

	public void updateFolder(FolderData folderData);

}