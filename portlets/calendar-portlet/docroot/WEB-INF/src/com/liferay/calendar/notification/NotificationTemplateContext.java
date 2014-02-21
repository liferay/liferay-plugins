/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.notification;

import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.util.PortletPropsValues;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 */
public class NotificationTemplateContext implements Cloneable, Serializable {

	public NotificationTemplateContext() {
		_notificationType = NotificationType.parse(
			PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE);
	}

	public NotificationTemplateContext(NotificationType notificationType) {
		_notificationType = notificationType;
	}

	public Serializable getAttribute(String name) {
		return _attributes.get(name);
	}

	public Map<String, Serializable> getAttributes() {
		return _attributes;
	}

	public String getBody() {
		return _body;
	}

	public long getCalendarId() {
		return _calendarId;
	}

	public CalendarNotificationTemplate getCalendarNotificationTemplate() {
		return _calendarNotificationTemplate;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public NotificationType getNotificationType() {
		return _notificationType;
	}

	public String getString(String name) {
		Serializable value = getAttribute(name);

		return GetterUtil.getString(value);
	}

	public String getSubject() {
		return _subject;
	}

	public void setAttribute(String name, Serializable value) {
		_attributes.put(name, value);
	}

	public void setAttributes(Map<String, Serializable> attributes) {
		_attributes = attributes;
	}

	public void setBody(String body) {
		_body = body;
	}

	public void setCalendarId(long calendarId) {
		_calendarId = calendarId;
	}

	public void setCalendarNotificationTemplate(
		CalendarNotificationTemplate calendarNotificationTemplate) {

		_calendarNotificationTemplate = calendarNotificationTemplate;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setNotificationType(NotificationType notificationType) {
		_notificationType = notificationType;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	private Map<String, Serializable> _attributes =
		new LinkedHashMap<String, Serializable>();
	private String _body;
	private long _calendarId;
	private CalendarNotificationTemplate _calendarNotificationTemplate;
	private long _companyId;
	private long _groupId;
	private NotificationType _notificationType;
	private String _subject;

}