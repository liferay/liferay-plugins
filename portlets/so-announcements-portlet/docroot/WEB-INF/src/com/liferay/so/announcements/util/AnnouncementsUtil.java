/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Raymond Augé
 * @author Evan Thibodeau
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

		List<Role> roles = new ArrayList<Role>();

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
		}

		if (roles.size() > 0) {
			scopes.put(_ROLE_CLASS_NAME_ID, _getRoleIds(roles));
		}

		return scopes;
	}

	public static String getRelativeTimeDescription(
		Date date, Locale locale, TimeZone timeZone) {

		long milliseconds = date.getTime();

		Format timeFormat = FastDateFormatFactoryUtil.getTime(locale, timeZone);

		int daysBetween = DateUtil.getDaysBetween(
			new Date(milliseconds), new Date(), timeZone);

		long millisAgo = System.currentTimeMillis() - milliseconds;

		if (millisAgo <= Time.MINUTE) {
			return LanguageUtil.get(locale, "about-a-minute-ago");
		}
		else if (millisAgo < Time.HOUR) {
			return LanguageUtil.format(
				locale, "x-minutes-ago", (millisAgo / Time.MINUTE));
		}
		else if ((millisAgo / Time.HOUR) == 1) {
			return LanguageUtil.get(locale, "about-an-hour-ago");
		}
		else if ((millisAgo < Time.DAY) || (daysBetween == 0)) {
			return LanguageUtil.format(
				locale, "x-hours-ago", (millisAgo / Time.HOUR));
		}
		else if (daysBetween == 1) {
			return LanguageUtil.format(
				locale, "yesterday-at-x", timeFormat.format(milliseconds));
		}

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"EEEE, MMMMM dd, yyyy", locale, timeZone);

		return dateFormat.format(milliseconds);
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

	private static final long _ROLE_CLASS_NAME_ID = PortalUtil.getClassNameId(
		Role.class.getName());

	private static final long _USER_CLASS_NAME_ID = PortalUtil.getClassNameId(
		User.class.getName());

	private static final long _USER_GROUP_CLASS_NAME_ID =
		PortalUtil.getClassNameId(UserGroup.class.getName());

}