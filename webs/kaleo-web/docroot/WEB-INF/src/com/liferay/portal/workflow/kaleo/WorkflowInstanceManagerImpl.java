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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManager;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.runtime.WorkflowEngine;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * <a href="WorkflowInstanceManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WorkflowInstanceManagerImpl implements WorkflowInstanceManager {

	public void deleteWorkflowInstance(long companyId, long workflowInstanceId)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		_workflowEngine.deleteWorkflowInstance(
			workflowInstanceId, serviceContext);
	}

	public List<String> getNextTransitionNames(
			long companyId, long userId, long workflowInstanceId)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			return _workflowEngine.getNextTransitionNames(
				workflowInstanceId, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public WorkflowInstance getWorkflowInstance(
			long companyId, long workflowInstanceId)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		return _workflowEngine.getWorkflowInstance(
			workflowInstanceId, serviceContext);
	}

	public int getWorkflowInstanceCount(
			long companyId, String workflowDefinitionName,
			Integer workflowDefinitionVersion, Boolean completed)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		return _workflowEngine.getWorkflowInstanceCount(
			workflowDefinitionName, workflowDefinitionVersion, completed,
			serviceContext);
	}

	public int getWorkflowInstanceCount(
			long companyId, Long userId, String assetClassName,
			Long assetClassPK, Boolean completed)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		return _workflowEngine.getWorkflowInstanceCount(
			userId, assetClassName, assetClassPK, completed,
			serviceContext);
	}

	public List<WorkflowInstance> getWorkflowInstances(
			long companyId, String workflowDefinitionName,
			Integer workflowDefinitionVersion, Boolean completed, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		return _workflowEngine.getWorkflowInstances(
			workflowDefinitionName, workflowDefinitionVersion, completed, start,
			end, orderByComparator, serviceContext);
	}

	public List<WorkflowInstance> getWorkflowInstances(
			long companyId, Long userId, String assetClassName,
			Long assetClassPK, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		return _workflowEngine.getWorkflowInstances(
			userId, assetClassName, assetClassPK, completed, start,
			end, orderByComparator, serviceContext);
	}

	public void setWorkflowEngine(WorkflowEngine workflowEngine) {
		_workflowEngine = workflowEngine;
	}

	public WorkflowInstance signalWorkflowInstance(
			long companyId, long userId, long workflowInstanceId,
			String transitionName, Map<String, Serializable> workflowContext)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		return _workflowEngine.signalWorkflowInstance(
			workflowInstanceId, transitionName, workflowContext,
			serviceContext);
	}

	public WorkflowInstance startWorkflowInstance(
			long companyId, long groupId, long userId,
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			String transitionName, Map<String, Serializable> workflowContext)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		return _workflowEngine.startWorkflowInstance(
			workflowDefinitionName, workflowDefinitionVersion, transitionName,
			workflowContext, serviceContext);
	}

	public WorkflowInstance updateWorkflowContext(
			long companyId, long workflowInstanceId,
			Map<String, Serializable> workflowContext)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		return _workflowEngine.updateContext(
			workflowInstanceId, workflowContext, serviceContext);
	}

	private WorkflowEngine _workflowEngine;

}