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
import com.liferay.calendar.workflow.CalendarBookingApprovalWorkflow;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.calendar.EventEndDateException;
import com.liferay.portlet.calendar.EventStartDateException;

import java.util.Date;
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
			int endDateYear, int endDateHour, int endDateMinute,
			boolean allDay, String recurrence, int priority,
			boolean outOfOffice, int remindBy, int firstReminder,
			int secondReminder, boolean required, String requestMessage,
			String responseMessage, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// CalendarBooking

		User user = userPersistence.findByPrimaryKey(userId);
		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);
		Date now = new Date();

		if (allDay) {
			startDateHour = 0;
			startDateMinute = 0;

			endDateHour = 23;
			endDateMinute = 59;
		}

		Locale locale = LocaleUtil.getDefault();

		validate(
			titleMap, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, locale, recurrence);

		TimeZone timeZone = TimeZoneUtil.getDefault();

		java.util.Calendar startDate =
			CalendarFactoryUtil.getCalendar(timeZone, locale);

		startDate.set(java.util.Calendar.MONTH, startDateMonth);
		startDate.set(java.util.Calendar.DATE, startDateDay);
		startDate.set(java.util.Calendar.YEAR, startDateYear);
		startDate.set(java.util.Calendar.HOUR_OF_DAY, startDateHour);
		startDate.set(java.util.Calendar.MINUTE, startDateMinute);
		startDate.set(java.util.Calendar.SECOND, 0);
		startDate.set(java.util.Calendar.MILLISECOND, 0);

		java.util.Calendar endDate =
			CalendarFactoryUtil.getCalendar(timeZone, locale);

		endDate.set(java.util.Calendar.MONTH, endDateMonth);
		endDate.set(java.util.Calendar.DATE, endDateDay);
		endDate.set(java.util.Calendar.YEAR, endDateYear);
		endDate.set(java.util.Calendar.HOUR_OF_DAY, endDateHour);
		endDate.set(java.util.Calendar.MINUTE, endDateMinute);
		endDate.set(java.util.Calendar.SECOND, 0);
		endDate.set(java.util.Calendar.MILLISECOND, 0);

		if (firstReminder < secondReminder) {
			int tmp = secondReminder;

			secondReminder = firstReminder;
			firstReminder = tmp;
		}

		long calendarBookingId = counterLocalService.increment();

		CalendarBooking calendarBooking =
			calendarBookingPersistence.create(calendarBookingId);

		calendarBooking.setUuid(serviceContext.getUuid());
		calendarBooking.setGroupId(calendar.getGroupId());
		calendarBooking.setCompanyId(user.getCompanyId());
		calendarBooking.setUserId(user.getUserId());
		calendarBooking.setUserName(user.getFullName());
		calendarBooking.setCreateDate(serviceContext.getCreateDate(now));
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(now));
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setParentCalendarBookingId(parentCalendarBookingId);
		calendarBooking.setCalendarResourceId(calendar.getCalendarResourceId());
		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocationMap(locationMap);
		calendarBooking.setType(type);
		calendarBooking.setStartDate(startDate.getTime());
		calendarBooking.setEndDate(endDate.getTime());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setPriority(priority);
		calendarBooking.setOutOfOffice(outOfOffice);
		calendarBooking.setRemindBy(remindBy);
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

	public void deleteCalendarBooking(CalendarBooking calendarBooking)
		throws SystemException, PortalException {

		// CalendarBooking

		calendarBookingPersistence.remove(calendarBooking);

		// Resources

		resourceLocalService.deleteResource(
			calendarBooking.getCompanyId(), CalendarBooking.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			calendarBooking.getCalendarBookingId());
	}

	public void deleteCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		deleteCalendarBooking(calendarBooking);
	}

	public void deleteCalendarBookings(long calendarId)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingPersistence.findByCalendarId(calendarId);

		for (CalendarBooking calendarBooking : calendarBookings) {
			deleteCalendarBooking(calendarBooking);
		}
	}

	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> locationMap, String type, int status,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			boolean allDay, String recurrence, int priority,
			boolean outOfOffice, int remindBy, int firstReminder,
			int secondReminder, boolean required, String requestMessage,
			String responseMessage, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// CalendarBooking

		User user = userPersistence.findByPrimaryKey(userId);

		if (allDay) {
			startDateHour = 0;
			startDateMinute = 0;

			endDateHour = 23;
			endDateMinute = 59;
		}

		Locale locale = LocaleUtil.getDefault();

		validate(
			titleMap, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, locale, recurrence);

		TimeZone timeZone = TimeZoneUtil.getDefault();

		CalendarBooking calendarBooking =
			calendarBookingPersistence.fetchByPrimaryKey(calendarBookingId);

		java.util.Calendar startDate =
			CalendarFactoryUtil.getCalendar(timeZone, locale);

		startDate.set(java.util.Calendar.MONTH, startDateMonth);
		startDate.set(java.util.Calendar.DATE, startDateDay);
		startDate.set(java.util.Calendar.YEAR, startDateYear);
		startDate.set(java.util.Calendar.HOUR_OF_DAY, startDateHour);
		startDate.set(java.util.Calendar.MINUTE, startDateMinute);
		startDate.set(java.util.Calendar.SECOND, 0);
		startDate.set(java.util.Calendar.MILLISECOND, 0);

		java.util.Calendar endDate =
			CalendarFactoryUtil.getCalendar(timeZone, locale);

		endDate.set(java.util.Calendar.MONTH, endDateMonth);
		endDate.set(java.util.Calendar.DATE, endDateDay);
		endDate.set(java.util.Calendar.YEAR, endDateYear);
		endDate.set(java.util.Calendar.HOUR_OF_DAY, endDateHour);
		endDate.set(java.util.Calendar.MINUTE, endDateMinute);
		endDate.set(java.util.Calendar.SECOND, 0);
		endDate.set(java.util.Calendar.MILLISECOND, 0);

		if (firstReminder < secondReminder) {
			int tmp = secondReminder;

			secondReminder = firstReminder;
			firstReminder = tmp;
		}

		calendarBooking.setCompanyId(user.getCompanyId());
		calendarBooking.setUserId(user.getUserId());
		calendarBooking.setUserName(user.getFullName());
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocationMap(locationMap);
		calendarBooking.setType(type);
		calendarBooking.setStartDate(startDate.getTime());
		calendarBooking.setEndDate(endDate.getTime());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setPriority(priority);
		calendarBooking.setOutOfOffice(outOfOffice);
		calendarBooking.setRemindBy(remindBy);
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

	protected void validate(
			Map<Locale, String> titleMap, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, Locale locale,
			String recurrence)
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

		TimeZone timeZone = TimeZone.getDefault();

		java.util.Calendar startDate =
			CalendarFactoryUtil.getCalendar(timeZone, locale);

		startDate.set(java.util.Calendar.MONTH, startDateMonth);
		startDate.set(java.util.Calendar.DATE, startDateDay);
		startDate.set(java.util.Calendar.YEAR, startDateYear);
		startDate.set(java.util.Calendar.HOUR_OF_DAY, startDateHour);
		startDate.set(java.util.Calendar.MINUTE, startDateMinute);
		startDate.set(java.util.Calendar.SECOND, 0);
		startDate.set(java.util.Calendar.MILLISECOND, 0);

		java.util.Calendar endDate =
			CalendarFactoryUtil.getCalendar(timeZone, locale);

		endDate.set(java.util.Calendar.MONTH, endDateMonth);
		endDate.set(java.util.Calendar.DATE, endDateDay);
		endDate.set(java.util.Calendar.YEAR, endDateYear);
		endDate.set(java.util.Calendar.HOUR_OF_DAY, endDateHour);
		endDate.set(java.util.Calendar.MINUTE, endDateMinute);
		endDate.set(java.util.Calendar.SECOND, 0);
		endDate.set(java.util.Calendar.MILLISECOND, 0);

		if (startDate.after(endDate)) {
			throw new CalendarBookingDurationException();
		}
	}

	@BeanReference(type = CalendarBookingApprovalWorkflow.class)
	protected CalendarBookingApprovalWorkflow calendarBookingApprovalWorkflow;

}