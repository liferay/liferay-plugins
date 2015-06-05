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

package com.liferay.portal.workflow.kaleo.export;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.workflow.kaleo.definition.Action;
import com.liferay.portal.workflow.kaleo.definition.AddressRecipient;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.AssignmentType;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.Notification;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.definition.NotificationType;
import com.liferay.portal.workflow.kaleo.definition.Recipient;
import com.liferay.portal.workflow.kaleo.definition.RecipientType;
import com.liferay.portal.workflow.kaleo.definition.ResourceActionAssignment;
import com.liferay.portal.workflow.kaleo.definition.RoleAssignment;
import com.liferay.portal.workflow.kaleo.definition.RoleRecipient;
import com.liferay.portal.workflow.kaleo.definition.ScriptAssignment;
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.definition.ScriptRecipient;
import com.liferay.portal.workflow.kaleo.definition.Timer;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.definition.UserAssignment;
import com.liferay.portal.workflow.kaleo.definition.UserRecipient;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public abstract class BaseNodeExporter implements NodeExporter {

	@Override
	public void exportNode(Node node, Element element, String namespace) {
		Element nodeElement = createNodeElement(element, namespace);

		addTextElement(nodeElement, "name", node.getName());

		if (Validator.isNotNull(node.getDescription())) {
			addTextElement(nodeElement, "description", node.getDescription());
		}

		if (Validator.isNotNull(node.getMetadata())) {
			addCDataElement(nodeElement, "metadata", node.getMetadata());
		}

		Set<Action> actions = node.getActions();
		Set<Notification> notifications = node.getNotifications();

		if (!actions.isEmpty() || !notifications.isEmpty()) {
			Element actionsElement = nodeElement.addElement("actions");

			exportActionsElement(
				actions, notifications, actionsElement, "action",
				"notification");
		}

		exportAdditionalNodeElements(node, nodeElement);
		exportTransitionsElement(node, nodeElement);
	}

	protected void addCDataElement(
		Element element, String elementName, String text) {

		Element childElement = element.addElement(elementName);

		childElement.addCDATA(text);
	}

	protected void addDelayDuration(
		Element timerElement, String elementName, DelayDuration delayDuration) {

		Element delayElement = timerElement.addElement(elementName);

		addTextElement(
			delayElement, "duration",
			String.valueOf(delayDuration.getDuration()));
		addTextElement(
			delayElement, "scale", delayDuration.getDurationScale().getValue());
	}

	protected void addTextElement(
		Element element, String elementName, String text) {

		Element childElement = element.addElement(elementName);

		if (Validator.isNotNull(text)) {
			childElement.addText(text);
		}
	}

	protected abstract Element createNodeElement(
		Element element, String namespace);

	protected void exportActionElement(Element actionElement, Action action) {
		addTextElement(actionElement, "name", action.getName());

		if (Validator.isNotNull(action.getDescription())) {
			addTextElement(
				actionElement, "description", action.getDescription());
		}

		populateScriptingElement(
			actionElement, action.getScript(),
			action.getScriptLanguage().getValue(),
			action.getScriptRequiredContexts());

		if (action.getPriority() > 0) {
			addTextElement(
				actionElement, "priority",
				String.valueOf(action.getPriority()));
		}

		addTextElement(
			actionElement, "execution-type",
			action.getExecutionType().getValue());
	}

	protected void exportActionsElement(
		Set<Action> actions, Set<Notification> notifications,
		Element actionsElement, String actionElementName,
		String notificationElementName) {

		for (Action action : actions) {
			Element actionElement = actionsElement.addElement(
				actionElementName);

			exportActionElement(actionElement, action);
		}

		for (Notification notification : notifications) {
			Element notificationElement = actionsElement.addElement(
				notificationElementName);

			exportNotificationElement(notificationElement, notification);
		}
	}

	protected abstract void exportAdditionalNodeElements(
		Node node, Element nodeElement);

	protected void exportAssignmentsElement(
		Set<Assignment> assignments, Element parentElement,
		String assignmentsElementName) {

		if (assignments.isEmpty()) {
			return;
		}

		Element assignmentsElement = parentElement.addElement(
			assignmentsElementName);

		Element resourceActionsElement = null;

		Element rolesElement = null;

		for (Assignment assignment : assignments) {
			AssignmentType assignmentType = assignment.getAssignmentType();

			if (assignmentType.equals(AssignmentType.RESOURCE_ACTION)) {
				if (resourceActionsElement == null) {
					resourceActionsElement = assignmentsElement.addElement(
						"resource-actions");
				}

				ResourceActionAssignment resourceActionAssignment =
					(ResourceActionAssignment)assignment;

				addTextElement(
					resourceActionsElement, "resource-action",
					resourceActionAssignment.getActionId());
			}
			else if (assignmentType.equals(AssignmentType.ROLE)) {
				if (rolesElement == null) {
					rolesElement = assignmentsElement.addElement("roles");
				}

				Element roleElement = rolesElement.addElement("role");

				RoleAssignment roleAssignment = (RoleAssignment)assignment;

				populateRoleElement(
					roleElement, roleAssignment.getRoleId(),
					roleAssignment.getRoleType(), roleAssignment.getRoleName(),
					roleAssignment.isAutoCreate());
			}
			else if (assignmentType.equals(AssignmentType.SCRIPT)) {
				Element scriptedAssignmentElement =
					assignmentsElement.addElement("scripted-assignment");

				ScriptAssignment scriptAssignment =
					(ScriptAssignment)assignment;

				populateScriptingElement(
					scriptedAssignmentElement, scriptAssignment.getScript(),
					scriptAssignment.getScriptLanguage().getValue(),
					scriptAssignment.getScriptRequiredContexts());
			}
			else if (assignmentType.equals(AssignmentType.USER)) {
				Element userElement = assignmentsElement.addElement("user");

				UserAssignment userAssignment = (UserAssignment)assignment;

				populateUserElement(
					userElement, userAssignment.getUserId(),
					userAssignment.getEmailAddress(),
					userAssignment.getScreenName());
			}
		}
	}

	protected void exportNotificationElement(
		Element notificationElement, Notification notification) {

		addTextElement(notificationElement, "name", notification.getName());

		if (Validator.isNotNull(notification.getDescription())) {
			addTextElement(
				notificationElement, "description",
				notification.getDescription());
		}

		addCDataElement(
			notificationElement, "template", notification.getTemplate());
		addTextElement(
			notificationElement, "template-language",
			notification.getTemplateLanguage().getValue());

		Set<NotificationType> notificationTypes =
			notification.getNotificationTypes();

		for (NotificationType notificationType : notificationTypes) {
			addTextElement(
				notificationElement, "notification-type",
				notificationType.getValue());
		}

		Map<NotificationReceptionType, Set<Recipient>> recipientsMap =
			notification.getRecipientsMap();

		for (Map.Entry<NotificationReceptionType, Set<Recipient>> entry :
				recipientsMap.entrySet()) {

			Set<Recipient> recipients = entry.getValue();
			NotificationReceptionType notificationReceptionType =
				entry.getKey();

			exportRecipientsElement(
				notificationElement, recipients, notificationReceptionType);
		}

		addTextElement(
			notificationElement, "execution-type",
			notification.getExecutionType().getValue());
	}

	protected void exportRecipientsElement(
		Element notificationElement, Set<Recipient> recipients,
		NotificationReceptionType notificationReceptionType) {

		if (recipients.isEmpty()) {
			return;
		}

		Element recipientsElement = notificationElement.addElement(
			"recipients");

		recipientsElement.addAttribute(
			"receptionType", notificationReceptionType.getValue());

		Element rolesElement = null;

		for (Recipient recipient : recipients) {
			RecipientType recipientType = recipient.getRecipientType();

			if (recipientType.equals(RecipientType.ADDRESS)) {
				AddressRecipient addressRecipient = (AddressRecipient)recipient;

				addTextElement(
					recipientsElement, "address",
					addressRecipient.getAddress());
			}
			else if (recipientType.equals(RecipientType.ASSIGNEES)) {
				addTextElement(recipientsElement, "assignees", null);
			}
			else if (recipientType.equals(RecipientType.ROLE)) {
				if (rolesElement == null) {
					rolesElement = recipientsElement.addElement("roles");
				}

				Element roleElement = rolesElement.addElement("role");

				RoleRecipient roleRecipient = (RoleRecipient)recipient;

				populateRoleElement(
					roleElement, roleRecipient.getRoleId(),
					roleRecipient.getRoleType(), roleRecipient.getRoleName(),
					roleRecipient.isAutoCreate());
			}
			else if (recipientType.equals(RecipientType.SCRIPT)) {
				Element scriptedRecipientElement = recipientsElement.addElement(
					"scripted-recipient");

				ScriptRecipient scriptRecipient = (ScriptRecipient)recipient;

				ScriptLanguage scriptLanguage =
					scriptRecipient.getScriptLanguage();

				populateScriptingElement(
					scriptedRecipientElement, scriptRecipient.getScript(),
					scriptLanguage.getValue(),
					scriptRecipient.getScriptRequiredContexts());
			}
			else if (recipientType.equals(RecipientType.USER)) {
				Element userElement = recipientsElement.addElement("user");

				UserRecipient userRecipient = (UserRecipient)recipient;

				populateUserElement(
					userElement, userRecipient.getUserId(),
					userRecipient.getEmailAddress(),
					userRecipient.getScreenName());
			}
		}
	}

	protected void exportTimersElement(
		Node node, Element nodeElement, String timersElementName,
		String timerElementName) {

		Set<Timer> timers = node.getTimers();

		if (timers.isEmpty()) {
			return;
		}

		Element timersElement = nodeElement.addElement(timersElementName);

		for (Timer timer : timers) {
			Element timerElement = timersElement.addElement(timerElementName);

			addTextElement(timerElement, "name", timer.getName());

			if (Validator.isNotNull(timer.getDescription())) {
				addTextElement(
					timerElement, "description", timer.getDescription());
			}

			DelayDuration delayDuration = timer.getDelayDuration();

			addDelayDuration(timerElement, "delay", delayDuration);

			DelayDuration recurrenceDelayDuration = timer.getRecurrence();

			if (recurrenceDelayDuration != null) {
				addDelayDuration(
					timerElement, "recurrence", recurrenceDelayDuration);
			}

			if (timer.isBlocking()) {
				addTextElement(
					timerElement, "blocking",
					String.valueOf(timer.isBlocking()));
			}

			Set<Action> actions = timer.getActions();

			Set<Notification> notifications = timer.getNotifications();

			Set<Assignment> assignments = timer.getReassignments();

			if (!actions.isEmpty() || !notifications.isEmpty() ||
				!assignments.isEmpty()) {

				Element timerActionsElement = timerElement.addElement(
					"timer-actions");

				exportActionsElement(
					actions, notifications, timerActionsElement, "timer-action",
					"timer-notification");
				exportAssignmentsElement(
					assignments, timerActionsElement, "reassignments");
			}
		}
	}

	protected void exportTransitionsElement(Node node, Element nodeElement) {
		List<Transition> outgoingTransitions =
			node.getOutgoingTransitionsList();

		if (outgoingTransitions.isEmpty()) {
			return;
		}

		Element transitionsElement = nodeElement.addElement("transitions");

		for (Transition outgoingTransition : outgoingTransitions) {
			Element transition = transitionsElement.addElement("transition");

			addTextElement(transition, "name", outgoingTransition.getName());
			addTextElement(
				transition, "target",
				outgoingTransition.getTargetNode().getName());

			if (outgoingTransition.isDefault()) {
				addTextElement(
					transition, "default",
					String.valueOf(outgoingTransition.isDefault()));
			}
		}
	}

	protected void populateRoleElement(
		Element roleElement, long roleId, String roleType, String roleName,
		boolean autoCreate) {

		if (roleId > 0) {
			addTextElement(roleElement, "role-id", String.valueOf(roleId));
		}
		else {
			addTextElement(roleElement, "role-type", roleType);
			addTextElement(roleElement, "name", roleName);

			if (!autoCreate) {
				addTextElement(
					roleElement, "auto-create", String.valueOf(autoCreate));
			}
		}
	}

	protected void populateScriptingElement(
		Element scriptingElement, String script, String scriptLanguage,
		String scriptRequiredContexts) {

		addCDataElement(scriptingElement, "script", script);
		addTextElement(scriptingElement, "script-language", scriptLanguage);

		if (Validator.isNotNull(scriptRequiredContexts)) {
			addTextElement(
				scriptingElement, "script-required-contexts",
				scriptRequiredContexts);
		}
	}

	protected void populateUserElement(
		Element userElement, long userId, String emailAddress,
		String screenName) {

		if (userId > 0) {
			addTextElement(userElement, "user-id", String.valueOf(userId));
		}

		if (Validator.isNotNull(emailAddress)) {
			addTextElement(userElement, "email-address", emailAddress);
		}

		if (Validator.isNotNull(screenName)) {
			addTextElement(userElement, "screen-name", screenName);
		}
	}

}