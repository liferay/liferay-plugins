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

package com.liferay.calendar.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CalendarBookingService}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarBookingService
 * @generated
 */
public class CalendarBookingServiceWrapper implements CalendarBookingService,
	ServiceWrapper<CalendarBookingService> {
	public CalendarBookingServiceWrapper(
		CalendarBookingService calendarBookingService) {
		_calendarBookingService = calendarBookingService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _calendarBookingService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarBookingService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _calendarBookingService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long userId, long calendarId, long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, java.util.Date startDate,
		java.util.Date endDate, boolean allDay, java.lang.String recurrence,
		int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.addCalendarBooking(userId, calendarId,
			parentCalendarBookingId, titleMap, descriptionMap, location,
			startDate, endDate, allDay, recurrence, firstReminder,
			secondReminder, serviceContext);
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long userId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, java.util.Date startDate,
		java.util.Date endDate, boolean allDay, java.lang.String recurrence,
		int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.addCalendarBooking(userId, calendarId,
			titleMap, descriptionMap, location, startDate, endDate, allDay,
			recurrence, firstReminder, secondReminder, serviceContext);
	}

	public com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.deleteCalendarBooking(calendarBookingId);
	}

	public com.liferay.calendar.model.CalendarBooking fetchByC_P(
		long calendarId, long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.fetchByC_P(calendarId,
			parentCalendarBookingId);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByP_S(
		long parentCalendarBookingId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.findByP_S(parentCalendarBookingId, status);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getByParentCalendarBookingId(
		long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getByParentCalendarBookingId(parentCalendarBookingId);
	}

	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarBooking(calendarBookingId);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarBookings(calendarId,
			startDate, endDate);
	}

	public void invokeTransition(long userId, long calendarBookingId,
		java.lang.String transitionName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarBookingService.invokeTransition(userId, calendarBookingId,
			transitionName, serviceContext);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, java.util.Date startDate,
		java.util.Date endDate, int[] status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.search(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startDate,
			endDate, status, start, end, orderByComparator);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, java.util.Date startDate,
		java.util.Date endDate, int[] status, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.search(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, title, description,
			location, startDate, endDate, status, andOperator, start, end,
			orderByComparator);
	}

	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, java.util.Date startDate,
		java.util.Date endDate, int[] status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.searchCount(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId,
			keywords, startDate, endDate, status);
	}

	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, java.util.Date startDate,
		java.util.Date endDate, int[] status, boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.searchCount(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startDate, endDate, status, andOperator);
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long userId, long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, int status, java.util.Date startDate,
		java.util.Date endDate, boolean allDay, java.lang.String recurrence,
		int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.updateCalendarBooking(userId,
			calendarBookingId, calendarId, titleMap, descriptionMap, location,
			status, startDate, endDate, allDay, recurrence, firstReminder,
			secondReminder, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalendarBookingService getWrappedCalendarBookingService() {
		return _calendarBookingService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalendarBookingService(
		CalendarBookingService calendarBookingService) {
		_calendarBookingService = calendarBookingService;
	}

	public CalendarBookingService getWrappedService() {
		return _calendarBookingService;
	}

	public void setWrappedService(CalendarBookingService calendarBookingService) {
		_calendarBookingService = calendarBookingService;
	}

	private CalendarBookingService _calendarBookingService;
}