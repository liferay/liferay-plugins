/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the calendar event local service. This utility wraps {@link com.liferay.calendar.service.impl.CalendarEventLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarEventLocalService
 * @see com.liferay.calendar.service.base.CalendarEventLocalServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarEventLocalServiceImpl
 * @generated
 */
public class CalendarEventLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarEventLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the calendar event to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarEvent the calendar event
	* @return the calendar event that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarEvent addCalendarEvent(
		com.liferay.calendar.model.CalendarEvent calendarEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addCalendarEvent(calendarEvent);
	}

	/**
	* Creates a new calendar event with the primary key. Does not add the calendar event to the database.
	*
	* @param calendarEventId the primary key for the new calendar event
	* @return the new calendar event
	*/
	public static com.liferay.calendar.model.CalendarEvent createCalendarEvent(
		long calendarEventId) {
		return getService().createCalendarEvent(calendarEventId);
	}

	/**
	* Deletes the calendar event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarEventId the primary key of the calendar event
	* @throws PortalException if a calendar event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteCalendarEvent(long calendarEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCalendarEvent(calendarEventId);
	}

	/**
	* Deletes the calendar event from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarEvent the calendar event
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteCalendarEvent(
		com.liferay.calendar.model.CalendarEvent calendarEvent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCalendarEvent(calendarEvent);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the calendar event with the primary key.
	*
	* @param calendarEventId the primary key of the calendar event
	* @return the calendar event
	* @throws PortalException if a calendar event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarEvent getCalendarEvent(
		long calendarEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarEvent(calendarEventId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the calendar event with the UUID in the group.
	*
	* @param uuid the UUID of calendar event
	* @param groupId the group id of the calendar event
	* @return the calendar event
	* @throws PortalException if a calendar event with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarEvent getCalendarEventByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarEventByUuidAndGroupId(uuid, groupId);
	}

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
	public static java.util.List<com.liferay.calendar.model.CalendarEvent> getCalendarEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarEvents(start, end);
	}

	/**
	* Returns the number of calendar events.
	*
	* @return the number of calendar events
	* @throws SystemException if a system exception occurred
	*/
	public static int getCalendarEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarEventsCount();
	}

	/**
	* Updates the calendar event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarEvent the calendar event
	* @return the calendar event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarEvent updateCalendarEvent(
		com.liferay.calendar.model.CalendarEvent calendarEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCalendarEvent(calendarEvent);
	}

	/**
	* Updates the calendar event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarEvent the calendar event
	* @param merge whether to merge the calendar event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the calendar event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarEvent updateCalendarEvent(
		com.liferay.calendar.model.CalendarEvent calendarEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCalendarEvent(calendarEvent, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.calendar.model.CalendarEvent addCalendarEvent(
		long userId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int durationHour, int durationMinute,
		boolean allDay, java.lang.String recurrence, java.lang.String type,
		int remindBy, int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCalendarEvent(userId, titleMap, descriptionMap,
			location, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, durationHour,
			durationMinute, allDay, recurrence, type, remindBy, firstReminder,
			secondReminder, serviceContext);
	}

	public static void addCalendarEventResources(
		com.liferay.calendar.model.CalendarEvent calendarEvent,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addCalendarEventResources(calendarEvent, addCommunityPermissions,
			addGuestPermissions);
	}

	public static void addCalendarEventResources(
		com.liferay.calendar.model.CalendarEvent calendarEvent,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addCalendarEventResources(calendarEvent, communityPermissions,
			guestPermissions);
	}

	public static com.liferay.calendar.model.CalendarEvent updateCalendarEvent(
		long calendarEventId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int durationHour, int durationMinute,
		boolean allDay, java.lang.String recurrence, java.lang.String type,
		int remindBy, int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCalendarEvent(calendarEventId, titleMap,
			descriptionMap, location, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, durationHour,
			durationMinute, allDay, recurrence, type, remindBy, firstReminder,
			secondReminder, serviceContext);
	}

	public static void updateCalendarEventResources(
		com.liferay.calendar.model.CalendarEvent calendarEvent,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateCalendarEventResources(calendarEvent, communityPermissions,
			guestPermissions);
	}

	public static void clearService() {
		_service = null;
	}

	public static CalendarEventLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CalendarEventLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					CalendarEventLocalService.class.getName(),
					portletClassLoader);

			_service = new CalendarEventLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(CalendarEventLocalServiceUtil.class,
				"_service");
			MethodCache.remove(CalendarEventLocalService.class);
		}

		return _service;
	}

	public void setService(CalendarEventLocalService service) {
		MethodCache.remove(CalendarEventLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(CalendarEventLocalServiceUtil.class,
			"_service");
		MethodCache.remove(CalendarEventLocalService.class);
	}

	private static CalendarEventLocalService _service;
}