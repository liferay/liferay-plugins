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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       EntryPersistenceImpl
 * @see       EntryUtil
 * @generated
 */
public interface EntryPersistence extends BasePersistence<Entry> {
	public void cacheResult(com.liferay.chat.model.Entry entry);

	public void cacheResult(
		java.util.List<com.liferay.chat.model.Entry> entries);

	public com.liferay.chat.model.Entry create(long entryId);

	public com.liferay.chat.model.Entry remove(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry updateImpl(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByPrimaryKey(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry fetchByPrimaryKey(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByCreateDate_First(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByCreateDate_Last(long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry[] findByCreateDate_PrevAndNext(
		long entryId, long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByFromUserId_First(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByFromUserId_Last(long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry[] findByFromUserId_PrevAndNext(
		long entryId, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByToUserId_First(long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByToUserId_Last(long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry[] findByToUserId_PrevAndNext(
		long entryId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByC_F_First(long createDate,
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByC_F_Last(long createDate,
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry[] findByC_F_PrevAndNext(long entryId,
		long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByC_T_First(long createDate,
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByC_T_Last(long createDate,
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry[] findByC_T_PrevAndNext(long entryId,
		long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByC_F_T_First(long createDate,
		long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByC_F_T_Last(long createDate,
		long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry[] findByC_F_T_PrevAndNext(
		long entryId, long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByF_T_C_First(long fromUserId,
		long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry findByF_T_C_Last(long fromUserId,
		long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry[] findByF_T_C_PrevAndNext(
		long entryId, long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByCreateDate(long createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByFromUserId(long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByToUserId(long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByC_T(long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByC_F_T(long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByF_T_C(long fromUserId, long toUserId,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCreateDate(long createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByFromUserId(long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByToUserId(long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByC_T(long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByC_F_T(long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByF_T_C(long fromUserId, long toUserId,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}