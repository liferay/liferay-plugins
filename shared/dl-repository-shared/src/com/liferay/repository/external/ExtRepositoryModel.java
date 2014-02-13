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
 * Represents the external repository model, which describes a folder, file, or
 * file version in the external repository. All data returned by this class'
 * implementation is in native repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryModel {

	/**
	 * Returns the external repository model's creation date.
	 *
	 * @return the external repository model's creation date
	 */
	public Date getCreateDate();

	/**
	 * Returns the external repository model's primary key.
	 *
	 * @return the external repository model's primary key
	 */
	public String getExtRepositoryModelKey();

	/**
	 * Returns the external repository model's owner. The returned user
	 * identifier is converted from the native repository format to the Liferay
	 * Portal format by calling the {@link
	 * ExtRepository#getLiferayLogin(String)} method.
	 *
	 * @return the external repository model's owner
	 */
	public String getOwner();

	/**
	 * Returns the external repository's model size in bytes.
	 *
	 * @return the external repository's model size in bytes
	 */
	public long getSize();

}