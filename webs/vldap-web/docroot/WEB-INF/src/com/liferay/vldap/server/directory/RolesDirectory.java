/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class RolesDirectory extends BaseDirectory {

	public RolesDirectory(Directory parentDirectory)
		throws Exception {

		super("ou=Roles", parentDirectory);

		initAttributes();
	}

	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", "Roles");
	}

	protected List<Directory> initDirectories() throws Exception {
		List<Role> roles = Collections.EMPTY_LIST;

		if (_user != null) {
			roles = RoleLocalServiceUtil.getUserRoles(_user.getUserId());

			List<UserGroupRole> userGroupRoles = Collections.EMPTY_LIST;

			Directory parentDirectory = getParentDirectory();

			parentDirectory = parentDirectory.getParentDirectory();
			parentDirectory = parentDirectory.getParentDirectory();

			if (parentDirectory instanceof CommunityDirectory) {
				CommunityDirectory communityDirectory =
					(CommunityDirectory)parentDirectory;

				Group group = communityDirectory.getGroup();

				userGroupRoles =
					UserGroupRoleLocalServiceUtil.getUserGroupRoles(
						_user.getUserId(), group.getGroupId());
			}
			else if (parentDirectory instanceof OrganizationDirectory) {
				OrganizationDirectory organizationDirectory =
					(OrganizationDirectory)parentDirectory;

				Organization organization =
					organizationDirectory.getOrganization();

				Group group = organization.getGroup();

				userGroupRoles =
					UserGroupRoleLocalServiceUtil.getUserGroupRoles(
						_user.getUserId(), group.getGroupId());
			}

			for (UserGroupRole userGroupRole : userGroupRoles) {
				Role role = userGroupRole.getRole();

				roles.add(role);
			}
		}
		else if (_company != null) {
			roles = RoleLocalServiceUtil.search(
				_company.getCompanyId(), null, null,
				new Integer[] {RoleConstants.TYPE_REGULAR},
				0, PortletPropsValues.SEARCH_MAX_SIZE,  null);
		}

		for (Role role : roles) {
			Directory roleDirectory = new RoleDirectory(role, this);

			_directories.add(roleDirectory);
		}

		return _directories;
	}

	protected void setCompany(Company company) {
		_company = company;
	}

	protected void setUser(User user) {
		_user = user;
	}

	private Company _company;
	private List<Directory> _directories = new ArrayList<Directory>();
	private User _user;

}