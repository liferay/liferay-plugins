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

import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.shared.ldap.entry.DefaultServerEntry;
import org.apache.directory.shared.ldap.entry.Entry;
import org.apache.directory.shared.ldap.entry.ServerEntry;
import org.apache.directory.shared.ldap.name.DN;
import org.apache.directory.shared.ldap.schema.AttributeType;
import org.apache.directory.shared.ldap.schema.SchemaManager;
import org.apache.directory.shared.ldap.schema.registries.AttributeTypeRegistry;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class Directory {

	public Directory(DN name) {
		_name = name;
	}

	public Directory(String name) throws Exception {
		_name = new DN(name);
	}

	public Directory findBase(DN name) {
		return findBase(name, this);
	}

	public void addAttribute(String attributeId, String value) {
		Attribute attribute = new Attribute();

		attribute.setAttributeId(attributeId);
		attribute.setValue(value);

		_attributes.add(attribute);
	}

	public void addDirectory(Directory directory) {
		_directories.add(directory);
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

	public List<Directory> getDirectories() {
		return _directories;
	}

	public DN getName() {
		return _name;
	}

	public Entry toEntry(
			SchemaManager schemaManager, List<String> requestAttributes)
		throws Exception {

		ServerEntry serverEntry = new DefaultServerEntry();

		serverEntry.setDn(_name);

		boolean wildcardAttributes = requestAttributes.contains(
			StringPool.STAR);

		AttributeTypeRegistry attributeTypeRegistry =
			schemaManager.getAttributeTypeRegistry();

		for (Attribute attribute : _attributes) {
			if (!wildcardAttributes &&
				!requestAttributes.contains(attribute.getAttributeId())) {

				continue;
			}

			AttributeType attributeType = attribute.getAttributeType(
				attributeTypeRegistry);
			String value = attribute.getValue();

			serverEntry.add(attributeType, value);
		}

		if (requestAttributes.contains("hassubordinates")) {
			Attribute subordinatesAttribute = new Attribute();

			subordinatesAttribute.setAttributeId("hassubordinates");
			subordinatesAttribute.setValue("true");

			AttributeType attributeType =
				subordinatesAttribute.getAttributeType(attributeTypeRegistry);
			String value = subordinatesAttribute.getValue();

			serverEntry.add(attributeType, value);
		}

		return serverEntry;
	}

	protected Directory findBase(DN name, Directory directory) {
		if (name.equals(directory.getName())) {
			return directory;
		}

		for (Directory curDirectory : directory.getDirectories()) {
			Directory resultDirectory = findBase(name, curDirectory);

			if (resultDirectory != null) {
				return resultDirectory;
			}
		}

		return null;
	}

	private List<Attribute> _attributes = new ArrayList<Attribute>();
	private List<Directory> _directories = new ArrayList<Directory>();
	private DN _name;

}