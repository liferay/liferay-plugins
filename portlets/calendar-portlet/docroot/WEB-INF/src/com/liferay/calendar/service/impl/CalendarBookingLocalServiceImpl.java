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

import com.liferay.calendar.CalendarBookingDurationException;
import com.liferay.calendar.CalendarBookingTitleException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.service.base.CalendarBookingLocalServiceBaseImpl;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.calendar.util.NotificationUtil;
import com.liferay.calendar.util.PortletPropsValues;
import com.liferay.calendar.util.RecurrenceUtil;
import com.liferay.calendar.workflow.CalendarBookingApprovalWorkflow;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Marcellus Tavares
 */
public class CalendarBookingLocalServiceImpl
	extends CalendarBookingLocalServiceBaseImpl {

	public CalendarBooking addCalendarBooking(
			long userId, long calendarId, long[] childCalendarIds,
			long parentCalendarBookingId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startTime,
			long endTime, boolean allDay, String recurrence, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			startTime);
		java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
			endTime);

		if (allDay) {
			startTimeJCalendar = JCalendarUtil.toMidnightJCalendar(
				startTimeJCalendar);
			endTimeJCalendar = JCalendarUtil.toLastHourJCalendar(
				endTimeJCalendar);
		}

		if (firstReminder < secondReminder) {
			long originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		Date now = new Date();

		validate(titleMap, startTimeJCalendar, endTimeJCalendar);

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
		calendarBooking.setStartTime(startTimeJCalendar.getTimeInMillis());
		calendarBooking.setEndTime(endTimeJCalendar.getTimeInMillis());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setFirstReminderType(firstReminderType);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setSecondReminderType(secondReminderType);
		calendarBooking.setExpandoBridgeAttributes(serviceContext);

		int status = CalendarBookingWorkflowConstants.STATUS_PENDING;

		if (parentCalendarBookingId == 0) {
			status = CalendarBookingWorkflowConstants.STATUS_APPROVED;
		}

		calendarBooking.setStatus(status);

		calendarBooking.setStatusDate(serviceContext.getModifiedDate(now));

		calendarBookingPersistence.update(calendarBooking);

		addChildCalendarBookings(
			calendarBooking, childCalendarIds, serviceContext);

		// Asset

		updateAsset(
			userId, calendarBooking, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

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

		long endTime = now.getTime() + Time.MONTH;

		calendarBookings = RecurrenceUtil.expandCalendarBookings(
			calendarBookings, now.getTime(), endTime, 1);

		for (CalendarBooking calendarBooking : calendarBookings) {
			try {
				NotificationUtil.notifyCalendarBookingReminders(
					calendarBooking, now.getTime());
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

		// Subscriptions

		subscriptionLocalService.deleteSubscriptions(
			calendarBooking.getCompanyId(), CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		// Asset

		assetEntryLocalService.deleteEntry(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		// Message boards

		mbMessageLocalService.deleteDiscussionMessages(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		// Ratings

		ratingsStatsLocalService.deleteStats(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

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

	public void deleteCalendarBookingInstance(
			CalendarBooking calendarBooking, long startTime,
			boolean allFollowing)
		throws PortalException, SystemException {

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			startTime);

		Recurrence recurrenceObj = calendarBooking.getRecurrenceObj();

		if (allFollowing) {
			if (startTime == calendarBooking.getStartTime()) {
				deleteCalendarBooking(calendarBooking);

				return;
			}

			if (recurrenceObj.getCount() > 0) {
				recurrenceObj.setCount(0);
			}

			startTimeJCalendar.add(java.util.Calendar.DATE, -1);

			recurrenceObj.setUntilJCalendar(startTimeJCalendar);
		}
		else {
			recurrenceObj.addExceptionDate(startTimeJCalendar);
		}

		calendarBooking.setRecurrence(
			RecurrenceSerializer.serialize(recurrenceObj));

		calendarBookingPersistence.update(calendarBooking);
	}

	public void deleteCalendarBookingInstance(
			long calendarBookingId, long startTime, boolean allFollowing)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		deleteCalendarBookingInstance(calendarBooking, startTime, allFollowing);
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
			long calendarId, long startTime, long endTime)
		throws SystemException {

		return getCalendarBookings(
			calendarId, startTime, endTime, QueryUtil.ALL_POS);
	}

	public List<CalendarBooking> getCalendarBookings(
			long calendarId, long startTime, long endTime, int max)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CalendarBooking.class, getClassLoader());

		Property property = PropertyFactoryUtil.forName("calendarId");

		dynamicQuery.add(property.eq(calendarId));

		if (startTime >= 0) {
			Property propertyStartTime = PropertyFactoryUtil.forName(
				"startTime");

			dynamicQuery.add(propertyStartTime.gt(startTime));
		}

		if (endTime >= 0) {
			Property propertyEndTime = PropertyFactoryUtil.forName("endTime");

			dynamicQuery.add(propertyEndTime.lt(endTime));
		}

		if (max > 0) {
			dynamicQuery.setLimit(0, max);
		}

		return dynamicQuery(dynamicQuery);
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
			String keywords, long startTime, long endTime, boolean recurring,
			int[] statuses, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByKeywords(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, keywords, startTime, endTime,
				recurring, statuses, start, end, orderByComparator);

		if (recurring) {
			calendarBookings = RecurrenceUtil.expandCalendarBookings(
				calendarBookings, startTime, endTime);
		}

		return calendarBookings;
	}

	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startTime,
			long endTime, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingFinder.findByC_G_C_C_P_T_D_L_S_E_S(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, title, description, location,
				startTime, endTime, recurring, statuses, andOperator, start,
				end, orderByComparator);

		if (recurring) {
			calendarBookings = RecurrenceUtil.expandCalendarBookings(
				calendarBookings, startTime, endTime);
		}

		return calendarBookings;
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startTime, long endTime, int[] statuses)
		throws SystemException {

		return calendarBookingFinder.countByKeywords(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startTime, endTime, statuses);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startTime,
			long endTime, int[] statuses, boolean andOperator)
		throws SystemException {

		return calendarBookingFinder.countByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startTime,
			endTime, statuses, andOperator);
	}

	public void updateAsset(
			long userId, CalendarBooking calendarBooking,
			long[] assetCategoryIds, String[] assetTagNames,
			long[] assetLinkEntryIds)
		throws PortalException, SystemException {

		long assetGroupId = calendarBooking.getGroupId();

		CalendarResource calendarResource =
			calendarBooking.getCalendarResource();

		long classNameId = calendarResource.getClassNameId();
		long groupClassNameId = PortalUtil.getClassNameId(Group.class);

		if (classNameId == groupClassNameId) {
			Group group = GroupLocalServiceUtil.getGroup(
				calendarResource.getClassPK());

			assetGroupId = group.getGroupId();
		}

		boolean visible = false;

		if (calendarBooking.isApproved()) {
			visible = true;
		}

		String summary = HtmlUtil.extractText(
			StringUtil.shorten(calendarBooking.getDescription(), 500));

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, assetGroupId, calendarBooking.getCreateDate(),
			calendarBooking.getModifiedDate(), CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId(), calendarBooking.getUuid(),
			0, assetCategoryIds, assetTagNames, visible, null, null, null,
			ContentTypes.TEXT_HTML, calendarBooking.getTitle(),
			calendarBooking.getDescription(), summary, null, null, 0, 0, null,
			false);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			long[] childCalendarIds, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startTime,
			long endTime, boolean allDay, String recurrence, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar booking

		User user = userPersistence.findByPrimaryKey(userId);
		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			startTime);
		java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
			endTime);

		if (allDay) {
			startTimeJCalendar = JCalendarUtil.toMidnightJCalendar(
				startTimeJCalendar);
			endTimeJCalendar = JCalendarUtil.toLastHourJCalendar(
				endTimeJCalendar);
		}

		if (firstReminder < secondReminder) {
			long originalSecondReminder = secondReminder;

			secondReminder = firstReminder;
			firstReminder = originalSecondReminder;
		}

		validate(titleMap, startTimeJCalendar, endTimeJCalendar);

		calendarBooking.setCompanyId(user.getCompanyId());
		calendarBooking.setUserId(user.getUserId());
		calendarBooking.setUserName(user.getFullName());
		calendarBooking.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setTitleMap(titleMap);
		calendarBooking.setDescriptionMap(descriptionMap);
		calendarBooking.setLocation(location);
		calendarBooking.setStartTime(startTimeJCalendar.getTimeInMillis());
		calendarBooking.setEndTime(endTimeJCalendar.getTimeInMillis());
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setFirstReminderType(firstReminderType);
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setSecondReminderType(secondReminderType);
		calendarBooking.setExpandoBridgeAttributes(serviceContext);

		calendarBookingPersistence.update(calendarBooking);

		addChildCalendarBookings(
			calendarBooking, childCalendarIds, serviceContext);

		// Asset

		updateAsset(
			userId, calendarBooking, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

		// Workflow

		calendarBookingApprovalWorkflow.invokeTransition(
			userId, calendarBookingId,
			CalendarBookingWorkflowConstants.toLabel(status), serviceContext);

		return calendarBooking;
	}

	public CalendarBooking updateCalendarBooking(
			long userId, long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startTime, long endTime, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		long[] childCalendarIds = getChildCalendarIds(
			calendarBookingId, calendarId);

		return updateCalendarBooking(
			userId, calendarBookingId, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	public CalendarBooking updateCalendarBookingInstance(
			long userId, long calendarBookingId, long calendarId,
			long[] childCalendarIds, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startTime,
			long endTime, boolean allDay, String recurrence,
			boolean allFollowing, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		String oldRecurrence = calendarBooking.getRecurrence();

		deleteCalendarBookingInstance(calendarBooking, startTime, allFollowing);

		if (allFollowing) {
			Recurrence recurrenceObj = RecurrenceSerializer.deserialize(
				recurrence);

			if (oldRecurrence.equals(recurrence) &&
				(recurrenceObj.getCount() > 0)) {

				int index = RecurrenceUtil.getIndexOfInstance(
					recurrence, calendarBooking.getStartTime(), startTime);

				recurrenceObj.setCount(recurrenceObj.getCount() - index);

				recurrence = RecurrenceSerializer.serialize(recurrenceObj);
			}
		}
		else {
			recurrence = StringPool.BLANK;
		}

		return addCalendarBooking(
			userId, calendarId, childCalendarIds,
			CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
			titleMap, descriptionMap, location, startTime, endTime, allDay,
			recurrence, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	public CalendarBooking updateCalendarBookingInstance(
			long userId, long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startTime, long endTime, boolean allDay,
			String recurrence, boolean allFollowing, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		long[] childCalendarIds = getChildCalendarIds(
			calendarBookingId, calendarId);

		return updateCalendarBookingInstance(
			userId, calendarBookingId, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
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

		calendarBookingPersistence.update(calendarBooking);

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
				calendarBooking.getLocation(), calendarBooking.getStartTime(),
				calendarBooking.getEndTime(), calendarBooking.getAllDay(),
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

	protected long[] getChildCalendarIds(
			long calendarBookingId, long calendarId)
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

		return childCalendarIds;
	}

	protected void validate(
			Map<Locale, String> titleMap, java.util.Calendar startTimeJCalendar,
			java.util.Calendar endTimeJCalendar)
		throws PortalException {

		if (Validator.isNull(titleMap) || titleMap.isEmpty()) {
			throw new CalendarBookingTitleException();
		}

		if (startTimeJCalendar.after(endTimeJCalendar)) {
			throw new CalendarBookingDurationException();
		}
	}

	@BeanReference(type = CalendarBookingApprovalWorkflow.class)
	protected CalendarBookingApprovalWorkflow calendarBookingApprovalWorkflow;

}