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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.AddressRecipient;
import com.liferay.portal.workflow.kaleo.definition.Recipient;
import com.liferay.portal.workflow.kaleo.definition.RecipientType;
import com.liferay.portal.workflow.kaleo.definition.RoleRecipient;
import com.liferay.portal.workflow.kaleo.definition.UserRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.service.base.KaleoNotificationRecipientLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.RoleUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoNotificationRecipientLocalServiceImpl
	extends KaleoNotificationRecipientLocalServiceBaseImpl {

	public KaleoNotificationRecipient addKaleoNotificationRecipient(
			long kaleoDefinitionId, long kaleoNotificationId,
			Recipient recipient, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
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
		kaleoNotificationRecipient.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoNotificationRecipient.setKaleoNotificationId(kaleoNotificationId);

		setRecipient(kaleoNotificationRecipient, recipient, serviceContext);

		kaleoNotificationRecipientPersistence.update(
			kaleoNotificationRecipient, false);

		return kaleoNotificationRecipient;
	}

	public void deleteCompanyKaleoNotificationRecipients(long companyId)
		throws SystemException {

		kaleoNotificationRecipientPersistence.removeByCompanyId(companyId);
	}

	public void deleteKaleoDefinitionKaleoNotificationRecipients(
			long kaleoDefinitionId)
		throws SystemException {

		kaleoNotificationRecipientPersistence.removeByKaleoDefinitionId(
			kaleoDefinitionId);
	}

	public List<KaleoNotificationRecipient> getKaleoNotificationRecipients(
			long kaleoNotificationId)
		throws SystemException {

		return kaleoNotificationRecipientPersistence.findByKaleoNotificationId(
			kaleoNotificationId);
	}

	protected void setRecipient(
			KaleoNotificationRecipient kaleoNotificationRecipient,
			Recipient recipient, ServiceContext serviceContext)
		throws PortalException, SystemException {

		RecipientType recipientType = recipient.getRecipientType();

		if (recipientType.equals(RecipientType.ROLE)) {
			kaleoNotificationRecipient.setRecipientClassName(
				Role.class.getName());

			RoleRecipient roleRecipient = (RoleRecipient)recipient;

			int roleType = RoleUtil.getRoleType(roleRecipient.getRoleType());

			Role role = null;

			if (Validator.isNotNull(roleRecipient.getRoleName())) {
				role = RoleUtil.getRole(
					roleRecipient.getRoleName(), roleType,
					roleRecipient.isAutoCreate(), serviceContext);
			}
			else {
				role = roleLocalService.getRole(roleRecipient.getRoleId());
			}

			kaleoNotificationRecipient.setRecipientClassPK(role.getClassPK());
			kaleoNotificationRecipient.setRecipientRoleType(roleType);
		}
		else if (recipientType.equals(RecipientType.USER)) {
			kaleoNotificationRecipient.setRecipientClassName(
				User.class.getName());

			UserRecipient userRecipient = (UserRecipient)recipient;

			User user = null;

			if (userRecipient.getUserId() > 0) {
				user = userLocalService.getUser(userRecipient.getUserId());
			}
			else if (Validator.isNotNull(userRecipient.getScreenName())) {
				user = userLocalService.getUserByScreenName(
					serviceContext.getCompanyId(),
					userRecipient.getScreenName());
			}
			else if (Validator.isNotNull(userRecipient.getEmailAddress())) {
				user = userLocalService.getUserByEmailAddress(
					serviceContext.getCompanyId(),
					userRecipient.getEmailAddress());
			}

			if (user != null) {
				kaleoNotificationRecipient.setRecipientClassPK(
					user.getUserId());
			}
		}
		else {
			AddressRecipient addressRecipient = (AddressRecipient)recipient;

			kaleoNotificationRecipient.setAddress(
				addressRecipient.getAddress());
		}
	}

}