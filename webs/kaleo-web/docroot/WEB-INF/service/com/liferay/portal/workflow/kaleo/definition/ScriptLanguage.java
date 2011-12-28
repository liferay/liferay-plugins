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
public enum ScriptLanguage {

	BEANSHELL("beanshell"), DRL("drl"), GROOVY("groovy"),
	JAVASCRIPT("javascript"), PYTHON("python"), RUBY("ruby");

	public static ScriptLanguage parse(String value) {
		if (BEANSHELL.getValue().equals(value)) {
			return BEANSHELL;
		}
		else if (DRL.getValue().equals(value)) {
			return DRL;
		}
		else if (GROOVY.getValue().equals(value)) {
			return GROOVY;
		}
		else if (JAVASCRIPT.getValue().equals(value)) {
			return JAVASCRIPT;
		}
		else if (PYTHON.getValue().equals(value)) {
			return PYTHON;
		}
		else if (RUBY.getValue().equals(value)) {
			return RUBY;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private ScriptLanguage(String value) {
		_value = value;
	}

	private String _value;

}