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
import com.liferay.calendar.service.permission.CalendarResourcePermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.permission.PortletPermissionUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 */
public class CalendarResourceServiceImpl
	extends CalendarResourceServiceBaseImpl {

	public CalendarResource addCalendarResource(
			String className, long classPK, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		PortletPermissionUtil.check(
			getPermissionChecker(), serviceContext.getPlid(),
			"1_WAR_calendarportlet", ActionKeys.ADD_RESOURCE);

		return calendarResourceLocalService.addCalendarResource(
			getUserId(), className, classPK, nameMap, descriptionMap, active,
			serviceContext);
	}

	public void deleteCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.DELETE);

		calendarResourceLocalService.deleteCalendarResource(calendarResourceId);
	}

	public CalendarResource getCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.VIEW);

		return calendarResourceLocalService.getCalendarResource(
			calendarResourceId);
	}

	public CalendarResource getCalendarResource(String className, long classPK)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourceLocalService.getCalendarResource(
				className, classPK);

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResource, ActionKeys.VIEW);

		return calendarResource;
	}

	public List<CalendarResource> getGroupCalendarResources(
			long groupId, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.filterFindByG_A(
			groupId, active, start, end, orderByComparator);
	}

	public List<CalendarResource> getGroupCalendarResources(
			long groupId, String name, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.filterFindByG_N_A(
			groupId, name, active, start, end, orderByComparator);
	}

	public int getGroupCalendarResourcesCount(long groupId, boolean active)
		throws SystemException {

		return calendarResourcePersistence.filterCountByG_A(groupId, active);
	}

	public int getGroupCalendarResourcesCount(
			long groupId, String name, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.filterCountByG_N_A(
			groupId, name, active);
	}

	public CalendarResource updateCalendarResource(
			long calendarResourceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.UPDATE);

		return calendarResourceLocalService.updateCalendarResource(
			calendarResourceId, nameMap, descriptionMap, active,
			serviceContext);
	}

}