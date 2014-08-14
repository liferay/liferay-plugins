/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.sync.engine;

import com.liferay.sync.engine.documentlibrary.event.DownloadFileEvent;
import com.liferay.sync.engine.documentlibrary.event.GetSyncDLObjectUpdateEvent;
import com.liferay.sync.engine.filesystem.SyncSiteWatchEventListener;
import com.liferay.sync.engine.filesystem.SyncWatchEventProcessor;
import com.liferay.sync.engine.filesystem.WatchEventListener;
import com.liferay.sync.engine.filesystem.Watcher;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.model.SyncWatchEvent;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncPropService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.service.SyncWatchEventService;
import com.liferay.sync.engine.upgrade.util.UpgradeUtil;
import com.liferay.sync.engine.util.LoggerUtil;
import com.liferay.sync.engine.util.PropsValues;
import com.liferay.sync.engine.util.SyncClientUpdater;
import com.liferay.sync.engine.util.SyncEngineUtil;

import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncEngine {

	public synchronized static void cancelSyncAccountTasks(long syncAccountId)
		throws Exception {

		if (!_running) {
			return;
		}

		Object[] syncAccountTasks = _syncAccountTasks.get(syncAccountId);

		if (syncAccountTasks == null) {
			return;
		}

		Watcher watcher = (Watcher)syncAccountTasks[0];

		watcher.close();

		ScheduledFuture<?> scheduledFuture =
			(ScheduledFuture<?>)syncAccountTasks[1];

		scheduledFuture.cancel(false);
	}

	public synchronized static boolean isRunning() {
		return _running;
	}

	public synchronized static void scheduleSyncAccountTasks(
		final long syncAccountId) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					doScheduleSyncAccountTasks(syncAccountId);
				}
				catch (Exception e) {
					_logger.error(e.getMessage(), e);
				}
			}

		};

		_executorService.execute(runnable);
	}

	public synchronized static void start() {
		if (_running) {
			return;
		}

		try {
			doStart();
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
	}

	public synchronized static void stop() {
		if (!_running) {
			return;
		}

		try {
			doStop();
		}
		catch (Exception e) {
			_logger.error(e.getMessage(), e);
		}
	}

	protected static void doScheduleSyncAccountTasks(long syncAccountId)
		throws Exception {

		if (!_running) {
			return;
		}

		SyncSiteService.synchronizeSyncSites(syncAccountId);

		SyncWatchEventService.deleteSyncWatchEvents(syncAccountId);

		SyncAccount syncAccount = SyncAccountService.synchronizeSyncAccount(
			syncAccountId);

		Path filePath = Paths.get(syncAccount.getFilePathName());

		WatchEventListener watchEventListener = new SyncSiteWatchEventListener(
			syncAccountId);

		synchronizeSyncFiles(filePath, syncAccountId, watchEventListener);

		Watcher watcher = new Watcher(filePath, true, watchEventListener);

		_executorService.execute(watcher);

		scheduleGetSyncDLObjectUpdateEvent(syncAccount, watcher);
	}

	protected static void doStart() throws Exception {
		_running = true;

		SyncEngineUtil.fireSyncEngineStateChanged(
			SyncEngineUtil.SYNC_ENGINE_STATE_STARTING);

		LoggerUtil.initLogger();

		_logger.info("Starting {}", PropsValues.SYNC_PRODUCT_NAME);

		UpgradeUtil.upgrade();

		SyncClientUpdater.scheduleAutoUpdateChecker(
			SyncPropService.getInteger("updateCheckInterval", 1440));

		SyncWatchEventProcessor syncWatchEventProcessor =
			new SyncWatchEventProcessor();

		_syncWatchEventProcessorExecutorService =
			Executors.newSingleThreadScheduledExecutor();

		_syncWatchEventProcessorExecutorService.scheduleAtFixedRate(
			syncWatchEventProcessor, 0, 3, TimeUnit.SECONDS);

		List<SyncAccount> syncAccounts = SyncAccountService.findAll();

		if (syncAccounts.isEmpty()) {
			SyncEngineUtil.fireSyncEngineStateChanged(
				SyncEngineUtil.SYNC_ENGINE_NOT_CONFIGURED);
		}

		for (long activeSyncAccountId :
				SyncAccountService.getActiveSyncAccountIds()) {

			scheduleSyncAccountTasks(activeSyncAccountId);
		}

		SyncEngineUtil.fireSyncEngineStateChanged(
			SyncEngineUtil.SYNC_ENGINE_STATE_STARTED);
	}

	protected static void doStop() throws Exception {
		SyncEngineUtil.fireSyncEngineStateChanged(
			SyncEngineUtil.SYNC_ENGINE_STATE_STOPPING);

		_logger.info("Stopping {}", PropsValues.SYNC_PRODUCT_NAME);

		for (long syncAccountId : _syncAccountTasks.keySet()) {
			cancelSyncAccountTasks(syncAccountId);
		}

		_syncWatchEventProcessorExecutorService.shutdown();

		SyncClientUpdater.cancelAutoUpdateChecker();

		SyncEngineUtil.fireSyncEngineStateChanged(
			SyncEngineUtil.SYNC_ENGINE_STATE_STOPPED);

		_running = false;
	}

	protected static void fireDeleteEvents(
			Path filePath, final long syncAccountId,
			WatchEventListener watchEventListener)
		throws IOException {

		long startTime = System.currentTimeMillis();

		Files.walkFileTree(
			filePath,
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(
						Path filePath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					SyncFile syncFile = SyncFileService.fetchSyncFile(
						filePath.toString(), syncAccountId);

					if (syncFile != null) {
						syncFile.setLocalSyncTime(System.currentTimeMillis());

						SyncFileService.update(syncFile);
					}

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
					Path filePath, BasicFileAttributes basicFileAttributes) {

					SyncFile syncFile = SyncFileService.fetchSyncFile(
						filePath.toString(), syncAccountId);

					if (syncFile != null) {
						syncFile.setLocalSyncTime(System.currentTimeMillis());

						SyncFileService.update(syncFile);
					}

					return FileVisitResult.CONTINUE;
				}

			}
		);

		List<SyncFile> deletedSyncFiles = SyncFileService.findSyncFiles(
			filePath.toString(), startTime, syncAccountId);

		for (SyncFile deletedSyncFile : deletedSyncFiles) {
			if (deletedSyncFile.getUiEvent() ==
					SyncFile.UI_EVENT_FILE_NAME_TOO_LONG) {

				continue;
			}

			watchEventListener.watchEvent(
				SyncWatchEvent.EVENT_TYPE_DELETE,
				Paths.get(deletedSyncFile.getFilePathName()));
		}
	}

	protected static void retryFileTransfers(long syncAccountId) {
		List<SyncFile> downloadingSyncFiles = SyncFileService.findSyncFiles(
			syncAccountId, SyncFile.UI_EVENT_DOWNLOADING);

		for (SyncFile downloadingSyncFile : downloadingSyncFiles) {
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("patch", false);
			parameters.put("syncFile", downloadingSyncFile);

			DownloadFileEvent downloadFileEvent = new DownloadFileEvent(
				syncAccountId, parameters);

			downloadFileEvent.run();
		}

		List<SyncFile> uploadingSyncFiles = SyncFileService.findSyncFiles(
			syncAccountId, SyncFile.UI_EVENT_UPLOADING);

		for (SyncFile uploadingSyncFile : uploadingSyncFiles) {
			if (uploadingSyncFile.getTypePK() > 0) {

				// Reset the checksum and let the engine retry the upload

				uploadingSyncFile.setChecksum("");

				SyncFileService.update(uploadingSyncFile);
			}
			else {

				// If the file does not exist on the portal yet, delete the
				// database entry and let the engine recreate it.

				SyncFileService.deleteSyncFile(uploadingSyncFile, false);
			}
		}
	}

	protected static void scheduleGetSyncDLObjectUpdateEvent(
		final SyncAccount syncAccount, Watcher watcher) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				SyncAccount updatedSyncAccount =
					SyncAccountService.fetchSyncAccount(
						syncAccount.getSyncAccountId());

				if (updatedSyncAccount.getState() !=
						SyncAccount.STATE_CONNECTED) {

					return;
				}

				Set<Long> syncSiteIds = SyncSiteService.getActiveSyncSiteIds(
					syncAccount.getSyncAccountId());

				for (long syncSiteId : syncSiteIds) {
					SyncSite syncSite = SyncSiteService.fetchSyncSite(
						syncSiteId);

					Map<String, Object> parameters =
						new HashMap<String, Object>();

					parameters.put("companyId", syncSite.getCompanyId());
					parameters.put("repositoryId", syncSite.getGroupId());
					parameters.put("syncSite", syncSite);

					GetSyncDLObjectUpdateEvent getSyncDLObjectUpdateEvent =
						new GetSyncDLObjectUpdateEvent(
							syncAccount.getSyncAccountId(), parameters);

					getSyncDLObjectUpdateEvent.run();
				}
			}

		};

		ScheduledFuture<?> scheduledFuture =
			_eventScheduledExecutorService.scheduleAtFixedRate(
				runnable, 0, syncAccount.getPollInterval(), TimeUnit.SECONDS);

		_syncAccountTasks.put(
			syncAccount.getSyncAccountId(),
			new Object[] {watcher, scheduledFuture});
	}

	protected static void synchronizeSyncFiles(
			Path filePath, long syncAccountId,
			WatchEventListener watchEventListener)
		throws IOException {

		fireDeleteEvents(filePath, syncAccountId, watchEventListener);

		retryFileTransfers(syncAccountId);
	}

	private static Logger _logger = LoggerFactory.getLogger(SyncEngine.class);

	private static ScheduledExecutorService _eventScheduledExecutorService =
		Executors.newScheduledThreadPool(5);
	private static ExecutorService _executorService =
		Executors.newCachedThreadPool();
	private static boolean _running;
	private static Map<Long, Object[]> _syncAccountTasks =
		new HashMap<Long, Object[]>();
	private static ScheduledExecutorService
		_syncWatchEventProcessorExecutorService;

}