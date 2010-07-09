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

package com.liferay.youtube.action;

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

		String annotations = ParamUtil.getString(actionRequest, "annotations");
		boolean autoplay = ParamUtil.getBoolean(actionRequest, "autoplay");
		String borderColor = ParamUtil.getString(actionRequest, "borderColor");
		boolean enableEnhancedGenieMenu = ParamUtil.getBoolean(
			actionRequest, "enableEnhancedGenieMenu");
		boolean enableFullscreen = ParamUtil.getBoolean(
			actionRequest, "enableFullscreen");
		boolean enableKeyboardControls = ParamUtil.getBoolean(
			actionRequest, "enableKeyboardControls");
		boolean enableRelatedVideos = ParamUtil.getBoolean(
			actionRequest, "enableRelatedVideos");
		boolean enableSearch = ParamUtil.getBoolean(
			actionRequest, "enableSearch");
		boolean hd = ParamUtil.getBoolean(actionRequest, "hd");
		String height = ParamUtil.getString(actionRequest, "height");
		boolean loop = ParamUtil.getBoolean(actionRequest, "loop");
		String playerColor = ParamUtil.getString(actionRequest, "playerColor");
		boolean showInfo = ParamUtil.getBoolean(actionRequest, "showInfo");
		boolean showThickerBorder = ParamUtil.getBoolean(
			actionRequest, "showThickerBorder");
		String startTime = ParamUtil.getString(actionRequest, "startTime");
		String url = ParamUtil.getString(actionRequest, "url");
		String width = ParamUtil.getString(actionRequest, "width");

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		preferences.setValue("annotations", annotations);
		preferences.setValue("autoplay", String.valueOf(autoplay));
		preferences.setValue("borderColor", borderColor);
		preferences.setValue(
			"enableEnhancedGenieMenu", String.valueOf(enableEnhancedGenieMenu));
		preferences.setValue(
			"enableFullscreen", String.valueOf(enableFullscreen));
		preferences.setValue(
			"enableKeyboardControls", String.valueOf(enableKeyboardControls));
		preferences.setValue(
			"enableRelatedVideos", String.valueOf(enableRelatedVideos));
		preferences.setValue("enableSearch", String.valueOf(enableSearch));
		preferences.setValue("hd", String.valueOf(hd));
		preferences.setValue("height", height);
		preferences.setValue("loop", String.valueOf(loop));
		preferences.setValue("playerColor", playerColor);
		preferences.setValue("showInfo", String.valueOf(showInfo));
		preferences.setValue(
			"showThickerBorder", String.valueOf(showThickerBorder));
		preferences.setValue("startTime", startTime);
		preferences.setValue("url", url);
		preferences.setValue("width", width);

		preferences.store();

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

}