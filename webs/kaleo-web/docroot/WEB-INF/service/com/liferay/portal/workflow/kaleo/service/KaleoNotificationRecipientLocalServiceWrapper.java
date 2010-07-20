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
 * This class is a wrapper for {@link KaleoNotificationRecipientLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipientLocalService
 * @generated
 */
public class KaleoNotificationRecipientLocalServiceWrapper
	implements KaleoNotificationRecipientLocalService {
	public KaleoNotificationRecipientLocalServiceWrapper(
		KaleoNotificationRecipientLocalService kaleoNotificationRecipientLocalService) {
		_kaleoNotificationRecipientLocalService = kaleoNotificationRecipientLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.addKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient createKaleoNotificationRecipient(
		long kaleoNotificationRecipientId) {
		return _kaleoNotificationRecipientLocalService.createKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	public void deleteKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipientLocalService.deleteKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	public void deleteKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipientLocalService.deleteKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient getKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.getKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.getKaleoNotificationRecipients(start,
			end);
	}

	public int getKaleoNotificationRecipientsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.getKaleoNotificationRecipientsCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.updateKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.updateKaleoNotificationRecipient(kaleoNotificationRecipient,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		long kaleoDefinitionId, long kaleoNotificationId,
		com.liferay.portal.workflow.kaleo.definition.Recipient recipient,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.addKaleoNotificationRecipient(kaleoDefinitionId,
			kaleoNotificationId, recipient, serviceContext);
	}

	public void deleteCompanyKaleoNotificationRecipients(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipientLocalService.deleteCompanyKaleoNotificationRecipients(companyId);
	}

	public void deleteKaleoDefinitionKaleoNotificationRecipients(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipientLocalService.deleteKaleoDefinitionKaleoNotificationRecipients(kaleoDefinitionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.getKaleoNotificationRecipients(kaleoNotificationId);
	}

	public KaleoNotificationRecipientLocalService getWrappedKaleoNotificationRecipientLocalService() {
		return _kaleoNotificationRecipientLocalService;
	}

	private KaleoNotificationRecipientLocalService _kaleoNotificationRecipientLocalService;
}