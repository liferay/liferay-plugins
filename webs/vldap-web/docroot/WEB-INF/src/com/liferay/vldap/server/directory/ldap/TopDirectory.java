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

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class TopDirectory extends Directory {

	public TopDirectory(String top) {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("o", "Liferay");

		_name = "o=".concat(escape(top));
	}

	@Override
	public String getName() {
		return _name;
	}

	private String _name;

}