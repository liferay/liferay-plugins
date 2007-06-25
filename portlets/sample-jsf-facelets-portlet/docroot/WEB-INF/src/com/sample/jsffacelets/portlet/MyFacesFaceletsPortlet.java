/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.jsffacelets.portlet;

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

	public void init() throws UnavailableException, PortletException {
		_editPage = (String) this.getInitParameter("EditPage");
		_helpPage = (String) this.getInitParameter("HelpPage");
		_viewPage = (String) this.getInitParameter("ViewPage");

		super.init();
	}

	public void render(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		PortletSession ses = req.getPortletSession();

		PortletMode mode = (PortletMode)ses.getAttribute("CurrentPortletMode");

		if (mode == null) {
			mode = req.getPortletMode();
		}

		if (mode != req.getPortletMode()) {
			req.setAttribute("isPortletModeChanged", Boolean.TRUE);
		}
		else {
			req.setAttribute("isPortletModeChanged", Boolean.FALSE);
		}

		ses.setAttribute("CurrentPortletMode", mode);

		super.render(req, res);
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

	protected void doEdit(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		Boolean isPortletModeChanged =
			(Boolean)req.getAttribute("isPortletModeChanged");

		if (isPortletModeChanged.booleanValue()) {
			setPortletRequestFlag(req);
			nonFacesRequest(req, res, _editPage);

			return;
		}

		facesRender(req, res);
	}

	protected void doHelp(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		Boolean isPortletModeChanged =
			(Boolean)req.getAttribute("isPortletModeChanged");

		if (isPortletModeChanged.booleanValue()) {
			setPortletRequestFlag(req);
			nonFacesRequest(req, res, _helpPage);

			return;
		}

		facesRender(req, res);
	}

	private String _editPage = null;
	private String _helpPage = null;
	private String _viewPage = null;

}