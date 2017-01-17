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

package com.liferay.sync.admin.portlet;

import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.sync.admin.exception.OAuthPortletUndeployedException;
import com.liferay.sync.service.SyncDeviceLocalServiceUtil;
import com.liferay.sync.service.SyncPreferencesLocalServiceUtil;
import com.liferay.sync.shared.util.PortletPropsKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

/**
 * @author Shinn Lok
 * @author Jonathan McCann
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteDevice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long syncDeviceId = ParamUtil.getLong(actionRequest, "syncDeviceId");

		SyncDeviceLocalServiceUtil.deleteSyncDevice(syncDeviceId);
	}

	public void updateDevice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long syncDeviceId = ParamUtil.getLong(actionRequest, "syncDeviceId");

		int status = ParamUtil.getInteger(actionRequest, "status");

		SyncDeviceLocalServiceUtil.updateStatus(syncDeviceId, status);
	}

	public void updatePreferences(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			doUpdatePreferences(actionRequest, actionResponse);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateSites(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String enabled = ParamUtil.getString(actionRequest, "enabled");
		String permissions = ParamUtil.getString(actionRequest, "permissions");

		long[] groupIds = ParamUtil.getLongValues(actionRequest, "groupIds");

		for (long groupId : groupIds) {
			Group group = GroupLocalServiceUtil.fetchGroup(groupId);

			UnicodeProperties typeSettingsProperties =
				group.getTypeSettingsProperties();

			if (Validator.isNotNull(enabled)) {
				typeSettingsProperties.setProperty("syncEnabled", enabled);
			}

			if (Validator.isNotNull(permissions)) {
				typeSettingsProperties.setProperty(
					"syncSiteMemberFilePermissions", permissions);
			}

			group.setTypeSettingsProperties(typeSettingsProperties);

			GroupLocalServiceUtil.updateGroup(group);
		}
	}

	protected void doUpdatePreferences(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences(
			CompanyThreadLocal.getCompanyId());

		boolean allowUserPersonalSites = ParamUtil.getBoolean(
			actionRequest, "allowUserPersonalSites");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_ALLOW_USER_PERSONAL_SITES,
			String.valueOf(allowUserPersonalSites));

		boolean enabled = ParamUtil.getBoolean(actionRequest, "enabled");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_SERVICES_ENABLED, String.valueOf(enabled));

		boolean forceSecurityMode = ParamUtil.getBoolean(
			actionRequest, "forceSecurityMode");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_CLIENT_FORCE_SECURITY_MODE,
			String.valueOf(forceSecurityMode));

		boolean lanEnabled = ParamUtil.getBoolean(actionRequest, "lanEnabled");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_LAN_ENABLED, String.valueOf(lanEnabled));

		int maxConnections = ParamUtil.getInteger(
			actionRequest, "maxConnections");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS,
			String.valueOf(maxConnections));

		int maxDownloadRate = ParamUtil.getInteger(
			actionRequest, "maxDownloadRate");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_CLIENT_MAX_DOWNLOAD_RATE,
			String.valueOf(maxDownloadRate));

		int maxUploadRate = ParamUtil.getInteger(
			actionRequest, "maxUploadRate");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_CLIENT_MAX_UPLOAD_RATE,
			String.valueOf(maxUploadRate));

		boolean oAuthEnabled = ParamUtil.getBoolean(
			actionRequest, "oAuthEnabled");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_OAUTH_ENABLED, String.valueOf(oAuthEnabled));

		int pollInterval = ParamUtil.getInteger(actionRequest, "pollInterval");

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL,
			String.valueOf(pollInterval));

		portletPreferences.setValue(
			PortletPropsKeys.SYNC_CONTEXT_MODIFIED_TIME,
			String.valueOf(System.currentTimeMillis()));

		portletPreferences.store();

		if (lanEnabled) {
			SyncPreferencesLocalServiceUtil.enableLanSync(
				CompanyThreadLocal.getCompanyId());
		}

		if (oAuthEnabled) {
			if (!DeployManagerUtil.isDeployed("oauth-portlet")) {
				SessionErrors.add(
					actionRequest, OAuthPortletUndeployedException.class);

				return;
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			SyncPreferencesLocalServiceUtil.enableOAuth(
				CompanyThreadLocal.getCompanyId(), serviceContext);
		}
	}

}