/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
@JsonIgnoreProperties(ignoreUnknown = true, value = "active")
public class SyncSite extends StateAwareModel {

	public static final int STATE_CONNECTED = 1;

	public static final int STATE_DISCONNECTED = 0;

	public static final int TYPE_OPEN = 1;

	public static final int TYPE_PRIVATE = 3;

	public static final int TYPE_RESTRICTED = 2;

	public static final int TYPE_SYSTEM = 4;

	public static final int UI_EVENT_SYNC_SITE_FOLDER_MISSING = 1;

	public boolean getActive() {
		return active;
	}

	public long getCompanyId() {
		return companyId;
	}

	public String getDescription() {
		return description;
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

	public String getName() {
		if ((type == 0) && !site) {
			return friendlyURL.substring(1);
		}

		return name.replace(" LFR_ORGANIZATION", " (Org)");
	}

	public long getParentGroupId() {
		return parentGroupId;
	}

	public long getRemoteSyncTime() {
		return remoteSyncTime;
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

	public boolean isActive() {
		return getActive();
	}

	public boolean isSite() {
		return getSite();
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setParentGroupId(long parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public void setRemoteSyncTime(long remoteSyncTime) {
		this.remoteSyncTime = remoteSyncTime;
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
	protected boolean active;

	@DatabaseField(useGetSet = true)
	protected long companyId;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String description;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePathName;

	@DatabaseField(useGetSet = true)
	protected String friendlyURL;

	@DatabaseField(useGetSet = true)
	protected long groupId;

	@DatabaseField(useGetSet = true)
	protected String name;

	@DatabaseField(useGetSet = true)
	protected long parentGroupId;

	@DatabaseField(useGetSet = true)
	protected long remoteSyncTime;

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