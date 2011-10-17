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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CalendarEventLocalService}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarEventLocalService
 * @generated
 */
public class CalendarEventLocalServiceWrapper
	implements CalendarEventLocalService,
		ServiceWrapper<CalendarEventLocalService> {
	public CalendarEventLocalServiceWrapper(
		CalendarEventLocalService calendarEventLocalService) {
		_calendarEventLocalService = calendarEventLocalService;
	}

	/**
	* Adds the calendar event to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarEvent the calendar event
	* @return the calendar event that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent addCalendarEvent(
		com.liferay.calendar.model.CalendarEvent calendarEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.addCalendarEvent(calendarEvent);
	}

	/**
	* Creates a new calendar event with the primary key. Does not add the calendar event to the database.
	*
	* @param calendarEventId the primary key for the new calendar event
	* @return the new calendar event
	*/
	public com.liferay.calendar.model.CalendarEvent createCalendarEvent(
		long calendarEventId) {
		return _calendarEventLocalService.createCalendarEvent(calendarEventId);
	}

	/**
	* Deletes the calendar event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarEventId the primary key of the calendar event
	* @throws PortalException if a calendar event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCalendarEvent(long calendarEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarEventLocalService.deleteCalendarEvent(calendarEventId);
	}

	/**
	* Deletes the calendar event from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarEvent the calendar event
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCalendarEvent(
		com.liferay.calendar.model.CalendarEvent calendarEvent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarEventLocalService.deleteCalendarEvent(calendarEvent);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the calendar event with the primary key.
	*
	* @param calendarEventId the primary key of the calendar event
	* @return the calendar event
	* @throws PortalException if a calendar event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent getCalendarEvent(
		long calendarEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.getCalendarEvent(calendarEventId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.getPersistedModel(primaryKeyObj);
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
	public com.liferay.calendar.model.CalendarEvent getCalendarEventByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.getCalendarEventByUuidAndGroupId(uuid,
			groupId);
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
	public java.util.List<com.liferay.calendar.model.CalendarEvent> getCalendarEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.getCalendarEvents(start, end);
	}

	/**
	* Returns the number of calendar events.
	*
	* @return the number of calendar events
	* @throws SystemException if a system exception occurred
	*/
	public int getCalendarEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.getCalendarEventsCount();
	}

	/**
	* Updates the calendar event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarEvent the calendar event
	* @return the calendar event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent updateCalendarEvent(
		com.liferay.calendar.model.CalendarEvent calendarEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.updateCalendarEvent(calendarEvent);
	}

	/**
	* Updates the calendar event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarEvent the calendar event
	* @param merge whether to merge the calendar event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the calendar event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarEvent updateCalendarEvent(
		com.liferay.calendar.model.CalendarEvent calendarEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventLocalService.updateCalendarEvent(calendarEvent,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _calendarEventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarEventLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.calendar.model.CalendarEvent addCalendarEvent(
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
		return _calendarEventLocalService.addCalendarEvent(userId, titleMap,
			descriptionMap, location, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, durationHour,
			durationMinute, allDay, recurrence, type, remindBy, firstReminder,
			secondReminder, serviceContext);
	}

	public com.liferay.calendar.model.CalendarEvent updateCalendarEvent(
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
		return _calendarEventLocalService.updateCalendarEvent(calendarEventId,
			titleMap, descriptionMap, location, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, durationHour,
			durationMinute, allDay, recurrence, type, remindBy, firstReminder,
			secondReminder, serviceContext);
	}

	public void updateCalendarEventResources(
		com.liferay.calendar.model.CalendarEvent calendarEvent,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarEventLocalService.updateCalendarEventResources(calendarEvent,
			communityPermissions, guestPermissions);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalendarEventLocalService getWrappedCalendarEventLocalService() {
		return _calendarEventLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalendarEventLocalService(
		CalendarEventLocalService calendarEventLocalService) {
		_calendarEventLocalService = calendarEventLocalService;
	}

	public CalendarEventLocalService getWrappedService() {
		return _calendarEventLocalService;
	}

	public void setWrappedService(
		CalendarEventLocalService calendarEventLocalService) {
		_calendarEventLocalService = calendarEventLocalService;
	}

	private CalendarEventLocalService _calendarEventLocalService;
}