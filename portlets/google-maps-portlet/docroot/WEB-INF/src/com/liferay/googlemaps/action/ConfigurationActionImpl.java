/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.googlemaps.action;

import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;

/**
 * @author Mark Wong
 */
public class ConfigurationActionImpl extends BaseConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		String license = ParamUtil.getString(actionRequest, "license");
		String mapAddress = ParamUtil.getString(actionRequest, "mapAddress");
		boolean mapInputEnabled = ParamUtil.getBoolean(
			actionRequest, "mapInputEnabled");
		String directionsAddress = ParamUtil.getString(
			actionRequest, "directionsAddress");
		boolean directionsInputEnabled = ParamUtil.getBoolean(
			actionRequest, "directionsInputEnabled");
		String height = ParamUtil.getString(actionRequest, "height");

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		preferences.setValue("license", license);
		preferences.setValue("map-address", mapAddress);
		preferences.setValue(
			"map-input-enabled", String.valueOf(mapInputEnabled));
		preferences.setValue("directions-address", directionsAddress);
		preferences.setValue(
			"directions-input-enabled", String.valueOf(directionsInputEnabled));
		preferences.setValue("height", height);

		preferences.store();

		PortletSession portletSession = actionRequest.getPortletSession();

		portletSession.removeAttribute(
			PortalUtil.getPortletNamespace(portletResource) + "mapAddress",
			PortletSession.APPLICATION_SCOPE);

		portletSession.removeAttribute(
			PortalUtil.getPortletNamespace(portletResource) +
				"directionsAddress",
			PortletSession.APPLICATION_SCOPE);

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

}