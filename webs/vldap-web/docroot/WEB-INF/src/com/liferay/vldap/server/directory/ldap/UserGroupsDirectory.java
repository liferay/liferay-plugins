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

package com.liferay.vldap.server.directory.ldap;

import com.liferay.portal.model.Company;

/**
 * @author Raymond Augé
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class UserGroupsDirectory extends Directory {

	public UserGroupsDirectory(String top, Company company) {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", "UserGroups");

		setName(top, company, "User Groups");
	}

}