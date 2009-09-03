/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.so.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="MemberRequestPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface MemberRequestPersistence extends BasePersistence {
	public void cacheResult(com.liferay.so.model.MemberRequest memberRequest);

	public void cacheResult(
		java.util.List<com.liferay.so.model.MemberRequest> memberRequests);

	public void clearCache();

	public com.liferay.so.model.MemberRequest create(long memberRequestId);

	public com.liferay.so.model.MemberRequest remove(long memberRequestId)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest remove(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest update(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest update(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest updateImpl(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest findByPrimaryKey(
		long memberRequestId)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest fetchByPrimaryKey(
		long memberRequestId) throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest findByKey(java.lang.String key)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest fetchByKey(java.lang.String key)
		throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest fetchByKey(java.lang.String key,
		boolean retrieveFromCache) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest findByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest findByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest[] findByReceiverUserId_PrevAndNext(
		long memberRequestId, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest findByR_S_First(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest findByR_S_Last(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest[] findByR_S_PrevAndNext(
		long memberRequestId, long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest findByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest fetchByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.SystemException;

	public com.liferay.so.model.MemberRequest fetchByG_R_S(long groupId,
		long receiverUserId, int status, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByKey(java.lang.String key)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public void removeByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.SystemException;

	public void removeByR_S(long receiverUserId, int status)
		throws com.liferay.portal.SystemException;

	public void removeByG_R_S(long groupId, long receiverUserId, int status)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByKey(java.lang.String key)
		throws com.liferay.portal.SystemException;

	public int countByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.SystemException;

	public int countByR_S(long receiverUserId, int status)
		throws com.liferay.portal.SystemException;

	public int countByG_R_S(long groupId, long receiverUserId, int status)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}