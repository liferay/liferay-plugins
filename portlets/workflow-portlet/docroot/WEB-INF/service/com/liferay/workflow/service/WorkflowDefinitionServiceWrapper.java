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
 * <a href="WorkflowDefinitionServiceWrapper.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionServiceWrapper
	implements WorkflowDefinitionService {
	public WorkflowDefinitionServiceWrapper(
		WorkflowDefinitionService workflowDefinitionService) {
		_workflowDefinitionService = workflowDefinitionService;
	}

	public com.liferay.workflow.model.WorkflowDefinition addDefinition(
		java.lang.String xml, boolean addCommunityPermissions,
		boolean addGuestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _workflowDefinitionService.addDefinition(xml,
			addCommunityPermissions, addGuestPermissions);
	}

	public com.liferay.workflow.model.WorkflowDefinition addDefinition(
		java.lang.String xml, java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _workflowDefinitionService.addDefinition(xml,
			communityPermissions, guestPermissions);
	}

	public com.liferay.workflow.model.WorkflowDefinition addDefinition(
		java.lang.String xml, java.lang.Boolean addCommunityPermissions,
		java.lang.Boolean addGuestPermissions,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _workflowDefinitionService.addDefinition(xml,
			addCommunityPermissions, addGuestPermissions, communityPermissions,
			guestPermissions);
	}

	public void addDefinitionResources(com.liferay.portal.model.User user,
		long definitionId, boolean addCommunityPermissions,
		boolean addGuestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_workflowDefinitionService.addDefinitionResources(user, definitionId,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addDefinitionResources(com.liferay.portal.model.User user,
		long definitionId, java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_workflowDefinitionService.addDefinitionResources(user, definitionId,
			communityPermissions, guestPermissions);
	}

	public com.liferay.workflow.model.WorkflowDefinition getDefinition(
		long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _workflowDefinitionService.getDefinition(definitionId);
	}

	private WorkflowDefinitionService _workflowDefinitionService;
}