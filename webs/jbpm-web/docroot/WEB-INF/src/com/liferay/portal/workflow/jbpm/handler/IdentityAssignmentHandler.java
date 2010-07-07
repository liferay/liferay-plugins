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

package com.liferay.portal.workflow.jbpm.handler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.jbpm.util.Assignment;
import com.liferay.portal.workflow.jbpm.util.AssignmentType;
import com.liferay.portal.workflow.jbpm.util.RoleAssignment;
import com.liferay.portal.workflow.jbpm.util.RoleRetrievalUtil;
import com.liferay.portal.workflow.jbpm.util.UserAssignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

/**
 * <a href="IdentityAssignmentHandler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class IdentityAssignmentHandler implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext)
		throws Exception {

		Set<Assignment> assignments = new HashSet<Assignment>();

		if (roles != null) {
			for (Map<String, String> role : roles) {
				long roleId = GetterUtil.getLong(role.get("role-id"));
				String roleType = role.get("role-type");
				String roleName = role.get("role-name");

				RoleAssignment roleAssignment = null;

				if (Validator.isNotNull(roleName)) {
					boolean autoCreate = GetterUtil.getBoolean(
						role.get("auto-create"), true);

					roleAssignment = new RoleAssignment(roleName, roleType);
					roleAssignment.setAutoCreate(autoCreate);
				}
				else {
					roleAssignment = new RoleAssignment(roleId, roleType);
				}

				assignments.add(roleAssignment);
			}
		}

		if (user != null) {
			long userId = GetterUtil.getLong(user.get("user-id"));
			String screenName = user.get("screen-name");
			String emailAddress = user.get("email-address");

			UserAssignment userAssignment = new UserAssignment(
				userId, screenName, emailAddress);

			assignments.add(userAssignment);
		}

		setAssignee(assignable, executionContext, assignments);
	}

	protected void setAssignee(
			Assignable assignable, ExecutionContext executionContext,
			Set<Assignment> assignments)
		throws PortalException, SystemException {

		ProcessInstance processInstance = executionContext.getProcessInstance();
		ContextInstance contextInstance = processInstance.getContextInstance();

		long companyId = GetterUtil.getLong(
			(String)contextInstance.getVariable(
				WorkflowConstants.CONTEXT_COMPANY_ID));

		List<String> roleIds = new ArrayList<String>();

		for (Assignment assignment : assignments) {
			AssignmentType assignmentType = assignment.getAssignmentType();

			if (assignmentType.equals(AssignmentType.ROLE)) {
				RoleAssignment roleAssignment = (RoleAssignment)assignment;

				int roleType = RoleRetrievalUtil.getRoleType(
					roleAssignment.getRoleType());

				ServiceContext serviceContext = new ServiceContext();
				serviceContext.setCompanyId(companyId);

				Role role = RoleRetrievalUtil.getRole(
					roleAssignment.getRoleName(), roleType,
					roleAssignment.isAutoCreate(), serviceContext);

				roleIds.add(Long.toString(role.getRoleId()));
			}
			else if (assignmentType.equals(AssignmentType.USER)) {
				UserAssignment userAssignment = (UserAssignment)assignment;

				User user = null;

				if (userAssignment.getUserId() > 0) {
					user = UserLocalServiceUtil.getUser(
						userAssignment.getUserId());
				}
				else if (Validator.isNotNull(
					userAssignment.getEmailAddress())) {

					user = UserLocalServiceUtil.getUserByEmailAddress(
						companyId, userAssignment.getEmailAddress());
				}
				else if (Validator.isNotNull(userAssignment.getScreenName())) {
					user = UserLocalServiceUtil.getUserByScreenName(
						companyId, userAssignment.getScreenName());
				}
				else {
					long userId = GetterUtil.getLong(
						(String)contextInstance.getVariable(
							WorkflowConstants.CONTEXT_USER_ID));

					user = UserLocalServiceUtil.getUser(userId);
				}

				assignable.setActorId(Long.toString(user.getUserId()));
			}
		}

		assignable.setPooledActors(roleIds.toArray(new String[0]));
	}

	protected Collection<Map<String, String>> roles;
	protected Map<String, String> user;

}