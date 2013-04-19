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
@SuppressWarnings("deprecation")
public class CDISessionImpl extends CDISession {

	public CDISessionImpl(PortletSession portletSession) {

		super(portletSession);
	}

	@Override
	public ServletContext getServletContext() {

		throw new UnsupportedOperationException();
	}

	@Override
	public javax.servlet.http.HttpSessionContext getSessionContext() {

		throw new UnsupportedOperationException();
	}

	@Override
	public Object getValue(String name) {

		return getPortletSession().getAttribute(name);
	}

	@Override
	public String[] getValueNames() {

		String[] valueNames = null;
		Enumeration<String> attributeNames =
			getPortletSession().getAttributeNames();

		if (attributeNames != null) {
			List<String> valueNameArray = new ArrayList<String>();

			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();
				valueNameArray.add(attributeName);
			}

			valueNames = valueNameArray.toArray(
				new String[valueNameArray.size()]);
		}

		return valueNames;
	}

	@Override
	public void putValue(String name, Object value) {

		getPortletSession().setAttribute(name, value);
	}

	@Override
	public void removeValue(String name) {

		getPortletSession().removeAttribute(name);
	}

}