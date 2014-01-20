/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sync.engine.filesystem;

import com.liferay.sync.engine.service.SyncWatchEventService;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Young
 */
public class SyncSiteWatchEventListener
	implements Runnable, WatchEventListener {

	@Override
	public void entryCreate(Path filePath, WatchEvent<Path> watchEvent) {
		addSyncWatchEvent(filePath, watchEvent);
	}

	@Override
	public void entryDelete(Path filePath, WatchEvent<Path> watchEvent) {
		addSyncWatchEvent(filePath, watchEvent);
	}

	@Override
	public void entryModify(Path filePath, WatchEvent<Path> watchEvent) {
		addSyncWatchEvent(filePath, watchEvent);
	}

	@Override
	public void overflow(Path filePath, WatchEvent<Path> watchEvent) {
		addSyncWatchEvent(filePath, watchEvent);
	}

	@Override
	public void run() {
	}

	protected void addSyncWatchEvent(
		Path filePath, WatchEvent<Path> watchEvent) {

		try {
			SyncWatchEventService.addSyncWatchEvent(
				filePath.toString(), watchEvent.kind().name());
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncWatchEventService.class);

}