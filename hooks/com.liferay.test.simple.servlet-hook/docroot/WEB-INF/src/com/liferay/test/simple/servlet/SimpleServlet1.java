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

package com.liferay.test.simple.servlet;

import java.io.IOException;

import java.util.Dictionary;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="SimpleServlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 */
public class SimpleServlet1 extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		resp.setContentType("text/plain");
		resp.getWriter().write("Hello from Liferay's OSGi Servlet Bridge!\n");

		Dictionary properties =
			Activator.getBundleContext().getBundle().getHeaders();

		Enumeration enumeration = properties.keys();

		while (enumeration.hasMoreElements()) {
			Object key = enumeration.nextElement();

			resp.getWriter().write(
				String.valueOf(key).concat(" = ").concat(
					String.valueOf(properties.get(key))).concat("\n"));
		}
	}

}