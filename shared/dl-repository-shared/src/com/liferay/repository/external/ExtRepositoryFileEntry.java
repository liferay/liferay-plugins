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
 * An external repository file entry object describes a file contained in an
 * external repository. Implementors of external repositories must provide an
 * implementation of this class to make the bridge between Liferay and external
 * repository domains.
 *
 * All data returned by implementations is in native repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryFileEntry extends ExtRepositoryObject {

	/**
	 * Gets repository login of the user who has checked out this file. The
	 * returned user identifier is converted from native to Liferay format by
	 * means of the method {@link ExtRepository#getLiferayLogin(String)}.
	 *
	 * @return a native repository user identifier or null if the file is not
	 *         currently checked out
	 */
	public String getCheckedOutBy();

	/**
	 * Gets the MIME type of the file. This method may return null if the MIME
	 * type is not available in the back end repository. In that case, Liferay
	 * Portal will guess the MIME type (usually by looking at the extension).
	 *
	 * @return the MIME type of the file or null if it is unknown
	 */
	public String getMimeType();

	/**
	 * Get the name of the file.
	 *
	 * @return the name of the file (including extension)
	 */
	public String getTitle();

}