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

import com.liferay.chat.model.Entry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * <a href="EntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       EntryPersistence
 * @see       EntryPersistenceImpl
 * @generated
 */
public class EntryUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Entry entry) {
		getPersistence().clearCache(entry);
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
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Entry remove(Entry entry) throws SystemException {
		return getPersistence().remove(entry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Entry update(Entry entry, boolean merge)
		throws SystemException {
		return getPersistence().update(entry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Entry update(Entry entry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(entry, merge, serviceContext);
	}

	public static void cacheResult(com.liferay.chat.model.Entry entry) {
		getPersistence().cacheResult(entry);
	}

	public static void cacheResult(
		java.util.List<com.liferay.chat.model.Entry> entries) {
		getPersistence().cacheResult(entries);
	}

	public static com.liferay.chat.model.Entry create(long entryId) {
		return getPersistence().create(entryId);
	}

	public static com.liferay.chat.model.Entry remove(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(entryId);
	}

	public static com.liferay.chat.model.Entry updateImpl(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(entry, merge);
	}

	public static com.liferay.chat.model.Entry findByPrimaryKey(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	public static com.liferay.chat.model.Entry fetchByPrimaryKey(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCreateDate(createDate);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCreateDate(createDate, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate(createDate, start, end, orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByCreateDate_First(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_First(createDate, orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByCreateDate_Last(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_Last(createDate, orderByComparator);
	}

	public static com.liferay.chat.model.Entry[] findByCreateDate_PrevAndNext(
		long entryId, long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_PrevAndNext(entryId, createDate,
			orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByFromUserId(fromUserId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByFromUserId(fromUserId, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFromUserId(fromUserId, start, end, orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByFromUserId_First(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFromUserId_First(fromUserId, orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByFromUserId_Last(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFromUserId_Last(fromUserId, orderByComparator);
	}

	public static com.liferay.chat.model.Entry[] findByFromUserId_PrevAndNext(
		long entryId, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFromUserId_PrevAndNext(entryId, fromUserId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToUserId(toUserId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToUserId(toUserId, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByToUserId(toUserId, start, end, orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByToUserId_First(
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToUserId_First(toUserId, orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByToUserId_Last(
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToUserId_Last(toUserId, orderByComparator);
	}

	public static com.liferay.chat.model.Entry[] findByToUserId_PrevAndNext(
		long entryId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByToUserId_PrevAndNext(entryId, toUserId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_F(createDate, fromUserId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_F(createDate, fromUserId, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F(createDate, fromUserId, start, end,
			orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByC_F_First(
		long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_First(createDate, fromUserId, orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByC_F_Last(long createDate,
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_Last(createDate, fromUserId, orderByComparator);
	}

	public static com.liferay.chat.model.Entry[] findByC_F_PrevAndNext(
		long entryId, long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_PrevAndNext(entryId, createDate, fromUserId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_T(createDate, toUserId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_T(createDate, toUserId, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_T(createDate, toUserId, start, end,
			orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByC_T_First(
		long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_T_First(createDate, toUserId, orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByC_T_Last(long createDate,
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_T_Last(createDate, toUserId, orderByComparator);
	}

	public static com.liferay.chat.model.Entry[] findByC_T_PrevAndNext(
		long entryId, long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_T_PrevAndNext(entryId, createDate, toUserId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_F_T(createDate, fromUserId, toUserId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T(createDate, fromUserId, toUserId, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T(createDate, fromUserId, toUserId, start, end,
			orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByC_F_T_First(
		long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T_First(createDate, fromUserId, toUserId,
			orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByC_F_T_Last(
		long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T_Last(createDate, fromUserId, toUserId,
			orderByComparator);
	}

	public static com.liferay.chat.model.Entry[] findByC_F_T_PrevAndNext(
		long entryId, long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T_PrevAndNext(entryId, createDate, fromUserId,
			toUserId, orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByF_T_C(fromUserId, toUserId, content);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C(fromUserId, toUserId, content, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C(fromUserId, toUserId, content, start, end,
			orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByF_T_C_First(
		long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C_First(fromUserId, toUserId, content,
			orderByComparator);
	}

	public static com.liferay.chat.model.Entry findByF_T_C_Last(
		long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C_Last(fromUserId, toUserId, content,
			orderByComparator);
	}

	public static com.liferay.chat.model.Entry[] findByF_T_C_PrevAndNext(
		long entryId, long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C_PrevAndNext(entryId, fromUserId, toUserId,
			content, orderByComparator);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.chat.model.Entry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByCreateDate(long createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCreateDate(createDate);
	}

	public static void removeByFromUserId(long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByFromUserId(fromUserId);
	}

	public static void removeByToUserId(long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByToUserId(toUserId);
	}

	public static void removeByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_F(createDate, fromUserId);
	}

	public static void removeByC_T(long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_T(createDate, toUserId);
	}

	public static void removeByC_F_T(long createDate, long fromUserId,
		long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_F_T(createDate, fromUserId, toUserId);
	}

	public static void removeByF_T_C(long fromUserId, long toUserId,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByF_T_C(fromUserId, toUserId, content);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCreateDate(long createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCreateDate(createDate);
	}

	public static int countByFromUserId(long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByFromUserId(fromUserId);
	}

	public static int countByToUserId(long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByToUserId(toUserId);
	}

	public static int countByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_F(createDate, fromUserId);
	}

	public static int countByC_T(long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_T(createDate, toUserId);
	}

	public static int countByC_F_T(long createDate, long fromUserId,
		long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_F_T(createDate, fromUserId, toUserId);
	}

	public static int countByF_T_C(long fromUserId, long toUserId,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByF_T_C(fromUserId, toUserId, content);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static EntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (EntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.chat.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					EntryPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(EntryPersistence persistence) {
		_persistence = persistence;
	}

	private static EntryPersistence _persistence;
}