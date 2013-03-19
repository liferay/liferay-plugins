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

package com.liferay.portal.workflow.kaleo.runtime.notification;

import com.liferay.portal.kernel.util.Validator;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public abstract class BaseNotificationSender implements NotificationSender {

	public void sendNotification(
			List<KaleoNotificationRecipient> kaleoNotificationRecipients,
			String defaultSubject, String notificationMessage,
			ExecutionContext executionContext)
		throws NotificationMessageSenderException {

		try {
			Set<NotificationRecipient> notificationRecipients =
				getNotificationRecipients(
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

	protected abstract void doSendNotification(
			Set<NotificationRecipient> notificationRecipients,
			String defaultSubject, String notificationMessage,
			ExecutionContext executionContext)
		throws Exception;

	protected void getAssignedRecipients(
			Set<NotificationRecipient> notificationRecipients,
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
				getNotificationRecipient(
					kaleoTaskAssignmentInstance.getAssigneeClassPK(),
					notificationRecipients, executionContext);
			}
			else {
				long roleId = kaleoTaskAssignmentInstance.getAssigneeClassPK();

				Role role = RoleLocalServiceUtil.getRole(roleId);

				getRoleRecipientAddresses(
					roleId, role.getType(), notificationRecipients,
					executionContext);
			}
		}
	}

	protected void getNotificationRecipient(
			long userId, Set<NotificationRecipient> notificationRecipients,
			ExecutionContext executionContext)
		throws Exception {

		if (userId <= 0) {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

			userId = kaleoInstance.getUserId();
		}

		User user = UserLocalServiceUtil.getUser(userId);

		if (user.isActive()) {

			notificationRecipients.add(new NotificationRecipient(user));
		}
	}

	protected Set<NotificationRecipient> getNotificationRecipients(
			List<KaleoNotificationRecipient> kaleoNotificationRecipients,
			ExecutionContext executionContext)
		throws Exception {

		Set<NotificationRecipient> notificationRecipients =
			new HashSet<NotificationRecipient>();

		if (kaleoNotificationRecipients.isEmpty()) {
			getAssignedRecipients(notificationRecipients, executionContext);
		}
		else {
			for (KaleoNotificationRecipient kaleoNotificationRecipient :
				kaleoNotificationRecipients) {

				if (Validator.isNotNull(
						kaleoNotificationRecipient.getAddress())) {

					notificationRecipients.add(
						new NotificationRecipient(
							(kaleoNotificationRecipient.getAddress())));
				}
				else {
					String recipientClassName =
						kaleoNotificationRecipient.getRecipientClassName();

					if (recipientClassName.equals(User.class.getName())) {
						getNotificationRecipient(
							kaleoNotificationRecipient.getRecipientClassPK(),
							notificationRecipients, executionContext);
					}
					else {
						getRoleRecipientAddresses(
							kaleoNotificationRecipient.getRecipientClassPK(),
							kaleoNotificationRecipient.getRecipientRoleType(),
							notificationRecipients, executionContext);
					}
				}
			}
		}

		return notificationRecipients;
	}

	protected void getRoleRecipientAddresses(
			long roleId, int roleType,
			Set<NotificationRecipient> notificationRecipients,
			ExecutionContext executionContext)
		throws Exception {

		if (roleType == RoleConstants.TYPE_REGULAR) {
			List<User> users = UserLocalServiceUtil.getRoleUsers(roleId);

			for (User user : users) {
				if (user.isActive()) {
					notificationRecipients.add(new NotificationRecipient(user));
				}
			}
		} else {
			KaleoInstanceToken kaleoInstanceToken =
				executionContext.getKaleoInstanceToken();

			List<UserGroupRole> userGroupRoles =
				UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
					kaleoInstanceToken.getGroupId(), roleId);

			for (UserGroupRole userGroupRole : userGroupRoles) {
				User user = userGroupRole.getUser();

				if (user.isActive()) {
					notificationRecipients.add(new NotificationRecipient(user));
				}
			}
		}
	}

}