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

package com.liferay.sync.engine.documentlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Shinn Lok
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncDLObject {

	public String getChecksum() {
		return checksum;
	}

	public String getDescription() {
		return description;
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

	public long getSyncDLObjectId() {
		return syncDLObjectId;
	}

	public String getType() {
		return type;
	}

	public long getTypePK() {
		return typePK;
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

	public void setSyncDLObjectId(long syncDLObjectId) {
		this.syncDLObjectId = syncDLObjectId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTypePK(long typePK) {
		this.typePK = typePK;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	protected String checksum;
	protected String description;
	protected String name;
	protected long parentFolderId;
	protected long repositoryId;
	protected long size;
	protected long syncDLObjectId;
	protected String type;
	protected long typePK;
	protected String version;

}