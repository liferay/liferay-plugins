/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManager;
import com.liferay.portal.kernel.workflow.comparator.WorkflowInstanceStateComparator;
import com.liferay.portal.workflow.jbpm.dao.CustomSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

/**
 * <a href="WorkflowInstanceManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceManagerImpl implements WorkflowInstanceManager {

	public void deleteWorkflowInstance(long workflowInstanceId) {
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
			long userId, long workflowInstanceId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			Set<Transition> transitions = token.getAvailableTransitions();

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

	public WorkflowInstance getWorkflowInstance(long workflowInstanceId)
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
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Boolean completed)
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
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
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

			List<WorkflowInstance> workflowInstances =
				new ArrayList<WorkflowInstance>(processInstances.size());

			for (ProcessInstance processInstance : processInstances) {
				Token token = processInstance.getRootToken();

				WorkflowInstance workflowInstance = getWorkflowInstance(token);

				workflowInstances.add(workflowInstance);
			}

			return workflowInstances;
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
			long userId, long workflowInstanceId, String transitionName,
			Map<String, Object> context)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			if (context != null) {
				ProcessInstance processInstance = token.getProcessInstance();

				ContextInstance contextInstance =
					processInstance.getContextInstance();

				for (Map.Entry<String, Object> entry : context.entrySet()) {
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
			long userId, String workflowDefinitionName,
			Integer workflowDefinitionVersion, String transitionName,
			Map<String, Object> context)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			ProcessDefinition processDefinition = getProcessDefinition(
				jbpmContext, workflowDefinitionName, workflowDefinitionVersion);

			ProcessInstance processInstance = new ProcessInstance(
				processDefinition);

			if (context != null) {
				ContextInstance contextInstance =
					processInstance.getContextInstance();

				contextInstance.addVariables(context);
			}

			if (Validator.isNotNull(transitionName)) {
				processInstance.signal(transitionName);
			}
			else {
				processInstance.signal();
			}

			jbpmContext.save(processInstance);

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

	public WorkflowInstance updateContext(
			long workflowInstanceId, Map<String, Object> context)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			if (context != null) {
				ProcessInstance processInstance = token.getProcessInstance();

				ContextInstance contextInstance =
					processInstance.getContextInstance();

				for (Map.Entry<String, Object> entry : context.entrySet()) {
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

	private static Log _log = LogFactoryUtil.getLog(
		WorkflowInstanceManagerImpl.class);

	private JbpmConfiguration _jbpmConfiguration;

}