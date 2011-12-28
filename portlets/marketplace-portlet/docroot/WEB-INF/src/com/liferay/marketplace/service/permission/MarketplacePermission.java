/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.marketplace.service.permission;

import com.liferay.marketplace.util.MarketplaceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class MarketplacePermission {

	public static void check(PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		if (!contains(permissionChecker)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		if (!permissionChecker.isOmniadmin()) {
			return false;
		}

		User user = UserLocalServiceUtil.getUserById(
			permissionChecker.getUserId());

		if (MarketplaceUtil.isMarketplaceAdmin(user)) {
			return true;
		}

		return false;
	}

}