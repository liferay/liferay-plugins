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

import com.liferay.httpservice.internal.http.ExtendedHttpService;
import com.liferay.httpservice.internal.http.FilterTracker;
import com.liferay.httpservice.internal.http.HttpServiceFactory;
import com.liferay.httpservice.internal.http.HttpSupport;
import com.liferay.httpservice.internal.http.ServletTracker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class WebExtenderServlet extends PortletServlet implements StrutsAction {

	public WebExtenderServlet(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Override
	public void destroy() {
		_filterTracker.close();
		_httpServiceRegistration.unregister();
		_httpServletRegistration.unregister();
		_servletTracker.close();

		super.destroy();
	}

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		service(request, response);

		return null;
	}

	@Override
	public String execute(
			StrutsAction originalStrutsAction, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		service(request, response);

		return null;
	}

	public BundleContext getBundleContext() {
		return _bundleContext;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		HttpSupport httpSupport = new HttpSupport(_bundleContext, this);

		_filterTracker = new ServiceTracker<Filter, Filter>(
			_bundleContext, Filter.class, new FilterTracker(httpSupport));

		_filterTracker.open();

		HttpServiceFactory httpServiceFactory = new HttpServiceFactory(
			httpSupport);

		Hashtable<String, Object> properties = new Hashtable<String, Object>();

		properties.put("bean.id", HttpService.class.getName());
		properties.put("original.bean", Boolean.TRUE);
		properties.put("service.vendor", ReleaseInfo.getVendor());

		_httpServiceRegistration = _bundleContext.registerService(
			new String[] {
				ExtendedHttpService.class.getName(),
				HttpService.class.getName(),
			},
			httpServiceFactory, properties);

		properties.put("bean.id", HttpServlet.class.getName());

		_httpServletRegistration = _bundleContext.registerService(
			HttpServlet.class, this, properties);

		_servletTracker = new ServiceTracker<Servlet, Servlet>(
			_bundleContext, Servlet.class, new ServletTracker(httpSupport));

		_servletTracker.open();
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		printHeaders(request);

		String portletId = (String)request.getAttribute(WebKeys.PORTLET_ID);
		String requestURI = request.getRequestURI();

		ServletContext servletContext = getServletContext(
			portletId, requestURI);

		service(request, response, servletContext, portletId, requestURI);
	}

	protected ServletContext getServletContext(
		String portletId, String requestURI) {

		Portlet portlet = null;

		if (Validator.isNotNull(portletId)) {
			try {
				String rootPortletId = PortletConstants.getRootPortletId(
					portletId);

				portlet = PortletLocalServiceUtil.getPortletById(rootPortletId);
			}
			catch (Exception e) {
				_log.debug(e, e);
			}
		}

		String servletContextName = null;

		if (portlet != null) {
			PortletApp portletApp = portlet.getPortletApp();

			servletContextName = portletApp.getServletContextName();
		}
		else if (requestURI != null) {
			servletContextName = requestURI;

			String contextPath = PortalUtil.getPathContext();

			if (Validator.isNotNull(contextPath) &&
				servletContextName.startsWith(contextPath)) {

				servletContextName = servletContextName.substring(
					contextPath.length());
			}

			String modulePath = Portal.PATH_MODULE + StringPool.SLASH;

			if (servletContextName.startsWith(modulePath)) {
				servletContextName = servletContextName.substring(
					modulePath.length());
			}

			if (servletContextName.startsWith(StringPool.SLASH)) {
				servletContextName = servletContextName.substring(1);
			}

			int index = servletContextName.indexOf(StringPool.SLASH);

			if (index != -1) {
				servletContextName = servletContextName.substring(0, index);
			}
		}

		return ServletContextPool.get(servletContextName);
	}

	protected void printHeaders(HttpServletRequest request) {
		if (!_log.isDebugEnabled()) {
			return;
		}

		Enumeration<String> enumeration = request.getHeaderNames();

		while (enumeration.hasMoreElements()) {
			Object name = enumeration.nextElement();

			_log.debug(name + " = " + request.getHeader(String.valueOf(name)));
		}
	}

	protected void service(
			HttpServletRequest request, HttpServletResponse response,
			ServletContext servletContext, String portletId, String requestURI)
		throws IOException, ServletException {

		BundleServletContext bundleServletContext =
			(BundleServletContext)servletContext;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(
				bundleServletContext.getClassLoader());

			RequestDispatcher requestDispatcher =
				bundleServletContext.getRequestDispatcher(requestURI);

			if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);

				return;
			}

			if (requestURI.endsWith("/invoke") &&
				Validator.isNotNull(portletId)) {

				super.service(request, response);

				return;
			}

			PortalUtil.sendError(
				HttpServletResponse.SC_NOT_FOUND,
				new IllegalArgumentException(
					"No servlet or resource mapped to " + requestURI),
				request, response);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WebExtenderServlet.class);

	private BundleContext _bundleContext;
	private ServiceTracker<Filter, Filter> _filterTracker;
	private ServiceRegistration<?> _httpServiceRegistration;
	private ServiceRegistration<HttpServlet> _httpServletRegistration;
	private ServiceTracker<Servlet, Servlet> _servletTracker;

}