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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.calendar.service.http.CalendarServiceSoap}.
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.http.CalendarServiceSoap
 * @generated
 */
public class CalendarSoap implements Serializable {
	public static CalendarSoap toSoapModel(Calendar model) {
		CalendarSoap soapModel = new CalendarSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCalendarId(model.getCalendarId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setResourceBlockId(model.getResourceBlockId());
		soapModel.setCalendarResourceId(model.getCalendarResourceId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setTimeZoneId(model.getTimeZoneId());
		soapModel.setColor(model.getColor());
		soapModel.setDefaultCalendar(model.getDefaultCalendar());
		soapModel.setEnableComments(model.getEnableComments());
		soapModel.setEnableRatings(model.getEnableRatings());

		return soapModel;
	}

	public static CalendarSoap[] toSoapModels(Calendar[] models) {
		CalendarSoap[] soapModels = new CalendarSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalendarSoap[][] toSoapModels(Calendar[][] models) {
		CalendarSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalendarSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalendarSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalendarSoap[] toSoapModels(List<Calendar> models) {
		List<CalendarSoap> soapModels = new ArrayList<CalendarSoap>(models.size());

		for (Calendar model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalendarSoap[soapModels.size()]);
	}

	public CalendarSoap() {
	}

	public long getPrimaryKey() {
		return _calendarId;
	}

	public void setPrimaryKey(long pk) {
		setCalendarId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCalendarId() {
		return _calendarId;
	}

	public void setCalendarId(long calendarId) {
		_calendarId = calendarId;
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

	public long getResourceBlockId() {
		return _resourceBlockId;
	}

	public void setResourceBlockId(long resourceBlockId) {
		_resourceBlockId = resourceBlockId;
	}

	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getTimeZoneId() {
		return _timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;
	}

	public int getColor() {
		return _color;
	}

	public void setColor(int color) {
		_color = color;
	}

	public boolean getDefaultCalendar() {
		return _defaultCalendar;
	}

	public boolean isDefaultCalendar() {
		return _defaultCalendar;
	}

	public void setDefaultCalendar(boolean defaultCalendar) {
		_defaultCalendar = defaultCalendar;
	}

	public boolean getEnableComments() {
		return _enableComments;
	}

	public boolean isEnableComments() {
		return _enableComments;
	}

	public void setEnableComments(boolean enableComments) {
		_enableComments = enableComments;
	}

	public boolean getEnableRatings() {
		return _enableRatings;
	}

	public boolean isEnableRatings() {
		return _enableRatings;
	}

	public void setEnableRatings(boolean enableRatings) {
		_enableRatings = enableRatings;
	}

	private String _uuid;
	private long _calendarId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _resourceBlockId;
	private long _calendarResourceId;
	private String _name;
	private String _description;
	private String _timeZoneId;
	private int _color;
	private boolean _defaultCalendar;
	private boolean _enableComments;
	private boolean _enableRatings;
}