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
 * Represents the external repository file entry object. Implementers of
 * external repositories must provide an implementation of this class to make
 * the bridge between Liferay Portal and external repository domains. All data
 * returned by these implementations is in native repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryFileEntry extends ExtRepositoryObject {

	/**
	 * Returns an identifier for the user who checked out the file from the
	 * external repository, or <code>null</code> if the file is not checked out.
	 * The returned user identifier is converted from the native repository
	 * format to the Liferay format by calling the {@link
	 * ExtRepository#getLiferayLogin(String)} method.
	 *
	 * @return an identifier for the user who checked out the file from the
	 *         external repository, or <code>null</code> if the file is not
	 *         checked out
	 */
	public String getCheckedOutBy();

	/**
	 * Returns the MIME type of the external repository file, or
	 * <code>null</code> if the MIME type is not available in the external
	 * repository. If the MIME type is unavailable, Liferay Portal guesses the
	 * MIME type (usually by looking at the extension).
	 *
	 * @return the MIME type of the external repository file, or
	 *         <code>null</code> if the MIME type is not available in the
	 *         external repository
	 */
	public String getMimeType();

	/**
	 * Returns the external repository file entry's name, including its
	 * extension.
	 *
	 * @return the external repository file entry's name, including its
	 *         extension
	 */
	public String getTitle();

}