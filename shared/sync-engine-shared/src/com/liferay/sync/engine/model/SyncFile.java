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

	public String getChangeLog() {
		return changeLog;
	}

	public String getChecksum() {
		return checksum;
	}

	public long getCompanyId() {
		return companyId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public String getDescription() {
		return description;
	}

	public String getExtension() {
		return extension;
	}

	public String getExtraSettings() {
		return extraSettings;
	}

	public String getFileKey() {
		return fileKey;
	}

	public String getFilePathName() {
		return filePathName;
	}

	public long getLockExpirationDate() {
		return lockExpirationDate;
	}

	public String getLockUserId() {
		return lockUserId;
	}

	public String getLockUserName() {
		return lockUserName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public long getModifiedTime() {
		return modifiedTime;
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

	public double getVersion() {
		return version;
	}

	public void setChangeLog(String changeLog) {
		this.changeLog = changeLog;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void setExtraSettings(String extraSettings) {
		this.extraSettings = extraSettings;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}

	public void setLockExpirationDate(long lockExpirationDate) {
		this.lockExpirationDate = lockExpirationDate;
	}

	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}

	public void setLockUserName(String lockUserName) {
		this.lockUserName = lockUserName;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
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

	public void setVersion(double version) {
		this.version = version;
	}

	@DatabaseField(useGetSet = true)
	protected String changeLog;

	@DatabaseField(useGetSet = true)
	protected String checksum;

	@DatabaseField(useGetSet = true)
	protected long companyId;

	@DatabaseField(useGetSet = true)
	protected long createTime;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String description;

	@DatabaseField(useGetSet = true)
	protected String extension;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String extraSettings;

	@DatabaseField(useGetSet = true)
	protected String fileKey;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePathName;

	@DatabaseField(useGetSet = true)
	protected long lockExpirationDate;

	@DatabaseField(useGetSet = true)
	protected String lockUserId;

	@DatabaseField(useGetSet = true)
	protected String lockUserName;

	@DatabaseField(useGetSet = true)
	protected String mimeType;

	@DatabaseField(useGetSet = true)
	protected long modifiedTime;

	@DatabaseField(useGetSet = true)
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

	@DatabaseField(useGetSet = true)
	protected String type;

	@DatabaseField(useGetSet = true)
	protected long typePK;

	@DatabaseField(useGetSet = true)
	protected String typeUuid;

	@DatabaseField(useGetSet = true)
	protected double version;

}