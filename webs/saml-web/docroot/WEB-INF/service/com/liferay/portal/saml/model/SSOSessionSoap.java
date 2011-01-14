/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class SSOSessionSoap implements Serializable {
	public static SSOSessionSoap toSoapModel(SSOSession model) {
		SSOSessionSoap soapModel = new SSOSessionSoap();

		soapModel.setSsoSessionId(model.getSsoSessionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setLastActiveDate(model.getLastActiveDate());
		soapModel.setKey(model.getKey());

		return soapModel;
	}

	public static SSOSessionSoap[] toSoapModels(SSOSession[] models) {
		SSOSessionSoap[] soapModels = new SSOSessionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SSOSessionSoap[][] toSoapModels(SSOSession[][] models) {
		SSOSessionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SSOSessionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SSOSessionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SSOSessionSoap[] toSoapModels(List<SSOSession> models) {
		List<SSOSessionSoap> soapModels = new ArrayList<SSOSessionSoap>(models.size());

		for (SSOSession model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SSOSessionSoap[soapModels.size()]);
	}

	public SSOSessionSoap() {
	}

	public long getPrimaryKey() {
		return _ssoSessionId;
	}

	public void setPrimaryKey(long pk) {
		setSsoSessionId(pk);
	}

	public long getSsoSessionId() {
		return _ssoSessionId;
	}

	public void setSsoSessionId(long ssoSessionId) {
		_ssoSessionId = ssoSessionId;
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

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getLastActiveDate() {
		return _lastActiveDate;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		_lastActiveDate = lastActiveDate;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	private long _ssoSessionId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _lastActiveDate;
	private String _key;
}