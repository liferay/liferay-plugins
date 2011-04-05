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

package com.liferay.documentlibrary.client.local;

import com.liferay.documentlibrary.client.data.FileData;
import com.liferay.documentlibrary.client.data.FileSystemManagerUtil;
import com.liferay.documentlibrary.client.data.FolderData;
import com.liferay.documentlibrary.client.event.FileSystemEventManagerUtil;
import com.liferay.documentlibrary.client.remote.RemoteFileSystemUtil;
import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import net.contentobjects.jnotify.JNotifyListener;

/**
 * @author Gail Hernandez
 */
public class LocalFileSystemListener implements JNotifyListener {

	public void fileRenamed(
		int watchId, String rootPath, String oldName, String newName) {

		if (_isCacheFolder(oldName)) {
			return;
		}

		_update(ChangeType.RENAMED, rootPath, oldName, newName);
	}

	public void fileModified(int watchId, String rootPath, String name) {
		if (_isCacheFolder(name)) {
			return;
		}

		_update(ChangeType.MODIFIED, rootPath, name);
	}

	public void fileDeleted(int watchId, String rootPath, String name) {
		if (_isCacheFolder(name)) {
			return;
		}

		_delete(rootPath, name);
	}

	public void fileCreated(int watchId, String rootPath, String name) {
		if (_isCacheFolder(name)) {
			return;
		}

		_update(ChangeType.ADDED, rootPath, name);
	}

	private void _createLocalFile(String rootPath, String name) {
		if (_log.isInfoEnabled()) {
			_log.info("Created new file " + rootPath + StringPool.SPACE +
				name);
		}
	}

	private void _createLocalFolder(String rootPath, String name) {
		if (_log.isInfoEnabled()) {
			_log.info("Creating new folder " + rootPath +
				StringPool.SPACE + name);
		}

		FolderData folderData = new FolderData();

		folderData.setRepositoryId(
			GetterUtil.getLong(AppPropsValues.REPOSITORY_ID));
		folderData.setTitle(name);

		FileSystemManagerUtil.addFolder(folderData);

		RemoteFileSystemUtil.addFolder(folderData);
	}

	private void _delete(String rootPath, String name) {
		String fullName = _getFullName(rootPath, name);

		if (FileSystemManagerUtil.isDirectory(fullName)) {
			FolderData folderData = FileSystemManagerUtil.getFolderData(
				fullName);

			FileSystemManagerUtil.deleteFolder(folderData);

			RemoteFileSystemUtil.deleteFolder(folderData);
		}
	}

	private String _getFullName(String root, String name) {
		StringBundler sb = new StringBundler(3);

		sb.append(root);
		sb.append(File.separator);
		sb.append(name);

		return sb.toString();
	}

	private boolean _isCacheFolder(String name) {
		return name.contains(".documentlibrary.client.cache");
	}

	private boolean _isDirectory(String root, String name) {
		File file = new File(_getFullName(root, name));

		return file.isDirectory();
	}

	private void _renameFolder(
		String rootPath, String oldName, String newName) {

		String fullName = _getFullName(rootPath, oldName);

		FolderData folderData = FileSystemManagerUtil.getFolderData(fullName);
		folderData.setTitle(newName);

		RemoteFileSystemUtil.updateFolder(folderData);
	}

	private void _update(
		ChangeType changeType, String rootPath, String name) {

		if (_isDirectory(rootPath, name)) {
			_updateFolder(changeType, rootPath, name);
		}
		else {
			_updateFile(changeType, rootPath, name);
		}
	}

	private void _updateFile(
		ChangeType changeType, String rootPath,	String name) {

		FileData data = FileSystemManagerUtil.getFileData(
			_getFullName(rootPath, name));

		if (data == null) {
			_createLocalFile(rootPath, name);
		}
		else if (FileSystemEventManagerUtil.isLocalFileEvent(
			data.getId(), changeType)) {

			if (_log.isInfoEnabled()) {
				_log.info(
					"Ignoring file event " + rootPath +
					StringPool.SPACE + name);
			}
		}
		else {
			_updateLocalFile(changeType, rootPath, name);
		}
	}

	private void _update(
		ChangeType changeType, String rootPath, String oldName,
		String newName) {

		if (_isDirectory(rootPath, newName)) {
			_renameFolder(rootPath, oldName, newName);
		}
	}

	private void _updateFolder(
		ChangeType changeType, String rootPath,	String name) {

		if (changeType == ChangeType.MODIFIED) {
			return;
		}

		FolderData data = FileSystemManagerUtil.getFolderData(
			_getFullName(rootPath, name));

		if (data == null) {
			_createLocalFolder(rootPath, name);
		}
		else if (FileSystemEventManagerUtil.isLocalFolderEvent(
			data.getId(), changeType)) {

			if (_log.isInfoEnabled()) {
				_log.info("Ignoring folder event " + rootPath +
					StringPool.SPACE + name);
				}
			}
			else {
				_updateLocalFolder(changeType, data);
			}
	}

	private void _updateLocalFolder(
		ChangeType changeType, FolderData folderData) {

		if (_log.isInfoEnabled()) {
			_log.info(
				"Updated folder " + changeType + folderData.getTitle());
		}
	}

	private void _updateLocalFile(
		ChangeType changeType, String rootPath, String name) {

		if (_log.isInfoEnabled()) {
			_log.info("Updated file " + rootPath + StringPool.BLANK +
				name);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LocalFileSystemListener.class.getName());

}