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
import javax.servlet.ServletException;
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
	public boolean authenticate(HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext getAsyncContext() {

		throw new UnsupportedOperationException();
	}

	@Override
	public Object getAttribute(String name) {

		return getPortletRequest().getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {

		return getPortletRequest().getAttributeNames();
	}

	@Override
	public String getAuthType() {

		return getPortletRequest().getAuthType();
	}

	@Override
	public String getCharacterEncoding() {

		PortletRequest wrappedPortletRequest = getPortletRequest();

		if (wrappedPortletRequest instanceof ClientDataRequest) {

			ClientDataRequest clientDataRequest =
				(ClientDataRequest)wrappedPortletRequest;

			return clientDataRequest.getCharacterEncoding();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public int getContentLength() {

		PortletRequest wrappedPortletRequest = getPortletRequest();

		if (wrappedPortletRequest instanceof ClientDataRequest) {

			ClientDataRequest clientDataRequest =
				(ClientDataRequest)wrappedPortletRequest;

			return clientDataRequest.getContentLength();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public String getContentType() {

		PortletRequest wrappedPortletRequest = getPortletRequest();

		if (wrappedPortletRequest instanceof ClientDataRequest) {

			ClientDataRequest clientDataRequest =
				(ClientDataRequest)wrappedPortletRequest;

			return clientDataRequest.getContentType();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public String getContextPath() {

		return getPortletRequest().getContextPath();
	}

	@Override
	public Cookie[] getCookies() {

		return getPortletRequest().getCookies();
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
	public ServletInputStream getInputStream() throws IOException {

		if (_servletInputStream == null) {

			PortletRequest wrappedPortletRequest = getPortletRequest();

			if (wrappedPortletRequest instanceof ClientDataRequest) {
				ClientDataRequest clientDataRequest =
					(ClientDataRequest)wrappedPortletRequest;
				_servletInputStream = new ServletInputStreamAdapter(
					clientDataRequest);
			}
			else {
				throw new UnsupportedOperationException();
			}
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

		return getPortletRequest().getLocale();
	}

	@Override
	public Enumeration<Locale> getLocales() {

		return getPortletRequest().getLocales();
	}

	@Override
	public String getLocalName() {

		return getPortletRequest().getLocale().getDisplayName();
	}

	@Override
	public int getLocalPort() {

		return getPortletRequest().getServerPort();
	}

	@Override
	public String getMethod() {

		PortletRequest wrappedPortletRequest = getPortletRequest();

		if (wrappedPortletRequest instanceof ClientDataRequest) {
			ClientDataRequest clientDataRequest =
				(ClientDataRequest)wrappedPortletRequest;

			return clientDataRequest.getMethod();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public String getParameter(String name) {

		return getPortletRequest().getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {

		return getPortletRequest().getParameterMap();
	}

	@Override
	public Enumeration<String> getParameterNames() {

		return getPortletRequest().getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {

		return getPortletRequest().getParameterValues(name);
	}

	@Override
	public Part getPart(String name) throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
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
		PortletRequest wrappedPortletRequest = getPortletRequest();

		if (wrappedPortletRequest instanceof ClientDataRequest) {

			ClientDataRequest clientDataRequest =
				(ClientDataRequest)wrappedPortletRequest;

			return clientDataRequest.getReader();
		}
		else {
			throw new UnsupportedOperationException();
		}
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

		return getPortletRequest().getRemoteUser();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {

		throw new UnsupportedOperationException();
	}

	@Override
	public String getRequestedSessionId() {

		return getPortletRequest().getRequestedSessionId();
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

		return getPortletRequest().getScheme();
	}

	@Override
	public String getServerName() {

		return getPortletRequest().getServerName();
	}

	@Override
	public int getServerPort() {

		return getPortletRequest().getServerPort();
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
	public HttpSession getSession(boolean create) {

		if (_cdiSession == null) {
			PortletSession portletSession =
				getPortletRequest().getPortletSession(create);

			if (portletSession != null) {
				_cdiSession = new CDISessionImpl(portletSession);
			}
		}

		return _cdiSession;
	}

	@Override
	public HttpSession getSession() {

		if (_cdiSession == null) {
			PortletSession portletSession =
				getPortletRequest().getPortletSession();
			_cdiSession = new CDISessionImpl(portletSession);
		}

		return _cdiSession;
	}

	@Override
	public Principal getUserPrincipal() {

		return getPortletRequest().getUserPrincipal();
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

		return getPortletRequest().isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {

		return getPortletRequest().isUserInRole(role);
	}

	@Override
	public void login(String username, String password)
		throws ServletException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void logout() throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeAttribute(String name) {

		getPortletRequest().removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object value) {

		getPortletRequest().setAttribute(name, value);
	}

	@Override
	public void setCharacterEncoding(String enc)
		throws UnsupportedEncodingException {

		PortletRequest wrappedPortletRequest = getPortletRequest();

		if (wrappedPortletRequest instanceof ClientDataRequest) {

			ClientDataRequest clientDataRequest =
				(ClientDataRequest)wrappedPortletRequest;

			clientDataRequest.setCharacterEncoding(enc);
		}
		else {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public AsyncContext startAsync(
		ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {

		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		throw new UnsupportedOperationException();
	}

	public class ServletInputStreamAdapter extends ServletInputStream {

		public ServletInputStreamAdapter(ClientDataRequest clientDataRequest) {

			_clientDataRequest = clientDataRequest;
		}

		@Override
		public int read() throws IOException {
			return _clientDataRequest.getPortletInputStream().read();
		}

		private ClientDataRequest _clientDataRequest;

	}

	private CDISession _cdiSession;
	private PortletRequest _portletRequest;
	private ServletInputStream _servletInputStream;

}