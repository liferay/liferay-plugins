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

package com.liferay.scriptingexecutor.scripts.groovy;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
class GroovyUser {

	GroovyUser(
		String emailAddress_, String password_, String firstName_,
		String lastName_, String jobTitle_) {

		emailAddress = emailAddress_;
		password = password_;
		firstName = firstName_;
		lastName = lastName_;
		jobTitle = jobTitle_;
	}

	void addRoles(
		GroovyScriptingContext scriptingContext, String... roleNames) {

		List<Role> roles = new ArrayList<Role>(roleNames.length);

		for (String roleName : roleNames) {
			Role role = RoleLocalServiceUtil.fetchRole(
				scriptingContext.companyId, roleName);

			roles.add(role);
		}

		RoleLocalServiceUtil.addUserRoles(user.getUserId(), roles);
	}

	void create(GroovyScriptingContext scriptingContext) {
		user = UserLocalServiceUtil.fetchUserByEmailAddress(
			scriptingContext.companyId, emailAddress);

		if (user != null) {
			return;
		}

		user = UserLocalServiceUtil.addUser(
			scriptingContext.defaultUserId, scriptingContext.companyId, false,
			password, password, true, null, emailAddress, 0, null,
			LocaleUtil.getDefault(), firstName, null, lastName, -1, -1, true, 1,
			1, 1977, jobTitle, new long[0], new long[0], new long[0],
			new long[0], false, scriptingContext.serviceContext);
	}

	void joinOrganizations(
		GroovyScriptingContext scriptingContext, String... organizationNames) {

		for (String organizationName : organizationNames) {
			Organization organization = GroovyOrganization.fetchOrganization(
					scriptingContext, organizationName);

			if (organization != null) {
				UserLocalServiceUtil.addOrganizationUser(
					organization.getOrganizationId(), user.getUserId());
			}
		}
	}

	void joinSites(
		GroovyScriptingContext liferayScriptingContext, String... siteNames) {

		for (String siteName : siteNames) {
			Group group = GroupLocalServiceUtil.fetchGroup(
				liferayScriptingContext.companyId, siteName);

			UserLocalServiceUtil.addGroupUser(
				group.getGroupId(), user.getUserId());
		}
	}

	String emailAddress;
	String firstName;
	String jobTitle;
	String lastName;
	String password;
	User user;

}