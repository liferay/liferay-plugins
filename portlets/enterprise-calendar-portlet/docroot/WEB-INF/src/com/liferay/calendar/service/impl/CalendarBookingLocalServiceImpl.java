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

import com.liferay.calendar.CalendarBookingDurationException;
import com.liferay.calendar.CalendarBookingTitleException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.base.CalendarBookingLocalServiceBaseImpl;
import com.liferay.calendar.util.CalendarUtil;
import com.liferay.calendar.workflow.CalendarBookingApprovalWorkflow;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.Conjunction;
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
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.calendar.EventEndDateException;
import com.liferay.portlet.calendar.EventStartDateException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarBookingLocalServiceImpl
	extends CalendarBookingLocalServiceBaseImpl {

	public CalendarBooking addCalendarBooking(
			long userId, long calendarId, long parentCalendarBookingId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> locationMap, String type, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, boolean allDay,
			String recurrence, int priority, boolean outOfOffice,
			int firstReminder, int secondReminder, boolean required,
			String requestMessage, String responseMessage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);
		Date now = new Date();

		if (allDay) {
			startDateHour = 0;
			startDateMinute = 0;

			endDateHour = 23;
			endDateMinute = 59;
		}

		java.util.Calendar startDateJCalendar = getJCalendar(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute);
		java.util.Calendar endDateJCalendar = getJCalendar(
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute);

		validate(
			titleMap, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, startDateJCalendar, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute,
			endDateJCalendar, recurrence);

		if (firstReminder < secondReminder) {
			int originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		long calendarBookingId = counterLocalService.increment();

		CalendarBooking calendarBooking = calendarBookingPersistence.create(
			calendarBookingId);

		calendarBooking.setUuid(serviceContext.getUuid());
		calendarBooking.setGroupId(calendar.getGroupId());
		calendarBooking.setCompanyId(user.getCompanyId());
		calendarBooking.setUserId(user.getUserId());
		calendarBooking.setUserName(user.getFullName());
		calendarBooking.setCreateDate(serviceContext.getCreateDate(now));
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(now));
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setCalendarResourceId(calendar.getCalendarResourceId());
		calendarBooking.setParentCalendarBookingId(parentCalendarBookingId);
		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocationMap(locationMap);
		calendarBooking.setType(type);
		calendarBooking.setStartDate(startDateJCalendar.getTime());
		calendarBooking.setEndDate(endDateJCalendar.getTime());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setPriority(priority);
		calendarBooking.setOutOfOffice(outOfOffice);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setRequired(required);
		calendarBooking.setRequestMessage(requestMessage);
		calendarBooking.setResponseMessage(responseMessage);
		calendarBooking.setStatus(WorkflowConstants.STATUS_DRAFT);
		calendarBooking.setStatusDate(serviceContext.getModifiedDate(now));

		calendarBookingPersistence.update(calendarBooking, false);

		// Resources

		resourceLocalService.addModelResources(calendarBooking, serviceContext);

		// Workflow

		calendarBookingApprovalWorkflow.startWorkflow(
			userId, calendarBookingId, serviceContext);

		return calendarBooking;
	}

	@Override
	public CalendarBooking deleteCalendarBooking(
			CalendarBooking calendarBooking)
		throws SystemException, PortalException {

		// Calendar booking

		calendarBookingPersistence.remove(calendarBooking);

		// Resources

		resourceLocalService.deleteResource(
			calendarBooking, ResourceConstants.SCOPE_INDIVIDUAL);

		return calendarBooking;
	}

	@Override
	public CalendarBooking deleteCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		deleteCalendarBooking(calendarBooking);

		return calendarBooking;
	}

	public void deleteCalendarBookings(long calendarId)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingPersistence.findByCalendarId(calendarId);

		for (CalendarBooking calendarBooking : calendarBookings) {
			deleteCalendarBooking(calendarBooking);
		}
	}

	@Override
	public CalendarBooking getCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		return calendarBookingPersistence.findByPrimaryKey(calendarBookingId);
	}

	public List<CalendarBooking> getCalendarBookings(long calendarId)
		throws SystemException {

		return calendarBookingPersistence.findByCalendarId(calendarId);
	}

	public List<CalendarBooking> getCalendarBookings(
			long calendarId, Date startDate, Date endDate)
		throws SystemException {

		return calendarBookingPersistence.findByC_S_E(
			calendarId, startDate, endDate);
	}

	public List<CalendarBooking> search(
			long calendarId, long calendarResourceId, String title,
			String description, String location, String type, Date startDate,
			Date endDate, Boolean allDay, int priority, Boolean outOfOffice,
			Boolean required, int status, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			calendarId, calendarResourceId, title, description, location, type,
			startDate, endDate, allDay, priority, outOfOffice, required, status,
			andOperator);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public long searchCount(
			long calendarId, long calendarResourceId, String title,
			String description, String location, String type, Date startDate,
			Date endDate, Boolean allDay, int priority, Boolean outOfOffice,
			Boolean required, int status, boolean andOperator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			calendarId, calendarResourceId, title, description, location, type,
			startDate, endDate, allDay, priority, outOfOffice, required, status,
			andOperator);

		return dynamicQueryCount(dynamicQuery);
	}

	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> locationMap, String type, int status,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			boolean allDay, String recurrence, int priority,
			boolean outOfOffice, int firstReminder, int secondReminder,
			boolean required, String requestMessage, String responseMessage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);

		if (allDay) {
			startDateHour = 0;
			startDateMinute = 0;

			endDateHour = 23;
			endDateMinute = 59;
		}

		java.util.Calendar startDateJCalendar = getJCalendar(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute);
		java.util.Calendar endDateJCalendar = getJCalendar(
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute);

		validate(
			titleMap, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, startDateJCalendar, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute,
			endDateJCalendar, recurrence);

		if (firstReminder < secondReminder) {
			int originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		calendarBooking.setCompanyId(user.getCompanyId());
		calendarBooking.setUserId(user.getUserId());
		calendarBooking.setUserName(user.getFullName());
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocationMap(locationMap);
		calendarBooking.setType(type);
		calendarBooking.setStartDate(startDateJCalendar.getTime());
		calendarBooking.setEndDate(endDateJCalendar.getTime());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setPriority(priority);
		calendarBooking.setOutOfOffice(outOfOffice);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setRequired(required);
		calendarBooking.setRequestMessage(requestMessage);
		calendarBooking.setResponseMessage(responseMessage);

		calendarBookingPersistence.update(calendarBooking, false);

		// Resources

		resourceLocalService.updateModelResources(
			calendarBooking, serviceContext);

		// Workflow

		calendarBookingApprovalWorkflow.invokeTransition(
			userId, calendarBookingId,
			CalendarBookingWorkflowConstants.toLabel(status), serviceContext);

		return calendarBooking;
	}

	public CalendarBooking updateStatus(
			long userId, long calendarBookingId, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(now));
		calendarBooking.setStatus(status);
		calendarBooking.setStatusByUserId(user.getUserId());
		calendarBooking.setStatusByUserName(user.getFullName());
		calendarBooking.setStatusDate(serviceContext.getModifiedDate(now));

		calendarBookingPersistence.update(calendarBooking, false);

		return calendarBooking;
	}

	protected DynamicQuery buildDynamicQuery(
		long calendarId, long calendarResourceId, String title,
		String description, String location, String type, Date startDate,
		Date endDate, Boolean allDay, int priority, Boolean outOfOffice,
		Boolean required, int status, boolean andOperator) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CalendarBooking.class, getClassLoader());

		if (calendarId > 0) {
			Property property = PropertyFactoryUtil.forName("calendarId");

			dynamicQuery.add(property.eq(calendarId));
		}

		if (calendarResourceId > 0) {
			Property property = PropertyFactoryUtil.forName(
				"calendarResourceId");

			dynamicQuery.add(property.eq(calendarResourceId));
		}

		if (Validator.isNotNull(type)) {
			Property property = PropertyFactoryUtil.forName("type");

			dynamicQuery.add(property.eq(type));
		}

		if ((endDate != null) && (startDate != null)) {
			Conjunction conjunction = RestrictionsFactoryUtil.conjunction();

			Property propertyEnd = PropertyFactoryUtil.forName("endDate");
			Property propertyStart = PropertyFactoryUtil.forName("startDate");

			conjunction.add(propertyEnd.ge(startDate));
			conjunction.add(propertyStart.le(endDate));

			dynamicQuery.add(conjunction);
		}

		if (allDay != null) {
			Property property = PropertyFactoryUtil.forName("allDay");

			dynamicQuery.add(property.eq(allDay));
		}

		if (priority >= 0) {
			Property property = PropertyFactoryUtil.forName("priority");

			dynamicQuery.add(property.eq(priority));
		}

		if (outOfOffice != null) {
			Property property = PropertyFactoryUtil.forName("outOfOffice");

			dynamicQuery.add(property.eq(outOfOffice));
		}

		if (required != null) {
			Property property = PropertyFactoryUtil.forName("required");

			dynamicQuery.add(property.eq(required));
		}

		if (status != WorkflowConstants.STATUS_ANY) {
			Property property = PropertyFactoryUtil.forName("status");

			dynamicQuery.add(property.eq(status));
		}

		Junction junction = null;

		if (andOperator) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		Map<String, String> terms = new HashMap<String, String>();

		if (Validator.isNotNull(title)) {
			terms.put("title", title);
		}

		if (Validator.isNotNull(description)) {
			terms.put("description", description);
		}

		if (Validator.isNotNull(location)) {
			terms.put("location", location);
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

	protected java.util.Calendar getJCalendar(
		int month, int day, int year, int hour, int minute) {

		TimeZone timeZone = TimeZoneUtil.getDefault();
		Locale locale = LocaleUtil.getDefault();

		java.util.Calendar jCalendar = CalendarFactoryUtil.getCalendar(
			timeZone, locale);

		jCalendar.set(java.util.Calendar.MONTH, month);
		jCalendar.set(java.util.Calendar.DATE, day);
		jCalendar.set(java.util.Calendar.YEAR, year);
		jCalendar.set(java.util.Calendar.HOUR_OF_DAY, hour);
		jCalendar.set(java.util.Calendar.MINUTE, minute);
		jCalendar.set(java.util.Calendar.SECOND, 0);
		jCalendar.set(java.util.Calendar.MILLISECOND, 0);

		return jCalendar;
	}

	protected void validate(
			Map<Locale, String> titleMap, int startDateMonth, int startDateDay,
			int startDateYear, int startDateHour, int startDateMinute,
			java.util.Calendar startDateJCalendar, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			java.util.Calendar endDateJCalendar, String recurrence)
		throws PortalException {

		if (Validator.isNull(titleMap) || titleMap.isEmpty()) {
			throw new CalendarBookingTitleException();
		}

		if (!Validator.isDate(startDateMonth, startDateDay, startDateYear)) {
			throw new EventStartDateException();
		}

		if (!Validator.isDate(endDateMonth, endDateDay, endDateYear)) {
			throw new EventEndDateException();
		}

		if (startDateJCalendar.after(endDateJCalendar)) {
			throw new CalendarBookingDurationException();
		}
	}

	@BeanReference(type = CalendarBookingApprovalWorkflow.class)
	protected CalendarBookingApprovalWorkflow calendarBookingApprovalWorkflow;

}