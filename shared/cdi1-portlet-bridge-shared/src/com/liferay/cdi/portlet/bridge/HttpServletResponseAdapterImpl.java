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

		_portletResponse = portletResponse;
		_locale = locale;
	}

	public void addCookie(Cookie cookie) {

		getPortletResponse().addProperty(cookie);
	}

	public void addDateHeader(String name, long value) {

		throw new UnsupportedOperationException();
	}

	public void addHeader(String name, String value) {

		throw new UnsupportedOperationException();
	}

	public void addIntHeader(String name, int value) {

		throw new UnsupportedOperationException();
	}

	public boolean containsHeader(String name) {

		throw new UnsupportedOperationException();
	}

	public String encodeRedirectUrl(String url) {

		throw new UnsupportedOperationException();
	}

	public String encodeRedirectURL(String url) {

		throw new UnsupportedOperationException();
	}

	public String encodeUrl(String url) {

		return getPortletResponse().encodeURL(url);
	}

	public String encodeURL(String url) {

		return getPortletResponse().encodeURL(url);
	}

	public void flushBuffer() throws IOException {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;
			mimeResponse.flushBuffer();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public int getBufferSize() {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;

			return mimeResponse.getBufferSize();
		}
		else {
			return 0;
		}
	}

	public String getCharacterEncoding() {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;

			return mimeResponse.getCharacterEncoding();
		}
		else {
			return null;
		}
	}

	public String getContentType() {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;

			return mimeResponse.getContentType();
		}
		else {
			return null;
		}
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

	public Locale getLocale() {

		return _locale;
	}

	public ServletOutputStream getOutputStream() throws IOException {

		if (_servletOutputStream == null) {

			PortletResponse wrappedPortletResponse = getPortletResponse();

			if (wrappedPortletResponse instanceof MimeResponse) {

				MimeResponse mimeResponse =
					(MimeResponse)wrappedPortletResponse;
				_servletOutputStream = new ServletOutputStreamAdapter(
					mimeResponse);
			}
			else {
				throw new UnsupportedOperationException();
			}
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

	public PrintWriter getWriter() throws IOException {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;

			return mimeResponse.getWriter();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public boolean isCommitted() {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;

			return mimeResponse.isCommitted();
		}
		else {
			return true;
		}
	}

	public void reset() {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;
			mimeResponse.reset();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public void resetBuffer() {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {
			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;
			mimeResponse.resetBuffer();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public void sendError(int sc) throws IOException {
		throw new UnsupportedOperationException();
	}

	public void sendError(int sc, String message) throws IOException {
		throw new UnsupportedOperationException();
	}

	public void sendRedirect(String location) throws IOException {
		throw new UnsupportedOperationException();
	}

	public void setBufferSize(int size) {

		throw new UnsupportedOperationException();
	}

	public void setCharacterEncoding(String charset) {

		throw new UnsupportedOperationException();
	}

	public void setContentLength(int len) {

		throw new UnsupportedOperationException();
	}

	public void setContentType(String type) {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof MimeResponse) {

			MimeResponse mimeResponse = (MimeResponse)wrappedPortletResponse;
			mimeResponse.setContentType(type);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public void setDateHeader(String name, long date) {

		throw new UnsupportedOperationException();
	}

	public void setHeader(String name, String value) {

		throw new UnsupportedOperationException();
	}

	public void setIntHeader(String name, int value) {

		throw new UnsupportedOperationException();
	}

	public void setLocale(Locale loc) {

		PortletResponse wrappedPortletResponse = getPortletResponse();

		if (wrappedPortletResponse instanceof ResourceResponse) {
			ResourceResponse resourceResponse =
				(ResourceResponse)wrappedPortletResponse;

			resourceResponse.setLocale(loc);
		}
	}

	public void setStatus(int sc) {

		throw new UnsupportedOperationException();
	}

	public void setStatus(int sc, String sm) {

		throw new UnsupportedOperationException();
	}

	protected class ServletOutputStreamAdapter extends ServletOutputStream {

		public ServletOutputStreamAdapter(MimeResponse mimeResponse) {

			_mimeResponse = mimeResponse;
		}

		@Override
		public void write(int b) throws IOException {
			_mimeResponse.getPortletOutputStream().write(b);
		}

		private MimeResponse _mimeResponse;

	}

	private Locale _locale;
	private PortletResponse _portletResponse;
	private ServletOutputStream _servletOutputStream;

}