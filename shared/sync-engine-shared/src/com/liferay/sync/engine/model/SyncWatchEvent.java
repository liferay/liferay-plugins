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

	public static final String ENTRY_CREATE =
		StandardWatchEventKinds.ENTRY_CREATE.name();

	public static final String ENTRY_DELETE =
		StandardWatchEventKinds.ENTRY_DELETE.name();

	public static final String ENTRY_MODIFY =
		StandardWatchEventKinds.ENTRY_MODIFY.name();

	public static final String OVERFLOW =
		StandardWatchEventKinds.OVERFLOW.name();

	public String getFilePath() {
		return filePath;
	}

	public String getKind() {
		return kind;
	}

	public long getSyncWatchEventId() {
		return syncWatchEventId;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setSyncWatchEventId(long syncWatchEventId) {
		this.syncWatchEventId = syncWatchEventId;
	}

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePath;

	@DatabaseField(useGetSet = true)
	protected String kind;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncWatchEventId;

}