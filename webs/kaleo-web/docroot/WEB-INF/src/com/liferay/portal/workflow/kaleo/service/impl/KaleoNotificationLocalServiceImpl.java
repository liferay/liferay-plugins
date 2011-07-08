/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.Notification;
import com.liferay.portal.workflow.kaleo.definition.NotificationType;
import com.liferay.portal.workflow.kaleo.definition.Recipient;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;
import com.liferay.portal.workflow.kaleo.service.base.KaleoNotificationLocalServiceBaseImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoNotificationLocalServiceImpl
	extends KaleoNotificationLocalServiceBaseImpl {

	public KaleoNotification addKaleoNotification(
			String kaleoClassName, long kaleoClassPK, long kaleoDefinitionId,
			String kaleoNodeName, Notification notification,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo notification

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoNotificationId = counterLocalService.increment();

		KaleoNotification kaleoNotification =
			kaleoNotificationPersistence.create(kaleoNotificationId);

		kaleoNotification.setCompanyId(user.getCompanyId());
		kaleoNotification.setUserId(user.getUserId());
		kaleoNotification.setUserName(user.getFullName());
		kaleoNotification.setCreateDate(now);
		kaleoNotification.setModifiedDate(now);
		kaleoNotification.setKaleoClassName(kaleoClassName);
		kaleoNotification.setKaleoClassPK(kaleoClassPK);
		kaleoNotification.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoNotification.setKaleoNodeName(kaleoNodeName);
		kaleoNotification.setName(notification.getName());
		kaleoNotification.setDescription(notification.getDescription());
		kaleoNotification.setExecutionType(
			notification.getExecutionType().getValue());
		kaleoNotification.setTemplate(notification.getTemplate());
		kaleoNotification.setTemplateLanguage(
			notification.getTemplateLanguage().getValue());

		Set<NotificationType> notificationTypes =
			notification.getNotificationTypes();

		if (!notificationTypes.isEmpty()) {
			StringBundler bundler = new StringBundler(
				notificationTypes.size() * 2);

			Iterator<NotificationType> itr = notificationTypes.iterator();

			while (itr.hasNext()) {
				NotificationType notificationType = itr.next();

				bundler.append(notificationType.getValue());

				if (itr.hasNext()) {
					bundler.append(StringPool.COMMA);
				}
			}

			kaleoNotification.setNotificationTypes(bundler.toString());
		}

		kaleoNotificationPersistence.update(kaleoNotification, false);

		// Kaleo notification recipients

		Set<Recipient> recipients = notification.getRecipients();

		for (Recipient recipient : recipients) {
			kaleoNotificationRecipientLocalService.
				addKaleoNotificationRecipient(
					kaleoDefinitionId, kaleoNotificationId,
					recipient, serviceContext);
		}

		return kaleoNotification;
	}

	public void deleteCompanyKaleoNotifications(long companyId)
		throws SystemException {

		// Kaleo notifications

		kaleoNotificationPersistence.removeByCompanyId(
			companyId);

		// Kaleo notification recipients

		kaleoNotificationRecipientLocalService.
			deleteCompanyKaleoNotificationRecipients(companyId);
	}

	public void deleteKaleoDefinitionKaleoNotifications(long kaleoDefinitionId)
		throws SystemException {

		// Kaleo notifications

		kaleoNotificationPersistence.removeByKaleoDefinitionId(
			kaleoDefinitionId);

		// Kaleo notification recipients

		kaleoNotificationRecipientLocalService.
			deleteKaleoDefinitionKaleoNotificationRecipients(kaleoDefinitionId);
	}

	public List<KaleoNotification> getKaleoNotifications(
			String kaleoClassName, long kaleoClassPK, String executionType)
		throws SystemException {

		return kaleoNotificationPersistence.findByKCN_KCPK_ET(
			kaleoClassName, kaleoClassPK, executionType);
	}

}