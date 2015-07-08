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

import com.liferay.calendar.model.Calendar;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.persistence.impl.CalendarPersistenceImpl
 * @see CalendarUtil
 * @generated
 */
@ProviderType
public interface CalendarPersistence extends BasePersistence<Calendar> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarUtil} to access the calendar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the calendars where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the matching calendars
	*/
	public java.util.List<Calendar> findByResourceBlockId(long resourceBlockId);

	/**
	* Returns a range of all the calendars where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	*/
	public java.util.List<Calendar> findByResourceBlockId(
		long resourceBlockId, int start, int end);

	/**
	* Returns an ordered range of all the calendars where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByResourceBlockId(
		long resourceBlockId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the first calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByResourceBlockId_First(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the first calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByResourceBlockId_First(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the last calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByResourceBlockId_Last(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the last calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByResourceBlockId_Last(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] findByResourceBlockId_PrevAndNext(long calendarId,
		long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Removes all the calendars where resourceBlockId = &#63; from the database.
	*
	* @param resourceBlockId the resource block ID
	*/
	public void removeByResourceBlockId(long resourceBlockId);

	/**
	* Returns the number of calendars where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the number of matching calendars
	*/
	public int countByResourceBlockId(long resourceBlockId);

	/**
	* Returns all the calendars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendars
	*/
	public java.util.List<Calendar> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the calendars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	*/
	public java.util.List<Calendar> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the calendars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the first calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the first calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the last calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the last calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set where uuid = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] findByUuid_PrevAndNext(long calendarId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Removes all the calendars where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of calendars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendars
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCalendarException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the calendar where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar that was removed
	*/
	public Calendar removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the number of calendars where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendars
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the calendars where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching calendars
	*/
	public java.util.List<Calendar> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the calendars where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	*/
	public java.util.List<Calendar> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the calendars where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the first calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the first calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the last calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the last calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] findByUuid_C_PrevAndNext(long calendarId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Removes all the calendars where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of calendars where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching calendars
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the matching calendars
	*/
	public java.util.List<Calendar> findByG_C(long groupId,
		long calendarResourceId);

	/**
	* Returns a range of all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	*/
	public java.util.List<Calendar> findByG_C(long groupId,
		long calendarResourceId, int start, int end);

	/**
	* Returns an ordered range of all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByG_C(long groupId,
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByG_C_First(long groupId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByG_C_First(long groupId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByG_C_Last(long groupId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByG_C_Last(long groupId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] findByG_C_PrevAndNext(long calendarId, long groupId,
		long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the matching calendars that the user has permission to view
	*/
	public java.util.List<Calendar> filterFindByG_C(long groupId,
		long calendarResourceId);

	/**
	* Returns a range of all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars that the user has permission to view
	*/
	public java.util.List<Calendar> filterFindByG_C(long groupId,
		long calendarResourceId, int start, int end);

	/**
	* Returns an ordered range of all the calendars that the user has permissions to view where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars that the user has permission to view
	*/
	public java.util.List<Calendar> filterFindByG_C(long groupId,
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] filterFindByG_C_PrevAndNext(long calendarId,
		long groupId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	*/
	public void removeByG_C(long groupId, long calendarResourceId);

	/**
	* Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the number of matching calendars
	*/
	public int countByG_C(long groupId, long calendarResourceId);

	/**
	* Returns the number of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the number of matching calendars that the user has permission to view
	*/
	public int filterCountByG_C(long groupId, long calendarResourceId);

	/**
	* Returns all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the matching calendars
	*/
	public java.util.List<Calendar> findByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar);

	/**
	* Returns a range of all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	*/
	public java.util.List<Calendar> findByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end);

	/**
	* Returns an ordered range of all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	*/
	public java.util.List<Calendar> findByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByG_C_D_First(long groupId, long calendarResourceId,
		boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByG_C_D_First(long groupId, long calendarResourceId,
		boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public Calendar findByG_C_D_Last(long groupId, long calendarResourceId,
		boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public Calendar fetchByG_C_D_Last(long groupId, long calendarResourceId,
		boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] findByG_C_D_PrevAndNext(long calendarId, long groupId,
		long calendarResourceId, boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the matching calendars that the user has permission to view
	*/
	public java.util.List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar);

	/**
	* Returns a range of all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars that the user has permission to view
	*/
	public java.util.List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end);

	/**
	* Returns an ordered range of all the calendars that the user has permissions to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars that the user has permission to view
	*/
	public java.util.List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Returns the calendars before and after the current calendar in the ordered set of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar[] filterFindByG_C_D_PrevAndNext(long calendarId,
		long groupId, long calendarResourceId, boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63; from the database.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	*/
	public void removeByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar);

	/**
	* Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the number of matching calendars
	*/
	public int countByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar);

	/**
	* Returns the number of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the number of matching calendars that the user has permission to view
	*/
	public int filterCountByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar);

	/**
	* Caches the calendar in the entity cache if it is enabled.
	*
	* @param calendar the calendar
	*/
	public void cacheResult(Calendar calendar);

	/**
	* Caches the calendars in the entity cache if it is enabled.
	*
	* @param calendars the calendars
	*/
	public void cacheResult(java.util.List<Calendar> calendars);

	/**
	* Creates a new calendar with the primary key. Does not add the calendar to the database.
	*
	* @param calendarId the primary key for the new calendar
	* @return the new calendar
	*/
	public Calendar create(long calendarId);

	/**
	* Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar that was removed
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar remove(long calendarId)
		throws com.liferay.calendar.NoSuchCalendarException;

	public Calendar updateImpl(Calendar calendar);

	/**
	* Returns the calendar with the primary key or throws a {@link NoSuchCalendarException} if it could not be found.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public Calendar findByPrimaryKey(long calendarId)
		throws com.liferay.calendar.NoSuchCalendarException;

	/**
	* Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	*/
	public Calendar fetchByPrimaryKey(long calendarId);

	@Override
	public java.util.Map<java.io.Serializable, Calendar> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the calendars.
	*
	* @return the calendars
	*/
	public java.util.List<Calendar> findAll();

	/**
	* Returns a range of all the calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of calendars
	*/
	public java.util.List<Calendar> findAll(int start, int end);

	/**
	* Returns an ordered range of all the calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendars
	*/
	public java.util.List<Calendar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Calendar> orderByComparator);

	/**
	* Removes all the calendars from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of calendars.
	*
	* @return the number of calendars
	*/
	public int countAll();
}