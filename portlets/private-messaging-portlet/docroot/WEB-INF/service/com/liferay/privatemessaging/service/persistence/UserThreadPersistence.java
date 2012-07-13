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

package com.liferay.privatemessaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.privatemessaging.model.UserThread;

/**
 * The persistence interface for the user thread service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadPersistenceImpl
 * @see UserThreadUtil
 * @generated
 */
public interface UserThreadPersistence extends BasePersistence<UserThread> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserThreadUtil} to access the user thread persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the user thread in the entity cache if it is enabled.
	*
	* @param userThread the user thread
	*/
	public void cacheResult(
		com.liferay.privatemessaging.model.UserThread userThread);

	/**
	* Caches the user threads in the entity cache if it is enabled.
	*
	* @param userThreads the user threads
	*/
	public void cacheResult(
		java.util.List<com.liferay.privatemessaging.model.UserThread> userThreads);

	/**
	* Creates a new user thread with the primary key. Does not add the user thread to the database.
	*
	* @param userThreadId the primary key for the new user thread
	* @return the new user thread
	*/
	public com.liferay.privatemessaging.model.UserThread create(
		long userThreadId);

	/**
	* Removes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread that was removed
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread remove(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	public com.liferay.privatemessaging.model.UserThread updateImpl(
		com.liferay.privatemessaging.model.UserThread userThread, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user thread with the primary key or throws a {@link com.liferay.privatemessaging.NoSuchUserThreadException} if it could not be found.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByPrimaryKey(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the user thread with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByPrimaryKey(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user threads where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @return the matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByMBThreadId(
		long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user threads where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mbThreadId the mb thread ID
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByMBThreadId(
		long mbThreadId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user threads where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mbThreadId the mb thread ID
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByMBThreadId(
		long mbThreadId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByMBThreadId_First(
		long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the first user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByMBThreadId_First(
		long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByMBThreadId_Last(
		long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the last user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByMBThreadId_Last(
		long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user threads before and after the current user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread[] findByMBThreadId_PrevAndNext(
		long userThreadId, long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns all the user threads where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user threads where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user threads where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user threads before and after the current user thread in the ordered set where userId = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread[] findByUserId_PrevAndNext(
		long userThreadId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or throws a {@link com.liferay.privatemessaging.NoSuchUserThreadException} if it could not be found.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByU_M(
		long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByU_M(
		long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByU_M(
		long userId, long mbThreadId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user threads where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @return the matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_D(
		long userId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user threads where userId = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_D(
		long userId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user threads where userId = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_D(
		long userId, boolean deleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByU_D_First(
		long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByU_D_First(
		long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByU_D_Last(
		long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByU_D_Last(
		long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user threads before and after the current user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread[] findByU_D_PrevAndNext(
		long userThreadId, long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @return the matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_R_D(
		long userId, boolean read, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_R_D(
		long userId, boolean read, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_R_D(
		long userId, boolean read, boolean deleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByU_R_D_First(
		long userId, boolean read, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByU_R_D_First(
		long userId, boolean read, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByU_R_D_Last(
		long userId, boolean read, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByU_R_D_Last(
		long userId, boolean read, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user threads before and after the current user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread[] findByU_R_D_PrevAndNext(
		long userThreadId, long userId, boolean read, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns all the user threads.
	*
	* @return the user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user threads where mbThreadId = &#63; from the database.
	*
	* @param mbThreadId the mb thread ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMBThreadId(long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user threads where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the user thread where userId = &#63; and mbThreadId = &#63; from the database.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the user thread that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread removeByU_M(
		long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Removes all the user threads where userId = &#63; and deleted = &#63; from the database.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_D(long userId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user threads where userId = &#63; and read = &#63; and deleted = &#63; from the database.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_R_D(long userId, boolean read, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user threads from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user threads where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @return the number of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countByMBThreadId(long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user threads where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user threads where userId = &#63; and mbThreadId = &#63;.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the number of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_M(long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user threads where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @return the number of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_D(long userId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @return the number of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_R_D(long userId, boolean read, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user threads.
	*
	* @return the number of user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}