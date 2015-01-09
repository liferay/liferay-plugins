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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupGroupRole;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.definition.RecipientType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

	protected void addAssignedRecipients(
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
				addUserNotificationRecipient(
					notificationRecipients,
					kaleoTaskAssignmentInstance.getAssigneeClassPK(),
					executionContext);
			}
			else {
				long roleId = kaleoTaskAssignmentInstance.getAssigneeClassPK();

				Role role = RoleLocalServiceUtil.getRole(roleId);

				addRoleRecipientAddresses(
					notificationRecipients, roleId, role.getType(),
					executionContext);
			}
		}
	}

	protected void addRoleRecipientAddresses(
			Set<NotificationRecipient> notificationRecipients, long roleId,
			int roleType, ExecutionContext executionContext)
		throws Exception {

		List<User> users = getRoleUsers(roleId, roleType, executionContext);

		for (User user : users) {
			if (user.isActive()) {
				NotificationRecipient notificationRecipient =
					new NotificationRecipient(user);

				notificationRecipients.add(notificationRecipient);
			}
		}
	}

	protected void addUserNotificationRecipient(
			Set<NotificationRecipient> notificationRecipients, long userId,
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
			NotificationRecipient notificationRecipient =
				new NotificationRecipient(user);

			notificationRecipients.add(notificationRecipient);
		}
	}

	protected abstract void doSendNotification(
			Set<NotificationRecipient> notificationRecipients,
			String defaultSubject, String notificationMessage,
			ExecutionContext executionContext)
		throws Exception;

	protected Set<NotificationRecipient> getNotificationRecipients(
			List<KaleoNotificationRecipient> kaleoNotificationRecipients,
			ExecutionContext executionContext)
		throws Exception {

		Set<NotificationRecipient> notificationRecipients = new HashSet<>();

		if (kaleoNotificationRecipients.isEmpty()) {
			addAssignedRecipients(notificationRecipients, executionContext);

			return notificationRecipients;
		}

		for (KaleoNotificationRecipient kaleoNotificationRecipient :
				kaleoNotificationRecipients) {

			String recipientClassName =
				kaleoNotificationRecipient.getRecipientClassName();

			if (recipientClassName.equals(RecipientType.ADDRESS.name())) {
				String address = kaleoNotificationRecipient.getAddress();

				NotificationRecipient notificationRecipient =
					new NotificationRecipient(address);

				notificationRecipients.add(notificationRecipient);
			}
			else if (recipientClassName.equals(
						RecipientType.ASSIGNEES.name())) {

				addAssignedRecipients(notificationRecipients, executionContext);
			}
			else if (recipientClassName.equals(Role.class.getName())) {
				addRoleRecipientAddresses(
					notificationRecipients,
					kaleoNotificationRecipient.getRecipientClassPK(),
					kaleoNotificationRecipient.getRecipientRoleType(),
					executionContext);
			}
			else if (recipientClassName.equals(User.class.getName())) {
				addUserNotificationRecipient(
					notificationRecipients,
					kaleoNotificationRecipient.getRecipientClassPK(),
					executionContext);
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unsupported recipient " + kaleoNotificationRecipient);
				}
			}
		}

		return notificationRecipients;
	}

	protected List<User> getRoleUsers(
			long roleId, int roleType, ExecutionContext executionContext)
		throws Exception {

		if (roleType == RoleConstants.TYPE_REGULAR) {
			return UserLocalServiceUtil.getInheritedRoleUsers(
				roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}

		List<User> users = new ArrayList<>();

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		List<UserGroupRole> userGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
				kaleoInstanceToken.getGroupId(), roleId);

		for (UserGroupRole userGroupRole : userGroupRoles) {
			users.add(userGroupRole.getUser());
		}

		List<UserGroupGroupRole> userGroupGroupRoles =
			UserGroupGroupRoleLocalServiceUtil.
				getUserGroupGroupRolesByGroupAndRole(
					kaleoInstanceToken.getGroupId(), roleId);

		for (UserGroupGroupRole userGroupGroupRole : userGroupGroupRoles) {
			users.addAll(
				UserLocalServiceUtil.getUserGroupUsers(
					userGroupGroupRole.getUserGroupId()));
		}

		return users;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseNotificationSender.class);

}