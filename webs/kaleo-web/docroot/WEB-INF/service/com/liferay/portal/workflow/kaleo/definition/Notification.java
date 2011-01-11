/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
 * @author Michael C. Han
 */
public class Notification extends DefinitionNode {

	public void configureParent(DefinitionNode parentNode) {
		Actions actions = (Actions)parentNode;

		actions.addNotification(this);
	}

	public void addNotificationType(NotificationType notificationType) {
		_notificationTypes.add(notificationType);
	}

	public void addRecipient(Recipient recipient) {
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

	public String getDescription() {
		return _description;
	}

	public ExecutionType getExecutionType() {
		return _executionType;
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

	public TemplateLanguage getTemplateLanguage() {
		return _templateLanguage;
	}

	public int hashCode() {
		return _name.hashCode();
	}

	public void setExecutionType(ExecutionType executionType) {
		_executionType = executionType;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setTemplate(String template) {
		_template = template;
	}

	public void setTemplateLanguage(TemplateLanguage templateLanguage) {
		_templateLanguage = templateLanguage;
	}

	private String _description;
	private ExecutionType _executionType;
	private String _name;
	private Set<NotificationType> _notificationTypes =
		new HashSet<NotificationType>();
	private Set<Recipient> _recipients = new HashSet<Recipient>();
	private String _template;
	private TemplateLanguage _templateLanguage;

}