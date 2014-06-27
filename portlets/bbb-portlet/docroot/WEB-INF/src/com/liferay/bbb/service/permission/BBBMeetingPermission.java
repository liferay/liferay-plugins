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

package com.liferay.bbb.service.permission;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Shinn Lok
 */
public class BBBMeetingPermission {

	public static void check(
			PermissionChecker permissionChecker, BBBMeeting bbbMeeting,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, bbbMeeting, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long bbbMeetingId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, bbbMeetingId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, BBBMeeting bbbMeeting,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				bbbMeeting.getCompanyId(), BBBMeeting.class.getName(),
				bbbMeeting.getBbbMeetingId(), bbbMeeting.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			bbbMeeting.getGroupId(), BBBMeeting.class.getName(),
			bbbMeeting.getBbbServerId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long bbbMeetingId,
			String actionId)
		throws PortalException {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		return contains(permissionChecker, bbbMeeting, actionId);
	}

}