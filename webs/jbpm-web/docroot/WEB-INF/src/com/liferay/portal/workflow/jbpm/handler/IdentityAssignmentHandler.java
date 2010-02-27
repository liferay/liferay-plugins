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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

/**
 * <a href="IdentityAssignmentHandler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class IdentityAssignmentHandler implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext)
		throws Exception {

		if (type.equals("user")) {
			if (Validator.isNull(id)) {
				User user = UserLocalServiceUtil.getUserByEmailAddress(
					GetterUtil.getLong(companyId), name);

				assignable.setActorId(String.valueOf(user.getUserId()));
			}
			else {
				assignable.setActorId(type);
			}
		}
		else if (type.equals("community")) {
			if (Validator.isNull(id)) {
				Group group = GroupLocalServiceUtil.getGroup(
					GetterUtil.getLong(companyId), name);

				id = String.valueOf(group.getGroupId());
			}

			long[] userIds = UserLocalServiceUtil.getGroupUserIds(
				GetterUtil.getLong(id));

			String[] actorIds = new String[userIds.length];

			for (int i = 0; i < userIds.length; i++) {
				actorIds[i] = String.valueOf(userIds[i]);
			}

			assignable.setPooledActors(actorIds);
		}
		else if (type.equals("role")) {
			if (Validator.isNull(id)) {
				Role role = RoleLocalServiceUtil.getRole(
					GetterUtil.getLong(companyId), name);

				id = String.valueOf(role.getRoleId());
			}

			long[] userIds = UserLocalServiceUtil.getRoleUserIds(
				GetterUtil.getLong(id));

			String[] actorIds = new String[userIds.length];

			for (int i = 0; i < userIds.length; i++) {
				actorIds[i] = String.valueOf(userIds[i]);
			}

			assignable.setPooledActors(actorIds);
		}
		else {
			assignable.setActorId(null);
			assignable.setPooledActors((String[]) null);
		}
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	protected String companyId;
	protected String id;
	protected String name;
	protected String type;

}