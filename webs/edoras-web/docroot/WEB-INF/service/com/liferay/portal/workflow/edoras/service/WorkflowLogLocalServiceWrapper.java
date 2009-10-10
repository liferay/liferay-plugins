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
 * <a href="WorkflowLogLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogLocalServiceWrapper implements WorkflowLogLocalService {
	public WorkflowLogLocalServiceWrapper(
		WorkflowLogLocalService workflowLogLocalService) {
		_workflowLogLocalService = workflowLogLocalService;
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowLog addWorkflowLog(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog)
		throws com.liferay.portal.SystemException {
		return _workflowLogLocalService.addWorkflowLog(workflowLog);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowLog createWorkflowLog(
		long workflowLogId) {
		return _workflowLogLocalService.createWorkflowLog(workflowLogId);
	}

	public void deleteWorkflowLog(long workflowLogId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_workflowLogLocalService.deleteWorkflowLog(workflowLogId);
	}

	public void deleteWorkflowLog(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog)
		throws com.liferay.portal.SystemException {
		_workflowLogLocalService.deleteWorkflowLog(workflowLog);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _workflowLogLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _workflowLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowLog getWorkflowLog(
		long workflowLogId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _workflowLogLocalService.getWorkflowLog(workflowLogId);
	}

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> getWorkflowLogs(
		int start, int end) throws com.liferay.portal.SystemException {
		return _workflowLogLocalService.getWorkflowLogs(start, end);
	}

	public int getWorkflowLogsCount() throws com.liferay.portal.SystemException {
		return _workflowLogLocalService.getWorkflowLogsCount();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowLog updateWorkflowLog(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog)
		throws com.liferay.portal.SystemException {
		return _workflowLogLocalService.updateWorkflowLog(workflowLog);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowLog updateWorkflowLog(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog,
		boolean merge) throws com.liferay.portal.SystemException {
		return _workflowLogLocalService.updateWorkflowLog(workflowLog, merge);
	}

	public WorkflowLogLocalService getWrappedWorkflowLogLocalService() {
		return _workflowLogLocalService;
	}

	private WorkflowLogLocalService _workflowLogLocalService;
}