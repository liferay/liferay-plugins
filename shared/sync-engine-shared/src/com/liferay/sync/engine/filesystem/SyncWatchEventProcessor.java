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

import com.liferay.sync.engine.model.SyncWatchEvent;
import com.liferay.sync.engine.service.SyncWatchEventService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Young
 */
public class SyncWatchEventProcessor implements Runnable {

	public void process() {
		_scheduledExecutorService.scheduleAtFixedRate(
			this, 0, 3, TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		if (_logger.isTraceEnabled()) {
			_logger.trace("Processing sync watch events");
		}

		List<SyncWatchEvent> syncWatchEvents = SyncWatchEventService.findAll();

		for (SyncWatchEvent syncWatchEvent : syncWatchEvents) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(
					"Event file path {} file type {} kind name {} timestamp {}",
					syncWatchEvent.getFilePath(), syncWatchEvent.getFileType(),
					syncWatchEvent.getKindName(),
					syncWatchEvent.getTimestamp());
			}

			SyncWatchEventService.deleteSyncWatchEvent(
				syncWatchEvent.getSyncWatchEventId());
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(
		SyncWatchEventProcessor.class);

	private ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

}