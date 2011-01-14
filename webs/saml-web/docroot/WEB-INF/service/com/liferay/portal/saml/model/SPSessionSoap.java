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
public class SPSessionSoap implements Serializable {
	public static SPSessionSoap toSoapModel(SPSession model) {
		SPSessionSoap soapModel = new SPSessionSoap();

		soapModel.setSpSessionId(model.getSpSessionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setSsoSessionId(model.getSsoSessionId());
		soapModel.setIssuer(model.getIssuer());

		return soapModel;
	}

	public static SPSessionSoap[] toSoapModels(SPSession[] models) {
		SPSessionSoap[] soapModels = new SPSessionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SPSessionSoap[][] toSoapModels(SPSession[][] models) {
		SPSessionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SPSessionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SPSessionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SPSessionSoap[] toSoapModels(List<SPSession> models) {
		List<SPSessionSoap> soapModels = new ArrayList<SPSessionSoap>(models.size());

		for (SPSession model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SPSessionSoap[soapModels.size()]);
	}

	public SPSessionSoap() {
	}

	public long getPrimaryKey() {
		return _spSessionId;
	}

	public void setPrimaryKey(long pk) {
		setSpSessionId(pk);
	}

	public long getSpSessionId() {
		return _spSessionId;
	}

	public void setSpSessionId(long spSessionId) {
		_spSessionId = spSessionId;
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

	public long getSsoSessionId() {
		return _ssoSessionId;
	}

	public void setSsoSessionId(long ssoSessionId) {
		_ssoSessionId = ssoSessionId;
	}

	public String getIssuer() {
		return _issuer;
	}

	public void setIssuer(String issuer) {
		_issuer = issuer;
	}

	private long _spSessionId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private long _ssoSessionId;
	private String _issuer;
}