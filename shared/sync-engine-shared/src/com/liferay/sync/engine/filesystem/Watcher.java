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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Michael Young
 */
public class Watcher implements Runnable {

	public Watcher(Path path, boolean recursive) throws IOException {
		_recursive = recursive;

		register(path, recursive);

		FileSystem fileSystem = FileSystems.getDefault();

		_watchService = fileSystem.newWatchService();
	}

	public void addWatchEventListener(WatchEventListener watchEventListener) {
		_watchEventListeners.add(watchEventListener);
	}

	public void close() throws IOException {
		_watchService.close();
	}

	public void fireWatchEventListeners(
		WatchEvent<Path> watchEvent, Path path) {

		WatchEvent.Kind kind = watchEvent.kind();

		for (WatchEventListener watchEventListener : _watchEventListeners) {
			if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
				watchEventListener.entryCreate(watchEvent, path);
			}
			else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
				watchEventListener.entryDelete(watchEvent, path);
			}
			else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
				watchEventListener.entryModify(watchEvent, path);
			}
			else if (kind == StandardWatchEventKinds.OVERFLOW) {
				watchEventListener.overflow(watchEvent, path);
			}
		}
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
			catch (InterruptedException ie) {
				return;
			}

			Path parentPath = _paths.get(watchKey);

			if (parentPath == null) {
				continue;
			}

			List<WatchEvent<?>> watchEvents = watchKey.pollEvents();

			for (int i = 0; i < watchEvents.size(); i++) {
				WatchEvent<Path> watchEvent = (WatchEvent<Path>)watchEvents.get(
					i);

				Path childPath = parentPath.resolve(watchEvent.context());

				fireWatchEventListeners(watchEvent, childPath);

				WatchEvent.Kind kind = watchEvent.kind();

				if (_recursive &&
					(kind == StandardWatchEventKinds.ENTRY_CREATE)) {

					try {
						if (Files.isDirectory(
								childPath, LinkOption.NOFOLLOW_LINKS)) {

							register(childPath, true);
						}
					}
					catch (IOException ioe) {
					}
				}
			}

			if (!watchKey.reset()) {
				_paths.remove(watchKey);

				if (_paths.isEmpty()) {
					break;
				}
			}
		}
	}

	protected void register(Path path, boolean recursive) throws IOException {
		if (recursive) {
			Files.walkFileTree(
				path,
				new SimpleFileVisitor<Path>() {

					@Override
					public FileVisitResult preVisitDirectory(
							Path path, BasicFileAttributes basicFileAttributes)
						throws IOException {

						register(path, false);

						return FileVisitResult.CONTINUE;
					}

				}
			);
		}
		else {
			WatchKey watchKey = path.register(
				_watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

			_paths.put(watchKey, path);
		}
	}

	private ExecutorService _executorService =
		Executors.newSingleThreadExecutor();
	private Map<WatchKey, Path> _paths = new HashMap<WatchKey, Path>();
	private boolean _recursive;
	private List<WatchEventListener> _watchEventListeners =
		new ArrayList<WatchEventListener>();
	private WatchService _watchService;

}