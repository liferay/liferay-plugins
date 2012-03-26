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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the calendar booking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingPersistenceImpl
 * @see CalendarBookingUtil
 * @generated
 */
public interface CalendarBookingPersistence extends BasePersistence<CalendarBooking> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarBookingUtil} to access the calendar booking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the calendar booking in the entity cache if it is enabled.
	*
	* @param calendarBooking the calendar booking
	*/
	public void cacheResult(
		com.liferay.calendar.model.CalendarBooking calendarBooking);

	/**
	* Caches the calendar bookings in the entity cache if it is enabled.
	*
	* @param calendarBookings the calendar bookings
	*/
	public void cacheResult(
		java.util.List<com.liferay.calendar.model.CalendarBooking> calendarBookings);

	/**
	* Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	*
	* @param calendarBookingId the primary key for the new calendar booking
	* @return the new calendar booking
	*/
	public com.liferay.calendar.model.CalendarBooking create(
		long calendarBookingId);

	/**
	* Removes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking that was removed
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking remove(
		long calendarBookingId)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.calendar.model.CalendarBooking updateImpl(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar booking with the primary key or throws a {@link com.liferay.calendar.NoSuchBookingException} if it could not be found.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking findByPrimaryKey(
		long calendarBookingId)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar booking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking, or <code>null</code> if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking fetchByPrimaryKey(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the calendar bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking[] findByUuid_PrevAndNext(
		long calendarBookingId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchBookingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar booking
	* @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the calendar bookings where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarId(
		long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarId(
		long calendarId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarId(
		long calendarId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByCalendarId_First(
		long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByCalendarId_Last(
		long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking[] findByCalendarId_PrevAndNext(
		long calendarBookingId, long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the calendar bookings where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarResourceId(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByCalendarResourceId_First(
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByCalendarResourceId_Last(
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking[] findByCalendarResourceId_PrevAndNext(
		long calendarBookingId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the calendar bookings where calendarResourceId = &#63; and status = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S(
		long calendarResourceId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S(
		long calendarResourceId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S(
		long calendarResourceId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByC_S_First(
		long calendarResourceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByC_S_Last(
		long calendarResourceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking[] findByC_S_PrevAndNext(
		long calendarBookingId, long calendarResourceId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @return the matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S_E(
		long calendarId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S_E(
		long calendarId, java.util.Date startDate, java.util.Date endDate,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_S_E(
		long calendarId, java.util.Date startDate, java.util.Date endDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByC_S_E_First(
		long calendarId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking findByC_S_E_Last(
		long calendarId, java.util.Date startDate, java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.calendar.model.CalendarBooking[] findByC_S_E_PrevAndNext(
		long calendarBookingId, long calendarId, java.util.Date startDate,
		java.util.Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the calendar bookings.
	*
	* @return the calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.calendar.model.CalendarBooking> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the calendar bookings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the calendar booking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar booking that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchBookingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the calendar bookings where calendarId = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCalendarId(long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the calendar bookings where calendarResourceId = &#63; from the database.
	*
	* @param calendarResourceId the calendar resource ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCalendarResourceId(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the calendar bookings where calendarResourceId = &#63; and status = &#63; from the database.
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_S(long calendarResourceId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_S_E(long calendarId, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the calendar bookings from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar bookings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar bookings where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public int countByCalendarId(long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar bookings where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public int countByCalendarResourceId(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar bookings where calendarResourceId = &#63; and status = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @param status the status
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_S(long calendarResourceId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	*
	* @param calendarId the calendar ID
	* @param startDate the start date
	* @param endDate the end date
	* @return the number of matching calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_S_E(long calendarId, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar bookings.
	*
	* @return the number of calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}