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

package com.liferay.calendar.service.persistence;

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.calendar.service.persistence.impl.CalendarBookingPersistenceImpl
 * @see CalendarBookingUtil
 * @generated
 */
@ProviderType
public interface CalendarBookingPersistence extends BasePersistence<CalendarBooking> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarBookingUtil} to access the calendar booking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the calendar bookings where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByResourceBlockId(
		long resourceBlockId);

	/**
	* Returns a range of all the calendar bookings where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByResourceBlockId(
		long resourceBlockId, int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByResourceBlockId(
		long resourceBlockId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the first calendar booking in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByResourceBlockId_First(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the first calendar booking in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByResourceBlockId_First(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the last calendar booking in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByResourceBlockId_Last(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the last calendar booking in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByResourceBlockId_Last(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where resourceBlockId = &#63;.
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking[] findByResourceBlockId_PrevAndNext(
		long calendarBookingId, long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Removes all the calendar bookings where resourceBlockId = &#63; from the database.
	*
	* @param resourceBlockId the resource block ID
	*/
	public void removeByResourceBlockId(long resourceBlockId);

	/**
	* Returns the number of calendar bookings where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the number of matching calendar bookings
	*/
	public int countByResourceBlockId(long resourceBlockId);

	/**
	* Returns all the calendar bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the calendar bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the first calendar booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the first calendar booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the last calendar booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the last calendar booking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where uuid = &#63;.
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking[] findByUuid_PrevAndNext(long calendarBookingId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Removes all the calendar bookings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of calendar bookings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar bookings
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the calendar booking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar booking that was removed
	*/
	public CalendarBooking removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the number of calendar bookings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendar bookings
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the calendar bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the calendar bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the first calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the first calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the last calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the last calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking[] findByUuid_C_PrevAndNext(long calendarBookingId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Removes all the calendar bookings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of calendar bookings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching calendar bookings
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the calendar bookings where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByCalendarId(long calendarId);

	/**
	* Returns a range of all the calendar bookings where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByCalendarId(long calendarId,
		int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByCalendarId(long calendarId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the first calendar booking in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByCalendarId_First(long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the first calendar booking in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByCalendarId_First(long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the last calendar booking in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByCalendarId_Last(long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the last calendar booking in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByCalendarId_Last(long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarId = &#63;.
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking[] findByCalendarId_PrevAndNext(
		long calendarBookingId, long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Removes all the calendar bookings where calendarId = &#63; from the database.
	*
	* @param calendarId the calendar ID
	*/
	public void removeByCalendarId(long calendarId);

	/**
	* Returns the number of calendar bookings where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the number of matching calendar bookings
	*/
	public int countByCalendarId(long calendarId);

	/**
	* Returns all the calendar bookings where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId);

	/**
	* Returns a range of all the calendar bookings where calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the first calendar booking in the ordered set where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByCalendarResourceId_First(
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the first calendar booking in the ordered set where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByCalendarResourceId_First(
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the last calendar booking in the ordered set where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByCalendarResourceId_Last(
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the last calendar booking in the ordered set where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByCalendarResourceId_Last(
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarResourceId = &#63;.
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking[] findByCalendarResourceId_PrevAndNext(
		long calendarBookingId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Removes all the calendar bookings where calendarResourceId = &#63; from the database.
	*
	* @param calendarResourceId the calendar resource ID
	*/
	public void removeByCalendarResourceId(long calendarResourceId);

	/**
	* Returns the number of calendar bookings where calendarResourceId = &#63;.
	*
	* @param calendarResourceId the calendar resource ID
	* @return the number of matching calendar bookings
	*/
	public int countByCalendarResourceId(long calendarResourceId);

	/**
	* Returns all the calendar bookings where parentCalendarBookingId = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId);

	/**
	* Returns a range of all the calendar bookings where parentCalendarBookingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId, int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where parentCalendarBookingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByParentCalendarBookingId_First(
		long parentCalendarBookingId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByParentCalendarBookingId_First(
		long parentCalendarBookingId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the last calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByParentCalendarBookingId_Last(
		long parentCalendarBookingId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the last calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByParentCalendarBookingId_Last(
		long parentCalendarBookingId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking[] findByParentCalendarBookingId_PrevAndNext(
		long calendarBookingId, long parentCalendarBookingId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Removes all the calendar bookings where parentCalendarBookingId = &#63; from the database.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	*/
	public void removeByParentCalendarBookingId(long parentCalendarBookingId);

	/**
	* Returns the number of calendar bookings where parentCalendarBookingId = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @return the number of matching calendar bookings
	*/
	public int countByParentCalendarBookingId(long parentCalendarBookingId);

	/**
	* Returns the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param calendarId the calendar ID
	* @param parentCalendarBookingId the parent calendar booking ID
	* @return the matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByC_P(long calendarId,
		long parentCalendarBookingId)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param calendarId the calendar ID
	* @param parentCalendarBookingId the parent calendar booking ID
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByC_P(long calendarId,
		long parentCalendarBookingId);

	/**
	* Returns the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param calendarId the calendar ID
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByC_P(long calendarId,
		long parentCalendarBookingId, boolean retrieveFromCache);

	/**
	* Removes the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @param parentCalendarBookingId the parent calendar booking ID
	* @return the calendar booking that was removed
	*/
	public CalendarBooking removeByC_P(long calendarId,
		long parentCalendarBookingId)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the number of calendar bookings where calendarId = &#63; and parentCalendarBookingId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param parentCalendarBookingId the parent calendar booking ID
	* @return the number of matching calendar bookings
	*/
	public int countByC_P(long calendarId, long parentCalendarBookingId);

	/**
	* Returns the calendar booking where calendarId = &#63; and vEventUid = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param calendarId the calendar ID
	* @param vEventUid the v event uid
	* @return the matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByC_V(long calendarId, java.lang.String vEventUid)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the calendar booking where calendarId = &#63; and vEventUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param calendarId the calendar ID
	* @param vEventUid the v event uid
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByC_V(long calendarId,
		java.lang.String vEventUid);

	/**
	* Returns the calendar booking where calendarId = &#63; and vEventUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param calendarId the calendar ID
	* @param vEventUid the v event uid
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByC_V(long calendarId,
		java.lang.String vEventUid, boolean retrieveFromCache);

	/**
	* Removes the calendar booking where calendarId = &#63; and vEventUid = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @param vEventUid the v event uid
	* @return the calendar booking that was removed
	*/
	public CalendarBooking removeByC_V(long calendarId,
		java.lang.String vEventUid)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the number of calendar bookings where calendarId = &#63; and vEventUid = &#63;.
	*
	* @param calendarId the calendar ID
	* @param vEventUid the v event uid
	* @return the number of matching calendar bookings
	*/
	public int countByC_V(long calendarId, java.lang.String vEventUid);

	/**
	* Returns all the calendar bookings where calendarId = &#63; and status = &#63;.
	*
	* @param calendarId the calendar ID
	* @param status the status
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByC_S(long calendarId, int status);

	/**
	* Returns a range of all the calendar bookings where calendarId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param status the status
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByC_S(long calendarId,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where calendarId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param status the status
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByC_S(long calendarId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the first calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	*
	* @param calendarId the calendar ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByC_S_First(long calendarId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the first calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	*
	* @param calendarId the calendar ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByC_S_First(long calendarId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the last calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	*
	* @param calendarId the calendar ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByC_S_Last(long calendarId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the last calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	*
	* @param calendarId the calendar ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByC_S_Last(long calendarId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param calendarId the calendar ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking[] findByC_S_PrevAndNext(long calendarBookingId,
		long calendarId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns all the calendar bookings where calendarId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param statuses the statuses
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByC_S(long calendarId,
		int[] statuses);

	/**
	* Returns a range of all the calendar bookings where calendarId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param statuses the statuses
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByC_S(long calendarId,
		int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where calendarId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param statuses the statuses
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByC_S(long calendarId,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Removes all the calendar bookings where calendarId = &#63; and status = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @param status the status
	*/
	public void removeByC_S(long calendarId, int status);

	/**
	* Returns the number of calendar bookings where calendarId = &#63; and status = &#63;.
	*
	* @param calendarId the calendar ID
	* @param status the status
	* @return the number of matching calendar bookings
	*/
	public int countByC_S(long calendarId, int status);

	/**
	* Returns the number of calendar bookings where calendarId = &#63; and status = any &#63;.
	*
	* @param calendarId the calendar ID
	* @param statuses the statuses
	* @return the number of matching calendar bookings
	*/
	public int countByC_S(long calendarId, int[] statuses);

	/**
	* Returns all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @return the matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByP_S(
		long parentCalendarBookingId, int status);

	/**
	* Returns a range of all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByP_S(
		long parentCalendarBookingId, int status, int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar bookings
	*/
	public java.util.List<CalendarBooking> findByP_S(
		long parentCalendarBookingId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByP_S_First(long parentCalendarBookingId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByP_S_First(long parentCalendarBookingId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the last calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking
	* @throws NoSuchBookingException if a matching calendar booking could not be found
	*/
	public CalendarBooking findByP_S_Last(long parentCalendarBookingId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the last calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public CalendarBooking fetchByP_S_Last(long parentCalendarBookingId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Returns the calendar bookings before and after the current calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* @param calendarBookingId the primary key of the current calendar booking
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking[] findByP_S_PrevAndNext(long calendarBookingId,
		long parentCalendarBookingId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Removes all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63; from the database.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	*/
	public void removeByP_S(long parentCalendarBookingId, int status);

	/**
	* Returns the number of calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	*
	* @param parentCalendarBookingId the parent calendar booking ID
	* @param status the status
	* @return the number of matching calendar bookings
	*/
	public int countByP_S(long parentCalendarBookingId, int status);

	/**
	* Caches the calendar booking in the entity cache if it is enabled.
	*
	* @param calendarBooking the calendar booking
	*/
	public void cacheResult(CalendarBooking calendarBooking);

	/**
	* Caches the calendar bookings in the entity cache if it is enabled.
	*
	* @param calendarBookings the calendar bookings
	*/
	public void cacheResult(java.util.List<CalendarBooking> calendarBookings);

	/**
	* Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	*
	* @param calendarBookingId the primary key for the new calendar booking
	* @return the new calendar booking
	*/
	public CalendarBooking create(long calendarBookingId);

	/**
	* Removes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking that was removed
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking remove(long calendarBookingId)
		throws com.liferay.calendar.NoSuchBookingException;

	public CalendarBooking updateImpl(CalendarBooking calendarBooking);

	/**
	* Returns the calendar booking with the primary key or throws a {@link NoSuchBookingException} if it could not be found.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking
	* @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking findByPrimaryKey(long calendarBookingId)
		throws com.liferay.calendar.NoSuchBookingException;

	/**
	* Returns the calendar booking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking, or <code>null</code> if a calendar booking with the primary key could not be found
	*/
	public CalendarBooking fetchByPrimaryKey(long calendarBookingId);

	@Override
	public java.util.Map<java.io.Serializable, CalendarBooking> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the calendar bookings.
	*
	* @return the calendar bookings
	*/
	public java.util.List<CalendarBooking> findAll();

	/**
	* Returns a range of all the calendar bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of calendar bookings
	*/
	public java.util.List<CalendarBooking> findAll(int start, int end);

	/**
	* Returns an ordered range of all the calendar bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendar bookings
	*/
	public java.util.List<CalendarBooking> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarBooking> orderByComparator);

	/**
	* Removes all the calendar bookings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of calendar bookings.
	*
	* @return the number of calendar bookings
	*/
	public int countAll();
}