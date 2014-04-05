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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Michael Young
 */
public class ConfigurationActionImpl extends BaseConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		doProcessAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		doRender(portletConfig, renderRequest, renderResponse);

		return "/gadget/configuration.jsp";
	}

	@Override
	protected Gadget getGadget(
			PortletConfig portletConfig, PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletResource = ParamUtil.getString(
			portletRequest, "portletResource");

		return ShindigUtil.getGadget(
			portletResource, themeDisplay.getCompanyId());
	}

}