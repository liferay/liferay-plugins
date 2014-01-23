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
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepositoryObject extends ExtRepositoryModel {

	public boolean containsPermission(
		ExtRepositoryPermission extRepositoryPermission);

	public String getDescription();

	public String getExtension();

	public Date getModifiedDate();

	public enum ExtRepositoryPermission {

		ACCESS, ADD_DISCUSSION, ADD_DOCUMENT, ADD_FOLDER, ADD_SHORTCUT,
		ADD_SUBFOLDER, DELETE, DELETE_DISCUSSION, PERMISSIONS, UPDATE,
		UPDATE_DISCUSSION, VIEW

	}

}