/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstanceHistory;
import com.liferay.portal.kernel.workflow.WorkflowInstanceInfo;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManager;
import com.liferay.portal.workflow.jbpm.db.CustomSession;

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
import org.jbpm.db.LoggingSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.logging.log.ProcessLog;

/**
 * <a href="WorkflowInstanceManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceManagerImpl implements WorkflowInstanceManager {

	public WorkflowInstanceInfo addContextInformation(
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

			return new WorkflowInstanceInfoImpl(token);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<String> getPossibleNextActivityNames(
			long workflowInstanceId, long userId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			Set<Transition> transitions = token.getAvailableTransitions();

			List<String> names = new ArrayList<String>(transitions.size());

			for (Transition transition : transitions) {
				names.add(transition.getName());
			}

			return names;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public List<WorkflowInstanceHistory> getWorkflowInstanceHistory(
			long workflowInstanceId, boolean includeChildren, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		List<WorkflowInstanceHistory> workflowInstanceHistories =
			new ArrayList<WorkflowInstanceHistory>();

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			LoggingSession loggingSession = jbpmContext.getLoggingSession();

			Token token = jbpmContext.loadToken(workflowInstanceId);

			List<ProcessLog> processLogs = loggingSession.findLogsByToken(
				token);

			for (ProcessLog processLog : processLogs) {
				workflowInstanceHistories.add(
					new WorkflowInstanceHistoryImpl(processLog));
			}

			if (includeChildren) {
				Stack<Token> tokens = new Stack<Token>();

				tokens.addAll(token.getChildren().values());

				while (!tokens.isEmpty()) {
					Token childToken = tokens.pop();

					processLogs = loggingSession.findLogsByToken(childToken);

					for (ProcessLog processLog : processLogs) {
						workflowInstanceHistories.add(
							new WorkflowInstanceHistoryImpl(processLog));
					}

					tokens.addAll(childToken.getChildren().values());
				}
			}

			Collections.sort(workflowInstanceHistories, orderByComparator);

			workflowInstanceHistories = ListUtil.subList(
				workflowInstanceHistories, start, end);

			return workflowInstanceHistories;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public int getWorkflowInstanceHistoryCount(
			long workflowInstanceId, boolean includeChildren)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			LoggingSession loggingSession = jbpmContext.getLoggingSession();

			Token token = jbpmContext.loadToken(workflowInstanceId);

			List<ProcessLog> processLogs = loggingSession.findLogsByToken(
				token);

			int count = processLogs.size();

			if (includeChildren) {
				Stack<Token> tokenStack = new Stack<Token>();

				tokenStack.addAll(token.getChildren().values());

				while (tokenStack.isEmpty() == false) {
					Token childToken = tokenStack.pop();

					processLogs = loggingSession.findLogsByToken(childToken);

					count += processLogs.size();

					tokenStack.addAll(childToken.getChildren().values());
				}
			}

			return count;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowInstanceInfo getWorkflowInstanceInfo(
			long workflowInstanceId, boolean retrieveChildrenInfo)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			WorkflowInstanceInfoImpl workflowInstanceInfoImpl =
				new WorkflowInstanceInfoImpl(token);

			if (retrieveChildrenInfo) {
				populateChildrenWorkflowInstanceInfos(
					token, workflowInstanceInfoImpl);
			}

			return workflowInstanceInfoImpl;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowInstanceInfo getWorkflowInstanceInfo(
			String relationType, long relationId, boolean retrieveChildrenInfo)
		throws WorkflowException {

		throw new WorkflowException(new UnsupportedOperationException());
	}

	public int getWorkflowInstanceInfoCount(
			String workflowDefinitionName, Integer workflowDefinitionVersion)
		throws WorkflowException {

		return getWorkflowInstanceInfoCount(
			workflowDefinitionName, workflowDefinitionVersion, Boolean.FALSE);
	}

	public int getWorkflowInstanceInfoCount(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean completed)
		throws WorkflowException {

		return getWorkflowInstanceInfoCount(
			workflowDefinitionName, workflowDefinitionVersion, completed);
	}

	public int getWorkflowInstanceInfoCount(
			String relationType, long relationId)
		throws WorkflowException {

		throw new WorkflowException(new UnsupportedOperationException());
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean completed, boolean retrieveChildrenInfo, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowInstanceInfos(
			workflowDefinitionName, workflowDefinitionVersion, completed,
			retrieveChildrenInfo, start, end, orderByComparator);
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean retrieveChildrenInfo, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowInstanceInfos(
			workflowDefinitionName, workflowDefinitionVersion,
			retrieveChildrenInfo, null, start, end, orderByComparator);
	}

	public List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String relationType, long relationId, boolean retrieveChildrenInfo,
			int start, int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		throw new WorkflowException(new UnsupportedOperationException());
	}

	public void removeWorkflowInstance(long workflowInstanceId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			GraphSession graphSession = jbpmContext.getGraphSession();

			Token token = graphSession.getToken(workflowInstanceId);

			if (token != null) {
				if (token.isRoot()) {
					graphSession.deleteProcessInstance(
						token.getProcessInstance());
				}
				else if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to remove a sub workflow instance with id " +
							workflowInstanceId + ". You can only remove a " +
								"root workflow instance.");
				}
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to remove workflow instance with id " +
						workflowInstanceId,
					e);
			}
		}
		finally {
			jbpmContext.close();
		}
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		_jbpmConfiguration = jbpmConfiguration;
	}

	public WorkflowInstanceInfo signalWorkflowInstance(
			long workflowInstanceId, Map<String, Object> attributes,
			long callingUserId)
		throws WorkflowException {

		return signalWorkflowInstance(
			workflowInstanceId, null, attributes, callingUserId);
	}

	public WorkflowInstanceInfo signalWorkflowInstance(
			long workflowInstanceId, String activityName,
			Map<String, Object> attributes, long callingUserId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Token token = jbpmContext.loadToken(workflowInstanceId);

			if (attributes != null) {
				ProcessInstance processInstance = token.getProcessInstance();

				ContextInstance contextInstance =
					processInstance.getContextInstance();

				for (Map.Entry<String, Object> entry : attributes.entrySet()) {
					contextInstance.setVariableLocally(
						entry.getKey(), entry.getValue(), token);
				}

				jbpmContext.save(token);
			}

			if (Validator.isNull(activityName)) {
				token.signal();
			}
			else {
				token.signal(activityName);
			}

			jbpmContext.save(token);

			return new WorkflowInstanceInfoImpl(token);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowInstanceInfo startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Map<String, Object> context, long callingUserId)
		throws WorkflowException {

		return startWorkflowInstance(
			workflowDefinitionName, workflowDefinitionVersion, context,
			callingUserId, null);
	}

	public WorkflowInstanceInfo startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Map<String, Object> context, long callingUserId,
			String activityName)
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

			if (Validator.isNotNull(activityName)) {
				processInstance.signal(activityName);
			}
			else {
				processInstance.signal();
			}

			jbpmContext.save(processInstance);

			return new WorkflowInstanceInfoImpl(processInstance.getRootToken());
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	public WorkflowInstanceInfo startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			String relationType, long relationId, Map<String, Object> context,
			long callingUserId)
		throws WorkflowException {

		throw new WorkflowException(new UnsupportedOperationException());
	}

	public WorkflowInstanceInfo startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			String relationType, long relationId, Map<String, Object> context,
			long callingUserId, String activityName)
		throws WorkflowException {

		throw new WorkflowException(new UnsupportedOperationException());
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

	protected int getWorkflowInstanceInfoCount(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Boolean completed)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			ProcessDefinition processDefinition = getProcessDefinition(
				jbpmContext, workflowDefinitionName, workflowDefinitionVersion);

			CustomSession customSession = new CustomSession(jbpmContext);

			return customSession.countProcessInstances(
				processDefinition.getId(), completed);
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected List<WorkflowInstanceInfo> getWorkflowInstanceInfos(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			boolean retrieveChildrenInfo, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			ProcessDefinition processDefinition = getProcessDefinition(
				jbpmContext, workflowDefinitionName, workflowDefinitionVersion);

			CustomSession customSession = new CustomSession(jbpmContext);

			List<ProcessInstance> processInstances =
				customSession.findProcessInstances(
					processDefinition.getId(), completed, start, end,
					orderByComparator);

			List<WorkflowInstanceInfo> workflowInstanceInfos =
				new ArrayList<WorkflowInstanceInfo>(processInstances.size());

			for (ProcessInstance processInstance : processInstances) {
				Token token = processInstance.getRootToken();

				WorkflowInstanceInfoImpl workflowInstanceInfoImpl =
					new WorkflowInstanceInfoImpl(token);

				if (retrieveChildrenInfo) {
					populateChildrenWorkflowInstanceInfos(
						token, workflowInstanceInfoImpl);
				}

				workflowInstanceInfos.add(workflowInstanceInfoImpl);
			}

			return workflowInstanceInfos;
		}
		catch (WorkflowException we) {
			throw we;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected void populateChildrenWorkflowInstanceInfos(
		Token token, WorkflowInstanceInfoImpl workflowInstanceInfoImpl) {

		Stack<ObjectValuePair<Token, WorkflowInstanceInfoImpl>>
			objectValuePairs =
				new Stack<ObjectValuePair<Token, WorkflowInstanceInfoImpl>>();

		objectValuePairs.push(
			new ObjectValuePair<Token, WorkflowInstanceInfoImpl>(
				token, workflowInstanceInfoImpl));

		while (!objectValuePairs.isEmpty()) {
			ObjectValuePair<Token, WorkflowInstanceInfoImpl> objectValuePair =
				objectValuePairs.pop();

			Token parentToken = objectValuePair.getKey();
			WorkflowInstanceInfoImpl parentWorkflowInstanceInfoImpl =
				objectValuePair.getValue();

			for (Token childToken : parentToken.getChildren().values()) {
				WorkflowInstanceInfoImpl childWorkflowInstanceInfoImpl =
					new WorkflowInstanceInfoImpl(childToken);

				parentWorkflowInstanceInfoImpl.addChild(
					childWorkflowInstanceInfoImpl);

				childWorkflowInstanceInfoImpl.setParent(
					parentWorkflowInstanceInfoImpl);

				objectValuePairs.push(
					new ObjectValuePair<Token, WorkflowInstanceInfoImpl>(
						childToken, childWorkflowInstanceInfoImpl));
			}
		}
	}

	private static Log _log =
		LogFactoryUtil.getLog(WorkflowInstanceManagerImpl.class);

	private JbpmConfiguration _jbpmConfiguration;

}