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

package com.liferay.opensocial.portlet;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.WebKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.service.ExpandoValueServiceUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.shindig.gadgets.spec.GadgetSpec;
import org.apache.shindig.gadgets.spec.UserPref;

/**
 * @author Michael Young
 */
public class ConfigurationActionImpl extends BaseConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject userPrefsJSONObject = JSONFactoryUtil.createJSONObject();

		Map<String, UserPref> userPrefs = getUserPrefs(actionRequest);

		for (UserPref userPref : userPrefs.values()) {
			String name = userPref.getName();

			String value = ParamUtil.getString(actionRequest, name);

			userPrefsJSONObject.put(name, value);
		}

		String namespace = ShindigUtil.getPortletResourceNamespace(
			actionRequest, themeDisplay);

		String columnName = ShindigUtil.getColumnUserPrefs(namespace);

		ExpandoValueServiceUtil.addValue(
			themeDisplay.getCompanyId(), User.class.getName(),
			ShindigUtil.getTableOpenSocial(), columnName,
			themeDisplay.getUserId(), userPrefsJSONObject.toString());

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

	public String render(PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		Map<String, UserPref> userPrefs = getUserPrefs(renderRequest);

		renderRequest.setAttribute(WebKeys.USER_PREFS, userPrefs);

		return "/gadget/configuration.jsp";
	}

	protected Map<String, UserPref> getUserPrefs(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletId = ParamUtil.getString(
			portletRequest, "portletResource");

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletId);

		Gadget gadget = ShindigUtil.getGadget(portlet.getPortletName());

		GadgetSpec gadgetSpec = ShindigUtil.getGadgetSpec(gadget.getUrl());

		return gadgetSpec.getUserPrefs();
	}

}