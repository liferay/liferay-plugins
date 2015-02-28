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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetLikePermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long assetEntrySetId,
			String actionId)
		throws PortalException, SystemException {

		getAssetEntrySetLikePermission().check(
			permissionChecker, assetEntrySetId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long assetEntrySetId,
			String actionId)
		throws PortalException, SystemException {

		return getAssetEntrySetLikePermission().contains(
			permissionChecker, assetEntrySetId, actionId);
	}

	public static AssetEntrySetLikePermission getAssetEntrySetLikePermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			AssetEntrySetPermissionUtil.class);

		return _assetEntrySetLikePermission;
	}

	public void setAssetEntrySetLikePermission(
		AssetEntrySetLikePermission assetEntrySetLikePermission) {

		PortalRuntimePermission.checkGetBeanProperty(getClass());

		_assetEntrySetLikePermission = assetEntrySetLikePermission;
	}

	private static AssetEntrySetLikePermission _assetEntrySetLikePermission;

}