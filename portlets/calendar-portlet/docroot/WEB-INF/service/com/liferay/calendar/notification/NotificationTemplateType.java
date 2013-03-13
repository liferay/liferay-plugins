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

/**
 * @author Eduardo Lundgren
 */
public enum NotificationTemplateType {

	REMINDER("reminder"), INVITE("invite");

	public static NotificationTemplateType parse(String value) {
		if (REMINDER.getValue().equals(value)) {
			return REMINDER;
		}
		else if (INVITE.getValue().equals(value)) {
			return INVITE;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private NotificationTemplateType(String value) {
		_value = value;
	}

	private String _value;

}