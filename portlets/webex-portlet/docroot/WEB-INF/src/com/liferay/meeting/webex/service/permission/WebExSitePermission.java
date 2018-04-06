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

package com.liferay.meeting.webex.service.permission;

import com.liferay.meeting.webex.model.WebExSite;
import com.liferay.meeting.webex.service.WebExSiteLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Anant Singh
 */
public class WebExSitePermission {

	public static void check(
			PermissionChecker permissionChecker, long webExSiteId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, webExSiteId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, WebExSite webExSite,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, webExSite, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long webExSiteId,
			String actionId)
		throws PortalException {

		WebExSite webExSite = WebExSiteLocalServiceUtil.getWebExSite(
			webExSiteId);

		return contains(permissionChecker, webExSite, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, WebExSite webExSite,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				webExSite.getCompanyId(), WebExSite.class.getName(),
				webExSite.getWebExSiteId(), webExSite.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			webExSite.getGroupId(), WebExSite.class.getName(),
			webExSite.getWebExSiteId(), actionId);
	}

}