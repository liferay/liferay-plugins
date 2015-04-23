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

package com.liferay.sync.service.impl;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.model.OAuthApplicationConstants;
import com.liferay.oauth.service.OAuthApplicationLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.sync.service.base.SyncPreferencesLocalServiceBaseImpl;
import com.liferay.sync.shared.util.PortletPropsKeys;
import com.liferay.util.portlet.PortletProps;

import java.io.InputStream;

import java.util.Map;
import java.util.Properties;

import javax.portlet.PortletPreferences;

/**
 * @author Dennis Ju
 */
public class SyncPreferencesLocalServiceImpl
	extends SyncPreferencesLocalServiceBaseImpl {

	@Override
	public OAuthApplication enableOAuth(
			long companyId, ServiceContext serviceContext)
		throws PortalException {

		long oAuthApplicationId = PrefsPropsUtil.getLong(
			companyId, PortletPropsKeys.SYNC_OAUTH_APPLICATION_ID, 0);

		OAuthApplication oAuthApplication =
			OAuthApplicationLocalServiceUtil.fetchOAuthApplication(
				oAuthApplicationId);

		if (oAuthApplication != null) {
			return oAuthApplication;
		}

		oAuthApplication = OAuthApplicationLocalServiceUtil.addOAuthApplication(
			serviceContext.getUserId(), "Liferay Sync", StringPool.BLANK,
			OAuthApplicationConstants.ACCESS_WRITE, true, "http://liferay-sync",
			"http://liferay-sync", serviceContext);

		ClassLoader classLoader = getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			"/resources/images/logo.png");

		OAuthApplicationLocalServiceUtil.updateLogo(
			oAuthApplication.getOAuthApplicationId(), inputStream);

		return oAuthApplication;
	}

	@Override
	public PortletPreferences getPortletPreferences(long companyId)
		throws PortalException {

		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences(
			companyId);

		Properties properties = PortletProps.getProperties();

		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String key = String.valueOf(entry.getKey());
			String value = String.valueOf(entry.getValue());

			if (portletPreferences.getValue(key, null) != null) {
				continue;
			}

			try {
				portletPreferences.setValue(key, value);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return portletPreferences;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncPreferencesLocalServiceImpl.class);

}