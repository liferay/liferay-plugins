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

package com.liferay.digg.action;

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

		String apiSearch = ParamUtil.getString(actionRequest, "apiSearch");
		String friendsUsername = ParamUtil.getString(
			actionRequest, "friendsUsername");
		String minDate = ParamUtil.getString(actionRequest, "minDate");
		String newsFriends = ParamUtil.getString(actionRequest, "newsFriends");
		String newsFront = ParamUtil.getString(actionRequest, "newsFront");
		String newsTop = ParamUtil.getString(actionRequest, "newsTop");
		String newsType = ParamUtil.getString(actionRequest, "newsType");
		String newsUser = ParamUtil.getString(actionRequest, "newsUser");
		boolean polling = ParamUtil.getBoolean(actionRequest, "polling");
		String pollingRate = ParamUtil.getString(actionRequest, "pollingRate");
		String searchSort = ParamUtil.getString(actionRequest, "searchSort");
		String searchTopics = ParamUtil.getString(
			actionRequest, "searchTopics");
		boolean showDesc = ParamUtil.getBoolean(actionRequest, "showDesc");
		boolean showDiggs = ParamUtil.getBoolean(actionRequest, "showDiggs");
		boolean showThumbs = ParamUtil.getBoolean(actionRequest, "showThumbs");
		String sourcePopOrUp = ParamUtil.getString(
			actionRequest, "sourcePopOrUp");
		String storyCount = ParamUtil.getString(actionRequest, "storyCount");
		boolean targetBlank = ParamUtil.getBoolean(
			actionRequest, "targetBlank");
		String url = ParamUtil.getString(actionRequest, "url");
		String urlSort = ParamUtil.getString(actionRequest, "urlSort");
		String userUsername = ParamUtil.getString(
			actionRequest, "userUsername");

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		preferences.setValue("apiSearch", apiSearch);
		preferences.setValue("friendsUsername", friendsUsername);
		preferences.setValue("minDate", minDate);
		preferences.setValue("newsFriends", newsFriends);
		preferences.setValue("newsFront", newsFront);
		preferences.setValue("newsTop", newsTop);
		preferences.setValue("newsType", newsType);
		preferences.setValue("newsUser", newsUser);
		preferences.setValue("polling", String.valueOf(polling));
		preferences.setValue("pollingRate", pollingRate);
		preferences.setValue("searchSort", searchSort);
		preferences.setValue("searchTopics", searchTopics);
		preferences.setValue("showDesc", String.valueOf(showDesc));
		preferences.setValue("showDiggs", String.valueOf(showDiggs));
		preferences.setValue("showThumbs", String.valueOf(showThumbs));
		preferences.setValue("sourcePopOrUp", sourcePopOrUp);
		preferences.setValue("storyCount", storyCount);
		preferences.setValue("targetBlank", String.valueOf(targetBlank));
		preferences.setValue("url", url);
		preferences.setValue("urlSort", urlSort);
		preferences.setValue("userUsername", userUsername);

		preferences.store();

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

}