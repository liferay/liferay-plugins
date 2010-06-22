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

package com.liferay.mail.vaadin;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationPortlet;
import com.vaadin.ui.Window;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.net.MalformedURLException;

import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

/**
 * <a href="MailPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Henri Sara
 */
public class MailPortlet extends AbstractApplicationPortlet {

	public static final String SERVLET_CONTEXT_NAME = "vaadin-mail-portlet";

	/**
	 * Notify user that not logged in.
	 */
	public void writeNotLoggedInPageHtml(RenderRequest request,
			RenderResponse response, Window window) throws IOException {
		response.setContentType("text/html");
		final BufferedWriter page = new BufferedWriter(new OutputStreamWriter(
				response.getPortletOutputStream(), "UTF-8"));

		page.write("<div class=\"portlet-msg-error\">Please login to use the mail portlet.</div>");

		page.close();
	}

	@Override
	protected void writeAjaxPage(RenderRequest request,
			RenderResponse response, Window window, Application application)
			throws IOException, MalformedURLException, PortletException {
		MailApplication mailApplication = (MailApplication) application;

		// user is set in the portlet listener by the application
		if (mailApplication.getController().getUser() == null) {
			// normally, the portal takes care of this
			// this branch is in case the user has not been registered in the controller
			writeNotLoggedInPageHtml(request, response, window);
		} else if (PortletMode.VIEW.equals(request.getPortletMode()) && WindowState.NORMAL.equals(request.getWindowState())) {
			mailApplication.writeViewPageHtml(request, response, window);
		} else {
			// this includes other modes as well as the maximized state
			super.writeAjaxPage(request, response, window, application);
		}
	}

	@Override
	protected Class<? extends Application> getApplicationClass()
			throws ClassNotFoundException {
		return MailApplication.class;
	}

	@Override
	protected String getTitle(RenderRequest request) {
		return "Vaadin Mail";
	}

}