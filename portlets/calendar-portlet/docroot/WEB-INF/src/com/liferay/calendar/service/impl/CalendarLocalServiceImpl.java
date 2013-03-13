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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.CalendarNameException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.base.CalendarLocalServiceBaseImpl;
import com.liferay.calendar.util.PortletPropsValues;
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

		if (color <= 0) {
			color = PortletPropsValues.CALENDAR_COLOR_DEFAULT;
		}

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

		calendarPersistence.update(calendar);

		// Resources

		resourceLocalService.addModelResources(calendar, serviceContext);

		// Calendar

		updateDefaultCalendar(calendar);

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
	public Calendar fetchCalendar(long calendarId) throws SystemException {
		return calendarPersistence.fetchByPrimaryKey(calendarId);
	}

	@Override
	public Calendar getCalendar(long calendarId)
		throws PortalException, SystemException {

		return calendarPersistence.findByPrimaryKey(calendarId);
	}

	public List<Calendar> getCalendarResourceCalendars(
			long groupId, long calendarResourceId)
		throws SystemException {

		return calendarPersistence.findByG_C(groupId, calendarResourceId);
	}

	public List<Calendar> getCalendarResourceCalendars(
			long groupId, long calendarResourceId, boolean defaultCalendar)
		throws SystemException {

		return calendarPersistence.findByG_C_D(
			groupId, calendarResourceId, defaultCalendar);
	}

	public List<Calendar> search(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String keywords, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarFinder.findByKeywords(
			companyId, groupIds, calendarResourceIds, keywords, start, end,
			orderByComparator);
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

	public void updateCalendar(long calendarId, boolean defaultCalendar)
		throws PortalException, SystemException {

		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		calendar.setDefaultCalendar(defaultCalendar);

		calendarPersistence.update(calendar);

		updateDefaultCalendar(calendar);
	}

	public Calendar updateCalendar(
			long calendarId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int color,
			boolean defaultCalendar, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar

		if (color <= 0) {
			color = PortletPropsValues.CALENDAR_COLOR_DEFAULT;
		}

		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		validate(nameMap);

		calendar.setModifiedDate(serviceContext.getModifiedDate(null));
		calendar.setNameMap(nameMap);
		calendar.setDescriptionMap(descriptionMap);
		calendar.setColor(color);
		calendar.setDefaultCalendar(defaultCalendar);

		calendarPersistence.update(calendar);

		// Calendar

		updateDefaultCalendar(calendar);

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

	public Calendar updateColor(
			long calendarId, int color, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (color <= 0) {
			color = PortletPropsValues.CALENDAR_COLOR_DEFAULT;
		}

		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		calendar.setModifiedDate(serviceContext.getModifiedDate(null));
		calendar.setColor(color);

		calendarPersistence.update(calendar);

		return calendar;
	}

	protected void updateDefaultCalendar(Calendar calendar)
		throws PortalException, SystemException {

		if (!calendar.isDefaultCalendar()) {
			return;
		}

		List<Calendar> defaultCalendars = getCalendarResourceCalendars(
			calendar.getGroupId(), calendar.getCalendarResourceId(), true);

		for (Calendar defaultCalendar : defaultCalendars) {
			if (defaultCalendar.getCalendarId() == calendar.getCalendarId()) {
				continue;
			}

			updateCalendar(defaultCalendar.getCalendarId(), false);
		}
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