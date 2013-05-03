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

package com.liferay.calendar.model;

import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.ClpSerializer;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 */
public class CalendarResourceClp extends BaseModelImpl<CalendarResource>
	implements CalendarResource {
	public CalendarResourceClp() {
	}

	public Class<?> getModelClass() {
		return CalendarResource.class;
	}

	public String getModelClassName() {
		return CalendarResource.class.getName();
	}

	public long getPrimaryKey() {
		return _calendarResourceId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCalendarResourceId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _calendarResourceId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("calendarResourceId", getCalendarResourceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("resourceBlockId", getResourceBlockId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("classUuid", getClassUuid());
		attributes.put("code", getCode());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("active", getActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long calendarResourceId = (Long)attributes.get("calendarResourceId");

		if (calendarResourceId != null) {
			setCalendarResourceId(calendarResourceId);
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

		Long resourceBlockId = (Long)attributes.get("resourceBlockId");

		if (resourceBlockId != null) {
			setResourceBlockId(resourceBlockId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String classUuid = (String)attributes.get("classUuid");

		if (classUuid != null) {
			setClassUuid(classUuid);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_calendarResourceRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarResourceId",
						long.class);

				method.invoke(_calendarResourceRemoteModel, calendarResourceId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_calendarResourceRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_calendarResourceRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_calendarResourceRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
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

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_calendarResourceRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_calendarResourceRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_calendarResourceRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getResourceBlockId() {
		return _resourceBlockId;
	}

	public void setResourceBlockId(long resourceBlockId) {
		_resourceBlockId = resourceBlockId;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setResourceBlockId", long.class);

				method.invoke(_calendarResourceRemoteModel, resourceBlockId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_calendarResourceRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_calendarResourceRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassUuid() {
		return _classUuid;
	}

	public void setClassUuid(String classUuid) {
		_classUuid = classUuid;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setClassUuid", String.class);

				method.invoke(_calendarResourceRemoteModel, classUuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setCode", String.class);

				method.invoke(_calendarResourceRemoteModel, code);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getName() {
		return _name;
	}

	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	public void setName(String name) {
		_name = name;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_calendarResourceRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setName(LocalizationUtil.updateLocalization(nameMap, getName(),
					"Name", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public String getDescription() {
		return _description;
	}

	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	public void setDescription(String description) {
		_description = description;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_calendarResourceRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setDescription(LocalizationUtil.updateLocalization(descriptionMap,
					getDescription(), "Description",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;

		if (_calendarResourceRemoteModel != null) {
			try {
				Class<?> clazz = _calendarResourceRemoteModel.getClass();

				Method method = clazz.getMethod("setActive", boolean.class);

				method.invoke(_calendarResourceRemoteModel, active);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public com.liferay.calendar.model.Calendar getDefaultCalendar() {
		try {
			String methodName = "getDefaultCalendar";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.calendar.model.Calendar returnObj = (com.liferay.calendar.model.Calendar)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isUser() {
		try {
			String methodName = "isUser";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isGroup() {
		try {
			String methodName = "isGroup";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isGlobal() {
		try {
			String methodName = "isGlobal";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public long getDefaultCalendarId() {
		try {
			String methodName = "getDefaultCalendarId";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.calendar.model.Calendar> getCalendars() {
		try {
			String methodName = "getCalendars";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.calendar.model.Calendar> returnObj = (java.util.List<com.liferay.calendar.model.Calendar>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getCalendarResourceRemoteModel() {
		return _calendarResourceRemoteModel;
	}

	public void setCalendarResourceRemoteModel(
		BaseModel<?> calendarResourceRemoteModel) {
		_calendarResourceRemoteModel = calendarResourceRemoteModel;
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

		Class<?> remoteModelClass = _calendarResourceRemoteModel.getClass();

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

		Object returnValue = method.invoke(_calendarResourceRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			CalendarResourceLocalServiceUtil.addCalendarResource(this);
		}
		else {
			CalendarResourceLocalServiceUtil.updateCalendarResource(this);
		}
	}

	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		setName(getName(defaultImportLocale), defaultImportLocale,
			defaultImportLocale);
		setDescription(getDescription(defaultImportLocale),
			defaultImportLocale, defaultImportLocale);
	}

	@Override
	public CalendarResource toEscapedModel() {
		return (CalendarResource)ProxyUtil.newProxyInstance(CalendarResource.class.getClassLoader(),
			new Class[] { CalendarResource.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CalendarResourceClp clone = new CalendarResourceClp();

		clone.setUuid(getUuid());
		clone.setCalendarResourceId(getCalendarResourceId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setResourceBlockId(getResourceBlockId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setClassUuid(getClassUuid());
		clone.setCode(getCode());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setActive(getActive());

		return clone;
	}

	public int compareTo(CalendarResource calendarResource) {
		int value = 0;

		value = getCode().compareToIgnoreCase(calendarResource.getCode());

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

		if (!(obj instanceof CalendarResourceClp)) {
			return false;
		}

		CalendarResourceClp calendarResource = (CalendarResourceClp)obj;

		long primaryKey = calendarResource.getPrimaryKey();

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
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", calendarResourceId=");
		sb.append(getCalendarResourceId());
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
		sb.append(", resourceBlockId=");
		sb.append(getResourceBlockId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", classUuid=");
		sb.append(getClassUuid());
		sb.append(", code=");
		sb.append(getCode());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.CalendarResource");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarResourceId</column-name><column-value><![CDATA[");
		sb.append(getCalendarResourceId());
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
			"<column><column-name>resourceBlockId</column-name><column-value><![CDATA[");
		sb.append(getResourceBlockId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classUuid</column-name><column-value><![CDATA[");
		sb.append(getClassUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>code</column-name><column-value><![CDATA[");
		sb.append(getCode());
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
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _calendarResourceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _resourceBlockId;
	private long _classNameId;
	private long _classPK;
	private String _classUuid;
	private String _code;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private boolean _active;
	private BaseModel<?> _calendarResourceRemoteModel;
}