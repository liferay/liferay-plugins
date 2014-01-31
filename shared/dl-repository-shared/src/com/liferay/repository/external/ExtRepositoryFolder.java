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
 * An external repository folder object describes a folder contained in an
 * external repository. Implementors of external repositories must provide an
 * implementation of this class to make the bridge between Liferay and external
 * repository domains.
 *
 * All data returned by implementations is in native repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryFolder extends ExtRepositoryObject {

	/**
	 * Gets the name of the folder.
	 *
	 * @return the name of the folder
	 */
	public String getName();

	/**
	 * Checks whether or not this is the root folder.
	 *
	 * @return <code>true</code> if this is the root folder
	 */
	public boolean isRoot();

}