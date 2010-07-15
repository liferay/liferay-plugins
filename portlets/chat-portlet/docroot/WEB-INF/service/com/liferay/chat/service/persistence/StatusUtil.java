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

package com.liferay.chat.service.persistence;

import com.liferay.chat.model.Status;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       StatusPersistence
 * @see       StatusPersistenceImpl
 * @generated
 */
public class StatusUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Status status) {
		getPersistence().clearCache(status);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Status> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Status> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Status> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Status remove(Status status) throws SystemException {
		return getPersistence().remove(status);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Status update(Status status, boolean merge)
		throws SystemException {
		return getPersistence().update(status, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Status update(Status status, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(status, merge, serviceContext);
	}

	public static void cacheResult(com.liferay.chat.model.Status status) {
		getPersistence().cacheResult(status);
	}

	public static void cacheResult(
		java.util.List<com.liferay.chat.model.Status> statuses) {
		getPersistence().cacheResult(statuses);
	}

	public static com.liferay.chat.model.Status create(long statusId) {
		return getPersistence().create(statusId);
	}

	public static com.liferay.chat.model.Status remove(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(statusId);
	}

	public static com.liferay.chat.model.Status updateImpl(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(status, merge);
	}

	public static com.liferay.chat.model.Status findByPrimaryKey(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(statusId);
	}

	public static com.liferay.chat.model.Status fetchByPrimaryKey(long statusId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(statusId);
	}

	public static com.liferay.chat.model.Status findByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static com.liferay.chat.model.Status fetchByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId(userId);
	}

	public static com.liferay.chat.model.Status fetchByUserId(long userId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId(userId, retrieveFromCache);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByModifiedDate(modifiedDate);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByModifiedDate(modifiedDate, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByModifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

	public static com.liferay.chat.model.Status findByModifiedDate_First(
		long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByModifiedDate_First(modifiedDate, orderByComparator);
	}

	public static com.liferay.chat.model.Status findByModifiedDate_Last(
		long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByModifiedDate_Last(modifiedDate, orderByComparator);
	}

	public static com.liferay.chat.model.Status[] findByModifiedDate_PrevAndNext(
		long statusId, long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByModifiedDate_PrevAndNext(statusId, modifiedDate,
			orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOnline(online);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOnline(online, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOnline(online, start, end, orderByComparator);
	}

	public static com.liferay.chat.model.Status findByOnline_First(
		boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOnline_First(online, orderByComparator);
	}

	public static com.liferay.chat.model.Status findByOnline_Last(
		boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOnline_Last(online, orderByComparator);
	}

	public static com.liferay.chat.model.Status[] findByOnline_PrevAndNext(
		long statusId, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOnline_PrevAndNext(statusId, online, orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByM_O(modifiedDate, online);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByM_O(modifiedDate, online, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_O(modifiedDate, online, start, end,
			orderByComparator);
	}

	public static com.liferay.chat.model.Status findByM_O_First(
		long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_O_First(modifiedDate, online, orderByComparator);
	}

	public static com.liferay.chat.model.Status findByM_O_Last(
		long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_O_Last(modifiedDate, online, orderByComparator);
	}

	public static com.liferay.chat.model.Status[] findByM_O_PrevAndNext(
		long statusId, long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_O_PrevAndNext(statusId, modifiedDate, online,
			orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Status> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.chat.model.Status> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByModifiedDate(long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByModifiedDate(modifiedDate);
	}

	public static void removeByOnline(boolean online)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOnline(online);
	}

	public static void removeByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByM_O(modifiedDate, online);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByModifiedDate(long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByModifiedDate(modifiedDate);
	}

	public static int countByOnline(boolean online)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOnline(online);
	}

	public static int countByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByM_O(modifiedDate, online);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static StatusPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (StatusPersistence)PortletBeanLocatorUtil.locate(com.liferay.chat.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					StatusPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(StatusPersistence persistence) {
		_persistence = persistence;
	}

	private static StatusPersistence _persistence;
}