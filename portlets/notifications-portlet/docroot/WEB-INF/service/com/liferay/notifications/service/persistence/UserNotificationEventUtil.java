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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the user notification event service. This utility wraps {@link UserNotificationEventPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationEventPersistence
 * @see UserNotificationEventPersistenceImpl
 * @generated
 */
public class UserNotificationEventUtil {
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
	public static void clearCache(UserNotificationEvent userNotificationEvent) {
		getPersistence().clearCache(userNotificationEvent);
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
	public static List<UserNotificationEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserNotificationEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserNotificationEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static UserNotificationEvent update(
		UserNotificationEvent userNotificationEvent) throws SystemException {
		return getPersistence().update(userNotificationEvent);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static UserNotificationEvent update(
		UserNotificationEvent userNotificationEvent,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(userNotificationEvent, serviceContext);
	}

	/**
	* Returns the user notification event where userNotificationEventId = &#63; or throws a {@link com.liferay.notifications.NoSuchUserNotificationEventException} if it could not be found.
	*
	* @param userNotificationEventId the user notification event ID
	* @return the matching user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent findByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserNotificationEventId(userNotificationEventId);
	}

	/**
	* Returns the user notification event where userNotificationEventId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userNotificationEventId the user notification event ID
	* @return the matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent fetchByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserNotificationEventId(userNotificationEventId);
	}

	/**
	* Returns the user notification event where userNotificationEventId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userNotificationEventId the user notification event ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent fetchByUserNotificationEventId(
		long userNotificationEventId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserNotificationEventId(userNotificationEventId,
			retrieveFromCache);
	}

	/**
	* Removes the user notification event where userNotificationEventId = &#63; from the database.
	*
	* @param userNotificationEventId the user notification event ID
	* @return the user notification event that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent removeByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByUserNotificationEventId(userNotificationEventId);
	}

	/**
	* Returns the number of user notification events where userNotificationEventId = &#63;.
	*
	* @param userNotificationEventId the user notification event ID
	* @return the number of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByUserNotificationEventId(userNotificationEventId);
	}

	/**
	* Returns all the user notification events where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @return the matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A(
		long userId, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_A(userId, actionRequired);
	}

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
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A(
		long userId, boolean actionRequired, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_A(userId, actionRequired, start, end);
	}

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
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A(
		long userId, boolean actionRequired, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A(userId, actionRequired, start, end,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent findByU_A_First(
		long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A_First(userId, actionRequired, orderByComparator);
	}

	/**
	* Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent fetchByU_A_First(
		long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_A_First(userId, actionRequired, orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent findByU_A_Last(
		long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A_Last(userId, actionRequired, orderByComparator);
	}

	/**
	* Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent fetchByU_A_Last(
		long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_A_Last(userId, actionRequired, orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent[] findByU_A_PrevAndNext(
		long notificationEventId, long userId, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A_PrevAndNext(notificationEventId, userId,
			actionRequired, orderByComparator);
	}

	/**
	* Removes all the user notification events where userId = &#63; and actionRequired = &#63; from the database.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_A(long userId, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_A(userId, actionRequired);
	}

	/**
	* Returns the number of user notification events where userId = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @return the number of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_A(long userId, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_A(userId, actionRequired);
	}

	/**
	* Returns all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @return the matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_D_A(
		long userId, boolean delivered, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_D_A(userId, delivered, actionRequired);
	}

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
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_D_A(
		long userId, boolean delivered, boolean actionRequired, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_D_A(userId, delivered, actionRequired, start, end);
	}

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
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_D_A(
		long userId, boolean delivered, boolean actionRequired, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_D_A(userId, delivered, actionRequired, start, end,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent findByU_D_A_First(
		long userId, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_D_A_First(userId, delivered, actionRequired,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent fetchByU_D_A_First(
		long userId, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_D_A_First(userId, delivered, actionRequired,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent findByU_D_A_Last(
		long userId, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_D_A_Last(userId, delivered, actionRequired,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent fetchByU_D_A_Last(
		long userId, boolean delivered, boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_D_A_Last(userId, delivered, actionRequired,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent[] findByU_D_A_PrevAndNext(
		long notificationEventId, long userId, boolean delivered,
		boolean actionRequired,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_D_A_PrevAndNext(notificationEventId, userId,
			delivered, actionRequired, orderByComparator);
	}

	/**
	* Removes all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63; from the database.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_D_A(long userId, boolean delivered,
		boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_D_A(userId, delivered, actionRequired);
	}

	/**
	* Returns the number of user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	*
	* @param userId the user ID
	* @param delivered the delivered
	* @param actionRequired the action required
	* @return the number of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_D_A(long userId, boolean delivered,
		boolean actionRequired)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_D_A(userId, delivered, actionRequired);
	}

	/**
	* Returns all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @return the matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A_A(
		long userId, boolean actionRequired, boolean archived)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_A_A(userId, actionRequired, archived);
	}

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
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A_A(
		long userId, boolean actionRequired, boolean archived, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A_A(userId, actionRequired, archived, start, end);
	}

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
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findByU_A_A(
		long userId, boolean actionRequired, boolean archived, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A_A(userId, actionRequired, archived, start, end,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent findByU_A_A_First(
		long userId, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A_A_First(userId, actionRequired, archived,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent fetchByU_A_A_First(
		long userId, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_A_A_First(userId, actionRequired, archived,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent findByU_A_A_Last(
		long userId, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A_A_Last(userId, actionRequired, archived,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent fetchByU_A_A_Last(
		long userId, boolean actionRequired, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_A_A_Last(userId, actionRequired, archived,
			orderByComparator);
	}

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
	public static com.liferay.notifications.model.UserNotificationEvent[] findByU_A_A_PrevAndNext(
		long notificationEventId, long userId, boolean actionRequired,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_A_A_PrevAndNext(notificationEventId, userId,
			actionRequired, archived, orderByComparator);
	}

	/**
	* Removes all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63; from the database.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_A_A(long userId, boolean actionRequired,
		boolean archived)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_A_A(userId, actionRequired, archived);
	}

	/**
	* Returns the number of user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	*
	* @param userId the user ID
	* @param actionRequired the action required
	* @param archived the archived
	* @return the number of matching user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_A_A(long userId, boolean actionRequired,
		boolean archived)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_A_A(userId, actionRequired, archived);
	}

	/**
	* Caches the user notification event in the entity cache if it is enabled.
	*
	* @param userNotificationEvent the user notification event
	*/
	public static void cacheResult(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent) {
		getPersistence().cacheResult(userNotificationEvent);
	}

	/**
	* Caches the user notification events in the entity cache if it is enabled.
	*
	* @param userNotificationEvents the user notification events
	*/
	public static void cacheResult(
		java.util.List<com.liferay.notifications.model.UserNotificationEvent> userNotificationEvents) {
		getPersistence().cacheResult(userNotificationEvents);
	}

	/**
	* Creates a new user notification event with the primary key. Does not add the user notification event to the database.
	*
	* @param notificationEventId the primary key for the new user notification event
	* @return the new user notification event
	*/
	public static com.liferay.notifications.model.UserNotificationEvent create(
		long notificationEventId) {
		return getPersistence().create(notificationEventId);
	}

	/**
	* Removes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event that was removed
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent remove(
		long notificationEventId)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(notificationEventId);
	}

	public static com.liferay.notifications.model.UserNotificationEvent updateImpl(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(userNotificationEvent);
	}

	/**
	* Returns the user notification event with the primary key or throws a {@link com.liferay.notifications.NoSuchUserNotificationEventException} if it could not be found.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event
	* @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent findByPrimaryKey(
		long notificationEventId)
		throws com.liferay.notifications.NoSuchUserNotificationEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(notificationEventId);
	}

	/**
	* Returns the user notification event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event, or <code>null</code> if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent fetchByPrimaryKey(
		long notificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(notificationEventId);
	}

	/**
	* Returns all the user notification events.
	*
	* @return the user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the user notification events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user notification events.
	*
	* @return the number of user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static UserNotificationEventPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UserNotificationEventPersistence)PortletBeanLocatorUtil.locate(com.liferay.notifications.service.ClpSerializer.getServletContextName(),
					UserNotificationEventPersistence.class.getName());

			ReferenceRegistry.registerReference(UserNotificationEventUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(UserNotificationEventPersistence persistence) {
	}

	private static UserNotificationEventPersistence _persistence;
}