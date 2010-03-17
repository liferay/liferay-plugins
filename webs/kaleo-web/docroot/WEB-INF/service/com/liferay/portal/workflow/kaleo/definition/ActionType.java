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

/**
 * <a href="ActionType.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public enum ActionType {

	ON_ENTRY("onEntry"), ON_EXIT("onExit");

	public static ActionType parse(String type) {
		if (ON_ENTRY.getType().equals(type)) {
			return ON_ENTRY;
		}
		else if (ON_EXIT.getType().equals(type)) {
			return ON_EXIT;
		}
		else {
			throw new IllegalArgumentException("Invalid type " + type);
		}
	}

	public String getType() {
		return _type;
	}

	private ActionType(String type) {
		_type = type;
	}

	private String _type;

}