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

package com.liferay.calendar.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.SessionClicks;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("user-settings")) {
			updateUserSettings(actionRequest, actionResponse);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void updateUserSettings(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		int defaultDuration = ParamUtil.getInteger(
			actionRequest, "defaultDuration");
		String defaultView = ParamUtil.getString(actionRequest, "defaultView");
		boolean isoTimeFormat = ParamUtil.getBoolean(
			actionRequest, "isoTimeFormat");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");
		boolean usePortalTimeZone = ParamUtil.getBoolean(
			actionRequest, "usePortalTimeZone");
		int weekStartsOn = ParamUtil.getInteger(actionRequest, "weekStartsOn");

		portletPreferences.setValue(
			"defaultDuration", String.valueOf(defaultDuration));
		portletPreferences.setValue("defaultView", defaultView);
		portletPreferences.setValue(
			"isoTimeFormat", String.valueOf(isoTimeFormat));
		portletPreferences.setValue("timeZoneId", timeZoneId);
		portletPreferences.setValue(
			"usePortalTimeZone", String.valueOf(usePortalTimeZone));
		portletPreferences.setValue(
			"weekStartsOn", String.valueOf(weekStartsOn));

		HttpServletRequest httpServletRequest =
			PortalUtil.getHttpServletRequest(actionRequest);

		SessionClicks.put(
			httpServletRequest, "calendar-portlet-default-view", defaultView);

		portletPreferences.store();
	}

}