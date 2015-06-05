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

import com.liferay.portal.model.User;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.definition.RecipientType;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationRecipient;

import java.util.List;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class AssigneeNotificationRecipientBuilder
	implements NotificationRecipientBuilder {

	@Override
	public void processKaleoNotificationRecipient(
			Set<NotificationRecipient> notificationRecipients,
			KaleoNotificationRecipient kaleoNotificationRecipient,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		addAssignedRecipients(
			notificationRecipients, notificationReceptionType,
			executionContext);
	}

	@Override
	public void processKaleoTaskAssignmentInstance(
			Set<NotificationRecipient> notificationRecipients,
			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		addAssignedRecipients(
			notificationRecipients, notificationReceptionType,
			executionContext);
	}

	public void setNotificationRecipientBuilderRegistry(
		NotificationRecipientBuilderRegistry
			notificationRecipientBuilderRegistry) {

		_notificationRecipientBuilderRegistry =
			notificationRecipientBuilderRegistry;
	}

	protected void addAssignedRecipients(
			Set<NotificationRecipient> notificationRecipients,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken == null) {
			return;
		}

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance :
				kaleoTaskAssignmentInstances) {

			RecipientType recipientType = RecipientType.ROLE;

			String assigneeClassName =
				kaleoTaskAssignmentInstance.getAssigneeClassName();

			if (assigneeClassName.equals(User.class.getName())) {
				recipientType = RecipientType.USER;
			}

			NotificationRecipientBuilder notificationRecipientBuilder =
				_notificationRecipientBuilderRegistry.
					getNotificationRecipientBuilder(recipientType);

			notificationRecipientBuilder.processKaleoTaskAssignmentInstance(
				notificationRecipients, kaleoTaskAssignmentInstance,
				notificationReceptionType, executionContext);
		}
	}

	private NotificationRecipientBuilderRegistry
		_notificationRecipientBuilderRegistry;

}