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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.shared.ldap.entry.DefaultServerEntry;
import org.apache.directory.shared.ldap.entry.Entry;
import org.apache.directory.shared.ldap.entry.ServerEntry;
import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.directory.shared.ldap.name.DN;
import org.apache.directory.shared.ldap.schema.AttributeType;
import org.apache.directory.shared.ldap.schema.SchemaManager;
import org.apache.directory.shared.ldap.schema.registries.AttributeTypeRegistry;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public abstract class BaseDirectory implements Directory {

	public BaseDirectory(String name) throws Exception {
		this(name, null);
	}

	public BaseDirectory(String name, Directory parentDirectory)
		throws Exception {

		name = escape(name.trim());

		if (parentDirectory != null) {
			_parentDirectory = parentDirectory;

			name += StringPool.COMMA + parentDirectory.getName();
		}

		try {
			_name = new DN(name);
		}
		catch (Exception e) {
			_log.error("Invalid name " + name);

			throw e;
		}
	}

	public void addAttribute(String attributeId, String value) {
		Attribute attribute = new Attribute();

		attribute.setAttributeId(attributeId);
		attribute.setValue(value);

		_attributes.add(attribute);
	}

	public Directory findBase(DN name) throws Exception {
		return findBase(name, this);
	}

	public Attribute getAttribute(String attributeId) {
		for (Attribute attribute : _attributes) {
			if (attributeId.equalsIgnoreCase(attribute.getAttributeId())) {
				return attribute;
			}
		}

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

	public List<Attribute> getAttributes() {
		return _attributes;
	}

	public List<Directory> getDirectories() throws Exception {
		if (_directories == null) {
			_directories = initDirectories();
		}

		return _directories;
	}

	public InternalRequest getInternalRequest() {
		Directory directory = _parentDirectory;

		while (directory != null) {
			InternalRequest internalRequest = directory.getInternalRequest();

			if (internalRequest != null) {
				return internalRequest;
			}

			directory = directory.getParentDirectory();
		}

		throw new RuntimeException("InternalRequest should never be null");
	}

	public DN getName() {
		return _name;
	}

	public Directory getParentDirectory() {
		return _parentDirectory;
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

	protected String escape(String name) {
		int pos = name.indexOf(CharPool.EQUAL);

		String suffix = name.substring(pos + 1);

		char[] charArray = suffix.toCharArray();

		StringBundler sb = new StringBundler();

		for (char c : charArray) {
			for (char escapeChar : _ESCAPE_CHARS) {
				if (c == escapeChar) {
					sb.append(CharPool.BACK_SLASH);

					break;
				}
			}

			sb.append(c);
		}

		String escapedSuffix = sb.toString();

		return name.substring(0, pos + 1) + escapedSuffix;
	}

	protected Directory findBase(DN name, Directory directory)
		throws Exception {

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

	protected boolean isDescendantOf(String name) {
		if (_name.getName().contains("," + name + ",")) {
			return true;
		}
		else {
			return false;
		}
	}

	protected abstract void initAttributes();

	protected abstract List<Directory> initDirectories() throws Exception;

	private static Log _log = LogFactoryUtil.getLog(BaseDirectory.class);

	/**
	 * http://www.rlmueller.net/CharactersEscaped.htm
	 */
	private static final char[] _ESCAPE_CHARS = {
		 ',', '\\', '#', '+', '<', '>', ';', '"', '='
	};

	private List<Attribute> _attributes = new ArrayList<Attribute>();
	private List<Directory> _directories;
	private Directory _parentDirectory;
	private DN _name;

}