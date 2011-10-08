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

package com.liferay.osgi.http;

import java.io.IOException;

import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public class HttpServiceServletWrapper extends HttpServlet {

	public HttpServiceServletWrapper(HttpServlet httpServlet) {
		_httpServlet = httpServlet;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		if (_initialized.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
			_httpServlet.init(servletConfig);
		}
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		_httpServlet.service(request, response);
	}

	private HttpServlet _httpServlet;
	private AtomicReference<Boolean> _initialized =
		new AtomicReference<Boolean>(Boolean.FALSE);

}