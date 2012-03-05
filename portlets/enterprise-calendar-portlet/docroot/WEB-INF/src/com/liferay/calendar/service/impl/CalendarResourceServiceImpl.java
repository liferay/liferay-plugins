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
import com.liferay.calendar.service.permission.EnterpriseCalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
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
			String code, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		EnterpriseCalendarPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_RESOURCE);

		return calendarResourceLocalService.addCalendarResource(
			getUserId(), groupId, className, classPK, classUuid, code, nameMap,
			descriptionMap, type, active, serviceContext);
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

	public List<CalendarResource> searchByKeywords(
			long groupId, long classNameId, long classPK, String keywords,
			Boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		return search(
			groupId, classNameId, classPK, keywords, keywords, keywords,
			null, active, false, start, end, orderByComparator);
	}

	public List<CalendarResource> search(
			long groupId, long classNameId, long classPK, String code,
			String name, String description, String type, Boolean active,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<CalendarResource> resources = calendarResourceLocalService.search(
			groupId, classNameId, classPK, code, name, description, type,
			active, andOperator, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			orderByComparator);

		List<CalendarResource> filteredResources = filterResources(resources);

		if (start > QueryUtil.ALL_POS && end > QueryUtil.ALL_POS) {
			return ListUtil.subList(filteredResources, start, end);
		}
		else {
			return filteredResources;
		}
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

	protected List<CalendarResource> filterResources(
			List<CalendarResource> resources)
		throws PortalException, SystemException {
	
		List<CalendarResource> filteredResources =
			new ArrayList<CalendarResource>();
	
		for (CalendarResource resource : resources) {
			if (CalendarResourcePermission.contains(
					getPermissionChecker(), resource, ActionKeys.VIEW)) {
	
				filteredResources.add(resource);
			}
		}
	
		return filteredResources;
	}

}