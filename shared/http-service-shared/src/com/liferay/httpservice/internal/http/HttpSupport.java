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
import com.liferay.httpservice.internal.servlet.WebExtenderServlet;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class HttpSupport {

	public HttpSupport(
		BundleContext bundleContext, WebExtenderServlet webExtenderServlet) {

		_bundleContext = bundleContext;
		_webExtenderServlet = webExtenderServlet;
	}

	public BundleContext getBundleContext() {
		return _bundleContext;
	}

	public BundleServletContext getBundleServletContext(Bundle bundle)
		throws InvalidSyntaxException {

		BundleServletContext bundleServletContext = getWABBundleServletContext(
			bundle);

		if (bundleServletContext != null) {
			return bundleServletContext;
		}

		return getNonWABBundleServletContext(bundle);
	}

	public Filter getFilter(Bundle bundle) throws InvalidSyntaxException {
		StringBundler sb = new StringBundler(7);

		sb.append("(&(bundle.symbolicName=");
		sb.append(bundle.getSymbolicName());
		sb.append(")(bundle.version=");
		sb.append(bundle.getVersion());
		sb.append(")(bundle.id=");
		sb.append(bundle.getBundleId());
		sb.append(")(Web-ContextPath=*))");

		return _bundleContext.createFilter(sb.toString());
	}

	public Filter getFilter(String contextId) throws InvalidSyntaxException {
		return _bundleContext.createFilter("(&(contextId=" + contextId + ")");
	}

	public HttpContext getHttpContext(String contextId)
		throws InvalidSyntaxException {

		if (Validator.isNull(contextId)) {
			return null;
		}

		Filter filter = getFilter(contextId);

		Collection<ServiceReference<HttpContext>> serviceReferences =
			_bundleContext.getServiceReferences(
				HttpContext.class, filter.toString());

		Iterator<ServiceReference<HttpContext>> iterator =
			serviceReferences.iterator();

		if (iterator.hasNext()) {
			ServiceReference<HttpContext> httpContextReference =
				iterator.next();

			return _bundleContext.getService(httpContextReference);
		}

		return null;
	}

	public BundleServletContext getNonWABBundleServletContext(Bundle bundle) {
		String servletContextName = BundleServletContext.getServletContextName(
			bundle, true);

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		if (servletContext == null) {
			BundleServletContext bundleServletContext =
				new BundleServletContext(
					bundle, servletContextName,
					_webExtenderServlet.getServletContext());

			bundleServletContext.setServletContextName(servletContextName);

			ServletContextPool.put(servletContextName, bundleServletContext);

			servletContext = bundleServletContext;
		}

		return (BundleServletContext)servletContext;
	}

	public BundleServletContext getWABBundleServletContext(Bundle bundle)
		throws InvalidSyntaxException {

		Filter filter = getFilter(bundle);

		Collection<ServiceReference<BundleServletContext>> serviceReferences =
			_bundleContext.getServiceReferences(
				BundleServletContext.class, filter.toString());

		Iterator<ServiceReference<BundleServletContext>> iterator =
			serviceReferences.iterator();

		if (iterator.hasNext()) {
			ServiceReference<BundleServletContext> servletContextReference =
				iterator.next();

			return _bundleContext.getService(servletContextReference);
		}

		return null;
	}

	public WebExtenderServlet getWebExtenderServlet() {
		return _webExtenderServlet;
	}

	private BundleContext _bundleContext;
	private WebExtenderServlet _webExtenderServlet;

}