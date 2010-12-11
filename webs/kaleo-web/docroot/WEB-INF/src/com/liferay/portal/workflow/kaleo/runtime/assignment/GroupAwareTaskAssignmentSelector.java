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

package com.liferay.portal.workflow.kaleo.runtime.assignment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceAction;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class GroupAwareTaskAssignmentSelector
	implements TaskAssignmentSelector {

	public Collection<KaleoTaskAssignment> calculateTaskAssignments(
			Collection<KaleoTaskAssignment> kaleoTaskAssignments,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		long groupId = kaleoInstanceToken.getGroupId();

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isLayout()) {
			group = GroupLocalServiceUtil.getGroup(group.getParentGroupId());
		}

		List<KaleoTaskAssignment> calculatedKaleoTaskAssignments =
			new ArrayList<KaleoTaskAssignment>();

		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			if (isValidAssignment(kaleoTaskAssignment, group)) {
				calculatedKaleoTaskAssignments.add(kaleoTaskAssignment);
			}
		}

		return calculatedKaleoTaskAssignments;
	}

	protected boolean isValidAssignment(
			KaleoTaskAssignment kaleoTaskAssignment, Group group)
		throws PortalException, SystemException {

		String assigneeClassName = kaleoTaskAssignment.getAssigneeClassName();

		if (assigneeClassName.equals(ResourceAction.class.getName()) ||
			assigneeClassName.equals(User.class.getName())) {

			return true;
		}

		long roleId = kaleoTaskAssignment.getAssigneeClassPK();

		Role role = RoleLocalServiceUtil.getRole(roleId);

		if (role.getType() == RoleConstants.TYPE_REGULAR) {
			return true;
		}
		else if (group.isCommunity() &&
				 (role.getType() == RoleConstants.TYPE_COMMUNITY)) {

			return true;
		}
		else if (group.isOrganization() &&
				 (role.getType() == RoleConstants.TYPE_ORGANIZATION)) {

			return true;
		}

		return false;
	}

}