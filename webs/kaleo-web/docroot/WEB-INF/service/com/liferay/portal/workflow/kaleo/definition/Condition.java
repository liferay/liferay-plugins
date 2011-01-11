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

/**
 * @author Michael C. Han
 */
public class Condition extends DefinitionNode {

	public void configureParent(DefinitionNode parentNode) {
		Transition transition = (Transition)parentNode;

		transition.setCondition(this);
	}

	public String getDescription() {
		return _description;
	}

	public String getScript() {
		return _script;
	}

	public ScriptLanguage getScriptLanguage() {
		return _scriptLanguage;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setScript(String script) {
		_script = script;
	}

	public void setScriptLanguage(ScriptLanguage scriptLanguage) {
		_scriptLanguage = scriptLanguage;
	}

	private String _description;
	private String _script;
	private ScriptLanguage _scriptLanguage;

}