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
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.base.CalendarBookingLocalServiceBaseImpl;
import com.liferay.calendar.util.CalendarUtil;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.calendar.util.PortletPropsValues;
import com.liferay.calendar.workflow.CalendarBookingApprovalWorkflow;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.SubscriptionSender;

import java.text.Format;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.internet.InternetAddress;
import javax.portlet.PortletPreferences;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarBookingLocalServiceImpl
	extends CalendarBookingLocalServiceBaseImpl {

	public CalendarBooking addCalendarBooking(
			long userId, long calendarId, long[] childCalendarIds,
			long parentCalendarBookingId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, Date startDate,
			Date endDate, boolean allDay, String recurrence, int firstReminder,
			int secondReminder, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		TimeZone timeZone = user.getTimeZone();

		if (parentCalendarBookingId > 0) {
			CalendarBooking parentCalendarBooking =
				CalendarBookingLocalServiceUtil.getCalendarBooking(
					parentCalendarBookingId);

			User creatorUser = userPersistence.findByPrimaryKey(
				parentCalendarBooking.getUserId());

			timeZone = creatorUser.getTimeZone();
		}

		java.util.Calendar startDateJCalendar = JCalendarUtil.getJCalendar(
			startDate, timeZone);
		java.util.Calendar endDateJCalendar = JCalendarUtil.getJCalendar(
			endDate, timeZone);

		if (allDay) {
			startDateJCalendar = toMidnightJCalendar(startDateJCalendar);
			endDateJCalendar = toLastHourJCalendar(endDateJCalendar);
		}

		if (firstReminder < secondReminder) {
			int originalSecondReminder = secondReminder;

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
		calendarBooking.setStartDate(startDateJCalendar.getTime());
		calendarBooking.setEndDate(endDateJCalendar.getTime());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setSecondReminder(secondReminder);

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

	public void checkBookings() throws PortalException, SystemException {
		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByFutureReminders();

		for (CalendarBooking calendarBooking : calendarBookings) {

			Calendar calendar = calendarPersistence.findByPrimaryKey(
				calendarBooking.getCalendarId());

			CalendarResource calendarResource =
				calendarResourcePersistence.findByPrimaryKey(
					calendar.getCalendarResourceId());

			if (calendarResource.getClassNameId() !=
					PortalUtil.getClassNameId(User.class)) {
				return;
			}

			User user = userPersistence.fetchByPrimaryKey(
				calendarResource.getUserId());

			if (user == null) {
				deleteCalendarBooking(calendarBooking);

				return;
			}

			java.util.Calendar now = CalendarFactoryUtil.getCalendar(
				user.getTimeZone());

			java.util.Calendar startDate = JCalendarUtil.getJCalendar(
				calendarBooking.getStartDate(), user.getTimeZone());

			remindUser(calendarBooking, user, startDate, now);
		}
	}

	@Override
	public CalendarBooking deleteCalendarBooking(
			CalendarBooking calendarBooking)
		throws SystemException, PortalException {

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
			long calendarId, Date startDate, Date endDate)
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
			String keywords, Date startDate, Date endDate, int[] statuses,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		return calendarBookingFinder.findByKeywords(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startDate, endDate, statuses,
			start, end, orderByComparator);
	}

	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, Date startDate,
			Date endDate, int[] statuses, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		return calendarBookingFinder.findByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startDate,
			endDate, statuses, andOperator, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, Date startDate, Date endDate, int[] statuses)
		throws SystemException {

		return calendarBookingFinder.countByKeywords(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startDate, endDate, statuses);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, Date startDate,
			Date endDate, int[] statuses, boolean andOperator)
		throws SystemException {

		return calendarBookingFinder.countByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startDate,
			endDate, statuses, andOperator);
	}

	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			long[] childCalendarIds, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, Date startDate,
			Date endDate, boolean allDay, String recurrence, int firstReminder,
			int secondReminder, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		java.util.Calendar startDateJCalendar = JCalendarUtil.getJCalendar(
			startDate, user.getTimeZone());
		java.util.Calendar endDateJCalendar = JCalendarUtil.getJCalendar(
			endDate, user.getTimeZone());

		if (allDay) {
			startDateJCalendar = toMidnightJCalendar(startDateJCalendar);
			endDateJCalendar = toLastHourJCalendar(endDateJCalendar);
		}

		if (firstReminder < secondReminder) {
			int originalSecondReminder = secondReminder;

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
		calendarBooking.setStartDate(startDateJCalendar.getTime());
		calendarBooking.setEndDate(endDateJCalendar.getTime());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setSecondReminder(secondReminder);

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
			String location, Date startDate, Date endDate, boolean allDay,
			String recurrence, int firstReminder, int secondReminder,
			int status, ServiceContext serviceContext)
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
			firstReminder, secondReminder, status, serviceContext);
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
				calendarBooking.getLocation(),
				calendarBooking.getUTCStartDate(),
				calendarBooking.getUTCEndDate(), calendarBooking.getAllDay(),
				calendarBooking.getRecurrence(),
				calendarBooking.getFirstReminder(),
				calendarBooking.getSecondReminder(), serviceContext);

			sendBookingNotifications(
				childCalendarBooking, calendarId, serviceContext);
		}
	}

	protected void remindUser(
			CalendarBooking calendarBooking, User user,
			java.util.Calendar startDate) {

		try {
			long ownerId = calendarBooking.getGroupId();
			int ownerType = PortletKeys.PREFS_OWNER_TYPE_GROUP;
			long plid = PortletKeys.PREFS_PLID_SHARED;
			String portletId = PortletKeys.CALENDAR;

			PortletPreferences preferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					calendarBooking.getCompanyId(), ownerId, ownerType, plid,
					portletId);

			if (!CalendarUtil.getEmailBookingReminderEnabled(preferences)) {
				return;
			}

			Company company = CompanyLocalServiceUtil.getCompany(
				user.getCompanyId());

			String fromName = CalendarUtil.getEmailFromName(
				preferences, calendarBooking.getCompanyId());
			String fromAddress = CalendarUtil.getEmailFromAddress(
				preferences, calendarBooking.getCompanyId());

			String toName = user.getFullName();
			String toAddress = user.getEmailAddress();

			String subject = CalendarUtil.getEmailBookingReminderSubject(
				preferences);
			String body = CalendarUtil.getEmailBookingReminderBody(preferences);

			Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
				user.getLocale(), user.getTimeZone());

			subject = StringUtil.replace(
				subject,
				new String[] {
					"[$BOOKING_LOCATION$]", "[$BOOKING_START_DATE$]",
					"[$BOOKING_TITLE$]", "[$FROM_ADDRESS$]", "[$FROM_NAME$]",
					"[$PORTAL_URL$]", "[$TO_ADDRESS$]", "[$TO_NAME$]"
				},
				new String[] {
					calendarBooking.getLocation(),
					dateFormatDateTime.format(startDate.getTime()),
					calendarBooking.getTitle(), fromAddress, fromName,
					company.getPortalURL(calendarBooking.getGroupId()),
					HtmlUtil.escape(toAddress),
					HtmlUtil.escape(toName),
				});

			body = StringUtil.replace(
				body,
				new String[] {
					"[$BOOKING_LOCATION$]", "[$BOOKING_START_DATE$]",
					"[$BOOKING_TITLE$]", "[$FROM_ADDRESS$]", "[$FROM_NAME$]",
					"[$PORTAL_URL$]", "[$PORTLET_NAME$]", "[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					calendarBooking.getLocation(),
					dateFormatDateTime.format(startDate.getTime()),
					calendarBooking.getTitle(user.getLocale()), fromAddress,
					fromName,
					company.getPortalURL(calendarBooking.getGroupId()),
					HtmlUtil.escape(toAddress), HtmlUtil.escape(toName),
				});

			InternetAddress from = new InternetAddress(
				fromAddress, fromName);

			InternetAddress to = new InternetAddress(toAddress, toName);

			MailMessage message = new MailMessage(
				from, to, subject, body, true);

			mailService.sendEmail(message);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	protected void remindUser(
			CalendarBooking calendarBooking, User user,
			java.util.Calendar startCalendar, java.util.Calendar nowCalendar) {

		Date startDate = startCalendar.getTime();

		long startTime = startDate.getTime();

		Date nowDate = nowCalendar.getTime();

		long nowTime = nowDate.getTime();

		if (startTime < nowTime) {
			return;
		}

		long diff = (startTime - nowTime) / _CALENDAR_BOOKING_CHECK_INTERVAL;

		if ((diff ==
				(calendarBooking.getFirstReminder() /
					_CALENDAR_BOOKING_CHECK_INTERVAL)) ||
			(diff ==
				(calendarBooking.getSecondReminder() /
					_CALENDAR_BOOKING_CHECK_INTERVAL))) {

			remindUser(calendarBooking, user, startCalendar);
		}
	}

	protected void sendBookingNotifications(
			CalendarBooking calendarBooking, long calendarId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<User> users = CalendarUtil.getBookingNotificationRecipients(
			calendarBooking);

		HttpServletRequest request = serviceContext.getRequest();

		ServletContext servletContext =
			request.getSession().getServletContext();

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			serviceContext.getPortletId());

		long ownerId = calendarBooking.getGroupId();
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_GROUP;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = PortletKeys.CALENDAR;

		PortletPreferences preferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				calendarBooking.getCompanyId(), ownerId, ownerType, plid,
				portletId);

		for (User user : users) {

			java.util.Calendar startDate = JCalendarUtil.getJCalendar(
				calendarBooking.getStartDate(), user.getTimeZone());

			Company company = CompanyLocalServiceUtil.getCompany(
				user.getCompanyId());

			String portletName = PortalUtil.getPortletTitle(
				portlet, servletContext, user.getLocale());

			String fromName = CalendarUtil.getEmailFromName(
				preferences, calendarBooking.getCompanyId());
			String fromAddress = CalendarUtil.getEmailFromAddress(
				preferences, calendarBooking.getCompanyId());

			String toName = user.getFullName();
			String toAddress = user.getEmailAddress();

			String subject = CalendarUtil.getEmailBookingReminderSubject(
				preferences);
			String body = CalendarUtil.getEmailBookingReminderBody(preferences);

			Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
				user.getLocale(), user.getTimeZone());

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			subscriptionSender.setBody(body);
			subscriptionSender.setCompanyId(calendarBooking.getCompanyId());
			subscriptionSender.setContextAttributes(
				"[$BOOKING_LOCATION$]", calendarBooking.getLocation(),
				"[$BOOKING_START_DATE$]",
				dateFormatDateTime.format(startDate.getTime()),
				"[$BOOKING_TITLE$]", calendarBooking.getTitle(user.getLocale()),
				"[$FROM_ADDRESS$]", fromAddress, "[$FROM_NAME$]", fromName,
				"[$PORTAL_URL$]",
				company.getPortalURL(calendarBooking.getGroupId()),
				"[$PORTLET_NAME$]", portletName, "[$TO_ADDRESS$]", toAddress,
				"[$TO_NAME$]", toName);
			subscriptionSender.setFrom(fromAddress, fromName);
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setMailId(
				"calendar_booking", calendarBooking.getCalendarBookingId());
			subscriptionSender.setPortletId(PortletKeys.CALENDAR);
			subscriptionSender.setScopeGroupId(calendarBooking.getGroupId());
			subscriptionSender.setServiceContext(serviceContext);
			subscriptionSender.setSubject(subject);
			subscriptionSender.setUserId(user.getUserId());

			subscriptionSender.addRuntimeSubscribers(toAddress, toName);

			subscriptionSender.flushNotificationsAsync();
		}
	}

	protected java.util.Calendar toLastHourJCalendar(
		java.util.Calendar jCalendar) {

		java.util.Calendar lastHourJCalendar =
			(java.util.Calendar)jCalendar.clone();

		lastHourJCalendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
		lastHourJCalendar.set(java.util.Calendar.MINUTE, 59);
		lastHourJCalendar.set(java.util.Calendar.SECOND, 59);
		lastHourJCalendar.set(java.util.Calendar.MILLISECOND, 999);

		return lastHourJCalendar;
	}

	protected java.util.Calendar toMidnightJCalendar(
		java.util.Calendar jCalendar) {

		java.util.Calendar midnightJCalendar =
			(java.util.Calendar)jCalendar.clone();

		midnightJCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
		midnightJCalendar.set(java.util.Calendar.MINUTE, 0);
		midnightJCalendar.set(java.util.Calendar.SECOND, 0);
		midnightJCalendar.set(java.util.Calendar.MILLISECOND, 0);

		return midnightJCalendar;
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

	private static final long _CALENDAR_BOOKING_CHECK_INTERVAL =
		PortletPropsValues.CALENDAR_BOOKING_CHECK_INTERVAL * Time.MINUTE;;

	private static final Log _log = LogFactoryUtil.getLog(
		CalendarBookingLocalServiceImpl.class);

}