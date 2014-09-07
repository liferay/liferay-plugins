/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.google.apps.connector;

/**
 * @author Brian Wing Shun Chan
 */
public class GGroup {

	public String getDescription() {
		return _description;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getEmailPermission() {
		return _emailPermission;
	}

	public String getName() {
		return _name;
	}

	public String getPermissionPreset() {
		return _permissionPreset;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setEmailPermission(String emailPermission) {
		_emailPermission = emailPermission;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPermissionPreset(String permissionPreset) {
		_permissionPreset = permissionPreset;
	}

	private String _description;
	private String _emailAddress;
	private String _emailPermission;
	private String _name;
	private String _permissionPreset;

}