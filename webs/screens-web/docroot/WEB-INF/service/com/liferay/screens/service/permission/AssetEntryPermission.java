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

package com.liferay.screens.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

/**
 * @author Samuel Kong
 */
public class AssetEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, AssetEntry entry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, entry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, entryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, String className, long classPK,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, className, classPK, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, AssetEntry entry,
			String actionId)
		throws PortalException {

		String className = PortalUtil.getClassName(entry.getClassNameId());

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		try {
			return assetRendererFactory.hasPermission(
				permissionChecker, entry.getClassPK(), actionId);
		}
		catch (Exception e) {
			throw new PrincipalException(e);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException, SystemException {

		AssetEntry entry = AssetEntryLocalServiceUtil.getEntry(entryId);

		return contains(permissionChecker, entry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, String className, long classPK,
			String actionId)
		throws PortalException, SystemException {

		AssetEntry entry = AssetEntryLocalServiceUtil.getEntry(
			className, classPK);

		return contains(permissionChecker, entry, actionId);
	}

}