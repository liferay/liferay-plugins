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

package com.liferay.calendar.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for CalendarResource. This utility wraps
 * {@link com.liferay.calendar.service.impl.CalendarResourceServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Eduardo Lundgren
 * @see CalendarResourceService
 * @see com.liferay.calendar.service.base.CalendarResourceServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarResourceServiceImpl
 * @generated
 */
@ProviderType
public class CalendarResourceServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarResourceServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.calendar.model.CalendarResource addCalendarResource(
		long groupId, long classNameId, long classPK,
		java.lang.String classUuid, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCalendarResource(groupId, classNameId, classPK,
			classUuid, code, nameMap, descriptionMap, active, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarResource deleteCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCalendarResource(calendarResourceId);
	}

	public static com.liferay.calendar.model.CalendarResource fetchCalendarResource(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCalendarResource(classNameId, classPK);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.calendar.model.CalendarResource getCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCalendarResource(calendarResourceId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator) {
		return getService()
				   .search(companyId, groupIds, classNameIds, code, name,
			description, active, andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator) {
		return getService()
				   .search(companyId, groupIds, classNameIds, keywords, active,
			andOperator, start, end, orderByComparator);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator) {
		return getService()
				   .searchCount(companyId, groupIds, classNameIds, code, name,
			description, active, andOperator);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active) {
		return getService()
				   .searchCount(companyId, groupIds, classNameIds, keywords,
			active);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendarResource(calendarResourceId, nameMap,
			descriptionMap, active, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CalendarResourceService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CalendarResourceService.class.getName());

			if (invokableService instanceof CalendarResourceService) {
				_service = (CalendarResourceService)invokableService;
			}
			else {
				_service = new CalendarResourceServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(CalendarResourceServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(CalendarResourceService service) {
	}

	private static CalendarResourceService _service;
}