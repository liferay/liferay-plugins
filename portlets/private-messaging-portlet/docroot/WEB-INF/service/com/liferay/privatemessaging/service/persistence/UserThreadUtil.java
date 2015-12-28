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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.privatemessaging.model.UserThread;

import java.util.List;

/**
 * The persistence utility for the user thread service. This utility wraps {@link com.liferay.privatemessaging.service.persistence.impl.UserThreadPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadPersistence
 * @see com.liferay.privatemessaging.service.persistence.impl.UserThreadPersistenceImpl
 * @generated
 */
@ProviderType
public class UserThreadUtil {
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
	public static void clearCache(UserThread userThread) {
		getPersistence().clearCache(userThread);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserThread> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserThread> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserThread> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static UserThread update(UserThread userThread) {
		return getPersistence().update(userThread);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static UserThread update(UserThread userThread,
		ServiceContext serviceContext) {
		return getPersistence().update(userThread, serviceContext);
	}

	/**
	* Returns all the user threads where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @return the matching user threads
	*/
	public static List<UserThread> findByMBThreadId(long mbThreadId) {
		return getPersistence().findByMBThreadId(mbThreadId);
	}

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
	public static List<UserThread> findByMBThreadId(long mbThreadId, int start,
		int end) {
		return getPersistence().findByMBThreadId(mbThreadId, start, end);
	}

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
	public static List<UserThread> findByMBThreadId(long mbThreadId, int start,
		int end, OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .findByMBThreadId(mbThreadId, start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user threads
	*/
	public static List<UserThread> findByMBThreadId(long mbThreadId, int start,
		int end, OrderByComparator<UserThread> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMBThreadId(mbThreadId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public static UserThread findByMBThreadId_First(long mbThreadId,
		OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByMBThreadId_First(mbThreadId, orderByComparator);
	}

	/**
	* Returns the first user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByMBThreadId_First(long mbThreadId,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .fetchByMBThreadId_First(mbThreadId, orderByComparator);
	}

	/**
	* Returns the last user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public static UserThread findByMBThreadId_Last(long mbThreadId,
		OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByMBThreadId_Last(mbThreadId, orderByComparator);
	}

	/**
	* Returns the last user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByMBThreadId_Last(long mbThreadId,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .fetchByMBThreadId_Last(mbThreadId, orderByComparator);
	}

	/**
	* Returns the user threads before and after the current user thread in the ordered set where mbThreadId = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param mbThreadId the mb thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public static UserThread[] findByMBThreadId_PrevAndNext(long userThreadId,
		long mbThreadId, OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByMBThreadId_PrevAndNext(userThreadId, mbThreadId,
			orderByComparator);
	}

	/**
	* Removes all the user threads where mbThreadId = &#63; from the database.
	*
	* @param mbThreadId the mb thread ID
	*/
	public static void removeByMBThreadId(long mbThreadId) {
		getPersistence().removeByMBThreadId(mbThreadId);
	}

	/**
	* Returns the number of user threads where mbThreadId = &#63;.
	*
	* @param mbThreadId the mb thread ID
	* @return the number of matching user threads
	*/
	public static int countByMBThreadId(long mbThreadId) {
		return getPersistence().countByMBThreadId(mbThreadId);
	}

	/**
	* Returns all the user threads where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user threads
	*/
	public static List<UserThread> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

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
	public static List<UserThread> findByUserId(long userId, int start, int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

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
	public static List<UserThread> findByUserId(long userId, int start,
		int end, OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user threads
	*/
	public static List<UserThread> findByUserId(long userId, int start,
		int end, OrderByComparator<UserThread> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public static UserThread findByUserId_First(long userId,
		OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByUserId_First(long userId,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public static UserThread findByUserId_Last(long userId,
		OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last user thread in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByUserId_Last(long userId,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the user threads before and after the current user thread in the ordered set where userId = &#63;.
	*
	* @param userThreadId the primary key of the current user thread
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user thread
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public static UserThread[] findByUserId_PrevAndNext(long userThreadId,
		long userId, OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByUserId_PrevAndNext(userThreadId, userId,
			orderByComparator);
	}

	/**
	* Removes all the user threads where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of user threads where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user threads
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or throws a {@link NoSuchUserThreadException} if it could not be found.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public static UserThread findByU_M(long userId, long mbThreadId)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence().findByU_M(userId, mbThreadId);
	}

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByU_M(long userId, long mbThreadId) {
		return getPersistence().fetchByU_M(userId, mbThreadId);
	}

	/**
	* Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByU_M(long userId, long mbThreadId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByU_M(userId, mbThreadId, retrieveFromCache);
	}

	/**
	* Removes the user thread where userId = &#63; and mbThreadId = &#63; from the database.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the user thread that was removed
	*/
	public static UserThread removeByU_M(long userId, long mbThreadId)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence().removeByU_M(userId, mbThreadId);
	}

	/**
	* Returns the number of user threads where userId = &#63; and mbThreadId = &#63;.
	*
	* @param userId the user ID
	* @param mbThreadId the mb thread ID
	* @return the number of matching user threads
	*/
	public static int countByU_M(long userId, long mbThreadId) {
		return getPersistence().countByU_M(userId, mbThreadId);
	}

	/**
	* Returns all the user threads where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @return the matching user threads
	*/
	public static List<UserThread> findByU_D(long userId, boolean deleted) {
		return getPersistence().findByU_D(userId, deleted);
	}

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
	public static List<UserThread> findByU_D(long userId, boolean deleted,
		int start, int end) {
		return getPersistence().findByU_D(userId, deleted, start, end);
	}

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
	public static List<UserThread> findByU_D(long userId, boolean deleted,
		int start, int end, OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .findByU_D(userId, deleted, start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user threads
	*/
	public static List<UserThread> findByU_D(long userId, boolean deleted,
		int start, int end, OrderByComparator<UserThread> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_D(userId, deleted, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public static UserThread findByU_D_First(long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByU_D_First(userId, deleted, orderByComparator);
	}

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByU_D_First(long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .fetchByU_D_First(userId, deleted, orderByComparator);
	}

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread
	* @throws NoSuchUserThreadException if a matching user thread could not be found
	*/
	public static UserThread findByU_D_Last(long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByU_D_Last(userId, deleted, orderByComparator);
	}

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByU_D_Last(long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .fetchByU_D_Last(userId, deleted, orderByComparator);
	}

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
	public static UserThread[] findByU_D_PrevAndNext(long userThreadId,
		long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByU_D_PrevAndNext(userThreadId, userId, deleted,
			orderByComparator);
	}

	/**
	* Removes all the user threads where userId = &#63; and deleted = &#63; from the database.
	*
	* @param userId the user ID
	* @param deleted the deleted
	*/
	public static void removeByU_D(long userId, boolean deleted) {
		getPersistence().removeByU_D(userId, deleted);
	}

	/**
	* Returns the number of user threads where userId = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param deleted the deleted
	* @return the number of matching user threads
	*/
	public static int countByU_D(long userId, boolean deleted) {
		return getPersistence().countByU_D(userId, deleted);
	}

	/**
	* Returns all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @return the matching user threads
	*/
	public static List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted) {
		return getPersistence().findByU_R_D(userId, read, deleted);
	}

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
	public static List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end) {
		return getPersistence().findByU_R_D(userId, read, deleted, start, end);
	}

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
	public static List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .findByU_R_D(userId, read, deleted, start, end,
			orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user threads
	*/
	public static List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end,
		OrderByComparator<UserThread> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_R_D(userId, read, deleted, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static UserThread findByU_R_D_First(long userId, boolean read,
		boolean deleted, OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByU_R_D_First(userId, read, deleted, orderByComparator);
	}

	/**
	* Returns the first user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByU_R_D_First(long userId, boolean read,
		boolean deleted, OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .fetchByU_R_D_First(userId, read, deleted, orderByComparator);
	}

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
	public static UserThread findByU_R_D_Last(long userId, boolean read,
		boolean deleted, OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByU_R_D_Last(userId, read, deleted, orderByComparator);
	}

	/**
	* Returns the last user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	*/
	public static UserThread fetchByU_R_D_Last(long userId, boolean read,
		boolean deleted, OrderByComparator<UserThread> orderByComparator) {
		return getPersistence()
				   .fetchByU_R_D_Last(userId, read, deleted, orderByComparator);
	}

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
	public static UserThread[] findByU_R_D_PrevAndNext(long userThreadId,
		long userId, boolean read, boolean deleted,
		OrderByComparator<UserThread> orderByComparator)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence()
				   .findByU_R_D_PrevAndNext(userThreadId, userId, read,
			deleted, orderByComparator);
	}

	/**
	* Removes all the user threads where userId = &#63; and read = &#63; and deleted = &#63; from the database.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	*/
	public static void removeByU_R_D(long userId, boolean read, boolean deleted) {
		getPersistence().removeByU_R_D(userId, read, deleted);
	}

	/**
	* Returns the number of user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param deleted the deleted
	* @return the number of matching user threads
	*/
	public static int countByU_R_D(long userId, boolean read, boolean deleted) {
		return getPersistence().countByU_R_D(userId, read, deleted);
	}

	/**
	* Caches the user thread in the entity cache if it is enabled.
	*
	* @param userThread the user thread
	*/
	public static void cacheResult(UserThread userThread) {
		getPersistence().cacheResult(userThread);
	}

	/**
	* Caches the user threads in the entity cache if it is enabled.
	*
	* @param userThreads the user threads
	*/
	public static void cacheResult(List<UserThread> userThreads) {
		getPersistence().cacheResult(userThreads);
	}

	/**
	* Creates a new user thread with the primary key. Does not add the user thread to the database.
	*
	* @param userThreadId the primary key for the new user thread
	* @return the new user thread
	*/
	public static UserThread create(long userThreadId) {
		return getPersistence().create(userThreadId);
	}

	/**
	* Removes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread that was removed
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public static UserThread remove(long userThreadId)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence().remove(userThreadId);
	}

	public static UserThread updateImpl(UserThread userThread) {
		return getPersistence().updateImpl(userThread);
	}

	/**
	* Returns the user thread with the primary key or throws a {@link NoSuchUserThreadException} if it could not be found.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread
	* @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	*/
	public static UserThread findByPrimaryKey(long userThreadId)
		throws com.liferay.privatemessaging.NoSuchUserThreadException {
		return getPersistence().findByPrimaryKey(userThreadId);
	}

	/**
	* Returns the user thread with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	*/
	public static UserThread fetchByPrimaryKey(long userThreadId) {
		return getPersistence().fetchByPrimaryKey(userThreadId);
	}

	public static java.util.Map<java.io.Serializable, UserThread> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user threads.
	*
	* @return the user threads
	*/
	public static List<UserThread> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<UserThread> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<UserThread> findAll(int start, int end,
		OrderByComparator<UserThread> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user threads
	*/
	public static List<UserThread> findAll(int start, int end,
		OrderByComparator<UserThread> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user threads from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user threads.
	*
	* @return the number of user threads
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UserThreadPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UserThreadPersistence)PortletBeanLocatorUtil.locate(com.liferay.privatemessaging.service.ClpSerializer.getServletContextName(),
					UserThreadPersistence.class.getName());

			ReferenceRegistry.registerReference(UserThreadUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static UserThreadPersistence _persistence;
}