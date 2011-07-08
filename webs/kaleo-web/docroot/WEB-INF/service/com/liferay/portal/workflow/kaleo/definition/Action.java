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

import com.liferay.portal.kernel.util.Validator;

/**
 * @author Michael C. Han
 */
public class Action {

	public Action(
		String name, String description, String executionType, String script,
		String scriptLanguage, int priority) {

		_name = name;
		_description = description;

		if (Validator.isNotNull(executionType)) {
			_executionType = ExecutionType.parse(executionType);
		}
		else {
			_executionType = ExecutionType.ON_TIMER;
		}

		_script = script;
		_scriptLanguage = ScriptLanguage.parse(scriptLanguage);
		_priority = priority;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Action)) {
			return false;
		}

		Action action = (Action)obj;

		if (Validator.equals(_name, action._name)) {
			return true;
		}

		return true;
	}

	public String getDescription() {
		return _description;
	}

	public ExecutionType getExecutionType() {
		return _executionType;
	}

	public String getName() {
		return _name;
	}

	public int getPriority() {
		return _priority;
	}

	public String getScript() {
		return _script;
	}

	public ScriptLanguage getScriptLanguage() {
		return _scriptLanguage;
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	private String _description;
	private ExecutionType _executionType;
	private String _name;
	private int _priority;
	private String _script;
	private ScriptLanguage _scriptLanguage;

}