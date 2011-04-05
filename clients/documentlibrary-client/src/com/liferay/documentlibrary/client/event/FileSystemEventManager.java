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

/**
 * @author Gail Hernandez
 */
public interface FileSystemEventManager {

	public void addLocalFileEvent(long id, ChangeType changeType);

	public void addLocalFolderEvent(long id, ChangeType changeType);

	public boolean isLocalFileEvent(long id, ChangeType changeType);

	public boolean isLocalFolderEvent(long id, ChangeType changeType);

}