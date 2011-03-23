/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link CalendarResource}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarResource
 * @generated
 */
public class CalendarResourceWrapper implements CalendarResource {
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
	* Gets the primary key of this calendar resource.
	*
	* @return the primary key of this calendar resource
	*/
	public long getPrimaryKey() {
		return _calendarResource.getPrimaryKey();
	}

	/**
	* Sets the primary key of this calendar resource
	*
	* @param pk the primary key of this calendar resource
	*/
	public void setPrimaryKey(long pk) {
		_calendarResource.setPrimaryKey(pk);
	}

	/**
	* Gets the uuid of this calendar resource.
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
	* Gets the calendar resource ID of this calendar resource.
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
	* Gets the group ID of this calendar resource.
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
	* Gets the company ID of this calendar resource.
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
	* Gets the user ID of this calendar resource.
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
	* Gets the user uuid of this calendar resource.
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
	* Gets the user name of this calendar resource.
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
	* Gets the create date of this calendar resource.
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
	* Gets the modified date of this calendar resource.
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
	* Gets the class name of the model instance this calendar resource is polymorphically associated with.
	*
	* @return the class name of the model instance this calendar resource is polymorphically associated with
	*/
	public java.lang.String getClassName() {
		return _calendarResource.getClassName();
	}

	/**
	* Gets the class name ID of this calendar resource.
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
	* Gets the class p k of this calendar resource.
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
	* Gets the class uuid of this calendar resource.
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
	* Gets the name of this calendar resource.
	*
	* @return the name of this calendar resource
	*/
	public java.lang.String getName() {
		return _calendarResource.getName();
	}

	/**
	* Gets the localized name of this calendar resource. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized name for
	* @return the localized name of this calendar resource
	*/
	public java.lang.String getName(java.util.Locale locale) {
		return _calendarResource.getName(locale);
	}

	/**
	* Gets the localized name of this calendar resource, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized name for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this calendar resource. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _calendarResource.getName(locale, useDefault);
	}

	/**
	* Gets the localized name of this calendar resource. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized name for
	* @return the localized name of this calendar resource
	*/
	public java.lang.String getName(java.lang.String languageId) {
		return _calendarResource.getName(languageId);
	}

	/**
	* Gets the localized name of this calendar resource, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized name for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this calendar resource
	*/
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _calendarResource.getName(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized name of this calendar resource.
	*
	* @return the locales and localized name
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
	* Sets the localized name of this calendar resource.
	*
	* @param name the localized name of this calendar resource
	* @param locale the locale to set the localized name for
	*/
	public void setName(java.lang.String name, java.util.Locale locale) {
		_calendarResource.setName(name, locale);
	}

	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_calendarResource.setName(name, locale, defaultLocale);
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

	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_calendarResource.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Gets the description of this calendar resource.
	*
	* @return the description of this calendar resource
	*/
	public java.lang.String getDescription() {
		return _calendarResource.getDescription();
	}

	/**
	* Gets the localized description of this calendar resource. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized description for
	* @return the localized description of this calendar resource
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _calendarResource.getDescription(locale);
	}

	/**
	* Gets the localized description of this calendar resource, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized description for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar resource. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _calendarResource.getDescription(locale, useDefault);
	}

	/**
	* Gets the localized description of this calendar resource. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized description for
	* @return the localized description of this calendar resource
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _calendarResource.getDescription(languageId);
	}

	/**
	* Gets the localized description of this calendar resource, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized description for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar resource
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _calendarResource.getDescription(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized description of this calendar resource.
	*
	* @return the locales and localized description
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
	* Sets the localized description of this calendar resource.
	*
	* @param description the localized description of this calendar resource
	* @param locale the locale to set the localized description for
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_calendarResource.setDescription(description, locale);
	}

	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_calendarResource.setDescription(description, locale, defaultLocale);
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

	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_calendarResource.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Gets the active of this calendar resource.
	*
	* @return the active of this calendar resource
	*/
	public boolean getActive() {
		return _calendarResource.getActive();
	}

	/**
	* Determines if this calendar resource is active.
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

	public void setEscapedModel(boolean escapedModel) {
		_calendarResource.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _calendarResource.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _calendarResource.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_calendarResource.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new CalendarResourceWrapper((CalendarResource)_calendarResource.clone());
	}

	public int compareTo(
		com.liferay.calendar.model.CalendarResource calendarResource) {
		return _calendarResource.compareTo(calendarResource);
	}

	public int hashCode() {
		return _calendarResource.hashCode();
	}

	public com.liferay.calendar.model.CalendarResource toEscapedModel() {
		return new CalendarResourceWrapper(_calendarResource.toEscapedModel());
	}

	public java.lang.String toString() {
		return _calendarResource.toString();
	}

	public java.lang.String toXmlString() {
		return _calendarResource.toXmlString();
	}

	public CalendarResource getWrappedCalendarResource() {
		return _calendarResource;
	}

	private CalendarResource _calendarResource;
}