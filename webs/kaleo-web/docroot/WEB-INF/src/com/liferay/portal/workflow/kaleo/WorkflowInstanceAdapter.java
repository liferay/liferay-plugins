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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.DefaultWorkflowInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public class WorkflowInstanceAdapter extends DefaultWorkflowInstance {

	public WorkflowInstanceAdapter(
			KaleoInstance kaleoInstance, KaleoInstanceToken kaleoInstanceToken)
		throws PortalException, SystemException {

		this(kaleoInstance, kaleoInstanceToken, null);
	}

	public WorkflowInstanceAdapter(
			KaleoInstance kaleoInstance, KaleoInstanceToken kaleoInstanceToken,
			Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		_kaleoInstanceToken = kaleoInstanceToken;

		setEndDate(kaleoInstance.getCompletionDate());
		setStartDate(kaleoInstance.getCreateDate());
		setState(kaleoInstanceToken.getCurrentKaleoNode().getName());

		if (workflowContext != null) {
			setWorkflowContext(workflowContext);
		}
		else {
			setWorkflowContext(
				WorkflowContextUtil.convert(
					kaleoInstance.getWorkflowContext()));
		}

		setWorkflowDefinitionName(kaleoInstance.getKaleoDefinitionName());
		setWorkflowDefinitionVersion(kaleoInstance.getKaleoDefinitionVersion());
		setWorkflowInstanceId(kaleoInstance.getKaleoInstanceId());
	}

	public KaleoInstanceToken getKaleoInstanceToken() {
		return _kaleoInstanceToken;
	}

	private KaleoInstanceToken _kaleoInstanceToken;

}