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
import java.io.PrintWriter;

import java.util.Collection;
import java.util.Locale;

import javax.portlet.ActionResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

/**
 * @author Neil Griffin
 */
public class CDIActionResponseImpl extends CDIActionResponse {

	public CDIActionResponseImpl(
		ActionResponse actionResponse,
		HttpServletResponseAdapter httpServletResponseAdapter) {

		super(actionResponse);

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
	public void flushBuffer() throws IOException {
		_httpServletResponseAdapter.flushBuffer();
	}

	@Override
	public int getBufferSize() {
		return _httpServletResponseAdapter.getBufferSize();
	}

	@Override
	public String getCharacterEncoding() {
		return _httpServletResponseAdapter.getCharacterEncoding();
	}

	@Override
	public String getContentType() {
		return _httpServletResponseAdapter.getContentType();
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
	public Locale getLocale() {
		return _httpServletResponseAdapter.getLocale();
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
	public PrintWriter getWriter() throws IOException {
		return _httpServletResponseAdapter.getWriter();
	}

	@Override
	public boolean isCommitted() {
		return _httpServletResponseAdapter.isCommitted();
	}

	@Override
	public void reset() {
		_httpServletResponseAdapter.reset();
	}

	@Override
	public void resetBuffer() {
		_httpServletResponseAdapter.resetBuffer();
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
	public void setBufferSize(int bufferSize) {
		_httpServletResponseAdapter.setBufferSize(bufferSize);
	}

	@Override
	public void setCharacterEncoding(String encoding) {
		_httpServletResponseAdapter.setCharacterEncoding(encoding);
	}

	@Override
	public void setContentLength(int contentLength) {
		_httpServletResponseAdapter.setContentLength(contentLength);
	}

	@Override
	public void setContentType(String contentType) {
		_httpServletResponseAdapter.setContentType(contentType);
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
	public void setLocale(Locale locale) {
		_httpServletResponseAdapter.setLocale(locale);
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