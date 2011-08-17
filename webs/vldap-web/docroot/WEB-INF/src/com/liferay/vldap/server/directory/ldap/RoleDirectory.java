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
import com.liferay.portal.model.Role;

import java.util.LinkedHashMap;

/**
 * @author Brian Wing Shun Chan
 * @author Jonathan Potter
 */
public class RoleDirectory extends Directory {

	public RoleDirectory(String top, Company company, Role role)
		throws Exception {

		addAttribute("cn", role.getName());
		addAttribute("description", role.getDescription());
		addAttribute("objectclass", "groupOfNames");
		addAttribute("objectclass", "organizationalRole");
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", role.getName());

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("usersRoles", role.getRoleId());

		addMemberAttributes(top, company, params);

		setName(top, company, "Roles", role.getName());
	}

}