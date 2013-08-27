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

package com.liferay.oauthlogin.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Andy Yang
 * @generated
 */
public class OAuthConnectionSoap implements Serializable {
	public static OAuthConnectionSoap toSoapModel(OAuthConnection model) {
		OAuthConnectionSoap soapModel = new OAuthConnectionSoap();

		soapModel.setOAuthConnectionId(model.getOAuthConnectionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEnabled(model.getEnabled());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setIconId(model.getIconId());
		soapModel.setOAuthVersion(model.getOAuthVersion());
		soapModel.setKey(model.getKey());
		soapModel.setSecret(model.getSecret());
		soapModel.setScope(model.getScope());
		soapModel.setAuthorizeURL(model.getAuthorizeURL());
		soapModel.setAccessTokenURL(model.getAccessTokenURL());
		soapModel.setAccessTokenVerb(model.getAccessTokenVerb());
		soapModel.setAccessTokenExtractorType(model.getAccessTokenExtractorType());
		soapModel.setRequestTokenURL(model.getRequestTokenURL());
		soapModel.setRequestTokenVerb(model.getRequestTokenVerb());
		soapModel.setSignatureServiceType(model.getSignatureServiceType());
		soapModel.setRedirectURL(model.getRedirectURL());
		soapModel.setSocialAccountIdURL(model.getSocialAccountIdURL());
		soapModel.setSocialAccountIdURLVerb(model.getSocialAccountIdURLVerb());
		soapModel.setSocialAccountIdField(model.getSocialAccountIdField());
		soapModel.setSocialAccountIdType(model.getSocialAccountIdType());
		soapModel.setSocialAccountIdScript(model.getSocialAccountIdScript());

		return soapModel;
	}

	public static OAuthConnectionSoap[] toSoapModels(OAuthConnection[] models) {
		OAuthConnectionSoap[] soapModels = new OAuthConnectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OAuthConnectionSoap[][] toSoapModels(
		OAuthConnection[][] models) {
		OAuthConnectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OAuthConnectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OAuthConnectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OAuthConnectionSoap[] toSoapModels(
		List<OAuthConnection> models) {
		List<OAuthConnectionSoap> soapModels = new ArrayList<OAuthConnectionSoap>(models.size());

		for (OAuthConnection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OAuthConnectionSoap[soapModels.size()]);
	}

	public OAuthConnectionSoap() {
	}

	public long getPrimaryKey() {
		return _oAuthConnectionId;
	}

	public void setPrimaryKey(long pk) {
		setOAuthConnectionId(pk);
	}

	public long getOAuthConnectionId() {
		return _oAuthConnectionId;
	}

	public void setOAuthConnectionId(long oAuthConnectionId) {
		_oAuthConnectionId = oAuthConnectionId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public boolean getEnabled() {
		return _enabled;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getIconId() {
		return _iconId;
	}

	public void setIconId(long iconId) {
		_iconId = iconId;
	}

	public int getOAuthVersion() {
		return _oAuthVersion;
	}

	public void setOAuthVersion(int oAuthVersion) {
		_oAuthVersion = oAuthVersion;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getSecret() {
		return _secret;
	}

	public void setSecret(String secret) {
		_secret = secret;
	}

	public String getScope() {
		return _scope;
	}

	public void setScope(String scope) {
		_scope = scope;
	}

	public String getAuthorizeURL() {
		return _authorizeURL;
	}

	public void setAuthorizeURL(String authorizeURL) {
		_authorizeURL = authorizeURL;
	}

	public String getAccessTokenURL() {
		return _accessTokenURL;
	}

	public void setAccessTokenURL(String accessTokenURL) {
		_accessTokenURL = accessTokenURL;
	}

	public int getAccessTokenVerb() {
		return _accessTokenVerb;
	}

	public void setAccessTokenVerb(int accessTokenVerb) {
		_accessTokenVerb = accessTokenVerb;
	}

	public int getAccessTokenExtractorType() {
		return _accessTokenExtractorType;
	}

	public void setAccessTokenExtractorType(int accessTokenExtractorType) {
		_accessTokenExtractorType = accessTokenExtractorType;
	}

	public String getRequestTokenURL() {
		return _requestTokenURL;
	}

	public void setRequestTokenURL(String requestTokenURL) {
		_requestTokenURL = requestTokenURL;
	}

	public int getRequestTokenVerb() {
		return _requestTokenVerb;
	}

	public void setRequestTokenVerb(int requestTokenVerb) {
		_requestTokenVerb = requestTokenVerb;
	}

	public int getSignatureServiceType() {
		return _signatureServiceType;
	}

	public void setSignatureServiceType(int signatureServiceType) {
		_signatureServiceType = signatureServiceType;
	}

	public String getRedirectURL() {
		return _redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		_redirectURL = redirectURL;
	}

	public String getSocialAccountIdURL() {
		return _socialAccountIdURL;
	}

	public void setSocialAccountIdURL(String socialAccountIdURL) {
		_socialAccountIdURL = socialAccountIdURL;
	}

	public int getSocialAccountIdURLVerb() {
		return _socialAccountIdURLVerb;
	}

	public void setSocialAccountIdURLVerb(int socialAccountIdURLVerb) {
		_socialAccountIdURLVerb = socialAccountIdURLVerb;
	}

	public String getSocialAccountIdField() {
		return _socialAccountIdField;
	}

	public void setSocialAccountIdField(String socialAccountIdField) {
		_socialAccountIdField = socialAccountIdField;
	}

	public int getSocialAccountIdType() {
		return _socialAccountIdType;
	}

	public void setSocialAccountIdType(int socialAccountIdType) {
		_socialAccountIdType = socialAccountIdType;
	}

	public String getSocialAccountIdScript() {
		return _socialAccountIdScript;
	}

	public void setSocialAccountIdScript(String socialAccountIdScript) {
		_socialAccountIdScript = socialAccountIdScript;
	}

	private long _oAuthConnectionId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _enabled;
	private String _name;
	private String _description;
	private long _iconId;
	private int _oAuthVersion;
	private String _key;
	private String _secret;
	private String _scope;
	private String _authorizeURL;
	private String _accessTokenURL;
	private int _accessTokenVerb;
	private int _accessTokenExtractorType;
	private String _requestTokenURL;
	private int _requestTokenVerb;
	private int _signatureServiceType;
	private String _redirectURL;
	private String _socialAccountIdURL;
	private int _socialAccountIdURLVerb;
	private String _socialAccountIdField;
	private int _socialAccountIdType;
	private String _socialAccountIdScript;
}