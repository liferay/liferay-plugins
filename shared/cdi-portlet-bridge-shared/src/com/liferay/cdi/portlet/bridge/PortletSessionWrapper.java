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

import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;

/**
 * @author Neil Griffin
 */
public class PortletSessionWrapper implements PortletSession {

	public PortletSessionWrapper(PortletSession portletSession) {
		_portletSession = portletSession;
	}

	@Override
	public Object getAttribute(String name) {
		return _portletSession.getAttribute(name);
	}

	@Override
	public Object getAttribute(String name, int value) {
		return _portletSession.getAttribute(name, value);
	}

	@Override
	public Map<String, Object> getAttributeMap() {
		return _portletSession.getAttributeMap();
	}

	@Override
	public Map<String, Object> getAttributeMap(int scope) {
		return _portletSession.getAttributeMap(scope);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return _portletSession.getAttributeNames();
	}

	@Override
	public Enumeration<String> getAttributeNames(int scope) {
		return _portletSession.getAttributeNames(scope);
	}

	@Override
	public long getCreationTime() {
		return _portletSession.getCreationTime();
	}

	@Override
	public String getId() {
		return _portletSession.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return _portletSession.getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {
		return _portletSession.getMaxInactiveInterval();
	}

	@Override
	public PortletContext getPortletContext() {
		return _portletSession.getPortletContext();
	}

	public PortletSession getPortletSession() {
		return _portletSession;
	}

	@Override
	public void invalidate() {
		_portletSession.invalidate();
	}

	@Override
	public boolean isNew() {
		return _portletSession.isNew();
	}

	@Override
	public void removeAttribute(String name) {
		_portletSession.removeAttribute(name);
	}

	@Override
	public void removeAttribute(String name, int value) {
		_portletSession.removeAttribute(name, value);
	}

	@Override
	public void setAttribute(String name, Object value) {
		_portletSession.setAttribute(name, value);
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		_portletSession.setAttribute(name, value, scope);
	}

	@Override
	public void setMaxInactiveInterval(int maxInactiveInterval) {
		_portletSession.setMaxInactiveInterval(maxInactiveInterval);
	}

	private PortletSession _portletSession;

}