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

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @author Neil Griffin
 */
public class HttpSessionWrapper implements HttpSession {

	public HttpSessionWrapper(HttpSession httpSession) {
		_httpSession = httpSession;
	}

	public Object getAttribute(String name) {
		return _httpSession.getAttribute(name);
	}

	public Enumeration<String> getAttributeNames() {
		return _httpSession.getAttributeNames();
	}

	public long getCreationTime() {
		return _httpSession.getCreationTime();
	}

	public HttpSession getHttpSession() {
		return _httpSession;
	}

	public String getId() {
		return _httpSession.getId();
	}

	public long getLastAccessedTime() {
		return _httpSession.getLastAccessedTime();
	}

	public int getMaxInactiveInterval() {
		return _httpSession.getMaxInactiveInterval();
	}

	public ServletContext getServletContext() {
		return _httpSession.getServletContext();
	}

	@SuppressWarnings("deprecation")
	public javax.servlet.http.HttpSessionContext getSessionContext() {
		return _httpSession.getSessionContext();
	}

	public Object getValue(String name) {
		return _httpSession.getAttribute(name);
	}

	@SuppressWarnings("deprecation")
	public String[] getValueNames() {
		return _httpSession.getValueNames();
	}

	public void invalidate() {
		_httpSession.invalidate();
	}

	public boolean isNew() {
		return _httpSession.isNew();
	}

	public void putValue(String name, Object value) {
		_httpSession.setAttribute(name, value);
	}

	public void removeAttribute(String name) {
		_httpSession.removeAttribute(name);
	}

	public void removeValue(String name) {
		_httpSession.removeAttribute(name);
	}

	public void setAttribute(String name, Object value) {
		_httpSession.setAttribute(name, value);
	}

	public void setMaxInactiveInterval(int maxInactiveInterval) {
		_httpSession.setMaxInactiveInterval(maxInactiveInterval);
	}

	private HttpSession _httpSession;

}