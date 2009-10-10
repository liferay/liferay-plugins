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
 * <a href="WorkflowDefinitionLocalServiceWrapper.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionLocalServiceWrapper
	implements WorkflowDefinitionLocalService {
	public WorkflowDefinitionLocalServiceWrapper(
		WorkflowDefinitionLocalService workflowDefinitionLocalService) {
		_workflowDefinitionLocalService = workflowDefinitionLocalService;
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowDefinition addWorkflowDefinition(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition)
		throws com.liferay.portal.SystemException {
		return _workflowDefinitionLocalService.addWorkflowDefinition(workflowDefinition);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowDefinition createWorkflowDefinition(
		long workflowDefinitionId) {
		return _workflowDefinitionLocalService.createWorkflowDefinition(workflowDefinitionId);
	}

	public void deleteWorkflowDefinition(long workflowDefinitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_workflowDefinitionLocalService.deleteWorkflowDefinition(workflowDefinitionId);
	}

	public void deleteWorkflowDefinition(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition)
		throws com.liferay.portal.SystemException {
		_workflowDefinitionLocalService.deleteWorkflowDefinition(workflowDefinition);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _workflowDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _workflowDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowDefinition getWorkflowDefinition(
		long workflowDefinitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _workflowDefinitionLocalService.getWorkflowDefinition(workflowDefinitionId);
	}

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> getWorkflowDefinitions(
		int start, int end) throws com.liferay.portal.SystemException {
		return _workflowDefinitionLocalService.getWorkflowDefinitions(start, end);
	}

	public int getWorkflowDefinitionsCount()
		throws com.liferay.portal.SystemException {
		return _workflowDefinitionLocalService.getWorkflowDefinitionsCount();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowDefinition updateWorkflowDefinition(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition)
		throws com.liferay.portal.SystemException {
		return _workflowDefinitionLocalService.updateWorkflowDefinition(workflowDefinition);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowDefinition updateWorkflowDefinition(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition,
		boolean merge) throws com.liferay.portal.SystemException {
		return _workflowDefinitionLocalService.updateWorkflowDefinition(workflowDefinition,
			merge);
	}

	public WorkflowDefinitionLocalService getWrappedWorkflowDefinitionLocalService() {
		return _workflowDefinitionLocalService;
	}

	private WorkflowDefinitionLocalService _workflowDefinitionLocalService;
}