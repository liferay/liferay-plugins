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

		GadgetManager gadgetManager = GadgetManagerFactory.getGadgetManager(
			gadget);

		renderResponse.setContentType(ContentTypes.TEXT_HTML_UTF8);

		String content = getContent(
			renderRequest, renderResponse, gadgetManager);

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