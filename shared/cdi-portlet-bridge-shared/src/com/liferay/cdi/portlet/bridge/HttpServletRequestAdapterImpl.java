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

	@Override
	public boolean authenticate(HttpServletResponse httpServletResponse) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext getAsyncContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getAttribute(String name) {
		return _portletRequest.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return _portletRequest.getAttributeNames();
	}

	@Override
	public String getAuthType() {
		return _portletRequest.getAuthType();
	}

	@Override
	public String getCharacterEncoding() {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getCharacterEncoding();
	}

	@Override
	public int getContentLength() {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getContentLength();
	}

	@Override
	public String getContentType() {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getContentType();
	}

	@Override
	public String getContextPath() {
		return _portletRequest.getContextPath();
	}

	@Override
	public Cookie[] getCookies() {
		return _portletRequest.getCookies();
	}

	@Override
	public long getDateHeader(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DispatcherType getDispatcherType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getHeader(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
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

	@Override
	public int getIntHeader(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLocalAddr() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Locale getLocale() {
		return _portletRequest.getLocale();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return _portletRequest.getLocales();
	}

	@Override
	public String getLocalName() {
		Locale locale = _portletRequest.getLocale();

		return locale.getDisplayName();
	}

	@Override
	public int getLocalPort() {
		return _portletRequest.getServerPort();
	}

	@Override
	public String getMethod() {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getMethod();
	}

	@Override
	public String getParameter(String name) {
		return _portletRequest.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return _portletRequest.getParameterMap();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return _portletRequest.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {
		return _portletRequest.getParameterValues(name);
	}

	@Override
	public Part getPart(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Part> getParts() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPathInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPathTranslated() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PortletRequest getPortletRequest() {
		return _portletRequest;
	}

	@Override
	public String getProtocol() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getQueryString() {
		throw new UnsupportedOperationException();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		return clientDataRequest.getReader();
	}

	@Override
	public String getRealPath(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRemoteAddr() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRemoteHost() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getRemotePort() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRemoteUser() {
		return _portletRequest.getRemoteUser();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRequestedSessionId() {
		return _portletRequest.getRequestedSessionId();
	}

	@Override
	public String getRequestURI() {
		throw new UnsupportedOperationException();
	}

	@Override
	public StringBuffer getRequestURL() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getScheme() {
		return _portletRequest.getScheme();
	}

	@Override
	public String getServerName() {
		return _portletRequest.getServerName();
	}

	@Override
	public int getServerPort() {
		return _portletRequest.getServerPort();
	}

	@Override
	public ServletContext getServletContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getServletPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public HttpSession getSession() {
		if (_portletSession == null) {
			_portletSession = new CDISessionImpl(
				_portletRequest.getPortletSession());
		}

		return _portletSession;
	}

	@Override
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

	@Override
	public Principal getUserPrincipal() {
		return _portletRequest.getUserPrincipal();
	}

	@Override
	public boolean isAsyncStarted() {
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSecure() {
		return _portletRequest.isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {
		return _portletRequest.isUserInRole(role);
	}

	@Override
	public void login(String username, String password) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void logout() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeAttribute(String name) {
		_portletRequest.removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		_portletRequest.setAttribute(name, value);
	}

	@Override
	public void setCharacterEncoding(String encoding)
		throws UnsupportedEncodingException {

		if (!(_portletRequest instanceof ClientDataRequest)) {
			throw new UnsupportedOperationException();
		}

		ClientDataRequest clientDataRequest =
			(ClientDataRequest)_portletRequest;

		clientDataRequest.setCharacterEncoding(encoding);
	}

	@Override
	public AsyncContext startAsync() {
		throw new UnsupportedOperationException();
	}

	@Override
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