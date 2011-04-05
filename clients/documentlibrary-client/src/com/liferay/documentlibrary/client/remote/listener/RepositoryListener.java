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

import com.liferay.documentlibrary.client.monitor.RemoteFileSystemMonitor;
import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.File;

import java.util.List;

/**
 * @author Gail Hernandez
 */
public class RepositoryListener {

	public RepositoryListener(
		RemoteFileSystemMonitor remoteFileSystemMonitor) {

		_remoteFileSystemMonitor = remoteFileSystemMonitor;
	}

	public void handleRepositories(List<String> repositoryIds) {
		for (String repositoryId : repositoryIds) {
			StringBundler sb = new StringBundler(3);

			sb.append(AppPropsValues.ROOT_FOLDER);
			sb.append(File.separator);
			sb.append("Repository-");
			sb.append(repositoryId);

			File file = new File(sb.toString());

			if (!file.exists()) {
				file.mkdir();
			}

			_remoteFileSystemMonitor.startMonitor();
		}
	}

	private RemoteFileSystemMonitor _remoteFileSystemMonitor;

}