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

import com.liferay.meeting.webex.model.WebExAccount;
import com.liferay.meeting.webex.service.WebExAccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Anant Singh
 */
public class WebExAccountPermission {

	public static void check(
			PermissionChecker permissionChecker, long webExAccountId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, webExAccountId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, WebExAccount webExAccount,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, webExAccount, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long webExAccountId,
			String actionId)
		throws PortalException {

		WebExAccount webExAccount =
			WebExAccountLocalServiceUtil.getWebExAccount(webExAccountId);

		return contains(permissionChecker, webExAccount, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, WebExAccount webExAccount,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				webExAccount.getCompanyId(), WebExAccount.class.getName(),
				webExAccount.getWebExAccountId(), webExAccount.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			webExAccount.getCompanyId(), WebExAccount.class.getName(),
			webExAccount.getWebExAccountId(), actionId);
	}

}