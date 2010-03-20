/*
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
 * <a href="RoleRecipient.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class RoleRecipient extends Recipient {

	public RoleRecipient(String roleName) {
		super(Type.ROLE);

		_roleName = roleName;
	}

	public RoleRecipient(long roleId) {
		super(Type.ROLE);

		_roleId = roleId;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof RoleAssignment)) {
			return false;
		}

		RoleRecipient roleRecipient = (RoleRecipient)obj;

		if (Validator.equals(_roleName, roleRecipient._roleName) &&
			(_roleId == roleRecipient._roleId)) {
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

	private String _roleName;
	private long _roleId;

}