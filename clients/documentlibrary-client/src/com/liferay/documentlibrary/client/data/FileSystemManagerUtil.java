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
public class FileSystemManagerUtil {

	public static void addFile(FileData fileData) {
		getFileSystemManager().addFile(fileData);
	}

	public static void addFolder(FolderData folderData) {
		getFileSystemManager().addFolder(folderData);
	}

	public static boolean containsFile(long fileId) {
		return getFileSystemManager().containsFile(fileId);
	}

	public static boolean containsFolder(long folderId) {
		return getFileSystemManager().containsFolder(folderId);
	}

	public static void deleteFolder(FolderData folderData) {
		getFileSystemManager().deleteFolder(folderData);
	}

	public static FileData getFileData(long fileId) {
		return getFileSystemManager().getFileData(fileId);
	}

	public static FileData getFileData(String fullName) {
		return getFileSystemManager().getFileData(fullName);
	}

	public static FileSystemManager getFileSystemManager() {
		return _fileSystemManager;
	}

	public static FolderData getFolderData(long folderId) {
		return getFileSystemManager().getFolderData(folderId);
	}

	public static FolderData getFolderData(String fullName) {
		return getFileSystemManager().getFolderData(fullName);
	}

	public static String getFullFileName(long fileId) {
		return getFileSystemManager().getFullFileName(fileId);
	}

	public static String getFullFolderName(long folderId) {
		return getFileSystemManager().getFullFolderName(folderId);
	}

	public static boolean isDirectory(String fullName) {
		return getFileSystemManager().isDirectory(fullName);
	}

	public void setFileFolderDataManager(
		FileSystemManager fileFolderDataManager) {

		_fileSystemManager = fileFolderDataManager;
	}

	private static FileSystemManager _fileSystemManager;

}