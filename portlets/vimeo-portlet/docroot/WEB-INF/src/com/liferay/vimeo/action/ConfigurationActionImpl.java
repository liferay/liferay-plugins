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

package com.liferay.vimeo.action;

import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

/**
 * @author David Truong
 * @author Jonathan Neal
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

		boolean autoplay = ParamUtil.getBoolean(actionRequest, "autoplay");
		boolean enableFullscreen = ParamUtil.getBoolean(
			actionRequest, "enableFullscreen");
		String height = ParamUtil.getString(actionRequest, "height");
		String playerColor = ParamUtil.getString(actionRequest, "playerColor");
		boolean showByline = ParamUtil.getBoolean(actionRequest, "showByline");
		boolean showPortrait = ParamUtil.getBoolean(
			actionRequest, "showPortrait");
		boolean showTitle = ParamUtil.getBoolean(actionRequest, "showTitle");
		String url = ParamUtil.getString(actionRequest, "url");
		String width = ParamUtil.getString(actionRequest, "width");

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		preferences.setValue("autoplay", String.valueOf(autoplay));
		preferences.setValue(
			"enableFullscreen", String.valueOf(enableFullscreen));
		preferences.setValue("height", height);
		preferences.setValue("playerColor", playerColor);
		preferences.setValue("showByline", String.valueOf(showByline));
		preferences.setValue("showPortrait", String.valueOf(showPortrait));
		preferences.setValue("showTitle", String.valueOf(showTitle));
		preferences.setValue("url", url);
		preferences.setValue("width", width);

		preferences.store();

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

}