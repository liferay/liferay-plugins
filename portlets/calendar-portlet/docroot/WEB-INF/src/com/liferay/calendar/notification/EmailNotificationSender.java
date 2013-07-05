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

package com.liferay.calendar.notification;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.model.CalendarNotificationTemplateConstants;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.util.NotificationUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.model.User;

import javax.mail.internet.InternetAddress;

/**
 * @author Eduardo Lundgren
 */
public class EmailNotificationSender implements NotificationSender {

	@Override
	public void sendNotification(
			NotificationRecipient notificationRecipient,
			NotificationTemplateContext notificationTemplateContext)
		throws NotificationSenderException {

		try {
			CalendarNotificationTemplate calendarNotificationTemplate =
				notificationTemplateContext.getCalendarNotificationTemplate();

			Calendar calendar = CalendarLocalServiceUtil.getCalendar(
				notificationTemplateContext.getCalendarId());

			User defaultSenderUser = NotificationUtil.getDefaultSenderUser(
				calendar);

			String fromAddress = NotificationUtil.getTemplatePropertyValue(
				calendarNotificationTemplate,
				CalendarNotificationTemplateConstants.PROPERTY_FROM_ADDRESS,
				defaultSenderUser.getEmailAddress());
			String fromName = NotificationUtil.getTemplatePropertyValue(
				calendarNotificationTemplate,
				CalendarNotificationTemplateConstants.PROPERTY_FROM_NAME,
				defaultSenderUser.getFullName());

			sendNotification(
				fromAddress, fromName, notificationRecipient,
				notificationTemplateContext.getSubject(),
				notificationTemplateContext.getBody());
		}
		catch (Exception e) {
			throw new NotificationSenderException(e);
		}
	}

	@Override
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

}