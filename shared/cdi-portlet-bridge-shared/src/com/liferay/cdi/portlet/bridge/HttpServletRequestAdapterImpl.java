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

package com.liferay.cdi.portlet.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.security.Principal;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ClientDataRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * @author Neil Griffin
 */
public class HttpServletRequestAdapterImpl
	implements HttpServletRequestAdapter {

	public HttpServletRequestAdapterImpl(PortletRequest portletRequest) {
		_portletRequest = portletRequest;
	}

	public boolean authenticate(HttpServletResponse httpServletResponse) {
		throw new UnsupportedOperationException();
	}

	public AsyncContext getAsyncContext() {
		throw new UnsupportedOperationException();
	}

	public Object getAttribute(String name) {
		return _portletRequest.getAttribute(name);
	}

	public Enumeration<String> getAttributeNames() {
		return _portletRequest.getAttributeNames();
	}

	public String getAuthType() {
		return _portletRequest.getAuthType();
	}

	public String getCharacterEncoding() {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getCharacterEncoding();
	}

	public int getContentLength() {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getContentLength();
	}

	public String getContentType() {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getContentType();
	}

	public String getContextPath() {
		return _portletRequest.getContextPath();
	}

	public Cookie[] getCookies() {
		return _portletRequest.getCookies();
	}

	public long getDateHeader(String name) {
		throw new UnsupportedOperationException();
	}

	public DispatcherType getDispatcherType() {
		throw new UnsupportedOperationException();
	}

	public String getHeader(String name) {
		throw new UnsupportedOperationException();
	}

	public Enumeration<String> getHeaderNames() {
		throw new UnsupportedOperationException();
	}

	public Enumeration<String> getHeaders(String name) {
		throw new UnsupportedOperationException();
	}

	public ServletInputStream getInputStream() {
		if (_servletInputStream == null) {
			if (!(_portletRequest instanceof ClientDataRequest)) {
				throw new UnsupportedOperationException();
			}

			ClientDataRequest clientDataRequest =
				(ClientDataRequest)_portletRequest;

			_servletInputStream = new ServletInputStreamAdapter(
				clientDataRequest);
		}

		return _servletInputStream;
	}

	public int getIntHeader(String name) {
		throw new UnsupportedOperationException();
	}

	public String getLocalAddr() {
		throw new UnsupportedOperationException();
	}

	public Locale getLocale() {
		return _portletRequest.getLocale();
	}

	public Enumeration<Locale> getLocales() {
		return _portletRequest.getLocales();
	}

	public String getLocalName() {
		Locale locale = _portletRequest.getLocale();

		return locale.getDisplayName();
	}

	public int getLocalPort() {
		return _portletRequest.getServerPort();
	}

	public String getMethod() {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getMethod();
	}

	public String getParameter(String name) {
		return _portletRequest.getParameter(name);
	}

	public Map<String, String[]> getParameterMap() {
		return _portletRequest.getParameterMap();
	}

	public Enumeration<String> getParameterNames() {
		return _portletRequest.getParameterNames();
	}

	public String[] getParameterValues(String name) {
		return _portletRequest.getParameterValues(name);
	}

	public Part getPart(String name) {
		throw new UnsupportedOperationException();
	}

	public Collection<Part> getParts() {
		throw new UnsupportedOperationException();
	}

	public String getPathInfo() {
		throw new UnsupportedOperationException();
	}

	public String getPathTranslated() {
		throw new UnsupportedOperationException();
	}

	public PortletRequest getPortletRequest() {
		return _portletRequest;
	}

	public String getProtocol() {
		throw new UnsupportedOperationException();
	}

	public String getQueryString() {
		throw new UnsupportedOperationException();
	}

	public BufferedReader getReader() throws IOException {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getReader();
	}

	public String getRealPath(String path) {
		throw new UnsupportedOperationException();
	}

	public String getRemoteAddr() {
		throw new UnsupportedOperationException();
	}

	public String getRemoteHost() {
		throw new UnsupportedOperationException();
	}

	public int getRemotePort() {
		throw new UnsupportedOperationException();
	}

	public String getRemoteUser() {
		return _portletRequest.getRemoteUser();
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		throw new UnsupportedOperationException();
	}

	public String getRequestedSessionId() {
		return _portletRequest.getRequestedSessionId();
	}

	public String getRequestURI() {
		throw new UnsupportedOperationException();
	}

	public StringBuffer getRequestURL() {
		throw new UnsupportedOperationException();
	}

	public String getScheme() {
		return _portletRequest.getScheme();
	}

	public String getServerName() {
		return _portletRequest.getServerName();
	}

	public int getServerPort() {
		return _portletRequest.getServerPort();
	}

	public ServletContext getServletContext() {
		throw new UnsupportedOperationException();
	}

	public String getServletPath() {
		throw new UnsupportedOperationException();
	}

	public HttpSession getSession() {
		if (_portletSession == null) {
			_portletSession = new CDISessionImpl(
				_portletRequest.getPortletSession());
		}

		return _portletSession;
	}

	public HttpSession getSession(boolean create) {
		if (_portletSession == null) {
			PortletSession portletSession = _portletRequest.getPortletSession(
				create);

			if (portletSession != null) {
				_portletSession = new CDISessionImpl(portletSession);
			}
		}

		return _portletSession;
	}

	public Principal getUserPrincipal() {
		return _portletRequest.getUserPrincipal();
	}

	public boolean isAsyncStarted() {
		return false;
	}

	public boolean isAsyncSupported() {
		return false;
	}

	public boolean isRequestedSessionIdFromCookie() {
		return false;
	}

	public boolean isRequestedSessionIdFromUrl() {
		return false;
	}

	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	public boolean isRequestedSessionIdValid() {
		throw new UnsupportedOperationException();
	}

	public boolean isSecure() {
		return _portletRequest.isSecure();
	}

	public boolean isUserInRole(String role) {
		return _portletRequest.isUserInRole(role);
	}

	public void login(String username, String password) {
		throw new UnsupportedOperationException();
	}

	public void logout() {
		throw new UnsupportedOperationException();
	}

	public void removeAttribute(String name) {
		_portletRequest.removeAttribute(name);
	}

	public void setAttribute(String name, Object value) {
		_portletRequest.setAttribute(name, value);
	}

	public void setCharacterEncoding(String encoding)
		throws UnsupportedEncodingException {

		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		clientDataRequest.setCharacterEncoding(encoding);
	}

	public AsyncContext startAsync() {
		throw new UnsupportedOperationException();
	}

	public AsyncContext startAsync(
		ServletRequest servletRequest, ServletResponse servletResponse) {

		throw new UnsupportedOperationException();
	}

	private PortletRequest _portletRequest;
	private CDISession _portletSession;
	private ServletInputStream _servletInputStream;

	private class ServletInputStreamAdapter extends ServletInputStream {

		public ServletInputStreamAdapter(ClientDataRequest clientDataRequest) {
			_clientDataRequest = clientDataRequest;
		}

		@Override
		public int read() throws IOException {
			return _clientDataRequest.getPortletInputStream().read();
		}

		private ClientDataRequest _clientDataRequest;

	}

}