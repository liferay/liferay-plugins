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

import com.liferay.calendar.model.CalendarResource;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the calendar resource service. This utility wraps {@link CalendarResourcePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarResourcePersistence
 * @see CalendarResourcePersistenceImpl
 * @generated
 */
public class CalendarResourceUtil {
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
	public static void clearCache(CalendarResource calendarResource) {
		getPersistence().clearCache(calendarResource);
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
	public static List<CalendarResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CalendarResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CalendarResource> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static CalendarResource update(CalendarResource calendarResource)
		throws SystemException {
		return getPersistence().update(calendarResource);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static CalendarResource update(CalendarResource calendarResource,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(calendarResource, serviceContext);
	}

	/**
	* Returns all the calendar resources where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByResourceBlockId(
		long resourceBlockId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResourceBlockId(resourceBlockId);
	}

	/**
	* Returns a range of all the calendar resources where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByResourceBlockId(
		long resourceBlockId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId(resourceBlockId, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByResourceBlockId(
		long resourceBlockId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId(resourceBlockId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByResourceBlockId_First(
		long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId_First(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByResourceBlockId_First(
		long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByResourceBlockId_First(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByResourceBlockId_Last(
		long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId_Last(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByResourceBlockId_Last(
		long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByResourceBlockId_Last(resourceBlockId,
			orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] findByResourceBlockId_PrevAndNext(
		long calendarResourceId, long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourceBlockId_PrevAndNext(calendarResourceId,
			resourceBlockId, orderByComparator);
	}

	/**
	* Removes all the calendar resources where resourceBlockId = &#63; from the database.
	*
	* @param resourceBlockId the resource block ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByResourceBlockId(long resourceBlockId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByResourceBlockId(resourceBlockId);
	}

	/**
	* Returns the number of calendar resources where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByResourceBlockId(long resourceBlockId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByResourceBlockId(resourceBlockId);
	}

	/**
	* Returns all the calendar resources where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the calendar resources where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where uuid = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] findByUuid_PrevAndNext(
		long calendarResourceId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(calendarResourceId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the calendar resources where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of calendar resources where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the calendar resource where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchResourceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the calendar resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the calendar resource where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar resource that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of calendar resources where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the calendar resources where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the calendar resources where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] findByUuid_C_PrevAndNext(
		long calendarResourceId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(calendarResourceId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the calendar resources where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of calendar resources where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the calendar resources where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the calendar resources where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where groupId = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] findByGroupId_PrevAndNext(
		long calendarResourceId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(calendarResourceId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the calendar resources that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the calendar resources that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set of calendar resources that the user has permission to view where groupId = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] filterFindByGroupId_PrevAndNext(
		long calendarResourceId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(calendarResourceId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the calendar resources where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of calendar resources where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of calendar resources that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the calendar resources where active = &#63;.
	*
	* @param active the active
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByActive(
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByActive(active);
	}

	/**
	* Returns a range of all the calendar resources where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByActive(
		boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByActive(active, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByActive(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByActive(active, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByActive_First(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByActive_First(active, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByActive_First(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByActive_First(active, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByActive_Last(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByActive_Last(
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where active = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] findByActive_PrevAndNext(
		long calendarResourceId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByActive_PrevAndNext(calendarResourceId, active,
			orderByComparator);
	}

	/**
	* Removes all the calendar resources where active = &#63; from the database.
	*
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByActive(boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByActive(active);
	}

	/**
	* Returns the number of calendar resources where active = &#63;.
	*
	* @param active the active
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByActive(boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByActive(active);
	}

	/**
	* Returns all the calendar resources where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_C(
		long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, code);
	}

	/**
	* Returns a range of all the calendar resources where groupId = &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_C(
		long groupId, java.lang.String code, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, code, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where groupId = &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_C(
		long groupId, java.lang.String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C(groupId, code, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByG_C_First(
		long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C_First(groupId, code, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByG_C_First(
		long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_C_First(groupId, code, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByG_C_Last(
		long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C_Last(groupId, code, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByG_C_Last(
		long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C_Last(groupId, code, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] findByG_C_PrevAndNext(
		long calendarResourceId, long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_PrevAndNext(calendarResourceId, groupId, code,
			orderByComparator);
	}

	/**
	* Returns all the calendar resources that the user has permission to view where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_C(
		long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_C(groupId, code);
	}

	/**
	* Returns a range of all the calendar resources that the user has permission to view where groupId = &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_C(
		long groupId, java.lang.String code, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_C(groupId, code, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources that the user has permissions to view where groupId = &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_C(
		long groupId, java.lang.String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_C(groupId, code, start, end, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set of calendar resources that the user has permission to view where groupId = &#63; and code = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] filterFindByG_C_PrevAndNext(
		long calendarResourceId, long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_C_PrevAndNext(calendarResourceId, groupId,
			code, orderByComparator);
	}

	/**
	* Returns all the calendar resources that the user has permission to view where groupId = any &#63; and code = &#63;.
	*
	* @param groupIds the group IDs
	* @param code the code
	* @return the matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_C(
		long[] groupIds, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_C(groupIds, code);
	}

	/**
	* Returns a range of all the calendar resources that the user has permission to view where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_C(
		long[] groupIds, java.lang.String code, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_C(groupIds, code, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources that the user has permission to view where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_C(
		long[] groupIds, java.lang.String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_C(groupIds, code, start, end,
			orderByComparator);
	}

	/**
	* Returns all the calendar resources where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_C(
		long[] groupIds, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupIds, code);
	}

	/**
	* Returns a range of all the calendar resources where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_C(
		long[] groupIds, java.lang.String code, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupIds, code, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_C(
		long[] groupIds, java.lang.String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C(groupIds, code, start, end, orderByComparator);
	}

	/**
	* Removes all the calendar resources where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID
	* @param code the code
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C(groupId, code);
	}

	/**
	* Returns the number of calendar resources where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupId, code);
	}

	/**
	* Returns the number of calendar resources where groupId = any &#63; and code = &#63;.
	*
	* @param groupIds the group IDs
	* @param code the code
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long[] groupIds, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupIds, code);
	}

	/**
	* Returns the number of calendar resources that the user has permission to view where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the number of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_C(groupId, code);
	}

	/**
	* Returns the number of calendar resources that the user has permission to view where groupId = any &#63; and code = &#63;.
	*
	* @param groupIds the group IDs
	* @param code the code
	* @return the number of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_C(long[] groupIds, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_C(groupIds, code);
	}

	/**
	* Returns all the calendar resources where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_A(
		long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, active);
	}

	/**
	* Returns a range of all the calendar resources where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_A(
		long groupId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, active, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_A(groupId, active, start, end, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByG_A_First(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_A_First(groupId, active, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByG_A_First(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_A_First(groupId, active, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByG_A_Last(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_A_Last(groupId, active, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByG_A_Last(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_A_Last(groupId, active, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] findByG_A_PrevAndNext(
		long calendarResourceId, long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_A_PrevAndNext(calendarResourceId, groupId, active,
			orderByComparator);
	}

	/**
	* Returns all the calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_A(
		long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_A(groupId, active);
	}

	/**
	* Returns a range of all the calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_A(
		long groupId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_A(groupId, active, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources that the user has permissions to view where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_A(groupId, active, start, end,
			orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set of calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] filterFindByG_A_PrevAndNext(
		long calendarResourceId, long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_A_PrevAndNext(calendarResourceId, groupId,
			active, orderByComparator);
	}

	/**
	* Removes all the calendar resources where groupId = &#63; and active = &#63; from the database.
	*
	* @param groupId the group ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_A(long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_A(groupId, active);
	}

	/**
	* Returns the number of calendar resources where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_A(long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_A(groupId, active);
	}

	/**
	* Returns the number of calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching calendar resources that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_A(long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_A(groupId, active);
	}

	/**
	* Returns the calendar resource where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.calendar.NoSuchResourceException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByC_C(
		long classNameId, long classPK)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns the calendar resource where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C(classNameId, classPK);
	}

	/**
	* Returns the calendar resource where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByC_C(
		long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C(classNameId, classPK, retrieveFromCache);
	}

	/**
	* Removes the calendar resource where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the calendar resource that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource removeByC_C(
		long classNameId, long classPK)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of calendar resources where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns all the calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @return the matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByC_C_A(
		long companyId, java.lang.String code, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C_A(companyId, code, active);
	}

	/**
	* Returns a range of all the calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByC_C_A(
		long companyId, java.lang.String code, boolean active, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C_A(companyId, code, active, start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByC_C_A(
		long companyId, java.lang.String code, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_A(companyId, code, active, start, end,
			orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByC_C_A_First(
		long companyId, java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_A_First(companyId, code, active, orderByComparator);
	}

	/**
	* Returns the first calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByC_C_A_First(
		long companyId, java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_A_First(companyId, code, active,
			orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByC_C_A_Last(
		long companyId, java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_A_Last(companyId, code, active, orderByComparator);
	}

	/**
	* Returns the last calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByC_C_A_Last(
		long companyId, java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_A_Last(companyId, code, active, orderByComparator);
	}

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource[] findByC_C_A_PrevAndNext(
		long calendarResourceId, long companyId, java.lang.String code,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_A_PrevAndNext(calendarResourceId, companyId,
			code, active, orderByComparator);
	}

	/**
	* Removes all the calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_A(long companyId, java.lang.String code,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C_A(companyId, code, active);
	}

	/**
	* Returns the number of calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @return the number of matching calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_A(long companyId, java.lang.String code,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C_A(companyId, code, active);
	}

	/**
	* Caches the calendar resource in the entity cache if it is enabled.
	*
	* @param calendarResource the calendar resource
	*/
	public static void cacheResult(
		com.liferay.calendar.model.CalendarResource calendarResource) {
		getPersistence().cacheResult(calendarResource);
	}

	/**
	* Caches the calendar resources in the entity cache if it is enabled.
	*
	* @param calendarResources the calendar resources
	*/
	public static void cacheResult(
		java.util.List<com.liferay.calendar.model.CalendarResource> calendarResources) {
		getPersistence().cacheResult(calendarResources);
	}

	/**
	* Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	*
	* @param calendarResourceId the primary key for the new calendar resource
	* @return the new calendar resource
	*/
	public static com.liferay.calendar.model.CalendarResource create(
		long calendarResourceId) {
		return getPersistence().create(calendarResourceId);
	}

	/**
	* Removes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource that was removed
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource remove(
		long calendarResourceId)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(calendarResourceId);
	}

	public static com.liferay.calendar.model.CalendarResource updateImpl(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(calendarResource);
	}

	/**
	* Returns the calendar resource with the primary key or throws a {@link com.liferay.calendar.NoSuchResourceException} if it could not be found.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource
	* @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource findByPrimaryKey(
		long calendarResourceId)
		throws com.liferay.calendar.NoSuchResourceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(calendarResourceId);
	}

	/**
	* Returns the calendar resource with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource, or <code>null</code> if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchByPrimaryKey(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(calendarResourceId);
	}

	/**
	* Returns all the calendar resources.
	*
	* @return the calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the calendar resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the calendar resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the calendar resources from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of calendar resources.
	*
	* @return the number of calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CalendarResourcePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CalendarResourcePersistence)PortletBeanLocatorUtil.locate(com.liferay.calendar.service.ClpSerializer.getServletContextName(),
					CalendarResourcePersistence.class.getName());

			ReferenceRegistry.registerReference(CalendarResourceUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(CalendarResourcePersistence persistence) {
	}

	private static CalendarResourcePersistence _persistence;
}