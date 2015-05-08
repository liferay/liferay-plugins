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

package com.liferay.privatemessaging.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.privatemessaging.service.http.UserThreadServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.privatemessaging.service.http.UserThreadServiceSoap
 * @generated
 */
@ProviderType
public class UserThreadSoap implements Serializable {
	public static UserThreadSoap toSoapModel(UserThread model) {
		UserThreadSoap soapModel = new UserThreadSoap();

		soapModel.setUserThreadId(model.getUserThreadId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMbThreadId(model.getMbThreadId());
		soapModel.setTopMBMessageId(model.getTopMBMessageId());
		soapModel.setRead(model.getRead());
		soapModel.setDeleted(model.getDeleted());

		return soapModel;
	}

	public static UserThreadSoap[] toSoapModels(UserThread[] models) {
		UserThreadSoap[] soapModels = new UserThreadSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserThreadSoap[][] toSoapModels(UserThread[][] models) {
		UserThreadSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserThreadSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserThreadSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserThreadSoap[] toSoapModels(List<UserThread> models) {
		List<UserThreadSoap> soapModels = new ArrayList<UserThreadSoap>(models.size());

		for (UserThread model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserThreadSoap[soapModels.size()]);
	}

	public UserThreadSoap() {
	}

	public long getPrimaryKey() {
		return _userThreadId;
	}

	public void setPrimaryKey(long pk) {
		setUserThreadId(pk);
	}

	public long getUserThreadId() {
		return _userThreadId;
	}

	public void setUserThreadId(long userThreadId) {
		_userThreadId = userThreadId;
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

	public long getMbThreadId() {
		return _mbThreadId;
	}

	public void setMbThreadId(long mbThreadId) {
		_mbThreadId = mbThreadId;
	}

	public long getTopMBMessageId() {
		return _topMBMessageId;
	}

	public void setTopMBMessageId(long topMBMessageId) {
		_topMBMessageId = topMBMessageId;
	}

	public boolean getRead() {
		return _read;
	}

	public boolean isRead() {
		return _read;
	}

	public void setRead(boolean read) {
		_read = read;
	}

	public boolean getDeleted() {
		return _deleted;
	}

	public boolean isDeleted() {
		return _deleted;
	}

	public void setDeleted(boolean deleted) {
		_deleted = deleted;
	}

	private long _userThreadId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _mbThreadId;
	private long _topMBMessageId;
	private boolean _read;
	private boolean _deleted;
}