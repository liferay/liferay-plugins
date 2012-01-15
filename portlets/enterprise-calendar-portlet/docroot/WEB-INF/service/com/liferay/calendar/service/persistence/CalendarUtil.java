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

import com.liferay.calendar.model.Calendar;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the calendar service. This utility wraps {@link CalendarPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarPersistence
 * @see CalendarPersistenceImpl
 * @generated
 */
public class CalendarUtil {
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
	public static void clearCache(Calendar calendar) {
		getPersistence().clearCache(calendar);
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
	public static List<Calendar> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Calendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Calendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Calendar update(Calendar calendar, boolean merge)
		throws SystemException {
		return getPersistence().update(calendar, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Calendar update(Calendar calendar, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(calendar, merge, serviceContext);
	}

	/**
	* Caches the calendar in the entity cache if it is enabled.
	*
	* @param calendar the calendar
	*/
	public static void cacheResult(com.liferay.calendar.model.Calendar calendar) {
		getPersistence().cacheResult(calendar);
	}

	/**
	* Caches the calendars in the entity cache if it is enabled.
	*
	* @param calendars the calendars
	*/
	public static void cacheResult(
		java.util.List<com.liferay.calendar.model.Calendar> calendars) {
		getPersistence().cacheResult(calendars);
	}

	/**
	* Creates a new calendar with the primary key. Does not add the calendar to the database.
	*
	* @param calendarId the primary key for the new calendar
	* @return the new calendar
	*/
	public static com.liferay.calendar.model.Calendar create(long calendarId) {
		return getPersistence().create(calendarId);
	}

	/**
	* Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar that was removed
	* @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar remove(long calendarId)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(calendarId);
	}

	public static com.liferay.calendar.model.Calendar updateImpl(
		com.liferay.calendar.model.Calendar calendar, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(calendar, merge);
	}

	/**
	* Returns the calendar with the primary key or throws a {@link com.liferay.calendar.NoSuchCalendarException} if it could not be found.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByPrimaryKey(
		long calendarId)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(calendarId);
	}

	/**
	* Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar fetchByPrimaryKey(
		long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(calendarId);
	}

	/**
	* Returns all the calendars where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByResourceBlockId(
		long resourceBlockId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResourceBlockId(resourceBlockId);
	}

	/**
	* Returns a range of all the calendars where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByResourceBlockId(
		long resourceBlockId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId(resourceBlockId, start, end);
	}

	/**
	* Returns an ordered range of all the calendars where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByResourceBlockId(
		long resourceBlockId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId(resourceBlockId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByResourceBlockId_First(
		long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId_First(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByResourceBlockId_Last(
		long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId_Last(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the calendars before and after the current calendar in the ordered set where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the primary key of the current calendar
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar[] findByResourceBlockId_PrevAndNext(
		long calendarId, long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId_PrevAndNext(calendarId,
			resourceBlockId, orderByComparator);
	}

	/**
	* Returns all the calendars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the calendars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the calendars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the calendars before and after the current calendar in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the primary key of the current calendar
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar[] findByUuid_PrevAndNext(
		long calendarId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(calendarId, uuid, orderByComparator);
	}

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchCalendarException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByG_C(
		long groupId, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, calendarResourceId);
	}

	/**
	* Returns a range of all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByG_C(
		long groupId, long calendarResourceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C(groupId, calendarResourceId, start, end);
	}

	/**
	* Returns an ordered range of all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByG_C(
		long groupId, long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C(groupId, calendarResourceId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByG_C_First(
		long groupId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_First(groupId, calendarResourceId,
			orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByG_C_Last(
		long groupId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_Last(groupId, calendarResourceId,
			orderByComparator);
	}

	/**
	* Returns the calendars before and after the current calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the primary key of the current calendar
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar[] findByG_C_PrevAndNext(
		long calendarId, long groupId, long calendarResourceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_PrevAndNext(calendarId, groupId,
			calendarResourceId, orderByComparator);
	}

	/**
	* Returns all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByG_C_D(
		long groupId, long calendarResourceId, boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_D(groupId, calendarResourceId, defaultCalendar);
	}

	/**
	* Returns a range of all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByG_C_D(
		long groupId, long calendarResourceId, boolean defaultCalendar,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_D(groupId, calendarResourceId, defaultCalendar,
			start, end);
	}

	/**
	* Returns an ordered range of all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findByG_C_D(
		long groupId, long calendarResourceId, boolean defaultCalendar,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_D(groupId, calendarResourceId, defaultCalendar,
			start, end, orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByG_C_D_First(
		long groupId, long calendarResourceId, boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_D_First(groupId, calendarResourceId,
			defaultCalendar, orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar findByG_C_D_Last(
		long groupId, long calendarResourceId, boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_D_Last(groupId, calendarResourceId,
			defaultCalendar, orderByComparator);
	}

	/**
	* Returns the calendars before and after the current calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param calendarId the primary key of the current calendar
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.Calendar[] findByG_C_D_PrevAndNext(
		long calendarId, long groupId, long calendarResourceId,
		boolean defaultCalendar,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_D_PrevAndNext(calendarId, groupId,
			calendarResourceId, defaultCalendar, orderByComparator);
	}

	/**
	* Returns all the calendars.
	*
	* @return the calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendars
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.Calendar> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the calendars where resourceBlockId = &#63; from the database.
	*
	* @param resourceBlockId the resource block ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByResourceBlockId(long resourceBlockId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByResourceBlockId(resourceBlockId);
	}

	/**
	* Removes all the calendars where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the calendar where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchCalendarException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C(long groupId, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C(groupId, calendarResourceId);
	}

	/**
	* Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63; from the database.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_C_D(groupId, calendarResourceId, defaultCalendar);
	}

	/**
	* Removes all the calendars from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of calendars where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the number of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static int countByResourceBlockId(long resourceBlockId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByResourceBlockId(resourceBlockId);
	}

	/**
	* Returns the number of calendars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of calendars where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the number of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long groupId, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupId, calendarResourceId);
	}

	/**
	* Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the number of matching calendars
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_C_D(groupId, calendarResourceId, defaultCalendar);
	}

	/**
	* Returns the number of calendars.
	*
	* @return the number of calendars
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CalendarPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CalendarPersistence)PortletBeanLocatorUtil.locate(com.liferay.calendar.service.ClpSerializer.getServletContextName(),
					CalendarPersistence.class.getName());

			ReferenceRegistry.registerReference(CalendarUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(CalendarPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(CalendarUtil.class, "_persistence");
	}

	private static CalendarPersistence _persistence;
}