/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.repository.external.model;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryObjectAdapterType
	<T extends ExtRepositoryModelAdapter<?>> {

	public static final ExtRepositoryObjectAdapterType
		<ExtRepositoryFileEntryAdapter> FILE =
			new ExtRepositoryObjectAdapterType<>("FILE");

	public static final ExtRepositoryObjectAdapterType
		<ExtRepositoryFolderAdapter> FOLDER =
			new ExtRepositoryObjectAdapterType<>("FOLDER");

	public static final ExtRepositoryObjectAdapterType
		<ExtRepositoryObjectAdapter<?>> OBJECT =
			new ExtRepositoryObjectAdapterType<>("OBJECT");

	@Override
	public String toString() {
		return _name;
	}

	private ExtRepositoryObjectAdapterType(String name) {
		_name = name;
	}

	private String _name;

}