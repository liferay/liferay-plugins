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

import java.util.ArrayList;
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

	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", _company.getWebId());
	}

	protected List<Directory> initDirectories() throws Exception {
		CommunitiesDirectory communitiesDirectory = new CommunitiesDirectory(
			this);

		communitiesDirectory.setCompany(_company);

		_directories.add(communitiesDirectory);

		OrganizationsDirectory organizationsDirectory =
			new OrganizationsDirectory(this);

		organizationsDirectory.setCompany(_company);

		_directories.add(organizationsDirectory);

		RolesDirectory rolesDirectory = new RolesDirectory(this);

		rolesDirectory.setCompany(_company);

		_directories.add(rolesDirectory);

		UsersDirectory usersDirectory = new UsersDirectory(this);

		usersDirectory.setCompany(_company);

		_directories.add(usersDirectory);

		return _directories;
	}

	private Company _company;
	private List<Directory> _directories = new ArrayList<Directory>();

}