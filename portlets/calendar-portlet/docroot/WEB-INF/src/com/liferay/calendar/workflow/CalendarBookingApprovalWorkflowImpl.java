/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 * @author Eduardo Lundgren
 */
public class CalendarBookingApprovalWorkflowImpl
	implements CalendarBookingApprovalWorkflow {

	@Override
	public Map<Long, List<String>> getActionNames(
			PermissionChecker permissionChecker, long[] calendarBookingIds)
		throws PortalException {

		Map<Long, List<String>> actionNames =
			new LinkedHashMap<Long, List<String>>();

		for (long calendarBookingId : calendarBookingIds) {
			CalendarBooking calendarBooking =
				CalendarBookingLocalServiceUtil.getCalendarBooking(
					calendarBookingId);

			List<String> transitions = new ArrayList<String>();

			if (CalendarPermission.contains(
					permissionChecker, calendarBooking.getCalendarId(),
					ActionKeys.MANAGE_BOOKINGS)) {

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

	@Override
	public void invokeTransition(
			long userId, CalendarBooking calendarBooking, int status,
			ServiceContext serviceContext)
		throws PortalException {

		if (status == CalendarBookingWorkflowConstants.STATUS_PENDING) {
			if (isAutoApproveCalendarBooking(userId, calendarBooking)) {
				status = CalendarBookingWorkflowConstants.STATUS_APPROVED;
			}
			else {
				status = CalendarBookingWorkflowConstants.STATUS_PENDING;
			}
		}

		CalendarBookingLocalServiceUtil.updateStatus(
			userId, calendarBooking, status, serviceContext);
	}

	@Override
	public void startWorkflow(
			long userId, CalendarBooking calendarBooking,
			ServiceContext serviceContext)
		throws PortalException {

		invokeTransition(
			userId, calendarBooking,
			CalendarBookingWorkflowConstants.STATUS_PENDING, serviceContext);
	}

	protected boolean isAutoApproveCalendarBooking(
			long userId, CalendarBooking calendarBooking)
		throws PortalException {

		if (calendarBooking.getStatus() ==
				CalendarBookingWorkflowConstants.STATUS_DENIED) {

			return false;
		}

		if (userId != calendarBooking.getUserId()) {
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