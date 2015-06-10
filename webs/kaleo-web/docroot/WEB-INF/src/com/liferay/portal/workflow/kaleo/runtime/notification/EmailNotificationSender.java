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

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author Michael C. Han
 */
public class EmailNotificationSender
	extends BaseNotificationSender implements NotificationSender {

	public void setFromAddress(String fromAddress) {
		_fromAddress = fromAddress;
	}

	public void setFromName(String fromName) {
		_fromName = fromName;
	}

	@Override
	protected void doSendNotification(
			Map<NotificationReceptionType, Set<NotificationRecipient>>
				notificationRecipients,
			String defaultSubject, String notificationMessage,
			ExecutionContext executionContext)
		throws Exception {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();

		String fromAddress = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_NOTIFICATION_SENDER_ADDRESS);

		if (Validator.isNull(fromAddress)) {
			fromAddress = _fromAddress;
		}

		String fromName = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_NOTIFICATION_SENDER_NAME);

		if (Validator.isNull(fromName)) {
			fromName = _fromName;
		}

		InternetAddress from = new InternetAddress(fromAddress, fromName);

		String subject = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_NOTIFICATION_SUBJECT);

		if (Validator.isNull(subject)) {
			subject = defaultSubject;
		}

		MailMessage mailMessage = new MailMessage(
			from, subject, notificationMessage, true);

		mailMessage.setTo(
			getInternetAddresses(
				notificationRecipients.get(NotificationReceptionType.TO)));
		mailMessage.setCC(
			getInternetAddresses(
				notificationRecipients.get(NotificationReceptionType.CC)));
		mailMessage.setBCC(
			getInternetAddresses(
				notificationRecipients.get(NotificationReceptionType.BCC)));

		MailServiceUtil.sendEmail(mailMessage);
	}

	protected InternetAddress[] getInternetAddresses(
			Set<NotificationRecipient> notificationRecipients)
		throws AddressException, UnsupportedEncodingException {

		if (notificationRecipients == null) {
			return new InternetAddress[0];
		}

		List<InternetAddress> internetAddresses = new ArrayList<>(
			notificationRecipients.size());

		for (NotificationRecipient notificationRecipient :
				notificationRecipients) {

			internetAddresses.add(notificationRecipient.getInternetAddress());
		}

		return internetAddresses.toArray(
			new InternetAddress[internetAddresses.size()]);
	}

	private String _fromAddress;
	private String _fromName;

}