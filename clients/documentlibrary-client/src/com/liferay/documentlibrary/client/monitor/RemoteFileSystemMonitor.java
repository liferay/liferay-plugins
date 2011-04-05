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

package com.liferay.documentlibrary.client.monitor;

import com.liferay.documentlibrary.client.remote.RemoteFileSystemUtil;
import com.liferay.documentlibrary.client.remote.data.Folder;
import com.liferay.documentlibrary.client.remote.listener.BootstrapFolderListener;
import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Gail Hernandez
 */
public class RemoteFileSystemMonitor {

	public void startMonitor() {
		if (_log.isInfoEnabled()) {
			_log.info("Starting to sync server with root " +
				AppPropsValues.ROOT_FOLDER);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Starting initial sync");
		}

		Folder folderData = new Folder();

		folderData.setFolderId(0);

		RemoteFileSystemUtil.bootstrapFolders(
			folderData, new BootstrapFolderListener());
	}

	private static Log _log = LogFactoryUtil.getLog(
		RemoteFileSystemMonitor.class.getName());

}