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
 * Represents the external repository object, being either an external
 * repository file object or folder object. All data returned by this class'
 * implementation is in native repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryObject extends ExtRepositoryModel {

	/**
	 * Returns <code>true</code> if the user has permission to perform the
	 * action on the external repository object.
	 *
	 * @param  extRepositoryPermission the action to check for permission
	 * @return <code>true</code> if the user has permission to perform the
	 *         action on the external repository object; <code>false</code>
	 *         otherwise
	 */
	public boolean containsPermission(
		ExtRepositoryPermission extRepositoryPermission);

	/**
	 * Returns the external repository object's description. The object's
	 * description is not its name.
	 *
	 * @return the external repository object's description
	 */
	public String getDescription();

	/**
	 * Returns the external repository object's file or folder extension,
	 * excluding any leading period.
	 *
	 * @return the external repository object's file or folder extension,
	 *         excluding any leading period
	 */
	public String getExtension();

	/**
	 * Returns the external repository object's last modified date.
	 *
	 * @return the external repository object's last modified date
	 */
	public Date getModifiedDate();

	/**
	 * Holds the permissions that external repositories must support. In this
	 * context, the external repository implementation may be asked about a
	 * permission, and it must answer correctly, but is not required to fully
	 * implement it.
	 * 
	 * <p>
	 * For instance, an external repository must not fail when asked about the
	 * <code>ADD_SHORTCUT</code> permission, even if the back-end external
	 * repository does not support shortcuts. However, it can return
	 * <code>false</code> when asked for that permission.
	 * </p>
	 *
	 * @author Iván Zaera
	 */
	public enum ExtRepositoryPermission {

		ACCESS, ADD_DISCUSSION, ADD_DOCUMENT, ADD_FOLDER, ADD_SHORTCUT,
		ADD_SUBFOLDER, DELETE, DELETE_DISCUSSION, PERMISSIONS, UPDATE,
		UPDATE_DISCUSSION, VIEW

	}

}