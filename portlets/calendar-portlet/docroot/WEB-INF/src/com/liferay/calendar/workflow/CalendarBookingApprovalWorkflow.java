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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public interface CalendarBookingApprovalWorkflow {
	public void startWorkflow(long userId, long bookingId)
			throws PortalException, SystemException;

	public Map<Long, List<String>> getActionNames(
			PermissionChecker permissionChecker, Long[] bookingIds)
		throws PortalException, SystemException;

	public Map<Long, List<String>> getActionNames(
			PermissionChecker permissionChecker, String assetType,
			Long[] assetPrimaryKeys, boolean completed)
		throws PortalException, SystemException;

	public void invokeTransition(
			long userId, Long bookingId, String transitionName)
		throws PortalException, SystemException;

	public void invokeTransition(
			long userId, String assetType, Long assetPrimaryKey,
			String transitionName, String transitionDesc, boolean completed)
		throws PortalException, SystemException;
}
