/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.template.StringTemplateResource;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.util.Validator;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marcellus Tavares
 * @author Michael C. Han
 */
public class TemplateNotificationMessageGenerator
	implements NotificationMessageGenerator {

	public String generateMessage(
			String kaleoClassName, long kaleoClassPK, String notificationName,
			String notificationTemplateLanguage, String notificationTemplate,
			ExecutionContext executionContext)
		throws NotificationMessageGenerationException {

		String templateManagerName = _templateManagerNames.get(
			notificationTemplateLanguage);

		if (Validator.isNull(templateManagerName)) {
			throw new NotificationMessageGenerationException(
				"Unsupported notification template language " +
					notificationTemplateLanguage);
		}

		try {
			String templateId =
				notificationName + kaleoClassName + kaleoClassPK;

			Template template = TemplateManagerUtil.getTemplate(
				templateManagerName,
				new StringTemplateResource(templateId, notificationTemplate),
				false);

			populateContextVariables(template, executionContext);

			StringWriter stringWriter = new StringWriter();

			template.processTemplate(stringWriter);

			return stringWriter.toString();
		}
		catch (Exception e) {
			throw new NotificationMessageGenerationException(
				"Unable to generate notification message", e);
		}
	}

	public void setTemplateManagerNames(
		Map<String, String> templateManagerNames) {

		_templateManagerNames = templateManagerNames;
	}

	protected void populateContextVariables(
			Template template, ExecutionContext executionContext)
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

			template.put(entry.getKey(), entry.getValue());
		}

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken != null) {
			KaleoTask kaleoTask = kaleoTaskInstanceToken.getKaleoTask();

			template.put("taskName", kaleoTask.getName());

			template.put("userId", kaleoTaskInstanceToken.getUserId());

			List<WorkflowTaskAssignee> workflowTaskAssignees =
				KaleoTaskAssignmentInstanceUtil.getWorkflowTaskAssignees(
					kaleoTaskInstanceToken);

			template.put("workflowTaskAssignees", workflowTaskAssignees);
		}
		else {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			template.put("userId", kaleoInstanceToken.getUserId());
		}
	}

	private Map<String, String> _templateManagerNames =
		new HashMap<String, String>();

}