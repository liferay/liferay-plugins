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

		_wrappedHttpSession = httpSession;
	}

	@Override
	public Object getAttribute(String name) {

		return getHttpSession().getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {

		return getHttpSession().getAttributeNames();
	}

	@Override
	public long getCreationTime() {

		return getHttpSession().getCreationTime();
	}

	public HttpSession getHttpSession() {

		return _wrappedHttpSession;
	}

	@Override
	public String getId() {

		return getHttpSession().getId();
	}

	@Override
	public long getLastAccessedTime() {

		return getHttpSession().getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {

		return getHttpSession().getMaxInactiveInterval();
	}

	@Override
	public ServletContext getServletContext() {

		return getHttpSession().getServletContext();
	}

	@Override
	@SuppressWarnings("deprecation")
	public javax.servlet.http.HttpSessionContext getSessionContext() {

		return getHttpSession().getSessionContext();
	}

	@Override
	public Object getValue(String name) {

		return getHttpSession().getAttribute(name);
	}

	@Override
	@SuppressWarnings("deprecation")
	public String[] getValueNames() {

		return getHttpSession().getValueNames();
	}

	@Override
	public void invalidate() {

		getHttpSession().invalidate();
	}

	@Override
	public boolean isNew() {

		return getHttpSession().isNew();
	}

	@Override
	public void putValue(String name, Object value) {

		getHttpSession().setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {

		getHttpSession().removeAttribute(name);
	}

	@Override
	public void removeValue(String name) {

		getHttpSession().removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object value) {

		getHttpSession().setAttribute(name, value);
	}

	@Override
	public void setMaxInactiveInterval(int maxInactiveInterval) {

		getHttpSession().setMaxInactiveInterval(maxInactiveInterval);
	}

	private HttpSession _wrappedHttpSession;

}