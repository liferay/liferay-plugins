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

package com.liferay.httpservice.internal.http;

import com.liferay.httpservice.internal.servlet.BundleServletContext;
import com.liferay.portal.kernel.servlet.ServletContextPool;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.osgi.service.http.HttpContext;
import org.osgi.service.http.NamespaceException;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class NonWABHttpServiceWrapper extends HttpServiceWrapper {

	public NonWABHttpServiceWrapper(BundleServletContext bundleServletContext) {
		super(bundleServletContext);
	}

	@Override
	public void registerFilter(
			String filterName, List<String> urlPatterns, Filter filter,
			Map<String, String> initParameters, HttpContext httpContext)
		throws NamespaceException, ServletException {

		super.registerFilter(
			filterName, urlPatterns, filter, initParameters, httpContext);

		_registrations.add(filterName);
	}

	@Override
	public void registerListener(
		Object listener, Map<String, String> initParameters,
		HttpContext httpContext) {

		if (httpContext == null) {
			httpContext = createDefaultHttpContext();
		}

		bundleServletContext.registerListener(
			listener, initParameters, httpContext);

		_registrations.add(listener);
	}

	@Override
	public void registerResources(
			String alias, String name, HttpContext httpContext)
		throws NamespaceException {

		if (httpContext == null) {
			httpContext = createDefaultHttpContext();
		}

		bundleServletContext.registerResources(alias, name, httpContext);

		_registrations.add(alias);
	}

	@Override
	public void registerServlet(
			String servletName, List<String> urlPatterns, Servlet servlet,
			Map<String, String> initParameters, HttpContext httpContext)
		throws NamespaceException, ServletException {

		super.registerServlet(
			servletName, urlPatterns, servlet, initParameters, httpContext);

		_registrations.add(servletName);
	}

	@Override
	public void registerServlet(
			String urlPattern, Servlet servlet,
			@SuppressWarnings("rawtypes") Dictionary initParameters,
			HttpContext httpContext)
		throws NamespaceException, ServletException {

		// This method is not called by Liferay directly, but is made available
		// for other OSGi modules that depend on the HTTP service

		super.registerServlet(urlPattern, servlet, initParameters, httpContext);

		_registrations.add(urlPattern);
	}

	@Override
	public void unregister(String servletName) {
		unregisterServlet(servletName);
	}

	@Override
	public void unregisterFilter(String filterName) {
		super.unregisterFilter(filterName);

		removeRegistration(filterName);
	}

	@Override
	public void unregisterListener(Object listener) {
		bundleServletContext.unregisterListener(listener);

		removeRegistration(listener);
	}

	@Override
	public void unregisterServlet(String servletName) {
		super.unregisterServlet(servletName);

		removeRegistration(servletName);
	}

	protected void removeRegistration(Object object) {
		_registrations.remove(object);

		if (!_registrations.isEmpty()) {
			return;
		}

		ServletContextPool.remove(bundleServletContext.getServletContextName());

		bundleServletContext = null;
	}

	private List<Object> _registrations = new ArrayList<Object>();

}