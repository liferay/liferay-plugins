/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.pluginssecuritymanager.portlet;

import com.liferay.pluginssecuritymanager.util.PluginsSecurityManagerUtil;
import com.liferay.pluginssecuritymanager.util.PortletPropsKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

/**
 * @author Shinn Lok
 * @author Brian Wing Shun Chan
 */
public class PluginsSecurityManagerPortlet extends MVCPortlet {

	public void updateConfiguration(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		boolean pluginsSecurityManagerAllowed = ParamUtil.getBoolean(
			actionRequest, "pluginsSecurityManagerAllowed");

		PortletPreferences preferences =
			PluginsSecurityManagerUtil.getPreferences();

		preferences.setValue(
			PortletPropsKeys.PLUGINS_SECURITY_MANAGER_ALLOWED,
			String.valueOf(pluginsSecurityManagerAllowed));

		preferences.store();

		PluginsSecurityManagerUtil.togglePACL();

		String redirect = PortalUtil.escapeRedirect(
			ParamUtil.getString(actionRequest, "redirect"));

		actionResponse.sendRedirect(redirect);
	}

}