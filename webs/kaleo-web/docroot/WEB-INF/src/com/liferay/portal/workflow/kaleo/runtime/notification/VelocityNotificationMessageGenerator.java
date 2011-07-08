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

package com.liferay.portal.workflow.kaleo.runtime.notification;

import com.liferay.portal.kernel.velocity.VelocityContext;
import com.liferay.portal.kernel.velocity.VelocityEngine;
import com.liferay.portal.kernel.velocity.VelocityEngineUtil;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.util.KaleoTaskAssignmentInstanceUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;
import java.io.StringWriter;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class VelocityNotificationMessageGenerator
	implements NotificationMessageGenerator {

	public String generateMessage(
			String kaleoClassName, long kaleoClassPK, String notificationName,
			String notificationTemplate, ExecutionContext executionContext)
		throws NotificationMessageGenerationException {

		VelocityEngine velocityEngine = VelocityEngineUtil.getVelocityEngine();

		VelocityContext velocityContext =
			VelocityEngineUtil.getRestrictedToolsContext();

		try {
			populateContextVariables(velocityContext, executionContext);

			StringWriter stringWriter = new StringWriter();

			velocityEngine.mergeTemplate(
				notificationName + kaleoClassName + kaleoClassPK,
				notificationTemplate, velocityContext, stringWriter);

			return stringWriter.toString();
		}
		catch (Exception e) {
			throw new NotificationMessageGenerationException(
				"Unable to generate notification message", e);
		}
	}

	protected void populateContextVariables(
			VelocityContext velocityContext, ExecutionContext executionContext)
		throws Exception {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();

		if (workflowContext == null) {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

			workflowContext = WorkflowContextUtil.convert(
				kaleoInstance.getWorkflowContext());
		}

		for (Map.Entry<String, Serializable> entry :
				workflowContext.entrySet()) {

			velocityContext.put(entry.getKey(), entry.getValue());
		}

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken != null) {
			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			velocityContext.put("taskName", kaleoTask.getName());

			velocityContext.put(
				"userId", kaleoTaskInstanceToken.getUserId());

			List<WorkflowTaskAssignee> workflowTaskAssignees =
				KaleoTaskAssignmentInstanceUtil.getWorkflowTaskAssignees(
					kaleoTaskInstanceToken);

			velocityContext.put("workflowTaskAssignees", workflowTaskAssignees);
		}
		else {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			velocityContext.put("userId", kaleoInstanceToken.getUserId());
		}
	}

}