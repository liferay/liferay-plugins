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
 * This class is a wrapper for {@link CalendarBooking}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarBooking
 * @generated
 */
public class CalendarBookingWrapper implements CalendarBooking {
	public CalendarBookingWrapper(CalendarBooking calendarBooking) {
		_calendarBooking = calendarBooking;
	}

	public Class<?> getModelClass() {
		return CalendarBooking.class;
	}

	public String getModelClassName() {
		return CalendarBooking.class.getName();
	}

	/**
	* Gets the primary key of this calendar booking.
	*
	* @return the primary key of this calendar booking
	*/
	public long getPrimaryKey() {
		return _calendarBooking.getPrimaryKey();
	}

	/**
	* Sets the primary key of this calendar booking
	*
	* @param primaryKey the primary key of this calendar booking
	*/
	public void setPrimaryKey(long primaryKey) {
		_calendarBooking.setPrimaryKey(primaryKey);
	}

	/**
	* Gets the uuid of this calendar booking.
	*
	* @return the uuid of this calendar booking
	*/
	public java.lang.String getUuid() {
		return _calendarBooking.getUuid();
	}

	/**
	* Sets the uuid of this calendar booking.
	*
	* @param uuid the uuid of this calendar booking
	*/
	public void setUuid(java.lang.String uuid) {
		_calendarBooking.setUuid(uuid);
	}

	/**
	* Gets the calendar booking ID of this calendar booking.
	*
	* @return the calendar booking ID of this calendar booking
	*/
	public long getCalendarBookingId() {
		return _calendarBooking.getCalendarBookingId();
	}

	/**
	* Sets the calendar booking ID of this calendar booking.
	*
	* @param calendarBookingId the calendar booking ID of this calendar booking
	*/
	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBooking.setCalendarBookingId(calendarBookingId);
	}

	/**
	* Gets the group ID of this calendar booking.
	*
	* @return the group ID of this calendar booking
	*/
	public long getGroupId() {
		return _calendarBooking.getGroupId();
	}

	/**
	* Sets the group ID of this calendar booking.
	*
	* @param groupId the group ID of this calendar booking
	*/
	public void setGroupId(long groupId) {
		_calendarBooking.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this calendar booking.
	*
	* @return the company ID of this calendar booking
	*/
	public long getCompanyId() {
		return _calendarBooking.getCompanyId();
	}

	/**
	* Sets the company ID of this calendar booking.
	*
	* @param companyId the company ID of this calendar booking
	*/
	public void setCompanyId(long companyId) {
		_calendarBooking.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this calendar booking.
	*
	* @return the user ID of this calendar booking
	*/
	public long getUserId() {
		return _calendarBooking.getUserId();
	}

	/**
	* Sets the user ID of this calendar booking.
	*
	* @param userId the user ID of this calendar booking
	*/
	public void setUserId(long userId) {
		_calendarBooking.setUserId(userId);
	}

	/**
	* Gets the user uuid of this calendar booking.
	*
	* @return the user uuid of this calendar booking
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBooking.getUserUuid();
	}

	/**
	* Sets the user uuid of this calendar booking.
	*
	* @param userUuid the user uuid of this calendar booking
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_calendarBooking.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this calendar booking.
	*
	* @return the user name of this calendar booking
	*/
	public java.lang.String getUserName() {
		return _calendarBooking.getUserName();
	}

	/**
	* Sets the user name of this calendar booking.
	*
	* @param userName the user name of this calendar booking
	*/
	public void setUserName(java.lang.String userName) {
		_calendarBooking.setUserName(userName);
	}

	/**
	* Gets the create date of this calendar booking.
	*
	* @return the create date of this calendar booking
	*/
	public java.util.Date getCreateDate() {
		return _calendarBooking.getCreateDate();
	}

	/**
	* Sets the create date of this calendar booking.
	*
	* @param createDate the create date of this calendar booking
	*/
	public void setCreateDate(java.util.Date createDate) {
		_calendarBooking.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this calendar booking.
	*
	* @return the modified date of this calendar booking
	*/
	public java.util.Date getModifiedDate() {
		return _calendarBooking.getModifiedDate();
	}

	/**
	* Sets the modified date of this calendar booking.
	*
	* @param modifiedDate the modified date of this calendar booking
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_calendarBooking.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the calendar event ID of this calendar booking.
	*
	* @return the calendar event ID of this calendar booking
	*/
	public long getCalendarEventId() {
		return _calendarBooking.getCalendarEventId();
	}

	/**
	* Sets the calendar event ID of this calendar booking.
	*
	* @param calendarEventId the calendar event ID of this calendar booking
	*/
	public void setCalendarEventId(long calendarEventId) {
		_calendarBooking.setCalendarEventId(calendarEventId);
	}

	/**
	* Gets the calendar resource ID of this calendar booking.
	*
	* @return the calendar resource ID of this calendar booking
	*/
	public long getCalendarResourceId() {
		return _calendarBooking.getCalendarResourceId();
	}

	/**
	* Sets the calendar resource ID of this calendar booking.
	*
	* @param calendarResourceId the calendar resource ID of this calendar booking
	*/
	public void setCalendarResourceId(long calendarResourceId) {
		_calendarBooking.setCalendarResourceId(calendarResourceId);
	}

	/**
	* Gets the class name of the model instance this calendar booking is polymorphically associated with.
	*
	* @return the class name of the model instance this calendar booking is polymorphically associated with
	*/
	public java.lang.String getClassName() {
		return _calendarBooking.getClassName();
	}

	/**
	* Gets the class name ID of this calendar booking.
	*
	* @return the class name ID of this calendar booking
	*/
	public long getClassNameId() {
		return _calendarBooking.getClassNameId();
	}

	/**
	* Sets the class name ID of this calendar booking.
	*
	* @param classNameId the class name ID of this calendar booking
	*/
	public void setClassNameId(long classNameId) {
		_calendarBooking.setClassNameId(classNameId);
	}

	/**
	* Gets the class p k of this calendar booking.
	*
	* @return the class p k of this calendar booking
	*/
	public long getClassPK() {
		return _calendarBooking.getClassPK();
	}

	/**
	* Sets the class p k of this calendar booking.
	*
	* @param classPK the class p k of this calendar booking
	*/
	public void setClassPK(long classPK) {
		_calendarBooking.setClassPK(classPK);
	}

	/**
	* Gets the title of this calendar booking.
	*
	* @return the title of this calendar booking
	*/
	public java.lang.String getTitle() {
		return _calendarBooking.getTitle();
	}

	/**
	* Gets the localized title of this calendar booking. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized title for
	* @return the localized title of this calendar booking
	*/
	public java.lang.String getTitle(java.util.Locale locale) {
		return _calendarBooking.getTitle(locale);
	}

	/**
	* Gets the localized title of this calendar booking, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized title for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this calendar booking. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _calendarBooking.getTitle(locale, useDefault);
	}

	/**
	* Gets the localized title of this calendar booking. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized title for
	* @return the localized title of this calendar booking
	*/
	public java.lang.String getTitle(java.lang.String languageId) {
		return _calendarBooking.getTitle(languageId);
	}

	/**
	* Gets the localized title of this calendar booking, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized title for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this calendar booking
	*/
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _calendarBooking.getTitle(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized title of this calendar booking.
	*
	* @return the locales and localized title
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _calendarBooking.getTitleMap();
	}

	/**
	* Sets the title of this calendar booking.
	*
	* @param title the title of this calendar booking
	*/
	public void setTitle(java.lang.String title) {
		_calendarBooking.setTitle(title);
	}

	/**
	* Sets the localized title of this calendar booking.
	*
	* @param title the localized title of this calendar booking
	* @param locale the locale to set the localized title for
	*/
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_calendarBooking.setTitle(title, locale);
	}

	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_calendarBooking.setTitle(title, locale, defaultLocale);
	}

	/**
	* Sets the localized titles of this calendar booking from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this calendar booking
	*/
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_calendarBooking.setTitleMap(titleMap);
	}

	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_calendarBooking.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Gets the name of this calendar booking.
	*
	* @return the name of this calendar booking
	*/
	public java.lang.String getName() {
		return _calendarBooking.getName();
	}

	/**
	* Gets the localized name of this calendar booking. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized name for
	* @return the localized name of this calendar booking
	*/
	public java.lang.String getName(java.util.Locale locale) {
		return _calendarBooking.getName(locale);
	}

	/**
	* Gets the localized name of this calendar booking, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized name for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this calendar booking. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _calendarBooking.getName(locale, useDefault);
	}

	/**
	* Gets the localized name of this calendar booking. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized name for
	* @return the localized name of this calendar booking
	*/
	public java.lang.String getName(java.lang.String languageId) {
		return _calendarBooking.getName(languageId);
	}

	/**
	* Gets the localized name of this calendar booking, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized name for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this calendar booking
	*/
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _calendarBooking.getName(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized name of this calendar booking.
	*
	* @return the locales and localized name
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _calendarBooking.getNameMap();
	}

	/**
	* Sets the name of this calendar booking.
	*
	* @param name the name of this calendar booking
	*/
	public void setName(java.lang.String name) {
		_calendarBooking.setName(name);
	}

	/**
	* Sets the localized name of this calendar booking.
	*
	* @param name the localized name of this calendar booking
	* @param locale the locale to set the localized name for
	*/
	public void setName(java.lang.String name, java.util.Locale locale) {
		_calendarBooking.setName(name, locale);
	}

	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_calendarBooking.setName(name, locale, defaultLocale);
	}

	/**
	* Sets the localized names of this calendar booking from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this calendar booking
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_calendarBooking.setNameMap(nameMap);
	}

	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_calendarBooking.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Gets the description of this calendar booking.
	*
	* @return the description of this calendar booking
	*/
	public java.lang.String getDescription() {
		return _calendarBooking.getDescription();
	}

	/**
	* Gets the localized description of this calendar booking. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized description for
	* @return the localized description of this calendar booking
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _calendarBooking.getDescription(locale);
	}

	/**
	* Gets the localized description of this calendar booking, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized description for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar booking. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _calendarBooking.getDescription(locale, useDefault);
	}

	/**
	* Gets the localized description of this calendar booking. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized description for
	* @return the localized description of this calendar booking
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _calendarBooking.getDescription(languageId);
	}

	/**
	* Gets the localized description of this calendar booking, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized description for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar booking
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _calendarBooking.getDescription(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized description of this calendar booking.
	*
	* @return the locales and localized description
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _calendarBooking.getDescriptionMap();
	}

	/**
	* Sets the description of this calendar booking.
	*
	* @param description the description of this calendar booking
	*/
	public void setDescription(java.lang.String description) {
		_calendarBooking.setDescription(description);
	}

	/**
	* Sets the localized description of this calendar booking.
	*
	* @param description the localized description of this calendar booking
	* @param locale the locale to set the localized description for
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_calendarBooking.setDescription(description, locale);
	}

	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_calendarBooking.setDescription(description, locale, defaultLocale);
	}

	/**
	* Sets the localized descriptions of this calendar booking from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this calendar booking
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_calendarBooking.setDescriptionMap(descriptionMap);
	}

	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_calendarBooking.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Gets the location of this calendar booking.
	*
	* @return the location of this calendar booking
	*/
	public java.lang.String getLocation() {
		return _calendarBooking.getLocation();
	}

	/**
	* Sets the location of this calendar booking.
	*
	* @param location the location of this calendar booking
	*/
	public void setLocation(java.lang.String location) {
		_calendarBooking.setLocation(location);
	}

	/**
	* Gets the start date of this calendar booking.
	*
	* @return the start date of this calendar booking
	*/
	public java.util.Date getStartDate() {
		return _calendarBooking.getStartDate();
	}

	/**
	* Sets the start date of this calendar booking.
	*
	* @param startDate the start date of this calendar booking
	*/
	public void setStartDate(java.util.Date startDate) {
		_calendarBooking.setStartDate(startDate);
	}

	/**
	* Gets the end date of this calendar booking.
	*
	* @return the end date of this calendar booking
	*/
	public java.util.Date getEndDate() {
		return _calendarBooking.getEndDate();
	}

	/**
	* Sets the end date of this calendar booking.
	*
	* @param endDate the end date of this calendar booking
	*/
	public void setEndDate(java.util.Date endDate) {
		_calendarBooking.setEndDate(endDate);
	}

	/**
	* Gets the duration hour of this calendar booking.
	*
	* @return the duration hour of this calendar booking
	*/
	public int getDurationHour() {
		return _calendarBooking.getDurationHour();
	}

	/**
	* Sets the duration hour of this calendar booking.
	*
	* @param durationHour the duration hour of this calendar booking
	*/
	public void setDurationHour(int durationHour) {
		_calendarBooking.setDurationHour(durationHour);
	}

	/**
	* Gets the duration minute of this calendar booking.
	*
	* @return the duration minute of this calendar booking
	*/
	public int getDurationMinute() {
		return _calendarBooking.getDurationMinute();
	}

	/**
	* Sets the duration minute of this calendar booking.
	*
	* @param durationMinute the duration minute of this calendar booking
	*/
	public void setDurationMinute(int durationMinute) {
		_calendarBooking.setDurationMinute(durationMinute);
	}

	/**
	* Gets the recurrence of this calendar booking.
	*
	* @return the recurrence of this calendar booking
	*/
	public java.lang.String getRecurrence() {
		return _calendarBooking.getRecurrence();
	}

	/**
	* Sets the recurrence of this calendar booking.
	*
	* @param recurrence the recurrence of this calendar booking
	*/
	public void setRecurrence(java.lang.String recurrence) {
		_calendarBooking.setRecurrence(recurrence);
	}

	/**
	* Gets the type of this calendar booking.
	*
	* @return the type of this calendar booking
	*/
	public java.lang.String getType() {
		return _calendarBooking.getType();
	}

	/**
	* Sets the type of this calendar booking.
	*
	* @param type the type of this calendar booking
	*/
	public void setType(java.lang.String type) {
		_calendarBooking.setType(type);
	}

	/**
	* Gets the required of this calendar booking.
	*
	* @return the required of this calendar booking
	*/
	public boolean getRequired() {
		return _calendarBooking.getRequired();
	}

	/**
	* Determines if this calendar booking is required.
	*
	* @return <code>true</code> if this calendar booking is required; <code>false</code> otherwise
	*/
	public boolean isRequired() {
		return _calendarBooking.isRequired();
	}

	/**
	* Sets whether this calendar booking is required.
	*
	* @param required the required of this calendar booking
	*/
	public void setRequired(boolean required) {
		_calendarBooking.setRequired(required);
	}

	/**
	* Gets the status of this calendar booking.
	*
	* @return the status of this calendar booking
	*/
	public int getStatus() {
		return _calendarBooking.getStatus();
	}

	/**
	* Sets the status of this calendar booking.
	*
	* @param status the status of this calendar booking
	*/
	public void setStatus(int status) {
		_calendarBooking.setStatus(status);
	}

	/**
	* Gets the status by user ID of this calendar booking.
	*
	* @return the status by user ID of this calendar booking
	*/
	public long getStatusByUserId() {
		return _calendarBooking.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this calendar booking.
	*
	* @param statusByUserId the status by user ID of this calendar booking
	*/
	public void setStatusByUserId(long statusByUserId) {
		_calendarBooking.setStatusByUserId(statusByUserId);
	}

	/**
	* Gets the status by user uuid of this calendar booking.
	*
	* @return the status by user uuid of this calendar booking
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBooking.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this calendar booking.
	*
	* @param statusByUserUuid the status by user uuid of this calendar booking
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_calendarBooking.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Gets the status by user name of this calendar booking.
	*
	* @return the status by user name of this calendar booking
	*/
	public java.lang.String getStatusByUserName() {
		return _calendarBooking.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this calendar booking.
	*
	* @param statusByUserName the status by user name of this calendar booking
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_calendarBooking.setStatusByUserName(statusByUserName);
	}

	/**
	* Gets the status date of this calendar booking.
	*
	* @return the status date of this calendar booking
	*/
	public java.util.Date getStatusDate() {
		return _calendarBooking.getStatusDate();
	}

	/**
	* Sets the status date of this calendar booking.
	*
	* @param statusDate the status date of this calendar booking
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_calendarBooking.setStatusDate(statusDate);
	}

	/**
	* @deprecated {@link #isApproved}
	*/
	public boolean getApproved() {
		return _calendarBooking.getApproved();
	}

	/**
	* Determines if this calendar booking is approved.
	*
	* @return <code>true</code> if this calendar booking is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _calendarBooking.isApproved();
	}

	/**
	* Determines if this calendar booking is a draft.
	*
	* @return <code>true</code> if this calendar booking is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _calendarBooking.isDraft();
	}

	/**
	* Determines if this calendar booking is expired.
	*
	* @return <code>true</code> if this calendar booking is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _calendarBooking.isExpired();
	}

	/**
	* Determines if this calendar booking is pending.
	*
	* @return <code>true</code> if this calendar booking is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _calendarBooking.isPending();
	}

	public boolean isNew() {
		return _calendarBooking.isNew();
	}

	public void setNew(boolean n) {
		_calendarBooking.setNew(n);
	}

	public boolean isCachedModel() {
		return _calendarBooking.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_calendarBooking.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _calendarBooking.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_calendarBooking.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _calendarBooking.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_calendarBooking.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _calendarBooking.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_calendarBooking.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new CalendarBookingWrapper((CalendarBooking)_calendarBooking.clone());
	}

	public int compareTo(
		com.liferay.calendar.model.CalendarBooking calendarBooking) {
		return _calendarBooking.compareTo(calendarBooking);
	}

	public int hashCode() {
		return _calendarBooking.hashCode();
	}

	public com.liferay.calendar.model.CalendarBooking toEscapedModel() {
		return new CalendarBookingWrapper(_calendarBooking.toEscapedModel());
	}

	public java.lang.String toString() {
		return _calendarBooking.toString();
	}

	public java.lang.String toXmlString() {
		return _calendarBooking.toXmlString();
	}

	public com.liferay.calendar.model.CalendarEvent getCalendarEvent()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBooking.getCalendarEvent();
	}

	public com.liferay.calendar.model.CalendarResource getCalendarResource()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBooking.getCalendarResource();
	}

	public CalendarBooking getWrappedCalendarBooking() {
		return _calendarBooking;
	}

	public void resetOriginalValues() {
		_calendarBooking.resetOriginalValues();
	}

	private CalendarBooking _calendarBooking;
}