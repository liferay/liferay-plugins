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

package com.liferay.portal.workflow.edoras.service;

/**
 * <a href="WorkflowInstanceLocalServiceWrapper.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceLocalServiceWrapper
	implements WorkflowInstanceLocalService {
	public WorkflowInstanceLocalServiceWrapper(
		WorkflowInstanceLocalService workflowInstanceLocalService) {
		_workflowInstanceLocalService = workflowInstanceLocalService;
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance addWorkflowInstance(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance)
		throws com.liferay.portal.SystemException {
		return _workflowInstanceLocalService.addWorkflowInstance(workflowInstance);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance createWorkflowInstance(
		long workflowInstanceId) {
		return _workflowInstanceLocalService.createWorkflowInstance(workflowInstanceId);
	}

	public void deleteWorkflowInstance(long workflowInstanceId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_workflowInstanceLocalService.deleteWorkflowInstance(workflowInstanceId);
	}

	public void deleteWorkflowInstance(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance)
		throws com.liferay.portal.SystemException {
		_workflowInstanceLocalService.deleteWorkflowInstance(workflowInstance);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _workflowInstanceLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _workflowInstanceLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance getWorkflowInstance(
		long workflowInstanceId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _workflowInstanceLocalService.getWorkflowInstance(workflowInstanceId);
	}

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> getWorkflowInstances(
		int start, int end) throws com.liferay.portal.SystemException {
		return _workflowInstanceLocalService.getWorkflowInstances(start, end);
	}

	public int getWorkflowInstancesCount()
		throws com.liferay.portal.SystemException {
		return _workflowInstanceLocalService.getWorkflowInstancesCount();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance updateWorkflowInstance(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance)
		throws com.liferay.portal.SystemException {
		return _workflowInstanceLocalService.updateWorkflowInstance(workflowInstance);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance updateWorkflowInstance(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance,
		boolean merge) throws com.liferay.portal.SystemException {
		return _workflowInstanceLocalService.updateWorkflowInstance(workflowInstance,
			merge);
	}

	public WorkflowInstanceLocalService getWrappedWorkflowInstanceLocalService() {
		return _workflowInstanceLocalService;
	}

	private WorkflowInstanceLocalService _workflowInstanceLocalService;
}