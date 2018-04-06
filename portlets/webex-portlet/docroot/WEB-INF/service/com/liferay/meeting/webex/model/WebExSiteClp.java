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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.meeting.webex.service.ClpSerializer;
import com.liferay.meeting.webex.service.WebExSiteLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class WebExSiteClp extends BaseModelImpl<WebExSite> implements WebExSite {
	public WebExSiteClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WebExSite.class;
	}

	@Override
	public String getModelClassName() {
		return WebExSite.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _webExSiteId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWebExSiteId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _webExSiteId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("webExSiteId", getWebExSiteId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("apiURL", getApiURL());
		attributes.put("login", getLogin());
		attributes.put("password", getPassword());
		attributes.put("partnerKey", getPartnerKey());
		attributes.put("siteKey", getSiteKey());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long webExSiteId = (Long)attributes.get("webExSiteId");

		if (webExSiteId != null) {
			setWebExSiteId(webExSiteId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String apiURL = (String)attributes.get("apiURL");

		if (apiURL != null) {
			setApiURL(apiURL);
		}

		String login = (String)attributes.get("login");

		if (login != null) {
			setLogin(login);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}

		String partnerKey = (String)attributes.get("partnerKey");

		if (partnerKey != null) {
			setPartnerKey(partnerKey);
		}

		Long siteKey = (Long)attributes.get("siteKey");

		if (siteKey != null) {
			setSiteKey(siteKey);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_webExSiteRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWebExSiteId() {
		return _webExSiteId;
	}

	@Override
	public void setWebExSiteId(long webExSiteId) {
		_webExSiteId = webExSiteId;

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setWebExSiteId", long.class);

				method.invoke(_webExSiteRemoteModel, webExSiteId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_webExSiteRemoteModel, groupId);
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

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_webExSiteRemoteModel, companyId);
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

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_webExSiteRemoteModel, userId);
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

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_webExSiteRemoteModel, userName);
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

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_webExSiteRemoteModel, createDate);
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

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_webExSiteRemoteModel, modifiedDate);
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

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_webExSiteRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getApiURL() {
		return _apiURL;
	}

	@Override
	public void setApiURL(String apiURL) {
		_apiURL = apiURL;

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setApiURL", String.class);

				method.invoke(_webExSiteRemoteModel, apiURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLogin() {
		return _login;
	}

	@Override
	public void setLogin(String login) {
		_login = login;

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setLogin", String.class);

				method.invoke(_webExSiteRemoteModel, login);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPassword() {
		return _password;
	}

	@Override
	public void setPassword(String password) {
		_password = password;

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setPassword", String.class);

				method.invoke(_webExSiteRemoteModel, password);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPartnerKey() {
		return _partnerKey;
	}

	@Override
	public void setPartnerKey(String partnerKey) {
		_partnerKey = partnerKey;

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setPartnerKey", String.class);

				method.invoke(_webExSiteRemoteModel, partnerKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSiteKey() {
		return _siteKey;
	}

	@Override
	public void setSiteKey(long siteKey) {
		_siteKey = siteKey;

		if (_webExSiteRemoteModel != null) {
			try {
				Class<?> clazz = _webExSiteRemoteModel.getClass();

				Method method = clazz.getMethod("setSiteKey", long.class);

				method.invoke(_webExSiteRemoteModel, siteKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccounts() {
		try {
			String methodName = "getWebExAccounts";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.meeting.webex.model.WebExAccount> returnObj =
				(java.util.List<com.liferay.meeting.webex.model.WebExAccount>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				WebExSite.class.getName()));
	}

	public BaseModel<?> getWebExSiteRemoteModel() {
		return _webExSiteRemoteModel;
	}

	public void setWebExSiteRemoteModel(BaseModel<?> webExSiteRemoteModel) {
		_webExSiteRemoteModel = webExSiteRemoteModel;
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

		Class<?> remoteModelClass = _webExSiteRemoteModel.getClass();

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

		Object returnValue = method.invoke(_webExSiteRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WebExSiteLocalServiceUtil.addWebExSite(this);
		}
		else {
			WebExSiteLocalServiceUtil.updateWebExSite(this);
		}
	}

	@Override
	public WebExSite toEscapedModel() {
		return (WebExSite)ProxyUtil.newProxyInstance(WebExSite.class.getClassLoader(),
			new Class[] { WebExSite.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WebExSiteClp clone = new WebExSiteClp();

		clone.setUuid(getUuid());
		clone.setWebExSiteId(getWebExSiteId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setApiURL(getApiURL());
		clone.setLogin(getLogin());
		clone.setPassword(getPassword());
		clone.setPartnerKey(getPartnerKey());
		clone.setSiteKey(getSiteKey());

		return clone;
	}

	@Override
	public int compareTo(WebExSite webExSite) {
		long primaryKey = webExSite.getPrimaryKey();

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

		if (!(obj instanceof WebExSiteClp)) {
			return false;
		}

		WebExSiteClp webExSite = (WebExSiteClp)obj;

		long primaryKey = webExSite.getPrimaryKey();

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

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", webExSiteId=");
		sb.append(getWebExSiteId());
		sb.append(", groupId=");
		sb.append(getGroupId());
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
		sb.append(", name=");
		sb.append(getName());
		sb.append(", apiURL=");
		sb.append(getApiURL());
		sb.append(", login=");
		sb.append(getLogin());
		sb.append(", password=");
		sb.append(getPassword());
		sb.append(", partnerKey=");
		sb.append(getPartnerKey());
		sb.append(", siteKey=");
		sb.append(getSiteKey());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.meeting.webex.model.WebExSite");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>webExSiteId</column-name><column-value><![CDATA[");
		sb.append(getWebExSiteId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>apiURL</column-name><column-value><![CDATA[");
		sb.append(getApiURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>login</column-name><column-value><![CDATA[");
		sb.append(getLogin());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>password</column-name><column-value><![CDATA[");
		sb.append(getPassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>partnerKey</column-name><column-value><![CDATA[");
		sb.append(getPartnerKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>siteKey</column-name><column-value><![CDATA[");
		sb.append(getSiteKey());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
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
	private BaseModel<?> _webExSiteRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.meeting.webex.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}