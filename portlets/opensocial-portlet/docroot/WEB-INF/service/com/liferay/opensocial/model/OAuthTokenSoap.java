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

package com.liferay.opensocial.model;

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
public class OAuthTokenSoap implements Serializable {
	public static OAuthTokenSoap toSoapModel(OAuthToken model) {
		OAuthTokenSoap soapModel = new OAuthTokenSoap();

		soapModel.setOauthTokenId(model.getOauthTokenId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGadgetId(model.getGadgetId());
		soapModel.setServiceName(model.getServiceName());
		soapModel.setModuleId(model.getModuleId());
		soapModel.setAccessToken(model.getAccessToken());
		soapModel.setTokenName(model.getTokenName());
		soapModel.setTokenSecret(model.getTokenSecret());
		soapModel.setSessionHandle(model.getSessionHandle());
		soapModel.setExpiration(model.getExpiration());

		return soapModel;
	}

	public static OAuthTokenSoap[] toSoapModels(OAuthToken[] models) {
		OAuthTokenSoap[] soapModels = new OAuthTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OAuthTokenSoap[][] toSoapModels(OAuthToken[][] models) {
		OAuthTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OAuthTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OAuthTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OAuthTokenSoap[] toSoapModels(List<OAuthToken> models) {
		List<OAuthTokenSoap> soapModels = new ArrayList<OAuthTokenSoap>(models.size());

		for (OAuthToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OAuthTokenSoap[soapModels.size()]);
	}

	public OAuthTokenSoap() {
	}

	public long getPrimaryKey() {
		return _oauthTokenId;
	}

	public void setPrimaryKey(long pk) {
		setOauthTokenId(pk);
	}

	public long getOauthTokenId() {
		return _oauthTokenId;
	}

	public void setOauthTokenId(long oauthTokenId) {
		_oauthTokenId = oauthTokenId;
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

	public long getGadgetId() {
		return _gadgetId;
	}

	public void setGadgetId(long gadgetId) {
		_gadgetId = gadgetId;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		_serviceName = serviceName;
	}

	public long getModuleId() {
		return _moduleId;
	}

	public void setModuleId(long moduleId) {
		_moduleId = moduleId;
	}

	public String getAccessToken() {
		return _accessToken;
	}

	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;
	}

	public String getTokenName() {
		return _tokenName;
	}

	public void setTokenName(String tokenName) {
		_tokenName = tokenName;
	}

	public String getTokenSecret() {
		return _tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		_tokenSecret = tokenSecret;
	}

	public String getSessionHandle() {
		return _sessionHandle;
	}

	public void setSessionHandle(String sessionHandle) {
		_sessionHandle = sessionHandle;
	}

	public long getExpiration() {
		return _expiration;
	}

	public void setExpiration(long expiration) {
		_expiration = expiration;
	}

	private long _oauthTokenId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _gadgetId;
	private String _serviceName;
	private long _moduleId;
	private String _accessToken;
	private String _tokenName;
	private String _tokenSecret;
	private String _sessionHandle;
	private long _expiration;
}