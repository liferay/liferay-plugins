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
 * An external repository file version object describes a version of a file
 * contained in an external repository. Developers of external repositories
 * must provide an implementation of this class to make the bridge between
 * Liferay and external repository domains.
 *
 * All data returned by implementations is in native ext repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryFileVersion extends ExtRepositoryModel {

	/**
	 * Get the comments associated to the commit of this version.
	 *
	 * @return the description of this version's commit
	 */
	public String getChangeLog();

	/**
	 * Get the MIME type of this version of a file. This method may return
	 * <code>null</code> if the MIME type is not available in the back end
	 * ext repository. In that case, Liferay Portal will guess the MIME type
	 * (usually by looking at the extension).
	 *
	 * @return the MIME type of this ext repository file version's version or
	 *         <code>null</code> if it is unknown
	 */
	public String getMimeType();

	/**
	 * Get the tag (name) of the ext repository version. For example: "1.0".
	 *
	 * @return the ext repository file version version
	 */
	public String getVersion();

}