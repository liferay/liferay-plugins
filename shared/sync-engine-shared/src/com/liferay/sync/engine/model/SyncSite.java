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
@DatabaseTable(daoClass = BasePersistenceImpl.class, tableName = "SyncSite")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncSite {

	public long getCompanyId() {
		return companyId;
	}

	public String getDescription() {
		return description;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public String getFilePathName() {
		return filePathName;
	}

	public String getFriendlyURL() {
		return friendlyURL;
	}

	public long getGroupId() {
		return groupId;
	}

	public long getLastRemoteSyncTime() {
		return lastRemoteSyncTime;
	}

	public String getName() {
		return name;
	}

	public boolean getSite() {
		return site;
	}

	public long getSyncAccountId() {
		return syncAccountId;
	}

	public long getSyncSiteId() {
		return syncSiteId;
	}

	public long getType() {
		return type;
	}

	public String getTypeSettings() {
		return typeSettings;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}

	public void setFriendlyURL(String friendlyURL) {
		this.friendlyURL = friendlyURL;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public void setLastRemoteSyncTime(long lastRemoteSyncTime) {
		this.lastRemoteSyncTime = lastRemoteSyncTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSite(boolean site) {
		this.site = site;
	}

	public void setSyncAccountId(long syncAccountId) {
		this.syncAccountId = syncAccountId;
	}

	public void setSyncSiteId(long syncSiteId) {
		this.syncSiteId = syncSiteId;
	}

	public void setType(long type) {
		this.type = type;
	}

	public void setTypeSettings(String typeSettings) {
		this.typeSettings = typeSettings;
	}

	@DatabaseField(useGetSet = true)
	protected long companyId;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String description;

	@DatabaseField(useGetSet = true)
	protected boolean enabled;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePathName;

	@DatabaseField(useGetSet = true)
	protected String friendlyURL;

	@DatabaseField(useGetSet = true)
	protected long groupId;

	@DatabaseField(useGetSet = true)
	protected long lastRemoteSyncTime;

	@DatabaseField(useGetSet = true)
	protected String name;

	@DatabaseField(useGetSet = true)
	protected boolean site;

	@DatabaseField(useGetSet = true)
	protected long syncAccountId;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncSiteId;

	@DatabaseField(useGetSet = true)
	protected long type;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String typeSettings;

}