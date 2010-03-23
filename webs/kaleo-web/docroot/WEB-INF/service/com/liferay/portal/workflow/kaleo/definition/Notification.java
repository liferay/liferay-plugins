/**
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

import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="Notification.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class Notification {

	public Notification(String name, String language, String actionType) {
		_name = name;
		_language = Language.parse(language);
		_actionType = ActionType.parse(actionType);
	}

	public void addNotificationType(String notificationType) {
		_notificationTypes.add(NotificationType.parse(notificationType));
	}

	public void addRecipients(Recipient recipient) {
		_recipients.add(recipient);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Notification)) {
			return false;
		}

		Notification notification = (Notification)obj;

		if (Validator.equals(_name, notification._name)) {
			return true;
		}

		return true;
	}

	public ActionType getActionType() {
		return _actionType;
	}

	public String getDescription() {
		return _description;
	}

	public Language getLanguage() {
		return _language;
	}

	public String getName() {
		return _name;
	}

	public Set<NotificationType> getNotificationTypes() {
		return _notificationTypes;
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

	private ActionType _actionType;
	private String _description;
	private Language _language;
	private String _name;
	private Set<NotificationType> _notificationTypes =
		new HashSet<NotificationType>();
	private Set<Recipient> _recipients = new HashSet<Recipient>();
	private String _template;

}