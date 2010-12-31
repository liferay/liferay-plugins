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

import com.liferay.portal.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class RoleDirectory extends BaseDirectory {

	public RoleDirectory(Role role, Directory parentDirectory)
		throws Exception {

		super("ou=" + role.getName(), parentDirectory);

		_role = role;

		initAttributes();
	}

	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", _role.getName());
	}

	protected List<Directory> initDirectories() throws Exception {
		if (!isDescendantOf("ou=Users")) {
			UsersDirectory usersDirectory = new UsersDirectory(this);

			usersDirectory.setRole(_role);

			_directories.add(usersDirectory);
		}

		return _directories;
	}

	private List<Directory> _directories = new ArrayList<Directory>();
	private Role _role;

}