/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
 * <a href="MyFacesFaceletsPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class MyFacesFaceletsPortlet
	extends org.apache.myfaces.portlet.MyFacesGenericPortlet {

	public void init() throws PortletException, UnavailableException {
		_editPage = getInitParameter("EditPage");
		_helpPage = getInitParameter("HelpPage");
		_viewPage = getInitParameter("ViewPage");

		super.init();
	}

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