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

package com.liferay.calendar.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CalendarResource}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarResource
 * @generated
 */
public class CalendarResourceWrapper implements CalendarResource,
	ModelWrapper<CalendarResource> {
	public CalendarResourceWrapper(CalendarResource calendarResource) {
		_calendarResource = calendarResource;
	}

	public Class<?> getModelClass() {
		return CalendarResource.class;
	}

	public String getModelClassName() {
		return CalendarResource.class.getName();
	}

	/**
	* Returns the primary key of this calendar resource.
	*
	* @return the primary key of this calendar resource
	*/
	public long getPrimaryKey() {
		return _calendarResource.getPrimaryKey();
	}

	/**
	* Sets the primary key of this calendar resource.
	*
	* @param primaryKey the primary key of this calendar resource
	*/
	public void setPrimaryKey(long primaryKey) {
		_calendarResource.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this calendar resource.
	*
	* @return the uuid of this calendar resource
	*/
	public java.lang.String getUuid() {
		return _calendarResource.getUuid();
	}

	/**
	* Sets the uuid of this calendar resource.
	*
	* @param uuid the uuid of this calendar resource
	*/
	public void setUuid(java.lang.String uuid) {
		_calendarResource.setUuid(uuid);
	}

	/**
	* Returns the calendar resource ID of this calendar resource.
	*
	* @return the calendar resource ID of this calendar resource
	*/
	public long getCalendarResourceId() {
		return _calendarResource.getCalendarResourceId();
	}

	/**
	* Sets the calendar resource ID of this calendar resource.
	*
	* @param calendarResourceId the calendar resource ID of this calendar resource
	*/
	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResource.setCalendarResourceId(calendarResourceId);
	}

	/**
	* Returns the group ID of this calendar resource.
	*
	* @return the group ID of this calendar resource
	*/
	public long getGroupId() {
		return _calendarResource.getGroupId();
	}

	/**
	* Sets the group ID of this calendar resource.
	*
	* @param groupId the group ID of this calendar resource
	*/
	public void setGroupId(long groupId) {
		_calendarResource.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this calendar resource.
	*
	* @return the company ID of this calendar resource
	*/
	public long getCompanyId() {
		return _calendarResource.getCompanyId();
	}

	/**
	* Sets the company ID of this calendar resource.
	*
	* @param companyId the company ID of this calendar resource
	*/
	public void setCompanyId(long companyId) {
		_calendarResource.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this calendar resource.
	*
	* @return the user ID of this calendar resource
	*/
	public long getUserId() {
		return _calendarResource.getUserId();
	}

	/**
	* Sets the user ID of this calendar resource.
	*
	* @param userId the user ID of this calendar resource
	*/
	public void setUserId(long userId) {
		_calendarResource.setUserId(userId);
	}

	/**
	* Returns the user uuid of this calendar resource.
	*
	* @return the user uuid of this calendar resource
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResource.getUserUuid();
	}

	/**
	* Sets the user uuid of this calendar resource.
	*
	* @param userUuid the user uuid of this calendar resource
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_calendarResource.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this calendar resource.
	*
	* @return the user name of this calendar resource
	*/
	public java.lang.String getUserName() {
		return _calendarResource.getUserName();
	}

	/**
	* Sets the user name of this calendar resource.
	*
	* @param userName the user name of this calendar resource
	*/
	public void setUserName(java.lang.String userName) {
		_calendarResource.setUserName(userName);
	}

	/**
	* Returns the create date of this calendar resource.
	*
	* @return the create date of this calendar resource
	*/
	public java.util.Date getCreateDate() {
		return _calendarResource.getCreateDate();
	}

	/**
	* Sets the create date of this calendar resource.
	*
	* @param createDate the create date of this calendar resource
	*/
	public void setCreateDate(java.util.Date createDate) {
		_calendarResource.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this calendar resource.
	*
	* @return the modified date of this calendar resource
	*/
	public java.util.Date getModifiedDate() {
		return _calendarResource.getModifiedDate();
	}

	/**
	* Sets the modified date of this calendar resource.
	*
	* @param modifiedDate the modified date of this calendar resource
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_calendarResource.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the resource block ID of this calendar resource.
	*
	* @return the resource block ID of this calendar resource
	*/
	public long getResourceBlockId() {
		return _calendarResource.getResourceBlockId();
	}

	/**
	* Sets the resource block ID of this calendar resource.
	*
	* @param resourceBlockId the resource block ID of this calendar resource
	*/
	public void setResourceBlockId(long resourceBlockId) {
		_calendarResource.setResourceBlockId(resourceBlockId);
	}

	/**
	* Returns the fully qualified class name of this calendar resource.
	*
	* @return the fully qualified class name of this calendar resource
	*/
	public java.lang.String getClassName() {
		return _calendarResource.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_calendarResource.setClassName(className);
	}

	/**
	* Returns the class name ID of this calendar resource.
	*
	* @return the class name ID of this calendar resource
	*/
	public long getClassNameId() {
		return _calendarResource.getClassNameId();
	}

	/**
	* Sets the class name ID of this calendar resource.
	*
	* @param classNameId the class name ID of this calendar resource
	*/
	public void setClassNameId(long classNameId) {
		_calendarResource.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this calendar resource.
	*
	* @return the class p k of this calendar resource
	*/
	public long getClassPK() {
		return _calendarResource.getClassPK();
	}

	/**
	* Sets the class p k of this calendar resource.
	*
	* @param classPK the class p k of this calendar resource
	*/
	public void setClassPK(long classPK) {
		_calendarResource.setClassPK(classPK);
	}

	/**
	* Returns the class uuid of this calendar resource.
	*
	* @return the class uuid of this calendar resource
	*/
	public java.lang.String getClassUuid() {
		return _calendarResource.getClassUuid();
	}

	/**
	* Sets the class uuid of this calendar resource.
	*
	* @param classUuid the class uuid of this calendar resource
	*/
	public void setClassUuid(java.lang.String classUuid) {
		_calendarResource.setClassUuid(classUuid);
	}

	/**
	* Returns the default calendar ID of this calendar resource.
	*
	* @return the default calendar ID of this calendar resource
	*/
	public long getDefaultCalendarId() {
		return _calendarResource.getDefaultCalendarId();
	}

	/**
	* Sets the default calendar ID of this calendar resource.
	*
	* @param defaultCalendarId the default calendar ID of this calendar resource
	*/
	public void setDefaultCalendarId(long defaultCalendarId) {
		_calendarResource.setDefaultCalendarId(defaultCalendarId);
	}

	/**
	* Returns the code of this calendar resource.
	*
	* @return the code of this calendar resource
	*/
	public java.lang.String getCode() {
		return _calendarResource.getCode();
	}

	/**
	* Sets the code of this calendar resource.
	*
	* @param code the code of this calendar resource
	*/
	public void setCode(java.lang.String code) {
		_calendarResource.setCode(code);
	}

	/**
	* Returns the name of this calendar resource.
	*
	* @return the name of this calendar resource
	*/
	public java.lang.String getName() {
		return _calendarResource.getName();
	}

	/**
	* Returns the localized name of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this calendar resource
	*/
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
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _calendarResource.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this calendar resource
	*/
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
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _calendarResource.getName(languageId, useDefault);
	}

	public java.lang.String getNameCurrentLanguageId() {
		return _calendarResource.getNameCurrentLanguageId();
	}

	public java.lang.String getNameCurrentValue() {
		return _calendarResource.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this calendar resource.
	*
	* @return the locales and localized names of this calendar resource
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _calendarResource.getNameMap();
	}

	/**
	* Sets the name of this calendar resource.
	*
	* @param name the name of this calendar resource
	*/
	public void setName(java.lang.String name) {
		_calendarResource.setName(name);
	}

	/**
	* Sets the localized name of this calendar resource in the language.
	*
	* @param name the localized name of this calendar resource
	* @param locale the locale of the language
	*/
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
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_calendarResource.setName(name, locale, defaultLocale);
	}

	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_calendarResource.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this calendar resource from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this calendar resource
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_calendarResource.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this calendar resource from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this calendar resource
	* @param defaultLocale the default locale
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_calendarResource.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the description of this calendar resource.
	*
	* @return the description of this calendar resource
	*/
	public java.lang.String getDescription() {
		return _calendarResource.getDescription();
	}

	/**
	* Returns the localized description of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this calendar resource
	*/
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
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _calendarResource.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this calendar resource
	*/
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
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _calendarResource.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _calendarResource.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _calendarResource.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this calendar resource.
	*
	* @return the locales and localized descriptions of this calendar resource
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _calendarResource.getDescriptionMap();
	}

	/**
	* Sets the description of this calendar resource.
	*
	* @param description the description of this calendar resource
	*/
	public void setDescription(java.lang.String description) {
		_calendarResource.setDescription(description);
	}

	/**
	* Sets the localized description of this calendar resource in the language.
	*
	* @param description the localized description of this calendar resource
	* @param locale the locale of the language
	*/
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
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_calendarResource.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_calendarResource.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this calendar resource from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this calendar resource
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_calendarResource.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this calendar resource from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this calendar resource
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_calendarResource.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the type of this calendar resource.
	*
	* @return the type of this calendar resource
	*/
	public java.lang.String getType() {
		return _calendarResource.getType();
	}

	/**
	* Sets the type of this calendar resource.
	*
	* @param type the type of this calendar resource
	*/
	public void setType(java.lang.String type) {
		_calendarResource.setType(type);
	}

	/**
	* Returns the active of this calendar resource.
	*
	* @return the active of this calendar resource
	*/
	public boolean getActive() {
		return _calendarResource.getActive();
	}

	/**
	* Returns <code>true</code> if this calendar resource is active.
	*
	* @return <code>true</code> if this calendar resource is active; <code>false</code> otherwise
	*/
	public boolean isActive() {
		return _calendarResource.isActive();
	}

	/**
	* Sets whether this calendar resource is active.
	*
	* @param active the active of this calendar resource
	*/
	public void setActive(boolean active) {
		_calendarResource.setActive(active);
	}

	public boolean isNew() {
		return _calendarResource.isNew();
	}

	public void setNew(boolean n) {
		_calendarResource.setNew(n);
	}

	public boolean isCachedModel() {
		return _calendarResource.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_calendarResource.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _calendarResource.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _calendarResource.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_calendarResource.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _calendarResource.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_calendarResource.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CalendarResourceWrapper((CalendarResource)_calendarResource.clone());
	}

	public int compareTo(
		com.liferay.calendar.model.CalendarResource calendarResource) {
		return _calendarResource.compareTo(calendarResource);
	}

	@Override
	public int hashCode() {
		return _calendarResource.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.calendar.model.CalendarResource> toCacheModel() {
		return _calendarResource.toCacheModel();
	}

	public com.liferay.calendar.model.CalendarResource toEscapedModel() {
		return new CalendarResourceWrapper(_calendarResource.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _calendarResource.toString();
	}

	public java.lang.String toXmlString() {
		return _calendarResource.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_calendarResource.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CalendarResource getWrappedCalendarResource() {
		return _calendarResource;
	}

	public CalendarResource getWrappedModel() {
		return _calendarResource;
	}

	public void resetOriginalValues() {
		_calendarResource.resetOriginalValues();
	}

	private CalendarResource _calendarResource;
}