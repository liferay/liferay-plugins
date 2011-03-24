/*
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.workflow;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarEvent;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarEventLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarActionKeys;
import com.liferay.calendar.service.permission.CalendarResourcePermission;
import com.liferay.calendar.util.CalendarConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class CalendarBookingApprovalWorkflowImpl 
	implements CalendarBookingApprovalWorkflow {
	
	public Map<Long, List<String>> getActionNames(
			PermissionChecker permissionChecker, Long[] bookingIds)
		throws PortalException, SystemException {

		Map<Long, List<String>> map = new LinkedHashMap<Long, List<String>>();

		for (long bookingId : bookingIds) {
			CalendarBooking booking =
				CalendarBookingLocalServiceUtil.getCalendarBooking(bookingId);

			List<String> transitions = new ArrayList<String>();

			if (CalendarResourcePermission.contains(
					permissionChecker, booking.getCalendarResourceId(),
					CalendarActionKeys.ACCEPT_BOOKING_EVENT)) {

				if (booking.getStatus() !=
						CalendarBookingWorkflowConstants.STATUS_APPROVED) {

					transitions.add(CalendarConstants.ACCEPT);
				}

				transitions.add(CalendarConstants.DECLINE);
			}

			map.put(bookingId, transitions);
		}

		return map;
	}

	public Map<Long, List<String>> getActionNames(
			PermissionChecker permissionChecker, String assetType,
			Long[] assetPrimaryKeys, boolean completed)
		throws PortalException, SystemException {

		return getActionNames(permissionChecker, assetPrimaryKeys);
	}

	public void invokeTransition(
			long userId, Long resourceBookingId, String transitionName)
		throws PortalException, SystemException {

		int statusId = CalendarBookingWorkflowConstants.toStatus(
			transitionName);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(
				resourceBookingId);

		CalendarEvent event = CalendarEventLocalServiceUtil.getCalendarEvent(
			calendarBooking.getCalendarEventId());

		if (statusId == CalendarBookingWorkflowConstants.STATUS_PENDING) {
			if (checkAutoApprovePendingEventBooking(
					userId, event, calendarBooking)) {

				CalendarBookingLocalServiceUtil.updateStatus(
					userId, resourceBookingId,
					CalendarBookingWorkflowConstants.STATUS_APPROVED);
			}
			else {

				CalendarBookingLocalServiceUtil.updateStatus(
					userId, calendarBooking.getCalendarBookingId(),
					CalendarBookingWorkflowConstants.STATUS_PENDING);
			}
		}
		else {
			CalendarBookingLocalServiceUtil.updateStatus(
				userId, calendarBooking.getCalendarBookingId(), statusId);
		}
	}

	public void invokeTransition(
			long userId, String assetType, Long assetPrimaryKey,
			String transitionName, String transitionDesc, boolean completed)
		throws PortalException, SystemException{

		invokeTransition(userId, assetPrimaryKey, transitionName);
	}

	public void startWorkflow(long userId, long bookingId)
		throws PortalException, SystemException {

		invokeTransition(
			userId, bookingId, CalendarBookingWorkflowConstants.LABEL_PENDING);
	}

	protected boolean checkAutoApprovePendingEventBooking(
			long userId, CalendarEvent calendarEvent,
			CalendarBooking calendarBooking)
		throws PortalException, SystemException {

		// Auto approve pending events when userId is the
		// calendarEvent owner userId or the calendarResource ownerId
		CalendarResource calendarResource =
			calendarBooking.getCalendarResource();

		return ((userId == calendarEvent.getUserId()) &&
				(userId == calendarResource.getUserId()) &&
				(calendarBooking.getStatus() !=
					CalendarBookingWorkflowConstants.STATUS_DENIED));
	}
}
