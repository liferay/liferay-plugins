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