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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;

import java.util.List;

/**
 * <a href="KaleoNotificationRecipientUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipientPersistence
 * @see       KaleoNotificationRecipientPersistenceImpl
 * @generated
 */
public class KaleoNotificationRecipientUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoNotificationRecipient remove(
		KaleoNotificationRecipient kaleoNotificationRecipient)
		throws SystemException {
		return getPersistence().remove(kaleoNotificationRecipient);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoNotificationRecipient update(
		KaleoNotificationRecipient kaleoNotificationRecipient, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoNotificationRecipient, merge);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient) {
		getPersistence().cacheResult(kaleoNotificationRecipient);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> kaleoNotificationRecipients) {
		getPersistence().cacheResult(kaleoNotificationRecipients);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient create(
		long kaleoNotificationRecipientId) {
		return getPersistence().create(kaleoNotificationRecipientId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient remove(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence().remove(kaleoNotificationRecipientId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoNotificationRecipient, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByPrimaryKey(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence().findByPrimaryKey(kaleoNotificationRecipientId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient fetchByPrimaryKey(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoNotificationRecipientId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoNotificationId(kaleoNotificationId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoNotificationId(kaleoNotificationId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoNotificationId(kaleoNotificationId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByKaleoNotificationId_First(
		long kaleoNotificationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoNotificationId_First(kaleoNotificationId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByKaleoNotificationId_Last(
		long kaleoNotificationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoNotificationId_Last(kaleoNotificationId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient[] findByKaleoNotificationId_PrevAndNext(
		long kaleoNotificationRecipientId, long kaleoNotificationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoNotificationId_PrevAndNext(kaleoNotificationRecipientId,
			kaleoNotificationId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByKaleoNotificationId(long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoNotificationId(kaleoNotificationId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByKaleoNotificationId(long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoNotificationId(kaleoNotificationId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoNotificationRecipientPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoNotificationRecipientPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoNotificationRecipientPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(
		KaleoNotificationRecipientPersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoNotificationRecipientPersistence _persistence;
}