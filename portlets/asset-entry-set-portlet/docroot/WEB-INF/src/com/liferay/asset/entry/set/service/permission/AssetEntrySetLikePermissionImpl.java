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

import com.liferay.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.asset.entry.set.service.AssetEntrySetLikeLocalServiceUtil;
import com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetLikePermissionImpl
	implements AssetEntrySetLikePermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, long assetEntrySetId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, assetEntrySetId, actionId)) {
			throw new PrincipalException();
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long assetEntrySetId,
			String actionId)
		throws PortalException, SystemException {

		AssetEntrySetLikePK assetEntrySetLikePK = new AssetEntrySetLikePK(
			assetEntrySetId, _USER_CLASS_NAME_ID,
			permissionChecker.getUserId());

		AssetEntrySetLike assetEntrySetLike =
			AssetEntrySetLikeLocalServiceUtil.fetchAssetEntrySetLike(
				assetEntrySetLikePK);

		if (assetEntrySetLike != null) {
			return true;
		}

		return false;
	}

	private static final long _USER_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(User.class);

}