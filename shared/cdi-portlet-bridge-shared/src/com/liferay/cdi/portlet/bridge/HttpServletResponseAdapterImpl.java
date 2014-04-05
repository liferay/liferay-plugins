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
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.Collection;
import java.util.Locale;

import javax.portlet.ClientDataRequest;
import javax.portlet.MimeResponse;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

/**
 * @author Neil Griffin
 */
public class HttpServletResponseAdapterImpl
	implements HttpServletResponseAdapter {

	public HttpServletResponseAdapterImpl(
		PortletResponse portletResponse, Locale locale) {

		_locale = locale;
		_portletResponse = portletResponse;
	}

	@Override
	public void addCookie(Cookie cookie) {
		_portletResponse.addProperty(cookie);
	}

	@Override
	public void addDateHeader(String name, long value) {
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

	@Override
	public String encodeRedirectUrl(String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeRedirectURL(String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeUrl(String url) {
		return getPortletResponse().encodeURL(url);
	}

	@Override
	public String encodeURL(String url) {
		return getPortletResponse().encodeURL(url);
	}

	@Override
	public void flushBuffer() throws IOException {
		if (!(_portletResponse instanceof MimeResponse)) {
			throw new UnsupportedOperationException();
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		mimeResponse.flushBuffer();
	}

	@Override
	public int getBufferSize() {
		if (!(_portletResponse instanceof ClientDataRequest)) {
			return 0;
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		return mimeResponse.getBufferSize();
	}

	@Override
	public String getCharacterEncoding() {
		if (!(_portletResponse instanceof MimeResponse)) {
			return null;
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		return mimeResponse.getCharacterEncoding();
	}

	@Override
	public String getContentType() {
		if (!(_portletResponse instanceof MimeResponse)) {
			return null;
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		return mimeResponse.getContentType();
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
		return _locale;
	}

	@Override
	public ServletOutputStream getOutputStream() {
		if (_servletOutputStream == null) {
			if (!(_portletResponse instanceof MimeResponse)) {
				throw new UnsupportedOperationException();
			}

			_servletOutputStream = new ServletOutputStreamAdapter(
				(MimeResponse)_portletResponse);
		}

		return _servletOutputStream;
	}

	@Override
	public PortletResponse getPortletResponse() {
		return _portletResponse;
	}

	@Override
	public int getStatus() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (!(_portletResponse instanceof MimeResponse)) {
			throw new UnsupportedOperationException();
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		return mimeResponse.getWriter();
	}

	@Override
	public boolean isCommitted() {
		if (!(_portletResponse instanceof MimeResponse)) {
			return true;
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		return mimeResponse.isCommitted();
	}

	@Override
	public void reset() {
		if (!(_portletResponse instanceof MimeResponse)) {
			throw new UnsupportedOperationException();
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		mimeResponse.reset();
	}

	@Override
	public void resetBuffer() {
		if (!(_portletResponse instanceof MimeResponse)) {
			throw new UnsupportedOperationException();
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		mimeResponse.resetBuffer();
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
	public void setBufferSize(int bufferSize) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCharacterEncoding(String encoding) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setContentLength(int contentLength) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setContentType(String contentType) {
		if (!(_portletResponse instanceof MimeResponse)) {
			throw new UnsupportedOperationException();
		}

		MimeResponse mimeResponse = (MimeResponse)_portletResponse;

		mimeResponse.setContentType(contentType);
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
		if (!(_portletResponse instanceof MimeResponse)) {
			return;
		}

		ResourceResponse resourceResponse = (ResourceResponse)_portletResponse;

		resourceResponse.setLocale(locale);
	}

	@Override
	public void setStatus(int status) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setStatus(int status, String message) {
		throw new UnsupportedOperationException();
	}

	private Locale _locale;
	private PortletResponse _portletResponse;
	private ServletOutputStream _servletOutputStream;

	private class ServletOutputStreamAdapter extends ServletOutputStream {

		public ServletOutputStreamAdapter(MimeResponse mimeResponse) {
			_mimeResponse = mimeResponse;
		}

		@Override
		public void write(int b) throws IOException {
			OutputStream outputStream = _mimeResponse.getPortletOutputStream();

			outputStream.write(b);
		}

		private MimeResponse _mimeResponse;

	}

}