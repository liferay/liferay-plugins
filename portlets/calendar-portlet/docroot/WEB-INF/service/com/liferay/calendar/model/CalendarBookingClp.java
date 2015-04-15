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

import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.ClpSerializer;

import com.liferay.portal.LocaleException;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.ContainerModel;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.trash.model.TrashEntry;
import com.liferay.portlet.trash.service.TrashEntryLocalServiceUtil;

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
public class CalendarBookingClp extends BaseModelImpl<CalendarBooking>
	implements CalendarBooking {
	public CalendarBookingClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return CalendarBooking.class;
	}

	@Override
	public String getModelClassName() {
		return CalendarBooking.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _calendarBookingId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCalendarBookingId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _calendarBookingId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("calendarBookingId", getCalendarBookingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("resourceBlockId", getResourceBlockId());
		attributes.put("calendarId", getCalendarId());
		attributes.put("calendarResourceId", getCalendarResourceId());
		attributes.put("parentCalendarBookingId", getParentCalendarBookingId());
		attributes.put("vEventUid", getVEventUid());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("location", getLocation());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());
		attributes.put("allDay", getAllDay());
		attributes.put("recurrence", getRecurrence());
		attributes.put("firstReminder", getFirstReminder());
		attributes.put("firstReminderType", getFirstReminderType());
		attributes.put("secondReminder", getSecondReminder());
		attributes.put("secondReminderType", getSecondReminderType());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

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

		Long calendarBookingId = (Long)attributes.get("calendarBookingId");

		if (calendarBookingId != null) {
			setCalendarBookingId(calendarBookingId);
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

		Long calendarId = (Long)attributes.get("calendarId");

		if (calendarId != null) {
			setCalendarId(calendarId);
		}

		Long calendarResourceId = (Long)attributes.get("calendarResourceId");

		if (calendarResourceId != null) {
			setCalendarResourceId(calendarResourceId);
		}

		Long parentCalendarBookingId = (Long)attributes.get(
				"parentCalendarBookingId");

		if (parentCalendarBookingId != null) {
			setParentCalendarBookingId(parentCalendarBookingId);
		}

		String vEventUid = (String)attributes.get("vEventUid");

		if (vEventUid != null) {
			setVEventUid(vEventUid);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String location = (String)attributes.get("location");

		if (location != null) {
			setLocation(location);
		}

		Long startTime = (Long)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Long endTime = (Long)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}

		Boolean allDay = (Boolean)attributes.get("allDay");

		if (allDay != null) {
			setAllDay(allDay);
		}

		String recurrence = (String)attributes.get("recurrence");

		if (recurrence != null) {
			setRecurrence(recurrence);
		}

		Long firstReminder = (Long)attributes.get("firstReminder");

		if (firstReminder != null) {
			setFirstReminder(firstReminder);
		}

		String firstReminderType = (String)attributes.get("firstReminderType");

		if (firstReminderType != null) {
			setFirstReminderType(firstReminderType);
		}

		Long secondReminder = (Long)attributes.get("secondReminder");

		if (secondReminder != null) {
			setSecondReminder(secondReminder);
		}

		String secondReminderType = (String)attributes.get("secondReminderType");

		if (secondReminderType != null) {
			setSecondReminderType(secondReminderType);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_calendarBookingRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarBookingId",
						long.class);

				method.invoke(_calendarBookingRemoteModel, calendarBookingId);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_calendarBookingRemoteModel, groupId);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_calendarBookingRemoteModel, companyId);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_calendarBookingRemoteModel, userId);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_calendarBookingRemoteModel, userName);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_calendarBookingRemoteModel, createDate);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_calendarBookingRemoteModel, modifiedDate);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setResourceBlockId", long.class);

				method.invoke(_calendarBookingRemoteModel, resourceBlockId);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarId", long.class);

				method.invoke(_calendarBookingRemoteModel, calendarId);
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarResourceId",
						long.class);

				method.invoke(_calendarBookingRemoteModel, calendarResourceId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getParentCalendarBookingId() {
		return _parentCalendarBookingId;
	}

	@Override
	public void setParentCalendarBookingId(long parentCalendarBookingId) {
		_parentCalendarBookingId = parentCalendarBookingId;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setParentCalendarBookingId",
						long.class);

				method.invoke(_calendarBookingRemoteModel,
					parentCalendarBookingId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getVEventUid() {
		return _vEventUid;
	}

	@Override
	public void setVEventUid(String vEventUid) {
		_vEventUid = vEventUid;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setVEventUid", String.class);

				method.invoke(_calendarBookingRemoteModel, vEventUid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getTitle(), languageId,
			useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_title = title;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_calendarBookingRemoteModel, title);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(LocalizationUtil.updateLocalization(getTitle(), "Title",
					title, languageId, defaultLanguageId));
		}
		else {
			setTitle(LocalizationUtil.removeLocalization(getTitle(), "Title",
					languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getDefault());
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale) {
		if (titleMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setTitle(LocalizationUtil.updateLocalization(titleMap, getTitle(),
					"Title", LocaleUtil.toLanguageId(defaultLocale)));
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

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_calendarBookingRemoteModel, description);
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
	public String getLocation() {
		return _location;
	}

	@Override
	public void setLocation(String location) {
		_location = location;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setLocation", String.class);

				method.invoke(_calendarBookingRemoteModel, location);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getStartTime() {
		return _startTime;
	}

	@Override
	public void setStartTime(long startTime) {
		_startTime = startTime;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setStartTime", long.class);

				method.invoke(_calendarBookingRemoteModel, startTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getEndTime() {
		return _endTime;
	}

	@Override
	public void setEndTime(long endTime) {
		_endTime = endTime;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setEndTime", long.class);

				method.invoke(_calendarBookingRemoteModel, endTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAllDay() {
		return _allDay;
	}

	@Override
	public boolean isAllDay() {
		return _allDay;
	}

	@Override
	public void setAllDay(boolean allDay) {
		_allDay = allDay;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setAllDay", boolean.class);

				method.invoke(_calendarBookingRemoteModel, allDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRecurrence() {
		return _recurrence;
	}

	@Override
	public void setRecurrence(String recurrence) {
		_recurrence = recurrence;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setRecurrence", String.class);

				method.invoke(_calendarBookingRemoteModel, recurrence);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getFirstReminder() {
		return _firstReminder;
	}

	@Override
	public void setFirstReminder(long firstReminder) {
		_firstReminder = firstReminder;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setFirstReminder", long.class);

				method.invoke(_calendarBookingRemoteModel, firstReminder);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFirstReminderType() {
		return _firstReminderType;
	}

	@Override
	public void setFirstReminderType(String firstReminderType) {
		_firstReminderType = firstReminderType;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setFirstReminderType",
						String.class);

				method.invoke(_calendarBookingRemoteModel, firstReminderType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSecondReminder() {
		return _secondReminder;
	}

	@Override
	public void setSecondReminder(long secondReminder) {
		_secondReminder = secondReminder;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setSecondReminder", long.class);

				method.invoke(_calendarBookingRemoteModel, secondReminder);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSecondReminderType() {
		return _secondReminderType;
	}

	@Override
	public void setSecondReminderType(String secondReminderType) {
		_secondReminderType = secondReminderType;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setSecondReminderType",
						String.class);

				method.invoke(_calendarBookingRemoteModel, secondReminderType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_calendarBookingRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_calendarBookingRemoteModel, statusByUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@Override
	public String getStatusByUserName() {
		return _statusByUserName;
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_calendarBookingRemoteModel, statusByUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;

		if (_calendarBookingRemoteModel != null) {
			try {
				Class<?> clazz = _calendarBookingRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_calendarBookingRemoteModel, statusDate);
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
	public boolean isRecurring() {
		try {
			String methodName = "isRecurring";

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

	@Override
	public com.liferay.calendar.model.CalendarBooking getParentCalendarBooking() {
		try {
			String methodName = "getParentCalendarBooking";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.calendar.model.CalendarBooking returnObj = (com.liferay.calendar.model.CalendarBooking)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getInstanceIndex() {
		try {
			String methodName = "getInstanceIndex";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setInstanceIndex(int instanceIndex) {
		try {
			String methodName = "setInstanceIndex";

			Class<?>[] parameterTypes = new Class<?>[] { int.class };

			Object[] parameterValues = new Object[] { instanceIndex };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.calendar.notification.NotificationType getSecondReminderNotificationType() {
		try {
			String methodName = "getSecondReminderNotificationType";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.calendar.notification.NotificationType returnObj = (com.liferay.calendar.notification.NotificationType)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public long getDuration() {
		try {
			String methodName = "getDuration";

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
	public com.liferay.calendar.model.Calendar getCalendar() {
		try {
			String methodName = "getCalendar";

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

	@Override
	public com.liferay.calendar.recurrence.Recurrence getRecurrenceObj() {
		try {
			String methodName = "getRecurrenceObj";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.calendar.recurrence.Recurrence returnObj = (com.liferay.calendar.recurrence.Recurrence)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings() {
		try {
			String methodName = "getChildCalendarBookings";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.calendar.model.CalendarBooking> returnObj =
				(java.util.List<com.liferay.calendar.model.CalendarBooking>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isMasterBooking() {
		try {
			String methodName = "isMasterBooking";

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

	@Override
	public com.liferay.calendar.notification.NotificationType getFirstReminderNotificationType() {
		try {
			String methodName = "getFirstReminderNotificationType";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.calendar.notification.NotificationType returnObj = (com.liferay.calendar.notification.NotificationType)invokeOnRemoteModel(methodName,
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
				CalendarBooking.class.getName()));
	}

	@Override
	public TrashEntry getTrashEntry() throws PortalException {
		if (!isInTrash()) {
			return null;
		}

		TrashEntry trashEntry = TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return trashEntry;
		}

		TrashHandler trashHandler = getTrashHandler();

		if (!Validator.isNull(trashHandler.getContainerModelClassName())) {
			ContainerModel containerModel = null;

			try {
				containerModel = trashHandler.getParentContainerModel(this);
			}
			catch (NoSuchModelException nsme) {
				return null;
			}

			while (containerModel != null) {
				if (containerModel instanceof TrashedModel) {
					TrashedModel trashedModel = (TrashedModel)containerModel;

					return trashedModel.getTrashEntry();
				}

				trashHandler = TrashHandlerRegistryUtil.getTrashHandler(trashHandler.getContainerModelClassName());

				if (trashHandler == null) {
					return null;
				}

				containerModel = trashHandler.getContainerModel(containerModel.getParentContainerModelId());
			}
		}

		return null;
	}

	@Override
	public long getTrashEntryClassPK() {
		return getPrimaryKey();
	}

	@Override
	public TrashHandler getTrashHandler() {
		return TrashHandlerRegistryUtil.getTrashHandler(getModelClassName());
	}

	@Override
	public boolean isInTrash() {
		if (getStatus() == WorkflowConstants.STATUS_IN_TRASH) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInTrashContainer() {
		TrashHandler trashHandler = getTrashHandler();

		if ((trashHandler == null) ||
				Validator.isNull(trashHandler.getContainerModelClassName())) {
			return false;
		}

		try {
			ContainerModel containerModel = trashHandler.getParentContainerModel(this);

			if (containerModel == null) {
				return false;
			}

			if (containerModel instanceof TrashedModel) {
				return ((TrashedModel)containerModel).isInTrash();
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean isInTrashExplicitly() {
		if (!isInTrash()) {
			return false;
		}

		TrashEntry trashEntry = TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isInTrashImplicitly() {
		if (!isInTrash()) {
			return false;
		}

		TrashEntry trashEntry = TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return false;
		}

		return true;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isApproved}
	 */
	@Deprecated
	@Override
	public boolean getApproved() {
		return isApproved();
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public BaseModel<?> getCalendarBookingRemoteModel() {
		return _calendarBookingRemoteModel;
	}

	public void setCalendarBookingRemoteModel(
		BaseModel<?> calendarBookingRemoteModel) {
		_calendarBookingRemoteModel = calendarBookingRemoteModel;
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

		Class<?> remoteModelClass = _calendarBookingRemoteModel.getClass();

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

		Object returnValue = method.invoke(_calendarBookingRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			CalendarBookingLocalServiceUtil.addCalendarBooking(this);
		}
		else {
			CalendarBookingLocalServiceUtil.updateCalendarBooking(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
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
		String xml = getTitle();

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

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
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
	public CalendarBooking toEscapedModel() {
		return (CalendarBooking)ProxyUtil.newProxyInstance(CalendarBooking.class.getClassLoader(),
			new Class[] { CalendarBooking.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CalendarBookingClp clone = new CalendarBookingClp();

		clone.setUuid(getUuid());
		clone.setCalendarBookingId(getCalendarBookingId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setResourceBlockId(getResourceBlockId());
		clone.setCalendarId(getCalendarId());
		clone.setCalendarResourceId(getCalendarResourceId());
		clone.setParentCalendarBookingId(getParentCalendarBookingId());
		clone.setVEventUid(getVEventUid());
		clone.setTitle(getTitle());
		clone.setDescription(getDescription());
		clone.setLocation(getLocation());
		clone.setStartTime(getStartTime());
		clone.setEndTime(getEndTime());
		clone.setAllDay(getAllDay());
		clone.setRecurrence(getRecurrence());
		clone.setFirstReminder(getFirstReminder());
		clone.setFirstReminderType(getFirstReminderType());
		clone.setSecondReminder(getSecondReminder());
		clone.setSecondReminderType(getSecondReminderType());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());

		return clone;
	}

	@Override
	public int compareTo(CalendarBooking calendarBooking) {
		int value = 0;

		if (getStartTime() < calendarBooking.getStartTime()) {
			value = -1;
		}
		else if (getStartTime() > calendarBooking.getStartTime()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = getTitle().compareToIgnoreCase(calendarBooking.getTitle());

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

		if (!(obj instanceof CalendarBookingClp)) {
			return false;
		}

		CalendarBookingClp calendarBooking = (CalendarBookingClp)obj;

		long primaryKey = calendarBooking.getPrimaryKey();

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
		StringBundler sb = new StringBundler(57);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", calendarBookingId=");
		sb.append(getCalendarBookingId());
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
		sb.append(", calendarId=");
		sb.append(getCalendarId());
		sb.append(", calendarResourceId=");
		sb.append(getCalendarResourceId());
		sb.append(", parentCalendarBookingId=");
		sb.append(getParentCalendarBookingId());
		sb.append(", vEventUid=");
		sb.append(getVEventUid());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", location=");
		sb.append(getLocation());
		sb.append(", startTime=");
		sb.append(getStartTime());
		sb.append(", endTime=");
		sb.append(getEndTime());
		sb.append(", allDay=");
		sb.append(getAllDay());
		sb.append(", recurrence=");
		sb.append(getRecurrence());
		sb.append(", firstReminder=");
		sb.append(getFirstReminder());
		sb.append(", firstReminderType=");
		sb.append(getFirstReminderType());
		sb.append(", secondReminder=");
		sb.append(getSecondReminder());
		sb.append(", secondReminderType=");
		sb.append(getSecondReminderType());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(88);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.CalendarBooking");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarBookingId</column-name><column-value><![CDATA[");
		sb.append(getCalendarBookingId());
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
			"<column><column-name>calendarId</column-name><column-value><![CDATA[");
		sb.append(getCalendarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarResourceId</column-name><column-value><![CDATA[");
		sb.append(getCalendarResourceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentCalendarBookingId</column-name><column-value><![CDATA[");
		sb.append(getParentCalendarBookingId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>vEventUid</column-name><column-value><![CDATA[");
		sb.append(getVEventUid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>location</column-name><column-value><![CDATA[");
		sb.append(getLocation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startTime</column-name><column-value><![CDATA[");
		sb.append(getStartTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endTime</column-name><column-value><![CDATA[");
		sb.append(getEndTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>allDay</column-name><column-value><![CDATA[");
		sb.append(getAllDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recurrence</column-name><column-value><![CDATA[");
		sb.append(getRecurrence());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>firstReminder</column-name><column-value><![CDATA[");
		sb.append(getFirstReminder());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>firstReminderType</column-name><column-value><![CDATA[");
		sb.append(getFirstReminderType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>secondReminder</column-name><column-value><![CDATA[");
		sb.append(getSecondReminder());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>secondReminderType</column-name><column-value><![CDATA[");
		sb.append(getSecondReminderType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _calendarBookingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _resourceBlockId;
	private long _calendarId;
	private long _calendarResourceId;
	private long _parentCalendarBookingId;
	private String _vEventUid;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _location;
	private long _startTime;
	private long _endTime;
	private boolean _allDay;
	private String _recurrence;
	private long _firstReminder;
	private String _firstReminderType;
	private long _secondReminder;
	private String _secondReminderType;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private BaseModel<?> _calendarBookingRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}