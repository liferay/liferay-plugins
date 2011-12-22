/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
 * @author Michael C. Han
 */
public enum ExecutionType {

	ON_ASSIGNMENT("onAssignment"), ON_ENTRY("onEntry"), ON_EXIT("onExit"),
	ON_TIMER("onTimer");

	public static ExecutionType parse(String value) {
		if (ON_ASSIGNMENT.getValue().equals(value)) {
			return ON_ASSIGNMENT;
		}
		else if (ON_ENTRY.getValue().equals(value)) {
			return ON_ENTRY;
		}
		else if (ON_EXIT.getValue().equals(value)) {
			return ON_EXIT;
		}
		else if (ON_TIMER.getValue().equals(value)) {
			return ON_TIMER;
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

	private ExecutionType(String value) {
		_value = value;
	}

	private String _value;

}