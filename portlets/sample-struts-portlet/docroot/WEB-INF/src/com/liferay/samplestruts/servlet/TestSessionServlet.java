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

package com.liferay.samplestruts.servlet;

import com.liferay.portal.kernel.util.ContentTypes;

import java.io.IOException;

import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class TestSessionServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		StringBuilder sb = new StringBuilder();

		// Remote User

		sb.append("<b>Remote User:</b><br /><br />");
		sb.append(request.getRemoteUser());
		sb.append("<br /><br />");

		// Session ID

		sb.append("<b>Session ID:</b><br /><br />");
		sb.append(request.getRequestedSessionId());
		sb.append("<br /><br />");

		// Servlet Session Attributes

		sb.append("<b>Servlet Session Attributes:</b><br /><br />");

		Enumeration enu = session.getAttributeNames();

		while (enu.hasMoreElements()) {
			String attrName = (String)enu.nextElement();

			Object attrValue = session.getAttribute(attrName);

			sb.append(attrName);
			sb.append("=");
			sb.append(attrValue);
			sb.append("<br />");
		}

		response.setContentType(ContentTypes.TEXT_HTML);

		ServletOutputStream servletOutputStream = response.getOutputStream();

		servletOutputStream.print(sb.toString());

		servletOutputStream.flush();
	}

}