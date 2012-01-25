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
 * This class is used by SOAP remote services, specifically {@link com.liferay.calendar.service.http.CalendarBookingServiceSoap}.
 *
 * @author    Eduardo Lundgren
 * @see       com.liferay.calendar.service.http.CalendarBookingServiceSoap
 * @generated
 */
public class CalendarBookingSoap implements Serializable {
	public static CalendarBookingSoap toSoapModel(CalendarBooking model) {
		CalendarBookingSoap soapModel = new CalendarBookingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCalendarBookingId(model.getCalendarBookingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCalendarId(model.getCalendarId());
		soapModel.setCalendarResourceId(model.getCalendarResourceId());
		soapModel.setParentCalendarBookingId(model.getParentCalendarBookingId());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setLocation(model.getLocation());
		soapModel.setType(model.getType());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setAllDay(model.getAllDay());
		soapModel.setRecurrence(model.getRecurrence());
		soapModel.setPriority(model.getPriority());
		soapModel.setOutOfOffice(model.getOutOfOffice());
		soapModel.setRemindBy(model.getRemindBy());
		soapModel.setFirstReminder(model.getFirstReminder());
		soapModel.setSecondReminder(model.getSecondReminder());
		soapModel.setRequired(model.getRequired());
		soapModel.setRequestMessage(model.getRequestMessage());
		soapModel.setResponseMessage(model.getResponseMessage());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static CalendarBookingSoap[] toSoapModels(CalendarBooking[] models) {
		CalendarBookingSoap[] soapModels = new CalendarBookingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalendarBookingSoap[][] toSoapModels(
		CalendarBooking[][] models) {
		CalendarBookingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalendarBookingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalendarBookingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalendarBookingSoap[] toSoapModels(
		List<CalendarBooking> models) {
		List<CalendarBookingSoap> soapModels = new ArrayList<CalendarBookingSoap>(models.size());

		for (CalendarBooking model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalendarBookingSoap[soapModels.size()]);
	}

	public CalendarBookingSoap() {
	}

	public long getPrimaryKey() {
		return _calendarBookingId;
	}

	public void setPrimaryKey(long pk) {
		setCalendarBookingId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
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

	public long getCalendarId() {
		return _calendarId;
	}

	public void setCalendarId(long calendarId) {
		_calendarId = calendarId;
	}

	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;
	}

	public long getParentCalendarBookingId() {
		return _parentCalendarBookingId;
	}

	public void setParentCalendarBookingId(long parentCalendarBookingId) {
		_parentCalendarBookingId = parentCalendarBookingId;
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

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
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

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public boolean getOutOfOffice() {
		return _outOfOffice;
	}

	public boolean isOutOfOffice() {
		return _outOfOffice;
	}

	public void setOutOfOffice(boolean outOfOffice) {
		_outOfOffice = outOfOffice;
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

	public boolean getRequired() {
		return _required;
	}

	public boolean isRequired() {
		return _required;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public String getRequestMessage() {
		return _requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		_requestMessage = requestMessage;
	}

	public String getResponseMessage() {
		return _responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		_responseMessage = responseMessage;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private String _uuid;
	private long _calendarBookingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _calendarId;
	private long _calendarResourceId;
	private long _parentCalendarBookingId;
	private String _title;
	private String _description;
	private String _location;
	private String _type;
	private Date _startDate;
	private Date _endDate;
	private boolean _allDay;
	private String _recurrence;
	private int _priority;
	private boolean _outOfOffice;
	private int _remindBy;
	private int _firstReminder;
	private int _secondReminder;
	private boolean _required;
	private String _requestMessage;
	private String _responseMessage;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
}