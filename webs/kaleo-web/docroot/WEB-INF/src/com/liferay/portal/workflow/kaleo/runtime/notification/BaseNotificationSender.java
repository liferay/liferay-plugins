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

import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.definition.RecipientType;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.notification.recipient.NotificationRecipientBuilder;
import com.liferay.portal.workflow.kaleo.runtime.notification.recipient.NotificationRecipientBuilderRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public abstract class BaseNotificationSender implements NotificationSender {

	@Override
	public void sendNotification(
			List<KaleoNotificationRecipient> kaleoNotificationRecipients,
			String defaultSubject, String notificationMessage,
			ExecutionContext executionContext)
		throws NotificationMessageSenderException {

		try {
			Map<NotificationReceptionType, Set<NotificationRecipient>>
				notificationRecipients = getNotificationRecipients(
					kaleoNotificationRecipients, executionContext);

			if (notificationRecipients.isEmpty()) {
				return;
			}

			doSendNotification(
				notificationRecipients, defaultSubject, notificationMessage,
				executionContext);
		}
		catch (Exception e) {
			throw new NotificationMessageSenderException(
				"Unable to send notification message", e);
		}
	}

	public void setNotificationRecipientBuilderRegistry(
		NotificationRecipientBuilderRegistry
			notificationRecipientBuilderRegistry) {

		_notificationRecipientBuilderRegistry =
			notificationRecipientBuilderRegistry;
	}

	protected abstract void doSendNotification(
			Map<NotificationReceptionType, Set<NotificationRecipient>>
				notificationRecipients, String defaultSubject,
			String notificationMessage, ExecutionContext executionContext)
		throws Exception;

	protected Map<NotificationReceptionType, Set<NotificationRecipient>>
		getNotificationRecipients(
			List<KaleoNotificationRecipient> kaleoNotificationRecipients,
			ExecutionContext executionContext)
		throws Exception {

		Map<NotificationReceptionType, Set<NotificationRecipient>>
			notificationRecipients = new HashMap<>();

		if (kaleoNotificationRecipients.isEmpty()) {
			Set<NotificationRecipient> notificationRecipientsSet =
				retrieveNotificationRecipients(
					notificationRecipients, NotificationReceptionType.TO);

			NotificationRecipientBuilder notificationRecipientBuilder =
				_notificationRecipientBuilderRegistry.
					getKaleoNotificationRecipientHandler(
						RecipientType.ASSIGNEES);

			notificationRecipientBuilder.processKaleoNotificationRecipient(
				notificationRecipientsSet, null, NotificationReceptionType.TO,
				executionContext);

			return notificationRecipients;
		}

		for (KaleoNotificationRecipient kaleoNotificationRecipient :
				kaleoNotificationRecipients) {

			NotificationReceptionType notificationReceptionType =
				NotificationReceptionType.parse(
					kaleoNotificationRecipient.getNotificationReceptionType());

			Set<NotificationRecipient> notificationRecipientsSet =
				retrieveNotificationRecipients(
					notificationRecipients, notificationReceptionType);

			RecipientType recipientType = RecipientType.valueOf(
				kaleoNotificationRecipient.getRecipientClassName());

			NotificationRecipientBuilder notificationRecipientBuilder =
				_notificationRecipientBuilderRegistry.
					getKaleoNotificationRecipientHandler(recipientType);

			notificationRecipientBuilder.processKaleoNotificationRecipient(
				notificationRecipientsSet, kaleoNotificationRecipient,
				notificationReceptionType, executionContext);
		}

		return notificationRecipients;
	}

	protected Set<NotificationRecipient> retrieveNotificationRecipients(
		Map<NotificationReceptionType, Set<NotificationRecipient>>
			notificationRecipients,
		NotificationReceptionType notificationReceptionType) {

		Set<NotificationRecipient> notificationRecipientsSet =
			notificationRecipients.get(notificationReceptionType);

		if (notificationRecipientsSet == null) {
			notificationRecipientsSet = new HashSet<>();

			notificationRecipients.put(
				notificationReceptionType, notificationRecipientsSet);
		}

		return notificationRecipientsSet;
	}

	private NotificationRecipientBuilderRegistry
		_notificationRecipientBuilderRegistry;

}