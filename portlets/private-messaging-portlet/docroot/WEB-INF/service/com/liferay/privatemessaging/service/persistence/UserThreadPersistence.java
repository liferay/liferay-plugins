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

package com.liferay.privatemessaging.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.privatemessaging.model.UserThread;

/**
 * The persistence interface for the user thread service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link UserThreadUtil} to access the user thread persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
 * </p>
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
	/**
	* Caches the user thread in the entity cache if it is enabled.
	*
	* @param userThread the user thread to cache
	*/
	public void cacheResult(
		com.liferay.privatemessaging.model.UserThread userThread);

	/**
	* Caches the user threads in the entity cache if it is enabled.
	*
	* @param userThreads the user threads to cache
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
	* @param userThreadId the primary key of the user thread to remove
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
	* Finds the user thread with the primary key or throws a {@link com.liferay.privatemessaging.NoSuchUserThreadException} if it could not be found.
	*
	* @param userThreadId the primary key of the user thread to find
	* @return the user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByPrimaryKey(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Finds the user thread with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userThreadId the primary key of the user thread to find
	* @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread fetchByPrimaryKey(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the user threads where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the user threads where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @return the range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the user threads where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first user thread in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
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
	* Finds the last user thread in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
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
	* Finds the user threads before and after the current user thread in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
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
	* Finds all the user threads where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread id to search with
	* @return the matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByMBThreadId(
		long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the user threads where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mbThreadId the mb thread id to search with
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @return the range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByMBThreadId(
		long mbThreadId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the user threads where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mbThreadId the mb thread id to search with
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByMBThreadId(
		long mbThreadId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first user thread in the ordered set where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mbThreadId the mb thread id to search with
	* @param orderByComparator the comparator to order the set by
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
	* Finds the last user thread in the ordered set where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mbThreadId the mb thread id to search with
	* @param orderByComparator the comparator to order the set by
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
	* Finds the user threads before and after the current user thread in the ordered set where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userThreadId the primary key of the current user thread
	* @param mbThreadId the mb thread id to search with
	* @param orderByComparator the comparator to order the set by
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
	* Finds all the user threads where userId = &#63; and read = &#63;.
	*
	* @param userId the user id to search with
	* @param read the read to search with
	* @return the matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_R(
		long userId, boolean read)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the user threads where userId = &#63; and read = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param read the read to search with
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @return the range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_R(
		long userId, boolean read, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the user threads where userId = &#63; and read = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param read the read to search with
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findByU_R(
		long userId, boolean read, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first user thread in the ordered set where userId = &#63; and read = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param read the read to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByU_R_First(
		long userId, boolean read,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Finds the last user thread in the ordered set where userId = &#63; and read = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param read the read to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread findByU_R_Last(
		long userId, boolean read,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Finds the user threads before and after the current user thread in the ordered set where userId = &#63; and read = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user id to search with
	* @param read the read to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next user thread
	* @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread[] findByU_R_PrevAndNext(
		long userThreadId, long userId, boolean read,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Finds all the user threads.
	*
	* @return the user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @return the range of user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user threads where userId = &#63; from the database.
	*
	* @param userId the user id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user threads where mbThreadId = &#63; from the database.
	*
	* @param mbThreadId the mb thread id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMBThreadId(long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user threads where userId = &#63; and read = &#63; from the database.
	*
	* @param userId the user id to search with
	* @param read the read to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_R(long userId, boolean read)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user threads from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the user threads where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the number of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the user threads where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread id to search with
	* @return the number of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countByMBThreadId(long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the user threads where userId = &#63; and read = &#63;.
	*
	* @param userId the user id to search with
	* @param read the read to search with
	* @return the number of matching user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_R(long userId, boolean read)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the user threads.
	*
	* @return the number of user threads
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}