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

package com.liferay.compat.hook.action;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.ActionRequestWrapper;
import javax.portlet.filter.RenderRequestWrapper;
import javax.portlet.filter.ResourceRequestWrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shinn Lok
 */
public class CompatEditConfigurationAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		PortletPreferences portletPreferences = getPortletPreferences(
			request, actionRequest.getPreferences());

		actionRequest = new ConfigurationActionRequest(
			actionRequest, portletPreferences);

		originalStrutsPortletAction.processAction(
			portletConfig, actionRequest, actionResponse);
	}

	@Override
	public String render(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		PortletPreferences portletPreferences = getPortletPreferences(
			request, renderRequest.getPreferences());

		renderRequest = new ConfigurationRenderRequest(
			renderRequest, portletPreferences);

		request.setAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST, renderRequest);

		return originalStrutsPortletAction.render(
			portletConfig, renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		PortletPreferences portletPreferences = getPortletPreferences(
			request, resourceRequest.getPreferences());

		resourceRequest = new ConfigurationResourceRequest(
			resourceRequest, portletPreferences);

		originalStrutsPortletAction.serveResource(
			portletConfig, resourceRequest, resourceResponse);
	}

	protected PortletPreferences getPortletPreferences(
			HttpServletRequest request, PortletPreferences portletPreferences)
		throws PortalException, SystemException {

		String portletResource = ParamUtil.getString(
			request, "portletResource");

		if (Validator.isNull(portletResource)) {
			return portletPreferences;
		}

		return PortletPreferencesFactoryUtil.getPortletSetup(
			request, portletResource);
	}

	private class ConfigurationActionRequest extends ActionRequestWrapper {

		public ConfigurationActionRequest(
			ActionRequest actionRequest,
			PortletPreferences portletPreferences) {

			super(actionRequest);

			_portletPreferences = portletPreferences;
		}

		@Override
		public PortletPreferences getPreferences() {
			return _portletPreferences;
		}

		private PortletPreferences _portletPreferences;

	}

	private class ConfigurationRenderRequest extends RenderRequestWrapper {

		public ConfigurationRenderRequest(
			RenderRequest renderRequest,
			PortletPreferences portletPreferences) {

			super(renderRequest);

			_portletPreferences = portletPreferences;
		}

		@Override
		public PortletPreferences getPreferences() {
			return _portletPreferences;
		}

		private PortletPreferences _portletPreferences;

	}

	private class ConfigurationResourceRequest extends ResourceRequestWrapper {

		public ConfigurationResourceRequest(
			ResourceRequest resourceRequest,
			PortletPreferences portletPreferences) {

			super(resourceRequest);

			_portletPreferences = portletPreferences;
		}

		@Override
		public PortletPreferences getPreferences() {
			return _portletPreferences;
		}

		private PortletPreferences _portletPreferences;

	}

}