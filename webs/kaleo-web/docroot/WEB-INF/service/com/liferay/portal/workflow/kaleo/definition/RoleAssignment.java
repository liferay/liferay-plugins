/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.util.Validator;

/**
 * <a href="RoleAssignment.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class RoleAssignment extends Assignment {

	public RoleAssignment(long roleId, boolean defaultValue) {
		super(AssignmentType.ROLE, defaultValue);

		_roleId = roleId;
	}

	public RoleAssignment(String roleName, boolean defaultValue) {
		super(AssignmentType.ROLE, defaultValue);

		_roleName = roleName;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof RoleAssignment)) {
			return false;
		}

		RoleAssignment roleAssignment = (RoleAssignment)obj;

		if (Validator.equals(_roleName, roleAssignment._roleName) &&
			(_roleId == roleAssignment._roleId)) {

			return true;
		}

		return true;
	}

	public long getRoleId() {
		return _roleId;
	}

	public String getRoleName() {
		return _roleName;
	}

	public int hashCode() {
		return _roleName.hashCode();
	}

	private long _roleId;
	private String _roleName;

}