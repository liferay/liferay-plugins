/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.shindig.servlet;

import com.google.inject.Injector;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shindig.common.servlet.GuiceServletContextListener;

/**
 * @author Igor Spasic
 */
public class AuthenticationServletFilter
	extends org.apache.shindig.auth.AuthenticationServletFilter {

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		if (injector == null) {
			HttpServletRequest request = (HttpServletRequest)servletRequest;

			HttpSession session = request.getSession();

			_init(session.getServletContext());
		}

		super.doFilter(servletRequest, servletResponse, filterChain);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		// LPS-23577 and LPS-41715

		injector = null;
	}

	private void _init(ServletContext servletContext) throws ServletException {
		injector = (Injector)servletContext.getAttribute(
			GuiceServletContextListener.INJECTOR_ATTRIBUTE);

		if (injector == null) {
			injector = (Injector)servletContext.getAttribute(
				GuiceServletContextListener.INJECTOR_NAME);

			if (injector == null) {
				throw new UnavailableException(
					"Guice injector is not available. Please register " +
						GuiceServletContextListener.class.getName() + ".");
			}
		}

		injector.injectMembers(this);
	}

}