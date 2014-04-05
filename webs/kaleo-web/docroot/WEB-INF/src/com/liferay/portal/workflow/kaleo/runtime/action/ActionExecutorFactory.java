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

package com.liferay.portal.workflow.kaleo.runtime.action;

import com.liferay.portal.kernel.workflow.WorkflowException;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public class ActionExecutorFactory {

	public static ActionExecutor getActionExecutor(String scriptLanguage)
		throws WorkflowException {

		ActionExecutor actionExecutor = _actionExecutors.get(scriptLanguage);

		if (actionExecutor == null) {
			throw new WorkflowException(
				"Invalid script language " + scriptLanguage);
		}

		return actionExecutor;
	}

	public void setActionExecutors(
		Map<String, ActionExecutor> actionExecutors) {

		_actionExecutors = actionExecutors;
	}

	private static Map<String, ActionExecutor> _actionExecutors;

}