/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public abstract class BaseTaskAssignmentSelector
	implements TaskAssignmentSelector {

	protected Collection<KaleoTaskAssignment> getKaleoTaskAssignments(
		Map<String, ?> results) {

		List<KaleoTaskAssignment> kaleoTaskAssignments = new ArrayList<>();

		User user = (User)results.get(USER_ASSIGNMENT);

		if (user != null) {
			KaleoTaskAssignment kaleoTaskAssignment =
				getUserKaleoTaskAssignment(user);

			kaleoTaskAssignments.add(kaleoTaskAssignment);
		}
		else {
			List<Role> roles = (List<Role>)results.get(ROLES_ASSIGNMENT);

			getRoleKaleoTaskAssignments(roles, kaleoTaskAssignments);
		}

		return kaleoTaskAssignments;
	}

	protected void getRoleKaleoTaskAssignments(
		List<Role> roles, List<KaleoTaskAssignment> kaleoTaskAssignments) {

		if (roles == null) {
			return;
		}

		for (Role role : roles) {
			KaleoTaskAssignment kaleoTaskAssignment =
				new KaleoTaskAssignmentImpl();

			kaleoTaskAssignment.setAssigneeClassName(Role.class.getName());
			kaleoTaskAssignment.setAssigneeClassPK(role.getRoleId());

			kaleoTaskAssignments.add(kaleoTaskAssignment);
		}
	}

	protected KaleoTaskAssignment getUserKaleoTaskAssignment(User user) {
		KaleoTaskAssignment kaleoTaskAssignment = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignment.setAssigneeClassName(User.class.getName());
		kaleoTaskAssignment.setAssigneeClassPK(user.getUserId());

		return kaleoTaskAssignment;
	}

	protected static final String ROLES_ASSIGNMENT = "roles";

	protected static final String USER_ASSIGNMENT = "user";

}