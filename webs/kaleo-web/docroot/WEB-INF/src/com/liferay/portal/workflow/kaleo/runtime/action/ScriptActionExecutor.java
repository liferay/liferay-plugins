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

package com.liferay.portal.workflow.kaleo.runtime.action;

import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.util.ContextUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="ScriptActionExecutor.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class ScriptActionExecutor implements ActionExecutor {

	public void execute(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws ActionExecutorException {

		try {
			doExecute(kaleoAction, executionContext);
		}
		catch (Exception e) {
			throw new ActionExecutorException(e);
		}
	}

	protected void doExecute(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws Exception {

		String language = kaleoAction.getLanguage();

		String script = kaleoAction.getScript();

		Map<String, Serializable> context = executionContext.getContext();

		if (context == null) {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

			context = ContextUtil.convert(kaleoInstance.getContext());
		}

		Map<String, Object> inputObjects = new HashMap<String, Object>(
			context);

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment =
			executionContext.getKaleoTaskInstanceAssigment();

		if (kaleoTaskInstanceAssignment != null) {
			inputObjects.put(
				"assigneeClassName",
				kaleoTaskInstanceAssignment.getAssigneeClassName());
			inputObjects.put(
				"assigneeClassPK",
				kaleoTaskInstanceAssignment.getAssigneeClassPK());

			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				executionContext.getKaleoTaskInstanceToken();

			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			inputObjects.put("taskName", kaleoTask.getName());

			inputObjects.put("userId", kaleoTaskInstanceAssignment.getUserId());
		}
		else {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			inputObjects.put("userId", kaleoInstanceToken.getUserId());
		}

		ScriptingUtil.exec(null, inputObjects, language, script);
	}

}