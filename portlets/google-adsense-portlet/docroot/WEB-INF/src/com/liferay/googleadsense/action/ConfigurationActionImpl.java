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

package com.liferay.googleadsense.action;

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
 * @author Brian Wing Shun Chan
 *
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

		int adFormat = ParamUtil.getInteger(actionRequest, "adFormat");
		int adType = ParamUtil.getInteger(actionRequest, "adType");
		String adClient = ParamUtil.getString(actionRequest, "adClient");
		String adChannel = ParamUtil.getString(actionRequest, "adChannel");

		String colorBorder = ParamUtil.getString(actionRequest, "colorBorder");
		String colorBg = ParamUtil.getString(actionRequest, "colorBg");
		String colorLink = ParamUtil.getString(actionRequest, "colorLink");
		String colorText = ParamUtil.getString(actionRequest, "colorText");
		String colorUrl = ParamUtil.getString(actionRequest, "colorUrl");

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		preferences.setValue("ad-format", String.valueOf(adFormat));
		preferences.setValue("ad-type", String.valueOf(adType));
		preferences.setValue("ad-client", adClient);
		preferences.setValue("ad-channel", adChannel);

		preferences.setValue("color-border", colorBorder);
		preferences.setValue("color-bg", colorBg);
		preferences.setValue("color-link", colorLink);
		preferences.setValue("color-text", colorText);
		preferences.setValue("color-url", colorUrl);

		preferences.store();

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

}