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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.AddressRecipient;
import com.liferay.portal.workflow.kaleo.definition.Recipient;
import com.liferay.portal.workflow.kaleo.definition.RecipientType;
import com.liferay.portal.workflow.kaleo.definition.RoleRecipient;
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.definition.ScriptRecipient;
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

	@Override
	public KaleoNotificationRecipient addKaleoNotificationRecipient(
			long kaleoDefinitionId, long kaleoNotificationId,
			Recipient recipient, ServiceContext serviceContext)
		throws PortalException {

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
		kaleoNotificationRecipient.setNotificationReceptionType(
			recipient.getNotificationReceptionType().getValue());

		setRecipient(kaleoNotificationRecipient, recipient, serviceContext);

		kaleoNotificationRecipientPersistence.update(
			kaleoNotificationRecipient);

		return kaleoNotificationRecipient;
	}

	@Override
	public void deleteCompanyKaleoNotificationRecipients(long companyId) {
		kaleoNotificationRecipientPersistence.removeByCompanyId(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoNotificationRecipients(
		long kaleoDefinitionId) {

		kaleoNotificationRecipientPersistence.removeByKaleoDefinitionId(
			kaleoDefinitionId);
	}

	@Override
	public List<KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long kaleoNotificationId) {

		return kaleoNotificationRecipientPersistence.findByKaleoNotificationId(
			kaleoNotificationId);
	}

	protected void setRecipient(
			KaleoNotificationRecipient kaleoNotificationRecipient,
			Recipient recipient, ServiceContext serviceContext)
		throws PortalException {

		RecipientType recipientType = recipient.getRecipientType();

		kaleoNotificationRecipient.setRecipientClassName(
			recipientType.getValue());

		if (recipientType.equals(RecipientType.ADDRESS)) {
			AddressRecipient addressRecipient = (AddressRecipient)recipient;

			kaleoNotificationRecipient.setAddress(
				addressRecipient.getAddress());
		}
		else if (recipientType.equals(RecipientType.ROLE)) {
			RoleRecipient roleRecipient = (RoleRecipient)recipient;

			int roleType = 0;

			Role role = null;

			if (Validator.isNotNull(roleRecipient.getRoleName())) {
				roleType = RoleUtil.getRoleType(roleRecipient.getRoleType());

				role = RoleUtil.getRole(
					roleRecipient.getRoleName(), roleType,
					roleRecipient.isAutoCreate(), serviceContext);
			}
			else {
				role = roleLocalService.getRole(roleRecipient.getRoleId());

				roleType = role.getType();
			}

			kaleoNotificationRecipient.setRecipientClassPK(role.getClassPK());
			kaleoNotificationRecipient.setRecipientRoleType(roleType);
		}
		else if (recipientType.equals(RecipientType.SCRIPT)) {
			ScriptRecipient scriptRecipient = (ScriptRecipient)recipient;

			kaleoNotificationRecipient.setRecipientScript(
				scriptRecipient.getScript());

			ScriptLanguage scriptLanguage = scriptRecipient.getScriptLanguage();

			kaleoNotificationRecipient.setRecipientScriptLanguage(
				scriptLanguage.getValue());

			kaleoNotificationRecipient.setRecipientScriptRequiredContexts(
				scriptRecipient.getScriptRequiredContexts());
		}
		else if (recipientType.equals(RecipientType.USER)) {
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
	}

}