/*
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

package com.liferay.portal.workflow.kaleo.runtime.assignment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class ResourceActionTaskAssignmentSelector
	extends BaseTaskAssignmentSelector {

	public Collection<KaleoTaskAssignment> calculateTaskAssignments(
			KaleoTaskAssignment configuredKaleoTaskAssignment,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();

		ServiceContext serviceContext =
			executionContext.getServiceContext();

		String resourceName = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

		String resourceClassPK = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_PK);

		String actionId = configuredKaleoTaskAssignment.getAssigneeActionId();

		List<Role> roles = RoleLocalServiceUtil.getResourceRoles(
			serviceContext.getCompanyId(), resourceName,
			ResourceConstants.SCOPE_INDIVIDUAL, resourceClassPK, actionId);

		List<KaleoTaskAssignment> kaleoTaskAssignments =
			new ArrayList<KaleoTaskAssignment>(roles.size());

		getRoleKaleoTaskAssignments(roles, kaleoTaskAssignments);

		return kaleoTaskAssignments;
	}
}