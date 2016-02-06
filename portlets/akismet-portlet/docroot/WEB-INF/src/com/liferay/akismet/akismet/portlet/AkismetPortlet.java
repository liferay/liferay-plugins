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

package com.liferay.akismet.akismet.portlet;

import com.liferay.akismet.util.AkismetUtil;
import com.liferay.akismet.util.PortletPropsKeys;
import com.liferay.akismet.util.PrefsPortletPropsUtil;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AkismetPortlet extends MVCPortlet {

	public void updateConfiguration(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String apiKey = ParamUtil.getString(actionRequest, "apiKey");
		int checkThreshold = ParamUtil.getInteger(
			actionRequest, "checkThreshold");
		boolean discussionsEnabled = ParamUtil.getBoolean(
			actionRequest, "discussionsEnabled");
		boolean messageBoardsEnabled = ParamUtil.getBoolean(
			actionRequest, "messageBoardsEnabled");
		int reportableTime = ParamUtil.getInteger(
			actionRequest, "reportableTime");
		boolean wikiEnabled = ParamUtil.getBoolean(
			actionRequest, "wikiEnabled");

		PortletPreferences preferences =
			PrefsPortletPropsUtil.getPortletPreferences(
				themeDisplay.getCompanyId());

		preferences.setValue(PortletPropsKeys.AKISMET_API_KEY, apiKey);
		preferences.setValue(
			PortletPropsKeys.AKISMET_CHECK_THRESHOLD,
			String.valueOf(checkThreshold));
		preferences.setValue(
			PortletPropsKeys.AKISMET_DISCUSSIONS_CHECK_ENABLED,
			String.valueOf(discussionsEnabled));
		preferences.setValue(
			PortletPropsKeys.AKISMET_MESSAGE_BOARDS_CHECK_ENABLED,
			String.valueOf(messageBoardsEnabled));
		preferences.setValue(
			PortletPropsKeys.AKISMET_REPORTABLE_TIME,
			String.valueOf(reportableTime));
		preferences.setValue(
			PortletPropsKeys.AKISMET_WIKI_CHECK_ENABLED,
			String.valueOf(wikiEnabled));

		preferences.store();

		if (!AkismetUtil.verifyApiKey(themeDisplay.getCompanyId(), apiKey)) {
			SessionErrors.add(actionRequest, "apiKeyError");
		}
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			if (RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(), themeDisplay.getCompanyId(),
					RoleConstants.ADMINISTRATOR, true)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

}