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
import com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthApplicationClp extends BaseModelImpl<OAuthApplication>
	implements OAuthApplication {
	public OAuthApplicationClp() {
	}

	public Class<?> getModelClass() {
		return OAuthApplication.class;
	}

	public String getModelClassName() {
		return OAuthApplication.class.getName();
	}

	public long getPrimaryKey() {
		return _applicationId;
	}

	public void setPrimaryKey(long primaryKey) {
		setApplicationId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_applicationId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("applicationId", getApplicationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ownerId", getOwnerId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("website", getWebsite());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());
		attributes.put("callBackURL", getCallBackURL());
		attributes.put("accessLevel", getAccessLevel());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
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

		Long ownerId = (Long)attributes.get("ownerId");

		if (ownerId != null) {
			setOwnerId(ownerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String website = (String)attributes.get("website");

		if (website != null) {
			setWebsite(website);
		}

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}

		String callBackURL = (String)attributes.get("callBackURL");

		if (callBackURL != null) {
			setCallBackURL(callBackURL);
		}

		Integer accessLevel = (Integer)attributes.get("accessLevel");

		if (accessLevel != null) {
			setAccessLevel(accessLevel);
		}
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
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

	public long getOwnerId() {
		return _ownerId;
	}

	public void setOwnerId(long ownerId) {
		_ownerId = ownerId;
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

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String website) {
		_website = website;
	}

	public String getConsumerKey() {
		return _consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return _consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;
	}

	public String getCallBackURL() {
		return _callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		_callBackURL = callBackURL;
	}

	public int getAccessLevel() {
		return _accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		_accessLevel = accessLevel;
	}

	public BaseModel<?> getOAuthApplicationRemoteModel() {
		return _oAuthApplicationRemoteModel;
	}

	public void setOAuthApplicationRemoteModel(
		BaseModel<?> oAuthApplicationRemoteModel) {
		_oAuthApplicationRemoteModel = oAuthApplicationRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			OAuthApplicationLocalServiceUtil.addOAuthApplication(this);
		}
		else {
			OAuthApplicationLocalServiceUtil.updateOAuthApplication(this);
		}
	}

	@Override
	public OAuthApplication toEscapedModel() {
		return (OAuthApplication)Proxy.newProxyInstance(OAuthApplication.class.getClassLoader(),
			new Class[] { OAuthApplication.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthApplicationClp clone = new OAuthApplicationClp();

		clone.setApplicationId(getApplicationId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setOwnerId(getOwnerId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setWebsite(getWebsite());
		clone.setConsumerKey(getConsumerKey());
		clone.setConsumerSecret(getConsumerSecret());
		clone.setCallBackURL(getCallBackURL());
		clone.setAccessLevel(getAccessLevel());

		return clone;
	}

	public int compareTo(OAuthApplication oAuthApplication) {
		long primaryKey = oAuthApplication.getPrimaryKey();

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

		OAuthApplicationClp oAuthApplication = null;

		try {
			oAuthApplication = (OAuthApplicationClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = oAuthApplication.getPrimaryKey();

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

		sb.append("{applicationId=");
		sb.append(getApplicationId());
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
		sb.append(", ownerId=");
		sb.append(getOwnerId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", website=");
		sb.append(getWebsite());
		sb.append(", consumerKey=");
		sb.append(getConsumerKey());
		sb.append(", consumerSecret=");
		sb.append(getConsumerSecret());
		sb.append(", callBackURL=");
		sb.append(getCallBackURL());
		sb.append(", accessLevel=");
		sb.append(getAccessLevel());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.oauth.model.OAuthApplication");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>applicationId</column-name><column-value><![CDATA[");
		sb.append(getApplicationId());
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
			"<column><column-name>ownerId</column-name><column-value><![CDATA[");
		sb.append(getOwnerId());
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
			"<column><column-name>website</column-name><column-value><![CDATA[");
		sb.append(getWebsite());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerKey</column-name><column-value><![CDATA[");
		sb.append(getConsumerKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerSecret</column-name><column-value><![CDATA[");
		sb.append(getConsumerSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>callBackURL</column-name><column-value><![CDATA[");
		sb.append(getCallBackURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessLevel</column-name><column-value><![CDATA[");
		sb.append(getAccessLevel());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _applicationId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _ownerId;
	private String _name;
	private String _description;
	private String _website;
	private String _consumerKey;
	private String _consumerSecret;
	private String _callBackURL;
	private int _accessLevel;
	private BaseModel<?> _oAuthApplicationRemoteModel;
}