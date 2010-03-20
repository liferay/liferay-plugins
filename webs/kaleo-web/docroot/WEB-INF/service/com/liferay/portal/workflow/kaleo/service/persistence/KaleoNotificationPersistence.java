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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;

/**
 * <a href="KaleoNotificationPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationPersistenceImpl
 * @see       KaleoNotificationUtil
 * @generated
 */
public interface KaleoNotificationPersistence extends BasePersistence<KaleoNotification> {
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification);

	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> kaleoNotifications);

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification create(
		long kaleoNotificationId);

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification remove(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification findByPrimaryKey(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification fetchByPrimaryKey(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKNI(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKNI(
		long kaleoNodeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKNI(
		long kaleoNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification findByKNI_First(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification findByKNI_Last(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification[] findByKNI_PrevAndNext(
		long kaleoNotificationId, long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKNI(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKNI(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int getKaleoNotificationRecipientsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean containsKaleoNotificationRecipient(long pk,
		long kaleoNotificationRecipientPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean containsKaleoNotificationRecipients(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;
}