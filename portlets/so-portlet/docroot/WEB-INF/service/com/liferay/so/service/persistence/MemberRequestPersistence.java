/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
 * The persistence interface for the member request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberRequestPersistenceImpl
 * @see MemberRequestUtil
 * @generated
 */
public interface MemberRequestPersistence extends BasePersistence<MemberRequest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MemberRequestUtil} to access the member request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the member request in the entity cache if it is enabled.
	*
	* @param memberRequest the member request
	*/
	public void cacheResult(com.liferay.so.model.MemberRequest memberRequest);

	/**
	* Caches the member requests in the entity cache if it is enabled.
	*
	* @param memberRequests the member requests
	*/
	public void cacheResult(
		java.util.List<com.liferay.so.model.MemberRequest> memberRequests);

	/**
	* Creates a new member request with the primary key. Does not add the member request to the database.
	*
	* @param memberRequestId the primary key for the new member request
	* @return the new member request
	*/
	public com.liferay.so.model.MemberRequest create(long memberRequestId);

	/**
	* Removes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request that was removed
	* @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest remove(long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	public com.liferay.so.model.MemberRequest updateImpl(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the member request with the primary key or throws a {@link com.liferay.so.NoSuchMemberRequestException} if it could not be found.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest findByPrimaryKey(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the member request with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request, or <code>null</code> if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest fetchByPrimaryKey(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the member request where key = &#63; or throws a {@link com.liferay.so.NoSuchMemberRequestException} if it could not be found.
	*
	* @param key the key
	* @return the matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest findByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the member request where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest fetchByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the member request where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest fetchByKey(java.lang.String key,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the member requests where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @return the matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the member requests where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the member requests where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest findByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest fetchByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request
	* @throws com.liferay.so.NoSuchMemberRequestException if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest findByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest fetchByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.so.model.MemberRequest[] findByReceiverUserId_PrevAndNext(
		long memberRequestId, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	public java.util.List<com.liferay.so.model.MemberRequest> findByR_S(
		long receiverUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.so.model.MemberRequest findByR_S_First(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest fetchByR_S_First(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.so.model.MemberRequest findByR_S_Last(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest fetchByR_S_Last(
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.so.model.MemberRequest[] findByR_S_PrevAndNext(
		long memberRequestId, long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

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
	public com.liferay.so.model.MemberRequest findByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest fetchByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.so.model.MemberRequest fetchByG_R_S(long groupId,
		long receiverUserId, int status, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the member requests.
	*
	* @return the member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the member request where key = &#63; from the database.
	*
	* @param key the key
	* @return the member request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest removeByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Removes all the member requests where receiverUserId = &#63; from the database.
	*
	* @param receiverUserId the receiver user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the member requests where receiverUserId = &#63; and status = &#63; from the database.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByR_S(long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the member request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest removeByG_R_S(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchMemberRequestException;

	/**
	* Removes all the member requests from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of member requests where key = &#63;.
	*
	* @param key the key
	* @return the number of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of member requests where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @return the number of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of member requests where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the number of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_S(long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of member requests where groupId = &#63; and receiverUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the number of matching member requests
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_R_S(long groupId, long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of member requests.
	*
	* @return the number of member requests
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}