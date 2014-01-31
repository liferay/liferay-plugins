/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.repository.external;

/**
 * This class describes how to locate a file version. It consists of two fields:
 * an {@link ExtRepositoryFileEntry} key and a version name belonging to that
 * file.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryFileVersionDescriptor {

	/**
	 * Create an ext repository file version descriptor with the given ext
	 * repository file entry key and ext repository version name.
	 *
	 * @param extRepositoryFileEntryKey the ext repository file entry key
	 * @param version the name of a version belonging to the specified ext
	 *        repository file entry
	 */
	public ExtRepositoryFileVersionDescriptor(
		String extRepositoryFileEntryKey, String version) {

		_extRepositoryFileEntryKey = extRepositoryFileEntryKey;
		_version = version;
	}

	/**
	 * Gets the ext repository file entry key.
	 *
	 * @return the ext repository file entry key
	 */
	public String getExtRepositoryFileEntryKey() {
		return _extRepositoryFileEntryKey;
	}

	/**
	 * Gets the ext repository version name.
	 *
	 * @return the ext repository version name
	 */
	public String getVersion() {
		return _version;
	}

	private String _extRepositoryFileEntryKey;
	private String _version;

}