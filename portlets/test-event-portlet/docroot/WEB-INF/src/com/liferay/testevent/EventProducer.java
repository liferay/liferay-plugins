/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.testevent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.portlet.GenericPortlet;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

/**
 * @author Shinn Lok
 */
public class EventProducer extends GenericPortlet {

	public static final String KEY = "ABC";
	public static final QName EVENT_QNAME =
		new QName("http://www.liferay.com", "lps18191");

	@Override
	public void processAction(ActionRequest request, ActionResponse response)
		throws PortletException, IOException {

		PortletPreferences prefs = request.getPreferences();

		prefs.setValue(EventProducer.KEY, "DEF");
		prefs.store();

		response.setEvent(EVENT_QNAME, new HashMap());
	}

	@Override
	public void doView(RenderRequest request,RenderResponse response) 
		throws PortletException, IOException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		try {
			PortletURL actionURL = response.createActionURL();

			writer.write("<a href=\"" + actionURL.toString()
				+ "\">Produce Event</a>");
		}
		finally {
			writer.close();
		}
	}
}