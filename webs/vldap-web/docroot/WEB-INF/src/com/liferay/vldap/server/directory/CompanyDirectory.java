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

import com.liferay.portal.model.Company;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CompanyDirectory extends BaseDirectory {

	public CompanyDirectory(Company company, Directory parentDirectory)
		throws Exception {

		super("ou=" + company.getWebId(), parentDirectory);

		_company = company;

		initAttributes();
	}

	@Override
	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", _company.getWebId());
	}

	@Override
	protected List<Directory> initDirectories() throws Exception {
		UsersDirectory usersDirectory = new UsersDirectory(this);

		usersDirectory.setCompany(_company);

		_directories.add(usersDirectory);

		CommunitiesDirectory communitiesDirectory = new CommunitiesDirectory(
			this, usersDirectory);

		communitiesDirectory.setCompany(_company);

		_directories.add(communitiesDirectory);

		OrganizationsDirectory organizationsDirectory =
			new OrganizationsDirectory(this, usersDirectory);

		organizationsDirectory.setCompany(_company);

		_directories.add(organizationsDirectory);

		RolesDirectory rolesDirectory = new RolesDirectory(
			this, usersDirectory);

		rolesDirectory.setCompany(_company);

		_directories.add(rolesDirectory);

		UserGroupsDirectory userGroupsDirectory = new UserGroupsDirectory(
			this, usersDirectory);

		userGroupsDirectory.setCompany(_company);

		_directories.add(userGroupsDirectory);

		return _directories;
	}

	private Company _company;

}