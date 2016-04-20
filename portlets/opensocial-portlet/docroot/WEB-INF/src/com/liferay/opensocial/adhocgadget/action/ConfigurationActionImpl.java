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

package com.liferay.opensocial.adhocgadget.action;

import com.liferay.opensocial.gadget.action.BaseConfigurationAction;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.WebKeys;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shindig.gadgets.spec.OAuthService;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class ConfigurationActionImpl extends BaseConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/adhoc_gadget/configuration.jsp";
	}

	@Override
	public void include(
			PortletConfig portletConfig, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		if (hasUserPrefs(portletConfig, request)) {
			doInclude(portletConfig, request, response);
		}

		try {
			Gadget gadget = getGadget(portletConfig, request);

			Map<String, OAuthService> oAuthServices =
				ShindigUtil.getOAuthServices(gadget.getUrl());

			request.setAttribute(WebKeys.OAUTH_SERVICES, oAuthServices);
		}
		catch (Exception e) {
		}

		super.include(portletConfig, request, response);
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String tabs2 = ParamUtil.get(actionRequest, "tabs2", "gadget");

		if (tabs2.equals("manage-oauth")) {
			ShindigUtil.updateOAuthConsumers(actionRequest, actionResponse);

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
		else if (tabs2.equals("preferences")) {
			doProcessAction(portletConfig, actionRequest, actionResponse);
		}
		else {
			String url = getParameter(actionRequest, "url");

			try {
				ShindigUtil.getGadgetSpec(url, false, true);
			}
			catch (Exception e) {
				SessionErrors.add(actionRequest, e.getClass());
			}

			setPreference(actionRequest, "url", url);

			super.processAction(portletConfig, actionRequest, actionResponse);
		}
	}

	@Override
	protected Gadget getGadget(
			PortletConfig portletConfig, HttpServletRequest request)
		throws Exception {

		RenderRequest renderRequest = (RenderRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		return ShindigUtil.getGadget(portletPreferences);
	}

}