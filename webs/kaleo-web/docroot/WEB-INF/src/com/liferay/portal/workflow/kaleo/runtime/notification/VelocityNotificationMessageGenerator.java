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

package com.liferay.portal.workflow.kaleo.runtime.notification;

import com.liferay.portal.kernel.velocity.VelocityContext;
import com.liferay.portal.kernel.velocity.VelocityEngine;
import com.liferay.portal.kernel.velocity.VelocityEngineUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.util.ContextUtil;

import java.io.Serializable;
import java.io.StringWriter;

import java.util.Map;

/**
 * <a href="VelocityNotificationMessageGenerator.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Michael C. Han
 */
public class VelocityNotificationMessageGenerator
	implements NotificationMessageGenerator {

	public String generateMessage(
			long kaleoNodeId, String notificationName,
			String notificationTemplate, ExecutionContext executionContext)
		throws NotificationMessageGenerationException {

		VelocityEngine velocityEngine = VelocityEngineUtil.getVelocityEngine();

		VelocityContext velocityContext =
			VelocityEngineUtil.getRestrictedToolsContext();

		try {
			populateContextVariables(velocityContext, executionContext);

			StringWriter stringWriter = new StringWriter();

			velocityEngine.mergeTemplate(
				notificationName + kaleoNodeId, notificationTemplate,
				velocityContext, stringWriter);

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

		Map<String, Serializable> context = executionContext.getContext();

		if (context == null) {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

			context = ContextUtil.convert(kaleoInstance.getContext());
		}

		for (Map.Entry<String, Serializable> contextEntry: context.entrySet()) {
			velocityContext.put(contextEntry.getKey(), contextEntry.getValue());
		}

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken != null) {
			velocityContext.put(
				"assigneeClassName",
				kaleoTaskInstanceToken.getAssigneeClassName());
			velocityContext.put(
				"assigneeClassPK",
				kaleoTaskInstanceToken.getAssigneeClassPK());

			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			velocityContext.put("taskName", kaleoTask.getName());

			velocityContext.put(
				"userId", kaleoTaskInstanceToken.getUserId());
		}
		else {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			velocityContext.put("userId", kaleoInstanceToken.getUserId());
		}
	}

}