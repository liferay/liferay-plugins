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

package com.liferay.calendar.workflow;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarEvent;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarResourcePermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;

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
			PermissionChecker permissionChecker, long[] calendarBookingIds)
		throws PortalException, SystemException {

		Map<Long, List<String>> actionNames =
			new LinkedHashMap<Long, List<String>>();

		for (long calendarBookingId : calendarBookingIds) {
			CalendarBooking calendarBooking =
				CalendarBookingLocalServiceUtil.getCalendarBooking(
					calendarBookingId);

			List<String> transitions = new ArrayList<String>();

			if (CalendarResourcePermission.contains(
					permissionChecker, calendarBooking.getCalendarResourceId(),
					ActionKeys.UPDATE_BOOKINGS)) {

				if (calendarBooking.getStatus() !=
						CalendarBookingWorkflowConstants.STATUS_APPROVED) {

					transitions.add("accept");
				}

				transitions.add("decline");
			}

			actionNames.put(calendarBookingId, transitions);
		}

		return actionNames;
	}

	public void invokeTransition(
			long userId, long calendarBookingId, String transitionName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		int status = CalendarBookingWorkflowConstants.toStatus(transitionName);

		if (status == CalendarBookingWorkflowConstants.STATUS_PENDING) {
			CalendarBooking calendarBooking =
				CalendarBookingLocalServiceUtil.getCalendarBooking(
					calendarBookingId);

			if (isAutoApproveCalendarBooking(userId, calendarBooking)) {
				CalendarBookingLocalServiceUtil.updateStatus(
					userId, calendarBookingId,
					CalendarBookingWorkflowConstants.STATUS_APPROVED,
					serviceContext);
			}
			else {
				CalendarBookingLocalServiceUtil.updateStatus(
					userId, calendarBooking.getCalendarBookingId(),
					CalendarBookingWorkflowConstants.STATUS_PENDING,
					serviceContext);
			}
		}
		else {
			CalendarBookingLocalServiceUtil.updateStatus(
				userId, calendarBookingId, status, serviceContext);
		}
	}

	public void startWorkflow(
			long userId, long calendarBookingId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		invokeTransition(
			userId, calendarBookingId,
			CalendarBookingWorkflowConstants.LABEL_PENDING, serviceContext);
	}

	protected boolean isAutoApproveCalendarBooking(
			long userId, CalendarBooking calendarBooking)
		throws PortalException, SystemException {

		if (calendarBooking.getStatus() ==
				CalendarBookingWorkflowConstants.STATUS_DENIED) {

			return false;
		}

		CalendarEvent calendarEvent = calendarBooking.getCalendarEvent();

		if (userId != calendarEvent.getUserId()) {
			return false;
		}

		CalendarResource calendarResource =
			calendarBooking.getCalendarResource();

		if (userId != calendarResource.getUserId()) {
			return false;
		}

		return true;
	}

}