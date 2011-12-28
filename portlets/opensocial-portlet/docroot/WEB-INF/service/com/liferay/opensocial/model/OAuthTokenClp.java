/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.opensocial.service.OAuthTokenLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthTokenClp extends BaseModelImpl<OAuthToken>
	implements OAuthToken {
	public OAuthTokenClp() {
	}

	public Class<?> getModelClass() {
		return OAuthToken.class;
	}

	public String getModelClassName() {
		return OAuthToken.class.getName();
	}

	public long getPrimaryKey() {
		return _oAuthTokenId;
	}

	public void setPrimaryKey(long primaryKey) {
		setOAuthTokenId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_oAuthTokenId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getOAuthTokenId() {
		return _oAuthTokenId;
	}

	public void setOAuthTokenId(long oAuthTokenId) {
		_oAuthTokenId = oAuthTokenId;
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
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

	public String getGadgetKey() {
		return _gadgetKey;
	}

	public void setGadgetKey(String gadgetKey) {
		_gadgetKey = gadgetKey;
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

	public void persist() throws SystemException {
		OAuthTokenLocalServiceUtil.updateOAuthToken(this);
	}

	@Override
	public OAuthToken toEscapedModel() {
		return (OAuthToken)Proxy.newProxyInstance(OAuthToken.class.getClassLoader(),
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
		if (obj == null) {
			return false;
		}

		OAuthTokenClp oAuthToken = null;

		try {
			oAuthToken = (OAuthTokenClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = oAuthToken.getPrimaryKey();

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
	private String _userUuid;
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
}