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

package com.liferay.documentlibrary.client.desktop;

import com.liferay.documentlibrary.client.data.FileSystemManager;
import com.liferay.documentlibrary.client.data.FileSystemManagerImpl;
import com.liferay.documentlibrary.client.data.FileSystemManagerUtil;
import com.liferay.documentlibrary.client.event.FileSystemEventManager;
import com.liferay.documentlibrary.client.event.FileSystemEventManagerImpl;
import com.liferay.documentlibrary.client.event.FileSystemEventManagerUtil;
import com.liferay.documentlibrary.client.monitor.LocalFileSystemMonitor;
import com.liferay.documentlibrary.client.monitor.RemoteFileSystemMonitor;
import com.liferay.documentlibrary.client.remote.RemoteFileSystem;
import com.liferay.documentlibrary.client.remote.RemoteFileSystemImpl;
import com.liferay.documentlibrary.client.remote.RemoteFileSystemUtil;
import com.liferay.documentlibrary.client.remote.listener.RepositoryListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Gail Hernandez
 */
public class DesktopClient {

	public DesktopClient() {
		_remoteFileSystemMonitor = new RemoteFileSystemMonitor();
	}

	public void startSync() {
		RepositoryListener repositoryListener = new RepositoryListener(
			_remoteFileSystemMonitor);

		RemoteFileSystemUtil.syncRepositories(repositoryListener);

		LocalFileSystemMonitor localFileSystemMonitor =
			new LocalFileSystemMonitor();

		localFileSystemMonitor.startMonitoring();
	}

	public static void main(String[] args) {
		try {
			_injectDependencies();

			DesktopClient desktopClient = new DesktopClient();

			desktopClient.startSync();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static void _injectDependencies() {
		FileSystemManager fileFolderDataManager =
			new FileSystemManagerImpl();

		FileSystemManagerUtil fileFolderDataManagerUtil =
			new FileSystemManagerUtil();

		fileFolderDataManagerUtil.setFileFolderDataManager(
			fileFolderDataManager);

		RemoteFileSystem remoteFileSystem =
			new RemoteFileSystemImpl();

		RemoteFileSystemUtil remoteFileSystemUtil =
			new RemoteFileSystemUtil();

		remoteFileSystemUtil.setRemoteFileSystem(remoteFileSystem);

		FileSystemEventManager fileSystemEventManager =
			new FileSystemEventManagerImpl();

		FileSystemEventManagerUtil fileSystemEventManagerUtil =
			new FileSystemEventManagerUtil();

		fileSystemEventManagerUtil.setFileSystemEventManager(
			fileSystemEventManager);
	}

	private static Log _log = LogFactoryUtil.getLog(
		DesktopClient.class.getName());

	private RemoteFileSystemMonitor _remoteFileSystemMonitor;

}