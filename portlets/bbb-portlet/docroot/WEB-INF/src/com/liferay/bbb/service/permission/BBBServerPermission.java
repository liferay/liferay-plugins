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

package com.liferay.bbb.service.permission;

import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.service.BBBServerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Shinn Lok
 */
public class BBBServerPermission {

	public static void check(
			PermissionChecker permissionChecker, BBBServer bbbServer,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, bbbServer, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long bbbServerId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, bbbServerId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, BBBServer bbbServer,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				bbbServer.getCompanyId(), BBBServer.class.getName(),
				bbbServer.getBbbServerId(), bbbServer.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			bbbServer.getGroupId(), BBBServer.class.getName(),
			bbbServer.getBbbServerId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long bbbServerId,
			String actionId)
		throws PortalException, SystemException {

		BBBServer bbbServer = BBBServerLocalServiceUtil.getBBBServer(
			bbbServerId);

		return contains(permissionChecker, bbbServer, actionId);
	}

}