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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
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
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
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

	public static JSONArray getJSONSharedToRecipients(
			long userId, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Set<User> users = new LinkedHashSet<User>();

		// Social relations

		JSONObject everyoneObject = JSONFactoryUtil.createJSONObject();

		everyoneObject.put("classNameId", _SOCIAL_RELATION_CLASS_NAME_ID);
		everyoneObject.put("id", AssetSharingEntryConstants.TYPE_EVERYONE);
		everyoneObject.put("name", "everyone");

		jsonArray.put(everyoneObject);

		JSONObject followersObject = JSONFactoryUtil.createJSONObject();

		followersObject.put("classNameId", _SOCIAL_RELATION_CLASS_NAME_ID);
		followersObject.put("id", SocialRelationConstants.TYPE_UNI_FOLLOWER);
		followersObject.put("name", "followers");

		jsonArray.put(followersObject);

		JSONObject connectorsObject = JSONFactoryUtil.createJSONObject();

		connectorsObject.put("classNameId", _SOCIAL_RELATION_CLASS_NAME_ID);
		connectorsObject.put("id", SocialRelationConstants.TYPE_BI_CONNECTION);
		connectorsObject.put("name", "connections");

		jsonArray.put(connectorsObject);

		LinkedHashMap<String, Object> socialRelationParams =
			new LinkedHashMap<String, Object>();

		socialRelationParams.put("inherit", Boolean.TRUE);

		socialRelationParams.put(
			"socialRelationType",
			new Long[] {
				userId, (long)SocialRelationConstants.TYPE_BI_CONNECTION});

		User currentUser = UserLocalServiceUtil.getUser(userId);

		List<User> connectedUsers = UserLocalServiceUtil.search(
			currentUser.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED,
			socialRelationParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new UserFirstNameComparator(true));

		users.addAll(connectedUsers);

		// Organization

		List<Organization> organizations = _getOrganizations(userId);

		if (organizations != null) {
			for (Organization organization : organizations) {
				JSONObject organizationObject =
					JSONFactoryUtil.createJSONObject();

				organizationObject.put(
					"classNameId", _ORGANIZATION_CLASS_NAME_ID);
				organizationObject.put("id", organization.getOrganizationId());
				organizationObject.put("name", organization.getName());

				jsonArray.put(organizationObject);

				LinkedHashMap<String, Object> userParams =
					new LinkedHashMap<String, Object>();

				userParams.put(
					"usersOrgs", new Long(organization.getOrganizationId()));

				List<User> orgUsers = UserLocalServiceUtil.search(
					organization.getCompanyId(), null,
					WorkflowConstants.STATUS_APPROVED, userParams,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new UserFirstNameComparator(true));

				users.addAll(orgUsers);
			}
		}

		// Site

		List<Group> groups = GroupLocalServiceUtil.getUserGroups(userId, true);

		for (Group group : groups) {
			JSONObject groupObject = JSONFactoryUtil.createJSONObject();

			groupObject.put("classNameId", _GROUP_CLASS_NAME_ID);
			groupObject.put("id", group.getGroupId());
			groupObject.put("name", group.getName());

			jsonArray.put(groupObject);

			LinkedHashMap<String, Object> userParams =
				new LinkedHashMap<String, Object>();

			userParams.put("inherit", Boolean.TRUE);
			userParams.put("usersGroups", new Long(group.getGroupId()));

			List<User> groupUsers = UserLocalServiceUtil.search(
				group.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED,
				userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new UserFirstNameComparator(true));

			users.addAll(groupUsers);
		}

		// User group

		List<UserGroup> userGroups =
			UserGroupLocalServiceUtil.getUserUserGroups(userId);

		for (UserGroup userGroup : userGroups) {
			JSONObject userGroupObject = JSONFactoryUtil.createJSONObject();

			userGroupObject.put("classNameId", _USER_GROUP_CLASS_NAME_ID);
			userGroupObject.put("id", userGroup.getUserGroupId());
			userGroupObject.put("name", userGroup.getName());

			jsonArray.put(userGroupObject);

			LinkedHashMap<String, Object> userParams =
				new LinkedHashMap<String, Object>();

			userParams.put(
				"usersUserGroups", new Long(userGroup.getUserGroupId()));

			List<User> userGroupUsers = UserLocalServiceUtil.search(
				userGroup.getCompanyId(), null,
				WorkflowConstants.STATUS_APPROVED, userParams,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new UserFirstNameComparator(true));

			users.addAll(userGroupUsers);
		}

		// Role

		Set<Role> roles = _getRoles(userId);

		for (Role role : roles) {
			JSONObject roleObject = JSONFactoryUtil.createJSONObject();

			roleObject.put("classNameId", _ROLE_CLASS_NAME_ID);
			roleObject.put("id", role.getRoleId());
			roleObject.put("name", role.getName());

			jsonArray.put(roleObject);
		}

		// Team

		List<Team> teams = TeamLocalServiceUtil.getUserTeams(userId);

		for (Team team : teams) {
			JSONObject teamObject = JSONFactoryUtil.createJSONObject();
			teamObject.put("classNameId", _TEAM_CLASS_NAME_ID);
			teamObject.put("id", team.getTeamId());
			teamObject.put("name", team.getName());

			jsonArray.put(teamObject);
		}

		// User

		for (User user : users) {
			JSONObject userObject = JSONFactoryUtil.createJSONObject();

			userObject.put("classNameId", _USER_CLASS_NAME_ID);
			userObject.put("email", user.getEmailAddress());
			userObject.put("id", user.getUserId());
			userObject.put("name", user.getFullName());
			userObject.put("portraitURL", user.getPortraitURL(themeDisplay));
			userObject.put("screenName", user.getScreenName());

			jsonArray.put(userObject);
		}

		return jsonArray;
	}

	public static LinkedHashMap<Long, long[]> getSharedToClassPKsMap(
			long userId)
		throws PortalException, SystemException {

		LinkedHashMap<Long, long[]> scopes = new LinkedHashMap<Long, long[]>();

		// Everyone, followed, and connected

		scopes.put(_SOCIAL_RELATION_CLASS_NAME_ID, new long[] {
			AssetSharingEntryConstants.TYPE_EVERYONE,
			SocialRelationConstants.TYPE_UNI_FOLLOWER,
			SocialRelationConstants.TYPE_BI_CONNECTION});

		// User

		scopes.put(_USER_CLASS_NAME_ID, new long[] {userId});

		// Organization

		List<Organization> organizations = _getOrganizations(userId);

		if (organizations != null) {
			scopes.put(
				_ORGANIZATION_CLASS_NAME_ID,
				_getOrganizationIds(organizations));
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

		Set<Role> roles = _getRoles(userId);

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

	private static List<Organization> _getOrganizations(long userId)
		throws PortalException, SystemException {

		List<Organization> userOrganizations =
			OrganizationLocalServiceUtil.getUserOrganizations(userId);

		if (userOrganizations.isEmpty()) {
			return null;
		}

		List<Organization> organizations = new ArrayList<Organization>();

		organizations.addAll(userOrganizations);

		for (Organization organization : userOrganizations) {
			List<Organization> parentOrganizations =
				OrganizationLocalServiceUtil.getParentOrganizations(
					organization.getOrganizationId());

			for (Organization parentOrganization : parentOrganizations) {
				organizations.add(parentOrganization);
			}
		}

		return organizations;
	}

	private static long[] _getRoleIds(Set<Role> roles) {
		long[] roleIds = new long[roles.size()];

		int i = 0;

		for (Role role : roles) {
			roleIds[i++] = role.getRoleId();
		}

		return roleIds;
	}

	private static Set<Role> _getRoles(long userId)
		throws PortalException, SystemException {

		Set<Role> roles = new LinkedHashSet<Role>();

		roles.addAll(RoleLocalServiceUtil.getUserRoles(userId));

		if (_PERMISSIONS_CHECK_GUEST_ENABLED) {
			User user = UserLocalServiceUtil.getUserById(userId);

			Role guestRole = RoleLocalServiceUtil.getRole(
				user.getCompanyId(), RoleConstants.GUEST);

			roles.add(guestRole);
		}

		return roles;
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