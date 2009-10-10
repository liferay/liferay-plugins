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

package com.liferay.workflow.service;

/**
 * <a href="WorkflowComponentServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowComponentServiceWrapper implements WorkflowComponentService {
	public WorkflowComponentServiceWrapper(
		WorkflowComponentService workflowComponentService) {
		_workflowComponentService = workflowComponentService;
	}

	public java.util.List getCurrentTasks(long instanceId, long tokenId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getCurrentTasks(instanceId, tokenId);
	}

	public java.lang.String getCurrentTasksXml(long instanceId, long tokenId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getCurrentTasksXml(instanceId, tokenId);
	}

	public java.lang.String deploy(java.lang.String xml)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.deploy(xml);
	}

	public java.lang.Object getDefinition(long definitionId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getDefinition(definitionId);
	}

	public java.util.List getDefinitions(long definitionId,
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getDefinitions(definitionId, name,
			start, end);
	}

	public java.lang.String getDefinitionsXml(long definitionId,
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getDefinitionsXml(definitionId, name,
			start, end);
	}

	public int getDefinitionsCount(long definitionId, java.lang.String name)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getDefinitionsCount(definitionId, name);
	}

	public java.lang.String getDefinitionsCountXml(long definitionId,
		java.lang.String name)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getDefinitionsCountXml(definitionId,
			name);
	}

	public java.lang.String getDefinitionXml(long definitionId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getDefinitionXml(definitionId);
	}

	public java.util.List getInstances(long definitionId, long instanceId,
		java.lang.String definitionName, java.lang.String definitionVersion,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean retrieveUserInstances,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getInstances(definitionId, instanceId,
			definitionName, definitionVersion, startDateGT, startDateLT,
			endDateGT, endDateLT, hideEndedTasks, retrieveUserInstances,
			andOperator, start, end);
	}

	public int getInstancesCount(long definitionId, long instanceId,
		java.lang.String definitionName, java.lang.String definitionVersion,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean retrieveUserInstances,
		boolean andOperator)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getInstancesCount(definitionId,
			instanceId, definitionName, definitionVersion, startDateGT,
			startDateLT, endDateGT, endDateLT, hideEndedTasks,
			retrieveUserInstances, andOperator);
	}

	public java.lang.String getInstancesCountXml(long definitionId,
		long instanceId, java.lang.String definitionName,
		java.lang.String definitionVersion, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, boolean hideEndedTasks,
		boolean retrieveUserInstances, boolean andOperator)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getInstancesCountXml(definitionId,
			instanceId, definitionName, definitionVersion, startDateGT,
			startDateLT, endDateGT, endDateLT, hideEndedTasks,
			retrieveUserInstances, andOperator);
	}

	public java.lang.String getInstancesXml(long definitionId, long instanceId,
		java.lang.String definitionName, java.lang.String definitionVersion,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean retrieveUserInstances,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getInstancesXml(definitionId,
			instanceId, definitionName, definitionVersion, startDateGT,
			startDateLT, endDateGT, endDateLT, hideEndedTasks,
			retrieveUserInstances, andOperator, start, end);
	}

	public com.liferay.workflow.model.WorkflowTask getTask(long taskId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getTask(taskId);
	}

	public java.lang.String getTaskXml(long taskId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getTaskXml(taskId);
	}

	public java.util.List getTaskFormElements(long taskId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getTaskFormElements(taskId);
	}

	public java.lang.String getTaskFormElementsXml(long taskId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getTaskFormElementsXml(taskId);
	}

	public java.util.List getTaskTransitions(long taskId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getTaskTransitions(taskId);
	}

	public java.lang.String getTaskTransitionsXml(long taskId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getTaskTransitionsXml(taskId);
	}

	public java.util.List getUserTasks(long instanceId,
		java.lang.String taskName, java.lang.String definitionName,
		java.lang.String assignedTo, java.lang.String createDateGT,
		java.lang.String createDateLT, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, boolean hideEndedTasks,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getUserTasks(instanceId, taskName,
			definitionName, assignedTo, createDateGT, createDateLT,
			startDateGT, startDateLT, endDateGT, endDateLT, hideEndedTasks,
			andOperator, start, end);
	}

	public int getUserTasksCount(long instanceId, java.lang.String taskName,
		java.lang.String definitionName, java.lang.String assignedTo,
		java.lang.String createDateGT, java.lang.String createDateLT,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean andOperator)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getUserTasksCount(instanceId,
			taskName, definitionName, assignedTo, createDateGT, createDateLT,
			startDateGT, startDateLT, endDateGT, endDateLT, hideEndedTasks,
			andOperator);
	}

	public java.lang.String getUserTasksCountXml(long instanceId,
		java.lang.String taskName, java.lang.String definitionName,
		java.lang.String assignedTo, java.lang.String createDateGT,
		java.lang.String createDateLT, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, boolean hideEndedTasks, boolean andOperator)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getUserTasksCountXml(instanceId,
			taskName, definitionName, assignedTo, createDateGT, createDateLT,
			startDateGT, startDateLT, endDateGT, endDateLT, hideEndedTasks,
			andOperator);
	}

	public java.lang.String getUserTasksXml(long instanceId,
		java.lang.String taskName, java.lang.String definitionName,
		java.lang.String assignedTo, java.lang.String createDateGT,
		java.lang.String createDateLT, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, boolean hideEndedTasks,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.getUserTasksXml(instanceId, taskName,
			definitionName, assignedTo, createDateGT, createDateLT,
			startDateGT, startDateLT, endDateGT, endDateLT, hideEndedTasks,
			andOperator, start, end);
	}

	public void signalInstance(long instanceId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		_workflowComponentService.signalInstance(instanceId);
	}

	public void signalToken(long instanceId, long tokenId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		_workflowComponentService.signalToken(instanceId, tokenId);
	}

	public java.lang.String startWorkflow(long definitionId)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.startWorkflow(definitionId);
	}

	public java.util.Map updateTask(long taskId, java.lang.String transition,
		java.util.Map parameterMap)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.updateTask(taskId, transition,
			parameterMap);
	}

	public java.lang.String updateTaskXml(long taskId,
		java.lang.String transition, java.util.Map parameterMap)
		throws com.liferay.portal.kernel.jbi.WorkflowComponentException {
		return _workflowComponentService.updateTaskXml(taskId, transition,
			parameterMap);
	}

	public WorkflowComponentService getWrappedWorkflowComponentService() {
		return _workflowComponentService;
	}

	private WorkflowComponentService _workflowComponentService;
}