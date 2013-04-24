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

import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class BundleServletRequest extends HttpServletRequestWrapper {

	public BundleServletRequest(
		BundleRequestDispatcher bundleRequestDispatcher,
		HttpServletRequest request) {

		super(request);

		_bundleRequestDispatcher = bundleRequestDispatcher;

		_bundleServletContext =
			_bundleRequestDispatcher.getBundleServletContext();

		_session = new BundleSession(
			_bundleServletContext, request.getSession());
	}

	@Override
	public Object getAttribute(String name) {
		if ((name.equals(
				JavaConstants.JAVAX_SERVLET_FORWARD_SERVLET_PATH) ||
			 name.equals(WebKeys.INVOKER_FILTER_URI)) &&
			_attributes.containsKey(WebKeys.SERVLET_PATH)) {

			return _attributes.get(WebKeys.SERVLET_PATH);
		}

		if (_maskedAttributes.contains(name)) {
			return _attributes.get(name);
		}

		return super.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		List<String> attributeNames = new UniqueList<String>();

		Enumeration<String> enumeration = super.getAttributeNames();

		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();

			attributeNames.add(name);
		}

		attributeNames.addAll(_attributes.keySet());

		return Collections.enumeration(attributeNames);
	}

	@Override
	public String getContextPath() {
		return _bundleServletContext.getContextPath();
	}

	@Override
	public String getPathInfo() {
		return _bundleRequestDispatcher.getPathInfo();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		RequestDispatcher requestDispatcher =
			_bundleServletContext.getRequestDispatcher(path);

		if (requestDispatcher != null) {
			return requestDispatcher;
		}

		return super.getRequestDispatcher(path);
	}

	@Override
	public String getRequestURI() {
		String contextPath = getContextPath();

		return contextPath.concat(_bundleRequestDispatcher.getRequestURI());
	}

	@Override
	public String getServletPath() {
		return _bundleRequestDispatcher.getServletPath();
	}

	@Override
	public HttpSession getSession() {
		return _session;
	}

	@Override
	public HttpSession getSession(boolean create) {
		return _session;
	}

	@Override
	public void removeAttribute(String name) {
		Object oldValue = null;

		if (_maskedAttributes.contains(name)) {
			oldValue = _attributes.remove(name);
		}
		else {
			oldValue = super.getAttribute(name);

			super.removeAttribute(name);
		}

		List<ServletRequestAttributeListener> servletRequestAttributeListeners =
			_bundleServletContext.getServletRequestAttributeListeners();

		for (ServletRequestAttributeListener servletRequestAttributeListener :
				servletRequestAttributeListeners) {

			ServletRequestAttributeEvent servletRequestAttributeEvent =
				new ServletRequestAttributeEvent(
					_bundleServletContext, this, name, oldValue);

			servletRequestAttributeListener.attributeReplaced(
				servletRequestAttributeEvent);
		}
	}

	@Override
	public void setAttribute(String name, Object value) {
		Object oldValue = null;

		if (_maskedAttributes.contains(name)) {
			oldValue = _attributes.put(name, value);
		}
		else {
			oldValue = super.getAttribute(name);

			super.setAttribute(name, value);
		}

		List<ServletRequestAttributeListener> servletRequestAttributeListeners =
			_bundleServletContext.getServletRequestAttributeListeners();

		for (ServletRequestAttributeListener servletRequestAttributeListener :
				servletRequestAttributeListeners) {

			ServletRequestAttributeEvent servletRequestAttributeEvent =
				new ServletRequestAttributeEvent(
					_bundleServletContext, this, name, oldValue);

			if (oldValue != null) {
				servletRequestAttributeListener.attributeReplaced(
					servletRequestAttributeEvent);
			}
			else {
				servletRequestAttributeListener.attributeAdded(
					servletRequestAttributeEvent);
			}
		}
	}

	private static Set<String> _maskedAttributes = SetUtil.fromArray(
		new String[] {
			JavaConstants.JAVAX_SERVLET_ERROR_REQUEST_URI,
			JavaConstants.JAVAX_SERVLET_FORWARD_CONTEXT_PATH,
			JavaConstants.JAVAX_SERVLET_FORWARD_SERVLET_PATH,
			JavaConstants.JAVAX_SERVLET_INCLUDE_PATH_INFO,
			JavaConstants.JAVAX_SERVLET_INCLUDE_QUERY_STRING,
			WebKeys.INVOKER_FILTER_URI, WebKeys.SERVLET_PATH
		});

	private Map<String, Object> _attributes = new HashMap<String, Object>();
	private BundleRequestDispatcher _bundleRequestDispatcher;
	private BundleServletContext _bundleServletContext;
	private HttpSession _session;

}