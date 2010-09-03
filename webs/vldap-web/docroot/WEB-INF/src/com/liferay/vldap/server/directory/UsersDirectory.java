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
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UsersDirectory extends BaseDirectory {

	public UsersDirectory(Directory parentDirectory)
		throws Exception {

		super("ou=Users", parentDirectory);

		initAttributes();
	}

	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", "Users");
	}

	protected List<Directory> initDirectories() throws Exception {
		List<User> users = Collections.EMPTY_LIST;

		if (_group != null) {
			users = UserLocalServiceUtil.getGroupUsers(_group.getGroupId());
		}
		else if (_organization != null) {
			users = UserLocalServiceUtil.getOrganizationUsers(
				_organization.getOrganizationId());
		}
		else if (_role != null) {
			users = UserLocalServiceUtil.getRoleUsers(_role.getRoleId());
		}
		else if (_company != null) {
			users = UserLocalServiceUtil.getCompanyUsers(
				_company.getCompanyId(), 0, PortletPropsValues.SEARCH_MAX_SIZE);
		}

		for (User user : users) {
			if (user.isDefaultUser()) {
				continue;
			}

			Directory userDirectory = new UserDirectory(user, this);

			_directories.add(userDirectory);
		}

		return _directories;
	}

	protected void setCompany(Company company) {
		_company = company;
	}

	protected void setGroup(Group group) {
		_group = group;
	}

	protected void setOrganization(Organization organization) {
		_organization = organization;
	}

	protected void setRole(Role role) {
		_role = role;
	}

	private Company _company;
	private List<Directory> _directories = new ArrayList<Directory>();
	private Group _group;
	private Organization _organization;
	private Role _role;

}