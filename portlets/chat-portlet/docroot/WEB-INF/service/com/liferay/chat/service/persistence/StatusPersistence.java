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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * @author    Brian Wing Shun Chan
 * @see       StatusPersistenceImpl
 * @see       StatusUtil
 * @generated
 */
public interface StatusPersistence extends BasePersistence<Status> {
	public void cacheResult(com.liferay.chat.model.Status status);

	public void cacheResult(
		java.util.List<com.liferay.chat.model.Status> statuses);

	public com.liferay.chat.model.Status create(long statusId);

	public com.liferay.chat.model.Status remove(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status updateImpl(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status findByPrimaryKey(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status fetchByPrimaryKey(long statusId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status findByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status fetchByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status fetchByUserId(long userId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status findByModifiedDate_First(
		long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status findByModifiedDate_Last(
		long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status[] findByModifiedDate_PrevAndNext(
		long statusId, long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status findByOnline_First(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status findByOnline_Last(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status[] findByOnline_PrevAndNext(
		long statusId, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status findByM_O_First(long modifiedDate,
		boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status findByM_O_Last(long modifiedDate,
		boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status[] findByM_O_PrevAndNext(
		long statusId, long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public void removeByModifiedDate(long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByOnline(boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByModifiedDate(long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByOnline(boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}