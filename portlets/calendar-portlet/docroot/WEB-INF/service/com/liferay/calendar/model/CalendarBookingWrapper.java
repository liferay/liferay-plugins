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
 * This class is a wrapper for {@link CalendarBooking}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarBooking
 * @generated
 */
@ProviderType
public class CalendarBookingWrapper implements CalendarBooking,
	ModelWrapper<CalendarBooking> {
	public CalendarBookingWrapper(CalendarBooking calendarBooking) {
		_calendarBooking = calendarBooking;
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
	}

	@Override
	public java.lang.Object clone() {
		return new CalendarBookingWrapper((CalendarBooking)_calendarBooking.clone());
	}

	@Override
	public int compareTo(
		com.liferay.calendar.model.CalendarBooking calendarBooking) {
		return _calendarBooking.compareTo(calendarBooking);
	}

	/**
	* Returns the all day of this calendar booking.
	*
	* @return the all day of this calendar booking
	*/
	@Override
	public boolean getAllDay() {
		return _calendarBooking.getAllDay();
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Deprecated
	@Override
	public boolean getApproved() {
		return _calendarBooking.getApproved();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _calendarBooking.getAvailableLanguageIds();
	}

	@Override
	public com.liferay.calendar.model.Calendar getCalendar()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBooking.getCalendar();
	}

	/**
	* Returns the calendar booking ID of this calendar booking.
	*
	* @return the calendar booking ID of this calendar booking
	*/
	@Override
	public long getCalendarBookingId() {
		return _calendarBooking.getCalendarBookingId();
	}

	/**
	* Returns the calendar ID of this calendar booking.
	*
	* @return the calendar ID of this calendar booking
	*/
	@Override
	public long getCalendarId() {
		return _calendarBooking.getCalendarId();
	}

	@Override
	public com.liferay.calendar.model.CalendarResource getCalendarResource()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBooking.getCalendarResource();
	}

	/**
	* Returns the calendar resource ID of this calendar booking.
	*
	* @return the calendar resource ID of this calendar booking
	*/
	@Override
	public long getCalendarResourceId() {
		return _calendarBooking.getCalendarResourceId();
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings() {
		return _calendarBooking.getChildCalendarBookings();
	}

	/**
	* Returns the company ID of this calendar booking.
	*
	* @return the company ID of this calendar booking
	*/
	@Override
	public long getCompanyId() {
		return _calendarBooking.getCompanyId();
	}

	/**
	* Returns the create date of this calendar booking.
	*
	* @return the create date of this calendar booking
	*/
	@Override
	public Date getCreateDate() {
		return _calendarBooking.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _calendarBooking.getDefaultLanguageId();
	}

	/**
	* Returns the description of this calendar booking.
	*
	* @return the description of this calendar booking
	*/
	@Override
	public java.lang.String getDescription() {
		return _calendarBooking.getDescription();
	}

	/**
	* Returns the localized description of this calendar booking in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this calendar booking
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _calendarBooking.getDescription(languageId);
	}

	/**
	* Returns the localized description of this calendar booking in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar booking
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _calendarBooking.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this calendar booking in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this calendar booking
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _calendarBooking.getDescription(locale);
	}

	/**
	* Returns the localized description of this calendar booking in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this calendar booking. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _calendarBooking.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _calendarBooking.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _calendarBooking.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this calendar booking.
	*
	* @return the locales and localized descriptions of this calendar booking
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _calendarBooking.getDescriptionMap();
	}

	@Override
	public long getDuration() {
		return _calendarBooking.getDuration();
	}

	/**
	* Returns the end time of this calendar booking.
	*
	* @return the end time of this calendar booking
	*/
	@Override
	public long getEndTime() {
		return _calendarBooking.getEndTime();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _calendarBooking.getExpandoBridge();
	}

	/**
	* Returns the first reminder of this calendar booking.
	*
	* @return the first reminder of this calendar booking
	*/
	@Override
	public long getFirstReminder() {
		return _calendarBooking.getFirstReminder();
	}

	@Override
	public com.liferay.calendar.notification.NotificationType getFirstReminderNotificationType() {
		return _calendarBooking.getFirstReminderNotificationType();
	}

	/**
	* Returns the first reminder type of this calendar booking.
	*
	* @return the first reminder type of this calendar booking
	*/
	@Override
	public java.lang.String getFirstReminderType() {
		return _calendarBooking.getFirstReminderType();
	}

	/**
	* Returns the group ID of this calendar booking.
	*
	* @return the group ID of this calendar booking
	*/
	@Override
	public long getGroupId() {
		return _calendarBooking.getGroupId();
	}

	@Override
	public int getInstanceIndex() {
		return _calendarBooking.getInstanceIndex();
	}

	/**
	* Returns the location of this calendar booking.
	*
	* @return the location of this calendar booking
	*/
	@Override
	public java.lang.String getLocation() {
		return _calendarBooking.getLocation();
	}

	/**
	* Returns the modified date of this calendar booking.
	*
	* @return the modified date of this calendar booking
	*/
	@Override
	public Date getModifiedDate() {
		return _calendarBooking.getModifiedDate();
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking getParentCalendarBooking()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBooking.getParentCalendarBooking();
	}

	/**
	* Returns the parent calendar booking ID of this calendar booking.
	*
	* @return the parent calendar booking ID of this calendar booking
	*/
	@Override
	public long getParentCalendarBookingId() {
		return _calendarBooking.getParentCalendarBookingId();
	}

	/**
	* Returns the primary key of this calendar booking.
	*
	* @return the primary key of this calendar booking
	*/
	@Override
	public long getPrimaryKey() {
		return _calendarBooking.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _calendarBooking.getPrimaryKeyObj();
	}

	/**
	* Returns the recurrence of this calendar booking.
	*
	* @return the recurrence of this calendar booking
	*/
	@Override
	public java.lang.String getRecurrence() {
		return _calendarBooking.getRecurrence();
	}

	@Override
	public com.liferay.calendar.recurrence.Recurrence getRecurrenceObj() {
		return _calendarBooking.getRecurrenceObj();
	}

	/**
	* Returns the resource block ID of this calendar booking.
	*
	* @return the resource block ID of this calendar booking
	*/
	@Override
	public long getResourceBlockId() {
		return _calendarBooking.getResourceBlockId();
	}

	/**
	* Returns the second reminder of this calendar booking.
	*
	* @return the second reminder of this calendar booking
	*/
	@Override
	public long getSecondReminder() {
		return _calendarBooking.getSecondReminder();
	}

	@Override
	public com.liferay.calendar.notification.NotificationType getSecondReminderNotificationType() {
		return _calendarBooking.getSecondReminderNotificationType();
	}

	/**
	* Returns the second reminder type of this calendar booking.
	*
	* @return the second reminder type of this calendar booking
	*/
	@Override
	public java.lang.String getSecondReminderType() {
		return _calendarBooking.getSecondReminderType();
	}

	/**
	* Returns the start time of this calendar booking.
	*
	* @return the start time of this calendar booking
	*/
	@Override
	public long getStartTime() {
		return _calendarBooking.getStartTime();
	}

	/**
	* Returns the status of this calendar booking.
	*
	* @return the status of this calendar booking
	*/
	@Override
	public int getStatus() {
		return _calendarBooking.getStatus();
	}

	/**
	* Returns the status by user ID of this calendar booking.
	*
	* @return the status by user ID of this calendar booking
	*/
	@Override
	public long getStatusByUserId() {
		return _calendarBooking.getStatusByUserId();
	}

	/**
	* Returns the status by user name of this calendar booking.
	*
	* @return the status by user name of this calendar booking
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _calendarBooking.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this calendar booking.
	*
	* @return the status by user uuid of this calendar booking
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _calendarBooking.getStatusByUserUuid();
	}

	/**
	* Returns the status date of this calendar booking.
	*
	* @return the status date of this calendar booking
	*/
	@Override
	public Date getStatusDate() {
		return _calendarBooking.getStatusDate();
	}

	@Override
	public java.util.TimeZone getTimeZone()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBooking.getTimeZone();
	}

	/**
	* Returns the title of this calendar booking.
	*
	* @return the title of this calendar booking
	*/
	@Override
	public java.lang.String getTitle() {
		return _calendarBooking.getTitle();
	}

	/**
	* Returns the localized title of this calendar booking in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this calendar booking
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _calendarBooking.getTitle(languageId);
	}

	/**
	* Returns the localized title of this calendar booking in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this calendar booking
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _calendarBooking.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this calendar booking in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this calendar booking
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _calendarBooking.getTitle(locale);
	}

	/**
	* Returns the localized title of this calendar booking in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this calendar booking. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _calendarBooking.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _calendarBooking.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _calendarBooking.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this calendar booking.
	*
	* @return the locales and localized titles of this calendar booking
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _calendarBooking.getTitleMap();
	}

	/**
	* Returns the trash entry created when this calendar booking was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this calendar booking.
	*
	* @return the trash entry created when this calendar booking was moved to the Recycle Bin
	*/
	@Override
	public com.liferay.portlet.trash.model.TrashEntry getTrashEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBooking.getTrashEntry();
	}

	/**
	* Returns the class primary key of the trash entry for this calendar booking.
	*
	* @return the class primary key of the trash entry for this calendar booking
	*/
	@Override
	public long getTrashEntryClassPK() {
		return _calendarBooking.getTrashEntryClassPK();
	}

	/**
	* Returns the trash handler for this calendar booking.
	*
	* @return the trash handler for this calendar booking
	*/
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return _calendarBooking.getTrashHandler();
	}

	/**
	* Returns the user ID of this calendar booking.
	*
	* @return the user ID of this calendar booking
	*/
	@Override
	public long getUserId() {
		return _calendarBooking.getUserId();
	}

	/**
	* Returns the user name of this calendar booking.
	*
	* @return the user name of this calendar booking
	*/
	@Override
	public java.lang.String getUserName() {
		return _calendarBooking.getUserName();
	}

	/**
	* Returns the user uuid of this calendar booking.
	*
	* @return the user uuid of this calendar booking
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _calendarBooking.getUserUuid();
	}

	/**
	* Returns the uuid of this calendar booking.
	*
	* @return the uuid of this calendar booking
	*/
	@Override
	public java.lang.String getUuid() {
		return _calendarBooking.getUuid();
	}

	/**
	* Returns the v event uid of this calendar booking.
	*
	* @return the v event uid of this calendar booking
	*/
	@Override
	public java.lang.String getVEventUid() {
		return _calendarBooking.getVEventUid();
	}

	@Override
	public int hashCode() {
		return _calendarBooking.hashCode();
	}

	/**
	* Returns <code>true</code> if this calendar booking is all day.
	*
	* @return <code>true</code> if this calendar booking is all day; <code>false</code> otherwise
	*/
	@Override
	public boolean isAllDay() {
		return _calendarBooking.isAllDay();
	}

	/**
	* Returns <code>true</code> if this calendar booking is approved.
	*
	* @return <code>true</code> if this calendar booking is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _calendarBooking.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _calendarBooking.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this calendar booking is denied.
	*
	* @return <code>true</code> if this calendar booking is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _calendarBooking.isDenied();
	}

	/**
	* Returns <code>true</code> if this calendar booking is a draft.
	*
	* @return <code>true</code> if this calendar booking is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _calendarBooking.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _calendarBooking.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this calendar booking is expired.
	*
	* @return <code>true</code> if this calendar booking is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _calendarBooking.isExpired();
	}

	/**
	* Returns <code>true</code> if this calendar booking is in the Recycle Bin.
	*
	* @return <code>true</code> if this calendar booking is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrash() {
		return _calendarBooking.isInTrash();
	}

	/**
	* Returns <code>true</code> if the parent of this calendar booking is in the Recycle Bin.
	*
	* @return <code>true</code> if the parent of this calendar booking is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrashContainer() {
		return _calendarBooking.isInTrashContainer();
	}

	@Override
	public boolean isInTrashExplicitly() {
		return _calendarBooking.isInTrashExplicitly();
	}

	@Override
	public boolean isInTrashImplicitly() {
		return _calendarBooking.isInTrashImplicitly();
	}

	/**
	* Returns <code>true</code> if this calendar booking is inactive.
	*
	* @return <code>true</code> if this calendar booking is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _calendarBooking.isInactive();
	}

	/**
	* Returns <code>true</code> if this calendar booking is incomplete.
	*
	* @return <code>true</code> if this calendar booking is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _calendarBooking.isIncomplete();
	}

	@Override
	public boolean isMasterBooking() {
		return _calendarBooking.isMasterBooking();
	}

	@Override
	public boolean isNew() {
		return _calendarBooking.isNew();
	}

	/**
	* Returns <code>true</code> if this calendar booking is pending.
	*
	* @return <code>true</code> if this calendar booking is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _calendarBooking.isPending();
	}

	@Override
	public boolean isRecurring() {
		return _calendarBooking.isRecurring();
	}

	/**
	* Returns <code>true</code> if this calendar booking is scheduled.
	*
	* @return <code>true</code> if this calendar booking is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _calendarBooking.isScheduled();
	}

	@Override
	public void persist() {
		_calendarBooking.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_calendarBooking.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_calendarBooking.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets whether this calendar booking is all day.
	*
	* @param allDay the all day of this calendar booking
	*/
	@Override
	public void setAllDay(boolean allDay) {
		_calendarBooking.setAllDay(allDay);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_calendarBooking.setCachedModel(cachedModel);
	}

	/**
	* Sets the calendar booking ID of this calendar booking.
	*
	* @param calendarBookingId the calendar booking ID of this calendar booking
	*/
	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBooking.setCalendarBookingId(calendarBookingId);
	}

	/**
	* Sets the calendar ID of this calendar booking.
	*
	* @param calendarId the calendar ID of this calendar booking
	*/
	@Override
	public void setCalendarId(long calendarId) {
		_calendarBooking.setCalendarId(calendarId);
	}

	/**
	* Sets the calendar resource ID of this calendar booking.
	*
	* @param calendarResourceId the calendar resource ID of this calendar booking
	*/
	@Override
	public void setCalendarResourceId(long calendarResourceId) {
		_calendarBooking.setCalendarResourceId(calendarResourceId);
	}

	/**
	* Sets the company ID of this calendar booking.
	*
	* @param companyId the company ID of this calendar booking
	*/
	@Override
	public void setCompanyId(long companyId) {
		_calendarBooking.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this calendar booking.
	*
	* @param createDate the create date of this calendar booking
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_calendarBooking.setCreateDate(createDate);
	}

	/**
	* Sets the description of this calendar booking.
	*
	* @param description the description of this calendar booking
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_calendarBooking.setDescription(description);
	}

	/**
	* Sets the localized description of this calendar booking in the language.
	*
	* @param description the localized description of this calendar booking
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_calendarBooking.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this calendar booking in the language, and sets the default locale.
	*
	* @param description the localized description of this calendar booking
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_calendarBooking.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_calendarBooking.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this calendar booking from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this calendar booking
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_calendarBooking.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this calendar booking from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this calendar booking
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_calendarBooking.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the end time of this calendar booking.
	*
	* @param endTime the end time of this calendar booking
	*/
	@Override
	public void setEndTime(long endTime) {
		_calendarBooking.setEndTime(endTime);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_calendarBooking.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_calendarBooking.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_calendarBooking.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the first reminder of this calendar booking.
	*
	* @param firstReminder the first reminder of this calendar booking
	*/
	@Override
	public void setFirstReminder(long firstReminder) {
		_calendarBooking.setFirstReminder(firstReminder);
	}

	/**
	* Sets the first reminder type of this calendar booking.
	*
	* @param firstReminderType the first reminder type of this calendar booking
	*/
	@Override
	public void setFirstReminderType(java.lang.String firstReminderType) {
		_calendarBooking.setFirstReminderType(firstReminderType);
	}

	/**
	* Sets the group ID of this calendar booking.
	*
	* @param groupId the group ID of this calendar booking
	*/
	@Override
	public void setGroupId(long groupId) {
		_calendarBooking.setGroupId(groupId);
	}

	@Override
	public void setInstanceIndex(int instanceIndex) {
		_calendarBooking.setInstanceIndex(instanceIndex);
	}

	/**
	* Sets the location of this calendar booking.
	*
	* @param location the location of this calendar booking
	*/
	@Override
	public void setLocation(java.lang.String location) {
		_calendarBooking.setLocation(location);
	}

	/**
	* Sets the modified date of this calendar booking.
	*
	* @param modifiedDate the modified date of this calendar booking
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_calendarBooking.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_calendarBooking.setNew(n);
	}

	/**
	* Sets the parent calendar booking ID of this calendar booking.
	*
	* @param parentCalendarBookingId the parent calendar booking ID of this calendar booking
	*/
	@Override
	public void setParentCalendarBookingId(long parentCalendarBookingId) {
		_calendarBooking.setParentCalendarBookingId(parentCalendarBookingId);
	}

	/**
	* Sets the primary key of this calendar booking.
	*
	* @param primaryKey the primary key of this calendar booking
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_calendarBooking.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_calendarBooking.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the recurrence of this calendar booking.
	*
	* @param recurrence the recurrence of this calendar booking
	*/
	@Override
	public void setRecurrence(java.lang.String recurrence) {
		_calendarBooking.setRecurrence(recurrence);
	}

	/**
	* Sets the resource block ID of this calendar booking.
	*
	* @param resourceBlockId the resource block ID of this calendar booking
	*/
	@Override
	public void setResourceBlockId(long resourceBlockId) {
		_calendarBooking.setResourceBlockId(resourceBlockId);
	}

	/**
	* Sets the second reminder of this calendar booking.
	*
	* @param secondReminder the second reminder of this calendar booking
	*/
	@Override
	public void setSecondReminder(long secondReminder) {
		_calendarBooking.setSecondReminder(secondReminder);
	}

	/**
	* Sets the second reminder type of this calendar booking.
	*
	* @param secondReminderType the second reminder type of this calendar booking
	*/
	@Override
	public void setSecondReminderType(java.lang.String secondReminderType) {
		_calendarBooking.setSecondReminderType(secondReminderType);
	}

	/**
	* Sets the start time of this calendar booking.
	*
	* @param startTime the start time of this calendar booking
	*/
	@Override
	public void setStartTime(long startTime) {
		_calendarBooking.setStartTime(startTime);
	}

	/**
	* Sets the status of this calendar booking.
	*
	* @param status the status of this calendar booking
	*/
	@Override
	public void setStatus(int status) {
		_calendarBooking.setStatus(status);
	}

	/**
	* Sets the status by user ID of this calendar booking.
	*
	* @param statusByUserId the status by user ID of this calendar booking
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_calendarBooking.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this calendar booking.
	*
	* @param statusByUserName the status by user name of this calendar booking
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_calendarBooking.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this calendar booking.
	*
	* @param statusByUserUuid the status by user uuid of this calendar booking
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_calendarBooking.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this calendar booking.
	*
	* @param statusDate the status date of this calendar booking
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_calendarBooking.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this calendar booking.
	*
	* @param title the title of this calendar booking
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_calendarBooking.setTitle(title);
	}

	/**
	* Sets the localized title of this calendar booking in the language.
	*
	* @param title the localized title of this calendar booking
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_calendarBooking.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this calendar booking in the language, and sets the default locale.
	*
	* @param title the localized title of this calendar booking
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_calendarBooking.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_calendarBooking.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this calendar booking from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this calendar booking
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_calendarBooking.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this calendar booking from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this calendar booking
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_calendarBooking.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this calendar booking.
	*
	* @param userId the user ID of this calendar booking
	*/
	@Override
	public void setUserId(long userId) {
		_calendarBooking.setUserId(userId);
	}

	/**
	* Sets the user name of this calendar booking.
	*
	* @param userName the user name of this calendar booking
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_calendarBooking.setUserName(userName);
	}

	/**
	* Sets the user uuid of this calendar booking.
	*
	* @param userUuid the user uuid of this calendar booking
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_calendarBooking.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this calendar booking.
	*
	* @param uuid the uuid of this calendar booking
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_calendarBooking.setUuid(uuid);
	}

	/**
	* Sets the v event uid of this calendar booking.
	*
	* @param vEventUid the v event uid of this calendar booking
	*/
	@Override
	public void setVEventUid(java.lang.String vEventUid) {
		_calendarBooking.setVEventUid(vEventUid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.calendar.model.CalendarBooking> toCacheModel() {
		return _calendarBooking.toCacheModel();
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking toEscapedModel() {
		return new CalendarBookingWrapper(_calendarBooking.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _calendarBooking.toString();
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking toUnescapedModel() {
		return new CalendarBookingWrapper(_calendarBooking.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _calendarBooking.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarBookingWrapper)) {
			return false;
		}

		CalendarBookingWrapper calendarBookingWrapper = (CalendarBookingWrapper)obj;

		if (Validator.equals(_calendarBooking,
					calendarBookingWrapper._calendarBooking)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _calendarBooking.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public CalendarBooking getWrappedCalendarBooking() {
		return _calendarBooking;
	}

	@Override
	public CalendarBooking getWrappedModel() {
		return _calendarBooking;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _calendarBooking.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _calendarBooking.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_calendarBooking.resetOriginalValues();
	}

	private final CalendarBooking _calendarBooking;
}