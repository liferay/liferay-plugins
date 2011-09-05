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

import com.liferay.portal.kernel.util.StringPool;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

/**
 * <a href="HttpServiceTracker.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 */
public class HttpServiceTracker extends ServiceTracker {

	public HttpServiceTracker(BundleContext bundleContext) {
		super(bundleContext, HttpService.class.getName(), null);

		_contextPath = StringPool.SLASH.concat(
			context.getBundle().getSymbolicName());
	}

	@Override
	public Object addingService(ServiceReference serviceReference) {
		HttpService httpService = (HttpService)super.addingService(
			serviceReference);

		if (httpService == null) {
			return null;
		}

		try {
			HttpContext httpContext = httpService.createDefaultHttpContext();

			System.out.println("Registering web plugin at " + _contextPath);

//			HttpContext commonContext = new BundleEntryHttpContext(
//				context.getBundle(), webappContext);

			httpService.registerServlet(
				"/1", new SimpleServlet1(), null, httpContext);
			httpService.registerServlet(
				"/2", new IncludeServlet2(), null, httpContext);
			httpService.registerResources("/*.png", "", httpContext);

//			Servlet jspServlet = new JspServlet(
//				context.getBundle(), null, webappContext);
//
//			httpService.registerServlet(
//				webappContext + "/*.jsp", jspServlet, null, commonContext);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return httpService;
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		HttpService httpService = (HttpService)service;

//		System.out.println("Unregistering web plugin at " + webappContext);

		httpService.unregister("/1");
		httpService.unregister("/2");
		httpService.unregister("/*.png");
//		httpService.unregister(webappContext + "/*.jsp");

		super.removedService(reference, service);
	}

	protected String _contextPath = null;

}