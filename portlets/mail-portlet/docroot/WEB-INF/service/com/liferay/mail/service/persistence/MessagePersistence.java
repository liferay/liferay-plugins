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

package com.liferay.mail.service.persistence;

import com.liferay.mail.model.Message;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * @author    Brian Wing Shun Chan
 * @see       MessagePersistenceImpl
 * @see       MessageUtil
 * @generated
 */
public interface MessagePersistence extends BasePersistence<Message> {
	public void cacheResult(com.liferay.mail.model.Message message);

	public void cacheResult(
		java.util.List<com.liferay.mail.model.Message> messages);

	public com.liferay.mail.model.Message create(long messageId);

	public com.liferay.mail.model.Message remove(long messageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message updateImpl(
		com.liferay.mail.model.Message message, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message findByPrimaryKey(long messageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message fetchByPrimaryKey(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message[] findByCompanyId_PrevAndNext(
		long messageId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message findByFolderId_First(long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message findByFolderId_Last(long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message[] findByFolderId_PrevAndNext(
		long messageId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message findByF_R(long folderId,
		long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message fetchByF_R(long folderId,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message fetchByF_R(long folderId,
		long remoteMessageId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByF_R(long folderId, long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByF_R(long folderId, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}