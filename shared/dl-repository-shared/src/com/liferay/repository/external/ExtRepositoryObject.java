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
 * An external repository object instance describes a folder or a file contained
 * in an external repository.
 *
 * All data returned by implementations is in native repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryObject extends ExtRepositoryModel {

	/**
	 * Checks whether the user has permission to perform the specified action
	 * on this object.
	 *
	 * @param extRepositoryPermission the action to check for permission
	 * @return true if the user is allowed to perform the action in this object
	 */
	public boolean containsPermission(
		ExtRepositoryPermission extRepositoryPermission);

	/**
	 * Gets the long description of this file or folder (note that the
	 * description is not the name).
	 *
	 * @return a long description of the object
	 */
	public String getDescription();

	/**
	 * Gets the file/folder extension of this object.
	 *
	 * @return the extension (without the leading period)
	 */
	public String getExtension();

	/**
	 * Get the date when this object was last modified.
	 *
	 * @return the last modified date of the object
	 */
	public Date getModifiedDate();

	/**
	 * This enum holds permissions that external repositories must support. In
	 * this context, "support" means that the external repository implementation
	 * may be asked about that permission and it must answer correctly, not that
	 * it must fully implement it.
	 *
	 * For instance, a repository must not fail when asked about ADD_SHORTCUT
	 * even if the back end repository does not support shortcuts. But it may
	 * well return always false when asked for that permission.
	 *
	 * @author Iván Zaera
	 */
	public enum ExtRepositoryPermission {

		ACCESS, ADD_DISCUSSION, ADD_DOCUMENT, ADD_FOLDER, ADD_SHORTCUT,
		ADD_SUBFOLDER, DELETE, DELETE_DISCUSSION, PERMISSIONS, UPDATE,
		UPDATE_DISCUSSION, VIEW

	}

}