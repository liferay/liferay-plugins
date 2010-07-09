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

package com.liferay.portal.workflow.kaleo.service;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoNotificationLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationLocalService
 * @generated
 */
public class KaleoNotificationLocalServiceWrapper
	implements KaleoNotificationLocalService {
	public KaleoNotificationLocalServiceWrapper(
		KaleoNotificationLocalService kaleoNotificationLocalService) {
		_kaleoNotificationLocalService = kaleoNotificationLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification addKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.addKaleoNotification(kaleoNotification);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification createKaleoNotification(
		long kaleoNotificationId) {
		return _kaleoNotificationLocalService.createKaleoNotification(kaleoNotificationId);
	}

	public void deleteKaleoNotification(long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteKaleoNotification(kaleoNotificationId);
	}

	public void deleteKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteKaleoNotification(kaleoNotification);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification getKaleoNotification(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotification(kaleoNotificationId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotifications(start, end);
	}

	public int getKaleoNotificationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotificationsCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification updateKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.updateKaleoNotification(kaleoNotification);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification updateKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.updateKaleoNotification(kaleoNotification,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification addKaleoNotification(
		long kaleoDefinitionId, long kaleoNodeId,
		java.lang.String kaleoNodeName,
		com.liferay.portal.workflow.kaleo.definition.Notification notification,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.addKaleoNotification(kaleoDefinitionId,
			kaleoNodeId, kaleoNodeName, notification, serviceContext);
	}

	public void deleteCompanyKaleoNotifications(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteCompanyKaleoNotifications(companyId);
	}

	public void deleteKaleoDefinitionKaleoNotifications(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteKaleoDefinitionKaleoNotifications(kaleoDefinitionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		long kaleoNodeId, java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotifications(kaleoNodeId,
			executionType);
	}

	public KaleoNotificationLocalService getWrappedKaleoNotificationLocalService() {
		return _kaleoNotificationLocalService;
	}

	private KaleoNotificationLocalService _kaleoNotificationLocalService;
}