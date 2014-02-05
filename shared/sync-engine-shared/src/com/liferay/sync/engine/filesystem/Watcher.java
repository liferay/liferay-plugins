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

import java.io.IOException;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

	public void close() throws IOException {
		_watchService.close();
	}

	public void processEvents() {
		_executorService.submit(this);
	}

	@Override
	public void run() {
		while (true) {
			WatchKey watchKey = null;

			try {
				watchKey = _watchService.take();
			}
			catch (Exception e) {
				return;
			}

			Path parentFilePath = _filePaths.get(watchKey);

			if (parentFilePath == null) {
				continue;
			}

			List<WatchEvent<?>> watchEvents = watchKey.pollEvents();

			for (int i = 0; i < watchEvents.size(); i++) {
				WatchEvent<Path> watchEvent = (WatchEvent<Path>)watchEvents.get(
					i);

				Path childFilePath = parentFilePath.resolve(
					watchEvent.context());

				childFilePath = childFilePath.normalize();

				fireWatchEventListener(childFilePath, watchEvent);

				WatchEvent.Kind<?> kind = watchEvent.kind();

				if (_recursive &&
					(kind == StandardWatchEventKinds.ENTRY_CREATE)) {

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

				fireWatchEventListener(
					SyncWatchEvent.EVENT_TYPE_DELETE, filePath);

				if (_filePaths.isEmpty()) {
					break;
				}
			}
		}
	}

	protected void fireWatchEventListener(
		Path filePath, WatchEvent<Path> watchEvent) {

		WatchEvent.Kind<?> kind = watchEvent.kind();

		fireWatchEventListener(kind.name(), filePath);
	}

	protected void fireWatchEventListener(String eventType, Path filePath) {
		_watchEventListener.watchEvent(eventType, filePath);
	}

	protected void register(Path filePath, boolean recursive)
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

						register(filePath, false);

						return FileVisitResult.CONTINUE;
					}

				}
			);
		}
		else {
			WatchKey watchKey = filePath.register(
				_watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

			_filePaths.put(watchKey, filePath);

			fireWatchEventListener(SyncWatchEvent.EVENT_TYPE_CREATE, filePath);

			if (_logger.isTraceEnabled()) {
				_logger.trace("Registered file path {}", filePath);
			}
		}
	}

	private static Logger _logger = LoggerFactory.getLogger(Watcher.class);

	private ExecutorService _executorService =
		Executors.newSingleThreadExecutor();
	private Map<WatchKey, Path> _filePaths = new HashMap<WatchKey, Path>();
	private boolean _recursive;
	private WatchEventListener _watchEventListener;
	private WatchService _watchService;

}