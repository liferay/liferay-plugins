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

import com.liferay.documentlibrary.client.data.FileSystemManagerUtil;
import com.liferay.documentlibrary.client.data.FolderData;
import com.liferay.documentlibrary.client.event.FileSystemEventManagerUtil;
import com.liferay.documentlibrary.client.local.ChangeType;
import com.liferay.documentlibrary.client.remote.data.Folder;
import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.File;

import java.util.Map.Entry;
import java.util.Map;

/**
 * @author Gail Hernandez
 */
public class BootstrapFolderListener implements FolderListener {

	public void handleFolders(Map<Long, Folder> folders) {
		for (Entry<Long, Folder> folder : folders.entrySet()) {
			Folder newFolder = folder.getValue();
			FolderData folderData = new FolderData(newFolder);

			Long folderId = folder.getKey();

			if (FileSystemManagerUtil.containsFolder(folderId)) {
				FolderData oldFolder = FileSystemManagerUtil.getFolderData(
					folderId);

				String oldFolderName = oldFolder.getTitle();

				String newFolderName = newFolder.getName();

				if (!oldFolderName.equals(newFolderName) ||
					(oldFolder.getParentFolderId() !=
						newFolder.getParentFolderId())) {

					_removeFolder(oldFolder);
					_addFolder(folderData);
				}
			}
			else {
				FileSystemManagerUtil.addFolder(folderData);

				_addFolder(folderData);
			}
		}
	}

	private void _addFolder(FolderData newFolder) {
		StringBundler sb = new StringBundler(3);

		sb.append(AppPropsValues.ROOT_FOLDER);
		sb.append(File.separator);
		sb.append(FileSystemManagerUtil.getFullFolderName(newFolder.getId()));

		File file = new File(sb.toString());

		if (!file.exists()) {
			if (_log.isInfoEnabled()) {
				_log.info("Creating directory " + file.getAbsolutePath());
			}

			FileSystemEventManagerUtil.addLocalFolderEvent(
				newFolder.getId(), ChangeType.ADDED);

			file.mkdir();
		}

	}

	private void _removeFolder(FolderData oldFolder) {
		StringBundler sb = new StringBundler(3);

		sb.append(AppPropsValues.ROOT_FOLDER);
		sb.append(File.separator);
		sb.append(FileSystemManagerUtil.getFullFolderName(oldFolder.getId()));

		File file = new File(sb.toString());

		if (file.exists()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Deleting directory " + file.getAbsolutePath());
			}

			file.delete();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BootstrapFolderListener.class.getName());

}