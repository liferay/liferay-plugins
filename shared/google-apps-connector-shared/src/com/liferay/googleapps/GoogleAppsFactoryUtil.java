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

/**
 * @author Brian Wing Shun Chan
 */
public class GoogleAppsFactoryUtil {

	public static GEmailSettingsManager getGEmailSettingsManager(
		long companyId) {

		return getGoogleAppsFactory().getGEmailSettingsManager(companyId);
	}

	public static GGroupManager getGGroupManager(long companyId) {
		return getGoogleAppsFactory().getGGroupManager(companyId);
	}

	public static GNicknameManager getGNicknameManager(long companyId) {
		return getGoogleAppsFactory().getGNicknameManager(companyId);
	}

	public static GoogleAppsFactory getGoogleAppsFactory() {
		return _googleAppsFactory;
	}

	public static GUserManager getGUserManager(long companyId) {
		return getGoogleAppsFactory().getGUserManager(companyId);
	}

	public void setGoogleAppsFactory(GoogleAppsFactory googleAppsFactory) {
		_googleAppsFactory = googleAppsFactory;
	}

	private static GoogleAppsFactory _googleAppsFactory =
		new GoogleAppsFactoryImpl();

}