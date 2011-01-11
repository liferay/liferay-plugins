/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Michael C. Han
 */
public class RoleAssignment extends Assignment {

	public RoleAssignment() {
		super(AssignmentType.ROLE);

		_autoCreate = true;
	}

	public RoleAssignment(long roleId, String roleType) {
		super(AssignmentType.ROLE);

		_roleId = roleId;
		_roleType = roleType;
	}

	public RoleAssignment(String roleName, String roleType) {
		super(AssignmentType.ROLE);

		_roleName = roleName;
		_roleType = roleType;
	}

	public void configureParent(DefinitionNode parentNode) {
		RoleAssignments roleAssignments = (RoleAssignments)parentNode;

		roleAssignments.addRoleAssignment(this);
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

	public String getRoleType() {
		return _roleType;
	}

	public int hashCode() {
		return _roleName.hashCode();
	}

	public boolean isAutoCreate() {
		return _autoCreate;
	}

	public void setName(String name) {
		_roleName = name;
	}

	public void setRoleType(String roleType) {
		_roleType = roleType;
	}

	public void setAutoCreate(String autoCreate) {
		_autoCreate = GetterUtil.getBoolean(autoCreate);
	}

	public void setAutoCreate(boolean autoCreate) {
		_autoCreate = autoCreate;
	}

	private boolean _autoCreate;
	private long _roleId;
	private String _roleName;
	private String _roleType;

}