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

package com.liferay.portal.workflow.kaleo.runtime.notification;

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

/**
 * @author Michael C. Han
 */
public class EmailNotificationSender implements NotificationSender {

	public void sendNotification(
			List<KaleoNotificationRecipient> kaleoNotificationRecipients,
			String defaultSubject, String notificationMessage,
			ExecutionContext executionContext)
		throws NotificationMessageSenderException {

		try {
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

			InternetAddress[] recipients = getRecipients(
				kaleoNotificationRecipients, executionContext);

			if (recipients.length == 0) {
				return;
			}

			String subject = (String)workflowContext.get(
				WorkflowConstants.CONTEXT_NOTIFICATION_SUBJECT);

			if (Validator.isNull(subject)) {
				subject = defaultSubject;
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

	protected void getAssignedRecipients(
			List<InternetAddress> internetAddresses,
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

			String assigneeClassName =
				kaleoTaskAssignmentInstance.getAssigneeClassName();

			if (assigneeClassName.equals(User.class.getName())) {
				getUserEmailAddress(
					kaleoTaskAssignmentInstance.getAssigneeClassPK(),
					internetAddresses, executionContext);
			}
			else {
				long roleId = kaleoTaskAssignmentInstance.getAssigneeClassPK();

				Role role = RoleLocalServiceUtil.getRole(roleId);

				getRoleRecipientAddresses(
					roleId, role.getType(), internetAddresses,
					executionContext);
			}
		}
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

					if (recipientClassName.equals(User.class.getName())) {
						getUserEmailAddress(
							kaleoNotificationRecipient.getRecipientClassPK(),
							internetAddresses, executionContext);
					}
					else {
						getRoleRecipientAddresses(
							kaleoNotificationRecipient.getRecipientClassPK(),
							kaleoNotificationRecipient.getRecipientRoleType(),
							internetAddresses, executionContext);
					}
				}
			}
		}

		return internetAddresses.toArray(
			new InternetAddress[internetAddresses.size()]);
	}

	protected void getRoleRecipientAddresses(
			long roleId, int roleType, List<InternetAddress> internetAddresses,
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
			long userId, List<InternetAddress> internetAddresses,
			ExecutionContext executionContext)
		throws Exception {

		if (userId <= 0) {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

			userId = kaleoInstance.getUserId();
		}

		User user = UserLocalServiceUtil.getUser(userId);

		InternetAddress internetAddress = new InternetAddress(
			user.getEmailAddress(), user.getFullName());

		internetAddresses.add(internetAddress);
	}

	private String _fromAddress;
	private String _fromName;

}