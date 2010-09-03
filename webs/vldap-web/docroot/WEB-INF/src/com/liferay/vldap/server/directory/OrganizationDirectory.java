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

import com.liferay.portal.model.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class OrganizationDirectory extends BaseDirectory {

	public OrganizationDirectory(
			Organization organization, Directory parentDirectory)
		throws Exception {

		super("ou=" + organization.getName(), parentDirectory);

		_organization = organization;

		initAttributes();
	}

	protected Organization getOrganization() {
		return _organization;
	}

	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", _organization.getName());
	}

	protected List<Directory> initDirectories() throws Exception {
		if (!isDescendantOf("ou=Users")) {
			UsersDirectory usersDirectory = new UsersDirectory(this);

			usersDirectory.setOrganization(_organization);

			_directories.add(usersDirectory);
		}

		return _directories;
	}

	private List<Directory> _directories = new ArrayList<Directory>();
	private Organization _organization;

}