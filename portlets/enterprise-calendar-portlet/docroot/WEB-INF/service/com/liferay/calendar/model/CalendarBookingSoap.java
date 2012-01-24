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
	public CalendarBookingSoap() {
	}

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

	public boolean getAllDay() {
		return _allDay;
	}

	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	public long getCalendarId() {
		return _calendarId;
	}

	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getDescription() {
		return _description;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public int getFirstReminder() {
		return _firstReminder;
	}

	public long getGroupId() {
		return _groupId;
	}

	public String getLocation() {
		return _location;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean getOutOfOffice() {
		return _outOfOffice;
	}

	public long getParentCalendarBookingId() {
		return _parentCalendarBookingId;
	}

	public long getPrimaryKey() {
		return _calendarBookingId;
	}

	public int getPriority() {
		return _priority;
	}

	public String getRecurrence() {
		return _recurrence;
	}

	public int getRemindBy() {
		return _remindBy;
	}

	public String getRequestMessage() {
		return _requestMessage;
	}

	public boolean getRequired() {
		return _required;
	}

	public String getResponseMessage() {
		return _responseMessage;
	}

	public int getSecondReminder() {
		return _secondReminder;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public int getStatus() {
		return _status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public String getTitle() {
		return _title;
	}

	public String getType() {
		return _type;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUserName() {
		return _userName;
	}

	public String getUuid() {
		return _uuid;
	}

	public boolean isAllDay() {
		return _allDay;
	}

	public boolean isOutOfOffice() {
		return _outOfOffice;
	}

	public boolean isRequired() {
		return _required;
	}

	public void setAllDay(boolean allDay) {
		_allDay = allDay;
	}

	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
	}

	public void setCalendarId(long calendarId) {
		_calendarId = calendarId;
	}

	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public void setFirstReminder(int firstReminder) {
		_firstReminder = firstReminder;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setLocation(String location) {
		_location = location;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public void setOutOfOffice(boolean outOfOffice) {
		_outOfOffice = outOfOffice;
	}

	public void setParentCalendarBookingId(long parentCalendarBookingId) {
		_parentCalendarBookingId = parentCalendarBookingId;
	}

	public void setPrimaryKey(long pk) {
		setCalendarBookingId(pk);
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public void setRecurrence(String recurrence) {
		_recurrence = recurrence;
	}

	public void setRemindBy(int remindBy) {
		_remindBy = remindBy;
	}

	public void setRequestMessage(String requestMessage) {
		_requestMessage = requestMessage;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public void setResponseMessage(String responseMessage) {
		_responseMessage = responseMessage;
	}

	public void setSecondReminder(int secondReminder) {
		_secondReminder = secondReminder;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
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