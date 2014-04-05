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
 * This class is used by SOAP remote services, specifically {@link com.liferay.calendar.service.http.CalendarResourceServiceSoap}.
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.http.CalendarResourceServiceSoap
 * @generated
 */
public class CalendarResourceSoap implements Serializable {
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
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setClassUuid(model.getClassUuid());
		soapModel.setCode(model.getCode());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
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

	public CalendarResourceSoap() {
	}

	public long getPrimaryKey() {
		return _calendarResourceId;
	}

	public void setPrimaryKey(long pk) {
		setCalendarResourceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;
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

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getClassUuid() {
		return _classUuid;
	}

	public void setClassUuid(String classUuid) {
		_classUuid = classUuid;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
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

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
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
	private long _classNameId;
	private long _classPK;
	private String _classUuid;
	private String _code;
	private String _name;
	private String _description;
	private boolean _active;
}