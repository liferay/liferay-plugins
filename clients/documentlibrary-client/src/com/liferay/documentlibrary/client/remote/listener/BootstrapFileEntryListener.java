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

package com.liferay.documentlibrary.client.remote.listener;

import com.liferay.documentlibrary.client.data.FileData;
import com.liferay.documentlibrary.client.data.FileSystemManagerUtil;
import com.liferay.documentlibrary.client.remote.RemoteFileSystemUtil;
import com.liferay.documentlibrary.client.remote.data.FileEntry;
import com.liferay.documentlibrary.client.remote.data.Folder;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map.Entry;
import java.util.Map;

/**
 * @author Gail Hernandez
 */
public class BootstrapFileEntryListener implements FileEntryListener {

	public void handleFiles(
		Folder parentFolder, Map<Long, FileEntry> fileEntries) {

		for (Entry<Long, FileEntry> fileEntry : fileEntries.entrySet()) {
			FileEntry newFile = fileEntry.getValue();

			Long fileEntryId = fileEntry.getKey();

			if (FileSystemManagerUtil.containsFile(fileEntryId)) {
				FileData originalFile = FileSystemManagerUtil.getFileData(
					fileEntryId);
			}
			else {
				FileData newFileData = new FileData(newFile);

				FileSystemManagerUtil.addFile(newFileData);
				RemoteFileSystemUtil.getFile(newFileData);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BootstrapFileEntryListener.class.getName());

}