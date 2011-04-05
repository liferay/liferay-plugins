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

package com.liferay.documentlibrary.client.event;

import com.liferay.documentlibrary.client.local.ChangeType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gail Hernandez
 */
public class FileSystemEventManagerImpl	implements FileSystemEventManager {

	public void addLocalFileEvent(long id, ChangeType changeType) {
		_files.put(id, changeType);
	}

	public void addLocalFolderEvent(long id, ChangeType changeType) {
		_folders.put(id, changeType);
	}

	public boolean isLocalFileEvent(long id, ChangeType changeType) {
		if (_files.containsKey(id)) {
			_files.remove(id);

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isLocalFolderEvent(long id, ChangeType changeType) {
		if (_folders.containsKey(id)) {
			_folders.remove(id);

			return true;
		}
		else {
			return false;
		}
	}

	private Map<Long, ChangeType> _files =
		new HashMap<Long, ChangeType>();

	private Map<Long, ChangeType> _folders =
		new HashMap<Long, ChangeType>();

}