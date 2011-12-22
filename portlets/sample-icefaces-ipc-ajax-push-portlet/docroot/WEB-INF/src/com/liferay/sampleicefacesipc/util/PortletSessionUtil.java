/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleicefacesipc.util;

import javax.faces.context.FacesContext;

import javax.portlet.PortletSession;

/**
 * <p>
 * This utility class is necessary to simulate JSF "session" scope across
 * multiple portlets. By default, the JSF framework defines session-scoped
 * managed beans in faces-config.xml within PortletSession.PORTLET_SCOPE instead
 * of PortletSession.APPLICATION_SCOPE, which defines a scope that can share
 * attribute values between portlets.
 * </p>
 *
 * @author Neil Griffin
 */
public class PortletSessionUtil {

	public static final String CUSTOMER_LIST = "CUSTOMER_LIST";

	public static final String SELECTED_CUSTOMER = "SELECTED_CUSTOMER";

	public static Object getSharedSessionAttribute(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		PortletSession portletSession =
			(PortletSession)facesContext.getExternalContext().getSession(false);

		return portletSession.getAttribute(
			key, PortletSession.APPLICATION_SCOPE);
	}

	public static void setSharedSessionAttribute(String key, Object value) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		PortletSession portletSession =
			(PortletSession)facesContext.getExternalContext().getSession(false);

		portletSession.setAttribute(
			key, value, PortletSession.APPLICATION_SCOPE);
	}

}