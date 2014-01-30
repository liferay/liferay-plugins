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
public class SyncAccount {

	public String getFilePathName() {
		return filePathName;
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

	public String getUrl() {
		return url;
	}

	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
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

	public void setUrl(String url) {
		this.url = url;
	}

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String filePathName;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String login;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String password;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncAccountId;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String url;

}