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

package com.liferay.portal.workflow.kaleo.runtime.notification.recipient;

import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.notification.recipient.script.NotificationRecipientEvaluator;
import com.liferay.portal.workflow.kaleo.runtime.notification.recipient.script.ScriptingNotificationRecipientConstants;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class ScriptNotificationRecipientBuilder
	implements NotificationRecipientBuilder {

	@Override
	public void processKaleoNotificationRecipient(
			Set<NotificationRecipient> notificationRecipients,
			KaleoNotificationRecipient kaleoNotificationRecipient,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		Map<String, ?> results = _notificationRecipientEvaluator.evaluate(
			kaleoNotificationRecipient, executionContext);

		Map<String, Serializable> resultsWorkflowContext =
			(Map<String, Serializable>)results.get(
				WorkflowContextUtil.WORKFLOW_CONTEXT_NAME);

		WorkflowContextUtil.mergeWorkflowContexts(
			executionContext, resultsWorkflowContext);

		User user = (User)results.get(
			ScriptingNotificationRecipientConstants.USER_RECIPIENT);

		if (user != null) {
			if (user.isActive()) {
				NotificationRecipient notificationRecipient =
					new NotificationRecipient(user, notificationReceptionType);

				notificationRecipients.add(notificationRecipient);
			}
		}
		else {
			List<Role> roles = (List<Role>)results.get(
				ScriptingNotificationRecipientConstants.ROLES_RECIPIENT);

			for (Role role : roles) {
				_roleNotificationRecipientBuilder.addRoleRecipientAddresses(
					notificationRecipients, role.getRoleId(), role.getType(),
					notificationReceptionType, executionContext);
			}
		}
	}

	@Override
	public void processKaleoTaskAssignmentInstance(
			Set<NotificationRecipient> notificationRecipients,
			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {
	}

	public void setNotificationRecipientEvaluator(
		NotificationRecipientEvaluator notificationRecipientEvaluator) {

		_notificationRecipientEvaluator = notificationRecipientEvaluator;
	}

	public void setRoleNotificationRecipientBuilder(
		RoleNotificationRecipientBuilder roleNotificationRecipientBuilder) {

		_roleNotificationRecipientBuilder = roleNotificationRecipientBuilder;
	}

	private NotificationRecipientEvaluator _notificationRecipientEvaluator;
	private RoleNotificationRecipientBuilder _roleNotificationRecipientBuilder;

}