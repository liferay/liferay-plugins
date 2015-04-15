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

import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.ClpSerializer;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Eduardo Lundgren
 */
@ProviderType
public class CalendarClp extends BaseModelImpl<Calendar> implements Calendar {
	public CalendarClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Calendar.class;
	}

	@Override
	public String getModelClassName() {
		return Calendar.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _calendarId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCalendarId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _calendarId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("calendarId", getCalendarId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("resourceBlockId", getResourceBlockId());
		attributes.put("calendarResourceId", getCalendarResourceId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("color", getColor());
		attributes.put("defaultCalendar", getDefaultCalendar());
		attributes.put("enableComments", getEnableComments());
		attributes.put("enableRatings", getEnableRatings());

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

		Long calendarId = (Long)attributes.get("calendarId");

		if (calendarId != null) {
			setCalendarId(calendarId);
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

		Long calendarResourceId = (Long)attributes.get("calendarResourceId");

		if (calendarResourceId != null) {
			setCalendarResourceId(calendarResourceId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String timeZoneId = (String)attributes.get("timeZoneId");

		if (timeZoneId != null) {
			setTimeZoneId(timeZoneId);
		}

		Integer color = (Integer)attributes.get("color");

		if (color != null) {
			setColor(color);
		}

		Boolean defaultCalendar = (Boolean)attributes.get("defaultCalendar");

		if (defaultCalendar != null) {
			setDefaultCalendar(defaultCalendar);
		}

		Boolean enableComments = (Boolean)attributes.get("enableComments");

		if (enableComments != null) {
			setEnableComments(enableComments);
		}

		Boolean enableRatings = (Boolean)attributes.get("enableRatings");

		if (enableRatings != null) {
			setEnableRatings(enableRatings);
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

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_calendarRemoteModel, uuid);
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

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarId", long.class);

				method.invoke(_calendarRemoteModel, calendarId);
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

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_calendarRemoteModel, groupId);
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

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_calendarRemoteModel, companyId);
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

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_calendarRemoteModel, userId);
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

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_calendarRemoteModel, userName);
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

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_calendarRemoteModel, createDate);
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

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_calendarRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getResourceBlockId() {
		return _resourceBlockId;
	}

	@Override
	public void setResourceBlockId(long resourceBlockId) {
		_resourceBlockId = resourceBlockId;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setResourceBlockId", long.class);

				method.invoke(_calendarRemoteModel, resourceBlockId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	@Override
	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarResourceId",
						long.class);

				method.invoke(_calendarRemoteModel, calendarResourceId);
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
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_calendarRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	@Override
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

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	@Override
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

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_calendarRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	@Override
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

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	@Override
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

	@Override
	public String getTimeZoneId() {
		return _timeZoneId;
	}

	@Override
	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setTimeZoneId", String.class);

				method.invoke(_calendarRemoteModel, timeZoneId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getColor() {
		return _color;
	}

	@Override
	public void setColor(int color) {
		_color = color;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setColor", int.class);

				method.invoke(_calendarRemoteModel, color);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getDefaultCalendar() {
		return _defaultCalendar;
	}

	@Override
	public boolean isDefaultCalendar() {
		return _defaultCalendar;
	}

	@Override
	public void setDefaultCalendar(boolean defaultCalendar) {
		_defaultCalendar = defaultCalendar;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setDefaultCalendar",
						boolean.class);

				method.invoke(_calendarRemoteModel, defaultCalendar);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getEnableComments() {
		return _enableComments;
	}

	@Override
	public boolean isEnableComments() {
		return _enableComments;
	}

	@Override
	public void setEnableComments(boolean enableComments) {
		_enableComments = enableComments;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setEnableComments",
						boolean.class);

				method.invoke(_calendarRemoteModel, enableComments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getEnableRatings() {
		return _enableRatings;
	}

	@Override
	public boolean isEnableRatings() {
		return _enableRatings;
	}

	@Override
	public void setEnableRatings(boolean enableRatings) {
		_enableRatings = enableRatings;

		if (_calendarRemoteModel != null) {
			try {
				Class<?> clazz = _calendarRemoteModel.getClass();

				Method method = clazz.getMethod("setEnableRatings",
						boolean.class);

				method.invoke(_calendarRemoteModel, enableRatings);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.util.TimeZone getTimeZone() {
		try {
			String methodName = "getTimeZone";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.TimeZone returnObj = (java.util.TimeZone)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.calendar.model.CalendarResource getCalendarResource() {
		try {
			String methodName = "getCalendarResource";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.calendar.model.CalendarResource returnObj = (com.liferay.calendar.model.CalendarResource)invokeOnRemoteModel(methodName,
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
				Calendar.class.getName()));
	}

	public BaseModel<?> getCalendarRemoteModel() {
		return _calendarRemoteModel;
	}

	public void setCalendarRemoteModel(BaseModel<?> calendarRemoteModel) {
		_calendarRemoteModel = calendarRemoteModel;
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

		Class<?> remoteModelClass = _calendarRemoteModel.getClass();

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

		Object returnValue = method.invoke(_calendarRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			CalendarLocalServiceUtil.addCalendar(this);
		}
		else {
			CalendarLocalServiceUtil.updateCalendar(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		prepareLocalizedFieldsForImport(null);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(getDescription(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public Calendar toEscapedModel() {
		return (Calendar)ProxyUtil.newProxyInstance(Calendar.class.getClassLoader(),
			new Class[] { Calendar.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CalendarClp clone = new CalendarClp();

		clone.setUuid(getUuid());
		clone.setCalendarId(getCalendarId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setResourceBlockId(getResourceBlockId());
		clone.setCalendarResourceId(getCalendarResourceId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setTimeZoneId(getTimeZoneId());
		clone.setColor(getColor());
		clone.setDefaultCalendar(getDefaultCalendar());
		clone.setEnableComments(getEnableComments());
		clone.setEnableRatings(getEnableRatings());

		return clone;
	}

	@Override
	public int compareTo(Calendar calendar) {
		int value = 0;

		value = getName().compareTo(calendar.getName());

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

		if (!(obj instanceof CalendarClp)) {
			return false;
		}

		CalendarClp calendar = (CalendarClp)obj;

		long primaryKey = calendar.getPrimaryKey();

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

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", calendarId=");
		sb.append(getCalendarId());
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
		sb.append(", calendarResourceId=");
		sb.append(getCalendarResourceId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", timeZoneId=");
		sb.append(getTimeZoneId());
		sb.append(", color=");
		sb.append(getColor());
		sb.append(", defaultCalendar=");
		sb.append(getDefaultCalendar());
		sb.append(", enableComments=");
		sb.append(getEnableComments());
		sb.append(", enableRatings=");
		sb.append(getEnableRatings());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.Calendar");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarId</column-name><column-value><![CDATA[");
		sb.append(getCalendarId());
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
			"<column><column-name>calendarResourceId</column-name><column-value><![CDATA[");
		sb.append(getCalendarResourceId());
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
			"<column><column-name>timeZoneId</column-name><column-value><![CDATA[");
		sb.append(getTimeZoneId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>color</column-name><column-value><![CDATA[");
		sb.append(getColor());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultCalendar</column-name><column-value><![CDATA[");
		sb.append(getDefaultCalendar());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enableComments</column-name><column-value><![CDATA[");
		sb.append(getEnableComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enableRatings</column-name><column-value><![CDATA[");
		sb.append(getEnableRatings());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _calendarId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _resourceBlockId;
	private long _calendarResourceId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _timeZoneId;
	private int _color;
	private boolean _defaultCalendar;
	private boolean _enableComments;
	private boolean _enableRatings;
	private BaseModel<?> _calendarRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}