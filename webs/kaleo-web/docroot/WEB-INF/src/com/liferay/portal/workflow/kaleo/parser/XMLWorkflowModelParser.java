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

package com.liferay.portal.workflow.kaleo.parser;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.workflow.kaleo.definition.Action;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.DueDateDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.definition.Fork;
import com.liferay.portal.workflow.kaleo.definition.Join;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.RoleAssignment;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.definition.UserAssignment;

import java.io.InputStream;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="XMLWorkflowModelParser.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class XMLWorkflowModelParser implements WorkflowModelParser {

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

		Definition definition = new Definition(name, description, version);

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

		processTransitions(
			definition, stateElements, taskElements, forkElements,
			joinElements);

		return definition;
	}

	protected Set<Action> parseActions(Element actionsElement) {
		if (actionsElement == null) {
			return Collections.EMPTY_SET;
		}

		Set<Action> actions = new HashSet<Action>();

		List<Element> actionElements = actionsElement.elements("action");

		for (Element actionElement : actionElements) {
			String name = actionElement.attributeValue("name");
			String type = actionElement.attributeValue("type");
			String language = actionElement.attributeValue("language");

			Action action = new Action(name, type, language);

			int executionOrder = GetterUtil.getInteger(
				actionElement.attributeValue("execution-order"));

			action.setExecutionOrder(executionOrder);

			action.setScript(actionElement.getText());

			actions.add(action);
		}

		return actions;
	}

	protected Set<Assignment> parseAssignments(Element assignmentsElement) {
		if (assignmentsElement == null) {
			return Collections.EMPTY_SET;
		}

		Set<Assignment> assignments = new HashSet<Assignment>();

		List<Element> roleAssignmentElements = assignmentsElement.elements(
			"role");

		for (Element roleAssignmentElement : roleAssignmentElements) {
			String name = roleAssignmentElement.attributeValue("name");
			boolean defaultValue = GetterUtil.getBoolean(
				roleAssignmentElement.attributeValue("default"));

			RoleAssignment roleAssignment = new RoleAssignment(
				name, defaultValue);

			assignments.add(roleAssignment);
		}

		List<Element> userAssignmentElements = assignmentsElement.elements(
			"user");

		for (Element userAssignmentElement : userAssignmentElements) {
			boolean defaultValue = GetterUtil.getBoolean(
				userAssignmentElement.attributeValue("default"));

			UserAssignment userAssignment = new UserAssignment(defaultValue);

			long userId = GetterUtil.getLong(
				userAssignmentElement.attributeValue("user-id"));

			userAssignment.setUserId(userId);

			String screenName = userAssignmentElement.attributeValue(
				"screen-name");

			userAssignment.setScreenName(screenName);

			String emailAddress = userAssignmentElement.attributeValue(
				"email-address");

			userAssignment.setEmailAddress(emailAddress);

			assignments.add(userAssignment);
		}

		return assignments;
	}

	protected Fork parseFork(Element forkElement) {
		String name = forkElement.attributeValue("name");
		String description = forkElement.elementText("description");

		Fork fork = new Fork(name, description);

		Element actionsElement = forkElement.element("actions");

		if (actionsElement != null) {
			Set<Action> actions = parseActions(actionsElement);

			fork.setActions(actions);
		}

		return fork;
	}

	protected Join parseJoin(Element joinElement) {
		String name = joinElement.attributeValue("name");
		String description = joinElement.elementText("description");

		Join join = new Join(name, description);

		Element actionsElement = joinElement.element("actions");

		if (actionsElement != null) {
			Set<Action> actions = parseActions(actionsElement);

			join.setActions(actions);
		}

		return join;
	}

	protected State parseState(Element stateElement) {
		String name = stateElement.attributeValue("name");
		String description = stateElement.elementText("description");

		State state = new State(name, description);

		boolean initial = GetterUtil.getBoolean(
			stateElement.attributeValue("initial"));

		state.setInitial(initial);

		Element actionsElement = stateElement.element("actions");

		if (actionsElement != null) {
			Set<Action> actions = parseActions(actionsElement);

			state.setActions(actions);
		}

		return state;
	}

	protected Task parseTask(Element taskElement)
		throws WorkflowException {

		String name = taskElement.attributeValue("name");
		String description = taskElement.elementText("description");

		Task task = new Task(name, description);

		String dueDateDurationStr = taskElement.elementText(
			"due-date-duration"); 

		if (Validator.isNotNull(dueDateDurationStr)) {
			double duration = Double.parseDouble(dueDateDurationStr);

			String dueDateScaleStr = taskElement.attributeValue("due-date-scale");
			if (Validator.isNull(dueDateScaleStr)) {
				throw new WorkflowException(
					"Must specify a scale for the due date duration of task :" +
					task.getName());
			}

			DurationScale durationScale = DurationScale.getValue(dueDateScaleStr);
			DueDateDuration dueDateDuration = new DueDateDuration(
				duration, durationScale);
			task.setDueDateDuration(dueDateDuration);
		}

		Element actionsElement = taskElement.element("actions");

		if (actionsElement != null) {
			Set<Action> actions = parseActions(actionsElement);

			task.setActions(actions);
		}

		Element assignmentsElement = taskElement.element("assignments");

		if (assignmentsElement != null) {
			Set<Assignment> assignments = parseAssignments(assignmentsElement);

			task.setAssignments(assignments);
		}

		return task;
	}

	protected void parseTransition(Definition definition, Element nodeElement)
		throws WorkflowException {

		String sourceName = nodeElement.attributeValue("name");

		Node sourceNode = definition.getNode(sourceName);

		if (sourceNode == null) {
			throw new WorkflowException(
				"Unable to find source node " + sourceName);
		}

		Element transitionsElement = nodeElement.element("transitions");

		if (transitionsElement == null) {
			return;
		}

		List<Element> transitionElements = transitionsElement.elements(
			"transition");

		for (Element transitionElement : transitionElements) {
			String transitionName = transitionElement.attributeValue(
				"name");

			Transition transition = new Transition(transitionName, sourceNode);

			boolean defaultValue = GetterUtil.getBoolean(
				transitionElement.attributeValue("default"));

			transition.setDefault(defaultValue);

			String targetName = transitionElement.attributeValue("target");

			Node targetNode = definition.getNode(targetName);

			if (targetNode == null) {
				throw new WorkflowException(
					"Unable to find target node " + targetName);
			}

			transition.setTargetNode(targetNode);

			sourceNode.addTransition(transition);
		}
	}

	protected void processTransitions(
			Definition definition, List<Element> stateElements,
			List<Element> taskElements, List<Element> forkElements,
			List<Element> joinElements)
		throws WorkflowException {

		for (Element stateElement : stateElements) {
			parseTransition(definition, stateElement);
		}

		for (Element taskElement : taskElements) {
			parseTransition(definition, taskElement);
		}

		for (Element forkElement : forkElements) {
			parseTransition(definition, forkElement);
		}

		for (Element joinElement : joinElements) {
			parseTransition(definition, joinElement);
		}
	}

	private boolean _validate;

}