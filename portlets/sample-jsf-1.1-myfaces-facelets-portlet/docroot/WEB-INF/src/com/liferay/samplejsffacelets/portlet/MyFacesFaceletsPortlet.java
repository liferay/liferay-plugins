/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.samplejsffacelets.portlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.UnavailableException;

/**
 * @author Raymond Augé
 */
public class MyFacesFaceletsPortlet
	extends org.apache.myfaces.portlet.MyFacesGenericPortlet {

	@Override
	public void init() throws PortletException, UnavailableException {
		_editPage = getInitParameter("EditPage");
		_helpPage = getInitParameter("HelpPage");
		_viewPage = getInitParameter("ViewPage");

		super.init();
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletSession portletSession = renderRequest.getPortletSession();

		PortletMode mode = (PortletMode)portletSession.getAttribute(
			"CurrentPortletMode");

		if (mode == null) {
			mode = renderRequest.getPortletMode();
		}

		if (mode != renderRequest.getPortletMode()) {
			renderRequest.setAttribute("isPortletModeChanged", Boolean.TRUE);
		}
		else {
			renderRequest.setAttribute("isPortletModeChanged", Boolean.FALSE);
		}

		portletSession.setAttribute("CurrentPortletMode", mode);

		super.render(renderRequest, renderResponse);
	}

	@Override
	protected void setDefaultView() throws UnavailableException {
		defaultView = getPortletConfig().getInitParameter(DEFAULT_VIEW);

		if (defaultView == null) {
			defaultView = _viewPage;
		}

		if (defaultView == null) {
			throw new UnavailableException(
				"Default JSF view is not specified in portlet.xml");
		}
	}

	@Override
	protected void doEdit(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		Boolean isPortletModeChanged =
			(Boolean)renderRequest.getAttribute("isPortletModeChanged");

		if (isPortletModeChanged.booleanValue()) {
			setPortletRequestFlag(renderRequest);
			nonFacesRequest(renderRequest, renderResponse, _editPage);

			return;
		}

		facesRender(renderRequest, renderResponse);
	}

	@Override
	protected void doHelp(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		Boolean isPortletModeChanged =
			(Boolean)renderRequest.getAttribute("isPortletModeChanged");

		if (isPortletModeChanged.booleanValue()) {
			setPortletRequestFlag(renderRequest);
			nonFacesRequest(renderRequest, renderResponse, _helpPage);

			return;
		}

		facesRender(renderRequest, renderResponse);
	}

	private String _editPage = null;
	private String _helpPage = null;
	private String _viewPage = null;

}