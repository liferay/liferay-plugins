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
 * Provides a wrapper for {@link CalendarImporterLocalService}.
 *
 * @author    Eduardo Lundgren
 * @see       CalendarImporterLocalService
 * @generated
 */
public class CalendarImporterLocalServiceWrapper
	implements CalendarImporterLocalService,
		ServiceWrapper<CalendarImporterLocalService> {
	public CalendarImporterLocalServiceWrapper(
		CalendarImporterLocalService calendarImporterLocalService) {
		_calendarImporterLocalService = calendarImporterLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _calendarImporterLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarImporterLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _calendarImporterLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public void importCalEvent(
		com.liferay.portlet.calendar.model.CalEvent calEvent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarImporterLocalService.importCalEvent(calEvent);
	}

	@Override
	public void importCalEvents()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarImporterLocalService.importCalEvents();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CalendarImporterLocalService getWrappedCalendarImporterLocalService() {
		return _calendarImporterLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCalendarImporterLocalService(
		CalendarImporterLocalService calendarImporterLocalService) {
		_calendarImporterLocalService = calendarImporterLocalService;
	}

	@Override
	public CalendarImporterLocalService getWrappedService() {
		return _calendarImporterLocalService;
	}

	@Override
	public void setWrappedService(
		CalendarImporterLocalService calendarImporterLocalService) {
		_calendarImporterLocalService = calendarImporterLocalService;
	}

	private CalendarImporterLocalService _calendarImporterLocalService;
}