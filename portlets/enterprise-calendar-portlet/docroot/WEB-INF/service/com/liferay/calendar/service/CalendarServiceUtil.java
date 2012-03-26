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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the calendar remote service. This utility wraps {@link com.liferay.calendar.service.impl.CalendarServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarService
 * @see com.liferay.calendar.service.base.CalendarServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarServiceImpl
 * @generated
 */
public class CalendarServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.calendar.model.Calendar addCalendar(
		long groupId, long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, boolean defaultCalendar,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCalendar(groupId, calendarResourceId, nameMap,
			descriptionMap, color, defaultCalendar, serviceContext);
	}

	public static com.liferay.calendar.model.Calendar deleteCalendar(
		long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCalendar(calendarId);
	}

	public static com.liferay.calendar.model.Calendar getCalendar(
		long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendar(calendarId);
	}

	public static com.liferay.calendar.model.Calendar updateCalendar(
		long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, boolean defaultCalendar,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCalendar(calendarId, nameMap, descriptionMap, color,
			defaultCalendar, serviceContext);
	}

	public static com.liferay.calendar.model.Calendar updateCalendar(
		long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCalendar(calendarId, nameMap, descriptionMap, color,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CalendarService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CalendarService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					CalendarService.class.getName(), portletClassLoader);

			_service = new CalendarServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(CalendarServiceUtil.class,
				"_service");
			MethodCache.remove(CalendarService.class);
		}

		return _service;
	}

	public void setService(CalendarService service) {
		MethodCache.remove(CalendarService.class);

		_service = service;

		ReferenceRegistry.registerReference(CalendarServiceUtil.class,
			"_service");
		MethodCache.remove(CalendarService.class);
	}

	private static CalendarService _service;
}