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

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * @author Michael Young
 */
public interface WatchEventListener {

	public void entryCreate(Path filePath, WatchEvent<Path> watchEvent);

	public void entryDelete(Path filePath, WatchEvent<Path> watchEvent);

	public void entryModify(Path filePath, WatchEvent<Path> watchEvent);

	public void overflow(Path filePath, WatchEvent<Path> watchEvent);

}