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

package com.liferay.vldap.server.handler.util;

import org.apache.directory.shared.ldap.schema.AttributeType;
import org.apache.directory.shared.ldap.schema.LdapSyntax;
import org.apache.directory.shared.ldap.schema.SyntaxChecker;
import org.apache.directory.shared.ldap.schema.registries.AttributeTypeRegistry;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class Attribute {

	public String getAttributeId() {
		return _attributeId;
	}

	public AttributeType getAttributeType(
			AttributeTypeRegistry attributeTypeRegistry)
		throws Exception {

		if (!attributeTypeRegistry.contains(_attributeId)) {
			return null;
		}

		return attributeTypeRegistry.lookup(_attributeId);
	}

	public AttributeType getAttributeType(SyntaxChecker syntaxChecker) {
		AttributeType attributeType = new AttributeType(_oid);

		attributeType.addName(_attributeId);

		LdapSyntax ldapSyntax = new LdapSyntax(_syntaxOid);

		ldapSyntax.setSyntaxChecker(syntaxChecker);

		attributeType.setSyntax(ldapSyntax);

		return attributeType;
	}

	public String getValue() {
		return _value;
	}

	public void setAttributeId(String attributeId) {
		_attributeId = attributeId;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _attributeId;
	private String _oid;
	private String _syntaxOid;
	private String _value;

}