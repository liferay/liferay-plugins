/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.directory;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class UserDirectoryBuilder {

	public static void buildUserDirectories(
			Company company, Directory currentDirectory,
			Directory usersDirectory)
		throws Exception {

		_buildUserDirectories(
			company, null, null, null, null, currentDirectory, usersDirectory);
	}

	public static void buildUserDirectories(
			Group group, Directory currentDirectory, Directory usersDirectory)
		throws Exception {

		_buildUserDirectories(
			null, group, null, null, null, currentDirectory, usersDirectory);
	}

	public static void buildUserDirectories(
			Organization organization, Directory currentDirectory,
			Directory usersDirectory)
		throws Exception {

		_buildUserDirectories(
			null, null, organization, null, null, currentDirectory,
			usersDirectory);
	}

	public static void buildUserDirectories(
			Role role, Directory currentDirectory, Directory usersDirectory)
		throws Exception {

		_buildUserDirectories(
			null, null, null, role, null, currentDirectory, usersDirectory);
	}

	public static void buildUserDirectories(
			UserGroup userGroup, Directory currentDirectory,
			Directory usersDirectory)
		throws Exception {

		_buildUserDirectories(
			null, null, null, null, userGroup, currentDirectory,
			usersDirectory);
	}

	private static void _buildUserDirectories(
			Company company, Group group, Organization organization,
			Role role, UserGroup userGroup, Directory currentDirectory,
			Directory usersDirectory)
		throws Exception {

		long companyId = 0;
		String firstName = null;
		String middleName = null;
		String lastName = null;
		String screenName = null;
		String emailAddress = null;
		int status = WorkflowConstants.STATUS_APPROVED;
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();
		boolean andSearch = true;
		int start = 0;
		int end = PortletPropsValues.SEARCH_MAX_SIZE;
		OrderByComparator orderByComparator = new UserScreenNameComparator();

		if (group != null) {
			companyId = group.getCompanyId();

			params.put("usersGroups", group.getGroupId());
		}
		else if (organization != null) {
			companyId = organization.getCompanyId();

			params.put("usersOrgs", organization.getOrganizationId());
		}
		else if (role != null) {
			companyId = role.getCompanyId();

			params.put("usersRoles", role.getRoleId());
		}
		else if (userGroup != null) {
			companyId = userGroup.getCompanyId();

			params.put("usersUserGroups", userGroup.getUserGroupId());
		}
		else if (company != null) {
			companyId = company.getCompanyId();
		}

		List<User> users = UserLocalServiceUtil.search(
			companyId, firstName, middleName, lastName, screenName,
			emailAddress, status, params, andSearch, start, end,
			orderByComparator);

		List<Directory> directories = currentDirectory.getDirectories();

		for (User user : users) {
			if (user.isDefaultUser()) {
				continue;
			}

			Directory userDirectory = new UserDirectory(user, usersDirectory);

			currentDirectory.addAttribute(
				"member", userDirectory.getName().getName());

			directories.add(userDirectory);
		}
	}

}