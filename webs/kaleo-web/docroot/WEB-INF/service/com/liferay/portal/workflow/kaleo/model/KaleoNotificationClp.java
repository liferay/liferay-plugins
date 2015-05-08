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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.workflow.kaleo.service.ClpSerializer;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoNotificationClp extends BaseModelImpl<KaleoNotification>
	implements KaleoNotification {
	public KaleoNotificationClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoNotification.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoNotification.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoNotificationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoNotificationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoNotificationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoNotificationId", getKaleoNotificationId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeName", getKaleoNodeName());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("executionType", getExecutionType());
		attributes.put("template", getTemplate());
		attributes.put("templateLanguage", getTemplateLanguage());
		attributes.put("notificationTypes", getNotificationTypes());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoNotificationId = (Long)attributes.get("kaleoNotificationId");

		if (kaleoNotificationId != null) {
			setKaleoNotificationId(kaleoNotificationId);
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

		String kaleoClassName = (String)attributes.get("kaleoClassName");

		if (kaleoClassName != null) {
			setKaleoClassName(kaleoClassName);
		}

		Long kaleoClassPK = (Long)attributes.get("kaleoClassPK");

		if (kaleoClassPK != null) {
			setKaleoClassPK(kaleoClassPK);
		}

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		String kaleoNodeName = (String)attributes.get("kaleoNodeName");

		if (kaleoNodeName != null) {
			setKaleoNodeName(kaleoNodeName);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String executionType = (String)attributes.get("executionType");

		if (executionType != null) {
			setExecutionType(executionType);
		}

		String template = (String)attributes.get("template");

		if (template != null) {
			setTemplate(template);
		}

		String templateLanguage = (String)attributes.get("templateLanguage");

		if (templateLanguage != null) {
			setTemplateLanguage(templateLanguage);
		}

		String notificationTypes = (String)attributes.get("notificationTypes");

		if (notificationTypes != null) {
			setNotificationTypes(notificationTypes);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoNotificationId() {
		return _kaleoNotificationId;
	}

	@Override
	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationId = kaleoNotificationId;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoNotificationId",
						long.class);

				method.invoke(_kaleoNotificationRemoteModel, kaleoNotificationId);
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

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoNotificationRemoteModel, groupId);
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

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoNotificationRemoteModel, companyId);
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

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoNotificationRemoteModel, userId);
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

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoNotificationRemoteModel, userName);
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

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoNotificationRemoteModel, createDate);
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

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoNotificationRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoClassName() {
		return _kaleoClassName;
	}

	@Override
	public void setKaleoClassName(String kaleoClassName) {
		_kaleoClassName = kaleoClassName;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoClassName",
						String.class);

				method.invoke(_kaleoNotificationRemoteModel, kaleoClassName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoClassPK = kaleoClassPK;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoClassPK", long.class);

				method.invoke(_kaleoNotificationRemoteModel, kaleoClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoNotificationRemoteModel, kaleoDefinitionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoNodeName() {
		return _kaleoNodeName;
	}

	@Override
	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoNodeName", String.class);

				method.invoke(_kaleoNotificationRemoteModel, kaleoNodeName);
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

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_kaleoNotificationRemoteModel, name);
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

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_kaleoNotificationRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExecutionType() {
		return _executionType;
	}

	@Override
	public void setExecutionType(String executionType) {
		_executionType = executionType;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setExecutionType", String.class);

				method.invoke(_kaleoNotificationRemoteModel, executionType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTemplate() {
		return _template;
	}

	@Override
	public void setTemplate(String template) {
		_template = template;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setTemplate", String.class);

				method.invoke(_kaleoNotificationRemoteModel, template);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTemplateLanguage() {
		return _templateLanguage;
	}

	@Override
	public void setTemplateLanguage(String templateLanguage) {
		_templateLanguage = templateLanguage;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setTemplateLanguage",
						String.class);

				method.invoke(_kaleoNotificationRemoteModel, templateLanguage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNotificationTypes() {
		return _notificationTypes;
	}

	@Override
	public void setNotificationTypes(String notificationTypes) {
		_notificationTypes = notificationTypes;

		if (_kaleoNotificationRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRemoteModel.getClass();

				Method method = clazz.getMethod("setNotificationTypes",
						String.class);

				method.invoke(_kaleoNotificationRemoteModel, notificationTypes);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getKaleoNotificationRemoteModel() {
		return _kaleoNotificationRemoteModel;
	}

	public void setKaleoNotificationRemoteModel(
		BaseModel<?> kaleoNotificationRemoteModel) {
		_kaleoNotificationRemoteModel = kaleoNotificationRemoteModel;
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

		Class<?> remoteModelClass = _kaleoNotificationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoNotificationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoNotificationLocalServiceUtil.addKaleoNotification(this);
		}
		else {
			KaleoNotificationLocalServiceUtil.updateKaleoNotification(this);
		}
	}

	@Override
	public KaleoNotification toEscapedModel() {
		return (KaleoNotification)ProxyUtil.newProxyInstance(KaleoNotification.class.getClassLoader(),
			new Class[] { KaleoNotification.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoNotificationClp clone = new KaleoNotificationClp();

		clone.setKaleoNotificationId(getKaleoNotificationId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoClassName(getKaleoClassName());
		clone.setKaleoClassPK(getKaleoClassPK());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoNodeName(getKaleoNodeName());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setExecutionType(getExecutionType());
		clone.setTemplate(getTemplate());
		clone.setTemplateLanguage(getTemplateLanguage());
		clone.setNotificationTypes(getNotificationTypes());

		return clone;
	}

	@Override
	public int compareTo(KaleoNotification kaleoNotification) {
		int value = 0;

		if (getKaleoNotificationId() < kaleoNotification.getKaleoNotificationId()) {
			value = -1;
		}
		else if (getKaleoNotificationId() > kaleoNotification.getKaleoNotificationId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof KaleoNotificationClp)) {
			return false;
		}

		KaleoNotificationClp kaleoNotification = (KaleoNotificationClp)obj;

		long primaryKey = kaleoNotification.getPrimaryKey();

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
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoNotificationId=");
		sb.append(getKaleoNotificationId());
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
		sb.append(", kaleoClassName=");
		sb.append(getKaleoClassName());
		sb.append(", kaleoClassPK=");
		sb.append(getKaleoClassPK());
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoNodeName=");
		sb.append(getKaleoNodeName());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", executionType=");
		sb.append(getExecutionType());
		sb.append(", template=");
		sb.append(getTemplate());
		sb.append(", templateLanguage=");
		sb.append(getTemplateLanguage());
		sb.append(", notificationTypes=");
		sb.append(getNotificationTypes());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoNotification");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoNotificationId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNotificationId());
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
			"<column><column-name>kaleoClassName</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoClassPK</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeName());
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
			"<column><column-name>executionType</column-name><column-value><![CDATA[");
		sb.append(getExecutionType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>template</column-name><column-value><![CDATA[");
		sb.append(getTemplate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>templateLanguage</column-name><column-value><![CDATA[");
		sb.append(getTemplateLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationTypes</column-name><column-value><![CDATA[");
		sb.append(getNotificationTypes());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoNotificationId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private String _kaleoNodeName;
	private String _name;
	private String _description;
	private String _executionType;
	private String _template;
	private String _templateLanguage;
	private String _notificationTypes;
	private BaseModel<?> _kaleoNotificationRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}