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
 * @author Pier Paolo Ramon
 */
public enum NotificationTemplateType {

	INVITE("invite"), MOVED_TO_TRASH("moved-to-trash"), REMINDER("reminder");

	public static NotificationTemplateType parse(String value) {
		if (INVITE.getValue().equals(value)) {
			return INVITE;
		}
		else if (MOVED_TO_TRASH.getValue().equals(value)) {
			return MOVED_TO_TRASH;
		}
		else if (REMINDER.getValue().equals(value)) {
			return REMINDER;
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