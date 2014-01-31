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
 * This class holds constants to meta-describe the types of {@link
 * ExtRepositoryObject}'s available. Each constant is tied to an {@link
 * ExtRepositoryObject} derived interface and can be used as parameters to
 * methods that need to differentiate between, for example, files or folders.
 *
 * The constants are designed so that Java generics can be used to make the
 * return of methods type-safe. See method
 * {@link ExtRepository#getExtRepositoryObject(ExtRepositoryObjectType, String,
 * String)}
 * for an example of a method signature using these constants.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public final class ExtRepositoryObjectType<T extends ExtRepositoryModel> {

	/**
	 * An {@link ExtRepositoryObjectType} constant referring to interface {@link
	 * ExtRepositoryFileEntry}
	 */
	public static final ExtRepositoryObjectType<ExtRepositoryFileEntry> FILE =
		new ExtRepositoryObjectType<ExtRepositoryFileEntry>("FILE");

	/**
	 * An {@link ExtRepositoryObjectType} constant referring to interface {@link
	 * ExtRepositoryFolder}
	 */
	public static final ExtRepositoryObjectType<ExtRepositoryFolder> FOLDER =
		new ExtRepositoryObjectType<ExtRepositoryFolder>("FOLDER");

	/**
	 * An {@link ExtRepositoryObjectType} constant referring to interface {@link
	 * ExtRepositoryObject} (includes both files and folders)
	 */
	public static final ExtRepositoryObjectType<ExtRepositoryObject> OBJECT =
		new ExtRepositoryObjectType<ExtRepositoryObject>("OBJECT");

	@Override
	public String toString() {
		return _name;
	}

	private ExtRepositoryObjectType(String name) {
		_name = name;
	}

	private String _name;

}