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

package com.liferay.portal.workflow.kaleo.parser;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.workflow.kaleo.definition.Action;
import com.liferay.portal.workflow.kaleo.definition.ActionAware;
import com.liferay.portal.workflow.kaleo.definition.AddressRecipient;
import com.liferay.portal.workflow.kaleo.definition.AssigneesRecipient;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.definition.Fork;
import com.liferay.portal.workflow.kaleo.definition.Join;
import com.liferay.portal.workflow.kaleo.definition.JoinXor;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.Notification;
import com.liferay.portal.workflow.kaleo.definition.NotificationAware;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.definition.ResourceActionAssignment;
import com.liferay.portal.workflow.kaleo.definition.RoleAssignment;
import com.liferay.portal.workflow.kaleo.definition.RoleRecipient;
import com.liferay.portal.workflow.kaleo.definition.ScriptAssignment;
import com.liferay.portal.workflow.kaleo.definition.ScriptRecipient;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.Timer;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.definition.UserAssignment;
import com.liferay.portal.workflow.kaleo.definition.UserRecipient;

import java.io.InputStream;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
public class XMLWorkflowModelParser implements WorkflowModelParser {

	@Override
	public Definition parse(InputStream inputStream) throws WorkflowException {
		try {
			return doParse(inputStream);
		}
		catch (Exception e) {
			throw new WorkflowException("Unable to parse definition", e);
		}
	}

	public void setValidate(boolean validate) {
		_validate = validate;
	}

	protected Definition doParse(InputStream inputStream) throws Exception {
		Document document = SAXReaderUtil.read(inputStream, _validate);

		Element rootElement = document.getRootElement();

		String name = rootElement.elementText("name");
		String description = rootElement.elementText("description");
		int version = GetterUtil.getInteger(rootElement.elementText("version"));

		Definition definition = new Definition(
			name, description, document.formattedString(), version);

		List<Element> conditionElements = rootElement.elements("condition");

		for (Element conditionElement : conditionElements) {
			Condition condition = parseCondition(conditionElement);

			definition.addNode(condition);
		}

		List<Element> forkElements = rootElement.elements("fork");

		for (Element forkElement : forkElements) {
			Fork fork = parseFork(forkElement);

			definition.addNode(fork);
		}

		List<Element> joinElements = rootElement.elements("join");

		for (Element joinElement : joinElements) {
			Join join = parseJoin(joinElement);

			definition.addNode(join);
		}

		List<Element> joinXorElements = rootElement.elements("join-xor");

		for (Element joinXorElement : joinXorElements) {
			JoinXor joinXor = parseJoinXor(joinXorElement);

			definition.addNode(joinXor);
		}

		List<Element> stateElements = rootElement.elements("state");

		for (Element stateElement : stateElements) {
			State state = parseState(stateElement);

			definition.addNode(state);
		}

		List<Element> taskElements = rootElement.elements("task");

		for (Element taskElement : taskElements) {
			Task task = parseTask(taskElement);

			definition.addNode(task);
		}

		parseTransitions(
			definition, conditionElements, forkElements, joinElements,
			joinXorElements, stateElements, taskElements);

		return definition;
	}

	protected void parseActionElements(
		List<Element> actionElements, ActionAware actionAware) {

		if (actionElements.isEmpty()) {
			return;
		}

		Set<Action> actions = new HashSet<>(actionElements.size());

		for (Element actionElement : actionElements) {
			String name = actionElement.elementText("name");
			String description = actionElement.elementText("description");
			String executionType = actionElement.elementText("execution-type");
			String script = actionElement.elementText("script");
			String scriptLanguage = actionElement.elementText(
				"script-language");
			String scriptRequiredContexts = actionElement.elementText(
				"script-required-contexts");
			int priority = GetterUtil.getInteger(
				actionElement.elementText("priority"));

			Action action = new Action(
				name, description, executionType, script, scriptLanguage,
				scriptRequiredContexts, priority);

			actions.add(action);
		}

		actionAware.setActions(actions);
	}

	protected void parseActionsElement(Element actionsElement, Node node) {
		if (actionsElement == null) {
			return;
		}

		List<Element> actionElements = actionsElement.elements("action");

		parseActionElements(actionElements, node);

		List<Element> notificationElements = actionsElement.elements(
			"notification");

		parseNotificationElements(notificationElements, node);
	}

	protected Set<Assignment> parseAssignments(Element assignmentsElement) {
		if (assignmentsElement == null) {
			return Collections.emptySet();
		}

		Set<Assignment> assignments = new HashSet<>();

		Element resourceActionsElement = assignmentsElement.element(
			"resource-actions");

		if (resourceActionsElement != null) {
			List<Element> resourceActionElements =
				resourceActionsElement.elements("resource-action");

			for (Element resourceActionElement : resourceActionElements) {
				String actionId = resourceActionElement.getText();

				if (Validator.isNotNull(actionId)) {
					ResourceActionAssignment resourceActionAssignment =
						new ResourceActionAssignment(actionId);

					assignments.add(resourceActionAssignment);
				}
			}
		}

		Element rolesElement = assignmentsElement.element("roles");

		if (rolesElement != null) {
			List<Element> roleAssignmentElements = rolesElement.elements(
				"role");

			for (Element roleAssignmentElement : roleAssignmentElements) {
				long roleId = GetterUtil.getLong(
					roleAssignmentElement.elementText("role-id"));
				String roleType = roleAssignmentElement.elementText(
					"role-type");
				String name = roleAssignmentElement.elementText("name");

				RoleAssignment roleAssignment = null;

				if (Validator.isNotNull(name)) {
					roleAssignment = new RoleAssignment(name, roleType);

					boolean autoCreate = GetterUtil.getBoolean(
						roleAssignmentElement.elementText("auto-create"), true);

					roleAssignment.setAutoCreate(autoCreate);
				}
				else {
					roleAssignment = new RoleAssignment(roleId);
				}

				assignments.add(roleAssignment);
			}
		}

		List<Element> scriptedAssignmentElements = assignmentsElement.elements(
			"scripted-assignment");

		for (Element scriptedAssignmentElement : scriptedAssignmentElements) {
			String script = scriptedAssignmentElement.elementText("script");
			String scriptLanguage = scriptedAssignmentElement.elementText(
				"script-language");
			String scriptRequiredContexts =
				scriptedAssignmentElement.elementText(
					"script-required-contexts");

			ScriptAssignment scriptAssignment = new ScriptAssignment(
				script, scriptLanguage, scriptRequiredContexts);

			assignments.add(scriptAssignment);
		}

		List<Element> userAssignmentElements = assignmentsElement.elements(
			"user");

		for (Element userAssignmentElement : userAssignmentElements) {
			long userId = GetterUtil.getLong(
				userAssignmentElement.elementText("user-id"));
			String screenName = userAssignmentElement.elementText(
				"screen-name");
			String emailAddress = userAssignmentElement.elementText(
				"email-address");

			UserAssignment userAssignment = new UserAssignment(
				userId, screenName, emailAddress);

			assignments.add(userAssignment);
		}

		return assignments;
	}

	protected Condition parseCondition(Element conditionElement) {
		String name = conditionElement.elementText("name");
		String description = conditionElement.elementText("description");
		String script = conditionElement.elementText("script");
		String scriptLanguage = conditionElement.elementText("script-language");
		String scriptRequiredContexts = conditionElement.elementText(
			"script-required-contexts");

		Condition condition = new Condition(
			name, description, script, scriptLanguage, scriptRequiredContexts);

		String metadata = conditionElement.elementText("metadata");

		condition.setMetadata(metadata);

		Element actionsElement = conditionElement.element("actions");

		parseActionsElement(actionsElement, condition);

		Element timersElement = conditionElement.element("timers");

		parseTimerElements(timersElement, condition);

		return condition;
	}

	protected DelayDuration parseDelay(Element delayElement) {
		if (delayElement == null) {
			return null;
		}

		double duration = GetterUtil.getDouble(
			delayElement.elementText("duration"));
		DurationScale durationScale = DurationScale.parse(
			delayElement.elementText("scale"));

		return new DelayDuration(duration, durationScale);
	}

	protected Fork parseFork(Element forkElement) {
		String name = forkElement.elementText("name");
		String description = forkElement.elementText("description");

		Fork fork = new Fork(name, description);

		String metadata = forkElement.elementText("metadata");

		fork.setMetadata(metadata);

		Element actionsElement = forkElement.element("actions");

		parseActionsElement(actionsElement, fork);

		Element timersElement = forkElement.element("timers");

		parseTimerElements(timersElement, fork);

		return fork;
	}

	protected Join parseJoin(Element joinElement) {
		String name = joinElement.elementText("name");
		String description = joinElement.elementText("description");

		Join join = new Join(name, description);

		String metadata = joinElement.elementText("metadata");

		join.setMetadata(metadata);

		Element actionsElement = joinElement.element("actions");

		parseActionsElement(actionsElement, join);

		Element timersElement = joinElement.element("timers");

		parseTimerElements(timersElement, join);

		return join;
	}

	protected JoinXor parseJoinXor(Element joinXorElement) {
		String name = joinXorElement.elementText("name");
		String description = joinXorElement.elementText("description");

		JoinXor joinXor = new JoinXor(name, description);

		String metadata = joinXorElement.elementText("metadata");

		joinXor.setMetadata(metadata);

		Element actionsElement = joinXorElement.element("actions");

		parseActionsElement(actionsElement, joinXor);

		Element timersElement = joinXorElement.element("timers");

		parseTimerElements(timersElement, joinXor);

		return joinXor;
	}

	protected void parseNotificationElements(
		List<Element> notificationElements,
		NotificationAware notificationAware) {

		if (notificationElements.isEmpty()) {
			return;
		}

		Set<Notification> notifications = new HashSet<>(
			notificationElements.size());

		for (Element notificationElement : notificationElements) {
			String name = notificationElement.elementText("name");
			String description = notificationElement.elementText("description");
			String executionType = notificationElement.elementText(
				"execution-type");
			String template = notificationElement.elementText("template");
			String templateLanguage = notificationElement.elementText(
				"template-language");

			Notification notification = new Notification(
				name, description, executionType, template, templateLanguage);

			List<Element> notificationTypeElements =
				notificationElement.elements("notification-type");

			for (Element notificationTypeElement : notificationTypeElements) {
				notification.addNotificationType(
					notificationTypeElement.getText());
			}

			List<Element> recipientsElements = notificationElement.elements(
				"recipients");

			for (Element recipientsElement : recipientsElements) {
				parseRecipients(
					recipientsElement, notification,
					NotificationReceptionType.parse(
						recipientsElement.attributeValue("receptionType")));
			}

			notifications.add(notification);
		}

		notificationAware.setNotifications(notifications);
	}

	protected void parseRecipients(
		Element recipientsElement, Notification notification,
		NotificationReceptionType notificationReceptionType) {

		if (recipientsElement == null) {
			return;
		}

		List<Element> addressRecipientElements = recipientsElement.elements(
			"address");

		for (Element addressRecipientElement : addressRecipientElements) {
			AddressRecipient addressRecipient = new AddressRecipient(
				addressRecipientElement.getText());

			addressRecipient.setNotificationReceptionType(
				notificationReceptionType);

			notification.addRecipients(addressRecipient);
		}

		Element assigneesRecipientElement = recipientsElement.element(
			"assignees");

		if (assigneesRecipientElement != null) {
			AssigneesRecipient assigneesRecipient = new AssigneesRecipient();

			assigneesRecipient.setNotificationReceptionType(
				notificationReceptionType);

			notification.addRecipients(assigneesRecipient);
		}

		Element rolesElement = recipientsElement.element("roles");

		if (rolesElement != null) {
			List<Element> roleReceipientElements = rolesElement.elements(
				"role");

			for (Element roleReceipientElement : roleReceipientElements) {
				long roleId = GetterUtil.getLong(
					roleReceipientElement.elementText("role-id"));
				String roleType = roleReceipientElement.elementText(
					"role-type");
				String name = roleReceipientElement.elementText("name");

				RoleRecipient roleRecipient = null;

				if (roleId > 0) {
					roleRecipient = new RoleRecipient(roleId, roleType);
				}
				else {
					roleRecipient = new RoleRecipient(name, roleType);

					boolean autoCreate = GetterUtil.getBoolean(
						roleReceipientElement.elementText("auto-create"), true);

					roleRecipient.setAutoCreate(autoCreate);
				}

				roleRecipient.setNotificationReceptionType(
					notificationReceptionType);

				notification.addRecipients(roleRecipient);
			}
		}

		List<Element> scriptedRecipientElements = recipientsElement.elements(
			"scripted-recipient");

		for (Element scriptedRecipientElement : scriptedRecipientElements) {
			String script = scriptedRecipientElement.elementText("script");
			String scriptLanguage = scriptedRecipientElement.elementText(
				"script-language");
			String scriptRequiredContexts =
				scriptedRecipientElement.elementText(
					"script-required-contexts");

			ScriptRecipient scriptRecipient = new ScriptRecipient(
				script, scriptLanguage, scriptRequiredContexts);

			scriptRecipient.setNotificationReceptionType(
				notificationReceptionType);

			notification.addRecipients(scriptRecipient);
		}

		List<Element> userRecipientElements = recipientsElement.elements(
			"user");

		for (Element userRecipientElement : userRecipientElements) {
			long userId = GetterUtil.getLong(
				userRecipientElement.elementText("user-id"));
			String screenName = userRecipientElement.elementText("screen-name");
			String emailAddress = userRecipientElement.elementText(
				"email-address");

			UserRecipient userRecipient = new UserRecipient(
				userId, screenName, emailAddress);

			userRecipient.setNotificationReceptionType(
				notificationReceptionType);

			notification.addRecipients(userRecipient);
		}
	}

	protected State parseState(Element stateElement) {
		String name = stateElement.elementText("name");
		String description = stateElement.elementText("description");
		boolean initial = GetterUtil.getBoolean(
			stateElement.elementText("initial"), false);

		State state = new State(name, description, initial);

		String metadata = stateElement.elementText("metadata");

		state.setMetadata(metadata);

		Element actionsElement = stateElement.element("actions");

		parseActionsElement(actionsElement, state);

		Element timersElement = stateElement.element("timers");

		parseTimerElements(timersElement, state);

		return state;
	}

	protected Task parseTask(Element taskElement) {
		String name = taskElement.elementText("name");
		String description = taskElement.elementText("description");

		Task task = new Task(name, description);

		String metadata = taskElement.elementText("metadata");

		task.setMetadata(metadata);

		Element actionsElement = taskElement.element("actions");

		parseActionsElement(actionsElement, task);

		Element assignmentsElement = taskElement.element("assignments");

		if (assignmentsElement != null) {
			Set<Assignment> assignments = parseAssignments(assignmentsElement);

			task.setAssignments(assignments);
		}

		Element timersElement = taskElement.element("task-timers");

		parseTaskTimerElements(timersElement, task);

		return task;
	}

	protected void parseTaskTimerElements(
		Element taskTimersElement, Node node) {

		if (taskTimersElement == null) {
			return;
		}

		List<Element> taskTimerElements = taskTimersElement.elements(
			"task-timer");

		if (taskTimerElements.isEmpty()) {
			return;
		}

		Set<Timer> timers = new HashSet<>(taskTimerElements.size());

		for (Element timerElement : taskTimerElements) {
			Timer timer = parseTimerElement(timerElement, true);

			timers.add(timer);
		}

		node.setTimers(timers);
	}

	protected void parseTimerActions(Element timersElement, Timer timer) {
		if (timersElement == null) {
			return;
		}

		List<Element> timerActionElements = timersElement.elements(
			"timer-action");

		parseActionElements(timerActionElements, timer);

		List<Element> timerNotificationElements = timersElement.elements(
			"timer-notification");

		parseNotificationElements(timerNotificationElements, timer);

		Element reassignmentsElement = timersElement.element("reassignments");

		if (reassignmentsElement != null) {
			Set<Assignment> assignments = parseAssignments(
				reassignmentsElement);

			timer.setReassignments(assignments);
		}
	}

	protected Timer parseTimerElement(
		Element timerElement, boolean isTaskTimer) {

		String name = timerElement.elementText("name");
		String description = timerElement.elementText("description");
		boolean blocking = GetterUtil.getBoolean(
			timerElement.elementText("blocking"), !isTaskTimer);

		Timer timer = new Timer(name, description, blocking);

		Element delayElement = timerElement.element("delay");

		DelayDuration delayDuration = parseDelay(delayElement);

		timer.setDelayDuration(delayDuration);

		if (!blocking) {
			Element recurrenceElement = timerElement.element("recurrence");

			DelayDuration recurrence = parseDelay(recurrenceElement);

			timer.setRecurrence(recurrence);
		}

		Element timerActions = timerElement.element("timer-actions");

		parseTimerActions(timerActions, timer);

		return timer;
	}

	protected void parseTimerElements(Element timersElement, Node node) {
		if (timersElement == null) {
			return;
		}

		List<Element> timerElements = timersElement.elements("timer");

		if (timerElements.isEmpty()) {
			return;
		}

		Set<Timer> timers = new HashSet<>(timerElements.size());

		for (Element timerElement : timerElements) {
			Timer timer = parseTimerElement(timerElement, false);

			timers.add(timer);
		}

		node.setTimers(timers);
	}

	protected void parseTransition(Definition definition, Element nodeElement) {
		String sourceName = nodeElement.elementText("name");

		Node sourceNode = definition.getNode(sourceName);

		Element transitionsElement = nodeElement.element("transitions");

		if (transitionsElement == null) {
			return;
		}

		List<Element> transitionElements = transitionsElement.elements(
			"transition");

		for (Element transitionElement : transitionElements) {
			String transitionName = transitionElement.elementText("name");

			String targetName = transitionElement.elementText("target");

			Node targetNode = definition.getNode(targetName);

			boolean defaultValue = GetterUtil.getBoolean(
				transitionElement.elementText("default"), true);

			Transition transition = new Transition(
				transitionName, sourceNode, targetNode, defaultValue);

			Element timerElement = transitionElement.element("timer");

			if (timerElement != null) {
				Timer timer = parseTimerElement(timerElement, false);

				transition.setTimers(timer);
			}

			sourceNode.addOutgoingTransition(transition);

			if (Validator.isNotNull(targetNode)) {
				targetNode.addIncomingTransition(transition);
			}
		}
	}

	protected void parseTransitions(
		Definition definition, List<Element> conditionElements,
		List<Element> forkElements, List<Element> joinElements,
		List<Element> joinXorElements, List<Element> stateElements,
		List<Element> taskElements) {

		for (Element conditionElement : conditionElements) {
			parseTransition(definition, conditionElement);
		}

		for (Element forkElement : forkElements) {
			parseTransition(definition, forkElement);
		}

		for (Element joinElement : joinElements) {
			parseTransition(definition, joinElement);
		}

		for (Element joinXorElement : joinXorElements) {
			parseTransition(definition, joinXorElement);
		}

		for (Element stateElement : stateElements) {
			parseTransition(definition, stateElement);
		}

		for (Element taskElement : taskElements) {
			parseTransition(definition, taskElement);
		}
	}

	private boolean _validate;

}