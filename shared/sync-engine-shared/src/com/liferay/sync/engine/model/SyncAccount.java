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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.liferay.sync.engine.service.persistence.BasePersistenceImpl;

/**
 * @author Shinn Lok
 */
@DatabaseTable(daoClass = BasePersistenceImpl.class, tableName = "SyncAccount")
public class SyncAccount extends StateAwareModel {

	public static final int STATE_CONNECTED = 2;

	public static final int STATE_CONNECTING = 1;

	public static final int STATE_DISCONNECTED = 0;

	public static final int UI_EVENT_AUTHENTICATION_EXCEPTION = 1;

	public static final int UI_EVENT_CONNECTION_EXCEPTION = 2;

	public static final int UI_EVENT_SYNC_ACCOUNT_FOLDER_MISSING = 3;

	public static final int UI_EVENT_SYNC_SERVICES_NOT_ACTIVE = 6;

	public static final int UI_EVENT_SYNC_WEB_MISSING = 4;

	public static final int UI_EVENT_SYNC_WEB_OUT_OF_DATE = 5;

	public boolean getActive() {
		return active;
	}

	public String getFilePathName() {
		return filePathName;
	}

	public String getLogin() {
		return login;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getPollInterval() {
		return pollInterval;
	}

	public boolean getSocialOfficeInstalled() {
		return socialOfficeInstalled;
	}

	public long getSyncAccountId() {
		return syncAccountId;
	}

	public boolean getTrustSelfSigned() {
		return trustSelfSigned;
	}

	public String getUrl() {
		return url;
	}

	public long getUserId() {
		return userId;
	}

	public boolean isActive() {
		return getActive();
	}

	public boolean isSocialOfficeInstalled() {
		return getSocialOfficeInstalled();
	}

	public boolean isTrustSelfSigned() {
		return getTrustSelfSigned();
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPollInterval(int pollInterval) {
		this.pollInterval = pollInterval;
	}

	public void setSocialOfficeInstalled(boolean socialOfficeInstalled) {
		this.socialOfficeInstalled = socialOfficeInstalled;
	}

	public void setSyncAccountId(long syncAccountId) {
		this.syncAccountId = syncAccountId;
	}

	public void setTrustSelfSigned(boolean trustSelfSigned) {
		this.trustSelfSigned = trustSelfSigned;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@DatabaseField(useGetSet = true)
	protected boolean active;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePathName;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String login;

	@DatabaseField(useGetSet = true)
	protected int maxConnections;

	@DatabaseField(useGetSet = true)
	protected String name;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String password;

	@DatabaseField(useGetSet = true)
	protected int pollInterval;

	@DatabaseField(useGetSet = true)
	protected boolean socialOfficeInstalled;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncAccountId;

	@DatabaseField(useGetSet = true)
	protected boolean trustSelfSigned;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String url;

	@DatabaseField(useGetSet = true)
	protected long userId;

}