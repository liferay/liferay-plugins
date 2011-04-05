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

package com.liferay.documentlibrary.client.event;

import com.liferay.documentlibrary.client.local.ChangeType;

/**
 * @author Gail Hernandez
 */
public class FileSystemEventManagerUtil {

	public static FileSystemEventManager getFileSystemEventManager() {
		return _fileSystemEventManager;
	}

	public static void addLocalFileEvent(long id, ChangeType changeType) {
		getFileSystemEventManager().addLocalFileEvent(id, changeType);
	}

	public static void addLocalFolderEvent(long id, ChangeType changeType) {
		getFileSystemEventManager().addLocalFolderEvent(id, changeType);
	}

	public static boolean isLocalFolderEvent(long id, ChangeType changeType) {
		return getFileSystemEventManager().isLocalFolderEvent(id, changeType);
	}

	public static boolean isLocalFileEvent(long id, ChangeType changeType) {
		return getFileSystemEventManager().isLocalFileEvent(id, changeType);
	}

	public void setFileSystemEventManager(
		FileSystemEventManager fileSystemEventManager) {

		_fileSystemEventManager = fileSystemEventManager;
	}

	private static FileSystemEventManager _fileSystemEventManager;

}