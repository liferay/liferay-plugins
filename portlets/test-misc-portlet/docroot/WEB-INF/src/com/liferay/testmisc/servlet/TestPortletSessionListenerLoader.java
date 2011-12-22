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

package com.liferay.testmisc.servlet;

import com.liferay.portal.kernel.servlet.PortletSessionListenerLoader;

/**
 * <p>
 * This is a <code>javax.servlet.ServletContextListener</code> that loads a
 * <code>javax.servlet.http.HttpSessionListener</code> and ensures the hot
 * deployed WAR's session events are triggered along with the portal's session
 * events. This is only needed for certain application servers under certain
 * configurations. Otherwise, you can just load the the
 * <code>HttpSessionListener</code> directly in WEB-INF/web.xml.
 * </p>
 *
 * <p>
 * See http://support.liferay.com/browse/LEP-2299.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see    com.liferay.testmisc.servlet.TestPortletSessionListener
 */
public class TestPortletSessionListenerLoader
	extends PortletSessionListenerLoader {

	public TestPortletSessionListenerLoader() {
		super(new TestPortletSessionListener());
	}

}