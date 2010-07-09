/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.sites.portlet;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Ryan Park
 *
 */
public class SitesPortlet extends MVCPortlet {

	public void updateRoles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userId = ParamUtil.getLong(actionRequest, "userId");
		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		long roleId = ParamUtil.getLong(actionRequest, "roleId");

		List<UserGroupRole> userGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRoles(userId, groupId);

		Iterator<UserGroupRole> itr = userGroupRoles.iterator();

		while (itr.hasNext()) {
			UserGroupRole userGroupRole = itr.next();

			Role role = userGroupRole.getRole();

			if ((role.getType() != RoleConstants.TYPE_COMMUNITY) ||
				(role.getName().equals(RoleConstants.COMMUNITY_MEMBER))) {

				itr.remove();
			}
		}

		long[] deleteRoleIds = new long[userGroupRoles.size()];

		for (int i = 0; i < userGroupRoles.size(); i++) {
			UserGroupRole userGroupRole = userGroupRoles.get(i);

			deleteRoleIds[i] = userGroupRole.getRoleId();
		}

		UserGroupRoleServiceUtil.deleteUserGroupRoles(
			userId, groupId, deleteRoleIds);

		if (roleId > 0) {
			UserGroupRoleServiceUtil.addUserGroupRoles(
				userId, groupId, new long[] {roleId});
		}
	}

}