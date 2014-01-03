/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.so.model.MemberRequest;

import java.util.List;

/**
 * The persistence utility for the member request service. This utility wraps {@link MemberRequestPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberRequestPersistence
 * @see MemberRequestPersistenceImpl
 * @generated
 */
public class MemberRequestUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(MemberRequest memberRequest) {
		getPersistence().clearCache(memberRequest);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MemberRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MemberRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MemberRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static MemberRequest update(MemberRequest memberRequest)
		throws SystemException {
		return getPersistence().update(memberRequest);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static MemberRequest update(MemberRequest memberRequest,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(memberRequest, serviceContext);
	}

	/**
	* Returns the member request where key = &#63; or throws a {@link com.liferay.so.NoSuchMemberRequestException} if it could not be found.
	*
	* @param key the key
	* @return the matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest findByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByKey(key);
	}

	/**
	* Returns the member request where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Returns the member request where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Removes the member request where key = &#63; from the database.
	*
	* @param key the key
	* @return the member request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest removeByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().removeByKey(key);
	}

	/**
	* Returns the number of member requests where key = &#63;.
	*
	* @param key the key
	* @return the number of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKey(key);
	}

	/**
	* Returns all the member requests where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @return the matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByReceiverUserId(receiverUserId);
	}

	/**
	* Returns a range of all the member requests where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByReceiverUserId(receiverUserId, start, end);
	}

	/**
	* Returns an ordered range of all the member requests where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByReceiverUserId(receiverUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest findByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence()
				   .findByReceiverUserId_First(receiverUserId, orderByComparator);
	}

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByReceiverUserId_First(receiverUserId,
			orderByComparator);
	}

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest findByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence()
				   .findByReceiverUserId_Last(receiverUserId, orderByComparator);
	}

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByReceiverUserId_Last(receiverUserId, orderByComparator);
	}

	/**
	* Returns the member requests before and after the current member request in the ordered set where receiverUserId = &#63;.
	*
	* @param memberRequestId the primary key of the current member request
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest[] findByReceiverUserId_PrevAndNext(
		long memberRequestId, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence()
				   .findByReceiverUserId_PrevAndNext(memberRequestId,
			receiverUserId, orderByComparator);
	}

	/**
	* Removes all the member requests where receiverUserId = &#63; from the database.
	*
	* @param receiverUserId the receiver user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByReceiverUserId(receiverUserId);
	}

	/**
	* Returns the number of member requests where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @return the number of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByReceiverUserId(receiverUserId);
	}

	/**
	* Returns all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(receiverUserId, status);
	}

	/**
	* Returns a range of all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(receiverUserId, status, start, end);
	}

	/**
	* Returns an ordered range of all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S(receiverUserId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest findByR_S_First(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence()
				   .findByR_S_First(receiverUserId, status, orderByComparator);
	}

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByR_S_First(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_S_First(receiverUserId, status, orderByComparator);
	}

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest findByR_S_Last(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence()
				   .findByR_S_Last(receiverUserId, status, orderByComparator);
	}

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByR_S_Last(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_S_Last(receiverUserId, status, orderByComparator);
	}

	/**
	* Returns the member requests before and after the current member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param memberRequestId the primary key of the current member request
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest[] findByR_S_PrevAndNext(
		long memberRequestId, long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence()
				   .findByR_S_PrevAndNext(memberRequestId, receiverUserId,
			status, orderByComparator);
	}

	/**
	* Removes all the member requests where receiverUserId = &#63; and status = &#63; from the database.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_S(long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_S(receiverUserId, status);
	}

	/**
	* Returns the number of member requests where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the number of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_S(long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_S(receiverUserId, status);
	}

	/**
	* Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or throws a {@link com.liferay.so.NoSuchMemberRequestException} if it could not be found.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest findByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByG_R_S(groupId, receiverUserId, status);
	}

	/**
	* Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByG_R_S(
		long groupId, long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_R_S(groupId, receiverUserId, status);
	}

	/**
	* Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByG_R_S(
		long groupId, long receiverUserId, int status, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_R_S(groupId, receiverUserId, status,
			retrieveFromCache);
	}

	/**
	* Removes the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the member request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest removeByG_R_S(
		long groupId, long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().removeByG_R_S(groupId, receiverUserId, status);
	}

	/**
	* Returns the number of member requests where groupId = &#63; and receiverUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the number of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_R_S(long groupId, long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_R_S(groupId, receiverUserId, status);
	}

	/**
	* Caches the member request in the entity cache if it is enabled.
	*
	* @param memberRequest the member request
	*/
	public static void cacheResult(
		com.liferay.so.model.MemberRequest memberRequest) {
		getPersistence().cacheResult(memberRequest);
	}

	/**
	* Caches the member requests in the entity cache if it is enabled.
	*
	* @param memberRequests the member requests
	*/
	public static void cacheResult(
		java.util.List<com.liferay.so.model.MemberRequest> memberRequests) {
		getPersistence().cacheResult(memberRequests);
	}

	/**
	* Creates a new member request with the primary key. Does not add the member request to the database.
	*
	* @param memberRequestId the primary key for the new member request
	* @return the new member request
	*/
	public static com.liferay.so.model.MemberRequest create(
		long memberRequestId) {
		return getPersistence().create(memberRequestId);
	}

	/**
	* Removes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request that was removed
	* @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest remove(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().remove(memberRequestId);
	}

	public static com.liferay.so.model.MemberRequest updateImpl(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(memberRequest);
	}

	/**
	* Returns the member request with the primary key or throws a {@link com.liferay.so.NoSuchMemberRequestException} if it could not be found.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest findByPrimaryKey(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException {
		return getPersistence().findByPrimaryKey(memberRequestId);
	}

	/**
	* Returns the member request with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request, or <code>null</code> if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest fetchByPrimaryKey(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(memberRequestId);
	}

	/**
	* Returns all the member requests.
	*
	* @return the member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the member requests from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of member requests.
	*
	* @return the number of member requests
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MemberRequestPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MemberRequestPersistence)PortletBeanLocatorUtil.locate(com.liferay.so.service.ClpSerializer.getServletContextName(),
					MemberRequestPersistence.class.getName());

			ReferenceRegistry.registerReference(MemberRequestUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(MemberRequestPersistence persistence) {
	}

	private static MemberRequestPersistence _persistence;
}