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

package com.liferay.calendar.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil;
import com.liferay.calendar.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 */
@ProviderType
public class CalendarNotificationTemplateClp extends BaseModelImpl<CalendarNotificationTemplate>
	implements CalendarNotificationTemplate {
	public CalendarNotificationTemplateClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return CalendarNotificationTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return CalendarNotificationTemplate.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _calendarNotificationTemplateId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCalendarNotificationTemplateId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _calendarNotificationTemplateId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("calendarNotificationTemplateId",
			getCalendarNotificationTemplateId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("calendarId", getCalendarId());
		attributes.put("notificationType", getNotificationType());
		attributes.put("notificationTypeSettings", getNotificationTypeSettings());
		attributes.put("notificationTemplateType", getNotificationTemplateType());
		attributes.put("subject", getSubject());
		attributes.put("body", getBody());

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

		Long calendarNotificationTemplateId = (Long)attributes.get(
				"calendarNotificationTemplateId");

		if (calendarNotificationTemplateId != null) {
			setCalendarNotificationTemplateId(calendarNotificationTemplateId);
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

		Long calendarId = (Long)attributes.get("calendarId");

		if (calendarId != null) {
			setCalendarId(calendarId);
		}

		String notificationType = (String)attributes.get("notificationType");

		if (notificationType != null) {
			setNotificationType(notificationType);
		}

		String notificationTypeSettings = (String)attributes.get(
				"notificationTypeSettings");

		if (notificationTypeSettings != null) {
			setNotificationTypeSettings(notificationTypeSettings);
		}

		String notificationTemplateType = (String)attributes.get(
				"notificationTemplateType");

		if (notificationTemplateType != null) {
			setNotificationTemplateType(notificationTemplateType);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
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

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_calendarNotificationTemplateRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCalendarNotificationTemplateId() {
		return _calendarNotificationTemplateId;
	}

	@Override
	public void setCalendarNotificationTemplateId(
		long calendarNotificationTemplateId) {
		_calendarNotificationTemplateId = calendarNotificationTemplateId;

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarNotificationTemplateId",
						long.class);

				method.invoke(_calendarNotificationTemplateRemoteModel,
					calendarNotificationTemplateId);
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

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_calendarNotificationTemplateRemoteModel, groupId);
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

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_calendarNotificationTemplateRemoteModel,
					companyId);
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

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_calendarNotificationTemplateRemoteModel, userId);
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

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_calendarNotificationTemplateRemoteModel, userName);
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

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_calendarNotificationTemplateRemoteModel,
					createDate);
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

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_calendarNotificationTemplateRemoteModel,
					modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCalendarId() {
		return _calendarId;
	}

	@Override
	public void setCalendarId(long calendarId) {
		_calendarId = calendarId;

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarId", long.class);

				method.invoke(_calendarNotificationTemplateRemoteModel,
					calendarId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNotificationType() {
		return _notificationType;
	}

	@Override
	public void setNotificationType(String notificationType) {
		_notificationType = notificationType;

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setNotificationType",
						String.class);

				method.invoke(_calendarNotificationTemplateRemoteModel,
					notificationType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNotificationTypeSettings() {
		return _notificationTypeSettings;
	}

	@Override
	public void setNotificationTypeSettings(String notificationTypeSettings) {
		_notificationTypeSettings = notificationTypeSettings;

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setNotificationTypeSettings",
						String.class);

				method.invoke(_calendarNotificationTemplateRemoteModel,
					notificationTypeSettings);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNotificationTemplateType() {
		return _notificationTemplateType;
	}

	@Override
	public void setNotificationTemplateType(String notificationTemplateType) {
		_notificationTemplateType = notificationTemplateType;

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setNotificationTemplateType",
						String.class);

				method.invoke(_calendarNotificationTemplateRemoteModel,
					notificationTemplateType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSubject() {
		return _subject;
	}

	@Override
	public void setSubject(String subject) {
		_subject = subject;

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setSubject", String.class);

				method.invoke(_calendarNotificationTemplateRemoteModel, subject);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getBody() {
		return _body;
	}

	@Override
	public void setBody(String body) {
		_body = body;

		if (_calendarNotificationTemplateRemoteModel != null) {
			try {
				Class<?> clazz = _calendarNotificationTemplateRemoteModel.getClass();

				Method method = clazz.getMethod("setBody", String.class);

				method.invoke(_calendarNotificationTemplateRemoteModel, body);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties notificationTypeSettingsProperties) {
		try {
			String methodName = "setTypeSettingsProperties";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.kernel.util.UnicodeProperties.class
				};

			Object[] parameterValues = new Object[] {
					notificationTypeSettingsProperties
				};

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getNotificationTypeSettingsProperties() {
		try {
			String methodName = "getNotificationTypeSettingsProperties";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.kernel.util.UnicodeProperties returnObj = (com.liferay.portal.kernel.util.UnicodeProperties)invokeOnRemoteModel(methodName,
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
				CalendarNotificationTemplate.class.getName()));
	}

	public BaseModel<?> getCalendarNotificationTemplateRemoteModel() {
		return _calendarNotificationTemplateRemoteModel;
	}

	public void setCalendarNotificationTemplateRemoteModel(
		BaseModel<?> calendarNotificationTemplateRemoteModel) {
		_calendarNotificationTemplateRemoteModel = calendarNotificationTemplateRemoteModel;
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

		Class<?> remoteModelClass = _calendarNotificationTemplateRemoteModel.getClass();

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

		Object returnValue = method.invoke(_calendarNotificationTemplateRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			CalendarNotificationTemplateLocalServiceUtil.addCalendarNotificationTemplate(this);
		}
		else {
			CalendarNotificationTemplateLocalServiceUtil.updateCalendarNotificationTemplate(this);
		}
	}

	@Override
	public CalendarNotificationTemplate toEscapedModel() {
		return (CalendarNotificationTemplate)ProxyUtil.newProxyInstance(CalendarNotificationTemplate.class.getClassLoader(),
			new Class[] { CalendarNotificationTemplate.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CalendarNotificationTemplateClp clone = new CalendarNotificationTemplateClp();

		clone.setUuid(getUuid());
		clone.setCalendarNotificationTemplateId(getCalendarNotificationTemplateId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCalendarId(getCalendarId());
		clone.setNotificationType(getNotificationType());
		clone.setNotificationTypeSettings(getNotificationTypeSettings());
		clone.setNotificationTemplateType(getNotificationTemplateType());
		clone.setSubject(getSubject());
		clone.setBody(getBody());

		return clone;
	}

	@Override
	public int compareTo(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		long primaryKey = calendarNotificationTemplate.getPrimaryKey();

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

		if (!(obj instanceof CalendarNotificationTemplateClp)) {
			return false;
		}

		CalendarNotificationTemplateClp calendarNotificationTemplate = (CalendarNotificationTemplateClp)obj;

		long primaryKey = calendarNotificationTemplate.getPrimaryKey();

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
		sb.append(", calendarNotificationTemplateId=");
		sb.append(getCalendarNotificationTemplateId());
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
		sb.append(", calendarId=");
		sb.append(getCalendarId());
		sb.append(", notificationType=");
		sb.append(getNotificationType());
		sb.append(", notificationTypeSettings=");
		sb.append(getNotificationTypeSettings());
		sb.append(", notificationTemplateType=");
		sb.append(getNotificationTemplateType());
		sb.append(", subject=");
		sb.append(getSubject());
		sb.append(", body=");
		sb.append(getBody());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.CalendarNotificationTemplate");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarNotificationTemplateId</column-name><column-value><![CDATA[");
		sb.append(getCalendarNotificationTemplateId());
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
			"<column><column-name>calendarId</column-name><column-value><![CDATA[");
		sb.append(getCalendarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationType</column-name><column-value><![CDATA[");
		sb.append(getNotificationType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationTypeSettings</column-name><column-value><![CDATA[");
		sb.append(getNotificationTypeSettings());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationTemplateType</column-name><column-value><![CDATA[");
		sb.append(getNotificationTemplateType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subject</column-name><column-value><![CDATA[");
		sb.append(getSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>body</column-name><column-value><![CDATA[");
		sb.append(getBody());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _calendarNotificationTemplateId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _calendarId;
	private String _notificationType;
	private String _notificationTypeSettings;
	private String _notificationTemplateType;
	private String _subject;
	private String _body;
	private BaseModel<?> _calendarNotificationTemplateRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}