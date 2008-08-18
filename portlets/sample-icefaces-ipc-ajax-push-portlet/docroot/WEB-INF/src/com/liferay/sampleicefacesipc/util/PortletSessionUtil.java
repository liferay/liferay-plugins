/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.sampleicefacesipc.util;

import javax.faces.context.FacesContext;

import javax.portlet.PortletSession;

/**
 * <a href="PortletSessionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * This utility class is necessary to simulate JSF "session" scope across
 * multiple portlets. By default, the JSF framework defines session-scoped
 * managed beans in faces-config.xml within PortletSession.PORTLET_SCOPE
 * instead of PortletSession.APPLICATION_SCOPE, which defines a scope that
 * can share attribute values between portlets.
 * </p>
 *
 * @author Neil Griffin
 *
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