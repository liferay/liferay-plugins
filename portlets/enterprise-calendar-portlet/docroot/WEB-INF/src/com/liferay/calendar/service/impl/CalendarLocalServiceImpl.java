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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
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

		return calendar;
	}

	public void deleteCalendar(Calendar calendar)
		throws PortalException, SystemException {

		// Calendar

		calendarPersistence.remove(calendar);

		// Calendar bookings

		calendarBookingLocalService.deleteCalendarBookings(
			calendar.getCalendarId());

		// Resources

		resourceLocalService.deleteResource(
			calendar.getCompanyId(), Calendar.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, calendar.getCalendarId());
	}

	public void deleteCalendar(long calendarId)
		throws PortalException, SystemException {

		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		deleteCalendar(calendar);
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

		// Resources

		resourceLocalService.updateModelResources(calendar, serviceContext);

		return calendar;
	}

	protected void validate(Map<Locale, String> nameMap)
		throws PortalException, SystemException {

		Locale locale = LocaleUtil.getDefault();

		String name = nameMap.get(locale);

		if (Validator.isNull(name)) {
			throw new CalendarNameException();
		}
	}

}