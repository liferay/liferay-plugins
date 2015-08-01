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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.opensocial.service.ClpSerializer;
import com.liferay.opensocial.service.OAuthTokenLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class OAuthTokenClp extends BaseModelImpl<OAuthToken>
	implements OAuthToken {
	public OAuthTokenClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthToken.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthToken.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthTokenId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthTokenId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthTokenId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthTokenId", getOAuthTokenId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("gadgetKey", getGadgetKey());
		attributes.put("serviceName", getServiceName());
		attributes.put("moduleId", getModuleId());
		attributes.put("accessToken", getAccessToken());
		attributes.put("tokenName", getTokenName());
		attributes.put("tokenSecret", getTokenSecret());
		attributes.put("sessionHandle", getSessionHandle());
		attributes.put("expiration", getExpiration());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthTokenId = (Long)attributes.get("oAuthTokenId");

		if (oAuthTokenId != null) {
			setOAuthTokenId(oAuthTokenId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String gadgetKey = (String)attributes.get("gadgetKey");

		if (gadgetKey != null) {
			setGadgetKey(gadgetKey);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		Long moduleId = (Long)attributes.get("moduleId");

		if (moduleId != null) {
			setModuleId(moduleId);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String tokenName = (String)attributes.get("tokenName");

		if (tokenName != null) {
			setTokenName(tokenName);
		}

		String tokenSecret = (String)attributes.get("tokenSecret");

		if (tokenSecret != null) {
			setTokenSecret(tokenSecret);
		}

		String sessionHandle = (String)attributes.get("sessionHandle");

		if (sessionHandle != null) {
			setSessionHandle(sessionHandle);
		}

		Long expiration = (Long)attributes.get("expiration");

		if (expiration != null) {
			setExpiration(expiration);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getOAuthTokenId() {
		return _oAuthTokenId;
	}

	@Override
	public void setOAuthTokenId(long oAuthTokenId) {
		_oAuthTokenId = oAuthTokenId;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setOAuthTokenId", long.class);

				method.invoke(_oAuthTokenRemoteModel, oAuthTokenId);
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

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_oAuthTokenRemoteModel, companyId);
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

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_oAuthTokenRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_oAuthTokenRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_oAuthTokenRemoteModel, createDate);
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

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_oAuthTokenRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getGadgetKey() {
		return _gadgetKey;
	}

	@Override
	public void setGadgetKey(String gadgetKey) {
		_gadgetKey = gadgetKey;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setGadgetKey", String.class);

				method.invoke(_oAuthTokenRemoteModel, gadgetKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getServiceName() {
		return _serviceName;
	}

	@Override
	public void setServiceName(String serviceName) {
		_serviceName = serviceName;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setServiceName", String.class);

				method.invoke(_oAuthTokenRemoteModel, serviceName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getModuleId() {
		return _moduleId;
	}

	@Override
	public void setModuleId(long moduleId) {
		_moduleId = moduleId;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setModuleId", long.class);

				method.invoke(_oAuthTokenRemoteModel, moduleId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAccessToken() {
		return _accessToken;
	}

	@Override
	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessToken", String.class);

				method.invoke(_oAuthTokenRemoteModel, accessToken);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTokenName() {
		return _tokenName;
	}

	@Override
	public void setTokenName(String tokenName) {
		_tokenName = tokenName;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setTokenName", String.class);

				method.invoke(_oAuthTokenRemoteModel, tokenName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTokenSecret() {
		return _tokenSecret;
	}

	@Override
	public void setTokenSecret(String tokenSecret) {
		_tokenSecret = tokenSecret;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setTokenSecret", String.class);

				method.invoke(_oAuthTokenRemoteModel, tokenSecret);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSessionHandle() {
		return _sessionHandle;
	}

	@Override
	public void setSessionHandle(String sessionHandle) {
		_sessionHandle = sessionHandle;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setSessionHandle", String.class);

				method.invoke(_oAuthTokenRemoteModel, sessionHandle);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getExpiration() {
		return _expiration;
	}

	@Override
	public void setExpiration(long expiration) {
		_expiration = expiration;

		if (_oAuthTokenRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setExpiration", long.class);

				method.invoke(_oAuthTokenRemoteModel, expiration);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getOAuthTokenRemoteModel() {
		return _oAuthTokenRemoteModel;
	}

	public void setOAuthTokenRemoteModel(BaseModel<?> oAuthTokenRemoteModel) {
		_oAuthTokenRemoteModel = oAuthTokenRemoteModel;
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

		Class<?> remoteModelClass = _oAuthTokenRemoteModel.getClass();

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

		Object returnValue = method.invoke(_oAuthTokenRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			OAuthTokenLocalServiceUtil.addOAuthToken(this);
		}
		else {
			OAuthTokenLocalServiceUtil.updateOAuthToken(this);
		}
	}

	@Override
	public OAuthToken toEscapedModel() {
		return (OAuthToken)ProxyUtil.newProxyInstance(OAuthToken.class.getClassLoader(),
			new Class[] { OAuthToken.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthTokenClp clone = new OAuthTokenClp();

		clone.setOAuthTokenId(getOAuthTokenId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setGadgetKey(getGadgetKey());
		clone.setServiceName(getServiceName());
		clone.setModuleId(getModuleId());
		clone.setAccessToken(getAccessToken());
		clone.setTokenName(getTokenName());
		clone.setTokenSecret(getTokenSecret());
		clone.setSessionHandle(getSessionHandle());
		clone.setExpiration(getExpiration());

		return clone;
	}

	@Override
	public int compareTo(OAuthToken oAuthToken) {
		long primaryKey = oAuthToken.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthTokenClp)) {
			return false;
		}

		OAuthTokenClp oAuthToken = (OAuthTokenClp)obj;

		long primaryKey = oAuthToken.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{oAuthTokenId=");
		sb.append(getOAuthTokenId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", gadgetKey=");
		sb.append(getGadgetKey());
		sb.append(", serviceName=");
		sb.append(getServiceName());
		sb.append(", moduleId=");
		sb.append(getModuleId());
		sb.append(", accessToken=");
		sb.append(getAccessToken());
		sb.append(", tokenName=");
		sb.append(getTokenName());
		sb.append(", tokenSecret=");
		sb.append(getTokenSecret());
		sb.append(", sessionHandle=");
		sb.append(getSessionHandle());
		sb.append(", expiration=");
		sb.append(getExpiration());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.opensocial.model.OAuthToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oAuthTokenId</column-name><column-value><![CDATA[");
		sb.append(getOAuthTokenId());
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
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
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
			"<column><column-name>gadgetKey</column-name><column-value><![CDATA[");
		sb.append(getGadgetKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serviceName</column-name><column-value><![CDATA[");
		sb.append(getServiceName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moduleId</column-name><column-value><![CDATA[");
		sb.append(getModuleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessToken</column-name><column-value><![CDATA[");
		sb.append(getAccessToken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tokenName</column-name><column-value><![CDATA[");
		sb.append(getTokenName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tokenSecret</column-name><column-value><![CDATA[");
		sb.append(getTokenSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionHandle</column-name><column-value><![CDATA[");
		sb.append(getSessionHandle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expiration</column-name><column-value><![CDATA[");
		sb.append(getExpiration());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _oAuthTokenId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _gadgetKey;
	private String _serviceName;
	private long _moduleId;
	private String _accessToken;
	private String _tokenName;
	private String _tokenSecret;
	private String _sessionHandle;
	private long _expiration;
	private BaseModel<?> _oAuthTokenRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.opensocial.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}