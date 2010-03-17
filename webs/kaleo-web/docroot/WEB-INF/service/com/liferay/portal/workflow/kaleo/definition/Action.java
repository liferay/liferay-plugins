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
 * <a href="Action.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class Action {

	public Action(String name, String actionType, String language) {
		_name = name;
		_actionType = ActionType.parse(actionType);
		_language = language;
	}

	public ActionType getActionType() {
		return _actionType;
	}

	public int getExecutionOrder() {
		return _executionOrder;
	}

	public String getLanguage() {
		return _language;
	}

	public String getName() {
		return _name;
	}

	public String getScript() {
		return _script;
	}

	public void setExecutionOrder(int executionOrder) {
		_executionOrder = executionOrder;
	}

	public void setScript(String script) {
		_script = script;
	}

	private ActionType _actionType;
	private int _executionOrder;
	private String _language;
	private String _name;
	private String _script;

}