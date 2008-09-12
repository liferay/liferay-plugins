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

package com.liferay.workflow.service;

/**
 * <a href="WorkflowComponentService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface WorkflowComponentService {
	public java.util.List getCurrentTasks(long instanceId, long tokenId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getCurrentTasksXml(long instanceId, long tokenId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String deploy(java.lang.String xml)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.Object getDefinition(long definitionId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.util.List getDefinitions(long definitionId,
		java.lang.String name, int start, int end)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getDefinitionsXml(long definitionId,
		java.lang.String name, int start, int end)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public int getDefinitionsCount(long definitionId, java.lang.String name)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getDefinitionsCountXml(long definitionId,
		java.lang.String name)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getDefinitionXml(long definitionId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.util.List getInstances(long definitionId, long instanceId,
		java.lang.String definitionName, java.lang.String definitionVersion,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean retrieveUserInstances,
		boolean andOperator, int start, int end)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public int getInstancesCount(long definitionId, long instanceId,
		java.lang.String definitionName, java.lang.String definitionVersion,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean retrieveUserInstances,
		boolean andOperator)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getInstancesCountXml(long definitionId,
		long instanceId, java.lang.String definitionName,
		java.lang.String definitionVersion, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, boolean hideEndedTasks,
		boolean retrieveUserInstances, boolean andOperator)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getInstancesXml(long definitionId, long instanceId,
		java.lang.String definitionName, java.lang.String definitionVersion,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean retrieveUserInstances,
		boolean andOperator, int start, int end)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public com.liferay.workflow.model.WorkflowTask getTask(long taskId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getTaskXml(long taskId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.util.List getTaskFormElements(long taskId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getTaskFormElementsXml(long taskId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.util.List getTaskTransitions(long taskId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getTaskTransitionsXml(long taskId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.util.List getUserTasks(long instanceId,
		java.lang.String taskName, java.lang.String definitionName,
		java.lang.String assignedTo, java.lang.String createDateGT,
		java.lang.String createDateLT, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, boolean hideEndedTasks,
		boolean andOperator, int start, int end)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public int getUserTasksCount(long instanceId, java.lang.String taskName,
		java.lang.String definitionName, java.lang.String assignedTo,
		java.lang.String createDateGT, java.lang.String createDateLT,
		java.lang.String startDateGT, java.lang.String startDateLT,
		java.lang.String endDateGT, java.lang.String endDateLT,
		boolean hideEndedTasks, boolean andOperator)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getUserTasksCountXml(long instanceId,
		java.lang.String taskName, java.lang.String definitionName,
		java.lang.String assignedTo, java.lang.String createDateGT,
		java.lang.String createDateLT, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, boolean hideEndedTasks, boolean andOperator)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String getUserTasksXml(long instanceId,
		java.lang.String taskName, java.lang.String definitionName,
		java.lang.String assignedTo, java.lang.String createDateGT,
		java.lang.String createDateLT, java.lang.String startDateGT,
		java.lang.String startDateLT, java.lang.String endDateGT,
		java.lang.String endDateLT, boolean hideEndedTasks,
		boolean andOperator, int start, int end)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public void signalInstance(long instanceId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public void signalToken(long instanceId, long tokenId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String startWorkflow(long definitionId)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.util.Map updateTask(long taskId, java.lang.String transition,
		java.util.Map parameterMap)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;

	public java.lang.String updateTaskXml(long taskId,
		java.lang.String transition, java.util.Map parameterMap)
		throws java.rmi.RemoteException,
			com.liferay.portal.kernel.jbi.WorkflowComponentException;
}