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

import com.liferay.calendar.model.CalendarEvent;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the calendar event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarEventPersistenceImpl
 * @see CalendarEventUtil
 * @generated
 */
public interface CalendarEventPersistence extends BasePersistence<CalendarEvent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarEventUtil} to access the calendar event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the calendar event in the entity cache if it is enabled.
	*
	* @param calendarEvent the calendar event
	*/
	public void cacheResult(
		com.liferay.calendar.model.CalendarEvent calendarEvent);

	/**
	* Caches the calendar events in the entity cache if it is enabled.
	*
	* @param calendarEvents the calendar events
	*/
	public void cacheResult(
		java.util.List<com.liferay.calendar.model.CalendarEvent> calendarEvents);

	/**
	* Creates a new calendar event with the primary key. Does not add the calendar event to the database.
	*
	* @param calendarEventId the primary key for the new calendar event
	* @return the new calendar event
	*/
	public com.liferay.calendar.model.CalendarEvent create(long calendarEventId);

	/**
	* Removes the calendar event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarEventId the primary key of the calendar event
	* @return the calendar event that was removed
	* @throws com.liferay.calendar.NoSuchEventException if a calendar event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent remove(long calendarEventId)
		throws com.liferay.calendar.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.calendar.model.CalendarEvent updateImpl(
		com.liferay.calendar.model.CalendarEvent calendarEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar event with the primary key or throws a {@link com.liferay.calendar.NoSuchEventException} if it could not be found.
	*
	* @param calendarEventId the primary key of the calendar event
	* @return the calendar event
	* @throws com.liferay.calendar.NoSuchEventException if a calendar event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent findByPrimaryKey(
		long calendarEventId)
		throws com.liferay.calendar.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarEventId the primary key of the calendar event
	* @return the calendar event, or <code>null</code> if a calendar event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent fetchByPrimaryKey(
		long calendarEventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the calendar events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarEvent> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the calendar events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar events
	* @param end the upper bound of the range of calendar events (not inclusive)
	* @return the range of matching calendar events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarEvent> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the calendar events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar events
	* @param end the upper bound of the range of calendar events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarEvent> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first calendar event in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar event
	* @throws com.liferay.calendar.NoSuchEventException if a matching calendar event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last calendar event in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar event
	* @throws com.liferay.calendar.NoSuchEventException if a matching calendar event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar events before and after the current calendar event in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarEventId the primary key of the current calendar event
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar event
	* @throws com.liferay.calendar.NoSuchEventException if a calendar event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent[] findByUuid_PrevAndNext(
		long calendarEventId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar event where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchEventException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar event
	* @throws com.liferay.calendar.NoSuchEventException if a matching calendar event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar event, or <code>null</code> if a matching calendar event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar event, or <code>null</code> if a matching calendar event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the calendar events.
	*
	* @return the calendar events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarEvent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the calendar events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of calendar events
	* @param end the upper bound of the range of calendar events (not inclusive)
	* @return the range of calendar events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarEvent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the calendar events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of calendar events
	* @param end the upper bound of the range of calendar events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendar events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the calendar events where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the calendar event where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the calendar events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar events
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar events where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendar events
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar events.
	*
	* @return the number of calendar events
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}