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

/**
 * @author Shinn Lok
 */
@DatabaseTable(daoClass = BasePersistenceImpl.class, tableName = "SyncAccount")
public class SyncAccount extends StateAwareModel {

	public static final int STATE_CONNECTED = 1;

	public static final int STATE_DISCONNECTED = 0;

	public static final int UI_EVENT_AUTHENTICATION_EXCEPTION = 1;

	public static final int UI_EVENT_CONNECTION_EXCEPTION = 2;

	public boolean getActive() {
		return active;
	}

	public String getFilePathName() {
		return filePathName;
	}

	public int getInterval() {
		return interval;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
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

	public boolean isActive() {
		return getActive();
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

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@DatabaseField(useGetSet = true)
	protected boolean active;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePathName;

	@DatabaseField(useGetSet = true)
	protected int interval;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String login;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String password;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncAccountId;

	@DatabaseField(useGetSet = true)
	protected boolean trustSelfSigned;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String url;

}