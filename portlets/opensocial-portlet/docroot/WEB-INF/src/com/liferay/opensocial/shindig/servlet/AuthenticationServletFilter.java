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

package com.liferay.opensocial.shindig.servlet;

import com.google.inject.Injector;
import com.liferay.portal.kernel.util.ServerDetector;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.apache.shindig.common.servlet.GuiceServletContextListener.*;

/**
 * @author Igor Spasic
 */
public class AuthenticationServletFilter extends
	org.apache.shindig.auth.AuthenticationServletFilter {

	@Override
	public void doFilter(
		ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		if (injector == null) {
			HttpServletRequest httpServletRequest = (HttpServletRequest)request;

			_init(httpServletRequest.getSession().getServletContext());
		}
		super.doFilter(request, response, chain);
	}

	public void init(FilterConfig config) throws ServletException {
		// LPS-23577
		if (ServerDetector.isWebSphere()) {
			injector = null;
		} else {
			super.init(config);
		}
	}

	private void _init(ServletContext context) throws ServletException {

		injector = (Injector)context.getAttribute(INJECTOR_ATTRIBUTE);

		if (injector == null) {
			injector = (Injector) context.getAttribute(INJECTOR_NAME);

			if (injector == null) {
				throw new UnavailableException(
					"Guice Injector not found! Make sure you registered " +
						GuiceServletContextListener.class.getName() +
						" as a listener");
			}
		}
		injector.injectMembers(this);
	}

}
