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

package com.liferay.portal.oauth.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.oauth.service.OAuthApplications_UsersLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthApplications_UsersClp extends BaseModelImpl<OAuthApplications_Users>
	implements OAuthApplications_Users {
	public OAuthApplications_UsersClp() {
	}

	public Class<?> getModelClass() {
		return OAuthApplications_Users.class;
	}

	public String getModelClassName() {
		return OAuthApplications_Users.class.getName();
	}

	public long getPrimaryKey() {
		return _oaauid;
	}

	public void setPrimaryKey(long primaryKey) {
		setOaauid(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_oaauid);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oaauid", getOaauid());
		attributes.put("accessToken", getAccessToken());
		attributes.put("accessSecret", getAccessSecret());
		attributes.put("applicationId", getApplicationId());
		attributes.put("authorized", getAuthorized());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oaauid = (Long)attributes.get("oaauid");

		if (oaauid != null) {
			setOaauid(oaauid);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String accessSecret = (String)attributes.get("accessSecret");

		if (accessSecret != null) {
			setAccessSecret(accessSecret);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Boolean authorized = (Boolean)attributes.get("authorized");

		if (authorized != null) {
			setAuthorized(authorized);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	public long getOaauid() {
		return _oaauid;
	}

	public void setOaauid(long oaauid) {
		_oaauid = oaauid;
	}

	public String getAccessToken() {
		return _accessToken;
	}

	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;
	}

	public String getAccessSecret() {
		return _accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		_accessSecret = accessSecret;
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	public boolean getAuthorized() {
		return _authorized;
	}

	public boolean isAuthorized() {
		return _authorized;
	}

	public void setAuthorized(boolean authorized) {
		_authorized = authorized;
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

	public BaseModel<?> getOAuthApplications_UsersRemoteModel() {
		return _oAuthApplications_UsersRemoteModel;
	}

	public void setOAuthApplications_UsersRemoteModel(
		BaseModel<?> oAuthApplications_UsersRemoteModel) {
		_oAuthApplications_UsersRemoteModel = oAuthApplications_UsersRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			OAuthApplications_UsersLocalServiceUtil.addOAuthApplications_Users(this);
		}
		else {
			OAuthApplications_UsersLocalServiceUtil.updateOAuthApplications_Users(this);
		}
	}

	@Override
	public OAuthApplications_Users toEscapedModel() {
		return (OAuthApplications_Users)Proxy.newProxyInstance(OAuthApplications_Users.class.getClassLoader(),
			new Class[] { OAuthApplications_Users.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthApplications_UsersClp clone = new OAuthApplications_UsersClp();

		clone.setOaauid(getOaauid());
		clone.setAccessToken(getAccessToken());
		clone.setAccessSecret(getAccessSecret());
		clone.setApplicationId(getApplicationId());
		clone.setAuthorized(getAuthorized());
		clone.setUserId(getUserId());

		return clone;
	}

	public int compareTo(OAuthApplications_Users oAuthApplications_Users) {
		long primaryKey = oAuthApplications_Users.getPrimaryKey();

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

		OAuthApplications_UsersClp oAuthApplications_Users = null;

		try {
			oAuthApplications_Users = (OAuthApplications_UsersClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = oAuthApplications_Users.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{oaauid=");
		sb.append(getOaauid());
		sb.append(", accessToken=");
		sb.append(getAccessToken());
		sb.append(", accessSecret=");
		sb.append(getAccessSecret());
		sb.append(", applicationId=");
		sb.append(getApplicationId());
		sb.append(", authorized=");
		sb.append(getAuthorized());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.oauth.model.OAuthApplications_Users");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oaauid</column-name><column-value><![CDATA[");
		sb.append(getOaauid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessToken</column-name><column-value><![CDATA[");
		sb.append(getAccessToken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessSecret</column-name><column-value><![CDATA[");
		sb.append(getAccessSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>applicationId</column-name><column-value><![CDATA[");
		sb.append(getApplicationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>authorized</column-name><column-value><![CDATA[");
		sb.append(getAuthorized());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _oaauid;
	private String _accessToken;
	private String _accessSecret;
	private long _applicationId;
	private boolean _authorized;
	private long _userId;
	private String _userUuid;
	private BaseModel<?> _oAuthApplications_UsersRemoteModel;
}