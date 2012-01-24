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
 * This class is used by SOAP remote services, specifically {@link com.liferay.calendar.service.http.CalendarResourceServiceSoap}.
 *
 * @author    Eduardo Lundgren
 * @see       com.liferay.calendar.service.http.CalendarResourceServiceSoap
 * @generated
 */
public class CalendarResourceSoap implements Serializable {
	public CalendarResourceSoap() {
	}

	public static CalendarResourceSoap toSoapModel(CalendarResource model) {
		CalendarResourceSoap soapModel = new CalendarResourceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCalendarResourceId(model.getCalendarResourceId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setResourceBlockId(model.getResourceBlockId());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setClassUuid(model.getClassUuid());
		soapModel.setDefaultCalendarId(model.getDefaultCalendarId());
		soapModel.setCode(model.getCode());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setType(model.getType());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static CalendarResourceSoap[] toSoapModels(CalendarResource[] models) {
		CalendarResourceSoap[] soapModels = new CalendarResourceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalendarResourceSoap[][] toSoapModels(
		CalendarResource[][] models) {
		CalendarResourceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalendarResourceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalendarResourceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalendarResourceSoap[] toSoapModels(
		List<CalendarResource> models) {
		List<CalendarResourceSoap> soapModels = new ArrayList<CalendarResourceSoap>(models.size());

		for (CalendarResource model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalendarResourceSoap[soapModels.size()]);
	}

	public boolean getActive() {
		return _active;
	}

	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public String getClassUuid() {
		return _classUuid;
	}

	public String getCode() {
		return _code;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public long getDefaultCalendarId() {
		return _defaultCalendarId;
	}

	public String getDescription() {
		return _description;
	}

	public long getGroupId() {
		return _groupId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public long getPrimaryKey() {
		return _calendarResourceId;
	}

	public long getResourceBlockId() {
		return _resourceBlockId;
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

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setClassUuid(String classUuid) {
		_classUuid = classUuid;
	}

	public void setCode(String code) {
		_code = code;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setDefaultCalendarId(long defaultCalendarId) {
		_defaultCalendarId = defaultCalendarId;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPrimaryKey(long pk) {
		setCalendarResourceId(pk);
	}

	public void setResourceBlockId(long resourceBlockId) {
		_resourceBlockId = resourceBlockId;
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
	private long _calendarResourceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _resourceBlockId;
	private String _className;
	private long _classPK;
	private String _classUuid;
	private long _defaultCalendarId;
	private String _code;
	private String _name;
	private String _description;
	private String _type;
	private boolean _active;
}