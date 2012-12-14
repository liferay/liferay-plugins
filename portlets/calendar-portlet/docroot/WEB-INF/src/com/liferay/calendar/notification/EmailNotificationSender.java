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

package com.liferay.calendar.notification;

import com.liferay.calendar.util.NotificationUtil;
import com.liferay.calendar.util.PortletPropsKeys;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.StringUtil;

import javax.mail.internet.InternetAddress;

/**
 * @author Eduardo Lundgren
 */
public class EmailNotificationSender implements NotificationSender {

	public void sendNotification(
			NotificationRecipient notificationRecipient,
			NotificationTemplateType notificationTemplateType,
			NotificationTemplateContext notificationTemplateContext)
		throws NotificationSenderException {

		try {
			String fromAddress = notificationTemplateContext.getString(
				"fromAddress");
			String fromName = notificationTemplateContext.getString("fromName");
			String subject = getSubject(
				notificationTemplateType, notificationTemplateContext);
			String body = getBody(
				notificationTemplateType, notificationTemplateContext);

			sendNotification(
				fromAddress, fromName, notificationRecipient, subject, body);
		}
		catch (Exception e) {
			throw new NotificationSenderException(e);
		}
	}

	public void sendNotification(
			String fromAddress, String fromName,
			NotificationRecipient notificationRecipient, String subject,
			String notificationMessage)
		throws NotificationSenderException {

		try {
			InternetAddress fromInternetAddress = new InternetAddress(
				fromAddress, fromName);

			MailMessage mailMessage = new MailMessage(
				fromInternetAddress, subject, notificationMessage, true);

			mailMessage.setHTMLFormat(notificationRecipient.isHTMLFormat());

			InternetAddress toInternetAddress = new InternetAddress(
				notificationRecipient.getEmailAddress());

			mailMessage.setTo(toInternetAddress);

			MailServiceUtil.sendEmail(mailMessage);
		}
		catch (Exception e) {
			throw new NotificationSenderException(
				"Unable to send mail message", e);
		}
	}

	protected String getBody(
			NotificationTemplateType notificationTemplateType,
			NotificationTemplateContext notificationTemplateContext)
		throws Exception {

		String notificationTemplateContent =
			NotificationUtil.getNotificationTemplateContent(
				PortletPropsKeys.CALENDAR_NOTIFICATION_BODY,
				NotificationType.EMAIL, notificationTemplateType,
				notificationTemplateContext);

		return processNotificationTemplateContent(
			notificationTemplateContent, notificationTemplateContext);
	}

	protected String getSubject(
			NotificationTemplateType notificationTemplateType,
			NotificationTemplateContext notificationTemplateContext)
		throws Exception {

		String notificationTemplateContent =
			NotificationUtil.getNotificationTemplateContent(
				PortletPropsKeys.CALENDAR_NOTIFICATION_SUBJECT,
				NotificationType.EMAIL, notificationTemplateType,
				notificationTemplateContext);

		return processNotificationTemplateContent(
			notificationTemplateContent, notificationTemplateContext);
	}

	protected String processNotificationTemplateContent(
			String notificationTemplateContent,
			NotificationTemplateContext notificationTemplateContext)
		throws Exception {

		notificationTemplateContent = StringUtil.replace(
			notificationTemplateContent,
			new String[] {
				"[$BOOKING_LOCATION$]", "[$BOOKING_START_DATE$]",
				"[$BOOKING_END_DATE$]", "[$BOOKING_TITLE$]", "[$FROM_ADDRESS$]",
				"[$FROM_NAME$]", "[$PORTAL_URL$]", "[$TO_ADDRESS$]",
				"[$TO_NAME$]", "[$PORTLET_NAME$]"
			},
			new String[] {
				notificationTemplateContext.getString("location"),
				notificationTemplateContext.getString("startTime"),
				notificationTemplateContext.getString("endTime"),
				notificationTemplateContext.getString("title"),
				notificationTemplateContext.getString("fromAddress"),
				notificationTemplateContext.getString("fromName"),
				notificationTemplateContext.getString("portalUrl"),
				notificationTemplateContext.getString("toAddress"),
				notificationTemplateContext.getString("toName"),
				notificationTemplateContext.getString("portletName"),
			});

		return notificationTemplateContent;
	}

}