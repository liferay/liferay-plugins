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

import com.liferay.calendar.model.CalendarResource;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the calendar resource service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.persistence.impl.CalendarResourcePersistenceImpl
 * @see CalendarResourceUtil
 * @generated
 */
@ProviderType
public interface CalendarResourcePersistence extends BasePersistence<CalendarResource> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarResourceUtil} to access the calendar resource persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the calendar resources where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByResourceBlockId(
		long resourceBlockId);

	/**
	* Returns a range of all the calendar resources where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByResourceBlockId(
		long resourceBlockId, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where resourceBlockId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourceBlockId the resource block ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByResourceBlockId(
		long resourceBlockId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the first calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByResourceBlockId_First(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the first calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByResourceBlockId_First(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the last calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByResourceBlockId_Last(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the last calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByResourceBlockId_Last(long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where resourceBlockId = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param resourceBlockId the resource block ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] findByResourceBlockId_PrevAndNext(
		long calendarResourceId, long resourceBlockId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Removes all the calendar resources where resourceBlockId = &#63; from the database.
	*
	* @param resourceBlockId the resource block ID
	*/
	public void removeByResourceBlockId(long resourceBlockId);

	/**
	* Returns the number of calendar resources where resourceBlockId = &#63;.
	*
	* @param resourceBlockId the resource block ID
	* @return the number of matching calendar resources
	*/
	public int countByResourceBlockId(long resourceBlockId);

	/**
	* Returns all the calendar resources where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the calendar resources where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the first calendar resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the first calendar resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the last calendar resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the last calendar resource in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where uuid = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] findByUuid_PrevAndNext(long calendarResourceId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Removes all the calendar resources where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of calendar resources where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar resources
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the calendar resource where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the calendar resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the calendar resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the calendar resource where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar resource that was removed
	*/
	public CalendarResource removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the number of calendar resources where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendar resources
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the calendar resources where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the calendar resources where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the first calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the first calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the last calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the last calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] findByUuid_C_PrevAndNext(
		long calendarResourceId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Removes all the calendar resources where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of calendar resources where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching calendar resources
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the calendar resources where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByGroupId(long groupId);

	/**
	* Returns a range of all the calendar resources where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where groupId = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] findByGroupId_PrevAndNext(
		long calendarResourceId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns all the calendar resources that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the calendar resources that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the calendar resources that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set of calendar resources that the user has permission to view where groupId = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] filterFindByGroupId_PrevAndNext(
		long calendarResourceId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Removes all the calendar resources where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of calendar resources where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching calendar resources
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of calendar resources that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching calendar resources that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the calendar resources where active = &#63;.
	*
	* @param active the active
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByActive(boolean active);

	/**
	* Returns a range of all the calendar resources where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByActive(boolean active,
		int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByActive(boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the first calendar resource in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the first calendar resource in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the last calendar resource in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the last calendar resource in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where active = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] findByActive_PrevAndNext(
		long calendarResourceId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Removes all the calendar resources where active = &#63; from the database.
	*
	* @param active the active
	*/
	public void removeByActive(boolean active);

	/**
	* Returns the number of calendar resources where active = &#63;.
	*
	* @param active the active
	* @return the number of matching calendar resources
	*/
	public int countByActive(boolean active);

	/**
	* Returns all the calendar resources where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_C(long groupId,
		java.lang.String code);

	/**
	* Returns a range of all the calendar resources where groupId = &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_C(long groupId,
		java.lang.String code, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where groupId = &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_C(long groupId,
		java.lang.String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByG_C_First(long groupId,
		java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByG_C_First(long groupId,
		java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByG_C_Last(long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByG_C_Last(long groupId,
		java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where groupId = &#63; and code = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] findByG_C_PrevAndNext(long calendarResourceId,
		long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns all the calendar resources that the user has permission to view where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_C(long groupId,
		java.lang.String code);

	/**
	* Returns a range of all the calendar resources that the user has permission to view where groupId = &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_C(long groupId,
		java.lang.String code, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources that the user has permissions to view where groupId = &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_C(long groupId,
		java.lang.String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set of calendar resources that the user has permission to view where groupId = &#63; and code = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param code the code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] filterFindByG_C_PrevAndNext(
		long calendarResourceId, long groupId, java.lang.String code,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns all the calendar resources that the user has permission to view where groupId = any &#63; and code = &#63;.
	*
	* @param groupIds the group IDs
	* @param code the code
	* @return the matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_C(long[] groupIds,
		java.lang.String code);

	/**
	* Returns a range of all the calendar resources that the user has permission to view where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_C(long[] groupIds,
		java.lang.String code, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources that the user has permission to view where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_C(long[] groupIds,
		java.lang.String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns all the calendar resources where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_C(long[] groupIds,
		java.lang.String code);

	/**
	* Returns a range of all the calendar resources where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_C(long[] groupIds,
		java.lang.String code, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where groupId = any &#63; and code = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param code the code
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_C(long[] groupIds,
		java.lang.String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Removes all the calendar resources where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID
	* @param code the code
	*/
	public void removeByG_C(long groupId, java.lang.String code);

	/**
	* Returns the number of calendar resources where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the number of matching calendar resources
	*/
	public int countByG_C(long groupId, java.lang.String code);

	/**
	* Returns the number of calendar resources where groupId = any &#63; and code = &#63;.
	*
	* @param groupIds the group IDs
	* @param code the code
	* @return the number of matching calendar resources
	*/
	public int countByG_C(long[] groupIds, java.lang.String code);

	/**
	* Returns the number of calendar resources that the user has permission to view where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the number of matching calendar resources that the user has permission to view
	*/
	public int filterCountByG_C(long groupId, java.lang.String code);

	/**
	* Returns the number of calendar resources that the user has permission to view where groupId = any &#63; and code = &#63;.
	*
	* @param groupIds the group IDs
	* @param code the code
	* @return the number of matching calendar resources that the user has permission to view
	*/
	public int filterCountByG_C(long[] groupIds, java.lang.String code);

	/**
	* Returns all the calendar resources where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_A(long groupId,
		boolean active);

	/**
	* Returns a range of all the calendar resources where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_A(long groupId,
		boolean active, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByG_A(long groupId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByG_A_First(long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the first calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByG_A_First(long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByG_A_Last(long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the last calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByG_A_Last(long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] findByG_A_PrevAndNext(long calendarResourceId,
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns all the calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_A(long groupId,
		boolean active);

	/**
	* Returns a range of all the calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_A(long groupId,
		boolean active, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources that the user has permissions to view where groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources that the user has permission to view
	*/
	public java.util.List<CalendarResource> filterFindByG_A(long groupId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set of calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] filterFindByG_A_PrevAndNext(
		long calendarResourceId, long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Removes all the calendar resources where groupId = &#63; and active = &#63; from the database.
	*
	* @param groupId the group ID
	* @param active the active
	*/
	public void removeByG_A(long groupId, boolean active);

	/**
	* Returns the number of calendar resources where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching calendar resources
	*/
	public int countByG_A(long groupId, boolean active);

	/**
	* Returns the number of calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching calendar resources that the user has permission to view
	*/
	public int filterCountByG_A(long groupId, boolean active);

	/**
	* Returns the calendar resource where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByC_C(long classNameId, long classPK)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the calendar resource where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByC_C(long classNameId, long classPK);

	/**
	* Returns the calendar resource where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache);

	/**
	* Removes the calendar resource where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the calendar resource that was removed
	*/
	public CalendarResource removeByC_C(long classNameId, long classPK)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the number of calendar resources where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching calendar resources
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Returns all the calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @return the matching calendar resources
	*/
	public java.util.List<CalendarResource> findByC_C_A(long companyId,
		java.lang.String code, boolean active);

	/**
	* Returns a range of all the calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByC_C_A(long companyId,
		java.lang.String code, boolean active, int start, int end);

	/**
	* Returns an ordered range of all the calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar resources
	*/
	public java.util.List<CalendarResource> findByC_C_A(long companyId,
		java.lang.String code, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the first calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByC_C_A_First(long companyId,
		java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the first calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByC_C_A_First(long companyId,
		java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the last calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource
	* @throws NoSuchResourceException if a matching calendar resource could not be found
	*/
	public CalendarResource findByC_C_A_Last(long companyId,
		java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the last calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	*/
	public CalendarResource fetchByC_C_A_Last(long companyId,
		java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Returns the calendar resources before and after the current calendar resource in the ordered set where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param calendarResourceId the primary key of the current calendar resource
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource[] findByC_C_A_PrevAndNext(long calendarResourceId,
		long companyId, java.lang.String code, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Removes all the calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	*/
	public void removeByC_C_A(long companyId, java.lang.String code,
		boolean active);

	/**
	* Returns the number of calendar resources where companyId = &#63; and code LIKE &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param code the code
	* @param active the active
	* @return the number of matching calendar resources
	*/
	public int countByC_C_A(long companyId, java.lang.String code,
		boolean active);

	/**
	* Caches the calendar resource in the entity cache if it is enabled.
	*
	* @param calendarResource the calendar resource
	*/
	public void cacheResult(CalendarResource calendarResource);

	/**
	* Caches the calendar resources in the entity cache if it is enabled.
	*
	* @param calendarResources the calendar resources
	*/
	public void cacheResult(java.util.List<CalendarResource> calendarResources);

	/**
	* Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	*
	* @param calendarResourceId the primary key for the new calendar resource
	* @return the new calendar resource
	*/
	public CalendarResource create(long calendarResourceId);

	/**
	* Removes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource that was removed
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource remove(long calendarResourceId)
		throws com.liferay.calendar.NoSuchResourceException;

	public CalendarResource updateImpl(CalendarResource calendarResource);

	/**
	* Returns the calendar resource with the primary key or throws a {@link NoSuchResourceException} if it could not be found.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource
	* @throws NoSuchResourceException if a calendar resource with the primary key could not be found
	*/
	public CalendarResource findByPrimaryKey(long calendarResourceId)
		throws com.liferay.calendar.NoSuchResourceException;

	/**
	* Returns the calendar resource with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource, or <code>null</code> if a calendar resource with the primary key could not be found
	*/
	public CalendarResource fetchByPrimaryKey(long calendarResourceId);

	@Override
	public java.util.Map<java.io.Serializable, CalendarResource> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the calendar resources.
	*
	* @return the calendar resources
	*/
	public java.util.List<CalendarResource> findAll();

	/**
	* Returns a range of all the calendar resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of calendar resources
	*/
	public java.util.List<CalendarResource> findAll(int start, int end);

	/**
	* Returns an ordered range of all the calendar resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendar resources
	*/
	public java.util.List<CalendarResource> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarResource> orderByComparator);

	/**
	* Removes all the calendar resources from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of calendar resources.
	*
	* @return the number of calendar resources
	*/
	public int countAll();
}