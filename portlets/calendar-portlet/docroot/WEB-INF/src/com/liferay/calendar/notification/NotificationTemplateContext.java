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

	public long getCalendarId() {
		return _calendarId;
	}

	public CalendarNotificationTemplate getCalendarNotificationTemplate() {
		return _calendarNotificationTemplate;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getFromAddress() {
		return _fromAddress;
	}

	public String getFromName() {
		return _fromName;
	}

	public long getGroupId() {
		return _groupId;
	}

	public NotificationTemplateType getNotificationTemplateType() {
		return _notificationTemplateType;
	}

	public NotificationType getNotificationType() {
		return _notificationType;
	}

	public String getString(String name) {
		Serializable value = getAttribute(name);

		return GetterUtil.getString(value);
	}

	public String getToAddress() {
		return _toAddress;
	}

	public String getToName() {
		return _toName;
	}

	public void setAttribute(String name, Serializable value) {
		_attributes.put(name, value);
	}

	public void setAttributes(Map<String, Serializable> attributes) {
		_attributes = attributes;
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

	public void setFromAddress(String fromAddress) {
		_fromAddress = fromAddress;
	}

	public void setFromName(String fromName) {
		_fromName = fromName;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setNotificationTemplateType(
		NotificationTemplateType notificationTemplateType) {

		_notificationTemplateType = notificationTemplateType;
	}

	public void setNotificationType(NotificationType notificationType) {
		_notificationType = notificationType;
	}

	public void setToAddress(String toAddress) {
		_toAddress = toAddress;
	}

	public void setToName(String toName) {
		_toName = toName;
	}

	private Map<String, Serializable> _attributes =
		new LinkedHashMap<String, Serializable>();
	private long _calendarId;
	private CalendarNotificationTemplate _calendarNotificationTemplate;
	private long _companyId;
	private String _fromAddress;
	private String _fromName;
	private long _groupId;
	private NotificationTemplateType _notificationTemplateType;
	private NotificationType _notificationType;
	private String _toAddress;
	private String _toName;

}