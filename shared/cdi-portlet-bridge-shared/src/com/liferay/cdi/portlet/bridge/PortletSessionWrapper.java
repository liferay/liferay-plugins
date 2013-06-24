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
		_portletSession = portletSession;
	}

	public Object getAttribute(String name) {
		return _portletSession.getAttribute(name);
	}

	public Object getAttribute(String name, int value) {
		return _portletSession.getAttribute(name, value);
	}

	public Map<String, Object> getAttributeMap() {
		return _portletSession.getAttributeMap();
	}

	public Map<String, Object> getAttributeMap(int scope) {
		return _portletSession.getAttributeMap(scope);
	}

	public Enumeration<String> getAttributeNames() {
		return _portletSession.getAttributeNames();
	}

	public Enumeration<String> getAttributeNames(int scope) {
		return _portletSession.getAttributeNames(scope);
	}

	public long getCreationTime() {
		return _portletSession.getCreationTime();
	}

	public String getId() {
		return _portletSession.getId();
	}

	public long getLastAccessedTime() {
		return _portletSession.getLastAccessedTime();
	}

	public int getMaxInactiveInterval() {
		return _portletSession.getMaxInactiveInterval();
	}

	public PortletContext getPortletContext() {
		return _portletSession.getPortletContext();
	}

	public PortletSession getPortletSession() {
		return _portletSession;
	}

	public void invalidate() {
		_portletSession.invalidate();
	}

	public boolean isNew() {
		return _portletSession.isNew();
	}

	public void removeAttribute(String name) {
		_portletSession.removeAttribute(name);
	}

	public void removeAttribute(String name, int value) {
		_portletSession.removeAttribute(name, value);
	}

	public void setAttribute(String name, Object value) {
		_portletSession.setAttribute(name, value);
	}

	public void setAttribute(String name, Object value, int scope) {
		_portletSession.setAttribute(name, value, scope);
	}

	public void setMaxInactiveInterval(int maxInactiveInterval) {
		_portletSession.setMaxInactiveInterval(maxInactiveInterval);
	}

	private PortletSession _portletSession;

}