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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.base.CalendarResourceServiceBaseImpl;
import com.liferay.calendar.service.permission.CalendarPortletPermission;
import com.liferay.calendar.service.permission.CalendarResourcePermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
 */
public class CalendarResourceServiceImpl
	extends CalendarResourceServiceBaseImpl {

	public CalendarResource addCalendarResource(
			long groupId, String className, long classPK, String classUuid,
			long defaultCalendarId, String code, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarPortletPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_RESOURCE);

		return calendarResourceLocalService.addCalendarResource(
			getUserId(), groupId, className, classPK, classUuid,
			defaultCalendarId, code, nameMap, descriptionMap, type, active,
			serviceContext);
	}

	public CalendarResource deleteCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.DELETE);

		return calendarResourceLocalService.deleteCalendarResource(
			calendarResourceId);
	}

	public CalendarResource getCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.VIEW);

		return calendarResourceLocalService.getCalendarResource(
			calendarResourceId);
	}

	public List<CalendarResource> search(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourceFinder.filterFindByKeywords(
			companyId, groupIds, classNameIds, keywords, active, start, end,
			orderByComparator);
	}

	public List<CalendarResource> search(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, String type, boolean active,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourceFinder.filterFindByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, code, name, description, type,
			active, andOperator, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active)
		throws SystemException {

		return calendarResourceFinder.filterCountByKeywords(
			companyId, groupIds, classNameIds, keywords, active);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, String type, boolean active,
			boolean andOperator)
		throws SystemException {

		return calendarResourceFinder.filterCountByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, code, name, description, type,
			active, andOperator);
	}

	public CalendarResource updateCalendarResource(
			long calendarResourceId, long defaultCalendarId, String code,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String type, boolean active, ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.UPDATE);

		return calendarResourceLocalService.updateCalendarResource(
			calendarResourceId, defaultCalendarId, code, nameMap,
			descriptionMap, type, active, serviceContext);
	}

	public CalendarResource updateCalendarResource(
			long calendarResourceId, String code, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.UPDATE);

		return calendarResourceLocalService.updateCalendarResource(
			calendarResourceId, code, nameMap, descriptionMap, type, active,
			serviceContext);
	}

}