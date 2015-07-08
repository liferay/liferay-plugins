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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the calendar service. This utility wraps {@link com.liferay.calendar.service.persistence.impl.CalendarPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarPersistence
 * @see com.liferay.calendar.service.persistence.impl.CalendarPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Calendar> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Calendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Calendar> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Calendar update(Calendar calendar) {
		return getPersistence().update(calendar);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Calendar update(Calendar calendar,
		ServiceContext serviceContext) {
		return getPersistence().update(calendar, serviceContext);
	}

	/**
	* Returns all the calendars where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the matching calendars
	*/
	public static List<Calendar> findByResourceBlockId(long resourceBlockId) {
		return getPersistence().findByResourceBlockId(resourceBlockId);
	}

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
	public static List<Calendar> findByResourceBlockId(long resourceBlockId,
		int start, int end) {
		return getPersistence()
				   .findByResourceBlockId(resourceBlockId, start, end);
	}

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
	public static List<Calendar> findByResourceBlockId(long resourceBlockId,
		int start, int end, OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .findByResourceBlockId(resourceBlockId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByResourceBlockId_First(long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByResourceBlockId_First(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByResourceBlockId_First(long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .fetchByResourceBlockId_First(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByResourceBlockId_Last(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .fetchByResourceBlockId_Last(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the calendars before and after the current calendar in the ordered set where resourceBlockId = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public static Calendar[] findByResourceBlockId_PrevAndNext(
		long calendarId, long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByResourceBlockId_PrevAndNext(calendarId,
			resourceBlockId, orderByComparator);
	}

	/**
	* Removes all the calendars where resourceBlockId = &#63; from the database.
	*
	* @param resourceBlockId the resource block ID
	*/
	public static void removeByResourceBlockId(long resourceBlockId) {
		getPersistence().removeByResourceBlockId(resourceBlockId);
	}

	/**
	* Returns the number of calendars where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the number of matching calendars
	*/
	public static int countByResourceBlockId(long resourceBlockId) {
		return getPersistence().countByResourceBlockId(resourceBlockId);
	}

	/**
	* Returns all the calendars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendars
	*/
	public static List<Calendar> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<Calendar> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<Calendar> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Calendar> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByUuid_First(java.lang.String uuid,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the calendars before and after the current calendar in the ordered set where uuid = &#63;.
	*
	* @param calendarId the primary key of the current calendar
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public static Calendar[] findByUuid_PrevAndNext(long calendarId,
		java.lang.String uuid, OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByUuid_PrevAndNext(calendarId, uuid, orderByComparator);
	}

	/**
	* Removes all the calendars where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of calendars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendars
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCalendarException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the calendar where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar that was removed
	*/
	public static Calendar removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of calendars where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendars
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the calendars where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching calendars
	*/
	public static List<Calendar> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<Calendar> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<Calendar> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static Calendar[] findByUuid_C_PrevAndNext(long calendarId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(calendarId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the calendars where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of calendars where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching calendars
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the matching calendars
	*/
	public static List<Calendar> findByG_C(long groupId, long calendarResourceId) {
		return getPersistence().findByG_C(groupId, calendarResourceId);
	}

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
	public static List<Calendar> findByG_C(long groupId,
		long calendarResourceId, int start, int end) {
		return getPersistence()
				   .findByG_C(groupId, calendarResourceId, start, end);
	}

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
	public static List<Calendar> findByG_C(long groupId,
		long calendarResourceId, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .findByG_C(groupId, calendarResourceId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByG_C_First(long groupId,
		long calendarResourceId, OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByG_C_First(groupId, calendarResourceId,
			orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByG_C_First(long groupId,
		long calendarResourceId, OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .fetchByG_C_First(groupId, calendarResourceId,
			orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar
	* @throws NoSuchCalendarException if a matching calendar could not be found
	*/
	public static Calendar findByG_C_Last(long groupId,
		long calendarResourceId, OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByG_C_Last(groupId, calendarResourceId,
			orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByG_C_Last(long groupId,
		long calendarResourceId, OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .fetchByG_C_Last(groupId, calendarResourceId,
			orderByComparator);
	}

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
	public static Calendar[] findByG_C_PrevAndNext(long calendarId,
		long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByG_C_PrevAndNext(calendarId, groupId,
			calendarResourceId, orderByComparator);
	}

	/**
	* Returns all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the matching calendars that the user has permission to view
	*/
	public static List<Calendar> filterFindByG_C(long groupId,
		long calendarResourceId) {
		return getPersistence().filterFindByG_C(groupId, calendarResourceId);
	}

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
	public static List<Calendar> filterFindByG_C(long groupId,
		long calendarResourceId, int start, int end) {
		return getPersistence()
				   .filterFindByG_C(groupId, calendarResourceId, start, end);
	}

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
	public static List<Calendar> filterFindByG_C(long groupId,
		long calendarResourceId, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .filterFindByG_C(groupId, calendarResourceId, start, end,
			orderByComparator);
	}

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
	public static Calendar[] filterFindByG_C_PrevAndNext(long calendarId,
		long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .filterFindByG_C_PrevAndNext(calendarId, groupId,
			calendarResourceId, orderByComparator);
	}

	/**
	* Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	*/
	public static void removeByG_C(long groupId, long calendarResourceId) {
		getPersistence().removeByG_C(groupId, calendarResourceId);
	}

	/**
	* Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the number of matching calendars
	*/
	public static int countByG_C(long groupId, long calendarResourceId) {
		return getPersistence().countByG_C(groupId, calendarResourceId);
	}

	/**
	* Returns the number of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @return the number of matching calendars that the user has permission to view
	*/
	public static int filterCountByG_C(long groupId, long calendarResourceId) {
		return getPersistence().filterCountByG_C(groupId, calendarResourceId);
	}

	/**
	* Returns all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the matching calendars
	*/
	public static List<Calendar> findByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar) {
		return getPersistence()
				   .findByG_C_D(groupId, calendarResourceId, defaultCalendar);
	}

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
	public static List<Calendar> findByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end) {
		return getPersistence()
				   .findByG_C_D(groupId, calendarResourceId, defaultCalendar,
			start, end);
	}

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
	public static List<Calendar> findByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .findByG_C_D(groupId, calendarResourceId, defaultCalendar,
			start, end, orderByComparator);
	}

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
	public static Calendar findByG_C_D_First(long groupId,
		long calendarResourceId, boolean defaultCalendar,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByG_C_D_First(groupId, calendarResourceId,
			defaultCalendar, orderByComparator);
	}

	/**
	* Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByG_C_D_First(long groupId,
		long calendarResourceId, boolean defaultCalendar,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .fetchByG_C_D_First(groupId, calendarResourceId,
			defaultCalendar, orderByComparator);
	}

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
	public static Calendar findByG_C_D_Last(long groupId,
		long calendarResourceId, boolean defaultCalendar,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByG_C_D_Last(groupId, calendarResourceId,
			defaultCalendar, orderByComparator);
	}

	/**
	* Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	*/
	public static Calendar fetchByG_C_D_Last(long groupId,
		long calendarResourceId, boolean defaultCalendar,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .fetchByG_C_D_Last(groupId, calendarResourceId,
			defaultCalendar, orderByComparator);
	}

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
	public static Calendar[] findByG_C_D_PrevAndNext(long calendarId,
		long groupId, long calendarResourceId, boolean defaultCalendar,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .findByG_C_D_PrevAndNext(calendarId, groupId,
			calendarResourceId, defaultCalendar, orderByComparator);
	}

	/**
	* Returns all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the matching calendars that the user has permission to view
	*/
	public static List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar) {
		return getPersistence()
				   .filterFindByG_C_D(groupId, calendarResourceId,
			defaultCalendar);
	}

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
	public static List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end) {
		return getPersistence()
				   .filterFindByG_C_D(groupId, calendarResourceId,
			defaultCalendar, start, end);
	}

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
	public static List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence()
				   .filterFindByG_C_D(groupId, calendarResourceId,
			defaultCalendar, start, end, orderByComparator);
	}

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
	public static Calendar[] filterFindByG_C_D_PrevAndNext(long calendarId,
		long groupId, long calendarResourceId, boolean defaultCalendar,
		OrderByComparator<Calendar> orderByComparator)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence()
				   .filterFindByG_C_D_PrevAndNext(calendarId, groupId,
			calendarResourceId, defaultCalendar, orderByComparator);
	}

	/**
	* Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63; from the database.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	*/
	public static void removeByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) {
		getPersistence()
			.removeByG_C_D(groupId, calendarResourceId, defaultCalendar);
	}

	/**
	* Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the number of matching calendars
	*/
	public static int countByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) {
		return getPersistence()
				   .countByG_C_D(groupId, calendarResourceId, defaultCalendar);
	}

	/**
	* Returns the number of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	*
	* @param groupId the group ID
	* @param calendarResourceId the calendar resource ID
	* @param defaultCalendar the default calendar
	* @return the number of matching calendars that the user has permission to view
	*/
	public static int filterCountByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) {
		return getPersistence()
				   .filterCountByG_C_D(groupId, calendarResourceId,
			defaultCalendar);
	}

	/**
	* Caches the calendar in the entity cache if it is enabled.
	*
	* @param calendar the calendar
	*/
	public static void cacheResult(Calendar calendar) {
		getPersistence().cacheResult(calendar);
	}

	/**
	* Caches the calendars in the entity cache if it is enabled.
	*
	* @param calendars the calendars
	*/
	public static void cacheResult(List<Calendar> calendars) {
		getPersistence().cacheResult(calendars);
	}

	/**
	* Creates a new calendar with the primary key. Does not add the calendar to the database.
	*
	* @param calendarId the primary key for the new calendar
	* @return the new calendar
	*/
	public static Calendar create(long calendarId) {
		return getPersistence().create(calendarId);
	}

	/**
	* Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar that was removed
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public static Calendar remove(long calendarId)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence().remove(calendarId);
	}

	public static Calendar updateImpl(Calendar calendar) {
		return getPersistence().updateImpl(calendar);
	}

	/**
	* Returns the calendar with the primary key or throws a {@link NoSuchCalendarException} if it could not be found.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar
	* @throws NoSuchCalendarException if a calendar with the primary key could not be found
	*/
	public static Calendar findByPrimaryKey(long calendarId)
		throws com.liferay.calendar.NoSuchCalendarException {
		return getPersistence().findByPrimaryKey(calendarId);
	}

	/**
	* Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	*/
	public static Calendar fetchByPrimaryKey(long calendarId) {
		return getPersistence().fetchByPrimaryKey(calendarId);
	}

	public static java.util.Map<java.io.Serializable, Calendar> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the calendars.
	*
	* @return the calendars
	*/
	public static List<Calendar> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Calendar> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Calendar> findAll(int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the calendars from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of calendars.
	*
	* @return the number of calendars
	*/
	public static int countAll() {
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

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(CalendarPersistence persistence) {
	}

	private static CalendarPersistence _persistence;
}