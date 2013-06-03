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

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class HttpServiceWrapper implements ExtendedHttpService, HttpService {

	public HttpServiceWrapper(BundleServletContext bundleServletContext) {
		this.bundleServletContext = bundleServletContext;
	}

	public HttpContext createDefaultHttpContext() {
		return bundleServletContext.getHttpContext();
	}

	public void registerFilter(
			String filterName, List<String> urlPatterns, Filter filter,
			Map<String, String> initParameters, HttpContext httpContext)
		throws NamespaceException, ServletException {

		if (httpContext == null) {
			httpContext = createDefaultHttpContext();
		}

		bundleServletContext.registerFilter(
			filterName, urlPatterns, filter, initParameters, httpContext);
	}

	public void registerListener(
		Object listener, Map<String, String> initParameters,
		HttpContext httpContext) {

		bundleServletContext.registerListener(
			listener, initParameters, httpContext);
	}

	public void registerResources(
			String alias, String name, HttpContext httpContext)
		throws NamespaceException {

		bundleServletContext.registerResources(alias, name, httpContext);
	}

	public void registerServlet(
			String servletName, List<String> urlPatterns, Servlet servlet,
			Map<String, String> initParameters, HttpContext httpContext)
		throws NamespaceException, ServletException {

		if (httpContext == null) {
			httpContext = createDefaultHttpContext();
		}

		bundleServletContext.registerServlet(
			servletName, urlPatterns, servlet, initParameters, httpContext);
	}

	public void registerServlet(
			String urlPattern, Servlet servlet,
			@SuppressWarnings("rawtypes") Dictionary initParameters,
			HttpContext httpContext)
		throws NamespaceException, ServletException {

		bundleServletContext.registerServlet(
			urlPattern, Arrays.asList(urlPattern), servlet,
			convertDictionaryToMap(initParameters), httpContext);
	}

	public void unregister(String servletName) {
		unregisterServlet(servletName);
	}

	public void unregisterFilter(String filterName) {
		bundleServletContext.unregisterFilter(filterName);
	}

	public void unregisterListener(Object listener) {
		bundleServletContext.unregisterListener(listener);
	}

	public void unregisterServlet(String servletName) {
		bundleServletContext.unregisterServlet(servletName);
	}

	protected <K, V> Map<K, V> convertDictionaryToMap(
		Dictionary<K, V> dictionary) {

		Enumeration<K> keys = dictionary.keys();
		Map<K, V> map = new HashMap<K, V>();

		while (keys.hasMoreElements()) {
			K key = keys.nextElement();

			map.put(key, dictionary.get(key));
		}

		return map;
	}

	protected BundleServletContext bundleServletContext;

}
