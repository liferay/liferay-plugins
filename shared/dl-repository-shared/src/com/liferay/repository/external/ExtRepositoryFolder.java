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
 * Represents the external repository folder object. Implementers of external
 * repositories must provide an implementation of this class to make the bridge
 * between Liferay Portal and external repository domains. All data returned by
 * these implementations is in native repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryFolder extends ExtRepositoryObject {

	/**
	 * Returns the external repository folder's name.
	 *
	 * @return the external repository folder's name
	 */
	public String getName();

	/**
	 * Returns <code>true</code> if the external repository folder is a root
	 * folder.
	 *
	 * @return <code>true</code> if the external repository folder is a root
	 *         folder; <code>false</code> otherwise
	 */
	public boolean isRoot();

}