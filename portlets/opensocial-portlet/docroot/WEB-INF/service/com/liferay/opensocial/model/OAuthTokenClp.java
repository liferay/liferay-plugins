/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

	public long getPrimaryKey() {
		return _oauthTokenId;
	}

	public void setPrimaryKey(long pk) {
		setOauthTokenId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_oauthTokenId);
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

	public long getGadgetId() {
		return _gadgetId;
	}

	public void setGadgetId(long gadgetId) {
		_gadgetId = gadgetId;
	}

	public long getModuleId() {
		return _moduleId;
	}

	public void setModuleId(long moduleId) {
		_moduleId = moduleId;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		_serviceName = serviceName;
	}

	public String getTokenName() {
		return _tokenName;
	}

	public void setTokenName(String tokenName) {
		_tokenName = tokenName;
	}

	public String getAccessToken() {
		return _accessToken;
	}

	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;
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

	public long getTokenExpireMillis() {
		return _tokenExpireMillis;
	}

	public void setTokenExpireMillis(long tokenExpireMillis) {
		_tokenExpireMillis = tokenExpireMillis;
	}

	public OAuthToken toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (OAuthToken)Proxy.newProxyInstance(OAuthToken.class.getClassLoader(),
				new Class[] { OAuthToken.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		OAuthTokenClp clone = new OAuthTokenClp();

		clone.setOauthTokenId(getOauthTokenId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setUserId(getUserId());
		clone.setGadgetId(getGadgetId());
		clone.setModuleId(getModuleId());
		clone.setServiceName(getServiceName());
		clone.setTokenName(getTokenName());
		clone.setAccessToken(getAccessToken());
		clone.setTokenSecret(getTokenSecret());
		clone.setSessionHandle(getSessionHandle());
		clone.setTokenExpireMillis(getTokenExpireMillis());

		return clone;
	}

	public int compareTo(OAuthToken oAuthToken) {
		long pk = oAuthToken.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

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

		long pk = oAuthToken.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{oauthTokenId=");
		sb.append(getOauthTokenId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", gadgetId=");
		sb.append(getGadgetId());
		sb.append(", moduleId=");
		sb.append(getModuleId());
		sb.append(", serviceName=");
		sb.append(getServiceName());
		sb.append(", tokenName=");
		sb.append(getTokenName());
		sb.append(", accessToken=");
		sb.append(getAccessToken());
		sb.append(", tokenSecret=");
		sb.append(getTokenSecret());
		sb.append(", sessionHandle=");
		sb.append(getSessionHandle());
		sb.append(", tokenExpireMillis=");
		sb.append(getTokenExpireMillis());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.opensocial.model.OAuthToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oauthTokenId</column-name><column-value><![CDATA[");
		sb.append(getOauthTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
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
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gadgetId</column-name><column-value><![CDATA[");
		sb.append(getGadgetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moduleId</column-name><column-value><![CDATA[");
		sb.append(getModuleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serviceName</column-name><column-value><![CDATA[");
		sb.append(getServiceName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tokenName</column-name><column-value><![CDATA[");
		sb.append(getTokenName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessToken</column-name><column-value><![CDATA[");
		sb.append(getAccessToken());
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
			"<column><column-name>tokenExpireMillis</column-name><column-value><![CDATA[");
		sb.append(getTokenExpireMillis());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _oauthTokenId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _userId;
	private String _userUuid;
	private long _gadgetId;
	private long _moduleId;
	private String _serviceName;
	private String _tokenName;
	private String _accessToken;
	private String _tokenSecret;
	private String _sessionHandle;
	private long _tokenExpireMillis;
}