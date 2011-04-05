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
public class RemoteFileSystemUtil {

	public static void addFolder(FolderData folderData) {
		getRemoteFileSystem().addFolder(folderData);
	}

	public static void bootstrapFiles(
		Folder parentFolder, FileEntryListener fileEntryListener) {

		getRemoteFileSystem().bootstrapFiles(parentFolder, fileEntryListener);
	}

	public static void bootstrapFolders(
		Folder parentFolder, FolderListener folderListener) {

		getRemoteFileSystem().bootstrapFolders(parentFolder, folderListener);
	}

	public static void updateFolder(FolderData folderData) {
		getRemoteFileSystem().updateFolder(folderData);
	}

	public static void deleteFolder(FolderData folderData) {
		getRemoteFileSystem().deleteFolder(folderData);
	}

	public static void getFile(FileData newFile) {
		getRemoteFileSystem().getFileContent(newFile);
	}

	public static RemoteFileSystem getRemoteFileSystem() {
		return _remoteFileSystem;
	}

	public void setRemoteFileSystem(RemoteFileSystem remoteFileSystem) {
		_remoteFileSystem = remoteFileSystem;
	}

	public static void syncRepositories(RepositoryListener repositoryListener) {
		getRemoteFileSystem().syncRepositories(repositoryListener);
	}

	private static RemoteFileSystem _remoteFileSystem;

}