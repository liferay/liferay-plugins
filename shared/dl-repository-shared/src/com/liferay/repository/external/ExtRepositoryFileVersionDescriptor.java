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
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryFileVersionDescriptor {

	public ExtRepositoryFileVersionDescriptor(
		String extRepositoryFileEntryKey, String version) {

		_extRepositoryFileEntryKey = extRepositoryFileEntryKey;
		_version = version;
	}

	public String getExtRepositoryFileEntryKey() {
		return _extRepositoryFileEntryKey;
	}

	public String getVersion() {
		return _version;
	}

	private String _extRepositoryFileEntryKey;
	private String _version;

}