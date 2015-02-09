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

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.sync.shared.util.PortletPropsKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

/**
 * @author Shinn Lok
 */
public class AdminPortlet extends MVCPortlet {

	public void configurePermissions(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		long[] groupIds = ParamUtil.getLongValues(actionRequest, "groupIds");

		String permissions = ParamUtil.getString(actionRequest, "permissions");

		for (long groupId : groupIds) {
			Group group = GroupLocalServiceUtil.fetchGroup(groupId);

			UnicodeProperties typeSettingsProperties =
				group.getTypeSettingsProperties();

			typeSettingsProperties.setProperty("permissions", permissions);

			group.setTypeSettingsProperties(typeSettingsProperties);

			GroupLocalServiceUtil.updateGroup(group);
		}

		sendRedirect(actionRequest, actionResponse);
	}

	public void configureSite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		String enableSyncSites = ParamUtil.getString(
			actionRequest, "enableSyncSites");

		long[] groupIds = ParamUtil.getLongValues(actionRequest, "groupIds");

		for (long groupId : groupIds) {
			Group group = GroupLocalServiceUtil.fetchGroup(groupId);

			UnicodeProperties typeSettingsProperties =
				group.getTypeSettingsProperties();

			typeSettingsProperties.setProperty("syncEnabled", enableSyncSites);

			group.setTypeSettingsProperties(typeSettingsProperties);

			GroupLocalServiceUtil.updateGroup(group);
		}

		sendRedirect(actionRequest, actionResponse);
	}

	public void updatePreferences(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			PortletPreferences portletPreferences =
				PrefsPropsUtil.getPreferences(
					CompanyThreadLocal.getCompanyId());

			int maxConnections = ParamUtil.getInteger(
				actionRequest, "maxConnections");

			portletPreferences.setValue(
				PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS,
				String.valueOf(maxConnections));

			int pollInterval = ParamUtil.getInteger(
				actionRequest, "pollInterval");

			portletPreferences.setValue(
				PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL,
				String.valueOf(pollInterval));

			boolean enabled = ParamUtil.getBoolean(actionRequest, "enabled");

			portletPreferences.setValue(
				PortletPropsKeys.SYNC_SERVICES_ENABLED,
				String.valueOf(enabled));

			portletPreferences.store();

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

}