/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.portlet;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.util.GadgetManager;
import com.liferay.opensocial.util.GadgetManagerFactory;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

/**
 * <a href="GadgetPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 */
public class GadgetPortlet extends GenericPortlet {

	public static final String PORTLET_NAME_PREFIX = "OPENSOCIAL_";

	public static final String VIEW_CANVAS = "canvas";

	public static final String VIEW_DEFAULT = "default";

	public static final String VIEW_HOME = "home";

	public static final String VIEW_PROFILE = "profile";

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			doRender(renderRequest, renderResponse);
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		Gadget gadget = getGadget();

		GadgetManager gadgetManager =
			GadgetManagerFactory.getGadgetManager(gadget);

		renderResponse.setContentType(ContentTypes.TEXT_HTML_UTF8);

		String content =
			getContent(renderRequest, renderResponse, gadgetManager);

		PortletResponseUtil.write(renderResponse, content);
	}

	protected String getContent(
		RenderRequest renderRequest, RenderResponse renderResponse,
		GadgetManager gadgetManager) {

		WindowState windowState = renderRequest.getWindowState();

		String content = null;

		if (windowState == WindowState.MAXIMIZED) {
			content = gadgetManager.getContent(VIEW_CANVAS);
		}
		else if (windowState == WindowState.NORMAL) {
			content = gadgetManager.getContent(VIEW_PROFILE);
		}

		if (content == null) {
			content = gadgetManager.getContent(VIEW_DEFAULT);
		}

		return content;
	}

	protected Gadget getGadget() throws Exception {
		String portletName = getPortletConfig().getPortletName();

		int pos = portletName.indexOf(
			StringPool.UNDERLINE, PORTLET_NAME_PREFIX.length());

		long gadgetId = GetterUtil.getLong(portletName.substring(pos + 1));

		return GadgetLocalServiceUtil.getGadget(gadgetId);
	}

}