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

package com.liferay.alloy.mvc.jsonwebservice;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.el.ELContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.CacheControl;
import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;
import javax.portlet.WindowState;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.Part;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

import javax.xml.namespace.QName;

import org.w3c.dom.Element;

/**
 * @author Ethan Bustad
 */
public class AlloyMockUtil {

	public static class MockActionRequest
		extends MockPortletRequest implements ActionRequest {

		@Override
		public String getCharacterEncoding() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getContentLength() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getContentType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getMethod() {
			throw new UnsupportedOperationException();
		}

		@Override
		public InputStream getPortletInputStream() {
			throw new UnsupportedOperationException();
		}

		@Override
		public BufferedReader getReader() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCharacterEncoding(String characterEncoding) {
			throw new UnsupportedOperationException();
		}

	}

	public static class MockActionResponse
		extends MockPortletResponse implements ActionResponse {

		@Override
		public PortletMode getPortletMode() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<String, String[]> getRenderParameterMap() {
			throw new UnsupportedOperationException();
		}

		@Override
		public WindowState getWindowState() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void removePublicRenderParameter(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void sendRedirect(String location) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void sendRedirect(String location, String renderUrlParamName) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setEvent(QName name, Serializable value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setEvent(String name, Serializable value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPortletMode(PortletMode portletMode) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRenderParameter(String key, String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRenderParameter(String key, String[] values) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRenderParameters(Map<String, String[]> parameters) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setWindowState(WindowState windowState) {
			throw new UnsupportedOperationException();
		}

	}

	public static class MockHttpServletRequest implements HttpServletRequest {

		@Override
		public boolean authenticate(HttpServletResponse response) {
			throw new UnsupportedOperationException();
		}

		@Override
		public AsyncContext getAsyncContext() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object getAttribute(String name) {
			return attributeMap.get(name);
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getAuthType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getCharacterEncoding() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getContentLength() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getContentType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getContextPath() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Cookie[] getCookies() {
			return null;
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
			return null;
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
			throw new UnsupportedOperationException();
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
			throw new UnsupportedOperationException();
		}

		@Override
		public Enumeration<Locale> getLocales() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getLocalName() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getLocalPort() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getMethod() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getParameter(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Enumeration<String> getParameterNames() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String[] getParameterValues(String name) {
			throw new UnsupportedOperationException();
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
		public String getProtocol() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getQueryString() {
			throw new UnsupportedOperationException();
		}

		@Override
		public BufferedReader getReader() {
			throw new UnsupportedOperationException();
		}

		@Deprecated
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
			throw new UnsupportedOperationException();
		}

		@Override
		public RequestDispatcher getRequestDispatcher(String path) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getRequestedSessionId() {
			throw new UnsupportedOperationException();
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
			throw new UnsupportedOperationException();
		}

		@Override
		public String getServerName() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getServerPort() {
			throw new UnsupportedOperationException();
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
			return new MockHttpSession();
		}

		@Override
		public HttpSession getSession(boolean create) {
			return new MockHttpSession();
		}

		@Override
		public java.security.Principal getUserPrincipal() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isAsyncStarted() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isAsyncSupported() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isRequestedSessionIdFromCookie() {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public boolean isRequestedSessionIdFromUrl() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isRequestedSessionIdFromURL() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isRequestedSessionIdValid() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isSecure() {
			return true;
		}

		@Override
		public boolean isUserInRole(String role) {
			throw new UnsupportedOperationException();
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
			attributeMap.remove(name);
		}

		@Override
		public void setAttribute(String name, Object value) {
			attributeMap.put(name, value);
		}

		@Override
		public void setCharacterEncoding(String characterEncoding) {
			throw new UnsupportedOperationException();
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

		protected Map<String, Object> attributeMap = new HashMap<>();

	}

	public static class MockHttpServletResponse implements HttpServletResponse {

		@Override
		public void addCookie(Cookie cookie) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addDateHeader(String name, long date) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addHeader(String name, String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addIntHeader(String name, int value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsHeader(String name) {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public String encodeRedirectUrl(String url) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String encodeRedirectURL(String url) {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public String encodeUrl(String url) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String encodeURL(String url) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void flushBuffer() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getBufferSize() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getCharacterEncoding() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getContentType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getHeader(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Collection<String> getHeaderNames() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Collection<String> getHeaders(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Locale getLocale() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ServletOutputStream getOutputStream() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getStatus() {
			throw new UnsupportedOperationException();
		}

		@Override
		public PrintWriter getWriter() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isCommitted() {
			return true;
		}

		@Override
		public void reset() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void resetBuffer() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void sendError(int status) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void sendError(int status, String message) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void sendRedirect(String location) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBufferSize(int size) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCharacterEncoding(String characterEncoding) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setContentLength(int contentLength) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setContentType(String type) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDateHeader(String name, long date) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHeader(String name, String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIntHeader(String name, int value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLocale(Locale locale) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStatus(int status) {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public void setStatus(int status, String message) {
			throw new UnsupportedOperationException();
		}

	}

	public static class MockHttpSession implements HttpSession {

		@Override
		public Object getAttribute(String name) {
			return attributeMap.get(name);
		}

		@Override
		public java.util.Enumeration<String> getAttributeNames() {
			throw new UnsupportedOperationException();
		}

		@Override
		public long getCreationTime() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getId() {
			throw new UnsupportedOperationException();
		}

		@Override
		public long getLastAccessedTime() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getMaxInactiveInterval() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ServletContext getServletContext() {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public HttpSessionContext getSessionContext() {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public Object getValue(String name) {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public String[] getValueNames() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void invalidate() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isNew() {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public void putValue(String name, Object value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void removeAttribute(String name) {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public void removeValue(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAttribute(String name, Object value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMaxInactiveInterval(int interval) {
			throw new UnsupportedOperationException();
		}

		protected Map<String, Object> attributeMap = new HashMap<>();

	}

	public static class MockPageContext extends PageContext {

		@Override
		public Object findAttribute(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void forward(String relativeUrlPath) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object getAttribute(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object getAttribute(String name, int scope) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Enumeration<String> getAttributeNamesInScope(int scope) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getAttributesScope(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public ELContext getELContext() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Exception getException() {
			throw new UnsupportedOperationException();
		}

		@Deprecated
		@Override
		public ExpressionEvaluator getExpressionEvaluator() {
			throw new UnsupportedOperationException();
		}

		@Override
		public JspWriter getOut() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object getPage() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ServletRequest getRequest() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ServletResponse getResponse() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ServletConfig getServletConfig() {
			return null;
		}

		@Override
		public ServletContext getServletContext() {
			return null;
		}

		@Override
		public HttpSession getSession() {
			return new MockHttpSession();
		}

		@Deprecated
		@Override
		public VariableResolver getVariableResolver() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void handlePageException(Exception e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void handlePageException(Throwable t) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void include(String relativeUrlPath) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void include(String relativeUrlPath, boolean flush) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void initialize(
			Servlet servlet, ServletRequest request, ServletResponse response,
			String errorPageURL, boolean needsSession, int bufferSize,
			boolean autoFlush) {

			throw new UnsupportedOperationException();
		}

		@Override
		public void release() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void removeAttribute(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void removeAttribute(String name, int scope) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAttribute(String name, Object value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAttribute(String name, Object value, int scope) {
			throw new UnsupportedOperationException();
		}

	}

	public static class MockPortletRequest implements PortletRequest {

		@Override
		public Object getAttribute(String name) {
			return attributeMap.get(name);
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getAuthType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getContextPath() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Cookie[] getCookies() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Locale getLocale() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Enumeration<Locale> getLocales() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getParameter(String name) {
			if (!parameterMap.containsKey(name)) {
				return null;
			}

			String[] values = parameterMap.get(name);

			if (values.length == 0) {
				return null;
			}

			return values[0];
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			return new HashMap<>(parameterMap);
		}

		@Override
		public Enumeration<String> getParameterNames() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String[] getParameterValues(String name) {
			return parameterMap.get(name);
		}

		@Override
		public PortalContext getPortalContext() {
			throw new UnsupportedOperationException();
		}

		@Override
		public PortletMode getPortletMode() {
			throw new UnsupportedOperationException();
		}

		@Override
		public PortletSession getPortletSession() {
			throw new UnsupportedOperationException();
		}

		@Override
		public PortletSession getPortletSession(boolean create) {
			throw new UnsupportedOperationException();
		}

		@Override
		public PortletPreferences getPreferences() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<String, String[]> getPrivateParameterMap() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Enumeration<String> getProperties(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getProperty(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Enumeration<String> getPropertyNames() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<String, String[]> getPublicParameterMap() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getRemoteUser() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getRequestedSessionId() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getResponseContentType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Enumeration<String> getResponseContentTypes() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getScheme() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getServerName() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getServerPort() {
			throw new UnsupportedOperationException();
		}

		@Override
		public java.security.Principal getUserPrincipal() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getWindowID() {
			throw new UnsupportedOperationException();
		}

		@Override
		public WindowState getWindowState() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isPortletModeAllowed(PortletMode mode) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isRequestedSessionIdValid() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isSecure() {
			return true;
		}

		@Override
		public boolean isUserInRole(String role) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isWindowStateAllowed(WindowState state) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void removeAttribute(String name) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAttribute(String name, Object o) {
			attributeMap.put(name, o);
		}

		protected Map<String, Object> attributeMap = new HashMap<>();
		protected Map<String, String[]> parameterMap = new HashMap<>();

	}

	public static class MockPortletResponse implements LiferayPortletResponse {

		@Override
		public void addDateHeader(String name, long date) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addHeader(String name, String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addIntHeader(String name, int value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addProperty(Cookie cookie) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addProperty(String key, Element element) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addProperty(String key, String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public PortletURL createActionURL() {
			throw new UnsupportedOperationException();
		}

		@Override
		public LiferayPortletURL createActionURL(String portletName) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Element createElement(String tagName) {
			throw new UnsupportedOperationException();
		}

		@Override
		public LiferayPortletURL createLiferayPortletURL(
			long plid, String portletName, String lifecycle) {

			throw new UnsupportedOperationException();
		}

		@Override
		public LiferayPortletURL createLiferayPortletURL(
			long plid, String portletName, String lifecycle,
			boolean includeLinkToLayoutUuid) {

			throw new UnsupportedOperationException();
		}

		@Override
		public LiferayPortletURL createLiferayPortletURL(String lifecycle) {
			throw new UnsupportedOperationException();
		}

		@Override
		public LiferayPortletURL createLiferayPortletURL(
			String portletName, String lifecycle) {

			throw new UnsupportedOperationException();
		}

		@Override
		public PortletURL createRenderURL() {
			throw new UnsupportedOperationException();
		}

		@Override
		public LiferayPortletURL createRenderURL(String portletName) {
			throw new UnsupportedOperationException();
		}

		@Override
		public ResourceURL createResourceURL() {
			throw new UnsupportedOperationException();
		}

		@Override
		public LiferayPortletURL createResourceURL(String portletName) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String encodeURL(String path) {
			throw new UnsupportedOperationException();
		}

		@Override
		public HttpServletResponse getHttpServletResponse() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getNamespace() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Portlet getPortlet() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<String, String[]> getProperties() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDateHeader(String name, long date) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHeader(String name, String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIntHeader(String name, int value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setProperty(String key, String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void transferMarkupHeadElements() {
			throw new UnsupportedOperationException();
		}

	}

	public static class MockRenderRequest
		extends MockPortletRequest implements RenderRequest {

		@Override
		public String getETag() {
			throw new UnsupportedOperationException();
		}

	}

	public static class MockRenderResponse
		extends MockPortletResponse implements RenderResponse {

		@Override
		public PortletURL createActionURL() {
			throw new UnsupportedOperationException();
		}

		@Override
		public PortletURL createRenderURL() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ResourceURL createResourceURL() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void flushBuffer() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getBufferSize() {
			throw new UnsupportedOperationException();
		}

		@Override
		public CacheControl getCacheControl() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getCharacterEncoding() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getContentType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public java.util.Locale getLocale() {
			throw new UnsupportedOperationException();
		}

		@Override
		public OutputStream getPortletOutputStream() {
			throw new UnsupportedOperationException();
		}

		@Override
		public PrintWriter getWriter() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isCommitted() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void reset() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void resetBuffer() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setBufferSize(int size) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setContentType(String type) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNextPossiblePortletModes(
			Collection<PortletMode> portletModes) {

			throw new UnsupportedOperationException();
		}

		@Override
		public void setTitle(String title) {
			throw new UnsupportedOperationException();
		}

	}

}