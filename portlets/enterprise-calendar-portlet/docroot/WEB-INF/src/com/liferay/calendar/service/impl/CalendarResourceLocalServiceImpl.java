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

import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.base.CalendarResourceLocalServiceBaseImpl;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarResourceLocalServiceImpl
	extends CalendarResourceLocalServiceBaseImpl {

	public CalendarResource addCalendarResource(
			long userId, long groupId, String className, long classPK,
			String classUuid, long defaultCalendarId, String code, Map<Locale,
			String> nameMap, Map<Locale, String> descriptionMap, String type,
			boolean active, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar resource

		long calendarResourceId = counterLocalService.increment();

		if (Validator.isNull(className)) {
			className = CalendarResource.class.getName();
			classPK = calendarResourceId;
		}

		long globalUserId = 0;

		if (CalendarResourceUtil.isGlobalResource(className)) {
			globalUserId = CalendarResourceUtil.getGlobalResourceUserId(
				className, classPK);
		}

		if (globalUserId > 0) {
			userId = globalUserId;
		}

		User user = userPersistence.findByPrimaryKey(userId);

		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		validate(classNameId, classPK);

		CalendarResource calendarResource = calendarResourcePersistence.create(
			calendarResourceId);

		calendarResource.setUuid(serviceContext.getUuid());
		calendarResource.setGroupId(groupId);
		calendarResource.setCompanyId(user.getCompanyId());
		calendarResource.setUserId(user.getUserId());
		calendarResource.setUserName(user.getFullName());
		calendarResource.setCreateDate(serviceContext.getCreateDate(now));
		calendarResource.setModifiedDate(serviceContext.getModifiedDate(now));
		calendarResource.setClassNameId(classNameId);

		if (className.equals(CalendarResource.class.getName())) {
			calendarResource.setClassPK(calendarResourceId);
		}
		else {
			calendarResource.setClassPK(classPK);
		}

		calendarResource.setClassUuid(classUuid);
		calendarResource.setDefaultCalendarId(defaultCalendarId);
		calendarResource.setCode(code);
		calendarResource.setNameMap(nameMap);
		calendarResource.setDescriptionMap(descriptionMap);
		calendarResource.setType(type);
		calendarResource.setActive(active);

		calendarResourcePersistence.update(calendarResource, false);

		// Resources

		resourceLocalService.addModelResources(
			calendarResource, serviceContext);

		return calendarResource;
	}

	@Override
	public CalendarResource deleteCalendarResource(
			CalendarResource calendarResource)
		throws PortalException, SystemException {

		// Calendar resource

		calendarResourcePersistence.remove(calendarResource);

		// Resources

		resourceLocalService.deleteResource(
			calendarResource, ResourceConstants.SCOPE_INDIVIDUAL);

		// Calendar bookings

		List<CalendarBooking> calendarBookings =
			calendarBookingPersistence.findByCalendarResourceId(
				calendarResource.getCalendarResourceId());

		for (CalendarBooking calendarBooking : calendarBookings) {
			calendarBookingLocalService.deleteCalendarBooking(calendarBooking);
		}

		return calendarResource;
	}

	@Override
	public CalendarResource deleteCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		return deleteCalendarResource(calendarResource);
	}

	@Override
	public CalendarResource getCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		return calendarResourcePersistence.findByPrimaryKey(calendarResourceId);
	}

	public List<CalendarResource> search(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, String type, boolean active,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourceFinder.findByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, code, name, description, type,
			active, andOperator, start, end, orderByComparator);
	}

	public List<CalendarResource> searchByKeywords(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourceFinder.findByKeywords(
			companyId, groupIds, classNameIds, keywords, active, start, end,
			orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active)
		throws SystemException {

		return calendarResourceFinder.countByKeywords(
			companyId, groupIds, classNameIds, keywords, active);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, String type, boolean active,
			boolean andOperator)
		throws SystemException {

		return calendarResourceFinder.countByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, code, name, description, type,
			active, andOperator);
	}

	public CalendarResource updateCalendarResource(
			long calendarResourceId, long defaultCalendarId, String code,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String type, boolean active, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar resource

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		calendarResource.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarResource.setDefaultCalendarId(defaultCalendarId);
		calendarResource.setCode(code);
		calendarResource.setNameMap(nameMap);
		calendarResource.setDescriptionMap(descriptionMap);
		calendarResource.setType(type);
		calendarResource.setActive(active);

		calendarResourcePersistence.update(calendarResource, false);

		// Resources

		resourceLocalService.updateModelResources(
			calendarResource, serviceContext);

		return calendarResource;
	}

	public CalendarResource updateCalendarResource(
			long calendarResourceId, String code, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		return updateCalendarResource(
			calendarResourceId, calendarResource.getDefaultCalendarId(), code,
			nameMap, descriptionMap, type, active, serviceContext);
	}

	public CalendarResource updateDefaultCalendarId(
			long calendarResourceId, long defaultCalendarId)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		calendarResource.setDefaultCalendarId(defaultCalendarId);

		calendarResourcePersistence.update(calendarResource, false);

		return calendarResource;
	}

	protected void validate(long classNameId, long classPK)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.fetchByC_C(classNameId, classPK);

		if (Validator.isNotNull(calendarResource)) {
			throw new DuplicateCalendarResourceException();
		}
	}

}