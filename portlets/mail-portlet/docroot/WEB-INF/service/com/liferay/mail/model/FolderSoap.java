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

package com.liferay.mail.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class FolderSoap implements Serializable {
	public static FolderSoap toSoapModel(Folder model) {
		FolderSoap soapModel = new FolderSoap();

		soapModel.setFolderId(model.getFolderId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setFullName(model.getFullName());
		soapModel.setDisplayName(model.getDisplayName());
		soapModel.setRemoteMessageCount(model.getRemoteMessageCount());

		return soapModel;
	}

	public static FolderSoap[] toSoapModels(Folder[] models) {
		FolderSoap[] soapModels = new FolderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FolderSoap[][] toSoapModels(Folder[][] models) {
		FolderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FolderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FolderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FolderSoap[] toSoapModels(List<Folder> models) {
		List<FolderSoap> soapModels = new ArrayList<FolderSoap>(models.size());

		for (Folder model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FolderSoap[soapModels.size()]);
	}

	public FolderSoap() {
	}

	public long getPrimaryKey() {
		return _folderId;
	}

	public void setPrimaryKey(long pk) {
		setFolderId(pk);
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public String getFullName() {
		return _fullName;
	}

	public void setFullName(String fullName) {
		_fullName = fullName;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public int getRemoteMessageCount() {
		return _remoteMessageCount;
	}

	public void setRemoteMessageCount(int remoteMessageCount) {
		_remoteMessageCount = remoteMessageCount;
	}

	private long _folderId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountId;
	private String _fullName;
	private String _displayName;
	private int _remoteMessageCount;
}