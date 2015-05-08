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

package com.liferay.privatemessaging.service.persistence;

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.privatemessaging.service.persistence.impl.UserThreadPersistenceImpl
 * @see UserThreadUtil
 * @generated
 */
@ProviderType
public interface UserThreadPersistence extends BasePersistence<UserThread> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserThreadUtil} to access the user thread persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user threads where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @return the matching user threads
	*/
	public java.util.List<UserThread> findByMBThreadId(long mbThreadId);

	/**
	* Returns a range of all the user threads where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param mbThreadId the mb thread ID
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of matching user threads
	*/
	public java.util.List<UserThread> findByMBThreadId(long mbThreadId,
		int start, int end);

	/**
	* Returns an ordered range of all the user threads where mbThreadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param mbThreadId the mb thread ID
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user threads
	*/
	public java.util.List<UserThread> findByMBThreadId(long mbThreadId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the first user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByMBThreadId_First(long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the first user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByMBThreadId_First(long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the last user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByMBThreadId_Last(long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the last user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByMBThreadId_Last(long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the user threads before and after the current user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public UserThread[] findByMBThreadId_PrevAndNext(long userThreadId,
		long mbThreadId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Removes all the user threads where mbThreadId = &#63; from the database.
	*
	* @param mbThreadId the mb thread ID
	*/
	public void removeByMBThreadId(long mbThreadId);

	/**
	* Returns the number of user threads where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @return the number of matching user threads
	*/
	public int countByMBThreadId(long mbThreadId);

	/**
	* Returns all the user threads where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user threads
	*/
	public java.util.List<UserThread> findByUserId(long userId);

	/**
	* Returns a range of all the user threads where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of matching user threads
	*/
	public java.util.List<UserThread> findByUserId(long userId, int start,
		int end);

	/**
	* Returns an ordered range of all the user threads where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user threads
	*/
	public java.util.List<UserThread> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the first user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the last user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the user threads before and after the current user thread in the ordered set where userId = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public UserThread[] findByUserId_PrevAndNext(long userThreadId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Removes all the user threads where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of user threads where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user threads
	*/
	public int countByUserId(long userId);

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or throws a {@link NoSuchUserThreadException} if it could not be found.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByU_M(long userId, long mbThreadId)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByU_M(long userId, long mbThreadId);

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByU_M(long userId, long mbThreadId,
		boolean retrieveFromCache);

	/**
	* Removes the user thread where userId = &#63; and mbThreadId = &#63; from the database.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the user thread that was removed
	*/
	public UserThread removeByU_M(long userId, long mbThreadId)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the number of user threads where userId = &#63; and mbThreadId = &#63;.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the number of matching user threads
	*/
	public int countByU_M(long userId, long mbThreadId);

	/**
	* Returns all the user threads where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @return the matching user threads
	*/
	public java.util.List<UserThread> findByU_D(long userId, boolean deleted);

	/**
	* Returns a range of all the user threads where userId = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of matching user threads
	*/
	public java.util.List<UserThread> findByU_D(long userId, boolean deleted,
		int start, int end);

	/**
	* Returns an ordered range of all the user threads where userId = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user threads
	*/
	public java.util.List<UserThread> findByU_D(long userId, boolean deleted,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByU_D_First(long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByU_D_First(long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByU_D_Last(long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByU_D_Last(long userId, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the user threads before and after the current user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public UserThread[] findByU_D_PrevAndNext(long userThreadId, long userId,
		boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Removes all the user threads where userId = &#63; and deleted = &#63; from the database.
	*
	* @param userId the user ID
	* @param deleted the deleted
	*/
	public void removeByU_D(long userId, boolean deleted);

	/**
	* Returns the number of user threads where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @return the number of matching user threads
	*/
	public int countByU_D(long userId, boolean deleted);

	/**
	* Returns all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @return the matching user threads
	*/
	public java.util.List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted);

	/**
	* Returns a range of all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of matching user threads
	*/
	public java.util.List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end);

	/**
	* Returns an ordered range of all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user threads
	*/
	public java.util.List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByU_R_D_First(long userId, boolean read,
		boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByU_R_D_First(long userId, boolean read,
		boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public UserThread findByU_R_D_Last(long userId, boolean read,
		boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public UserThread fetchByU_R_D_Last(long userId, boolean read,
		boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Returns the user threads before and after the current user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public UserThread[] findByU_R_D_PrevAndNext(long userThreadId, long userId,
		boolean read, boolean deleted,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Removes all the user threads where userId = &#63; and read = &#63; and deleted = &#63; from the database.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	*/
	public void removeByU_R_D(long userId, boolean read, boolean deleted);

	/**
	* Returns the number of user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @return the number of matching user threads
	*/
	public int countByU_R_D(long userId, boolean read, boolean deleted);

	/**
	* Caches the user thread in the entity cache if it is enabled.
	*
	* @param userThread the user thread
	*/
	public void cacheResult(UserThread userThread);

	/**
	* Caches the user threads in the entity cache if it is enabled.
	*
	* @param userThreads the user threads
	*/
	public void cacheResult(java.util.List<UserThread> userThreads);

	/**
	* Creates a new user thread with the primary key. Does not add the user thread to the database.
	*
	* @param userThreadId the primary key for the new user thread
	* @return the new user thread
	*/
	public UserThread create(long userThreadId);

	/**
	* Removes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread that was removed
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public UserThread remove(long userThreadId)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	public UserThread updateImpl(UserThread userThread);

	/**
	* Returns the user thread with the primary key or throws a {@link NoSuchUserThreadException} if it could not be found.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public UserThread findByPrimaryKey(long userThreadId)
		throws com.liferay.privatemessaging.NoSuchUserThreadException;

	/**
	* Returns the user thread with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	*/
	public UserThread fetchByPrimaryKey(long userThreadId);

	@Override
	public java.util.Map<java.io.Serializable, UserThread> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user threads.
	*
	* @return the user threads
	*/
	public java.util.List<UserThread> findAll();

	/**
	* Returns a range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of user threads
	*/
	public java.util.List<UserThread> findAll(int start, int end);

	/**
	* Returns an ordered range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user threads
	*/
	public java.util.List<UserThread> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserThread> orderByComparator);

	/**
	* Removes all the user threads from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user threads.
	*
	* @return the number of user threads
	*/
	public int countAll();
}