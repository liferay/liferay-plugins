/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.so.model.MemberRequest;

import java.util.List;

/**
 * <a href="MemberRequestUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MemberRequestUtil {
	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static MemberRequest remove(MemberRequest memberRequest)
		throws SystemException {
		return getPersistence().remove(memberRequest);
	}

	public static MemberRequest update(MemberRequest memberRequest,
		boolean merge) throws SystemException {
		return getPersistence().update(memberRequest, merge);
	}

	public static void cacheResult(
		com.liferay.so.model.MemberRequest memberRequest) {
		getPersistence().cacheResult(memberRequest);
	}

	public static void cacheResult(
		java.util.List<com.liferay.so.model.MemberRequest> memberRequests) {
		getPersistence().cacheResult(memberRequests);
	}

	public static com.liferay.so.model.MemberRequest create(
		long memberRequestId) {
		return getPersistence().create(memberRequestId);
	}

	public static com.liferay.so.model.MemberRequest remove(
		long memberRequestId)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().remove(memberRequestId);
	}

	public static com.liferay.so.model.MemberRequest updateImpl(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(memberRequest, merge);
	}

	public static com.liferay.so.model.MemberRequest findByPrimaryKey(
		long memberRequestId)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByPrimaryKey(memberRequestId);
	}

	public static com.liferay.so.model.MemberRequest fetchByPrimaryKey(
		long memberRequestId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(memberRequestId);
	}

	public static com.liferay.so.model.MemberRequest findByKey(
		java.lang.String key)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByKey(key);
	}

	public static com.liferay.so.model.MemberRequest fetchByKey(
		java.lang.String key) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByKey(key);
	}

	public static com.liferay.so.model.MemberRequest fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findByReceiverUserId(receiverUserId);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByReceiverUserId(receiverUserId, start, end);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByReceiverUserId(receiverUserId, start, end, obc);
	}

	public static com.liferay.so.model.MemberRequest findByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByReceiverUserId_First(receiverUserId, obc);
	}

	public static com.liferay.so.model.MemberRequest findByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByReceiverUserId_Last(receiverUserId, obc);
	}

	public static com.liferay.so.model.MemberRequest[] findByReceiverUserId_PrevAndNext(
		long memberRequestId, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence()
				   .findByReceiverUserId_PrevAndNext(memberRequestId,
			receiverUserId, obc);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByR_S(receiverUserId, status);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByR_S(receiverUserId, status, start, end);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByR_S(receiverUserId, status, start, end, obc);
	}

	public static com.liferay.so.model.MemberRequest findByR_S_First(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByR_S_First(receiverUserId, status, obc);
	}

	public static com.liferay.so.model.MemberRequest findByR_S_Last(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByR_S_Last(receiverUserId, status, obc);
	}

	public static com.liferay.so.model.MemberRequest[] findByR_S_PrevAndNext(
		long memberRequestId, long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence()
				   .findByR_S_PrevAndNext(memberRequestId, receiverUserId,
			status, obc);
	}

	public static com.liferay.so.model.MemberRequest findByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByG_R_S(groupId, receiverUserId, status);
	}

	public static com.liferay.so.model.MemberRequest fetchByG_R_S(
		long groupId, long receiverUserId, int status)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByG_R_S(groupId, receiverUserId, status);
	}

	public static com.liferay.so.model.MemberRequest fetchByG_R_S(
		long groupId, long receiverUserId, int status, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .fetchByG_R_S(groupId, receiverUserId, status,
			retrieveFromCache);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByKey(java.lang.String key)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		getPersistence().removeByKey(key);
	}

	public static void removeByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByReceiverUserId(receiverUserId);
	}

	public static void removeByR_S(long receiverUserId, int status)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByR_S(receiverUserId, status);
	}

	public static void removeByG_R_S(long groupId, long receiverUserId,
		int status)
		throws com.liferay.portal.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		getPersistence().removeByG_R_S(groupId, receiverUserId, status);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByKey(key);
	}

	public static int countByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByReceiverUserId(receiverUserId);
	}

	public static int countByR_S(long receiverUserId, int status)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByR_S(receiverUserId, status);
	}

	public static int countByG_R_S(long groupId, long receiverUserId, int status)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_R_S(groupId, receiverUserId, status);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static MemberRequestPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MemberRequestPersistence)PortletBeanLocatorUtil.locate(com.liferay.so.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					MemberRequestPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(MemberRequestPersistence persistence) {
		_persistence = persistence;
	}

	private static MemberRequestPersistence _persistence;
}