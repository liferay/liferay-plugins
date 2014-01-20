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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.liferay.sync.engine.service.persistence.BasePersistenceImpl;

/**
 * @author Shinn Lok
 */
@DatabaseTable(daoClass = BasePersistenceImpl.class, tableName = "SyncFile")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncFile {

	public static final String TYPE_FILE = "file";

	public static final String TYPE_FOLDER = "folder";

	public String getChecksum() {
		return checksum;
	}

	public String getDescription() {
		return description;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getName() {
		return name;
	}

	public long getParentFolderId() {
		return parentFolderId;
	}

	public long getRepositoryId() {
		return repositoryId;
	}

	public long getSize() {
		return size;
	}

	public long getSyncAccountId() {
		return syncAccountId;
	}

	public long getSyncFileId() {
		return syncFileId;
	}

	public String getType() {
		return type;
	}

	public long getTypePK() {
		return typePK;
	}

	public String getTypeUuid() {
		return typeUuid;
	}

	public String getVersion() {
		return version;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentFolderId(long parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public void setRepositoryId(long repositoryId) {
		this.repositoryId = repositoryId;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setSyncAccountId(long syncAccountId) {
		this.syncAccountId = syncAccountId;
	}

	public void setSyncFileId(long syncFileId) {
		this.syncFileId = syncFileId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTypePK(long typePK) {
		this.typePK = typePK;
	}

	public void setTypeUuid(String typeUuid) {
		this.typeUuid = typeUuid;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@DatabaseField(useGetSet = true, width = 255)
	protected String checksum;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String description;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePath;

	@DatabaseField(useGetSet = true, width = 255)
	protected String name;

	@DatabaseField(useGetSet = true)
	protected long parentFolderId;

	@DatabaseField(useGetSet = true)
	protected long repositoryId;

	@DatabaseField(useGetSet = true)
	protected long size;

	@DatabaseField(useGetSet = true)
	protected long syncAccountId;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncFileId;

	@DatabaseField(useGetSet = true, width = 255)
	protected String type;

	@DatabaseField(useGetSet = true)
	protected long typePK;

	@DatabaseField(useGetSet = true, width = 75)
	protected String typeUuid;

	@DatabaseField(useGetSet = true, width = 255)
	protected String version;

}