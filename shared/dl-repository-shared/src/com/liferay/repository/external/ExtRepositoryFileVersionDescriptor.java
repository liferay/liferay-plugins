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
 * Provides methods to locate an external repository file version. An external
 * repository file's version descriptor consists of an {@link
 * ExtRepositoryFileEntry} key and the version name belonging to that file
 * entry.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryFileVersionDescriptor {

	/**
	 * Creates an external repository file version descriptor with the
	 * repository file entry key and version name.
	 *
	 * @param extRepositoryFileEntryKey the repository file entry key
	 * @param version the repository file entry's version name
	 */
	public ExtRepositoryFileVersionDescriptor(
		String extRepositoryFileEntryKey, String version) {

		_extRepositoryFileEntryKey = extRepositoryFileEntryKey;
		_version = version;
	}

	/**
	 * Returns the external repository file entry key.
	 *
	 * @return the external repository file entry key
	 */
	public String getExtRepositoryFileEntryKey() {
		return _extRepositoryFileEntryKey;
	}

	/**
	 * Returns the external repository version name.
	 *
	 * @return the external repository version name
	 */
	public String getVersion() {
		return _version;
	}

	private String _extRepositoryFileEntryKey;
	private String _version;

}