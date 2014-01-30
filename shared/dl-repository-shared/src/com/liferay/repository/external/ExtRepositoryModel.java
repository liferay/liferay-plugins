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

import java.util.Date;

/**
 * An external repository model object describes a folder, file or version
 * contained in an external repository.
 *
 * All data returned by implementations is in native repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryModel {

	/**
	 * Gets the creation date of this model.
	 *
	 * @return the creation date of this model
	 */
	public Date getCreateDate();

	/**
	 * Gets the primary key of this model in the external repository.
	 *
	 * @return a primary key in external repository format
	 */
	public String getExtRepositoryModelKey();

	/**
	 * Gets the owner of this model in the external repository. The returned
	 * user identifier is converted from native to Liferay format by means of
	 * the method {@link ExtRepository#getLiferayLogin(String)}.
	 *
	 * @return a user identifier in the native repository format
	 */
	public String getOwner();

	/**
	 * Get the size of this model in bytes.
	 *
	 * @return the size of this model in bytes
	 */
	public long getSize();

}