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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public interface CalendarBookingApprovalWorkflow {

	public Map<Long, List<String>> getActionNames(
			PermissionChecker permissionChecker, long[] calendarBookingIds)
		throws PortalException, SystemException;

	public void invokeTransition(
			long userId, long calendarBookingId, String transitionName,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	public void startWorkflow(
			long userId, long calendarBookingId, ServiceContext serviceContext)
		throws PortalException, SystemException;

}