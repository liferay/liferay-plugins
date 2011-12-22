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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.calendar.service.http.CalendarEventServiceSoap}.
 *
 * @author    Eduardo Lundgren
 * @see       com.liferay.calendar.service.http.CalendarEventServiceSoap
 * @generated
 */
public class CalendarEventSoap implements Serializable {
	public static CalendarEventSoap toSoapModel(CalendarEvent model) {
		CalendarEventSoap soapModel = new CalendarEventSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCalendarEventId(model.getCalendarEventId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setLocation(model.getLocation());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setDurationHour(model.getDurationHour());
		soapModel.setDurationMinute(model.getDurationMinute());
		soapModel.setAllDay(model.getAllDay());
		soapModel.setRecurrence(model.getRecurrence());
		soapModel.setType(model.getType());
		soapModel.setRemindBy(model.getRemindBy());
		soapModel.setFirstReminder(model.getFirstReminder());
		soapModel.setSecondReminder(model.getSecondReminder());

		return soapModel;
	}

	public static CalendarEventSoap[] toSoapModels(CalendarEvent[] models) {
		CalendarEventSoap[] soapModels = new CalendarEventSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalendarEventSoap[][] toSoapModels(CalendarEvent[][] models) {
		CalendarEventSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalendarEventSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalendarEventSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalendarEventSoap[] toSoapModels(List<CalendarEvent> models) {
		List<CalendarEventSoap> soapModels = new ArrayList<CalendarEventSoap>(models.size());

		for (CalendarEvent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalendarEventSoap[soapModels.size()]);
	}

	public CalendarEventSoap() {
	}

	public long getPrimaryKey() {
		return _calendarEventId;
	}

	public void setPrimaryKey(long pk) {
		setCalendarEventId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCalendarEventId() {
		return _calendarEventId;
	}

	public void setCalendarEventId(long calendarEventId) {
		_calendarEventId = calendarEventId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getLocation() {
		return _location;
	}

	public void setLocation(String location) {
		_location = location;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public int getDurationHour() {
		return _durationHour;
	}

	public void setDurationHour(int durationHour) {
		_durationHour = durationHour;
	}

	public int getDurationMinute() {
		return _durationMinute;
	}

	public void setDurationMinute(int durationMinute) {
		_durationMinute = durationMinute;
	}

	public boolean getAllDay() {
		return _allDay;
	}

	public boolean isAllDay() {
		return _allDay;
	}

	public void setAllDay(boolean allDay) {
		_allDay = allDay;
	}

	public String getRecurrence() {
		return _recurrence;
	}

	public void setRecurrence(String recurrence) {
		_recurrence = recurrence;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public int getRemindBy() {
		return _remindBy;
	}

	public void setRemindBy(int remindBy) {
		_remindBy = remindBy;
	}

	public int getFirstReminder() {
		return _firstReminder;
	}

	public void setFirstReminder(int firstReminder) {
		_firstReminder = firstReminder;
	}

	public int getSecondReminder() {
		return _secondReminder;
	}

	public void setSecondReminder(int secondReminder) {
		_secondReminder = secondReminder;
	}

	private String _uuid;
	private long _calendarEventId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private String _location;
	private Date _startDate;
	private Date _endDate;
	private int _durationHour;
	private int _durationMinute;
	private boolean _allDay;
	private String _recurrence;
	private String _type;
	private int _remindBy;
	private int _firstReminder;
	private int _secondReminder;
}