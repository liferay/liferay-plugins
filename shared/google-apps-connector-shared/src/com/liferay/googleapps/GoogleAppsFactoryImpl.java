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

package com.liferay.googleapps;

import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class GoogleAppsFactoryImpl implements GoogleAppsFactory {

	@Override
	public GEmailSettingsManager getGEmailSettingsManager(long companyId) {
		return getGoogleApps(companyId).getGEmailSettingsManager();
	}

	@Override
	public GGroupManager getGGroupManager(long companyId) {
		return getGoogleApps(companyId).getGGroupManager();
	}

	@Override
	public GNicknameManager getGNicknameManager(long companyId) {
		return getGoogleApps(companyId).getGNicknameManager();
	}

	@Override
	public GUserManager getGUserManager(long companyId) {
		return getGoogleApps(companyId).getGUserManager();
	}

	protected GoogleApps getGoogleApps(long companyId) {
		GoogleApps googleApps = _googleAppsMap.get(companyId);

		if (googleApps == null) {
			googleApps = new GoogleApps(companyId);

			_googleAppsMap.put(companyId, googleApps);
		}

		return googleApps;
	}

	private static Map<Long, GoogleApps> _googleAppsMap =
		new ConcurrentHashMap<Long, GoogleApps>();

}