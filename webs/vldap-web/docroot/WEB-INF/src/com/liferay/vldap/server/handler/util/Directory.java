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

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.shared.ldap.name.DN;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class Directory {

	public Directory findBase(DN name) {
		return null;
	}

	public Attribute getAttribute(String attributeId, String value) {
		for (Attribute attribute : _attributes) {
			if (attributeId.equalsIgnoreCase(attribute.getAttributeId()) &&
				value.equalsIgnoreCase(attribute.getValue())) {

				return attribute;
			}
		}

		return null;
	}

	public Attribute getAttribute(String attributeId) {
		for (Attribute attribute : _attributes) {
			if (attributeId.equalsIgnoreCase(attribute.getAttributeId())) {
				return attribute;
			}
		}

		return null;
	}

	private List<Attribute> _attributes = new ArrayList<Attribute>();

}