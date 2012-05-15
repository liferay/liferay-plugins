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
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.base.CalendarBookingLocalServiceBaseImpl;
import com.liferay.calendar.util.CalendarUtil;
import com.liferay.calendar.workflow.CalendarBookingApprovalWorkflow;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

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
			String location, Date startDate, Date endDate, boolean allDay,
			String recurrence, int firstReminder, int secondReminder,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);
		Date now = new Date();

		TimeZone timeZone = user.getTimeZone();

		if (parentCalendarBookingId > 0) {
			CalendarBooking parentCalendarBooking =
				CalendarBookingLocalServiceUtil.getCalendarBooking(
					parentCalendarBookingId);

			User creatorUser = userPersistence.findByPrimaryKey(
				parentCalendarBooking.getUserId());

			timeZone = creatorUser.getTimeZone();
		}

		java.util.Calendar startDateCal = CalendarUtil.getCalendar(
			startDate, timeZone);

		java.util.Calendar endDateCal = CalendarUtil.getCalendar(
			endDate, timeZone);

		if (allDay) {
			startDateCal = CalendarUtil.toMidnight(startDateCal);
			endDateCal = CalendarUtil.toLastHour(endDateCal);
		}

		validate(titleMap, startDateCal, endDateCal);

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

		int status = CalendarBookingWorkflowConstants.STATUS_PENDING;

		if (parentCalendarBookingId == 0) {
			parentCalendarBookingId = calendarBookingId;

			status = CalendarBookingWorkflowConstants.STATUS_APPROVED;
		}

		calendarBooking.setParentCalendarBookingId(parentCalendarBookingId);
		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocation(location);
		calendarBooking.setStartDate(startDateCal.getTime());
		calendarBooking.setEndDate(endDateCal.getTime());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setStatus(status);
		calendarBooking.setStatusDate(serviceContext.getModifiedDate(now));

		calendarBookingPersistence.update(calendarBooking, false);

		// Workflow

		calendarBookingApprovalWorkflow.startWorkflow(
			userId, calendarBookingId, serviceContext);

		return calendarBooking;
	}

	public CalendarBooking addCalendarBooking(
			long userId, long calendarId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, Date startDate,
			Date endDate, boolean allDay, String recurrence, int firstReminder,
			int secondReminder, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return addCalendarBooking(
			userId, calendarId, 0, titleMap, descriptionMap, location,
			startDate, endDate, allDay, recurrence, firstReminder,
			secondReminder, serviceContext);
	}

	public int countByParentCalendarBookingId(
			long calendarId, long parentCalendarBookingId)
		throws PortalException, SystemException {

		return calendarBookingPersistence.countByC_P(
			calendarId, parentCalendarBookingId);
	}

	@Override
	public CalendarBooking deleteCalendarBooking(
			CalendarBooking calendarBooking)
		throws SystemException, PortalException {

		// Calendar booking

		calendarBookingPersistence.remove(calendarBooking);

		// Invited calendar bookings

		List<CalendarBooking> invitedBookings = getByParentCalendarBookingId(
			calendarBooking.getCalendarBookingId());

		for (CalendarBooking invitedBooking : invitedBookings) {
			deleteCalendarBooking(invitedBooking);
		}

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

	public CalendarBooking fetchByC_P(
			long calendarId, long parentCalendarBookingId)
		throws SystemException {

		return calendarBookingPersistence.fetchByC_P(
			calendarId, parentCalendarBookingId);
	}

	public List<CalendarBooking> findByP_S(
			long parentCalendarBookingId, int status)
		throws SystemException {

		return calendarBookingPersistence.findByP_S(
			parentCalendarBookingId, status);
	}

	public List<CalendarBooking> getByParentCalendarBookingId(
			long parentCalendarBookingId)
		throws SystemException {

		return calendarBookingPersistence.findByParentCalendarBookingId(
			parentCalendarBookingId);
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
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, Date startDate, Date endDate, int[] status,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		return calendarBookingFinder.findByKeywords(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startDate, endDate, status,
			start, end, orderByComparator);
	}

	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, Date startDate,
			Date endDate, int[] status, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarBookingFinder.findByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startDate,
			endDate, status, andOperator, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, Date startDate, Date endDate, int[] status)
		throws SystemException {

		return calendarBookingFinder.countByKeywords(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startDate, endDate, status);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, Date startDate,
			Date endDate, int[] status, boolean andOperator)
		throws SystemException {

		return calendarBookingFinder.countByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startDate,
			endDate, status, andOperator);
	}

	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, int status, Date startDate, Date endDate,
			boolean allDay, String recurrence, int firstReminder,
			int secondReminder, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		User user = userPersistence.findByPrimaryKey(userId);

		TimeZone timeZone = user.getTimeZone();

		java.util.Calendar startDateCal = CalendarUtil.getCalendar(
			startDate, timeZone);

		java.util.Calendar endDateCal = CalendarUtil.getCalendar(
			endDate, timeZone);

		if (allDay) {
			startDateCal = CalendarUtil.toMidnight(startDateCal);
			endDateCal = CalendarUtil.toLastHour(endDateCal);
		}

		validate(titleMap, startDateCal, endDateCal);

		if (firstReminder < secondReminder) {
			int originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		calendarBooking.setCompanyId(user.getCompanyId());
		calendarBooking.setUserId(user.getUserId());
		calendarBooking.setUserName(user.getFullName());
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocation(location);
		calendarBooking.setStartDate(startDateCal.getTime());
		calendarBooking.setEndDate(endDateCal.getTime());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setSecondReminder(secondReminder);

		calendarBookingPersistence.update(calendarBooking, false);

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
			Map<Locale, String> titleMap, java.util.Calendar startDateJCalendar,
			java.util.Calendar endDateJCalendar)
		throws PortalException {

		if (Validator.isNull(titleMap) || titleMap.isEmpty()) {
			throw new CalendarBookingTitleException();
		}

		if (startDateJCalendar.after(endDateJCalendar)) {
			throw new CalendarBookingDurationException();
		}
	}

	@BeanReference(type = CalendarBookingApprovalWorkflow.class)
	protected CalendarBookingApprovalWorkflow calendarBookingApprovalWorkflow;

}