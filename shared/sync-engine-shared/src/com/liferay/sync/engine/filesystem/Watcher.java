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

package com.liferay.sync.engine.filesystem;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.model.SyncWatchEvent;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.util.FilePathNameUtil;

import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.pachler.nio.file.FileSystem;
import name.pachler.nio.file.FileSystems;
import name.pachler.nio.file.Paths;
import name.pachler.nio.file.StandardWatchEventKind;
import name.pachler.nio.file.WatchEvent;
import name.pachler.nio.file.WatchKey;
import name.pachler.nio.file.WatchService;
import name.pachler.nio.file.ext.ExtendedWatchEventKind;
import name.pachler.nio.file.impl.PathImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Young
 */
public class Watcher implements Runnable {

	public Watcher(
			Path filePath, boolean recursive,
			WatchEventListener watchEventListener)
		throws IOException {

		_recursive = recursive;
		_watchEventListener = watchEventListener;

		FileSystem fileSystem = FileSystems.getDefault();

		_watchService = fileSystem.newWatchService();

		register(filePath, recursive);
	}

	public void close() {
		try {
			_watchService.close();
		}
		catch (Exception e) {
			_watchService = null;
		}
	}

	@Override
	public void run() {
		while (true) {
			if (_watchService == null) {
				break;
			}

			WatchKey watchKey = null;

			try {
				watchKey = _watchService.take();
			}
			catch (Exception e) {
				if (e instanceof ConcurrentModificationException) {
					continue;
				}

				break;
			}

			Path parentFilePath = _filePaths.get(watchKey);

			if (parentFilePath == null) {
				continue;
			}

			List<WatchEvent<?>> watchEvents = watchKey.pollEvents();

			for (int i = 0; i < watchEvents.size(); i++) {
				WatchEvent<Path> watchEvent = (WatchEvent<Path>)watchEvents.get(
					i);

				PathImpl pathImpl = (PathImpl)watchEvent.context();

				if (pathImpl == null) {
					continue;
				}

				Path childFilePath = parentFilePath.resolve(
					pathImpl.toString());

				fireWatchEventListener(childFilePath, watchEvent);

				WatchEvent.Kind<?> kind = watchEvent.kind();

				if (_recursive &&
					(kind == StandardWatchEventKind.ENTRY_CREATE)) {

					try {
						if (Files.isDirectory(
								childFilePath, LinkOption.NOFOLLOW_LINKS)) {

							register(childFilePath, true);
						}
					}
					catch (IOException ioe) {
					}
				}
			}

			if (!watchKey.reset()) {
				Path filePath = _filePaths.remove(watchKey);

				if (_logger.isTraceEnabled()) {
					_logger.trace("Unregistered file path {}", filePath);
				}

				processMissingFilePath(filePath);

				if (_filePaths.isEmpty()) {
					break;
				}
			}
		}
	}

	protected void doRegister(Path filePath, boolean recursive)
		throws IOException {

		if (recursive) {
			Files.walkFileTree(
				filePath,
				new SimpleFileVisitor<Path>() {

					@Override
					public FileVisitResult preVisitDirectory(
							Path filePath,
							BasicFileAttributes basicFileAttributes)
						throws IOException {

						doRegister(filePath, false);

						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFile(
						Path filePath,
						BasicFileAttributes basicFileAttributes) {

						if (Files.exists(filePath)) {
							fireWatchEventListener(
								SyncWatchEvent.EVENT_TYPE_CREATE, filePath);
						}

						return FileVisitResult.CONTINUE;
					}

				}
			);
		}
		else {
			name.pachler.nio.file.Path jpathwatchFilePath = Paths.get(
				filePath.toString());

			WatchKey watchKey = jpathwatchFilePath.register(
				_watchService, ExtendedWatchEventKind.KEY_INVALID,
				StandardWatchEventKind.ENTRY_CREATE,
				StandardWatchEventKind.ENTRY_DELETE,
				StandardWatchEventKind.ENTRY_MODIFY);

			_filePaths.put(watchKey, filePath);

			fireWatchEventListener(SyncWatchEvent.EVENT_TYPE_CREATE, filePath);

			if (_logger.isTraceEnabled()) {
				_logger.trace("Registered file path {}", filePath);
			}
		}
	}

	protected void fireWatchEventListener(
		Path filePath, WatchEvent<Path> watchEvent) {

		WatchEvent.Kind<?> kind = watchEvent.kind();

		fireWatchEventListener(kind.name(), filePath);
	}

	protected void fireWatchEventListener(String eventType, Path filePath) {
		SyncFile syncFile = SyncFileService.fetchSyncFile(
			FilePathNameUtil.getFilePathName(filePath),
			_watchEventListener.getSyncAccountId());

		if (syncFile != null) {
			syncFile.setLocalSyncTime(System.currentTimeMillis());

			SyncFileService.update(syncFile);

			if (syncFile.getState() == SyncFile.STATE_ERROR) {
				return;
			}
		}

		_watchEventListener.watchEvent(eventType, filePath);
	}

	protected void processMissingFilePath(Path filePath) {
		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			_watchEventListener.getSyncAccountId());

		String filePathName = FilePathNameUtil.getFilePathName(filePath);

		if (filePathName.equals(syncAccount.getFilePathName())) {
			syncAccount.setActive(false);
			syncAccount.setUiEvent(
				SyncAccount.UI_EVENT_SYNC_ACCOUNT_FOLDER_MISSING);

			SyncAccountService.update(syncAccount);
		}
		else {
			SyncSite syncSite = SyncSiteService.fetchSyncSite(
				syncAccount.getFilePathName(), syncAccount.getSyncAccountId());

			if (syncSite != null) {
				syncSite.setActive(false);
				syncSite.setUiEvent(SyncSite.UI_EVENT_SYNC_SITE_FOLDER_MISSING);

				SyncSiteService.update(syncSite);
			}
			else {
				fireWatchEventListener(
					SyncWatchEvent.EVENT_TYPE_DELETE, filePath);
			}
		}
	}

	protected void register(Path filePath, boolean recursive)
		throws IOException {

		if (Files.notExists(filePath)) {
			processMissingFilePath(filePath);

			return;
		}

		long startTime = System.currentTimeMillis();

		doRegister(filePath, recursive);

		List<SyncFile> syncFiles = SyncFileService.findSyncFiles(
			FilePathNameUtil.getFilePathName(filePath), startTime,
			_watchEventListener.getSyncAccountId());

		for (SyncFile syncFile : syncFiles) {
			fireWatchEventListener(
				SyncWatchEvent.EVENT_TYPE_DELETE,
				java.nio.file.Paths.get(syncFile.getFilePathName()));
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(Watcher.class);

	private Map<WatchKey, Path> _filePaths = new HashMap<WatchKey, Path>();
	private boolean _recursive;
	private WatchEventListener _watchEventListener;
	private WatchService _watchService;

}