/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.announcements.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class AnnouncementsUtil {

	public static LinkedHashMap<Long, long[]> getAnnouncementScopes(long userId)
		throws PortalException, SystemException {

		LinkedHashMap<Long, long[]> scopes = new LinkedHashMap<Long, long[]>();

		// General announcements

		scopes.put(new Long(0), new long[] {0});

		// Personal announcements

		scopes.put(_USER_CLASS_NAME_ID, new long[] {userId});

		// Organization announcements

		List<Group> groupsList = new ArrayList<Group>();

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getUserOrganizations(userId);

		if (!organizations.isEmpty()) {
			List<Organization> organizationsList =
				new ArrayList<Organization>();

			organizationsList.addAll(organizations);

			for (Organization organization : organizations) {
				groupsList.add(organization.getGroup());

				List<Organization> parentOrganizations =
					OrganizationLocalServiceUtil.getParentOrganizations(
						organization.getOrganizationId());

				for (Organization parentOrganization : parentOrganizations) {
					organizationsList.add(parentOrganization);
					groupsList.add(parentOrganization.getGroup());
				}
			}

			scopes.put(
				_ORGANIZATION_CLASS_NAME_ID,
				_getOrganizationIds(organizationsList));
		}

		// Site announcements

		List<Group> groups = GroupLocalServiceUtil.getUserGroups(userId, true);

		if (!groups.isEmpty()) {
			scopes.put(_GROUP_CLASS_NAME_ID, _getGroupIds(groups));

			groupsList.addAll(groups);
		}

		// User group announcements

		List<UserGroup> userGroups =
			UserGroupLocalServiceUtil.getUserUserGroups(userId);

		if (!userGroups.isEmpty()) {
			scopes.put(_USER_GROUP_CLASS_NAME_ID, _getUserGroupIds(userGroups));

			for (UserGroup userGroup : userGroups) {
				groupsList.add(userGroup.getGroup());
			}
		}

		// Role announcements

		List<Role> roles = new UniqueList<Role>();

		if (!groupsList.isEmpty()) {
			roles = RoleLocalServiceUtil.getUserRelatedRoles(
				userId, groupsList);

			roles = ListUtil.copy(roles);

			for (Group group : groupsList) {
				roles.addAll(
					RoleLocalServiceUtil.getUserGroupRoles(
						userId, group.getGroupId()));
				roles.addAll(
					RoleLocalServiceUtil.getUserGroupGroupRoles(
						userId, group.getGroupId()));
			}
		}
		else {
			roles = RoleLocalServiceUtil.getUserRoles(userId);

			roles = ListUtil.copy(roles);
		}

		if (_PERMISSIONS_CHECK_GUEST_ENABLED) {
			User user = UserLocalServiceUtil.getUserById(userId);

			Role guestRole = RoleLocalServiceUtil.getRole(
				user.getCompanyId(), RoleConstants.GUEST);

			roles.add(guestRole);
		}

		if (roles.size() > 0) {
			scopes.put(_ROLE_CLASS_NAME_ID, _getRoleIds(roles));
		}

		return scopes;
	}

	private static long[] _getGroupIds(List<Group> groups) {
		long[] groupIds = new long[groups.size()];

		int i = 0;

		for (Group group : groups) {
			groupIds[i++] = group.getGroupId();
		}

		return groupIds;
	}

	private static long[] _getOrganizationIds(
		List<Organization> organizations) {

		long[] organizationIds = new long[organizations.size()];

		int i = 0;

		for (Organization organization : organizations) {
			organizationIds[i++] = organization.getOrganizationId();
		}

		return organizationIds;
	}

	private static long[] _getRoleIds(List<Role> roles) {
		long[] roleIds = new long[roles.size()];

		int i = 0;

		for (Role role : roles) {
			roleIds[i++] = role.getRoleId();
		}

		return roleIds;
	}

	private static long[] _getUserGroupIds(List<UserGroup> userGroups) {
		long[] userGroupIds = new long[userGroups.size()];

		int i = 0;

		for (UserGroup userGroup : userGroups) {
			userGroupIds[i++] = userGroup.getUserGroupId();
		}

		return userGroupIds;
	}

	private static final long _GROUP_CLASS_NAME_ID = PortalUtil.getClassNameId(
		Group.class.getName());

	private static final long _ORGANIZATION_CLASS_NAME_ID =
		PortalUtil.getClassNameId(Organization.class.getName());

	private static final boolean _PERMISSIONS_CHECK_GUEST_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.PERMISSIONS_CHECK_GUEST_ENABLED));

	private static final long _ROLE_CLASS_NAME_ID = PortalUtil.getClassNameId(
		Role.class.getName());

	private static final long _USER_CLASS_NAME_ID = PortalUtil.getClassNameId(
		User.class.getName());

	private static final long _USER_GROUP_CLASS_NAME_ID =
		PortalUtil.getClassNameId(UserGroup.class.getName());

}