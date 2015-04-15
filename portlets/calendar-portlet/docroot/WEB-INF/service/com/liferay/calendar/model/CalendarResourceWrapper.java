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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CalendarResource}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarResource
 * @generated
 */
@ProviderType
public class CalendarResourceWrapper implements CalendarResource,
	ModelWrapper<CalendarResource> {
	public CalendarResourceWrapper(CalendarResource calendarResource) {
		_calendarResource = calendarResource;
	}

	@Override
	public Class<?> getModelClass() {
		return CalendarResource.class;
	}

	@Override
	public String getModelClassName() {
		return CalendarResource.class.getName();
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

	@Override
	public java.lang.Object clone() {
		return new CalendarResourceWrapper((CalendarResource)_calendarResource.clone());
	}

	@Override
	public int compareTo(
		com.liferay.calendar.model.CalendarResource calendarResource) {
		return _calendarResource.compareTo(calendarResource);
	}

	/**
	* Returns the active of this calendar resource.
	*
	* @return the active of this calendar resource
	*/
	@Override
	public boolean getActive() {
		return _calendarResource.getActive();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _calendarResource.getAvailableLanguageIds();
	}

	/**
	* Returns the calendar resource ID of this calendar resource.
	*
	* @return the calendar resource ID of this calendar resource
	*/
	@Override
	public long getCalendarResourceId() {
		return _calendarResource.getCalendarResourceId();
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendars() {
		return _calendarResource.getCalendars();
	}

	/**
	* Returns the fully qualified class name of this calendar resource.
	*
	* @return the fully qualified class name of this calendar resource
	*/
	@Override
	public java.lang.String getClassName() {
		return _calendarResource.getClassName();
	}

	/**
	* Returns the class name ID of this calendar resource.
	*
	* @return the class name ID of this calendar resource
	*/
	@Override
	public long getClassNameId() {
		return _calendarResource.getClassNameId();
	}

	/**
	* Returns the class p k of this calendar resource.
	*
	* @return the class p k of this calendar resource
	*/
	@Override
	public long getClassPK() {
		return _calendarResource.getClassPK();
	}

	/**
	* Returns the class uuid of this calendar resource.
	*
	* @return the class uuid of this calendar resource
	*/
	@Override
	public java.lang.String getClassUuid() {
		return _calendarResource.getClassUuid();
	}

	/**
	* Returns the code of this calendar resource.
	*
	* @return the code of this calendar resource
	*/
	@Override
	public java.lang.String getCode() {
		return _calendarResource.getCode();
	}

	/**
	* Returns the company ID of this calendar resource.
	*
	* @return the company ID of this calendar resource
	*/
	@Override
	public long getCompanyId() {
		return _calendarResource.getCompanyId();
	}

	/**
	* Returns the create date of this calendar resource.
	*
	* @return the create date of this calendar resource
	*/
	@Override
	public Date getCreateDate() {
		return _calendarResource.getCreateDate();
	}

	@Override
	public com.liferay.calendar.model.Calendar getDefaultCalendar() {
		return _calendarResource.getDefaultCalendar();
	}

	@Override
	public long getDefaultCalendarId() {
		return _calendarResource.getDefaultCalendarId();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _calendarResource.getDefaultLanguageId();
	}

	/**
	* Returns the description of this calendar resource.
	*
	* @return the description of this calendar resource
	*/
	@Override
	public java.lang.String getDescription() {
		return _calendarResource.getDescription();
	}

	/**
	* Returns the localized description of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this calendar resource
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _calendarResource.getDescription(languageId);
	}

	/**
	* Returns the localized description of this calendar resource in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar resource
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _calendarResource.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this calendar resource
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _calendarResource.getDescription(locale);
	}

	/**
	* Returns the localized description of this calendar resource in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar resource. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _calendarResource.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _calendarResource.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _calendarResource.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this calendar resource.
	*
	* @return the locales and localized descriptions of this calendar resource
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _calendarResource.getDescriptionMap();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _calendarResource.getExpandoBridge();
	}

	/**
	* Returns the group ID of this calendar resource.
	*
	* @return the group ID of this calendar resource
	*/
	@Override
	public long getGroupId() {
		return _calendarResource.getGroupId();
	}

	/**
	* Returns the modified date of this calendar resource.
	*
	* @return the modified date of this calendar resource
	*/
	@Override
	public Date getModifiedDate() {
		return _calendarResource.getModifiedDate();
	}

	/**
	* Returns the name of this calendar resource.
	*
	* @return the name of this calendar resource
	*/
	@Override
	public java.lang.String getName() {
		return _calendarResource.getName();
	}

	/**
	* Returns the localized name of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this calendar resource
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _calendarResource.getName(languageId);
	}

	/**
	* Returns the localized name of this calendar resource in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this calendar resource
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _calendarResource.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this calendar resource
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _calendarResource.getName(locale);
	}

	/**
	* Returns the localized name of this calendar resource in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this calendar resource. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _calendarResource.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _calendarResource.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _calendarResource.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this calendar resource.
	*
	* @return the locales and localized names of this calendar resource
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _calendarResource.getNameMap();
	}

	/**
	* Returns the primary key of this calendar resource.
	*
	* @return the primary key of this calendar resource
	*/
	@Override
	public long getPrimaryKey() {
		return _calendarResource.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _calendarResource.getPrimaryKeyObj();
	}

	/**
	* Returns the resource block ID of this calendar resource.
	*
	* @return the resource block ID of this calendar resource
	*/
	@Override
	public long getResourceBlockId() {
		return _calendarResource.getResourceBlockId();
	}

	@Override
	public java.util.TimeZone getTimeZone()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResource.getTimeZone();
	}

	@Override
	public java.lang.String getTimeZoneId()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarResource.getTimeZoneId();
	}

	/**
	* Returns the user ID of this calendar resource.
	*
	* @return the user ID of this calendar resource
	*/
	@Override
	public long getUserId() {
		return _calendarResource.getUserId();
	}

	/**
	* Returns the user name of this calendar resource.
	*
	* @return the user name of this calendar resource
	*/
	@Override
	public java.lang.String getUserName() {
		return _calendarResource.getUserName();
	}

	/**
	* Returns the user uuid of this calendar resource.
	*
	* @return the user uuid of this calendar resource
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _calendarResource.getUserUuid();
	}

	/**
	* Returns the uuid of this calendar resource.
	*
	* @return the uuid of this calendar resource
	*/
	@Override
	public java.lang.String getUuid() {
		return _calendarResource.getUuid();
	}

	@Override
	public int hashCode() {
		return _calendarResource.hashCode();
	}

	/**
	* Returns <code>true</code> if this calendar resource is active.
	*
	* @return <code>true</code> if this calendar resource is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _calendarResource.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _calendarResource.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _calendarResource.isEscapedModel();
	}

	@Override
	public boolean isGroup() {
		return _calendarResource.isGroup();
	}

	@Override
	public boolean isNew() {
		return _calendarResource.isNew();
	}

	@Override
	public boolean isUser() {
		return _calendarResource.isUser();
	}

	@Override
	public void persist() {
		_calendarResource.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_calendarResource.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_calendarResource.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets whether this calendar resource is active.
	*
	* @param active the active of this calendar resource
	*/
	@Override
	public void setActive(boolean active) {
		_calendarResource.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_calendarResource.setCachedModel(cachedModel);
	}

	/**
	* Sets the calendar resource ID of this calendar resource.
	*
	* @param calendarResourceId the calendar resource ID of this calendar resource
	*/
	@Override
	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResource.setCalendarResourceId(calendarResourceId);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_calendarResource.setClassName(className);
	}

	/**
	* Sets the class name ID of this calendar resource.
	*
	* @param classNameId the class name ID of this calendar resource
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_calendarResource.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this calendar resource.
	*
	* @param classPK the class p k of this calendar resource
	*/
	@Override
	public void setClassPK(long classPK) {
		_calendarResource.setClassPK(classPK);
	}

	/**
	* Sets the class uuid of this calendar resource.
	*
	* @param classUuid the class uuid of this calendar resource
	*/
	@Override
	public void setClassUuid(java.lang.String classUuid) {
		_calendarResource.setClassUuid(classUuid);
	}

	/**
	* Sets the code of this calendar resource.
	*
	* @param code the code of this calendar resource
	*/
	@Override
	public void setCode(java.lang.String code) {
		_calendarResource.setCode(code);
	}

	/**
	* Sets the company ID of this calendar resource.
	*
	* @param companyId the company ID of this calendar resource
	*/
	@Override
	public void setCompanyId(long companyId) {
		_calendarResource.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this calendar resource.
	*
	* @param createDate the create date of this calendar resource
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_calendarResource.setCreateDate(createDate);
	}

	/**
	* Sets the description of this calendar resource.
	*
	* @param description the description of this calendar resource
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_calendarResource.setDescription(description);
	}

	/**
	* Sets the localized description of this calendar resource in the language.
	*
	* @param description the localized description of this calendar resource
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_calendarResource.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this calendar resource in the language, and sets the default locale.
	*
	* @param description the localized description of this calendar resource
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_calendarResource.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_calendarResource.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this calendar resource from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this calendar resource
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_calendarResource.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this calendar resource from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this calendar resource
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_calendarResource.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_calendarResource.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_calendarResource.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_calendarResource.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this calendar resource.
	*
	* @param groupId the group ID of this calendar resource
	*/
	@Override
	public void setGroupId(long groupId) {
		_calendarResource.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this calendar resource.
	*
	* @param modifiedDate the modified date of this calendar resource
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_calendarResource.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this calendar resource.
	*
	* @param name the name of this calendar resource
	*/
	@Override
	public void setName(java.lang.String name) {
		_calendarResource.setName(name);
	}

	/**
	* Sets the localized name of this calendar resource in the language.
	*
	* @param name the localized name of this calendar resource
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_calendarResource.setName(name, locale);
	}

	/**
	* Sets the localized name of this calendar resource in the language, and sets the default locale.
	*
	* @param name the localized name of this calendar resource
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_calendarResource.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_calendarResource.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this calendar resource from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this calendar resource
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_calendarResource.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this calendar resource from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this calendar resource
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_calendarResource.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_calendarResource.setNew(n);
	}

	/**
	* Sets the primary key of this calendar resource.
	*
	* @param primaryKey the primary key of this calendar resource
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_calendarResource.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_calendarResource.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the resource block ID of this calendar resource.
	*
	* @param resourceBlockId the resource block ID of this calendar resource
	*/
	@Override
	public void setResourceBlockId(long resourceBlockId) {
		_calendarResource.setResourceBlockId(resourceBlockId);
	}

	/**
	* Sets the user ID of this calendar resource.
	*
	* @param userId the user ID of this calendar resource
	*/
	@Override
	public void setUserId(long userId) {
		_calendarResource.setUserId(userId);
	}

	/**
	* Sets the user name of this calendar resource.
	*
	* @param userName the user name of this calendar resource
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_calendarResource.setUserName(userName);
	}

	/**
	* Sets the user uuid of this calendar resource.
	*
	* @param userUuid the user uuid of this calendar resource
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_calendarResource.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this calendar resource.
	*
	* @param uuid the uuid of this calendar resource
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_calendarResource.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.calendar.model.CalendarResource> toCacheModel() {
		return _calendarResource.toCacheModel();
	}

	@Override
	public com.liferay.calendar.model.CalendarResource toEscapedModel() {
		return new CalendarResourceWrapper(_calendarResource.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _calendarResource.toString();
	}

	@Override
	public com.liferay.calendar.model.CalendarResource toUnescapedModel() {
		return new CalendarResourceWrapper(_calendarResource.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _calendarResource.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarResourceWrapper)) {
			return false;
		}

		CalendarResourceWrapper calendarResourceWrapper = (CalendarResourceWrapper)obj;

		if (Validator.equals(_calendarResource,
					calendarResourceWrapper._calendarResource)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _calendarResource.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public CalendarResource getWrappedCalendarResource() {
		return _calendarResource;
	}

	@Override
	public CalendarResource getWrappedModel() {
		return _calendarResource;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _calendarResource.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _calendarResource.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_calendarResource.resetOriginalValues();
	}

	private final CalendarResource _calendarResource;
}