/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.announcements.util;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.service.permission.RolePermissionUtil;
import com.liferay.portal.kernel.service.permission.UserGroupPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.List;

/**
 * @author Lin Cui
 */
public class SOAnnouncementsUtil {

	public static boolean hasGroups(ThemeDisplay themeDisplay)
		throws Exception {

		List<Group> groups = GroupLocalServiceUtil.getUserGroups(
			themeDisplay.getUserId(), true);

		if (!groups.isEmpty()) {
			for (Group group : groups) {
				if (((group.isOrganization() && group.isSite()) ||
					 group.isRegularSite()) &&
					GroupPermissionUtil.contains(
						themeDisplay.getPermissionChecker(), group.getGroupId(),
						ActionKeys.MANAGE_ANNOUNCEMENTS)) {

					return true;
				}
			}
		}

		return false;
	}

	public static boolean hasOrganizations(ThemeDisplay themeDisplay)
		throws Exception {

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getUserOrganizations(
				themeDisplay.getUserId());

		for (Organization organization : organizations) {
			if (OrganizationPermissionUtil.contains(
					themeDisplay.getPermissionChecker(),
					organization.getOrganizationId(),
					ActionKeys.MANAGE_ANNOUNCEMENTS)) {

				return true;
			}
		}

		return false;
	}

	public static boolean hasRoles(ThemeDisplay themeDisplay) throws Exception {
		List<Role> roles = RoleLocalServiceUtil.getRoles(
			themeDisplay.getCompanyId());

		for (Role role : roles) {
			if (role.isTeam()) {
				Team team = TeamLocalServiceUtil.getTeam(role.getClassPK());

				if (GroupPermissionUtil.contains(
						themeDisplay.getPermissionChecker(), team.getGroupId(),
						ActionKeys.MANAGE_ANNOUNCEMENTS)) {

					return true;
				}
			}
			else if (RolePermissionUtil.contains(
						themeDisplay.getPermissionChecker(), role.getRoleId(),
						ActionKeys.MANAGE_ANNOUNCEMENTS)) {

				return true;
			}
		}

		return false;
	}

	public static boolean hasUserGroups(ThemeDisplay themeDisplay)
		throws Exception {

		List<UserGroup> userGroups = UserGroupLocalServiceUtil.getUserGroups(
			themeDisplay.getCompanyId());

		for (UserGroup userGroup : userGroups) {
			if (UserGroupPermissionUtil.contains(
					themeDisplay.getPermissionChecker(),
					userGroup.getUserGroupId(),
					ActionKeys.MANAGE_ANNOUNCEMENTS)) {

				return true;
			}
		}

		return false;
	}

}