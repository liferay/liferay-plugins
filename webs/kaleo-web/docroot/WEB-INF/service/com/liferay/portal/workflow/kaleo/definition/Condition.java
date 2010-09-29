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

/**
 * <a href="Condition.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class Condition {

	public Condition(String language, String script) {
		this(null, language, script);
	}

	public Condition(String description, String language, String script) {
		_description = description;
		_scriptLanguage = ScriptLanguage.parse(language);
		_script = script;
	}

	public String getDescription() {
		return _description;
	}

	public ScriptLanguage getScriptLanguage() {
		return _scriptLanguage;
	}

	public String getScript() {
		return _script;
	}

	private String _description;
	private String _script;
	private ScriptLanguage _scriptLanguage;
}
