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

package com.liferay.portal.workflow.kaleo.runtime.notification;

import com.liferay.portal.kernel.freemarker.FreeMarkerContext;
import com.liferay.portal.kernel.freemarker.FreeMarkerEngine;
import com.liferay.portal.kernel.freemarker.FreeMarkerEngineUtil;
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
public class FreeMarkerNotificationMessageGenerator
	implements NotificationMessageGenerator {

	public String generateMessage(
			String kaleoClassName, long kaleoClassPK, String notificationName,
			String notificationTemplate, ExecutionContext executionContext)
		throws NotificationMessageGenerationException {

		FreeMarkerEngine freeMarkerEngine =
			FreeMarkerEngineUtil.getFreeMarkerEngine();

		FreeMarkerContext freeMarketContext =
			FreeMarkerEngineUtil.getWrappedRestrictedToolsContext();

		try {
			populateContextVariables(freeMarketContext, executionContext);

			StringWriter stringWriter = new StringWriter();

			freeMarkerEngine.mergeTemplate(
				notificationName + kaleoClassName + kaleoClassPK,
				notificationTemplate, freeMarketContext, stringWriter);

			return stringWriter.toString();
		}
		catch (Exception e) {
			throw new NotificationMessageGenerationException(
				"Unable to generate notification message", e);
		}
	}

	protected void populateContextVariables(
			FreeMarkerContext freeMarkerContext,
			ExecutionContext executionContext)
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

			freeMarkerContext.put(entry.getKey(), entry.getValue());
		}

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken != null) {
			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			freeMarkerContext.put("taskName", kaleoTask.getName());

			freeMarkerContext.put("userId", kaleoTaskInstanceToken.getUserId());

			List<WorkflowTaskAssignee> workflowTaskAssignees =
				KaleoTaskAssignmentInstanceUtil.getWorkflowTaskAssignees(
					kaleoTaskInstanceToken);

			freeMarkerContext.put(
				"workflowTaskAssignees", workflowTaskAssignees);
		}
		else {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			freeMarkerContext.put("userId", kaleoInstanceToken.getUserId());
		}
	}

}