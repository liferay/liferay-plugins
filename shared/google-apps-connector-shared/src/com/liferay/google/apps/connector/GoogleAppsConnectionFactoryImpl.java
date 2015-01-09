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

package com.liferay.google.apps.connector;

import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class GoogleAppsConnectionFactoryImpl
	implements GoogleAppsConnectionFactory {

	@Override
	public GEmailSettingsManager getGEmailSettingsManager(long companyId) {
		return getGoogleAppsConnection(companyId).getGEmailSettingsManager();
	}

	@Override
	public GGroupManager getGGroupManager(long companyId) {
		return getGoogleAppsConnection(companyId).getGGroupManager();
	}

	@Override
	public GNicknameManager getGNicknameManager(long companyId) {
		return getGoogleAppsConnection(companyId).getGNicknameManager();
	}

	@Override
	public GUserManager getGUserManager(long companyId) {
		return getGoogleAppsConnection(companyId).getGUserManager();
	}

	protected GoogleAppsConnection getGoogleAppsConnection(long companyId) {
		GoogleAppsConnection googleApps = _googleAppsConnections.get(companyId);

		if (googleApps == null) {
			googleApps = new GoogleAppsConnection(companyId);

			_googleAppsConnections.put(companyId, googleApps);
		}

		return googleApps;
	}

	private static Map<Long, GoogleAppsConnection> _googleAppsConnections =
		new ConcurrentHashMap<>();

}