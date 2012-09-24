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
import com.liferay.portal.oauth.service.ApplicationUserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ApplicationUserClp extends BaseModelImpl<ApplicationUser>
	implements ApplicationUser {
	public ApplicationUserClp() {
	}

	public Class<?> getModelClass() {
		return ApplicationUser.class;
	}

	public String getModelClassName() {
		return ApplicationUser.class.getName();
	}

	public long getPrimaryKey() {
		return _oaauId;
	}

	public void setPrimaryKey(long primaryKey) {
		setOaauId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_oaauId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oaauId", getOaauId());
		attributes.put("userId", getUserId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("accessToken", getAccessToken());
		attributes.put("accessSecret", getAccessSecret());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oaauId = (Long)attributes.get("oaauId");

		if (oaauId != null) {
			setOaauId(oaauId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String accessSecret = (String)attributes.get("accessSecret");

		if (accessSecret != null) {
			setAccessSecret(accessSecret);
		}
	}

	public long getOaauId() {
		return _oaauId;
	}

	public void setOaauId(long oaauId) {
		_oaauId = oaauId;
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

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
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

	public BaseModel<?> getApplicationUserRemoteModel() {
		return _applicationUserRemoteModel;
	}

	public void setApplicationUserRemoteModel(
		BaseModel<?> applicationUserRemoteModel) {
		_applicationUserRemoteModel = applicationUserRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			ApplicationUserLocalServiceUtil.addApplicationUser(this);
		}
		else {
			ApplicationUserLocalServiceUtil.updateApplicationUser(this);
		}
	}

	@Override
	public ApplicationUser toEscapedModel() {
		return (ApplicationUser)Proxy.newProxyInstance(ApplicationUser.class.getClassLoader(),
			new Class[] { ApplicationUser.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ApplicationUserClp clone = new ApplicationUserClp();

		clone.setOaauId(getOaauId());
		clone.setUserId(getUserId());
		clone.setApplicationId(getApplicationId());
		clone.setAccessToken(getAccessToken());
		clone.setAccessSecret(getAccessSecret());

		return clone;
	}

	public int compareTo(ApplicationUser applicationUser) {
		long primaryKey = applicationUser.getPrimaryKey();

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

		ApplicationUserClp applicationUser = null;

		try {
			applicationUser = (ApplicationUserClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = applicationUser.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{oaauId=");
		sb.append(getOaauId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", applicationId=");
		sb.append(getApplicationId());
		sb.append(", accessToken=");
		sb.append(getAccessToken());
		sb.append(", accessSecret=");
		sb.append(getAccessSecret());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.oauth.model.ApplicationUser");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oaauId</column-name><column-value><![CDATA[");
		sb.append(getOaauId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>applicationId</column-name><column-value><![CDATA[");
		sb.append(getApplicationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessToken</column-name><column-value><![CDATA[");
		sb.append(getAccessToken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessSecret</column-name><column-value><![CDATA[");
		sb.append(getAccessSecret());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _oaauId;
	private long _userId;
	private String _userUuid;
	private long _applicationId;
	private String _accessToken;
	private String _accessSecret;
	private BaseModel<?> _applicationUserRemoteModel;
}