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

import javax.portlet.ResourceResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

/**
 * @author Neil Griffin
 */
public class CDIResourceResponseImpl extends CDIResourceResponse {

	public CDIResourceResponseImpl(
		ResourceResponse resourceResponse,
		HttpServletResponseAdapter httpServletResponseAdapter) {

		super(resourceResponse);

		_httpServletResponseAdapter = httpServletResponseAdapter;
	}

	@Override
	public void addCookie(Cookie cookie) {
		_httpServletResponseAdapter.addCookie(cookie);
	}

	@Override
	public void addDateHeader(String name, long value) {
		_httpServletResponseAdapter.addDateHeader(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		_httpServletResponseAdapter.addHeader(name, value);
	}

	@Override
	public void addIntHeader(String name, int value) {
		_httpServletResponseAdapter.addIntHeader(name, value);
	}

	@Override
	public boolean containsHeader(String name) {
		return _httpServletResponseAdapter.containsHeader(name);
	}

	@Override
	@SuppressWarnings("deprecation")
	public String encodeRedirectUrl(String url) {
		return _httpServletResponseAdapter.encodeRedirectUrl(url);
	}

	@Override
	public String encodeRedirectURL(String url) {
		return _httpServletResponseAdapter.encodeRedirectURL(url);
	}

	@Override
	@SuppressWarnings("deprecation")
	public String encodeUrl(String url) {
		return _httpServletResponseAdapter.encodeUrl(url);
	}

	@Override
	public String getHeader(String name) {
		return _httpServletResponseAdapter.getHeader(name);
	}

	@Override
	public Collection<String> getHeaderNames() {
		return _httpServletResponseAdapter.getHeaderNames();
	}

	@Override
	public Collection<String> getHeaders(String name) {
		return _httpServletResponseAdapter.getHeaders(name);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return _httpServletResponseAdapter.getOutputStream();
	}

	@Override
	public int getStatus() {
		return _httpServletResponseAdapter.getStatus();
	}

	@Override
	public void sendError(int status) throws IOException {
		_httpServletResponseAdapter.sendError(status);
	}

	@Override
	public void sendError(int status, String message) throws IOException {
		_httpServletResponseAdapter.sendError(status, message);
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		_httpServletResponseAdapter.sendRedirect(location);
	}

	@Override
	public void setDateHeader(String name, long value) {
		_httpServletResponseAdapter.setDateHeader(name, value);
	}

	@Override
	public void setHeader(String name, String value) {
		_httpServletResponseAdapter.setHeader(name, value);
	}

	@Override
	public void setIntHeader(String name, int value) {
		_httpServletResponseAdapter.setIntHeader(name, value);
	}

	@Override
	public void setStatus(int status) {
		_httpServletResponseAdapter.setStatus(status);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void setStatus(int status, String message) {
		_httpServletResponseAdapter.setStatus(status, message);
	}

	private HttpServletResponseAdapter _httpServletResponseAdapter;

}