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

package com.liferay.calendar.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.IOException;

/**
 * @author Adam Brandizzi
 */
public class CalendarNotificationTemplateImpl
	extends CalendarNotificationTemplateBaseImpl {

	public CalendarNotificationTemplateImpl() {
	}

	@Override
	public String getNotificationTypeSettings() {
		if (_notificationTypeSettingsProperties == null) {
			return super.getNotificationTypeSettings();
		}
		else {
			return _notificationTypeSettingsProperties.toString();
		}
	}

	@Override
	public UnicodeProperties getNotificationTypeSettingsProperties() {
		if (_notificationTypeSettingsProperties == null) {
			_notificationTypeSettingsProperties = new UnicodeProperties(true);

			try {
				_notificationTypeSettingsProperties.load(
					super.getNotificationTypeSettings());
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
		}

		return _notificationTypeSettingsProperties;
	}

	@Override
	public void setNotificationTypeSettings(String notificationTypeSettings) {
		_notificationTypeSettingsProperties = null;

		super.setNotificationTypeSettings(notificationTypeSettings);
	}

	@Override
	public void setTypeSettingsProperties(
		UnicodeProperties notificationTypeSettingsProperties) {

		_notificationTypeSettingsProperties =
			notificationTypeSettingsProperties;

		super.setNotificationTypeSettings(
			_notificationTypeSettingsProperties.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(
		CalendarNotificationTemplateImpl.class);

	private UnicodeProperties _notificationTypeSettingsProperties;

}