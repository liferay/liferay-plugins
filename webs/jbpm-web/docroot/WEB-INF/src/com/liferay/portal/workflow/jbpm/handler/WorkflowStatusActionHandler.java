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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowStatusActionHandler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 */
public class WorkflowStatusActionHandler implements ActionHandler {

	public void execute(ExecutionContext executionContext) throws Exception {
		long companyId = (Long)executionContext.getVariable(
			WorkflowConstants.COMPANY_ID);
		long groupId = (Long)executionContext.getVariable(
			WorkflowConstants.GROUP_ID);

		if (userId == 0) {
			TaskInstance taskInstance = executionContext.getTaskInstance();

			if (taskInstance != null) {
				userId = getUserId(companyId, taskInstance.getActorId());
			}
		}

		String className = (String)executionContext.getVariable(
			WorkflowConstants.ENTRY_CLASS_NAME);
		long classPK = (Long)executionContext.getVariable(
			WorkflowConstants.ENTRY_CLASS_PK);

		WorkflowStatusManagerUtil.updateStatus(
			companyId, groupId, userId, className, classPK,
			WorkflowConstants.fromLabel(status));
	}

	protected long getUserId(long companyId, String actorId) throws Exception {
		if (Validator.isEmailAddress(actorId)) {
			User user = UserLocalServiceUtil.getUserByEmailAddress(
				companyId, actorId);

			return user.getUserId();
		}
		else {
			return GetterUtil.getLong(actorId);
		}
	}

	private String status;
	private long userId;

}