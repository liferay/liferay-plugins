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

package com.liferay.vldap.server.codec;

import org.apache.directory.shared.ldap.message.spi.BinaryAttributeDetector;
import org.apache.directory.shared.ldap.schema.AttributeType;
import org.apache.directory.shared.ldap.schema.LdapSyntax;
import org.apache.directory.shared.ldap.schema.SchemaManager;
import org.apache.directory.shared.ldap.util.StringTools;

/**
 * This class is copied from an anonymous class found in
 * {@link org.apache.directory.server.ldap.LdapProtocolCodecFactory}.
 *
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class DefaultBinaryAttributeDetector implements BinaryAttributeDetector {

	public DefaultBinaryAttributeDetector(SchemaManager schemaManager) {
		_schemaManager = schemaManager;
	}

	public boolean isBinary(String attributeId) {
		try {
			AttributeType attributeType =
				_schemaManager.lookupAttributeTypeRegistry(attributeId);

			LdapSyntax ldapSyntax = attributeType.getSyntax();

			return !ldapSyntax.isHumanReadable();
		}
		catch (Exception e) {
			if (StringTools.isEmpty(attributeId)) {
				return false;
			}

			return attributeId.endsWith(";binary");
		}
	}

	private SchemaManager _schemaManager;

}