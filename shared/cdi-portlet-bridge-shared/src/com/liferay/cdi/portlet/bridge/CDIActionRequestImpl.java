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

package com.liferay.cdi.portlet.bridge;

import java.io.IOException;

import java.util.Collection;
import java.util.Enumeration;

import javax.portlet.ActionRequest;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * @author Neil Griffin
 */
public class CDIActionRequestImpl extends CDIActionRequest {

	public CDIActionRequestImpl(
		ActionRequest actionRequest,
		HttpServletRequestAdapter httpServletRequestAdapter) {

		super(actionRequest);

		_httpServletRequestAdapter = httpServletRequestAdapter;
	}

	@Override
	public boolean authenticate(HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		return _httpServletRequestAdapter.authenticate(httpServletResponse);
	}

	@Override
	public AsyncContext getAsyncContext() {
		return _httpServletRequestAdapter.getAsyncContext();
	}

	@Override
	public long getDateHeader(String name) {
		return _httpServletRequestAdapter.getDateHeader(name);
	}

	@Override
	public DispatcherType getDispatcherType() {
		return _httpServletRequestAdapter.getDispatcherType();
	}

	@Override
	public String getHeader(String name) {
		return _httpServletRequestAdapter.getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		return _httpServletRequestAdapter.getHeaderNames();
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		return _httpServletRequestAdapter.getHeaders(name);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return _httpServletRequestAdapter.getInputStream();
	}

	@Override
	public int getIntHeader(String name) {
		return _httpServletRequestAdapter.getIntHeader(name);
	}

	@Override
	public String getLocalAddr() {
		return _httpServletRequestAdapter.getLocalAddr();
	}

	@Override
	public String getLocalName() {
		return _httpServletRequestAdapter.getLocalName();
	}

	@Override
	public int getLocalPort() {
		return _httpServletRequestAdapter.getLocalPort();
	}

	@Override
	public Part getPart(String name) throws IOException, ServletException {
		return _httpServletRequestAdapter.getPart(name);
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		return _httpServletRequestAdapter.getParts();
	}

	@Override
	public String getPathInfo() {
		return _httpServletRequestAdapter.getPathInfo();
	}

	@Override
	public String getPathTranslated() {
		return _httpServletRequestAdapter.getPathTranslated();
	}

	@Override
	public String getProtocol() {
		return _httpServletRequestAdapter.getProtocol();
	}

	@Override
	public String getQueryString() {
		return _httpServletRequestAdapter.getQueryString();
	}

	@Override
	@SuppressWarnings("deprecation")
	public String getRealPath(String path) {
		return _httpServletRequestAdapter.getRealPath(path);
	}

	@Override
	public String getRemoteAddr() {
		return _httpServletRequestAdapter.getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {
		return _httpServletRequestAdapter.getRemoteHost();
	}

	@Override
	public int getRemotePort() {
		return _httpServletRequestAdapter.getRemotePort();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return _httpServletRequestAdapter.getRequestDispatcher(path);
	}

	@Override
	public String getRequestURI() {
		return _httpServletRequestAdapter.getRequestURI();
	}

	@Override
	public StringBuffer getRequestURL() {
		return _httpServletRequestAdapter.getRequestURL();
	}

	@Override
	public ServletContext getServletContext() {
		return _httpServletRequestAdapter.getServletContext();
	}

	@Override
	public String getServletPath() {
		return _httpServletRequestAdapter.getServletPath();
	}

	@Override
	public HttpSession getSession() {
		return _httpServletRequestAdapter.getSession();
	}

	@Override
	public HttpSession getSession(boolean create) {
		return _httpServletRequestAdapter.getSession(create);
	}

	@Override
	public boolean isAsyncStarted() {
		return _httpServletRequestAdapter.isAsyncStarted();
	}

	@Override
	public boolean isAsyncSupported() {
		return _httpServletRequestAdapter.isAsyncSupported();
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return _httpServletRequestAdapter.isRequestedSessionIdFromCookie();
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return _httpServletRequestAdapter.isRequestedSessionIdFromURL();
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		return _httpServletRequestAdapter.isRequestedSessionIdFromURL();
	}

	@Override
	public void login(String username, String password)
		throws ServletException {

		_httpServletRequestAdapter.login(username, password);
	}

	@Override
	public void logout() throws ServletException {
		_httpServletRequestAdapter.logout();
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return _httpServletRequestAdapter.startAsync();
	}

	@Override
	public AsyncContext startAsync(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IllegalStateException {

		return _httpServletRequestAdapter.startAsync(
			servletRequest, servletResponse);
	}

	private HttpServletRequestAdapter _httpServletRequestAdapter;

}