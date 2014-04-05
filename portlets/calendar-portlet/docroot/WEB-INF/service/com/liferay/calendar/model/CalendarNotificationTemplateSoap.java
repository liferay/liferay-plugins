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
 * This class is used by SOAP remote services, specifically {@link com.liferay.calendar.service.http.CalendarNotificationTemplateServiceSoap}.
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.http.CalendarNotificationTemplateServiceSoap
 * @generated
 */
public class CalendarNotificationTemplateSoap implements Serializable {
	public static CalendarNotificationTemplateSoap toSoapModel(
		CalendarNotificationTemplate model) {
		CalendarNotificationTemplateSoap soapModel = new CalendarNotificationTemplateSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCalendarNotificationTemplateId(model.getCalendarNotificationTemplateId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCalendarId(model.getCalendarId());
		soapModel.setNotificationType(model.getNotificationType());
		soapModel.setNotificationTypeSettings(model.getNotificationTypeSettings());
		soapModel.setNotificationTemplateType(model.getNotificationTemplateType());
		soapModel.setSubject(model.getSubject());
		soapModel.setBody(model.getBody());

		return soapModel;
	}

	public static CalendarNotificationTemplateSoap[] toSoapModels(
		CalendarNotificationTemplate[] models) {
		CalendarNotificationTemplateSoap[] soapModels = new CalendarNotificationTemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalendarNotificationTemplateSoap[][] toSoapModels(
		CalendarNotificationTemplate[][] models) {
		CalendarNotificationTemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalendarNotificationTemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalendarNotificationTemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalendarNotificationTemplateSoap[] toSoapModels(
		List<CalendarNotificationTemplate> models) {
		List<CalendarNotificationTemplateSoap> soapModels = new ArrayList<CalendarNotificationTemplateSoap>(models.size());

		for (CalendarNotificationTemplate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalendarNotificationTemplateSoap[soapModels.size()]);
	}

	public CalendarNotificationTemplateSoap() {
	}

	public long getPrimaryKey() {
		return _calendarNotificationTemplateId;
	}

	public void setPrimaryKey(long pk) {
		setCalendarNotificationTemplateId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCalendarNotificationTemplateId() {
		return _calendarNotificationTemplateId;
	}

	public void setCalendarNotificationTemplateId(
		long calendarNotificationTemplateId) {
		_calendarNotificationTemplateId = calendarNotificationTemplateId;
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

	public String getNotificationType() {
		return _notificationType;
	}

	public void setNotificationType(String notificationType) {
		_notificationType = notificationType;
	}

	public String getNotificationTypeSettings() {
		return _notificationTypeSettings;
	}

	public void setNotificationTypeSettings(String notificationTypeSettings) {
		_notificationTypeSettings = notificationTypeSettings;
	}

	public String getNotificationTemplateType() {
		return _notificationTemplateType;
	}

	public void setNotificationTemplateType(String notificationTemplateType) {
		_notificationTemplateType = notificationTemplateType;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	private String _uuid;
	private long _calendarNotificationTemplateId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _calendarId;
	private String _notificationType;
	private String _notificationTypeSettings;
	private String _notificationTemplateType;
	private String _subject;
	private String _body;
}