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

import java.util.List;

import org.apache.directory.shared.ldap.entry.Entry;
import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.directory.shared.ldap.name.DN;
import org.apache.directory.shared.ldap.schema.SchemaManager;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public interface Directory {

	public void addAttribute(String attributeId, String value);

	public Directory findBase(DN name) throws Exception;

	public Attribute getAttribute(String attributeId);

	public Attribute getAttribute(String attributeId, String value);

	public List<Directory> getDirectories() throws Exception;

	public InternalRequest getInternalRequest();

	public Directory getParentDirectory();

	public DN getName();

	public Entry toEntry(
			SchemaManager schemaManager, List<String> requestAttributes)
		throws Exception;

}