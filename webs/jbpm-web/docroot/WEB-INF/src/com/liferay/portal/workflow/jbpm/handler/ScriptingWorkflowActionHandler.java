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

package com.liferay.portal.workflow.jbpm.handler;

import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.workflow.ContextConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="ScriptingWorkflowActionHandler.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Michael C. Han
 */
public class ScriptingWorkflowActionHandler implements ActionHandler {

	public void execute(ExecutionContext executionContext) throws Exception {
		ContextInstance contextInstance =
			executionContext.getContextInstance();

		Map<String, Object> workflowContext = contextInstance.getVariables();

		Map<String, Object> inputObjects = new HashMap<String, Object>(
			workflowContext);

		inputObjects.put("workflowContext", workflowContext);

		Long companyId = (Long)inputObjects.get(ContextConstants.COMPANY_ID);

		TaskInstance taskInstance = executionContext.getTaskInstance();

		if (taskInstance != null) {
			inputObjects.put("assigneeClassName", User.class.getName());
			inputObjects.put("assigneeClassPK", taskInstance.getActorId());
			inputObjects.put("taskName", taskInstance.getName());
			inputObjects.put("userId", taskInstance.getActorId());
		}
		else if (companyId != null) {
			User user = UserLocalServiceUtil.getDefaultUser(companyId);

			inputObjects.put("userId", user.getUserId());
		}

		ScriptingUtil.exec(null, inputObjects, language, script);
	}

	private String language;
	private String script;

}