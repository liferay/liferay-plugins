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

package com.liferay.notifications.service.persistence;

import com.liferay.notifications.model.UserNotificationEvent;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the user notification event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationEventPersistenceImpl
 * @see UserNotificationEventUtil
 * @generated
 */
public interface UserNotificationEventPersistence extends BasePersistence<UserNotificationEvent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserNotificationEventUtil} to access the user notification event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the user notification event where userNotificationEventId = &#63; or throws a {@link com.liferay.notifications.NoSuchUserNotificationEventException} if it could not be found.
	*
	* @param userNotificationEventId the user notification event ID
	* @return the matching user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent findByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user notification event where userNotificationEventId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userNotificationEventId the user notification event ID
	* @return the matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user notification event where userNotificationEventId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userNotificationEventId the user notification event ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByUserNotificationEventId(
		long userNotificationEventId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the user notification event where userNotificationEventId = &#63; from the database.
	*
	* @param userNotificationEventId the user notification event ID
	* @return the user notification event that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent removeByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user notification events where userNotificationEventId = &#63;.
	*
	* @param userNotificationEventId the user notification event ID
	* @return the number of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserNotificationEventId(long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user notification events where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @return the matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A(
		long userId, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user notification events where userId = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A(
		long userId, boolean actionRequired, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A(
		long userId, boolean actionRequired, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent findByU_A_First(
		long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByU_A_First(
		long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent findByU_A_Last(
		long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByU_A_Last(
		long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	*
	* @param notificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent[] findByU_A_PrevAndNext(
		long notificationEventId, long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user notification events where userId = &#63; and actionRequired = &#63; from the database.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_A(long userId, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user notification events where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @return the number of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_A(long userId, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @return the matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_D_A(
		long userId, boolean delivered, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_D_A(
		long userId, boolean delivered, boolean actionRequired, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_D_A(
		long userId, boolean delivered, boolean actionRequired, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent findByU_D_A_First(
		long userId, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByU_D_A_First(
		long userId, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent findByU_D_A_Last(
		long userId, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByU_D_A_Last(
		long userId, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param notificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent[] findByU_D_A_PrevAndNext(
		long notificationEventId, long userId, boolean delivered,
		boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63; from the database.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_D_A(long userId, boolean delivered,
		boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @return the number of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_D_A(long userId, boolean delivered,
		boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @return the matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A_A(
		long userId, boolean actionRequired, boolean archived)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A_A(
		long userId, boolean actionRequired, boolean archived, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A_A(
		long userId, boolean actionRequired, boolean archived, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent findByU_A_A_First(
		long userId, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByU_A_A_First(
		long userId, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent findByU_A_A_Last(
		long userId, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByU_A_A_Last(
		long userId, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param notificationEventId the primary key of the current user notification event
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent[] findByU_A_A_PrevAndNext(
		long notificationEventId, long userId, boolean actionRequired,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63; from the database.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_A_A(long userId, boolean actionRequired,
		boolean archived)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @return the number of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_A_A(long userId, boolean actionRequired,
		boolean archived)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the user notification event in the entity cache if it is enabled.
	*
	* @param userNotificationEvent the user notification event
	*/
	public void cacheResult(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent);

	/**
	* Caches the user notification events in the entity cache if it is enabled.
	*
	* @param userNotificationEvents the user notification events
	*/
	public void cacheResult(
		java.util.List<com.liferay.notifications.model.UserNotificationEvent> userNotificationEvents);

	/**
	* Creates a new user notification event with the primary key. Does not add the user notification event to the database.
	*
	* @param notificationEventId the primary key for the new user notification event
	* @return the new user notification event
	*/
	public com.liferay.notifications.model.UserNotificationEvent create(
		long notificationEventId);

	/**
	* Removes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event that was removed
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent remove(
		long notificationEventId)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.notifications.model.UserNotificationEvent updateImpl(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user notification event with the primary key or throws a {@link com.liferay.notifications.NoSuchUserNotificationEventException} if it could not be found.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent findByPrimaryKey(
		long notificationEventId)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user notification event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event, or <code>null</code> if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.notifications.model.UserNotificationEvent fetchByPrimaryKey(
		long notificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user notification events.
	*
	* @return the user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user notification events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user notification events.
	*
	* @return the number of user notification events
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}