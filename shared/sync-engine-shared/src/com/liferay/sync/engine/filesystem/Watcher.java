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

/**
 * @author Michael Young
 */
public class Watcher {

	public Watcher(Path dir, boolean recursive) throws IOException {
		_recursive = recursive;

		if (recursive) {
			_registerRecursive(dir);
		}
		else {
			_register(dir);
		}
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
		while (true) {
			WatchKey watchKey;

			try {
				watchKey = _watchService.take();
			}
			catch (InterruptedException ie) {
				return;
			}

			Path dir = _watchKeys.get(watchKey);

			if (dir == null) {
				continue;
			}

			List<WatchEvent<?>> watchEvents = watchKey.pollEvents();

			for (int i = 0; i < watchEvents.size(); i++) {
				WatchEvent<Path> watchEvent = (WatchEvent<Path>)watchEvents.get(
					i);

				Path name = watchEvent.context();
				Path child = dir.resolve(name);

				fireWatchEventListeners(watchEvent, child);

				WatchEvent.Kind kind = watchEvent.kind();

				if (_recursive &&
					(kind == StandardWatchEventKinds.ENTRY_CREATE)) {

					try {
						boolean isDirectory = Files.isDirectory(
							child, LinkOption.NOFOLLOW_LINKS);

						if (isDirectory) {
							_registerRecursive(child);
						}
					}
					catch (IOException ioe) {
					}
				}
			}

			boolean valid = watchKey.reset();

			if (!valid) {
				_watchKeys.remove(watchKey);

				if (_watchKeys.isEmpty()) {
					break;
				}
			}
		}
	}

	private void _register(Path dir) throws IOException {
		WatchKey key = dir.register(
			_watchService, StandardWatchEventKinds.ENTRY_CREATE,
			StandardWatchEventKinds.ENTRY_DELETE,
			StandardWatchEventKinds.ENTRY_MODIFY);

		_watchKeys.put(key, dir);
	}

	private void _registerRecursive(Path dir) throws IOException {
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(
					Path dir, BasicFileAttributes basicFileAttributes)
				throws IOException {

				_register(dir);

				return FileVisitResult.CONTINUE;
			}
		});
	}

	private boolean _recursive;
	private List<WatchEventListener> _watchEventListeners =
		new ArrayList<WatchEventListener>();
	private Map<WatchKey, Path> _watchKeys = new HashMap<WatchKey, Path>();
	private WatchService _watchService =
		FileSystems.getDefault().newWatchService();

}