/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.jbpm;

import com.liferay.jbpm.db.GraphSession;
import com.liferay.jbpm.util.TaskFormElement;
import com.liferay.jbpm.util.WorkflowUtil;
import com.liferay.portal.kernel.jbi.WorkflowComponent;
import com.liferay.portal.kernel.jbi.WorkflowComponentException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.JS;
import com.liferay.util.xml.DocUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.context.def.VariableAccess;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowComponentImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class WorkflowComponentImpl implements WorkflowComponent {

	public String process(HttpServletRequest req) {
		String result = null;

		try {
			userId = ParamUtil.getString(req, "userId");
			timeZoneId = ParamUtil.getString(req, "timeZoneId");
			timeZone = TimeZone.getTimeZone(timeZoneId);
			jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
			graphSession = new GraphSession(userId, timeZoneId, jbpmContext);
			taskMgmtSession = jbpmContext.getTaskMgmtSession();

			String cmd = ParamUtil.getString(req, "cmd");

			if (cmd.equals("deploy")) {
				String xml = ParamUtil.getString(req, "xml");

				result = deploy(xml);
			}
			else if (cmd.equals("getCurrentTasksXml")) {
				long instanceId = ParamUtil.getLong(req, "instanceId");
				long tokenId = ParamUtil.getLong(req, "tokenId");

				result = getCurrentTasksXml(instanceId, tokenId);
			}
			else if (cmd.equals("getDefinitionsCountXml")) {
				long definitionId = ParamUtil.getLong(req, "definitionId");
				String name = ParamUtil.getString(req, "name");

				result = getDefinitionsCountXml(definitionId, name);
			}
			else if (cmd.equals("getDefinitionsXml")) {
				long definitionId = ParamUtil.getLong(req, "definitionId");
				String name = ParamUtil.getString(req, "name");
				int begin = ParamUtil.getInteger(req, "begin");
				int end = ParamUtil.getInteger(req, "end");

				result = getDefinitionsXml(definitionId, name, begin, end);
			}
			else if (cmd.equals("getDefinitionXml")) {
				long definitionId = ParamUtil.getLong(req, "definitionId");

				result = getDefinitionXml(definitionId);
			}
			else if (cmd.equals("getInstancesCountXml")) {
				long definitionId = ParamUtil.getLong(req, "definitionId");
				long instanceId = ParamUtil.getLong(req, "instanceId");
				String definitionName = ParamUtil.getString(
					req, "definitionName");
				String definitionVersion = ParamUtil.getString(
					req, "definitionVersion");
				String startDateGT = ParamUtil.getString(req, "startDateGT");
				String startDateLT = ParamUtil.getString(req, "startDateLT");
				String endDateGT = ParamUtil.getString(req, "endDateGT");
				String endDateLT = ParamUtil.getString(req, "endDateLT");
				boolean hideEndedTasks = ParamUtil.getBoolean(
					req, "hideEndedTasks");
				boolean retrieveUserInstances = ParamUtil.getBoolean(
					req, "retrieveUserInstances");
				boolean andOperator = ParamUtil.getBoolean(req, "andOperator");

				result = getInstancesCountXml(
					definitionId, instanceId, definitionName, definitionVersion,
					startDateGT, startDateLT, endDateGT, endDateLT,
					hideEndedTasks, retrieveUserInstances, andOperator);
			}
			else if (cmd.equals("getInstancesXml")) {
				long definitionId = ParamUtil.getLong(req, "definitionId");
				long instanceId = ParamUtil.getLong(req, "instanceId");
				String definitionName = ParamUtil.getString(
					req, "definitionName");
				String definitionVersion = ParamUtil.getString(
					req, "definitionVersion");
				String startDateGT = ParamUtil.getString(req, "startDateGT");
				String startDateLT = ParamUtil.getString(req, "startDateLT");
				String endDateGT = ParamUtil.getString(req, "endDateGT");
				String endDateLT = ParamUtil.getString(req, "endDateLT");
				boolean hideEndedTasks = ParamUtil.getBoolean(
					req, "hideEndedTasks");
				boolean retrieveUserInstances = ParamUtil.getBoolean(
					req, "retrieveUserInstances");
				boolean andOperator = ParamUtil.getBoolean(req, "andOperator");
				int begin = ParamUtil.getInteger(req, "begin");
				int end = ParamUtil.getInteger(req, "end");

				result = getInstancesXml(
					definitionId, instanceId, definitionName, definitionVersion,
					startDateGT, startDateLT, endDateGT, endDateLT,
					hideEndedTasks, retrieveUserInstances, andOperator, begin,
					end);
			}
			else if (cmd.equals("getTaskXml")) {
				long taskId = ParamUtil.getLong(req, "taskId");

				result = getTaskXml(taskId);
			}
			else if (cmd.equals("getTaskFormElementsXml")) {
				long taskId = ParamUtil.getLong(req, "taskId");

				result = getTaskFormElementsXml(taskId);
			}
			else if (cmd.equals("getTaskTransitionsXml")) {
				long taskId = ParamUtil.getLong(req, "taskId");

				result = getTaskTransitionsXml(taskId);
			}
			else if (cmd.equals("getUserTasksCountXml")) {
				long instanceId = ParamUtil.getLong(req, "instanceId");
				String taskName = ParamUtil.getString(req, "taskName");
				String definitionName = ParamUtil.getString(
					req, "definitionName");
				String assignedTo = ParamUtil.getString(req, "assignedTo");
				String createDateGT = ParamUtil.getString(req, "createDateGT");
				String createDateLT = ParamUtil.getString(req, "createDateLT");
				String startDateGT = ParamUtil.getString(req, "startDateGT");
				String startDateLT = ParamUtil.getString(req, "startDateLT");
				String endDateGT = ParamUtil.getString(req, "endDateGT");
				String endDateLT = ParamUtil.getString(req, "endDateLT");
				boolean hideEndedTasks = ParamUtil.getBoolean(
					req, "hideEndedTasks");
				boolean andOperator = ParamUtil.getBoolean(req, "andOperator");

				result = getUserTasksCountXml(
					instanceId, taskName, definitionName, assignedTo,
					createDateGT, createDateLT, startDateGT, startDateLT,
					endDateGT, endDateLT, hideEndedTasks, andOperator);
			}
			else if (cmd.equals("getUserTasksXml")) {
				long instanceId = ParamUtil.getLong(req, "instanceId");
				String taskName = ParamUtil.getString(req, "taskName");
				String definitionName = ParamUtil.getString(
					req, "definitionName");
				String assignedTo = ParamUtil.getString(req, "assignedTo");
				String createDateGT = ParamUtil.getString(req, "createDateGT");
				String createDateLT = ParamUtil.getString(req, "createDateLT");
				String startDateGT = ParamUtil.getString(req, "startDateGT");
				String startDateLT = ParamUtil.getString(req, "startDateLT");
				String endDateGT = ParamUtil.getString(req, "endDateGT");
				String endDateLT = ParamUtil.getString(req, "endDateLT");
				boolean hideEndedTasks = ParamUtil.getBoolean(
					req, "hideEndedTasks");
				boolean andOperator = ParamUtil.getBoolean(req, "andOperator");
				int begin = ParamUtil.getInteger(req, "begin");
				int end = ParamUtil.getInteger(req, "end");

				result = getUserTasksXml(
					instanceId, taskName, definitionName, assignedTo,
					createDateGT, createDateLT, startDateGT, startDateLT,
					endDateGT, endDateLT, hideEndedTasks, andOperator, begin,
					end);
			}
			else if (cmd.equals("signalInstance")) {
				long instanceId = ParamUtil.getLong(req, "instanceId");

				signalInstance(instanceId);
			}
			else if (cmd.equals("signalToken")) {
				long instanceId = ParamUtil.getLong(req, "instanceId");
				long tokenId = ParamUtil.getLong(req, "tokenId");

				signalToken(instanceId, tokenId);
			}
			else if (cmd.equals("startWorkflow")) {
				long definitionId = ParamUtil.getLong(req, "definitionId");

				result = startWorkflow(definitionId);
			}
			else if (cmd.equals("updateTaskXml")) {
				long taskId = ParamUtil.getLong(req, "taskId");
				String transition = ParamUtil.getString(req, "transition");

				result = updateTaskXml(
					taskId, transition, req.getParameterMap());
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			try {
				jbpmContext.close();
			}
			catch (Exception e) {
			}

			try {
				graphSession.close();
			}
			catch (Exception e) {
			}
		}

		if (Validator.isNull(result)) {
			result = "<result />";
		}

		return result;
	}

	public String deploy(String xml) throws WorkflowComponentException {
		ProcessDefinition definition = ProcessDefinition.parseXmlString(xml);

		jbpmContext.deployProcessDefinition(definition);

		StringMaker sm = new StringMaker();

		sm.append("<result>");
		sm.append("<definitionId>");
		sm.append(definition.getId());
		sm.append("</definitionId>");
		sm.append("</result>");

		return sm.toString();
	}

	public List getCurrentTasks(long instanceId, long tokenId)
		throws WorkflowComponentException {

		List userTasks = getUserTasks(
			instanceId, null, null, null, null, null, null, null, null, null,
			false, false, 0, 0);

		List currentTasks = new ArrayList();

		List tokenTasks = taskMgmtSession.findTaskInstancesByToken(tokenId);

		if (tokenTasks.size() == 0) {
			currentTasks = null;
		}
		else {
			Iterator itr = tokenTasks.iterator();

			Set tokenTaskIds = new HashSet();

			while (itr.hasNext()) {
				TaskInstance task = (TaskInstance)itr.next();

				tokenTaskIds.add(new Long(task.getId()));
			}

			itr = userTasks.iterator();

			while (itr.hasNext()) {
				TaskInstance task = (TaskInstance)itr.next();

				if (tokenTaskIds.contains(new Long(task.getId()))) {
					currentTasks.add(task);
				}
			}
		}

		// At this point, if currentTasks is null, then that means no tasks
		// exist for this token, and the instance must be signaled to continue.
		// If currentTasks is just empty, then that means the current token
		// has tasks, but the current user cannot manage them.

		return currentTasks;
	}

	public String getCurrentTasksXml(long instanceId, long tokenId)
		throws WorkflowComponentException {

		List tasks = getCurrentTasks(instanceId, tokenId);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		if (tasks != null) {
			for (int i = 0; i < tasks.size(); i++) {
				TaskInstance task = (TaskInstance)tasks.get(i);

				createElement(task, root);
			}
		}

		return doc.asXML();
	}

	public Object getDefinition(long definitionId)
		throws WorkflowComponentException {

		ProcessDefinition definition =
			graphSession.loadProcessDefinition(definitionId);

		WorkflowUtil.initDefinition(definition);

		return definition;
	}

	public List getDefinitions(
			long definitionId, String name, int begin, int end)
		throws WorkflowComponentException {

		List definitions = new ArrayList();

		if (definitionId > 0) {
			ProcessDefinition definition =
				(ProcessDefinition)getDefinition(definitionId);

			definitions.add(definition);
		}
		else {
			definitions =
				graphSession.findProcessDefinitionsByName(name, begin, end);
		}

		return definitions;
	}

	public String getDefinitionsXml(
			long definitionId, String name, int begin, int end)
		throws WorkflowComponentException {

		List definitions = getDefinitions(definitionId, name, begin, end);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		for (int i = 0; i < definitions.size(); i++) {
			ProcessDefinition definition =
				(ProcessDefinition)definitions.get(i);

			createElement(definition, root);
		}

		return doc.asXML();
	}

	public int getDefinitionsCount(long definitionId, String name)
		throws WorkflowComponentException {

		int count = 0;

		if (definitionId > 0) {
			count = 1;
		}
		else {
			count = graphSession.countProcessDefinitionsByName(name);
		}

		return count;
	}

	public String getDefinitionsCountXml(long definitionId, String name)
		throws WorkflowComponentException {

		int count = getDefinitionsCount(definitionId, name);

		return getCountXml(count);
	}

	public String getDefinitionXml(long definitionId)
		throws WorkflowComponentException {

		ProcessDefinition definition =
			(ProcessDefinition)getDefinition(definitionId);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		createElement(definition, root);

		return doc.asXML();
	}

	public List getInstances(
			long definitionId, long instanceId, String definitionName,
			String definitionVersion, String startDateGT, String startDateLT,
			String endDateGT, String endDateLT, boolean hideEndedTasks,
			boolean retrieveUserInstances, boolean andOperator, int begin,
			int end)
		throws WorkflowComponentException {

		List instances = new ArrayList();

		if (definitionId > 0){
			ProcessDefinition definition =
				graphSession.loadProcessDefinition(definitionId);

			instances = graphSession.findProcessInstances(definition.getId());

			WorkflowUtil.initInstances(instances);
		}
		else if (instanceId > 0){
			ProcessInstance instance =
				graphSession.loadProcessInstance(instanceId);

			WorkflowUtil.initInstance(instance);

			instances.add(instance);
		}
		else {
			String assignedUserId = null;

			if (retrieveUserInstances) {
				assignedUserId = userId;
			}

			instances = graphSession.findProcessInstancesBySearchTerms(
				definitionName, definitionVersion, startDateGT, startDateLT,
				endDateGT, endDateLT, hideEndedTasks, assignedUserId,
				andOperator, begin, end);
		}

		return instances;
	}

	public int getInstancesCount(
			long definitionId, long instanceId, String definitionName,
			String definitionVersion, String startDateGT, String startDateLT,
			String endDateGT, String endDateLT, boolean hideEndedTasks,
			boolean retrieveUserInstances, boolean andOperator)
		throws WorkflowComponentException {

		if (definitionId > 0){
			return 1;
		}
		else if (instanceId > 0){
			return 1;
		}
		else {
			String assignedUserId = null;

			if (retrieveUserInstances) {
				assignedUserId = userId;
			}

			return graphSession.countProcessInstancesBySearchTerms(
				definitionName, definitionVersion, startDateGT, startDateLT,
				endDateGT, endDateLT, hideEndedTasks, assignedUserId,
				andOperator);
		}
	}

	public String getInstancesCountXml(
			long definitionId, long instanceId, String definitionName,
			String definitionVersion, String startDateGT, String startDateLT,
			String endDateGT, String endDateLT, boolean hideEndedTasks,
			boolean retrieveUserInstances, boolean andOperator)
		throws WorkflowComponentException {

		int count = getInstancesCount(
			definitionId, instanceId, definitionName, definitionVersion,
			startDateGT, startDateLT, endDateGT, endDateLT, hideEndedTasks,
			retrieveUserInstances, andOperator);

		return getCountXml(count);
	}

	public String getInstancesXml(
			long definitionId, long instanceId, String definitionName,
			String definitionVersion, String startDateGT, String startDateLT,
			String endDateGT, String endDateLT, boolean hideEndedTasks,
			boolean retrieveUserInstances, boolean andOperator, int begin,
			int end)
		throws WorkflowComponentException {

		List instances = getInstances(
			definitionId, instanceId, definitionName, definitionVersion,
			startDateGT, startDateLT, endDateGT, endDateLT, hideEndedTasks,
			retrieveUserInstances, andOperator, begin, end);

			Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		for (int i = 0; i < instances.size(); i++) {
			ProcessInstance instance = (ProcessInstance)instances.get(i);

			createElement(instance, root, true);
		}

		return doc.asXML();
	}

	public Object getTask(long taskId)
		throws WorkflowComponentException {

		TaskInstance task = taskMgmtSession.loadTaskInstance(taskId);

		WorkflowUtil.initTask(task);

		return task;
	}

	public String getTaskXml(long taskId)
		throws WorkflowComponentException {

		TaskInstance task =
			(TaskInstance)getTask(taskId);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		createElement(task, root);

		return doc.asXML();
	}

	public List getTaskFormElements(long taskId)
		throws WorkflowComponentException {

		List taskFormElements = new ArrayList();

		TaskInstance task = taskMgmtSession.loadTaskInstance(taskId);

		Map variableInstances = task.getVariables();

		List variableAccesses =
			task.getTask().getTaskController().getVariableAccesses();

		Iterator itr = variableAccesses.iterator();

		while (itr.hasNext()) {
			VariableAccess variableAccess = (VariableAccess)itr.next();

			String value = (String)variableInstances.get(
				variableAccess.getVariableName());

			TaskFormElement taskFormElement =
				new TaskFormElement(variableAccess, value);

			taskFormElements.add(taskFormElement);
		}

		return taskFormElements;
	}

	public String getTaskFormElementsXml(long taskId)
		throws WorkflowComponentException {

		List taskFormElements = getTaskFormElements(taskId);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		for (int i = 0; i < taskFormElements.size(); i++) {
			TaskFormElement taskFormElement =
				(TaskFormElement)taskFormElements.get(i);

			createElement(taskFormElement, root);
		}

		return doc.asXML();
	}

	public List getTaskTransitions(long taskId)
		throws WorkflowComponentException {

		TaskInstance task = taskMgmtSession.loadTaskInstance(taskId);

		return task.getAvailableTransitions();
	}

	public String getTaskTransitionsXml(long taskId)
		throws WorkflowComponentException {

		List transitions = getTaskTransitions(taskId);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		for (int i = 0; i < transitions.size(); i++) {
			Transition transition = (Transition)transitions.get(i);

			DocUtil.add(root, "transition", transition.getName());
		}

		return doc.asXML();
	}

	public List getUserTasks(
			long instanceId, String taskName, String definitionName,
			String assignedTo, String createDateGT, String createDateLT,
			String startDateGT, String startDateLT, String endDateGT,
			String endDateLT, boolean hideEndedTasks, boolean andOperator,
			int begin, int end)
		throws WorkflowComponentException {

		List tasks = new ArrayList();

		if (Validator.isNull(taskName) && Validator.isNull(definitionName) &&
			Validator.isNull(assignedTo) && Validator.isNull(createDateGT) &&
			Validator.isNull(createDateLT) && Validator.isNull(startDateGT) &&
			Validator.isNull(startDateLT) && Validator.isNull(endDateGT) &&
			Validator.isNull(endDateLT)) {

			List taskInstances = taskMgmtSession.findTaskInstances(userId);

			taskInstances.addAll(
				taskMgmtSession.findPooledTaskInstances(userId));

			Iterator itr = taskInstances.iterator();

			while (itr.hasNext()) {
				TaskInstance task = (TaskInstance)itr.next();

				ProcessInstance instance = task.getToken().getProcessInstance();

				if (instance.getId() == instanceId) {
					WorkflowUtil.initTask(task);

					tasks.add(task);
				}
			}
		}
		else {
			tasks = graphSession.findTaskInstancesBySearchTerms(
				taskName, definitionName, assignedTo, createDateGT,
				createDateLT, startDateGT, startDateLT, endDateGT, endDateLT,
				hideEndedTasks, andOperator, begin, end);

			WorkflowUtil.initTasks(tasks);
		}

		return tasks;
	}

	public int getUserTasksCount(
			long instanceId, String taskName, String definitionName,
			String assignedTo, String createDateGT, String createDateLT,
			String startDateGT, String startDateLT, String endDateGT,
			String endDateLT, boolean hideEndedTasks, boolean andOperator)
		throws WorkflowComponentException {

		return graphSession.countTaskInstancesBySearchTerms(
			taskName, definitionName, assignedTo,	createDateGT, createDateLT,
			startDateGT, startDateLT, endDateGT, endDateLT, hideEndedTasks,
			andOperator);
	}

	public String getUserTasksCountXml(
			long instanceId, String taskName, String definitionName,
			String assignedTo, String createDateGT, String createDateLT,
			String startDateGT, String startDateLT, String endDateGT,
			String endDateLT, boolean hideEndedTasks, boolean andOperator)
		throws WorkflowComponentException {

		int count = getUserTasksCount(
			instanceId, taskName, definitionName, assignedTo, createDateGT,
			createDateLT, startDateGT, startDateLT, endDateGT, endDateLT,
			hideEndedTasks, andOperator);

		return getCountXml(count);
	}

	public String getUserTasksXml(
			long instanceId, String taskName, String definitionName,
			String assignedTo, String createDateGT, String createDateLT,
			String startDateGT, String startDateLT, String endDateGT,
			String endDateLT, boolean hideEndedTasks, boolean andOperator,
			int begin, int end)
		throws WorkflowComponentException {

		List tasks = getUserTasks(
			instanceId, taskName, definitionName, assignedTo, createDateGT,
			createDateLT, startDateGT, startDateLT, endDateGT, endDateLT,
			hideEndedTasks, andOperator, begin, end);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		for (int i = 0; i < tasks.size(); i++) {
			TaskInstance task = (TaskInstance)tasks.get(i);

			createElement(task, root);
		}

		return doc.asXML();
	}

	public void signalInstance(long instanceId)
		throws WorkflowComponentException {

		ProcessInstance instance = jbpmContext.loadProcessInstance(instanceId);

		instance.signal();

		jbpmContext.save(instance);
	}

	public void signalToken(long instanceId, long tokenId)
		throws WorkflowComponentException {

		ProcessInstance instance = jbpmContext.loadProcessInstance(instanceId);

		Token token = instance.getRootToken();

		if (token.getId() == tokenId) {
			token.signal();
		}
		else {
			Map activeChildren = instance.getRootToken().getActiveChildren();

			Iterator itr = activeChildren.values().iterator();

			while (itr.hasNext()) {
				token = (Token)itr.next();

				if (token.getId() == tokenId) {
					token.signal();

					break;
				}
			}
		}
	}

	public String startWorkflow(long definitionId)
		throws WorkflowComponentException {

		ProcessDefinition definition =
			graphSession.loadProcessDefinition(definitionId);

		ProcessInstance instance = new ProcessInstance(definition);

		// After creating process instance, assign the currently authenticated
		// user to the first task if it exists

		if (instance.getTaskMgmtInstance().getTaskMgmtDefinition().
				getStartTask() != null) {

			jbpmContext.setActorId(userId);

			instance.getTaskMgmtInstance().createStartTaskInstance();
		}

		jbpmContext.save(instance);

		WorkflowUtil.initInstance(instance);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		createElement(instance, root, false);

		return doc.asXML();
	}

	public Map updateTask(long taskId, String transition, Map parameterMap)
		throws WorkflowComponentException {

		List taskFormElements = getTaskFormElements(taskId);

		TaskInstance task = taskMgmtSession.loadTaskInstance(taskId);

		String actorId = task.getActorId();

		try {
			task.start();

			if (actorId == null) {
				task.setActorId(userId);
			}
		}
		catch (Exception e) {
			_log.error("Task has already started");
		}

		Map variables = new HashMap();
		Map errors = new HashMap();

		Iterator itr = taskFormElements.iterator();

		while (itr.hasNext()) {
			TaskFormElement taskFormElement = (TaskFormElement)itr.next();

			String name = taskFormElement.getDisplayName();

			if (taskFormElement.isWritable()) {
				String value = getParamValue(parameterMap, name);

				if (taskFormElement.getType().equals(
						TaskFormElement.TYPE_DATE)) {

					value = getParamValue(parameterMap, JS.getSafeName(name));
				}

				variables.put(taskFormElement.getVariableName(), value);

				String error = validate(taskFormElement, parameterMap);

				if (error != null) {
					errors.put(name, error);
				}
			}
		}

		task.addVariables(variables);

		if (errors.size() == 0) {
			try {
				task.end(transition);
			}
			catch (Exception e) {
				_log.error("Task has already ended");
			}
		}

		ProcessInstance instance = task.getToken().getProcessInstance();

		WorkflowUtil.initInstance(instance);

		jbpmContext.save(instance);

		return errors;
	}

	public String updateTaskXml(
			long taskId, String transition, Map parameterMap)
		throws WorkflowComponentException {

		Map errors = updateTask(taskId, transition, parameterMap);

		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("results");

		Iterator itr = errors.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry)itr.next();

			String name = (String)entry.getKey();
			String code = (String)entry.getValue();

			Element el = root.addElement("error");

			DocUtil.add(el, "name", name);
			DocUtil.add(el, "code", code);
		}

		return doc.asXML();
	}

	protected void createElement(ProcessDefinition definition, Element root) {
		Element el = root.addElement("definition");

		DocUtil.add(el, "definitionId", definition.getId());
		DocUtil.add(el, "name", definition.getName());
		DocUtil.add(el, "version", definition.getVersion());
	}

	protected void createElement(
			ProcessInstance instance, Element root, boolean includeToken)
		throws WorkflowComponentException {

		Element el = root.addElement("instance");

		DocUtil.add(el, "instanceId", instance.getId());
		DocUtil.add(el, "startDate", formatDateTime(instance.getStart()));
		DocUtil.add(el, "endDate", formatDateTime(instance.getEnd()));

		if (instance.hasEnded()) {
			DocUtil.add(el, "ended", "true");
		}
		else {
			DocUtil.add(el, "ended", "false");
		}

		createElement(instance.getProcessDefinition(), el);

		if (includeToken) {
			createElement(instance.getRootToken(), el, true);
		}
	}

	protected void createElement(TaskInstance task, Element root)
		throws WorkflowComponentException {

		Element el = root.addElement("task");

		DocUtil.add(el, "taskId", task.getId());
		DocUtil.add(el, "name", task.getName());
		DocUtil.add(el, "assignedUserId", task.getActorId());
		DocUtil.add(el, "createDate", formatDateTime(task.getCreate()));
		DocUtil.add(el, "startDate", formatDateTime(task.getStart()));
		DocUtil.add(el, "endDate", formatDateTime(task.getEnd()));

		createElement(task.getToken().getProcessInstance(), el, false);
	}

	protected void createElement(
		TaskFormElement taskFormElement, Element root) {

		Element el = root.addElement("taskFormElement");

		DocUtil.add(el, "type", taskFormElement.getType());
		DocUtil.add(el, "displayName", taskFormElement.getDisplayName());
		DocUtil.add(el, "variableName", taskFormElement.getVariableName());
		DocUtil.add(el, "value", taskFormElement.getValue());
		DocUtil.add(el, "readable", taskFormElement.isReadable());
		DocUtil.add(el, "writable", taskFormElement.isWritable());
		DocUtil.add(el, "required", taskFormElement.isRequired());

		List values = taskFormElement.getValueList();

		Element valuesEl = el.addElement("values");

		for (int i = 0; i < values.size(); i++) {
			String value = (String)values.get(i);

			DocUtil.add(valuesEl, "value", value);
		}
	}

	protected void createElement(
			Token token, Element root, boolean checkChildren)
		throws WorkflowComponentException {

		Element tokenEl = root.addElement("token");

		DocUtil.add(tokenEl, "tokenId", token.getId());
		DocUtil.add(tokenEl, "name", token.getNode().getName());

		if (token.getNode().toString().startsWith("Join")) {
			DocUtil.add(tokenEl, "type", "join");
		}
		else {
			DocUtil.add(tokenEl, "type", "default");
		}

		List tasks = getCurrentTasks(
			token.getProcessInstance().getId(), token.getId());

		if (tasks == null) {
			Element task = tokenEl.addElement("task");

			task.addElement("taskId").addText("null");
		}
		else {
			for (int i = 0; i < tasks.size(); i++) {
				TaskInstance task = (TaskInstance)tasks.get(i);

				createElement(task, tokenEl);
			}
		}

		if (checkChildren) {
			Map activeChildren = getActiveChildren(
				token.getProcessInstance().getId());

			if (hasActiveChildren(activeChildren)) {
				Iterator itr = activeChildren.values().iterator();

				while (itr.hasNext()) {
					Token child = (Token)itr.next();

					createElement(child, tokenEl, false);
				}
			}
		}
	}

	protected String formatDate(Date date) {
		if (date == null) {
			return StringPool.BLANK;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		return sdf.format(date);
	}

	protected Date formatDate(String date) throws ParseException {
		if (date == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		return sdf.parse(date);
	}

	protected String formatDateTime(Date date) {
		if (date == null) {
			return StringPool.BLANK;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");

		return sdf.format(date);
	}

	protected Date formatDateTime(String date) throws ParseException {
		if (date == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");

		return sdf.parse(date);
	}

	protected Map getActiveChildren(long instanceId) {
		ProcessInstance processInstance =
			jbpmContext.loadProcessInstance(instanceId);

		Map activeChildren = processInstance.getRootToken().getActiveChildren();

		WorkflowUtil.initTokens(new ArrayList(activeChildren.values()));

		return activeChildren;
	}

	protected String getCountXml(int count) {
		StringMaker sm = new StringMaker();

		sm.append("<result>");
		sm.append("<count>");
		sm.append(count);
		sm.append("</count>");
		sm.append("</result>");

		return sm.toString();
	}

	protected String getParamValue(Map parameterMap, String name) {
		String value = StringPool.BLANK;

		String[] values = (String[])parameterMap.get(name);

		if ((values != null) && (values.length > 0)) {
			value = values[0];
		}

		return value;
	}

	protected boolean hasActiveChildren(Map activeChildren) {
		Iterator itr = activeChildren.values().iterator();

		while (itr.hasNext()) {
			Token token = (Token)itr.next();

			if (!token.getNode().toString().startsWith("Join")) {
				return true;
			}
		}

		return false;
	}

	protected String validate(
		TaskFormElement taskFormElement, Map parameterMap) {

		String error = null;

		String type = taskFormElement.getType();
		String name = taskFormElement.getDisplayName();
		String value = getParamValue(parameterMap, name);

		if (type.equals(TaskFormElement.TYPE_CHECKBOX)) {
			if (taskFormElement.isRequired() && value.equals("false")) {
				error = "required-value";
			}
		}
		else if (type.equals(TaskFormElement.TYPE_DATE)) {
			value = getParamValue(parameterMap, JS.getSafeName(name));

			if (taskFormElement.isRequired()) {
				try {
					formatDate(value);

					String[] dateValues = StringUtil.split(value, "/");

					int month = GetterUtil.getInteger(dateValues[0]) - 1;
					int day = GetterUtil.getInteger(dateValues[1]);
					int year = GetterUtil.getInteger(dateValues[2]);

					if (!Validator.isDate(month, day, year)) {
						error = "invalid-date";
					}
				}
				catch (Exception e) {
					error = "invalid-date";
				}
			}
		}
		else if (type.equals(TaskFormElement.TYPE_EMAIL)) {
			if (taskFormElement.isRequired() && Validator.isNull(value)) {
				error = "required-value";
			}
			else if (!Validator.isNull(value) &&
					 !Validator.isEmailAddress(value)) {

				error = "invalid-email";
			}
		}
		else if (type.equals(TaskFormElement.TYPE_NUMBER)) {
			if (taskFormElement.isRequired() && Validator.isNull(value)) {
				error = "required-value";
			}
			else if (!Validator.isNull(value) && !Validator.isNumber(value)) {
				error = "invalid-number";
			}
		}
		else if (type.equals(TaskFormElement.TYPE_PASSWORD)) {
			if (taskFormElement.isRequired() && Validator.isNull(value)) {
				error = "required-value";
			}
		}
		else if (type.equals(TaskFormElement.TYPE_PHONE)) {
			if (taskFormElement.isRequired() && Validator.isNull(value)) {
				error = "required-value";
			}
			else if (!Validator.isNull(value) &&
					 !Validator.isPhoneNumber(value)) {

				error = "invalid-phone";
			}
		}
		else if (type.equals(TaskFormElement.TYPE_RADIO)) {
			if (taskFormElement.isRequired() && Validator.isNull(value)) {
				error = "required-value";
			}
		}
		else if (type.equals(TaskFormElement.TYPE_SELECT)) {
			if (taskFormElement.isRequired() && Validator.isNull(value)) {
				error = "required-value";
			}
		}
		else if (type.equals(TaskFormElement.TYPE_TEXT)) {
			if (taskFormElement.isRequired() && Validator.isNull(value)) {
				error = "required-value";
			}
		}
		else if (type.equals(TaskFormElement.TYPE_TEXTAREA)) {
			if (taskFormElement.isRequired() && Validator.isNull(value)) {
				error = "required-value";
			}
		}

		return error;
	}

	protected String userId;
	protected String timeZoneId;
	protected TimeZone timeZone;
	protected JbpmContext jbpmContext;
	protected GraphSession graphSession;
	protected TaskMgmtSession taskMgmtSession;

	private static Log _log =
		LogFactoryUtil.getLog(WorkflowComponentImpl.class);

}