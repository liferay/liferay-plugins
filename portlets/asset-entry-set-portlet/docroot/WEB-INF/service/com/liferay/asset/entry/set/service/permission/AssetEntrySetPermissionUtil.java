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

package com.liferay.asset.entry.set.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long classNameId, long classPK,
			String actionId)
		throws PortalException {

		_assetEntrySetPermission.check(
			permissionChecker, classNameId, classPK, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long classNameId, long classPK,
		String actionId) {

		return _assetEntrySetPermission.contains(
			permissionChecker, classNameId, classPK, actionId);
	}

	public void setAssetEntrySetPermission(
		AssetEntrySetPermission assetEntrySetPermission) {

		_assetEntrySetPermission = assetEntrySetPermission;
	}

	private static AssetEntrySetPermission _assetEntrySetPermission;

}