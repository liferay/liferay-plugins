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

import com.liferay.calendar.model.CalendarNotificationTemplate;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the calendar notification template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.persistence.impl.CalendarNotificationTemplatePersistenceImpl
 * @see CalendarNotificationTemplateUtil
 * @generated
 */
@ProviderType
public interface CalendarNotificationTemplatePersistence extends BasePersistence<CalendarNotificationTemplate> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarNotificationTemplateUtil} to access the calendar notification template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the calendar notification templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByUuid(
		java.lang.String uuid);

	/**
	* Returns a range of all the calendar notification templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the calendar notification templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the first calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the first calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the last calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the last calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the calendar notification templates before and after the current calendar notification template in the ordered set where uuid = &#63;.
	*
	* @param calendarNotificationTemplateId the primary key of the current calendar notification template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar notification template
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public CalendarNotificationTemplate[] findByUuid_PrevAndNext(
		long calendarNotificationTemplateId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Removes all the calendar notification templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of calendar notification templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching calendar notification templates
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the calendar notification template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate findByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the calendar notification template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByUUID_G(java.lang.String uuid,
		long groupId);

	/**
	* Returns the calendar notification template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the calendar notification template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the calendar notification template that was removed
	*/
	public CalendarNotificationTemplate removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the number of calendar notification templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching calendar notification templates
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the first calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the first calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the last calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the last calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the calendar notification templates before and after the current calendar notification template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param calendarNotificationTemplateId the primary key of the current calendar notification template
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar notification template
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public CalendarNotificationTemplate[] findByUuid_C_PrevAndNext(
		long calendarNotificationTemplateId, java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Removes all the calendar notification templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of calendar notification templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching calendar notification templates
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the calendar notification templates where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId);

	/**
	* Returns a range of all the calendar notification templates where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId, int start, int end);

	/**
	* Returns an ordered range of all the calendar notification templates where calendarId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param calendarId the calendar ID
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findByCalendarId(
		long calendarId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the first calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate findByCalendarId_First(
		long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the first calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByCalendarId_First(
		long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the last calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate findByCalendarId_Last(long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the last calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByCalendarId_Last(
		long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the calendar notification templates before and after the current calendar notification template in the ordered set where calendarId = &#63;.
	*
	* @param calendarNotificationTemplateId the primary key of the current calendar notification template
	* @param calendarId the calendar ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next calendar notification template
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public CalendarNotificationTemplate[] findByCalendarId_PrevAndNext(
		long calendarNotificationTemplateId, long calendarId,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Removes all the calendar notification templates where calendarId = &#63; from the database.
	*
	* @param calendarId the calendar ID
	*/
	public void removeByCalendarId(long calendarId);

	/**
	* Returns the number of calendar notification templates where calendarId = &#63;.
	*
	* @param calendarId the calendar ID
	* @return the number of matching calendar notification templates
	*/
	public int countByCalendarId(long calendarId);

	/**
	* Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @return the matching calendar notification template
	* @throws NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate findByC_NT_NTT(long calendarId,
		java.lang.String notificationType,
		java.lang.String notificationTemplateType)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByC_NT_NTT(long calendarId,
		java.lang.String notificationType,
		java.lang.String notificationTemplateType);

	/**
	* Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	public CalendarNotificationTemplate fetchByC_NT_NTT(long calendarId,
		java.lang.String notificationType,
		java.lang.String notificationTemplateType, boolean retrieveFromCache);

	/**
	* Removes the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; from the database.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @return the calendar notification template that was removed
	*/
	public CalendarNotificationTemplate removeByC_NT_NTT(long calendarId,
		java.lang.String notificationType,
		java.lang.String notificationTemplateType)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the number of calendar notification templates where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63;.
	*
	* @param calendarId the calendar ID
	* @param notificationType the notification type
	* @param notificationTemplateType the notification template type
	* @return the number of matching calendar notification templates
	*/
	public int countByC_NT_NTT(long calendarId,
		java.lang.String notificationType,
		java.lang.String notificationTemplateType);

	/**
	* Caches the calendar notification template in the entity cache if it is enabled.
	*
	* @param calendarNotificationTemplate the calendar notification template
	*/
	public void cacheResult(
		CalendarNotificationTemplate calendarNotificationTemplate);

	/**
	* Caches the calendar notification templates in the entity cache if it is enabled.
	*
	* @param calendarNotificationTemplates the calendar notification templates
	*/
	public void cacheResult(
		java.util.List<CalendarNotificationTemplate> calendarNotificationTemplates);

	/**
	* Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	*
	* @param calendarNotificationTemplateId the primary key for the new calendar notification template
	* @return the new calendar notification template
	*/
	public CalendarNotificationTemplate create(
		long calendarNotificationTemplateId);

	/**
	* Removes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template that was removed
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public CalendarNotificationTemplate remove(
		long calendarNotificationTemplateId)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	public CalendarNotificationTemplate updateImpl(
		CalendarNotificationTemplate calendarNotificationTemplate);

	/**
	* Returns the calendar notification template with the primary key or throws a {@link NoSuchNotificationTemplateException} if it could not be found.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template
	* @throws NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	*/
	public CalendarNotificationTemplate findByPrimaryKey(
		long calendarNotificationTemplateId)
		throws com.liferay.calendar.NoSuchNotificationTemplateException;

	/**
	* Returns the calendar notification template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template, or <code>null</code> if a calendar notification template with the primary key could not be found
	*/
	public CalendarNotificationTemplate fetchByPrimaryKey(
		long calendarNotificationTemplateId);

	@Override
	public java.util.Map<java.io.Serializable, CalendarNotificationTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the calendar notification templates.
	*
	* @return the calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findAll();

	/**
	* Returns a range of all the calendar notification templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the calendar notification templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of calendar notification templates
	*/
	public java.util.List<CalendarNotificationTemplate> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CalendarNotificationTemplate> orderByComparator);

	/**
	* Removes all the calendar notification templates from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of calendar notification templates.
	*
	* @return the number of calendar notification templates
	*/
	public int countAll();
}