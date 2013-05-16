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

import java.util.Collection;
import java.util.Enumeration;

import javax.portlet.EventRequest;

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
public class CDIEventRequestImpl extends CDIEventRequest {

	public CDIEventRequestImpl(
		EventRequest eventRequest,
		HttpServletRequestAdapter httpServletRequestAdapter) {

		super(eventRequest);

		_httpServletRequestAdapter = httpServletRequestAdapter;
	}

	public boolean authenticate(HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		return _httpServletRequestAdapter.authenticate(httpServletResponse);
	}

	public AsyncContext getAsyncContext() {
		return _httpServletRequestAdapter.getAsyncContext();
	}

	public String getCharacterEncoding() {
		return _httpServletRequestAdapter.getCharacterEncoding();
	}

	public int getContentLength() {
		return _httpServletRequestAdapter.getContentLength();
	}

	public String getContentType() {
		return _httpServletRequestAdapter.getContentType();
	}

	public long getDateHeader(String name) {
		return _httpServletRequestAdapter.getDateHeader(name);
	}

	public DispatcherType getDispatcherType() {
		return _httpServletRequestAdapter.getDispatcherType();
	}

	public String getHeader(String name) {
		return _httpServletRequestAdapter.getHeader(name);
	}

	public Enumeration<String> getHeaderNames() {
		return _httpServletRequestAdapter.getHeaderNames();
	}

	public Enumeration<String> getHeaders(String name) {
		return _httpServletRequestAdapter.getHeaders(name);
	}

	public ServletInputStream getInputStream() throws IOException {
		return _httpServletRequestAdapter.getInputStream();
	}

	public int getIntHeader(String name) {
		return _httpServletRequestAdapter.getIntHeader(name);
	}

	public String getLocalAddr() {
		return _httpServletRequestAdapter.getLocalAddr();
	}

	public String getLocalName() {
		return _httpServletRequestAdapter.getLocalName();
	}

	public int getLocalPort() {
		return _httpServletRequestAdapter.getLocalPort();
	}

	public Part getPart(String name) throws IOException, ServletException {
		return _httpServletRequestAdapter.getPart(name);
	}

	public Collection<Part> getParts() throws IOException, ServletException {
		return _httpServletRequestAdapter.getParts();
	}

	public String getPathInfo() {
		return _httpServletRequestAdapter.getPathInfo();
	}

	public String getPathTranslated() {
		return _httpServletRequestAdapter.getPathTranslated();
	}

	public String getProtocol() {
		return _httpServletRequestAdapter.getProtocol();
	}

	public String getQueryString() {
		return _httpServletRequestAdapter.getQueryString();
	}

	public BufferedReader getReader() throws IOException {
		return _httpServletRequestAdapter.getReader();
	}

	@SuppressWarnings("deprecation")
	public String getRealPath(String path) {
		return _httpServletRequestAdapter.getRealPath(path);
	}

	public String getRemoteAddr() {
		return _httpServletRequestAdapter.getRemoteAddr();
	}

	public String getRemoteHost() {
		return _httpServletRequestAdapter.getRemoteHost();
	}

	public int getRemotePort() {
		return _httpServletRequestAdapter.getRemotePort();
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		return _httpServletRequestAdapter.getRequestDispatcher(path);
	}

	public String getRequestURI() {
		return _httpServletRequestAdapter.getRequestURI();
	}

	public StringBuffer getRequestURL() {
		return _httpServletRequestAdapter.getRequestURL();
	}

	public ServletContext getServletContext() {
		return _httpServletRequestAdapter.getServletContext();
	}

	public String getServletPath() {
		return _httpServletRequestAdapter.getServletPath();
	}

	public HttpSession getSession() {
		return _httpServletRequestAdapter.getSession();
	}

	public HttpSession getSession(boolean create) {
		return _httpServletRequestAdapter.getSession(create);
	}

	public boolean isAsyncStarted() {
		return _httpServletRequestAdapter.isAsyncStarted();
	}

	public boolean isAsyncSupported() {
		return _httpServletRequestAdapter.isAsyncSupported();
	}

	public boolean isRequestedSessionIdFromCookie() {
		return _httpServletRequestAdapter.isRequestedSessionIdFromCookie();
	}

	@SuppressWarnings("deprecation")
	public boolean isRequestedSessionIdFromUrl() {
		return _httpServletRequestAdapter.isRequestedSessionIdFromUrl();
	}

	public boolean isRequestedSessionIdFromURL() {
		return _httpServletRequestAdapter.isRequestedSessionIdFromURL();
	}

	public void login(String username, String password)
		throws ServletException {

		_httpServletRequestAdapter.login(username, password);
	}

	public void logout() throws ServletException {
		_httpServletRequestAdapter.logout();
	}

	public void setCharacterEncoding(String encoding)
		throws UnsupportedEncodingException {

		_httpServletRequestAdapter.setCharacterEncoding(encoding);
	}

	public AsyncContext startAsync() throws IllegalStateException {
		return _httpServletRequestAdapter.startAsync();
	}

	public AsyncContext startAsync(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IllegalStateException {

		return _httpServletRequestAdapter.startAsync(
			servletRequest, servletResponse);
	}

	private HttpServletRequestAdapter _httpServletRequestAdapter;

}