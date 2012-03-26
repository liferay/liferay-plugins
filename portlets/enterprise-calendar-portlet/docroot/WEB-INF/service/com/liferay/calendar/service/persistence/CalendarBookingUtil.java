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

package com.liferay.calendar.service.persistence;

import com.liferay.calendar.model.CalendarBooking;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the calendar booking service. This utility wraps {@link CalendarBookingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingPersistence
 * @see CalendarBookingPersistenceImpl
 * @generated
 */
public class CalendarBookingUtil {
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
	public static void clearCache(CalendarBooking calendarBooking) {
		getPersistence().clearCache(calendarBooking);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CalendarBooking> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CalendarBooking> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CalendarBooking> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CalendarBooking update(CalendarBooking calendarBooking,
		boolean merge) throws SystemException {
		return getPersistence().update(calendarBooking, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CalendarBooking update(CalendarBooking calendarBooking,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(calendarBooking, merge, serviceContext);
	}

	/**
	* Caches the calendar booking in the entity cache if it is enabled.
	*
	* @param calendarBooking the calendar booking
	*/
	public static void cacheResult(
		com.liferay.calendar.model.CalendarBooking calendarBooking) {
		getPersistence().cacheResult(calendarBooking);
	}

	/**
	* Caches the calendar bookings in the entity cache if it is enabled.
	*
	* @param calendarBookings the calendar bookings
	*/
	public static void cacheResult(
		java.util.List<com.liferay.calendar.model.CalendarBooking> calendarBookings) {
		getPersistence().cacheResult(calendarBookings);
	}

	/**
	* Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	*
	* @param calendarBookingId the primary key for the new calendar booking
	* @return the new calendar booking
	*/
	public static com.liferay.calendar.model.CalendarBooking create(
		long calendarBookingId) {
		return getPersistence().create(calendarBookingId);
	}

	/**
	* Removes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking that was removed
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking remove(
		long calendarBookingId)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking updateImpl(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(calendarBooking, merge);
	}

	/**
	* Returns the calendar booking with the primary key or throws a {@link com.liferay.calendar.NoSuchBookingException} if it could not be found.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByPrimaryKey(
		long calendarBookingId)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(calendarBookingId);
	}

	/**
	* Returns the calendar booking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking, or <code>null</code> if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking fetchByPrimaryKey(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(calendarBookingId);
	}

	/**
	* Returns all the calendar bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the calendar bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the calendar bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar booking in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar booking in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking[] findByUuid_PrevAndNext(
		long calendarBookingId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(calendarBookingId, uuid,
			orderByComparator);
	}

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchBookingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the calendar bookings where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarId(
		long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCalendarId(calendarId);
	}

	/**
	* Returns a range of all the calendar bookings where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarId(
		long calendarId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCalendarId(calendarId, start, end);
	}

	/**
	* Returns an ordered range of all the calendar bookings where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarId(
		long calendarId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarId(calendarId, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar booking in the ordered set where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByCalendarId_First(
		long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarId_First(calendarId, orderByComparator);
	}

	/**
	* Returns the last calendar booking in the ordered set where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByCalendarId_Last(
		long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarId_Last(calendarId, orderByComparator);
	}

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking[] findByCalendarId_PrevAndNext(
		long calendarBookingId, long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarId_PrevAndNext(calendarBookingId, calendarId,
			orderByComparator);
	}

	/**
	* Returns all the calendar bookings where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarResourceId(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCalendarResourceId(calendarResourceId);
	}

	/**
	* Returns a range of all the calendar bookings where calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarResourceId(calendarResourceId, start, end);
	}

	/**
	* Returns an ordered range of all the calendar bookings where calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarResourceId(calendarResourceId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar booking in the ordered set where calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByCalendarResourceId_First(
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarResourceId_First(calendarResourceId,
			orderByComparator);
	}

	/**
	* Returns the last calendar booking in the ordered set where calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByCalendarResourceId_Last(
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarResourceId_Last(calendarResourceId,
			orderByComparator);
	}

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking[] findByCalendarResourceId_PrevAndNext(
		long calendarBookingId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCalendarResourceId_PrevAndNext(calendarBookingId,
			calendarResourceId, orderByComparator);
	}

	/**
	* Returns all the calendar bookings where calendarResourceId = &#63; and status = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S(
		long calendarResourceId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S(calendarResourceId, status);
	}

	/**
	* Returns a range of all the calendar bookings where calendarResourceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S(
		long calendarResourceId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S(calendarResourceId, status, start, end);
	}

	/**
	* Returns an ordered range of all the calendar bookings where calendarResourceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S(
		long calendarResourceId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S(calendarResourceId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar booking in the ordered set where calendarResourceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByC_S_First(
		long calendarResourceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_First(calendarResourceId, status,
			orderByComparator);
	}

	/**
	* Returns the last calendar booking in the ordered set where calendarResourceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByC_S_Last(
		long calendarResourceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_Last(calendarResourceId, status, orderByComparator);
	}

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarResourceId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking[] findByC_S_PrevAndNext(
		long calendarBookingId, long calendarResourceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_PrevAndNext(calendarBookingId,
			calendarResourceId, status, orderByComparator);
	}

	/**
	* Returns all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S_E(
		long calendarId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S_E(calendarId, startDate, endDate);
	}

	/**
	* Returns a range of all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S_E(
		long calendarId, java.util.Date startDate, java.util.Date endDate,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_E(calendarId, startDate, endDate, start, end);
	}

	/**
	* Returns an ordered range of all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S_E(
		long calendarId, java.util.Date startDate, java.util.Date endDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_E(calendarId, startDate, endDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar booking in the ordered set where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByC_S_E_First(
		long calendarId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_E_First(calendarId, startDate, endDate,
			orderByComparator);
	}

	/**
	* Returns the last calendar booking in the ordered set where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking findByC_S_E_Last(
		long calendarId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_E_Last(calendarId, startDate, endDate,
			orderByComparator);
	}

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking[] findByC_S_E_PrevAndNext(
		long calendarBookingId, long calendarId, java.util.Date startDate,
		java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_E_PrevAndNext(calendarBookingId, calendarId,
			startDate, endDate, orderByComparator);
	}

	/**
	* Returns all the calendar bookings.
	*
	* @return the calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the calendar bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the calendar bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the calendar bookings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the calendar booking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar booking that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarBooking removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the calendar bookings where calendarId = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCalendarId(long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCalendarId(calendarId);
	}

	/**
	* Removes all the calendar bookings where calendarResourceId = &#63; from the database.
	*
	* @param calendarResourceId the calendar resource ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCalendarResourceId(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCalendarResourceId(calendarResourceId);
	}

	/**
	* Removes all the calendar bookings where calendarResourceId = &#63; and status = &#63; from the database.
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_S(long calendarResourceId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_S(calendarResourceId, status);
	}

	/**
	* Removes all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_S_E(long calendarId, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_S_E(calendarId, startDate, endDate);
	}

	/**
	* Removes all the calendar bookings from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of calendar bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of calendar bookings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of calendar bookings where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCalendarId(long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCalendarId(calendarId);
	}

	/**
	* Returns the number of calendar bookings where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCalendarResourceId(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCalendarResourceId(calendarResourceId);
	}

	/**
	* Returns the number of calendar bookings where calendarResourceId = &#63; and status = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_S(long calendarResourceId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_S(calendarResourceId, status);
	}

	/**
	* Returns the number of calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_S_E(long calendarId, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_S_E(calendarId, startDate, endDate);
	}

	/**
	* Returns the number of calendar bookings.
	*
	* @return the number of calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CalendarBookingPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CalendarBookingPersistence)PortletBeanLocatorUtil.locate(com.liferay.calendar.service.ClpSerializer.getServletContextName(),
					CalendarBookingPersistence.class.getName());

			ReferenceRegistry.registerReference(CalendarBookingUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(CalendarBookingPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(CalendarBookingUtil.class,
			"_persistence");
	}

	private static CalendarBookingPersistence _persistence;
}