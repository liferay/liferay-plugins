/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.definition;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="Notification.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class Notification {
	public enum Language {
		TEXT("text"), VELOCITY("velocity"), FREEMARKER("freemarker");

		public static Language parse(String value) {
			if (TEXT._value.equals(value)) {
				return TEXT;
			}
			else if (VELOCITY._value.equals(value)) {
				return VELOCITY;
			}
			else if (FREEMARKER._value.equals(value)) {
				return FREEMARKER;
			}

			throw new IllegalArgumentException("Invalid value " + value);
		}

		public String getValue() {
			return _value;
		}

		private Language(String value) {
			_value = value;
		}

		private String _value;
	}

	public enum Type {
		EMAIL("email"), PRIVATE_MESSAGE("private-message"), IM("im");

		public static Type parse(String value) {
			if (EMAIL._value.equals(value)) {
				return EMAIL;
			}
			else if (PRIVATE_MESSAGE._value.equals(value)) {
				return PRIVATE_MESSAGE;
			}
			else if (IM._value.equals(value)) {
				return IM;
			}

			throw new IllegalArgumentException("Invalid value " + value);
		}

		public String getValue() {
			return _value;
		}

		private Type(String value) {
			_value = value;
		}

		private String _value;
	}

	public Notification(String name, String language) {
		_name = name;
		_language = Language.parse(language);
	}

	public void addNotificationType(String notificationType) {
		_notificationTypes.add(Type.parse(notificationType));
	}

	public void addRecipients(Recipient recipient) {
		_recipients.add(recipient);
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		Notification notification = (Notification) object;

		return notification.getName().equals(getName());
	}

	public String getDescription() {
		return _description;
	}

	public Language getLanguage() {
		return _language;
	}

	public Set<Type> getNotificationTypes() {
		return _notificationTypes;
	}

	public String getName() {
		return _name;
	}

	public Set<Recipient> getRecipients() {
		return _recipients;
	}

	public String getTemplate() {
		return _template;
	}

	public int hashCode() {
		return _name.hashCode();
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setTemplate(String template) {
		_template = template;
	}

	private String _description;
	private Language _language;
	private String _name;
	private Set<Type> _notificationTypes = new HashSet<Type>();
	private Set<Recipient> _recipients = new HashSet<Recipient>();
	private String _template;

}