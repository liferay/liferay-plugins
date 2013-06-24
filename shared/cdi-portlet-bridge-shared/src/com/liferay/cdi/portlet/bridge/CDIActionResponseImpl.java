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

	public void addCookie(Cookie cookie) {
		_httpServletResponseAdapter.addCookie(cookie);
	}

	public void addDateHeader(String name, long value) {
		_httpServletResponseAdapter.addDateHeader(name, value);
	}

	public void addHeader(String name, String value) {
		_httpServletResponseAdapter.addHeader(name, value);
	}

	public void addIntHeader(String name, int value) {
		_httpServletResponseAdapter.addIntHeader(name, value);
	}

	public boolean containsHeader(String name) {
		return _httpServletResponseAdapter.containsHeader(name);
	}

	@SuppressWarnings("deprecation")
	public String encodeRedirectUrl(String url) {
		return _httpServletResponseAdapter.encodeRedirectUrl(url);
	}

	public String encodeRedirectURL(String url) {
		return _httpServletResponseAdapter.encodeRedirectURL(url);
	}

	@SuppressWarnings("deprecation")
	public String encodeUrl(String url) {
		return _httpServletResponseAdapter.encodeUrl(url);
	}

	public void flushBuffer() throws IOException {
		_httpServletResponseAdapter.flushBuffer();
	}

	public int getBufferSize() {
		return _httpServletResponseAdapter.getBufferSize();
	}

	public String getCharacterEncoding() {
		return _httpServletResponseAdapter.getCharacterEncoding();
	}

	public String getContentType() {
		return _httpServletResponseAdapter.getContentType();
	}

	public String getHeader(String name) {
		return _httpServletResponseAdapter.getHeader(name);
	}

	public Collection<String> getHeaderNames() {
		return _httpServletResponseAdapter.getHeaderNames();
	}

	public Collection<String> getHeaders(String name) {
		return _httpServletResponseAdapter.getHeaders(name);
	}

	public Locale getLocale() {
		return _httpServletResponseAdapter.getLocale();
	}

	public ServletOutputStream getOutputStream() throws IOException {
		return _httpServletResponseAdapter.getOutputStream();
	}

	public int getStatus() {
		return _httpServletResponseAdapter.getStatus();
	}

	public PrintWriter getWriter() throws IOException {
		return _httpServletResponseAdapter.getWriter();
	}

	public boolean isCommitted() {
		return _httpServletResponseAdapter.isCommitted();
	}

	public void reset() {
		_httpServletResponseAdapter.reset();
	}

	public void resetBuffer() {
		_httpServletResponseAdapter.resetBuffer();
	}

	public void sendError(int status) throws IOException {
		_httpServletResponseAdapter.sendError(status);
	}

	public void sendError(int status, String message) throws IOException {
		_httpServletResponseAdapter.sendError(status, message);
	}

	public void setBufferSize(int bufferSize) {
		_httpServletResponseAdapter.setBufferSize(bufferSize);
	}

	public void setCharacterEncoding(String encoding) {
		_httpServletResponseAdapter.setCharacterEncoding(encoding);
	}

	public void setContentLength(int contentLength) {
		_httpServletResponseAdapter.setContentLength(contentLength);
	}

	public void setContentType(String contentType) {
		_httpServletResponseAdapter.setContentType(contentType);
	}

	public void setDateHeader(String name, long value) {
		_httpServletResponseAdapter.setDateHeader(name, value);
	}

	public void setHeader(String name, String value) {
		_httpServletResponseAdapter.setHeader(name, value);
	}

	public void setIntHeader(String name, int value) {
		_httpServletResponseAdapter.setIntHeader(name, value);
	}

	public void setLocale(Locale locale) {
		_httpServletResponseAdapter.setLocale(locale);
	}

	public void setStatus(int status) {
		_httpServletResponseAdapter.setStatus(status);
	}

	@SuppressWarnings("deprecation")
	public void setStatus(int status, String message) {
		_httpServletResponseAdapter.setStatus(status, message);
	}

	private HttpServletResponseAdapter _httpServletResponseAdapter;

}