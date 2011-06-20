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
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.UserGroupLocalServiceUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class UserGroupsDirectory extends BaseDirectory {

	public UserGroupsDirectory(
			Directory parentDirectory, Directory usersDirectory)
		throws Exception {

		super("ou=User Groups", parentDirectory);

		_usersDirectory = usersDirectory;

		initAttributes();
	}

	@Override
	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", "UserGroups");
	}

	@Override
	protected List<Directory> initDirectories() throws Exception {
		List<UserGroup> userGroups = Collections.emptyList();

		if (_company != null) {
			userGroups = UserGroupLocalServiceUtil.getUserGroups(
				_company.getCompanyId());
		}

		for (UserGroup userGroup : userGroups) {
			Directory userGroupDirectory = new UserGroupDirectory(
				userGroup, this, _usersDirectory);

			_directories.add(userGroupDirectory);
		}

		return _directories;
	}

	protected void setCompany(Company company) {
		_company = company;
	}

	private Company _company;
	private Directory _usersDirectory;

}