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

package com.liferay.sync.engine.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.liferay.sync.engine.service.persistence.BasePersistenceImpl;

import java.nio.file.StandardWatchEventKinds;

/**
 * @author Michael Young
 */
@DatabaseTable(
	daoClass = BasePersistenceImpl.class, tableName = "SyncWatchEvent")
public class SyncWatchEvent {

	public static final String EVENT_TYPE_CREATE =
		StandardWatchEventKinds.ENTRY_CREATE.name();

	public static final String EVENT_TYPE_DELETE =
		StandardWatchEventKinds.ENTRY_DELETE.name();

	public static final String EVENT_TYPE_MODIFY =
		StandardWatchEventKinds.ENTRY_MODIFY.name();

	public static final String EVENT_TYPE_OVERFLOW =
		StandardWatchEventKinds.OVERFLOW.name();

	public String getEventType() {
		return eventType;
	}

	public String getFilePathName() {
		return filePathName;
	}

	public String getFileType() {
		return fileType;
	}

	public long getSyncAccountId() {
		return syncAccountId;
	}

	public long getSyncWatchEventId() {
		return syncWatchEventId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setSyncAccountId(long syncAccountId) {
		this.syncAccountId = syncAccountId;
	}

	public void setSyncWatchEventId(long syncWatchEventId) {
		this.syncWatchEventId = syncWatchEventId;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@DatabaseField(useGetSet = true)
	protected String eventType;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePathName;

	@DatabaseField(useGetSet = true)
	protected String fileType;

	@DatabaseField(useGetSet = true)
	protected long syncAccountId;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncWatchEventId;

	@DatabaseField(useGetSet = true)
	protected long timestamp;

}