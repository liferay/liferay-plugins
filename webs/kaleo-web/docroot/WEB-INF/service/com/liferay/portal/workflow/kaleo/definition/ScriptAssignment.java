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
public class ScriptAssignment extends Assignment {

	public ScriptAssignment(String script, String scriptLanguage) {
		super(AssignmentType.SCRIPT);

		_script = script;
		_scriptLanguage = ScriptLanguage.parse(scriptLanguage);
	}

	public String getScript() {
		return _script;
	}

	public ScriptLanguage getScriptLanguage() {
		return _scriptLanguage;
	}

	public void setScript(String script) {
		_script = script;
	}

	public void setScriptLanguage(ScriptLanguage scriptLanguage) {
		_scriptLanguage = scriptLanguage;
	}

	private String _script;
	private ScriptLanguage _scriptLanguage;

}