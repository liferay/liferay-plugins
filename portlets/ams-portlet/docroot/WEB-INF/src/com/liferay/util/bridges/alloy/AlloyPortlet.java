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

package com.liferay.util.bridges.alloy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.Router;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="AlloyPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class AlloyPortlet extends GenericPortlet {

	public void init(PortletConfig portletConfig) throws PortletException {
		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)portletConfig;

		Portlet portlet = liferayPortletConfig.getPortlet();

		FriendlyURLMapper friendlyURLMapper =
			portlet.getFriendlyURLMapperInstance();

		Router router = friendlyURLMapper.getRouter();

		_defaultRouteParameters = new HashMap<String, String>();

		router.urlToParameters("GET/", _defaultRouteParameters);

		super.init(portletConfig);
	}

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		String path = getPath(actionRequest);

		include(path, actionRequest, actionResponse);
	}

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String path = getPath(renderRequest);

		include(path, renderRequest, renderResponse);
	}

	protected Map<String, String> getDefaultRouteParameters() {
		return _defaultRouteParameters;
		/*Map<String, String> defaultRouteParameters =
			new HashMap<String, String[]>();

		defaultRouteParameters.put("controller", new String[] {"assets"});
		defaultRouteParameters.put("action", new String[] {"index"});

		return defaultRouteParameters;*/
	}

	protected String getPath(PortletRequest portletRequest) {
		String controllerPath = ParamUtil.getString(
			portletRequest, "controller");

		if (Validator.isNull(controllerPath)) {
			Map<String, String> defaultRouteParameters =
				getDefaultRouteParameters();

			controllerPath = defaultRouteParameters.get("controller");
		}

		return "/WEB-INF/jsp/controllers/" + controllerPath + "_controller.jsp";
	}

	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException, PortletException {

		PortletRequestDispatcher portletRequestDispatcher =
			getPortletContext().getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		}
		else {
			portletRequestDispatcher.include(portletRequest, portletResponse);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AlloyPortlet.class);

	private Map<String, String> _defaultRouteParameters =
		new HashMap<String, String>();

}