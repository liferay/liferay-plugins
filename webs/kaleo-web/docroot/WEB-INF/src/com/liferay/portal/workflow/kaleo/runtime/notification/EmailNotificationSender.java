/**
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
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.io.UnsupportedEncodingException;
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
			List<KaleoNotificationRecipient> kaleoNotificationRecipients,
			String subject, String notificationMessage,
			ExecutionContext executionContext)
		throws NotificationMessageSenderException {

		try {
			InternetAddress from = new InternetAddress(
				_fromAddress, _fromName);

			InternetAddress[] recipients = getRecipients(
				kaleoNotificationRecipients, executionContext);

			if (recipients.length == 0) {
				return;
			}

			MailMessage mailMessage = new MailMessage(
				from, subject, notificationMessage, true);

			mailMessage.setTo(recipients);

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
			List<KaleoNotificationRecipient> kaleoNotificationRecipients,
			ExecutionContext executionContext)
		throws Exception {

		List<InternetAddress> internetAddresses =
			new ArrayList<InternetAddress>();

		if (kaleoNotificationRecipients.isEmpty()) {
			getAssignedRecipients(internetAddresses, executionContext);
		}
		else {
			for (KaleoNotificationRecipient kaleoNotificationRecipient :
					kaleoNotificationRecipients) {

				if (Validator.isNotNull(
						kaleoNotificationRecipient.getAddress())) {

					InternetAddress internetAddress = new InternetAddress(
						kaleoNotificationRecipient.getAddress());

					internetAddresses.add(internetAddress);
				}
				else {
					String recipientClassName =
						kaleoNotificationRecipient.getRecipientClassName();

					if (User.class.getName().equals(recipientClassName)) {
						getUserEmailAddress(
							kaleoNotificationRecipient.getRecipientClassPK(),
							internetAddresses);
					}
					else {
						getRoleRecipientAddresses(
							kaleoNotificationRecipient.getRecipientRoleType(),
							kaleoNotificationRecipient.getRecipientClassPK(),
							internetAddresses, executionContext);
					}
				}
			}
		}

		return internetAddresses.toArray(
			new InternetAddress[internetAddresses.size()]);
	}

	protected void getAssignedRecipients(
			List<InternetAddress> internetAddresses,
			ExecutionContext executionContext)
		throws Exception {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken == null) {
			return;
		}

		if (User.class.getName().equals(
			kaleoTaskInstanceToken.getAssigneeClassName())) {

			getUserEmailAddress(
				kaleoTaskInstanceToken.getAssigneeClassPK(), internetAddresses);
		}
		else {
			long roleId = kaleoTaskInstanceToken.getAssigneeClassPK();
			Role role = RoleLocalServiceUtil.getRole(roleId);

			getRoleRecipientAddresses(
				role.getType(), roleId, internetAddresses, executionContext);
			
		}
	}

	protected void getRoleRecipientAddresses(
			int roleType, long roleId, 
			List<InternetAddress> internetAddresses,
			ExecutionContext executionContext)
		throws Exception {

		if (roleType == RoleConstants.TYPE_REGULAR) {

			List<User> users = UserLocalServiceUtil.getRoleUsers(roleId);

			for (User user : users) {
				InternetAddress internetAddress = new InternetAddress(
					user.getEmailAddress(), user.getFullName());

				internetAddresses.add(internetAddress);
			}
		}
		else {
			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				executionContext.getKaleoTaskInstanceToken();

			List<UserGroupRole> userGroupRoles =
				UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
					kaleoTaskInstanceToken.getGroupId(), roleId);

			for (UserGroupRole userGroupRole : userGroupRoles) {
				User user = userGroupRole.getUser();

				InternetAddress internetAddress = new InternetAddress(
					user.getEmailAddress(), user.getFullName());

				internetAddresses.add(internetAddress);
			}
		}
	}
	
	protected void getUserEmailAddress(
			long userId, List<InternetAddress> internetAddresses)
		throws Exception {
		User user = UserLocalServiceUtil.getUser(userId);

		InternetAddress internetAddress = new InternetAddress(
			user.getEmailAddress(), user.getFullName());

		internetAddresses.add(internetAddress);
	}


	private String _fromAddress;
	private String _fromName;

}