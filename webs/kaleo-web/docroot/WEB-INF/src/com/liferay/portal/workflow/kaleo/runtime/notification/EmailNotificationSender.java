/*
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

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * <a href="EmailNotificationSender.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class EmailNotificationSender implements NotificationSender {
	public void sendNotification(
			List<KaleoNotificationRecipient> notificationRecipients,
			String subject, String notificationMessage,
			ExecutionContext executionContext)
		throws NotificationMessageSenderException {

		try {
			InternetAddress from = new InternetAddress(
				_fromAddress, _fromName);

			MailMessage mailMessage = new MailMessage(
				from, subject, notificationMessage, true);

			mailMessage.setTo(getRecipients(notificationRecipients));

			MailServiceUtil.sendEmail(mailMessage);

		}
		catch (Exception e) {
			throw new NotificationMessageSenderException(
				"Unable to send mail message", e);
		}

	}

	public void setFromAddress(String fromAddress) {
		_fromAddress = fromAddress;
	}

	public void setFromName(String fromName) {
		_fromName = fromName;
	}

	protected InternetAddress[] getRecipients(
			List<KaleoNotificationRecipient> notificationRecipients)
		throws Exception {

		List<InternetAddress> internetAddresses =
			new ArrayList<InternetAddress>();

		for (KaleoNotificationRecipient recipient : notificationRecipients) {
			if (Validator.isNotNull(recipient.getAddress())) {
				internetAddresses.add(
					new InternetAddress(recipient.getAddress()));
			}
			else {
				String recipientClass = recipient.getRecipientClassName();

				if (User.class.getName().equals(recipientClass)) {
					User user = UserLocalServiceUtil.getUser(
						recipient.getRecipientClassPK());

					internetAddresses.add(
						new InternetAddress(
							user.getEmailAddress(), user.getFullName()));
				}
				else {
					List<User> roleUsers = UserLocalServiceUtil.getRoleUsers(
						recipient.getRecipientClassPK());

					for (User user : roleUsers) {
						internetAddresses.add(
							new InternetAddress(
								user.getEmailAddress(), user.getFullName()));
					}
				}
			}
		}

		return internetAddresses.toArray(
			new InternetAddress[internetAddresses.size()]);
	}

	private String _fromAddress;
	private String _fromName;

}