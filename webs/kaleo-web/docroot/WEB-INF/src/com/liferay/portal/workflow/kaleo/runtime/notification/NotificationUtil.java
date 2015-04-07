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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.definition.ExecutionType;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationRecipientLocalServiceUtil;

import java.util.List;

/**
 * @author Michael C. Han
 */
public class NotificationUtil {

	public static void sendKaleoNotifications(
			String kaleoClassName, long kaleoClassPK,
			ExecutionType executionType, ExecutionContext executionContext)
		throws PortalException {

		List<KaleoNotification> kaleoNotifications =
			KaleoNotificationLocalServiceUtil.getKaleoNotifications(
				kaleoClassName, kaleoClassPK, executionType.getValue());

		for (KaleoNotification kaleoNotification : kaleoNotifications) {
			_sendKaleoNotification(kaleoNotification, executionContext);
		}
	}

	private static void _sendKaleoNotification(
			KaleoNotification kaleoNotification,
			ExecutionContext executionContext)
		throws PortalException {

		NotificationMessageGenerator notificationMessageGenerator =
			NotificationMessageGeneratorFactory.getNotificationMessageGenerator(
				kaleoNotification.getTemplateLanguage());

		String notificationMessage =
			notificationMessageGenerator.generateMessage(
				kaleoNotification.getKaleoClassName(),
				kaleoNotification.getKaleoClassPK(),
				kaleoNotification.getName(),
				kaleoNotification.getTemplateLanguage(),
				kaleoNotification.getTemplate(), executionContext);

		String notificationSubject = StringPool.BLANK;

		if (Validator.isNotNull(kaleoNotification.getDescription())) {
			notificationSubject = notificationMessageGenerator.generateMessage(
				kaleoNotification.getKaleoClassName(),
				kaleoNotification.getKaleoClassPK(),
				kaleoNotification.getName(),
				kaleoNotification.getTemplateLanguage(),
				kaleoNotification.getDescription(), executionContext);
		}

		String[] notificationTypes = StringUtil.split(
			kaleoNotification.getNotificationTypes());

		List<KaleoNotificationRecipient> kaleoNotificationRecipient =
			KaleoNotificationRecipientLocalServiceUtil.
				getKaleoNotificationRecipients(
					kaleoNotification.getKaleoNotificationId());

		for (String notificationType : notificationTypes) {
			NotificationSender notificationSender =
				NotificationSenderFactory.getNotificationSender(
					notificationType);

			notificationSender.sendNotification(
				kaleoNotificationRecipient, notificationSubject,
				notificationMessage, executionContext);
		}
	}

}