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

package com.liferay.googlemaps.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletSession;

/**
 * @author Mark Wong
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletSession portletSession = actionRequest.getPortletSession();

		portletSession.removeAttribute(
			PortalUtil.getPortletNamespace(portletResource) + "mapAddress",
			PortletSession.APPLICATION_SCOPE);

		portletSession.removeAttribute(
			PortalUtil.getPortletNamespace(portletResource) +
				"directionsAddress",
			PortletSession.APPLICATION_SCOPE);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

}