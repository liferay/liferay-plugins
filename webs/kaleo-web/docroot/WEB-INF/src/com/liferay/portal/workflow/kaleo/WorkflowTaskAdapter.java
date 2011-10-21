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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.DefaultWorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.util.KaleoTaskAssignmentInstanceUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class WorkflowTaskAdapter extends DefaultWorkflowTask {

	public WorkflowTaskAdapter(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		_kaleoTaskInstanceToken = kaleoTaskInstanceToken;

		setCreateDate(kaleoTaskInstanceToken.getCreateDate());
		setCompletionDate(kaleoTaskInstanceToken.getCompletionDate());
		setDescription(kaleoTaskInstanceToken.getKaleoTask().getDescription());
		setDueDate(kaleoTaskInstanceToken.getDueDate());
		setName(kaleoTaskInstanceToken.getKaleoTask().getName());

		if (workflowContext != null) {
			setOptionalAttributes(workflowContext);
		}
		else {
			setOptionalAttributes(
				WorkflowContextUtil.convert(
					kaleoTaskInstanceToken.getWorkflowContext()));
		}

		KaleoInstanceToken kaleoInstanceToken =
			kaleoTaskInstanceToken.getKaleoInstanceToken();
		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

		setWorkflowDefinitionId(kaleoInstance.getKaleoDefinitionId());
		setWorkflowDefinitionName(kaleoInstance.getKaleoDefinitionName());
		setWorkflowDefinitionVersion(kaleoInstance.getKaleoDefinitionVersion());
		setWorkflowInstanceId(kaleoInstance.getKaleoInstanceId());

		List<WorkflowTaskAssignee> workflowTaskAssignees =
			KaleoTaskAssignmentInstanceUtil.getWorkflowTaskAssignees(
				kaleoTaskInstanceToken);

		setWorkflowTaskAssignees(workflowTaskAssignees);

		setWorkflowTaskId(kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
	}

	public KaleoTaskInstanceToken getKaleoTaskInstanceToken() {
		return _kaleoTaskInstanceToken;
	}

	private KaleoTaskInstanceToken _kaleoTaskInstanceToken;

}