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

package com.liferay.alloy.mvc;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.NoSuchResourceActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Ethan Bustad
 */
public class AlloyPermission {

	public static void check(
			PermissionChecker permissionChecker, long groupId, String name,
			long primKey, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, name, primKey, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			ThemeDisplay themeDisplay, BaseModel<?> baseModel, String action)
		throws PortalException {

		if (!contains(themeDisplay, baseModel, action)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			ThemeDisplay themeDisplay, String controller, String action)
		throws PortalException {

		if (!contains(themeDisplay, controller, action)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String name,
		long primKey, String actionId) {

		return contains(permissionChecker, groupId, name, primKey, actionId, 0);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String name,
		long primKey, String actionId, long ownerId) {

		try {
			ResourceActionsUtil.checkAction(name, actionId);
		}
		catch (NoSuchResourceActionException nsrae) {
			return true;
		}

		if (name.indexOf(CharPool.PERIOD) != -1) {
			if (ownerId <= 0) {
				ownerId = getOwnerId(name, primKey);
			}

			if (permissionChecker.hasOwnerPermission(
					permissionChecker.getCompanyId(), name, primKey, ownerId,
					actionId)) {

				return true;
			}
		}

		return permissionChecker.hasPermission(
			groupId, name, primKey, actionId);
	}

	public static boolean contains(
		ThemeDisplay themeDisplay, BaseModel<?> baseModel, String action) {

		return contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			BeanPropertiesUtil.getString(baseModel, "modelClassName"),
			(Long)baseModel.getPrimaryKeyObj(), StringUtil.toUpperCase(action));
	}

	public static boolean contains(
		ThemeDisplay themeDisplay, String controller, String action) {

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String actionId = formatActionId(controller, action);

		return contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			portletDisplay.getRootPortletId(), themeDisplay.getScopeGroupId(),
			actionId);
	}

	protected static String formatActionId(String controller, String action) {
		StringBuilder sb = new StringBuilder(StringUtil.toUpperCase(action));

		for (int i = 0; i < action.length(); i++) {
			char c = action.charAt(i);

			if (Character.isUpperCase(c) && (i > 0)) {
				int delta = sb.length() - action.length();

				sb.insert(i + delta, CharPool.UNDERLINE);
			}
		}

		sb.append(StringPool.POUND);
		sb.append(StringUtil.toUpperCase(controller));

		return sb.toString();
	}

	protected static long getOwnerId(String className, long classPK) {
		BaseModel<?> baseModel = null;

		try {
			AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
				className);

			baseModel = alloyServiceInvoker.fetchModel(classPK);
		}
		catch (Exception e) {
		}

		return BeanPropertiesUtil.getLongSilent(baseModel, "userId");
	}

}