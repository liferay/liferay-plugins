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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManager;
import com.liferay.portal.workflow.jbpm.comparator.WorkflowInstanceStateComparator;
import com.liferay.portal.workflow.jbpm.dao.CustomSession;
import com.liferay.portal.workflow.jbpm.util.AssigneeRetrievalUtil;
import com.liferay.portal.workflow.jbpm.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.hibernate.Session;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.Node.NodeType;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;

/**
 * <a href="WorkflowInstanceManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class WorkflowInstanceManagerImpl implements WorkflowInstanceManager {

	public void deleteWorkflowInstance(
		long companyId, long workflowInstanceId) {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			GraphSession graphSession = jbpmContext.getGraphSession();

			Token token = graphSession.getToken(workflowInstanceId);

			if (token != null) {
				if (token.isRoot()) {
					CustomSession customSession = new CustomSession(
						jbpmContext);

					ProcessInstance processInstance =
						token.getProcessInstance();

					customSession.deleteProcessInstanceExtension(
						processInstance.getId());

					customSession.deleteTaskInstanceExtensions(
						processInstance.getId());

					customSession.deleteWorkflowLogs(processInstance.getId());

					graphSession.deleteProcessInstance(processInstance);
				}
				else if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to delete a child workflow instance " +
							workflowInstanceId + ". You can only delete a " +
								"root workflow instance.");
				}
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to delete workflow instance " + workflowInstanceId,
					e);
			}
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<String> getNextTransitionNames(
			long companyId, long userId, long workflowInstanceId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			Node node = token.getNode();

			NodeType nodeType = node.getNodeType();

			Set<Transition> transitions = Collections.EMPTY_SET;

			if (nodeType.equals(NodeType.Task)) {
				transitions = token.getAvailableTransitions();
			}

			List<String> transitionNames = new ArrayList<String>(
				transitions.size());

			for (Transition transition : transitions) {
				transitionNames.add(transition.getName());
			}

			return transitionNames;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowInstance getWorkflowInstance(
			long companyId, long workflowInstanceId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			return getWorkflowInstance(token);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowInstanceCount(
			long companyId, Long userId, String assetClassName,
			Long assetClassPK, Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countProcessInstances(
				companyId, userId, assetClassName, assetClassPK, completed);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowInstanceCount(
			long companyId, String workflowDefinitionName,
			Integer workflowDefinitionVersion, Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			ProcessDefinition processDefinition = getProcessDefinition(
				jbpmContext, workflowDefinitionName, workflowDefinitionVersion);

			return customSession.countProcessInstances(
				processDefinition.getId(), completed);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowInstance> getWorkflowInstances(
			long companyId, Long userId, String assetClassName,
			Long assetClassPK, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			List<ProcessInstance> processInstances =
				customSession.findProcessInstances(
					companyId, userId, assetClassName, assetClassPK, completed,
					start, end, orderByComparator);

			return toWorkflowInstaces(processInstances);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowInstance> getWorkflowInstances(
			long companyId, String workflowDefinitionName,
			Integer workflowDefinitionVersion, Boolean completed, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			CustomSession customSession = new CustomSession(jbpmContext);

			ProcessDefinition processDefinition = getProcessDefinition(
				jbpmContext, workflowDefinitionName, workflowDefinitionVersion);

			List<ProcessInstance> processInstances =
				customSession.findProcessInstances(
					processDefinition.getId(), completed, start, end,
					orderByComparator);

			return toWorkflowInstaces(processInstances);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		_jbpmConfiguration = jbpmConfiguration;
	}

	public WorkflowInstance signalWorkflowInstance(
			long companyId, long userId, long workflowInstanceId,
			String transitionName, Map<String, Serializable> workflowContext)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			if (workflowContext != null) {
				ProcessInstance processInstance = token.getProcessInstance();

				ContextInstance contextInstance =
					processInstance.getContextInstance();

				for (Map.Entry<String, Serializable> entry :
						workflowContext.entrySet()) {

					contextInstance.setVariableLocally(
						entry.getKey(), entry.getValue(), token);
				}

				jbpmContext.save(token);
			}

			if (Validator.isNull(transitionName)) {
				token.signal();
			}
			else {
				token.signal(transitionName);
			}

			jbpmContext.save(token);

			return getWorkflowInstance(token);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowInstance startWorkflowInstance(
			long companyId, long groupId, long userId,
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			String transitionName, Map<String, Serializable> workflowContext)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			ProcessDefinition processDefinition = getProcessDefinition(
				jbpmContext, workflowDefinitionName, workflowDefinitionVersion);

			ProcessInstance processInstance = new ProcessInstance(
				processDefinition);

			if (workflowContext != null) {
				ContextInstance contextInstance =
					processInstance.getContextInstance();

				Map<String, Object> variables =
					new HashMap<String, Object>(workflowContext);

				contextInstance.addVariables(variables);
			}

			if (Validator.isNotNull(transitionName)) {
				processInstance.signal(transitionName);
			}
			else {
				processInstance.signal();
			}

			jbpmContext.save(processInstance);

			saveProcessInstanceExtension(
				companyId, groupId, userId, processInstance, workflowContext);

			Token token = processInstance.getRootToken();

			return getWorkflowInstance(token);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowInstance updateWorkflowContext(
			long companyId, long workflowInstanceId,
			Map<String, Serializable> workflowContext)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			if (workflowContext != null) {
				ProcessInstance processInstance = token.getProcessInstance();

				ContextInstance contextInstance =
					processInstance.getContextInstance();

				for (Map.Entry<String, Serializable> entry :
						workflowContext.entrySet()) {

					contextInstance.setVariableLocally(
						entry.getKey(), entry.getValue(), token);
				}

				jbpmContext.save(token);
			}

			return getWorkflowInstance(token);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected ProcessDefinition getProcessDefinition(
			JbpmContext jbpmContext, String workflowDefinitionName,
			Integer workflowDefinitionVersion)
		throws WorkflowException {

		GraphSession graphSession = jbpmContext.getGraphSession();

		ProcessDefinition processDefinition = null;

		if (workflowDefinitionVersion != null) {
			processDefinition = graphSession.findProcessDefinition(
				workflowDefinitionName, workflowDefinitionVersion);
		}
		else {
			processDefinition = graphSession.findLatestProcessDefinition(
				workflowDefinitionName);
		}

		if (processDefinition != null) {
			return processDefinition;
		}

		throw new WorkflowException(
			"No workflow definition with name " + workflowDefinitionName +
				" and version " + workflowDefinitionVersion);
	}

	protected WorkflowInstance getWorkflowInstance(Token token) {
		WorkflowInstanceImpl workflowInstanceImpl =
			new WorkflowInstanceImpl(token);

		populateChildrenWorkflowInstances(token, workflowInstanceImpl);

		return workflowInstanceImpl;
	}

	protected void populateChildrenWorkflowInstances(
		Token token, WorkflowInstanceImpl workflowInstanceImpl) {

		Stack<ObjectValuePair<Token, WorkflowInstanceImpl>>
			objectValuePairs =
				new Stack<ObjectValuePair<Token, WorkflowInstanceImpl>>();

		objectValuePairs.push(
			new ObjectValuePair<Token, WorkflowInstanceImpl>(
				token, workflowInstanceImpl));

		while (!objectValuePairs.isEmpty()) {
			ObjectValuePair<Token, WorkflowInstanceImpl> objectValuePair =
				objectValuePairs.pop();

			Token parentToken = objectValuePair.getKey();
			WorkflowInstanceImpl parentWorkflowInstanceImpl =
				objectValuePair.getValue();

			Map<String, Token> children = parentToken.getChildren();

			if (children == null) {
				continue;
			}

			for (Token childToken : children.values()) {
				WorkflowInstanceImpl childWorkflowInstanceImpl =
					new WorkflowInstanceImpl(childToken);

				parentWorkflowInstanceImpl.addChildWorkflowInstance(
					childWorkflowInstanceImpl);

				childWorkflowInstanceImpl.setParentWorkflowInstance(
					parentWorkflowInstanceImpl);

				objectValuePairs.push(
					new ObjectValuePair<Token, WorkflowInstanceImpl>(
						childToken, childWorkflowInstanceImpl));
			}
		}

		Collections.sort(
			workflowInstanceImpl.getChildrenWorkflowInstances(),
			new WorkflowInstanceStateComparator(true));
	}

	protected void saveProcessInstanceExtension(
			long companyId, long groupId, long userId,
			ProcessInstance processInstance,
			Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		JbpmContext jbpmContext =  _jbpmConfiguration.getCurrentJbpmContext();

		String className = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
		long classPK = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		ProcessInstanceExtensionImpl processInstanceExtensionImpl =
			new ProcessInstanceExtensionImpl(
				companyId, groupId, userId, className, classPK,
				processInstance);

		Session session = jbpmContext.getSession();

		session.save(processInstanceExtensionImpl);

		TaskMgmtInstance taskMgmtInstance =
			processInstance.getTaskMgmtInstance();

		Collection<TaskInstance> taskInstances =
			taskMgmtInstance.getTaskInstances();

		if (taskInstances != null) {
			for (TaskInstance taskInstance : taskInstances) {
				List<Assignee> assignees = AssigneeRetrievalUtil.getAssignees(
					companyId, groupId, taskInstance.getActorId(),
					taskInstance.getPooledActors());

				String workflowContextJSON = WorkflowContextUtil.convertToJSON(
					workflowContext);

				TaskInstanceExtensionImpl taskInstanceExtensionImpl =
					new TaskInstanceExtensionImpl(
						companyId, groupId, userId, assignees,
						workflowContextJSON, taskInstance);

				session.save(taskInstanceExtensionImpl);
			}
		}
	}

	protected List<WorkflowInstance> toWorkflowInstaces(
		List<ProcessInstance> processInstances) {

		List<WorkflowInstance> workflowInstances =
			new ArrayList<WorkflowInstance>(processInstances.size());

		for (ProcessInstance processInstance : processInstances) {
			Token token = processInstance.getRootToken();

			WorkflowInstance workflowInstance = getWorkflowInstance(token);

			workflowInstances.add(workflowInstance);
		}

		return workflowInstances;
	}

	private static Log _log = LogFactoryUtil.getLog(
		WorkflowInstanceManagerImpl.class);

	private JbpmConfiguration _jbpmConfiguration;

}