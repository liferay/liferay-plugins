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

import com.liferay.portal.model.Group;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CommunityDirectory extends BaseDirectory {

	public CommunityDirectory(
			Group group, Directory parentDirectory, Directory usersDirectory)
		throws Exception {

		super("ou=" + group.getName(), parentDirectory);

		_group = group;
		_usersDirectory = usersDirectory;

		initAttributes();
	}

	protected Group getGroup() {
		return _group;
	}

	@Override
	protected void initAttributes() {
		addAttribute("cn", _group.getName());
		addAttribute("description", _group.getDescription());
		addAttribute("objectClass", "groupOfNames");
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", _group.getName());
	}

	@Override
	protected List<Directory> initDirectories() throws Exception {
		UserDirectoryBuilder.buildUserDirectories(
			_group, this, _usersDirectory);

		return _directories;
	}

	private Group _group;
	private Directory _usersDirectory;

}