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
 * This class is used by SOAP remote services, specifically {@link com.liferay.meeting.webex.service.http.WebExSiteServiceSoap}.
 *
 * @author Anant Singh
 * @see com.liferay.meeting.webex.service.http.WebExSiteServiceSoap
 * @generated
 */
@ProviderType
public class WebExSiteSoap implements Serializable {
	public static WebExSiteSoap toSoapModel(WebExSite model) {
		WebExSiteSoap soapModel = new WebExSiteSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setWebExSiteId(model.getWebExSiteId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setApiURL(model.getApiURL());
		soapModel.setLogin(model.getLogin());
		soapModel.setPassword(model.getPassword());
		soapModel.setPartnerKey(model.getPartnerKey());
		soapModel.setSiteKey(model.getSiteKey());

		return soapModel;
	}

	public static WebExSiteSoap[] toSoapModels(WebExSite[] models) {
		WebExSiteSoap[] soapModels = new WebExSiteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WebExSiteSoap[][] toSoapModels(WebExSite[][] models) {
		WebExSiteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WebExSiteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WebExSiteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WebExSiteSoap[] toSoapModels(List<WebExSite> models) {
		List<WebExSiteSoap> soapModels = new ArrayList<WebExSiteSoap>(models.size());

		for (WebExSite model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WebExSiteSoap[soapModels.size()]);
	}

	public WebExSiteSoap() {
	}

	public long getPrimaryKey() {
		return _webExSiteId;
	}

	public void setPrimaryKey(long pk) {
		setWebExSiteId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getWebExSiteId() {
		return _webExSiteId;
	}

	public void setWebExSiteId(long webExSiteId) {
		_webExSiteId = webExSiteId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getApiURL() {
		return _apiURL;
	}

	public void setApiURL(String apiURL) {
		_apiURL = apiURL;
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

	public String getPartnerKey() {
		return _partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		_partnerKey = partnerKey;
	}

	public long getSiteKey() {
		return _siteKey;
	}

	public void setSiteKey(long siteKey) {
		_siteKey = siteKey;
	}

	private String _uuid;
	private long _webExSiteId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _apiURL;
	private String _login;
	private String _password;
	private String _partnerKey;
	private long _siteKey;
}