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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.AddressRecipient;
import com.liferay.portal.workflow.kaleo.definition.Recipient;
import com.liferay.portal.workflow.kaleo.definition.RoleRecipient;
import com.liferay.portal.workflow.kaleo.definition.UserRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.service.base.KaleoNotificationRecipientLocalServiceBaseImpl;

import java.util.Date;

/**
 * <a href="KaleoNotificationRecipientLocalServiceImpl.java.html"><b><i>View
 * Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class KaleoNotificationRecipientLocalServiceImpl
	extends KaleoNotificationRecipientLocalServiceBaseImpl {

	public KaleoNotificationRecipient addRecipient(
			long kaleoNotificationId, Recipient recipient,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoNotificationRecipientId = counterLocalService.increment();

		KaleoNotificationRecipient kaleoNotificationRecipient =
			kaleoNotificationRecipientPersistence.create(
				kaleoNotificationRecipientId);

		kaleoNotificationRecipient.setCompanyId(user.getCompanyId());
		kaleoNotificationRecipient.setUserId(user.getUserId());
		kaleoNotificationRecipient.setUserName(user.getFullName());
		kaleoNotificationRecipient.setCreateDate(now);
		kaleoNotificationRecipient.setModifiedDate(now);

		setRecipient(kaleoNotificationRecipient, recipient, serviceContext);

		kaleoNotificationRecipientPersistence.update(
			kaleoNotificationRecipient, false);

		return kaleoNotificationRecipient;

	}

	private void setRecipient(
			KaleoNotificationRecipient kaleoNotificationRecipient,
			Recipient recipient, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Recipient.Type recipientType = recipient.getRecipientType();

		if (recipientType.equals(Recipient.Type.ROLE)) {
			kaleoNotificationRecipient.setRecipientClassName(
				Role.class.getName());

			RoleRecipient roleRecipient = (RoleRecipient)recipient;

			Role role = null;
			if (Validator.isNotNull(roleRecipient.getRoleName())) {
				role = roleLocalService.getRole(
					serviceContext.getCompanyId(), roleRecipient.getRoleName());
			}
			else {
				role = roleLocalService.getRole(roleRecipient.getRoleId());
			}

			kaleoNotificationRecipient.setRecipientClassPK(role.getClassPK());
		}
		else if (recipientType.equals(Recipient.Type.USER)) {
			kaleoNotificationRecipient.setRecipientClassName(
				User.class.getName());

			UserRecipient roleRecipient = (UserRecipient)recipient;

			User user = null;

			if (roleRecipient.getUserId() > 0) {
				user = userLocalService.getUser(roleRecipient.getUserId());
			}
			else if (Validator.isNotNull(roleRecipient.getScreenName())) {
				user = userLocalService.getUserByScreenName(
					serviceContext.getCompanyId(),
					roleRecipient.getScreenName());
			}
			else {
				user = userLocalService.getUserByEmailAddress(
					serviceContext.getCompanyId(),
					roleRecipient.getEmailAddress());
			}

			kaleoNotificationRecipient.setRecipientClassPK(user.getUserId());
		}
		else {
			AddressRecipient addressRecipient = (AddressRecipient)recipient;

			kaleoNotificationRecipient.setAddress(
				addressRecipient.getAddress());
		}
	}
}