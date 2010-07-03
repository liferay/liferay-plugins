/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalInitable;
import com.liferay.portal.kernel.util.PortalInitableUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <a href="WSRPServletContextListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPServletContextListener
	implements PortalInitable, ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		try {
			WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlets();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void contextInitialized(ServletContextEvent event) {
		_classLoader = PortletClassLoaderUtil.getClassLoader();

		PortalInitableUtil.init(this);
	}

	public void portalInit() {
		PortalInitThread portalInitThread = new PortalInitThread();

		portalInitThread.setContextClassLoader(_classLoader);

		portalInitThread.start();
	}

	private static Log _log = LogFactoryUtil.getLog(
		WSRPServletContextListener.class);

	private ClassLoader _classLoader;

}