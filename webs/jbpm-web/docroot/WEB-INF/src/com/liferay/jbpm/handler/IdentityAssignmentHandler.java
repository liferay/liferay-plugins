/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.jbpm.handler;

import com.liferay.client.portal.model.GroupSoap;
import com.liferay.client.portal.model.RoleSoap;
import com.liferay.client.portal.model.UserSoap;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

/**
 * <a href="IdentityAssignmentHandler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class IdentityAssignmentHandler
	extends DefaultHandler implements AssignmentHandler {

	public void assign(
		Assignable assignable, ExecutionContext executionContext) {

		try {
			if (type.equals("user")) {
				if (Validator.isNotNull(id)) {
					assignable.setActorId(id);
				}
				else {
					UserSoap user = getUserService().getUserByEmailAddress(
						GetterUtil.getLong(companyId), name);

					assignable.setActorId(String.valueOf(user.getUserId()));
				}
			}
			else if (type.equals("community")) {
				if (Validator.isNull(id)) {
					GroupSoap group = getGroupService().getGroup(
						GetterUtil.getLong(companyId), name);

					id = String.valueOf(group.getGroupId());
				}

				UserSoap[] users = getUserService().getGroupUsers(
					GetterUtil.getLong(id));

				String[] actorIds = new String[users.length];

				for (int i = 0; i < users.length; i++) {
					actorIds[i] = String.valueOf(users[i].getUserId());
				}

				assignable.setPooledActors(actorIds);
			}
			else if (type.equals("role")) {
				if (Validator.isNull(id)) {
					RoleSoap role = getRoleService().getRole(
						GetterUtil.getLong(companyId), name);

					id = String.valueOf(role.getRoleId());
				}

				UserSoap[] users = getUserService().getRoleUsers(
					GetterUtil.getLong(id));

				String[] actorIds = new String[users.length];

				for (int i = 0; i < users.length; i++) {
					actorIds[i] = String.valueOf(users[i].getUserId());
				}

				assignable.setPooledActors(actorIds);
			}
		}
		catch (Exception e) {
			assignable.setPooledActors(null);
		}
	}

	protected String type;
	protected String companyId;
	protected String id;
	protected String name;

}