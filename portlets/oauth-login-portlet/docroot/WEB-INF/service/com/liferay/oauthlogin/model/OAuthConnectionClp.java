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

import com.liferay.oauthlogin.service.ClpSerializer;
import com.liferay.oauthlogin.service.OAuthConnectionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andy Yang
 */
public class OAuthConnectionClp extends BaseModelImpl<OAuthConnection>
	implements OAuthConnection {
	public OAuthConnectionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthConnection.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthConnection.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthConnectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthConnectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthConnectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthConnectionId", getOAuthConnectionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("enabled", getEnabled());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("iconId", getIconId());
		attributes.put("oAuthVersion", getOAuthVersion());
		attributes.put("key", getKey());
		attributes.put("secret", getSecret());
		attributes.put("scope", getScope());
		attributes.put("authorizeURL", getAuthorizeURL());
		attributes.put("accessTokenURL", getAccessTokenURL());
		attributes.put("accessTokenVerb", getAccessTokenVerb());
		attributes.put("accessTokenExtractorType", getAccessTokenExtractorType());
		attributes.put("requestTokenURL", getRequestTokenURL());
		attributes.put("requestTokenVerb", getRequestTokenVerb());
		attributes.put("signatureServiceType", getSignatureServiceType());
		attributes.put("redirectURL", getRedirectURL());
		attributes.put("socialAccountIdURL", getSocialAccountIdURL());
		attributes.put("socialAccountIdURLVerb", getSocialAccountIdURLVerb());
		attributes.put("socialAccountIdField", getSocialAccountIdField());
		attributes.put("socialAccountIdType", getSocialAccountIdType());
		attributes.put("socialAccountIdScript", getSocialAccountIdScript());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthConnectionId = (Long)attributes.get("oAuthConnectionId");

		if (oAuthConnectionId != null) {
			setOAuthConnectionId(oAuthConnectionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long iconId = (Long)attributes.get("iconId");

		if (iconId != null) {
			setIconId(iconId);
		}

		Integer oAuthVersion = (Integer)attributes.get("oAuthVersion");

		if (oAuthVersion != null) {
			setOAuthVersion(oAuthVersion);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String secret = (String)attributes.get("secret");

		if (secret != null) {
			setSecret(secret);
		}

		String scope = (String)attributes.get("scope");

		if (scope != null) {
			setScope(scope);
		}

		String authorizeURL = (String)attributes.get("authorizeURL");

		if (authorizeURL != null) {
			setAuthorizeURL(authorizeURL);
		}

		String accessTokenURL = (String)attributes.get("accessTokenURL");

		if (accessTokenURL != null) {
			setAccessTokenURL(accessTokenURL);
		}

		Integer accessTokenVerb = (Integer)attributes.get("accessTokenVerb");

		if (accessTokenVerb != null) {
			setAccessTokenVerb(accessTokenVerb);
		}

		Integer accessTokenExtractorType = (Integer)attributes.get(
				"accessTokenExtractorType");

		if (accessTokenExtractorType != null) {
			setAccessTokenExtractorType(accessTokenExtractorType);
		}

		String requestTokenURL = (String)attributes.get("requestTokenURL");

		if (requestTokenURL != null) {
			setRequestTokenURL(requestTokenURL);
		}

		Integer requestTokenVerb = (Integer)attributes.get("requestTokenVerb");

		if (requestTokenVerb != null) {
			setRequestTokenVerb(requestTokenVerb);
		}

		Integer signatureServiceType = (Integer)attributes.get(
				"signatureServiceType");

		if (signatureServiceType != null) {
			setSignatureServiceType(signatureServiceType);
		}

		String redirectURL = (String)attributes.get("redirectURL");

		if (redirectURL != null) {
			setRedirectURL(redirectURL);
		}

		String socialAccountIdURL = (String)attributes.get("socialAccountIdURL");

		if (socialAccountIdURL != null) {
			setSocialAccountIdURL(socialAccountIdURL);
		}

		Integer socialAccountIdURLVerb = (Integer)attributes.get(
				"socialAccountIdURLVerb");

		if (socialAccountIdURLVerb != null) {
			setSocialAccountIdURLVerb(socialAccountIdURLVerb);
		}

		String socialAccountIdField = (String)attributes.get(
				"socialAccountIdField");

		if (socialAccountIdField != null) {
			setSocialAccountIdField(socialAccountIdField);
		}

		Integer socialAccountIdType = (Integer)attributes.get(
				"socialAccountIdType");

		if (socialAccountIdType != null) {
			setSocialAccountIdType(socialAccountIdType);
		}

		String socialAccountIdScript = (String)attributes.get(
				"socialAccountIdScript");

		if (socialAccountIdScript != null) {
			setSocialAccountIdScript(socialAccountIdScript);
		}
	}

	@Override
	public long getOAuthConnectionId() {
		return _oAuthConnectionId;
	}

	@Override
	public void setOAuthConnectionId(long oAuthConnectionId) {
		_oAuthConnectionId = oAuthConnectionId;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setOAuthConnectionId",
						long.class);

				method.invoke(_oAuthConnectionRemoteModel, oAuthConnectionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_oAuthConnectionRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_oAuthConnectionRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_oAuthConnectionRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_oAuthConnectionRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getEnabled() {
		return _enabled;
	}

	@Override
	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		_enabled = enabled;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setEnabled", boolean.class);

				method.invoke(_oAuthConnectionRemoteModel, enabled);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_oAuthConnectionRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_oAuthConnectionRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getIconId() {
		return _iconId;
	}

	@Override
	public void setIconId(long iconId) {
		_iconId = iconId;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setIconId", long.class);

				method.invoke(_oAuthConnectionRemoteModel, iconId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getOAuthVersion() {
		return _oAuthVersion;
	}

	@Override
	public void setOAuthVersion(int oAuthVersion) {
		_oAuthVersion = oAuthVersion;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setOAuthVersion", int.class);

				method.invoke(_oAuthConnectionRemoteModel, oAuthVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public void setKey(String key) {
		_key = key;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setKey", String.class);

				method.invoke(_oAuthConnectionRemoteModel, key);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSecret() {
		return _secret;
	}

	@Override
	public void setSecret(String secret) {
		_secret = secret;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSecret", String.class);

				method.invoke(_oAuthConnectionRemoteModel, secret);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getScope() {
		return _scope;
	}

	@Override
	public void setScope(String scope) {
		_scope = scope;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setScope", String.class);

				method.invoke(_oAuthConnectionRemoteModel, scope);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAuthorizeURL() {
		return _authorizeURL;
	}

	@Override
	public void setAuthorizeURL(String authorizeURL) {
		_authorizeURL = authorizeURL;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAuthorizeURL", String.class);

				method.invoke(_oAuthConnectionRemoteModel, authorizeURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAccessTokenURL() {
		return _accessTokenURL;
	}

	@Override
	public void setAccessTokenURL(String accessTokenURL) {
		_accessTokenURL = accessTokenURL;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessTokenURL",
						String.class);

				method.invoke(_oAuthConnectionRemoteModel, accessTokenURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getAccessTokenVerb() {
		return _accessTokenVerb;
	}

	@Override
	public void setAccessTokenVerb(int accessTokenVerb) {
		_accessTokenVerb = accessTokenVerb;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessTokenVerb", int.class);

				method.invoke(_oAuthConnectionRemoteModel, accessTokenVerb);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getAccessTokenExtractorType() {
		return _accessTokenExtractorType;
	}

	@Override
	public void setAccessTokenExtractorType(int accessTokenExtractorType) {
		_accessTokenExtractorType = accessTokenExtractorType;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessTokenExtractorType",
						int.class);

				method.invoke(_oAuthConnectionRemoteModel,
					accessTokenExtractorType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRequestTokenURL() {
		return _requestTokenURL;
	}

	@Override
	public void setRequestTokenURL(String requestTokenURL) {
		_requestTokenURL = requestTokenURL;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setRequestTokenURL",
						String.class);

				method.invoke(_oAuthConnectionRemoteModel, requestTokenURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRequestTokenVerb() {
		return _requestTokenVerb;
	}

	@Override
	public void setRequestTokenVerb(int requestTokenVerb) {
		_requestTokenVerb = requestTokenVerb;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setRequestTokenVerb", int.class);

				method.invoke(_oAuthConnectionRemoteModel, requestTokenVerb);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSignatureServiceType() {
		return _signatureServiceType;
	}

	@Override
	public void setSignatureServiceType(int signatureServiceType) {
		_signatureServiceType = signatureServiceType;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSignatureServiceType",
						int.class);

				method.invoke(_oAuthConnectionRemoteModel, signatureServiceType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRedirectURL() {
		return _redirectURL;
	}

	@Override
	public void setRedirectURL(String redirectURL) {
		_redirectURL = redirectURL;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setRedirectURL", String.class);

				method.invoke(_oAuthConnectionRemoteModel, redirectURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSocialAccountIdURL() {
		return _socialAccountIdURL;
	}

	@Override
	public void setSocialAccountIdURL(String socialAccountIdURL) {
		_socialAccountIdURL = socialAccountIdURL;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSocialAccountIdURL",
						String.class);

				method.invoke(_oAuthConnectionRemoteModel, socialAccountIdURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSocialAccountIdURLVerb() {
		return _socialAccountIdURLVerb;
	}

	@Override
	public void setSocialAccountIdURLVerb(int socialAccountIdURLVerb) {
		_socialAccountIdURLVerb = socialAccountIdURLVerb;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSocialAccountIdURLVerb",
						int.class);

				method.invoke(_oAuthConnectionRemoteModel,
					socialAccountIdURLVerb);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSocialAccountIdField() {
		return _socialAccountIdField;
	}

	@Override
	public void setSocialAccountIdField(String socialAccountIdField) {
		_socialAccountIdField = socialAccountIdField;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSocialAccountIdField",
						String.class);

				method.invoke(_oAuthConnectionRemoteModel, socialAccountIdField);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSocialAccountIdType() {
		return _socialAccountIdType;
	}

	@Override
	public void setSocialAccountIdType(int socialAccountIdType) {
		_socialAccountIdType = socialAccountIdType;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSocialAccountIdType",
						int.class);

				method.invoke(_oAuthConnectionRemoteModel, socialAccountIdType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSocialAccountIdScript() {
		return _socialAccountIdScript;
	}

	@Override
	public void setSocialAccountIdScript(String socialAccountIdScript) {
		_socialAccountIdScript = socialAccountIdScript;

		if (_oAuthConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSocialAccountIdScript",
						String.class);

				method.invoke(_oAuthConnectionRemoteModel, socialAccountIdScript);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getOAuthConnectionRemoteModel() {
		return _oAuthConnectionRemoteModel;
	}

	public void setOAuthConnectionRemoteModel(
		BaseModel<?> oAuthConnectionRemoteModel) {
		_oAuthConnectionRemoteModel = oAuthConnectionRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _oAuthConnectionRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_oAuthConnectionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			OAuthConnectionLocalServiceUtil.addOAuthConnection(this);
		}
		else {
			OAuthConnectionLocalServiceUtil.updateOAuthConnection(this);
		}
	}

	@Override
	public OAuthConnection toEscapedModel() {
		return (OAuthConnection)ProxyUtil.newProxyInstance(OAuthConnection.class.getClassLoader(),
			new Class[] { OAuthConnection.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthConnectionClp clone = new OAuthConnectionClp();

		clone.setOAuthConnectionId(getOAuthConnectionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setEnabled(getEnabled());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setIconId(getIconId());
		clone.setOAuthVersion(getOAuthVersion());
		clone.setKey(getKey());
		clone.setSecret(getSecret());
		clone.setScope(getScope());
		clone.setAuthorizeURL(getAuthorizeURL());
		clone.setAccessTokenURL(getAccessTokenURL());
		clone.setAccessTokenVerb(getAccessTokenVerb());
		clone.setAccessTokenExtractorType(getAccessTokenExtractorType());
		clone.setRequestTokenURL(getRequestTokenURL());
		clone.setRequestTokenVerb(getRequestTokenVerb());
		clone.setSignatureServiceType(getSignatureServiceType());
		clone.setRedirectURL(getRedirectURL());
		clone.setSocialAccountIdURL(getSocialAccountIdURL());
		clone.setSocialAccountIdURLVerb(getSocialAccountIdURLVerb());
		clone.setSocialAccountIdField(getSocialAccountIdField());
		clone.setSocialAccountIdType(getSocialAccountIdType());
		clone.setSocialAccountIdScript(getSocialAccountIdScript());

		return clone;
	}

	@Override
	public int compareTo(OAuthConnection oAuthConnection) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				oAuthConnection.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthConnectionClp)) {
			return false;
		}

		OAuthConnectionClp oAuthConnection = (OAuthConnectionClp)obj;

		long primaryKey = oAuthConnection.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{oAuthConnectionId=");
		sb.append(getOAuthConnectionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", enabled=");
		sb.append(getEnabled());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", iconId=");
		sb.append(getIconId());
		sb.append(", oAuthVersion=");
		sb.append(getOAuthVersion());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", secret=");
		sb.append(getSecret());
		sb.append(", scope=");
		sb.append(getScope());
		sb.append(", authorizeURL=");
		sb.append(getAuthorizeURL());
		sb.append(", accessTokenURL=");
		sb.append(getAccessTokenURL());
		sb.append(", accessTokenVerb=");
		sb.append(getAccessTokenVerb());
		sb.append(", accessTokenExtractorType=");
		sb.append(getAccessTokenExtractorType());
		sb.append(", requestTokenURL=");
		sb.append(getRequestTokenURL());
		sb.append(", requestTokenVerb=");
		sb.append(getRequestTokenVerb());
		sb.append(", signatureServiceType=");
		sb.append(getSignatureServiceType());
		sb.append(", redirectURL=");
		sb.append(getRedirectURL());
		sb.append(", socialAccountIdURL=");
		sb.append(getSocialAccountIdURL());
		sb.append(", socialAccountIdURLVerb=");
		sb.append(getSocialAccountIdURLVerb());
		sb.append(", socialAccountIdField=");
		sb.append(getSocialAccountIdField());
		sb.append(", socialAccountIdType=");
		sb.append(getSocialAccountIdType());
		sb.append(", socialAccountIdScript=");
		sb.append(getSocialAccountIdScript());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(82);

		sb.append("<model><model-name>");
		sb.append("com.liferay.oauthlogin.model.OAuthConnection");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oAuthConnectionId</column-name><column-value><![CDATA[");
		sb.append(getOAuthConnectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enabled</column-name><column-value><![CDATA[");
		sb.append(getEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>iconId</column-name><column-value><![CDATA[");
		sb.append(getIconId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oAuthVersion</column-name><column-value><![CDATA[");
		sb.append(getOAuthVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>secret</column-name><column-value><![CDATA[");
		sb.append(getSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scope</column-name><column-value><![CDATA[");
		sb.append(getScope());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>authorizeURL</column-name><column-value><![CDATA[");
		sb.append(getAuthorizeURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessTokenURL</column-name><column-value><![CDATA[");
		sb.append(getAccessTokenURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessTokenVerb</column-name><column-value><![CDATA[");
		sb.append(getAccessTokenVerb());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessTokenExtractorType</column-name><column-value><![CDATA[");
		sb.append(getAccessTokenExtractorType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>requestTokenURL</column-name><column-value><![CDATA[");
		sb.append(getRequestTokenURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>requestTokenVerb</column-name><column-value><![CDATA[");
		sb.append(getRequestTokenVerb());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signatureServiceType</column-name><column-value><![CDATA[");
		sb.append(getSignatureServiceType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>redirectURL</column-name><column-value><![CDATA[");
		sb.append(getRedirectURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>socialAccountIdURL</column-name><column-value><![CDATA[");
		sb.append(getSocialAccountIdURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>socialAccountIdURLVerb</column-name><column-value><![CDATA[");
		sb.append(getSocialAccountIdURLVerb());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>socialAccountIdField</column-name><column-value><![CDATA[");
		sb.append(getSocialAccountIdField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>socialAccountIdType</column-name><column-value><![CDATA[");
		sb.append(getSocialAccountIdType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>socialAccountIdScript</column-name><column-value><![CDATA[");
		sb.append(getSocialAccountIdScript());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _oAuthConnectionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
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
	private BaseModel<?> _oAuthConnectionRemoteModel;
}