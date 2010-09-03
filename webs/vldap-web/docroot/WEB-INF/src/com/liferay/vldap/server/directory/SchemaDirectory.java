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

import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SchemaDirectory extends BaseDirectory {

	public SchemaDirectory() throws Exception {
		super("cn=schema");

		initAttributes();
	}

	protected void initAttributes() {
		addAttribute("createtimestamp", "20090818022733Z");
		addAttribute("modifytimestamp", "20090818022733Z");
		addAttribute("objectclass", "subschema");
		addAttribute("objectclass", "subentry");
		addAttribute("objectclass", "top");
	}

	protected List<Directory> initDirectories() {
		return Collections.EMPTY_LIST;
	}

}