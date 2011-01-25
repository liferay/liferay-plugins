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

package com.liferay.sampleservletfilter.hook.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Mika Koivisto
 */
public class SampleFilter implements Filter {

	public void destroy() {
		System.out.println("SampleFilter.destroy()");
	}

	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		System.out.println("SampleFilter.doFilter() - Hello " + _hello +
			" uri: " + ((HttpServletRequest)servletRequest).getRequestURI());

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SampleFilter.init()");

		_hello = filterConfig.getInitParameter("hello");
	}

	private String _hello;
}
