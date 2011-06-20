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

package com.liferay.vldap.server.directory.builder;

import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.SearchBase;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.server.directory.ldap.TopDirectory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class TopBuilder extends DirectoryBuilder {

	@Override
	protected List<Directory> buildDirectories(
		SearchBase searchBase, List<FilterConstraint> filterConstraints) {

		List<Directory> directories = new ArrayList<Directory>();

		Directory directory = new TopDirectory("Liferay");

		directories.add(directory);

		return directories;
	}

}