/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.httpservice.internal.servlet;

import com.liferay.httpservice.servlet.BundleServletConfig;

import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpContext;

/**
 * @author Raymond Augé
 */
public class BundleFilterChain implements FilterChain {

	public void addFilter(Filter filter) {
		_filters.add(filter);
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		Filter filter = _filters.poll();

		if (filter == null) {
			ServletConfig servletConfig = _servlet.getServletConfig();

			if (servletConfig instanceof BundleServletConfig) {
				BundleServletConfig bundleServletConfig =
					(BundleServletConfig)servletConfig;

				HttpContext httpContext = bundleServletConfig.getHttpContext();

				if (!httpContext.handleSecurity(
						(HttpServletRequest)servletRequest,
						(HttpServletResponse)servletResponse)) {

					return;
				}
			}

			_servlet.service(servletRequest, servletResponse);

			return;
		}

		filter.doFilter(servletRequest, servletResponse, this);
	}

	public void setServlet(Servlet servlet) {
		_servlet = servlet;
	}

	private Queue<Filter> _filters = new LinkedList<Filter>();
	private Servlet _servlet;

}