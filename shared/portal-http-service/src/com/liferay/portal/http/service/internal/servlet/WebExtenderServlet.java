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

package com.liferay.portal.http.service.internal.servlet;

import com.liferay.portal.http.service.internal.http.ExtendedHttpService;
import com.liferay.portal.http.service.internal.http.FilterTracker;
import com.liferay.portal.http.service.internal.http.HttpServiceFactory;
import com.liferay.portal.http.service.internal.http.HttpSupport;
import com.liferay.portal.http.service.internal.http.ServletTracker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.JavaConstants;
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
public class WebExtenderServlet extends PortletServlet {

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

	public BundleContext getBundleContext() {
		return _bundleContext;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		HttpSupport httpSupport = new HttpSupport(_bundleContext, this);

		HttpServiceFactory httpServiceFactory = new HttpServiceFactory(
			httpSupport);

		_filterTracker = new ServiceTracker<Filter, Filter>(
			_bundleContext, Filter.class, new FilterTracker(httpSupport));

		_filterTracker.open();

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

		String includeRequestURI = (String)request.getAttribute(
			JavaConstants.JAVAX_SERVLET_INCLUDE_PATH_INFO);

		if (Validator.isNotNull(includeRequestURI)) {
			requestURI = includeRequestURI;
		}

		ServletContext servletContext = getServletContext(
			portletId, requestURI);

		if (servletContext == null) {
			response.sendError(
				HttpServletResponse.SC_NOT_FOUND,
				"No servlet or resource mapped to " + requestURI);

			return;
		}

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
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
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

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(requestURI);

		if (requestDispatcher != null) {
			requestDispatcher.forward(request, response);

			return;
		}

		if (requestURI.endsWith("/invoke") && Validator.isNotNull(portletId)) {
			super.service(request, response);

			return;
		}

		response.sendError(
			HttpServletResponse.SC_NOT_FOUND,
			"No servlet or resource mapped to " + requestURI);
	}

	private static Log _log = LogFactoryUtil.getLog(WebExtenderServlet.class);

	private BundleContext _bundleContext;
	private ServiceTracker<Filter, Filter> _filterTracker;
	private ServiceRegistration<?> _httpServiceRegistration;
	private ServiceRegistration<HttpServlet> _httpServletRegistration;
	private ServiceTracker<Servlet, Servlet> _servletTracker;

}