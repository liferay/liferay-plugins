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

package com.liferay.alfrescocontent.action;

import com.liferay.alfrescocontent.util.AlfrescoContentCacheUtil;
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
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Michael Young
 *
 */
public class ConfigurationActionImpl extends BaseConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.UPDATE)) {
			String userId = ParamUtil.getString(actionRequest, "userId");
			String password = ParamUtil.getString(actionRequest, "password");
			String uuid = ParamUtil.getString(actionRequest, "uuid");
			boolean showEditIcon = ParamUtil.getBoolean(
				actionRequest, "showEditIcon");
			boolean maximizeLinks = ParamUtil.getBoolean(
				actionRequest, "maximizeLinks");

			String portletResource = ParamUtil.getString(
				actionRequest, "portletResource");

			PortletPreferences preferences =
				PortletPreferencesFactoryUtil.getPortletSetup(
					actionRequest, portletResource);

			preferences.setValue("user-id", userId);
			preferences.setValue("password", password);
			preferences.setValue("uuid", uuid);
			preferences.setValue(
				"show-edit-icon", String.valueOf(showEditIcon));
			preferences.setValue(
				"maximize-links", String.valueOf(maximizeLinks));

			preferences.store();
		}
		else if (cmd.equals("clearCache")) {
			AlfrescoContentCacheUtil.clearCache();
		}

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

}