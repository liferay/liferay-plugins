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

import com.liferay.documentlibrary.client.local.LocalFileSystemListener;
import com.liferay.documentlibrary.client.util.AppPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;

/**
 * @author Gail Hernandez
 */
public class LocalFileSystemMonitor {

	public void startMonitoring() {
		int mask = JNotify.FILE_CREATED | JNotify.FILE_DELETED |
			JNotify.FILE_MODIFIED | JNotify.FILE_RENAMED;

		stopMonitoring();

		if (_log.isInfoEnabled()) {
			_log.info("Syncing server with root " + AppPropsValues.ROOT_FOLDER);
		}

		LocalFileSystemListener folderListener = new LocalFileSystemListener();

		try {
			_watchId = JNotify.addWatch(
				AppPropsValues.ROOT_FOLDER, mask, true, folderListener);

			if (_log.isInfoEnabled()) {
				_log.info("Starting to monitor " + AppPropsValues.ROOT_FOLDER);
			}
		}
		catch (JNotifyException e) {
			_log.error(e,e);
		}
	}

	public void stopMonitoring() {
		try {
			JNotify.removeWatch(_watchId);
		}
		catch (JNotifyException e) {
			_log.error(e,e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LocalFileSystemMonitor.class.getName());

	private int _watchId;

}