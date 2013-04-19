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
import java.util.Map;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;

/**
 * @author Neil Griffin
 */
public class PortletSessionWrapper implements PortletSession {

	public PortletSessionWrapper(PortletSession portletSession) {

		_wrappedPortletSession = portletSession;
	}

	@Override
	public Object getAttribute(String name) {

		return getPortletSession().getAttribute(name);
	}

	@Override
	public Object getAttribute(String name, int value) {

		return getPortletSession().getAttribute(name, value);
	}

	@Override
	public Map<String, Object> getAttributeMap() {

		return getPortletSession().getAttributeMap();
	}

	@Override
	public Map<String, Object> getAttributeMap(int scope) {

		return getPortletSession().getAttributeMap(scope);
	}

	@Override
	public Enumeration<String> getAttributeNames() {

		return getPortletSession().getAttributeNames();
	}

	@Override
	public Enumeration<String> getAttributeNames(int scope) {

		return getPortletSession().getAttributeNames(scope);
	}

	@Override
	public long getCreationTime() {

		return getPortletSession().getCreationTime();
	}

	@Override
	public String getId() {

		return getPortletSession().getId();
	}

	@Override
	public long getLastAccessedTime() {

		return getPortletSession().getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {

		return getPortletSession().getMaxInactiveInterval();
	}

	@Override
	public PortletContext getPortletContext() {

		return getPortletSession().getPortletContext();
	}

	public PortletSession getPortletSession() {

		return _wrappedPortletSession;
	}

	@Override
	public void invalidate() {

		getPortletSession().invalidate();
	}

	@Override
	public boolean isNew() {

		return getPortletSession().isNew();
	}

	@Override
	public void removeAttribute(String name) {

		getPortletSession().removeAttribute(name);
	}

	@Override
	public void removeAttribute(String name, int value) {

		getPortletSession().removeAttribute(name, value);
	}

	@Override
	public void setAttribute(String name, Object value) {

		getPortletSession().setAttribute(name, value);
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {

		getPortletSession().setAttribute(name, value, scope);
	}

	@Override
	public void setMaxInactiveInterval(int maxInactiveInterval) {

		getPortletSession().setMaxInactiveInterval(maxInactiveInterval);
	}

	private PortletSession _wrappedPortletSession;

}