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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.portlet.PortletSession;

import javax.servlet.ServletContext;

/**
 * @author Neil Griffin
 */
public class CDISessionImpl extends CDISession {

	public CDISessionImpl(PortletSession portletSession) {
		super(portletSession);
	}

	public ServletContext getServletContext() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("deprecation")
	public javax.servlet.http.HttpSessionContext getSessionContext() {
		throw new UnsupportedOperationException();
	}

	public Object getValue(String name) {
		PortletSession portletSession = getPortletSession();

		return portletSession.getAttribute(name);
	}

	public String[] getValueNames() {
		PortletSession portletSession = getPortletSession();

		Enumeration<String> attributeNames = portletSession.getAttributeNames();

		if (attributeNames == null) {
			return null;
		}

		List<String> valueNames = new ArrayList<String>();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();

			valueNames.add(attributeName);
		}

		return valueNames.toArray(new String[valueNames.size()]);
	}

	public void putValue(String name, Object value) {
		PortletSession portletSession = getPortletSession();

		portletSession.setAttribute(name, value);
	}

	public void removeValue(String name) {
		PortletSession portletSession = getPortletSession();

		portletSession.removeAttribute(name);
	}

}