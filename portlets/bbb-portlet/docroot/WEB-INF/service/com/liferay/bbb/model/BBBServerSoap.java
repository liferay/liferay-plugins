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

package com.liferay.bbb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Shinn Lok
 * @generated
 */
public class BBBServerSoap implements Serializable {
	public static BBBServerSoap toSoapModel(BBBServer model) {
		BBBServerSoap soapModel = new BBBServerSoap();

		soapModel.setBbbServerId(model.getBbbServerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setUrl(model.getUrl());
		soapModel.setSecret(model.getSecret());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static BBBServerSoap[] toSoapModels(BBBServer[] models) {
		BBBServerSoap[] soapModels = new BBBServerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BBBServerSoap[][] toSoapModels(BBBServer[][] models) {
		BBBServerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BBBServerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BBBServerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BBBServerSoap[] toSoapModels(List<BBBServer> models) {
		List<BBBServerSoap> soapModels = new ArrayList<BBBServerSoap>(models.size());

		for (BBBServer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BBBServerSoap[soapModels.size()]);
	}

	public BBBServerSoap() {
	}

	public long getPrimaryKey() {
		return _bbbServerId;
	}

	public void setPrimaryKey(long pk) {
		setBbbServerId(pk);
	}

	public long getBbbServerId() {
		return _bbbServerId;
	}

	public void setBbbServerId(long bbbServerId) {
		_bbbServerId = bbbServerId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getSecret() {
		return _secret;
	}

	public void setSecret(String secret) {
		_secret = secret;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _bbbServerId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _secret;
	private boolean _active;
}