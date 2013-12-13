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

package com.liferay.util.bridges.alloy;

import com.liferay.portal.NoSuchResourceActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * @author Ethan Bustad
 */
public class AlloyPermission {

	public static void check(
			PermissionChecker permissionChecker, long groupId, String portletId,
			String controller, String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, groupId, portletId, controller, actionId)) {

			throw new PrincipalException();
		}
	}

	public static void check(
			ThemeDisplay themeDisplay, String controller, String actionId)
		throws PortalException {

		if (!contains(themeDisplay, controller, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String portletId,
		String controller, String actionId) {

		actionId =
			StringUtil.toUpperCase(actionId) + StringPool.UNDERLINE +
				StringUtil.toUpperCase(controller);

		try {
			ResourceActionsUtil.checkAction(portletId, actionId);
		}
		catch (NoSuchResourceActionException e) {
			return true;
		}

		return permissionChecker.hasPermission(
			groupId, portletId, groupId, actionId);
	}

	public static boolean contains(
		ThemeDisplay themeDisplay, String controller, String actionId) {

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			portletDisplay.getRootPortletId(), controller, actionId);
	}

}