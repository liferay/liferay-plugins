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
 * <a href="WorkflowJobLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowJobLocalServiceWrapper implements WorkflowJobLocalService {
	public WorkflowJobLocalServiceWrapper(
		WorkflowJobLocalService workflowJobLocalService) {
		_workflowJobLocalService = workflowJobLocalService;
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowJob addWorkflowJob(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob)
		throws com.liferay.portal.SystemException {
		return _workflowJobLocalService.addWorkflowJob(workflowJob);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowJob createWorkflowJob(
		long workflowJobId) {
		return _workflowJobLocalService.createWorkflowJob(workflowJobId);
	}

	public void deleteWorkflowJob(long workflowJobId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_workflowJobLocalService.deleteWorkflowJob(workflowJobId);
	}

	public void deleteWorkflowJob(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob)
		throws com.liferay.portal.SystemException {
		_workflowJobLocalService.deleteWorkflowJob(workflowJob);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _workflowJobLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _workflowJobLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowJob getWorkflowJob(
		long workflowJobId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _workflowJobLocalService.getWorkflowJob(workflowJobId);
	}

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> getWorkflowJobs(
		int start, int end) throws com.liferay.portal.SystemException {
		return _workflowJobLocalService.getWorkflowJobs(start, end);
	}

	public int getWorkflowJobsCount() throws com.liferay.portal.SystemException {
		return _workflowJobLocalService.getWorkflowJobsCount();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowJob updateWorkflowJob(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob)
		throws com.liferay.portal.SystemException {
		return _workflowJobLocalService.updateWorkflowJob(workflowJob);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowJob updateWorkflowJob(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob,
		boolean merge) throws com.liferay.portal.SystemException {
		return _workflowJobLocalService.updateWorkflowJob(workflowJob, merge);
	}

	public WorkflowJobLocalService getWrappedWorkflowJobLocalService() {
		return _workflowJobLocalService;
	}

	private WorkflowJobLocalService _workflowJobLocalService;
}