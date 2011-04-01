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

import com.liferay.portal.model.UserGroup;
import com.liferay.vldap.util.UserCollection;

import java.util.List;

/**
 * @author Raymond Augé
 */
public class UserGroupDirectory extends BaseDirectory {

	public UserGroupDirectory(
			UserGroup userGroup, Directory parentDirectory,
			Directory usersDirectory)
		throws Exception {

		super("ou=" + userGroup.getName(), parentDirectory);

		_userGroup = userGroup;
		_usersDirectory = usersDirectory;

		initAttributes();
	}

	protected void initAttributes() {
		addAttribute("cn", _userGroup.getName());
		addAttribute("description", _userGroup.getDescription());
		addAttribute("objectClass", "groupOfNames");
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", _userGroup.getName());
	}

	protected List<Directory> initDirectories() throws Exception {
		UserCollection.collectUsers(_userGroup, this, _usersDirectory);

		return _directories;
	}

	private UserGroup _userGroup;
	private Directory _usersDirectory;

}