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
import com.liferay.calendar.util.CalendarUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.HashMap;
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
			long groupId, long calendarResourceId, String name,
			String description, Boolean defaultCalendar, boolean andOperator,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, calendarResourceId, name, description, defaultCalendar,
			andOperator);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public long searchCount(
			long groupId, long calendarResourceId, String name,
			String description, Boolean defaultCalendar, boolean andOperator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, calendarResourceId, name, description, defaultCalendar,
			andOperator);

		return dynamicQueryCount(dynamicQuery);
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

	protected DynamicQuery buildDynamicQuery(
		long groupId, long calendarResourceId, String name, String description,
		Boolean defaultCalendar, boolean andOperator) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Calendar.class, getClassLoader());

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		if (calendarResourceId > 0) {
			Property property = PropertyFactoryUtil.forName(
				"calendarResourceId");

			dynamicQuery.add(property.eq(calendarResourceId));
		}

		if (defaultCalendar != null) {
			Property property = PropertyFactoryUtil.forName("defaultCalendar");

			dynamicQuery.add(property.eq(defaultCalendar));
		}

		Junction junction = null;

		if (andOperator) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		Map<String, String> terms = new HashMap<String, String>();

		if (Validator.isNotNull(name)) {
			terms.put("name", name);
		}

		if (Validator.isNotNull(description)) {
			terms.put("description", description);
		}

		for (Map.Entry<String, String> entry : terms.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			for (String keyword : CalendarUtil.splitKeywords(value)) {
				Criterion criterion = RestrictionsFactoryUtil.ilike(
					key, StringUtil.quote(keyword, StringPool.PERCENT));

				disjunction.add(criterion);
			}

			junction.add(disjunction);
		}

		return dynamicQuery.add(junction);
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