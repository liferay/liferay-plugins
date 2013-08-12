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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.servlet.Servlet;

import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class ServletTracker
	extends BaseServiceTrackerCustomizer<Servlet, Servlet> {

	public ServletTracker(HttpSupport httpSupport) {
		super(httpSupport);
	}

	@Override
	protected void registerService(
			BundleServletContext bundleServletContext,
			ServiceReference<Servlet> serviceReference, Servlet servlet,
			Map<String, String> initParameters, HttpContext httpContext)
		throws Exception {

		String servletName = GetterUtil.getString(
			serviceReference.getProperty("servletName"));

		if (Validator.isNull(servletName)) {
			servletName = String.valueOf(
				serviceReference.getProperty(Constants.SERVICE_ID));
		}

		String urlPattern = GetterUtil.getString(
			serviceReference.getProperty("urlPattern"));

		if (Validator.isNull(urlPattern)) {
			urlPattern = GetterUtil.getString(
				serviceReference.getProperty("alias"));
		}

		if (Validator.isNull(urlPattern)) {
			return;
		}

		bundleServletContext.registerServlet(
			servletName, urlPattern, servlet, initParameters, httpContext);
	}

	@Override
	protected void unregisterService(
		BundleServletContext bundleServletContext,
		ServiceReference<Servlet> serviceReference) {

		String servletName = GetterUtil.getString(
			serviceReference.getProperty("servletName"));

		if (Validator.isNull(servletName)) {
			servletName = String.valueOf(
				serviceReference.getProperty(Constants.SERVICE_ID));
		}

		bundleServletContext.unregisterServlet(servletName);
	}

}