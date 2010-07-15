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

package com.liferay.so.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.so.model.MemberRequest;

/**
 * @author    Brian Wing Shun Chan
 * @see       MemberRequestPersistenceImpl
 * @see       MemberRequestUtil
 * @generated
 */
public interface MemberRequestPersistence extends BasePersistence<MemberRequest> {
	public void cacheResult(com.liferay.so.model.MemberRequest memberRequest);

	public void cacheResult(
		java.util.List<com.liferay.so.model.MemberRequest> memberRequests);

	public com.liferay.so.model.MemberRequest create(long memberRequestId);

	public com.liferay.so.model.MemberRequest remove(long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest updateImpl(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.so.model.MemberRequest findByPrimaryKey(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest fetchByPrimaryKey(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.so.model.MemberRequest findByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest fetchByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.so.model.MemberRequest fetchByKey(java.lang.String key,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.so.model.MemberRequest findByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest findByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest[] findByReceiverUserId_PrevAndNext(
		long memberRequestId, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.so.model.MemberRequest findByR_S_First(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest findByR_S_Last(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest[] findByR_S_PrevAndNext(
		long memberRequestId, long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest findByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest fetchByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.so.model.MemberRequest fetchByG_R_S(long groupId,
		long receiverUserId, int status, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public void removeByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByR_S(long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByG_R_S(long groupId, long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByR_S(long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_R_S(long groupId, long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}