/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.akismet.portlet;

import com.liferay.akismet.util.AkismetUtil;
import com.liferay.akismet.util.PortletPropsKeys;
import com.liferay.akismet.util.PrefsPortletPropsUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 */
public class AkismetPortlet extends MVCPortlet {

	public void updateConfiguration(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean enabled = ParamUtil.getBoolean(actionRequest, "enabled");
		String apiKey = ParamUtil.getString(actionRequest, "apiKey");

		PortletPreferences preferences =
			PrefsPortletPropsUtil.getPortletPreferences(
				themeDisplay.getCompanyId());

		preferences.setValue("enabled", String.valueOf(enabled));
		preferences.setValue(PortletPropsKeys.AKISMET_API_KEY, apiKey);

		preferences.store();

		if (!AkismetUtil.verifyApiKey(themeDisplay.getCompanyId(), apiKey)) {
			SessionErrors.add(actionRequest, "apiKeyError");
		}
	}

}