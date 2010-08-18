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

import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		_servletContext = servletContextEvent.getServletContext();
		_classLoader = PortletClassLoaderUtil.getClassLoader();

		registerPortalLifecycle();
	}

	protected void doPortalDestroy() throws Exception {
		WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlets();
	}

	protected void doPortalInit() {
		PortalInitThread portalInitThread = new PortalInitThread();

		portalInitThread.setContextClassLoader(_classLoader);
		portalInitThread.setServletContext(_servletContext);

		portalInitThread.start();
	}

	private ClassLoader _classLoader;
	private ServletContext _servletContext;

}