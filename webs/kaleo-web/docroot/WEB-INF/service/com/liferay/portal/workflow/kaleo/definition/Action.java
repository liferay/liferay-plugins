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
public class Action extends DefinitionNode {

	public void configureParent(DefinitionNode parentNode) {
		Actions actions = (Actions)parentNode;

		actions.addAction(this);
	}

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

	public int hashCode() {
		return _name.hashCode();
	}

	public void setExecutionType(ExecutionType executionType) {
		_executionType = executionType;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setScript(String script) {
		_script = script;
	}

	public void setScriptLanguage(ScriptLanguage scriptLanguage) {
		_scriptLanguage = scriptLanguage;
	}

	private String _description;
	private ExecutionType _executionType;
	private String _name;
	private int _priority;
	private String _script;
	private ScriptLanguage _scriptLanguage;

}