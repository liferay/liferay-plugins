/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.so.service.persistence.impl.MemberRequestPersistenceImpl
 * @see MemberRequestUtil
 * @generated
 */
@ProviderType
public interface MemberRequestPersistence extends BasePersistence<MemberRequest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MemberRequestUtil} to access the member request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the member request where key = &#63; or throws a {@link NoSuchMemberRequestException} if it could not be found.
	*
	* @param key the key
	* @return the matching member request
	* @throws NoSuchMemberRequestException if a matching member request could not be found
	*/
	public MemberRequest findByKey(java.lang.String key)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the member request where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	*/
	public MemberRequest fetchByKey(java.lang.String key);

	/**
	* Returns the member request where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	*/
	public MemberRequest fetchByKey(java.lang.String key,
		boolean retrieveFromCache);

	/**
	* Removes the member request where key = &#63; from the database.
	*
	* @param key the key
	* @return the member request that was removed
	*/
	public MemberRequest removeByKey(java.lang.String key)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the number of member requests where key = &#63;.
	*
	* @param key the key
	* @return the number of matching member requests
	*/
	public int countByKey(java.lang.String key);

	/**
	* Returns all the member requests where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @return the matching member requests
	*/
	public java.util.List<MemberRequest> findByReceiverUserId(
		long receiverUserId);

	/**
	* Returns a range of all the member requests where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of matching member requests
	*/
	public java.util.List<MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end);

	/**
	* Returns an ordered range of all the member requests where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching member requests
	*/
	public java.util.List<MemberRequest> findByReceiverUserId(
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator);

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request
	* @throws NoSuchMemberRequestException if a matching member request could not be found
	*/
	public MemberRequest findByReceiverUserId_First(long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request, or <code>null</code> if a matching member request could not be found
	*/
	public MemberRequest fetchByReceiverUserId_First(long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator);

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request
	* @throws NoSuchMemberRequestException if a matching member request could not be found
	*/
	public MemberRequest findByReceiverUserId_Last(long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request, or <code>null</code> if a matching member request could not be found
	*/
	public MemberRequest fetchByReceiverUserId_Last(long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator);

	/**
	* Returns the member requests before and after the current member request in the ordered set where receiverUserId = &#63;.
	*
	* @param memberRequestId the primary key of the current member request
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next member request
	* @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	*/
	public MemberRequest[] findByReceiverUserId_PrevAndNext(
		long memberRequestId, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Removes all the member requests where receiverUserId = &#63; from the database.
	*
	* @param receiverUserId the receiver user ID
	*/
	public void removeByReceiverUserId(long receiverUserId);

	/**
	* Returns the number of member requests where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @return the number of matching member requests
	*/
	public int countByReceiverUserId(long receiverUserId);

	/**
	* Returns all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the matching member requests
	*/
	public java.util.List<MemberRequest> findByR_S(long receiverUserId,
		int status);

	/**
	* Returns a range of all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of matching member requests
	*/
	public java.util.List<MemberRequest> findByR_S(long receiverUserId,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the member requests where receiverUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching member requests
	*/
	public java.util.List<MemberRequest> findByR_S(long receiverUserId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator);

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request
	* @throws NoSuchMemberRequestException if a matching member request could not be found
	*/
	public MemberRequest findByR_S_First(long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the first member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member request, or <code>null</code> if a matching member request could not be found
	*/
	public MemberRequest fetchByR_S_First(long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator);

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request
	* @throws NoSuchMemberRequestException if a matching member request could not be found
	*/
	public MemberRequest findByR_S_Last(long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the last member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member request, or <code>null</code> if a matching member request could not be found
	*/
	public MemberRequest fetchByR_S_Last(long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator);

	/**
	* Returns the member requests before and after the current member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	*
	* @param memberRequestId the primary key of the current member request
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next member request
	* @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	*/
	public MemberRequest[] findByR_S_PrevAndNext(long memberRequestId,
		long receiverUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Removes all the member requests where receiverUserId = &#63; and status = &#63; from the database.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	*/
	public void removeByR_S(long receiverUserId, int status);

	/**
	* Returns the number of member requests where receiverUserId = &#63; and status = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the number of matching member requests
	*/
	public int countByR_S(long receiverUserId, int status);

	/**
	* Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or throws a {@link NoSuchMemberRequestException} if it could not be found.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the matching member request
	* @throws NoSuchMemberRequestException if a matching member request could not be found
	*/
	public MemberRequest findByG_R_S(long groupId, long receiverUserId,
		int status) throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	*/
	public MemberRequest fetchByG_R_S(long groupId, long receiverUserId,
		int status);

	/**
	* Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching member request, or <code>null</code> if a matching member request could not be found
	*/
	public MemberRequest fetchByG_R_S(long groupId, long receiverUserId,
		int status, boolean retrieveFromCache);

	/**
	* Removes the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the member request that was removed
	*/
	public MemberRequest removeByG_R_S(long groupId, long receiverUserId,
		int status) throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the number of member requests where groupId = &#63; and receiverUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param receiverUserId the receiver user ID
	* @param status the status
	* @return the number of matching member requests
	*/
	public int countByG_R_S(long groupId, long receiverUserId, int status);

	/**
	* Caches the member request in the entity cache if it is enabled.
	*
	* @param memberRequest the member request
	*/
	public void cacheResult(MemberRequest memberRequest);

	/**
	* Caches the member requests in the entity cache if it is enabled.
	*
	* @param memberRequests the member requests
	*/
	public void cacheResult(java.util.List<MemberRequest> memberRequests);

	/**
	* Creates a new member request with the primary key. Does not add the member request to the database.
	*
	* @param memberRequestId the primary key for the new member request
	* @return the new member request
	*/
	public MemberRequest create(long memberRequestId);

	/**
	* Removes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request that was removed
	* @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	*/
	public MemberRequest remove(long memberRequestId)
		throws com.liferay.so.NoSuchMemberRequestException;

	public MemberRequest updateImpl(MemberRequest memberRequest);

	/**
	* Returns the member request with the primary key or throws a {@link NoSuchMemberRequestException} if it could not be found.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request
	* @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	*/
	public MemberRequest findByPrimaryKey(long memberRequestId)
		throws com.liferay.so.NoSuchMemberRequestException;

	/**
	* Returns the member request with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request, or <code>null</code> if a member request with the primary key could not be found
	*/
	public MemberRequest fetchByPrimaryKey(long memberRequestId);

	@Override
	public java.util.Map<java.io.Serializable, MemberRequest> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the member requests.
	*
	* @return the member requests
	*/
	public java.util.List<MemberRequest> findAll();

	/**
	* Returns a range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of member requests
	*/
	public java.util.List<MemberRequest> findAll(int start, int end);

	/**
	* Returns an ordered range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of member requests
	*/
	public java.util.List<MemberRequest> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MemberRequest> orderByComparator);

	/**
	* Removes all the member requests from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of member requests.
	*
	* @return the number of member requests
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}