/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class UserNotificationMessageSender
	extends BaseNotificationSender implements NotificationSender {

	@Override
	protected void doSendNotification(
			Map<NotificationReceptionType, Set<NotificationRecipient>>
				notificationRecipients, String defaultSubject,
			String notificationMessage, ExecutionContext executionContext)
		throws Exception {

		JSONObject jsonObject = populateJSONObject(
			notificationMessage, executionContext);

		for (Map.Entry<NotificationReceptionType, Set<NotificationRecipient>>
				entry : notificationRecipients.entrySet()) {

			for (NotificationRecipient notificationRecipient :
					entry.getValue()) {

				if (notificationRecipient.getUserId() <= 0) {
					continue;
				}

				UserNotificationEventLocalServiceUtil.
					sendUserNotificationEvents(
						notificationRecipient.getUserId(),
						PortletKeys.MY_WORKFLOW_TASK,
						UserNotificationDeliveryConstants.TYPE_WEBSITE,
						jsonObject);
			}
		}
	}

	protected JSONObject populateJSONObject(
		String notificationMessage, ExecutionContext executionContext) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();

		jsonObject.put(
			WorkflowConstants.CONTEXT_COMPANY_ID,
			String.valueOf(
				workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID)));
		jsonObject.put(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME,
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));
		jsonObject.put(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_PK,
			String.valueOf(
				workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)));
		jsonObject.put(
			WorkflowConstants.CONTEXT_ENTRY_TYPE,
			(String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE));
		jsonObject.put(
			WorkflowConstants.CONTEXT_GROUP_ID,
			String.valueOf(
				workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID)));
		jsonObject.put(
			WorkflowConstants.CONTEXT_USER_ID,
			String.valueOf(
				workflowContext.get(WorkflowConstants.CONTEXT_USER_ID)));

		jsonObject.put("notificationMessage", notificationMessage);

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		jsonObject.put(
			"workflowInstanceId", kaleoInstanceToken.getKaleoInstanceId());

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		jsonObject.put(
			"workflowTaskId",
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());

		return jsonObject;
	}

}