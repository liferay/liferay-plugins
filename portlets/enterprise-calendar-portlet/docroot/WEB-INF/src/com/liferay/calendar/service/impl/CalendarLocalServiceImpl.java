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

import com.liferay.calendar.CalendarNameException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.base.CalendarLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
 */
public class CalendarLocalServiceImpl extends CalendarLocalServiceBaseImpl {

	public Calendar addCalendar(
			long userId, long groupId, long calendarResourceId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			int color, boolean defaultCalendar, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(nameMap);

		long calendarId = counterLocalService.increment();

		Calendar calendar = calendarPersistence.create(calendarId);

		calendar.setUuid(serviceContext.getUuid());
		calendar.setGroupId(groupId);
		calendar.setCompanyId(user.getCompanyId());
		calendar.setUserId(user.getUserId());
		calendar.setUserName(user.getFullName());
		calendar.setCreateDate(serviceContext.getCreateDate(now));
		calendar.setModifiedDate(serviceContext.getModifiedDate(now));
		calendar.setCalendarResourceId(calendarResourceId);
		calendar.setNameMap(nameMap);
		calendar.setDescriptionMap(descriptionMap);
		calendar.setColor(color);
		calendar.setDefaultCalendar(defaultCalendar);

		calendarPersistence.update(calendar, false);

		// Resources

		resourceLocalService.addModelResources(calendar, serviceContext);

		// Calendar resource

		if (defaultCalendar) {
			calendarResourceLocalService.updateDefaultCalendarId(
				calendarResourceId, calendarId);
		}

		return calendar;
	}

	@Override
	public Calendar deleteCalendar(Calendar calendar)
		throws PortalException, SystemException {

		// Calendar

		calendarPersistence.remove(calendar);

		// Resources

		resourceLocalService.deleteResource(
			calendar, ResourceConstants.SCOPE_INDIVIDUAL);

		// Calendar bookings

		calendarBookingLocalService.deleteCalendarBookings(
			calendar.getCalendarId());

		return calendar;
	}

	@Override
	public Calendar deleteCalendar(long calendarId)
		throws PortalException, SystemException {

		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		return deleteCalendar(calendar);
	}

	@Override
	public Calendar getCalendar(long calendarId)
		throws PortalException, SystemException {

		return calendarPersistence.findByPrimaryKey(calendarId);
	}

	public List<Calendar> getResourceCalendars(
			long groupId, long calendarResourceId)
		throws SystemException {

		return calendarPersistence.findByG_C(groupId, calendarResourceId);
	}

	public List<Calendar> search(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String name, String description, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		return calendarFinder.findByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, name, description,
			andOperator, start, end, orderByComparator);
	}

	public List<Calendar> searchByKeywords(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String keywords, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarFinder.findByKeywords(
			companyId, groupIds, calendarResourceIds, keywords, start, end,
			orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String keywords, boolean andOperator)
		throws SystemException {

		return calendarFinder.countByKeywords(
			companyId, groupIds, calendarResourceIds, keywords);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String name, String description, boolean andOperator)
		throws SystemException {

		return calendarFinder.countByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, name, description,
			andOperator);
	}

	public Calendar updateCalendar(
			long calendarId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int color,
			boolean defaultCalendar, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar

		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		validate(nameMap);

		calendar.setModifiedDate(serviceContext.getModifiedDate(null));
		calendar.setNameMap(nameMap);
		calendar.setDescriptionMap(descriptionMap);
		calendar.setColor(color);
		calendar.setDefaultCalendar(defaultCalendar);

		calendarPersistence.update(calendar, false);

		// Calendar resource

		if (defaultCalendar) {
			calendarResourceLocalService.updateDefaultCalendarId(
				calendar.getCalendarResourceId(), calendarId);
		}

		// Resources

		resourceLocalService.updateModelResources(calendar, serviceContext);

		return calendar;
	}

	public Calendar updateCalendar(
			long calendarId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int color,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		return updateCalendar(
			calendarId, nameMap, descriptionMap, color,
			calendar.isDefaultCalendar(), serviceContext);
	}

	protected void validate(Map<Locale, String> nameMap)
		throws PortalException {

		Locale locale = LocaleUtil.getDefault();

		String name = nameMap.get(locale);

		if (Validator.isNull(name)) {
			throw new CalendarNameException();
		}
	}

}