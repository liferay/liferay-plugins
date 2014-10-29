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

package com.liferay.asset.sharing.util;

import com.liferay.asset.sharing.model.AssetSharingEntryConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialRelation;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Sherry Yang
 */
public class AssetSharingUtil {

	public static LinkedHashMap<Long, long[]> getSharedToClassPKsMap(
			long userId)
		throws PortalException {

		LinkedHashMap<Long, long[]> scopes = new LinkedHashMap<Long, long[]>();

		// Everyone, followed, and connected

		scopes.put(_SOCIAL_RELATION_CLASS_NAME_ID, new long[] {
			AssetSharingEntryConstants.TYPE_EVERYONE,
			SocialRelationConstants.TYPE_UNI_FOLLOWER,
			SocialRelationConstants.TYPE_BI_CONNECTION});

		// User

		scopes.put(_USER_CLASS_NAME_ID, new long[] {userId});

		// Organization

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getUserOrganizations(userId);

		if (!organizations.isEmpty()) {
			List<Organization> organizationsList =
				new ArrayList<Organization>();

			organizationsList.addAll(organizations);

			for (Organization organization : organizations) {
				List<Organization> parentOrganizations =
					OrganizationLocalServiceUtil.getParentOrganizations(
						organization.getOrganizationId());

				for (Organization parentOrganization : parentOrganizations) {
					organizationsList.add(parentOrganization);
				}
			}

			scopes.put(
				_ORGANIZATION_CLASS_NAME_ID,
				_getOrganizationIds(organizationsList));
		}

		// Site

		List<Group> groups = GroupLocalServiceUtil.getUserGroups(userId, true);

		if (!groups.isEmpty()) {
			scopes.put(_GROUP_CLASS_NAME_ID, _getGroupIds(groups));
		}

		// User group

		List<UserGroup> userGroups =
			UserGroupLocalServiceUtil.getUserUserGroups(userId);

		if (!userGroups.isEmpty()) {
			scopes.put(_USER_GROUP_CLASS_NAME_ID, _getUserGroupIds(userGroups));
		}

		// Role

		Set<Role> roles = new LinkedHashSet<Role>();

		roles.addAll(RoleLocalServiceUtil.getUserRoles(userId));

		if (_PERMISSIONS_CHECK_GUEST_ENABLED) {
			User user = UserLocalServiceUtil.getUserById(userId);

			Role guestRole = RoleLocalServiceUtil.getRole(
				user.getCompanyId(), RoleConstants.GUEST);

			roles.add(guestRole);
		}

		if (!roles.isEmpty()) {
			scopes.put(_ROLE_CLASS_NAME_ID, _getRoleIds(roles));
		}

		// Team

		List<Team> teams = TeamLocalServiceUtil.getUserTeams(userId);

		if (!teams.isEmpty()) {
			scopes.put(_TEAM_CLASS_NAME_ID, _getTeamIds(teams));
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

	private static long[] _getRoleIds(Set<Role> roles) {
		long[] roleIds = new long[roles.size()];

		int i = 0;

		for (Role role : roles) {
			roleIds[i++] = role.getRoleId();
		}

		return roleIds;
	}

	private static long[] _getTeamIds(List<Team> teams) {
		long[] teamIds = new long[teams.size()];

		int i = 0;

		for (Team team : teams) {
			teamIds[i++] = team.getTeamId();
		}

		return teamIds;
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

	private static final long _SOCIAL_RELATION_CLASS_NAME_ID =
		PortalUtil.getClassNameId(SocialRelation.class.getName());

	private static final long _TEAM_CLASS_NAME_ID = PortalUtil.getClassNameId(
		Team.class.getName());

	private static final long _USER_CLASS_NAME_ID = PortalUtil.getClassNameId(
		User.class.getName());

	private static final long _USER_GROUP_CLASS_NAME_ID =
		PortalUtil.getClassNameId(UserGroup.class.getName());

}