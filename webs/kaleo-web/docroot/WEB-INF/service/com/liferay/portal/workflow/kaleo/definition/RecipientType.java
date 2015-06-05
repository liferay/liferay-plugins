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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;

/**
 * @author Michael C. Han
 */
public enum RecipientType {

	ADDRESS("address"), ASSIGNEES("assignees"), ROLE(Role.class.getName()),
	SCRIPT("script"), USER(User.class.getName());

	public static RecipientType parse(String value) {
		if (ADDRESS.getValue().equals(value)) {
			return ADDRESS;
		}
		else if (ASSIGNEES.getValue().equals(value)) {
			return ASSIGNEES;
		}
		else if (ROLE.getValue().equals(value)) {
			return ROLE;
		}
		else if (SCRIPT.getValue().equals(value)) {
			return SCRIPT;
		}
		else if (USER.getValue().equals(value)) {
			return USER;
		}
		else {
			throw new IllegalArgumentException("Invalid value " + value);
		}
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private RecipientType(String value) {
		_value = value;
	}

	private String _value;

}