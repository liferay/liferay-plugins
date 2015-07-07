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

package com.liferay.opensocial.gadget.action;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.WebKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.service.ExpandoValueServiceUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shindig.gadgets.spec.GadgetSpec;
import org.apache.shindig.gadgets.spec.UserPref;

/**
 * @author Michael Young
 */
public abstract class BaseConfigurationAction
	extends DefaultConfigurationAction {

	protected void doInclude(
			PortletConfig portletConfig, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		Map<String, UserPref> userPrefs = getUserPrefs(portletConfig, request);

		request.setAttribute(WebKeys.USER_PREFS, userPrefs);
	}

	protected void doProcessAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		HttpServletRequest request =
			PortalUtil.getHttpServletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		JSONObject userPrefsJSONObject = JSONFactoryUtil.createJSONObject();

		Map<String, UserPref> userPrefs = getUserPrefs(
			portletConfig, request);

		for (UserPref userPref : userPrefs.values()) {
			String name = userPref.getName();

			String value = ParamUtil.getString(actionRequest, name);

			userPrefsJSONObject.put(name, value);
		}

		String namespace = ShindigUtil.getPortletResourceNamespace(
			actionRequest, themeDisplay);

		String columnName = ShindigUtil.getColumnUserPrefs(
			namespace, themeDisplay);

		ExpandoValueServiceUtil.addValue(
			themeDisplay.getCompanyId(), Layout.class.getName(),
			ShindigUtil.getTableOpenSocial(), columnName, layout.getPlid(),
			userPrefsJSONObject.toString());

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			portletResource);

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
	}

	protected abstract Gadget getGadget(
			PortletConfig portletConfig, HttpServletRequest request)
		throws Exception;

	protected Map<String, UserPref> getUserPrefs(
			PortletConfig portletConfig, HttpServletRequest request)
		throws Exception {

		Gadget gadget = getGadget(portletConfig, request);

		GadgetSpec gadgetSpec = ShindigUtil.getGadgetSpec(gadget.getUrl());

		return gadgetSpec.getUserPrefs();
	}

	protected boolean hasUserPrefs(
		PortletConfig portletConfig, HttpServletRequest request) {

		try {
			Map<String, UserPref> userPrefs = getUserPrefs(
				portletConfig, request);

			return ShindigUtil.hasUserPrefs(userPrefs);
		}
		catch (Exception e) {
			return false;
		}
	}

}