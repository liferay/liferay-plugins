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

package com.liferay.marketplace.service.impl;

import com.liferay.marketplace.model.App;
import com.liferay.marketplace.service.base.AppServiceBaseImpl;
import com.liferay.marketplace.service.permission.MarketplacePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.InputStream;

/**
 * @author Ryan Park
 */
public class AppServiceImpl extends AppServiceBaseImpl {

	public App addApp(long remoteAppId, String version, InputStream inputStream)
		throws PortalException, SystemException {

		MarketplacePermission.check(getPermissionChecker());

		return appLocalService.addApp(
			getUserId(), remoteAppId, version, inputStream);
	}

	public App deleteApp(long appId) throws PortalException, SystemException {
		MarketplacePermission.check(getPermissionChecker());

		return appLocalService.deleteApp(appId);
	}

	public void installApp(long remoteAppId)
		throws PortalException, SystemException {

		MarketplacePermission.check(getPermissionChecker());

		appLocalService.installApp(remoteAppId);
	}

	public void uninstallApp(long remoteAppId)
		throws PortalException, SystemException {

		MarketplacePermission.check(getPermissionChecker());

		appLocalService.uninstallApp(remoteAppId);
	}

	public App updateApp(long appId, String version, InputStream inputStream)
		throws PortalException, SystemException {

		MarketplacePermission.check(getPermissionChecker());

		return appLocalService.updateApp(appId, version, inputStream);
	}

}