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

import com.liferay.portal.model.Organization;
import com.liferay.vldap.util.UserCollection;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class OrganizationDirectory extends BaseDirectory {

	public OrganizationDirectory(
			Organization organization, Directory parentDirectory,
			Directory usersDirectory)
		throws Exception {

		super("ou=" + organization.getName(), parentDirectory);

		_organization = organization;
		_usersDirectory = usersDirectory;

		initAttributes();
	}

	protected Organization getOrganization() {
		return _organization;
	}

	protected void initAttributes() {
		addAttribute("cn", _organization.getName());
		addAttribute("objectClass", "groupOfNames");
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", _organization.getName());
	}

	protected List<Directory> initDirectories() throws Exception {
		UserCollection.collectUsers(_organization, this, _usersDirectory);

		return _directories;
	}

	private Organization _organization;
	private Directory _usersDirectory;

}