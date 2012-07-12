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
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.base.CalendarBookingLocalServiceBaseImpl;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.calendar.util.NotificationUtil;
import com.liferay.calendar.util.PortletPropsValues;
import com.liferay.calendar.util.RecurrenceUtil;
import com.liferay.calendar.workflow.CalendarBookingApprovalWorkflow;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarBookingLocalServiceImpl
	extends CalendarBookingLocalServiceBaseImpl {

	public CalendarBooking addCalendarBooking(
			long userId, long calendarId, long[] childCalendarIds,
			long parentCalendarBookingId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startDate,
			long endDate, boolean allDay, String recurrence, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		java.util.Calendar startDateJCalendar = JCalendarUtil.getJCalendar(
			startDate);
		java.util.Calendar endDateJCalendar = JCalendarUtil.getJCalendar(
			endDate);

		if (allDay) {
			startDateJCalendar = JCalendarUtil.toMidnightJCalendar(
				startDateJCalendar);
			endDateJCalendar = JCalendarUtil.toLastHourJCalendar(
				endDateJCalendar);
		}

		if (firstReminder < secondReminder) {
			long originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		Date now = new Date();

		validate(titleMap, startDateJCalendar, endDateJCalendar);

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

		if (parentCalendarBookingId > 0) {
			calendarBooking.setParentCalendarBookingId(parentCalendarBookingId);
		}
		else {
			calendarBooking.setParentCalendarBookingId(calendarBookingId);
		}

		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocation(location);
		calendarBooking.setStartDate(startDateJCalendar.getTimeInMillis());
		calendarBooking.setEndDate(endDateJCalendar.getTimeInMillis());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setFirstReminderType(firstReminderType);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setSecondReminderType(secondReminderType);

		int status = CalendarBookingWorkflowConstants.STATUS_PENDING;

		if (parentCalendarBookingId == 0) {
			status = CalendarBookingWorkflowConstants.STATUS_APPROVED;
		}

		calendarBooking.setStatus(status);

		calendarBooking.setStatusDate(serviceContext.getModifiedDate(now));

		calendarBookingPersistence.update(calendarBooking, false);

		addChildCalendarBookings(
			calendarBooking, childCalendarIds, serviceContext);

		// Workflow

		calendarBookingApprovalWorkflow.startWorkflow(
			userId, calendarBookingId, serviceContext);

		return calendarBooking;
	}

	public void checkCalendarBookings()
		throws PortalException, SystemException {

		Date now = new Date();

		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByFutureReminders(now.getTime());

		for (CalendarBooking calendarBooking : calendarBookings) {
			try {
				NotificationUtil.notifyCalendarBookingReminders(
					calendarBooking);
			}
			catch (PortalException pe) {
				throw pe;
			}
			catch (SystemException se) {
				throw se;
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
		}
	}

	@Override
	public CalendarBooking deleteCalendarBooking(
			CalendarBooking calendarBooking)
		throws PortalException, SystemException {

		// Calendar booking

		calendarBookingPersistence.remove(calendarBooking);

		// Calendar bookings

		List<CalendarBooking> childCalendarBookings = getChildCalendarBookings(
			calendarBooking.getCalendarBookingId());

		for (CalendarBooking childCalendarBooking : childCalendarBookings) {
			deleteCalendarBooking(childCalendarBooking);
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

	public CalendarBooking fetchCalendarBooking(String uuid, long groupId)
		throws SystemException {

		return calendarBookingPersistence.fetchByUUID_G(uuid, groupId);
	}

	@Override
	public CalendarBooking getCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		return calendarBookingPersistence.findByPrimaryKey(calendarBookingId);
	}

	public CalendarBooking getCalendarBooking(
			long calendarId, long parentCalendarBookingId)
		throws PortalException, SystemException {

		return calendarBookingPersistence.findByC_P(
			calendarId, parentCalendarBookingId);
	}

	public List<CalendarBooking> getCalendarBookings(long calendarId)
		throws SystemException {

		return calendarBookingPersistence.findByCalendarId(calendarId);
	}

	public List<CalendarBooking> getCalendarBookings(
			long calendarId, long startDate, long endDate)
		throws SystemException {

		return calendarBookingPersistence.findByC_S_E(
			calendarId, startDate, endDate);
	}

	public int getCalendarBookingsCount(
			long calendarId, long parentCalendarBookingId)
		throws SystemException {

		return calendarBookingPersistence.countByC_P(
			calendarId, parentCalendarBookingId);
	}

	public List<CalendarBooking> getChildCalendarBookings(
			long calendarBookingId)
		throws SystemException {

		return calendarBookingPersistence.findByParentCalendarBookingId(
			calendarBookingId);
	}

	public List<CalendarBooking> getChildCalendarBookings(
			long parentCalendarBookingId, int status)
		throws SystemException {

		return calendarBookingPersistence.findByP_S(
			parentCalendarBookingId, status);
	}

	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startDate, long endDate, boolean recurring,
			int[] statuses, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByKeywords(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, keywords, startDate, endDate,
				recurring, statuses, start, end, orderByComparator);

		if (recurring) {
			calendarBookings = RecurrenceUtil.expandCalendarBookings(
				calendarBookings, endDate);
		}

		return calendarBookings;
	}

	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startDate,
			long endDate, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByC_G_C_C_P_T_D_L_S_E_S(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, title, description, location,
				startDate, endDate, recurring, statuses, andOperator, start,
				end, orderByComparator);

		if (recurring) {
			calendarBookings = RecurrenceUtil.expandCalendarBookings(
				calendarBookings, endDate);
		}

		return calendarBookings;
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startDate, long endDate, int[] statuses)
		throws SystemException {

		return calendarBookingFinder.countByKeywords(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startDate, endDate, statuses);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startDate,
			long endDate, int[] statuses, boolean andOperator)
		throws SystemException {

		return calendarBookingFinder.countByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startDate,
			endDate, statuses, andOperator);
	}

	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			long[] childCalendarIds, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startDate,
			long endDate, boolean allDay, String recurrence, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		java.util.Calendar startDateJCalendar = JCalendarUtil.getJCalendar(
			startDate);
		java.util.Calendar endDateJCalendar = JCalendarUtil.getJCalendar(
			endDate);

		if (allDay) {
			startDateJCalendar = JCalendarUtil.toMidnightJCalendar(
				startDateJCalendar);
			endDateJCalendar = JCalendarUtil.toLastHourJCalendar(
				endDateJCalendar);
		}

		if (firstReminder < secondReminder) {
			long originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		validate(titleMap, startDateJCalendar, endDateJCalendar);

		calendarBooking.setCompanyId(user.getCompanyId());
		calendarBooking.setUserId(user.getUserId());
		calendarBooking.setUserName(user.getFullName());
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocation(location);
		calendarBooking.setStartDate(startDateJCalendar.getTimeInMillis());
		calendarBooking.setEndDate(endDateJCalendar.getTimeInMillis());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setFirstReminderType(firstReminderType);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setSecondReminderType(secondReminderType);

		calendarBookingPersistence.update(calendarBooking, false);

		addChildCalendarBookings(
			calendarBooking, childCalendarIds, serviceContext);

		// Workflow

		calendarBookingApprovalWorkflow.invokeTransition(
			userId, calendarBookingId,
			CalendarBookingWorkflowConstants.toLabel(status), serviceContext);

		return calendarBooking;
	}

	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startDate, long endDate, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		List<CalendarBooking> childCalendarBookings =
			calendarBookingPersistence.findByParentCalendarBookingId(
				calendarBookingId);

		long[] childCalendarIds = new long[childCalendarBookings.size()];

		for (int i = 0; i < childCalendarIds.length; i++) {
			CalendarBooking childCalendarBooking = childCalendarBookings.get(i);

			if (childCalendarBooking.getCalendarId() ==
					calendarBooking.getCalendarId()) {

				childCalendarIds[i] = calendarId;
			}
			else {
				childCalendarIds[i] = childCalendarBooking.getCalendarId();
			}
		}

		return updateCalendarBooking(
			userId, calendarBookingId, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startDate, endDate, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
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

	protected void addChildCalendarBookings(
			CalendarBooking calendarBooking, long[] childCalendarIds,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (!calendarBooking.isMasterBooking()) {
			return;
		}

		List<CalendarBooking> childCalendarBookings =
			calendarBookingPersistence.findByParentCalendarBookingId(
				calendarBooking.getCalendarBookingId());

		for (CalendarBooking childCalendarBooking : childCalendarBookings) {
			if (childCalendarBooking.isMasterBooking() ||
				ArrayUtil.contains(
					childCalendarIds, childCalendarBooking.getCalendarId())) {

				continue;
			}

			deleteCalendarBooking(childCalendarBooking.getCalendarBookingId());
		}

		for (long calendarId : childCalendarIds) {
			int count = calendarBookingPersistence.countByC_P(
				calendarId, calendarBooking.getCalendarBookingId());

			if (count > 0) {
				continue;
			}

			CalendarBooking childCalendarBooking = addCalendarBooking(
				calendarBooking.getUserId(), calendarId, new long[0],
				calendarBooking.getCalendarBookingId(),
				calendarBooking.getTitleMap(),
				calendarBooking.getDescriptionMap(),
				calendarBooking.getLocation(), calendarBooking.getStartDate(),
				calendarBooking.getEndDate(), calendarBooking.getAllDay(),
				calendarBooking.getRecurrence(),
				calendarBooking.getFirstReminder(),
				calendarBooking.getFirstReminderType(),
				calendarBooking.getSecondReminder(),
				calendarBooking.getSecondReminderType(), serviceContext);

			try {
				NotificationType notificationType = NotificationType.parse(
					PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE);

				NotificationUtil.notifyCalendarBookingInvites(
					childCalendarBooking, notificationType);
			}
			catch (Exception e) {
			}
		}
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