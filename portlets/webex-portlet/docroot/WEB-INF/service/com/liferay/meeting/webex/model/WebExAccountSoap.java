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

package com.liferay.meeting.webex.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.meeting.webex.service.http.WebExAccountServiceSoap}.
 *
 * @author Anant Singh
 * @see com.liferay.meeting.webex.service.http.WebExAccountServiceSoap
 * @generated
 */
@ProviderType
public class WebExAccountSoap implements Serializable {
	public static WebExAccountSoap toSoapModel(WebExAccount model) {
		WebExAccountSoap soapModel = new WebExAccountSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setWebExAccountId(model.getWebExAccountId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWebExSiteId(model.getWebExSiteId());
		soapModel.setLogin(model.getLogin());
		soapModel.setPassword(model.getPassword());

		return soapModel;
	}

	public static WebExAccountSoap[] toSoapModels(WebExAccount[] models) {
		WebExAccountSoap[] soapModels = new WebExAccountSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WebExAccountSoap[][] toSoapModels(WebExAccount[][] models) {
		WebExAccountSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WebExAccountSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WebExAccountSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WebExAccountSoap[] toSoapModels(List<WebExAccount> models) {
		List<WebExAccountSoap> soapModels = new ArrayList<WebExAccountSoap>(models.size());

		for (WebExAccount model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WebExAccountSoap[soapModels.size()]);
	}

	public WebExAccountSoap() {
	}

	public long getPrimaryKey() {
		return _webExAccountId;
	}

	public void setPrimaryKey(long pk) {
		setWebExAccountId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getWebExAccountId() {
		return _webExAccountId;
	}

	public void setWebExAccountId(long webExAccountId) {
		_webExAccountId = webExAccountId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getWebExSiteId() {
		return _webExSiteId;
	}

	public void setWebExSiteId(long webExSiteId) {
		_webExSiteId = webExSiteId;
	}

	public String getLogin() {
		return _login;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	private String _uuid;
	private long _webExAccountId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _webExSiteId;
	private String _login;
	private String _password;
}