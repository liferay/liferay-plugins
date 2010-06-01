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
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.List;

/**
 * <a href="GroupAwareTaskAssignmentSelector.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class GroupAwareTaskAssignmentSelector
	implements TaskAssignmentSelector {

	public KaleoTaskAssignment getTaskAssignment(
			KaleoTask task, ExecutionContext executionContext)
		throws SystemException, PortalException {

		KaleoInstanceToken instanceToken =
			executionContext.getKaleoInstanceToken();

		long groupId = instanceToken.getGroupId();
		Group group = GroupLocalServiceUtil.getGroup(groupId);

		KaleoTaskAssignment defaultTaskAssignment =
			task.getDefaultKaleoTaskAssignment();

		if (isValidAssignment(defaultTaskAssignment, group)) {
			return defaultTaskAssignment;
		}
		else {
			List<KaleoTaskAssignment> taskAssignments =
				task.getKaleoTaskAssignments();

			for (KaleoTaskAssignment taskAssignment : taskAssignments) {
				if (isValidAssignment(taskAssignment, group)) {
					return taskAssignment;
				}
			}
		}

		throw new WorkflowException(
			"Unable to select a valid assignment for task: " + task);
	}

	protected boolean isValidAssignment(
			KaleoTaskAssignment assignment, Group group)
		throws SystemException, PortalException {

		if (User.class.getName().equals(assignment.getAssigneeClassName())) {
			return true;
		}

		long roleId = assignment.getAssigneeClassPK();
		Role role = RoleLocalServiceUtil.getRole(roleId);
		int roleType = role.getType();

		if (role.getType() == RoleConstants.TYPE_REGULAR) {
			return true;
		}

		if (group.isCommunity() && (roleType == RoleConstants.TYPE_COMMUNITY)) {
			return true;
		}
		else if (group.isOrganization() &&
				 (roleType == RoleConstants.TYPE_ORGANIZATION)) {
			return true;
		}

		return false;
	}
}
