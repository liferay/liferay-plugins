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
 * This class is a wrapper for {@link CalendarEvent}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarEvent
 * @generated
 */
public class CalendarEventWrapper implements CalendarEvent {
	public CalendarEventWrapper(CalendarEvent calendarEvent) {
		_calendarEvent = calendarEvent;
	}

	public Class<?> getModelClass() {
		return CalendarEvent.class;
	}

	public String getModelClassName() {
		return CalendarEvent.class.getName();
	}

	/**
	* Gets the primary key of this calendar event.
	*
	* @return the primary key of this calendar event
	*/
	public long getPrimaryKey() {
		return _calendarEvent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this calendar event
	*
	* @param pk the primary key of this calendar event
	*/
	public void setPrimaryKey(long pk) {
		_calendarEvent.setPrimaryKey(pk);
	}

	/**
	* Gets the uuid of this calendar event.
	*
	* @return the uuid of this calendar event
	*/
	public java.lang.String getUuid() {
		return _calendarEvent.getUuid();
	}

	/**
	* Sets the uuid of this calendar event.
	*
	* @param uuid the uuid of this calendar event
	*/
	public void setUuid(java.lang.String uuid) {
		_calendarEvent.setUuid(uuid);
	}

	/**
	* Gets the calendar event ID of this calendar event.
	*
	* @return the calendar event ID of this calendar event
	*/
	public long getCalendarEventId() {
		return _calendarEvent.getCalendarEventId();
	}

	/**
	* Sets the calendar event ID of this calendar event.
	*
	* @param calendarEventId the calendar event ID of this calendar event
	*/
	public void setCalendarEventId(long calendarEventId) {
		_calendarEvent.setCalendarEventId(calendarEventId);
	}

	/**
	* Gets the group ID of this calendar event.
	*
	* @return the group ID of this calendar event
	*/
	public long getGroupId() {
		return _calendarEvent.getGroupId();
	}

	/**
	* Sets the group ID of this calendar event.
	*
	* @param groupId the group ID of this calendar event
	*/
	public void setGroupId(long groupId) {
		_calendarEvent.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this calendar event.
	*
	* @return the company ID of this calendar event
	*/
	public long getCompanyId() {
		return _calendarEvent.getCompanyId();
	}

	/**
	* Sets the company ID of this calendar event.
	*
	* @param companyId the company ID of this calendar event
	*/
	public void setCompanyId(long companyId) {
		_calendarEvent.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this calendar event.
	*
	* @return the user ID of this calendar event
	*/
	public long getUserId() {
		return _calendarEvent.getUserId();
	}

	/**
	* Sets the user ID of this calendar event.
	*
	* @param userId the user ID of this calendar event
	*/
	public void setUserId(long userId) {
		_calendarEvent.setUserId(userId);
	}

	/**
	* Gets the user uuid of this calendar event.
	*
	* @return the user uuid of this calendar event
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEvent.getUserUuid();
	}

	/**
	* Sets the user uuid of this calendar event.
	*
	* @param userUuid the user uuid of this calendar event
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_calendarEvent.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this calendar event.
	*
	* @return the user name of this calendar event
	*/
	public java.lang.String getUserName() {
		return _calendarEvent.getUserName();
	}

	/**
	* Sets the user name of this calendar event.
	*
	* @param userName the user name of this calendar event
	*/
	public void setUserName(java.lang.String userName) {
		_calendarEvent.setUserName(userName);
	}

	/**
	* Gets the create date of this calendar event.
	*
	* @return the create date of this calendar event
	*/
	public java.util.Date getCreateDate() {
		return _calendarEvent.getCreateDate();
	}

	/**
	* Sets the create date of this calendar event.
	*
	* @param createDate the create date of this calendar event
	*/
	public void setCreateDate(java.util.Date createDate) {
		_calendarEvent.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this calendar event.
	*
	* @return the modified date of this calendar event
	*/
	public java.util.Date getModifiedDate() {
		return _calendarEvent.getModifiedDate();
	}

	/**
	* Sets the modified date of this calendar event.
	*
	* @param modifiedDate the modified date of this calendar event
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_calendarEvent.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the title of this calendar event.
	*
	* @return the title of this calendar event
	*/
	public java.lang.String getTitle() {
		return _calendarEvent.getTitle();
	}

	/**
	* Gets the localized title of this calendar event. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized title for
	* @return the localized title of this calendar event
	*/
	public java.lang.String getTitle(java.util.Locale locale) {
		return _calendarEvent.getTitle(locale);
	}

	/**
	* Gets the localized title of this calendar event, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized title for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this calendar event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _calendarEvent.getTitle(locale, useDefault);
	}

	/**
	* Gets the localized title of this calendar event. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized title for
	* @return the localized title of this calendar event
	*/
	public java.lang.String getTitle(java.lang.String languageId) {
		return _calendarEvent.getTitle(languageId);
	}

	/**
	* Gets the localized title of this calendar event, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized title for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this calendar event
	*/
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _calendarEvent.getTitle(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized title of this calendar event.
	*
	* @return the locales and localized title
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _calendarEvent.getTitleMap();
	}

	/**
	* Sets the title of this calendar event.
	*
	* @param title the title of this calendar event
	*/
	public void setTitle(java.lang.String title) {
		_calendarEvent.setTitle(title);
	}

	/**
	* Sets the localized title of this calendar event.
	*
	* @param title the localized title of this calendar event
	* @param locale the locale to set the localized title for
	*/
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_calendarEvent.setTitle(title, locale);
	}

	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_calendarEvent.setTitle(title, locale, defaultLocale);
	}

	/**
	* Sets the localized titles of this calendar event from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this calendar event
	*/
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_calendarEvent.setTitleMap(titleMap);
	}

	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_calendarEvent.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Gets the description of this calendar event.
	*
	* @return the description of this calendar event
	*/
	public java.lang.String getDescription() {
		return _calendarEvent.getDescription();
	}

	/**
	* Gets the localized description of this calendar event. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale to get the localized description for
	* @return the localized description of this calendar event
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _calendarEvent.getDescription(locale);
	}

	/**
	* Gets the localized description of this calendar event, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local to get the localized description for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _calendarEvent.getDescription(locale, useDefault);
	}

	/**
	* Gets the localized description of this calendar event. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized description for
	* @return the localized description of this calendar event
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _calendarEvent.getDescription(languageId);
	}

	/**
	* Gets the localized description of this calendar event, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the id of the language to get the localized description for
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar event
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _calendarEvent.getDescription(languageId, useDefault);
	}

	/**
	* Gets a map of the locales and localized description of this calendar event.
	*
	* @return the locales and localized description
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _calendarEvent.getDescriptionMap();
	}

	/**
	* Sets the description of this calendar event.
	*
	* @param description the description of this calendar event
	*/
	public void setDescription(java.lang.String description) {
		_calendarEvent.setDescription(description);
	}

	/**
	* Sets the localized description of this calendar event.
	*
	* @param description the localized description of this calendar event
	* @param locale the locale to set the localized description for
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_calendarEvent.setDescription(description, locale);
	}

	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_calendarEvent.setDescription(description, locale, defaultLocale);
	}

	/**
	* Sets the localized descriptions of this calendar event from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this calendar event
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_calendarEvent.setDescriptionMap(descriptionMap);
	}

	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_calendarEvent.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Gets the location of this calendar event.
	*
	* @return the location of this calendar event
	*/
	public java.lang.String getLocation() {
		return _calendarEvent.getLocation();
	}

	/**
	* Sets the location of this calendar event.
	*
	* @param location the location of this calendar event
	*/
	public void setLocation(java.lang.String location) {
		_calendarEvent.setLocation(location);
	}

	/**
	* Gets the start date of this calendar event.
	*
	* @return the start date of this calendar event
	*/
	public java.util.Date getStartDate() {
		return _calendarEvent.getStartDate();
	}

	/**
	* Sets the start date of this calendar event.
	*
	* @param startDate the start date of this calendar event
	*/
	public void setStartDate(java.util.Date startDate) {
		_calendarEvent.setStartDate(startDate);
	}

	/**
	* Gets the end date of this calendar event.
	*
	* @return the end date of this calendar event
	*/
	public java.util.Date getEndDate() {
		return _calendarEvent.getEndDate();
	}

	/**
	* Sets the end date of this calendar event.
	*
	* @param endDate the end date of this calendar event
	*/
	public void setEndDate(java.util.Date endDate) {
		_calendarEvent.setEndDate(endDate);
	}

	/**
	* Gets the duration hour of this calendar event.
	*
	* @return the duration hour of this calendar event
	*/
	public int getDurationHour() {
		return _calendarEvent.getDurationHour();
	}

	/**
	* Sets the duration hour of this calendar event.
	*
	* @param durationHour the duration hour of this calendar event
	*/
	public void setDurationHour(int durationHour) {
		_calendarEvent.setDurationHour(durationHour);
	}

	/**
	* Gets the duration minute of this calendar event.
	*
	* @return the duration minute of this calendar event
	*/
	public int getDurationMinute() {
		return _calendarEvent.getDurationMinute();
	}

	/**
	* Sets the duration minute of this calendar event.
	*
	* @param durationMinute the duration minute of this calendar event
	*/
	public void setDurationMinute(int durationMinute) {
		_calendarEvent.setDurationMinute(durationMinute);
	}

	/**
	* Gets the all day of this calendar event.
	*
	* @return the all day of this calendar event
	*/
	public boolean getAllDay() {
		return _calendarEvent.getAllDay();
	}

	/**
	* Determines if this calendar event is all day.
	*
	* @return <code>true</code> if this calendar event is all day; <code>false</code> otherwise
	*/
	public boolean isAllDay() {
		return _calendarEvent.isAllDay();
	}

	/**
	* Sets whether this calendar event is all day.
	*
	* @param allDay the all day of this calendar event
	*/
	public void setAllDay(boolean allDay) {
		_calendarEvent.setAllDay(allDay);
	}

	/**
	* Gets the recurrence of this calendar event.
	*
	* @return the recurrence of this calendar event
	*/
	public java.lang.String getRecurrence() {
		return _calendarEvent.getRecurrence();
	}

	/**
	* Sets the recurrence of this calendar event.
	*
	* @param recurrence the recurrence of this calendar event
	*/
	public void setRecurrence(java.lang.String recurrence) {
		_calendarEvent.setRecurrence(recurrence);
	}

	/**
	* Gets the type of this calendar event.
	*
	* @return the type of this calendar event
	*/
	public java.lang.String getType() {
		return _calendarEvent.getType();
	}

	/**
	* Sets the type of this calendar event.
	*
	* @param type the type of this calendar event
	*/
	public void setType(java.lang.String type) {
		_calendarEvent.setType(type);
	}

	/**
	* Gets the remind by of this calendar event.
	*
	* @return the remind by of this calendar event
	*/
	public int getRemindBy() {
		return _calendarEvent.getRemindBy();
	}

	/**
	* Sets the remind by of this calendar event.
	*
	* @param remindBy the remind by of this calendar event
	*/
	public void setRemindBy(int remindBy) {
		_calendarEvent.setRemindBy(remindBy);
	}

	/**
	* Gets the first reminder of this calendar event.
	*
	* @return the first reminder of this calendar event
	*/
	public int getFirstReminder() {
		return _calendarEvent.getFirstReminder();
	}

	/**
	* Sets the first reminder of this calendar event.
	*
	* @param firstReminder the first reminder of this calendar event
	*/
	public void setFirstReminder(int firstReminder) {
		_calendarEvent.setFirstReminder(firstReminder);
	}

	/**
	* Gets the second reminder of this calendar event.
	*
	* @return the second reminder of this calendar event
	*/
	public int getSecondReminder() {
		return _calendarEvent.getSecondReminder();
	}

	/**
	* Sets the second reminder of this calendar event.
	*
	* @param secondReminder the second reminder of this calendar event
	*/
	public void setSecondReminder(int secondReminder) {
		_calendarEvent.setSecondReminder(secondReminder);
	}

	public boolean isNew() {
		return _calendarEvent.isNew();
	}

	public void setNew(boolean n) {
		_calendarEvent.setNew(n);
	}

	public boolean isCachedModel() {
		return _calendarEvent.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_calendarEvent.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _calendarEvent.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_calendarEvent.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _calendarEvent.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _calendarEvent.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_calendarEvent.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new CalendarEventWrapper((CalendarEvent)_calendarEvent.clone());
	}

	public int compareTo(com.liferay.calendar.model.CalendarEvent calendarEvent) {
		return _calendarEvent.compareTo(calendarEvent);
	}

	public int hashCode() {
		return _calendarEvent.hashCode();
	}

	public com.liferay.calendar.model.CalendarEvent toEscapedModel() {
		return new CalendarEventWrapper(_calendarEvent.toEscapedModel());
	}

	public java.lang.String toString() {
		return _calendarEvent.toString();
	}

	public java.lang.String toXmlString() {
		return _calendarEvent.toXmlString();
	}

	public CalendarEvent getWrappedCalendarEvent() {
		return _calendarEvent;
	}

	public void resetOriginalValues() {
		_calendarEvent.resetOriginalValues();
	}

	private CalendarEvent _calendarEvent;
}