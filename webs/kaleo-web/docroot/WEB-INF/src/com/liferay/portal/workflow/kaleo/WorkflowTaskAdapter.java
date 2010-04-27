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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.DefaultWorkflowTask;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * <a href="WorkflowTaskAdapter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WorkflowTaskAdapter extends DefaultWorkflowTask {

	public WorkflowTaskAdapter(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Map<String, Serializable> optionalAttributes)
		throws PortalException, SystemException {

		String assigneeClassName =
			kaleoTaskInstanceToken.getAssigneeClassName();
		long assigneeClassPK = kaleoTaskInstanceToken.getAssigneeClassPK();

		if (assigneeClassName.equals(Role.class.getName())) {
			setAssigneeRoleId(assigneeClassPK);
		}
		else {
			setAssigneeUserId(assigneeClassPK);

			User user = UserLocalServiceUtil.getUser(assigneeClassPK);

			setAssigneeEmailAddress(user.getEmailAddress());
		}

		setCreateDate(kaleoTaskInstanceToken.getCreateDate());
		setCompletionDate(kaleoTaskInstanceToken.getCompletionDate());
		setDueDate(kaleoTaskInstanceToken.getDueDate());
		setName(kaleoTaskInstanceToken.getKaleoTask().getName());
		setDescription(kaleoTaskInstanceToken.getKaleoTask().getDescription());

		if (optionalAttributes != null) {
			setOptionalAttributes(optionalAttributes);
		}
		else {
			setOptionalAttributes(
				WorkflowContextUtil.convert(
					kaleoTaskInstanceToken.getContext()));
		}

		KaleoInstanceToken kaleoInstanceToken =
			kaleoTaskInstanceToken.getKaleoInstanceToken();
		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

		setWorkflowDefinitionId(kaleoInstance.getKaleoDefinitionId());
		setWorkflowDefinitionName(kaleoInstance.getKaleoDefinitionName());
		setWorkflowDefinitionVersion(kaleoInstance.getKaleoDefinitionVersion());
		setWorkflowInstanceId(kaleoInstance.getKaleoInstanceId());
		setWorkflowTaskId(kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
	}

}