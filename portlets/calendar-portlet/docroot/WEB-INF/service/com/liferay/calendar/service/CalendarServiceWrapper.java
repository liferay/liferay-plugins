/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
 * Provides a wrapper for {@link CalendarService}.
 *
 * @author Eduardo Lundgren
 * @see CalendarService
 * @generated
 */
public class CalendarServiceWrapper implements CalendarService,
	ServiceWrapper<CalendarService> {
	public CalendarServiceWrapper(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _calendarService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _calendarService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.calendar.model.Calendar addCalendar(long groupId,
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, boolean defaultCalendar, boolean enableComments,
		boolean enableRatings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.addCalendar(groupId, calendarResourceId,
			nameMap, descriptionMap, color, defaultCalendar, enableComments,
			enableRatings, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.Calendar deleteCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.deleteCalendar(calendarId);
	}

	@Override
	public java.lang.String exportCalendar(long calendarId,
		java.lang.String type) throws java.lang.Exception {
		return _calendarService.exportCalendar(calendarId, type);
	}

	@Override
	public com.liferay.calendar.model.Calendar fetchCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.fetchCalendar(calendarId);
	}

	@Override
	public com.liferay.calendar.model.Calendar getCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.getCalendar(calendarId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendarResourceCalendars(
		long groupId, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.getCalendarResourceCalendars(groupId,
			calendarResourceId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendarResourceCalendars(
		long groupId, long calendarResourceId, boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.getCalendarResourceCalendars(groupId,
			calendarResourceId, defaultCalendar);
	}

	@Override
	public void importCalendar(long calendarId, java.lang.String data,
		java.lang.String type) throws java.lang.Exception {
		_calendarService.importCalendar(calendarId, data, type);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.search(companyId, groupIds,
			calendarResourceIds, keywords, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.search(companyId, groupIds,
			calendarResourceIds, keywords, andOperator, start, end,
			orderByComparator, actionId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.search(companyId, groupIds,
			calendarResourceIds, name, description, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.search(companyId, groupIds,
			calendarResourceIds, name, description, andOperator, start, end,
			orderByComparator, actionId);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.searchCount(companyId, groupIds,
			calendarResourceIds, keywords, andOperator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords,
		boolean andOperator, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.searchCount(companyId, groupIds,
			calendarResourceIds, keywords, andOperator, actionId);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.searchCount(companyId, groupIds,
			calendarResourceIds, name, description, andOperator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.searchCount(companyId, groupIds,
			calendarResourceIds, name, description, andOperator, actionId);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateCalendar(long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, boolean defaultCalendar, boolean enableComments,
		boolean enableRatings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.updateCalendar(calendarId, nameMap,
			descriptionMap, color, defaultCalendar, enableComments,
			enableRatings, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateCalendar(long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.updateCalendar(calendarId, nameMap,
			descriptionMap, color, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateColor(long calendarId,
		int color, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.updateColor(calendarId, color, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CalendarService getWrappedCalendarService() {
		return _calendarService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCalendarService(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	@Override
	public CalendarService getWrappedService() {
		return _calendarService;
	}

	@Override
	public void setWrappedService(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	private CalendarService _calendarService;
}