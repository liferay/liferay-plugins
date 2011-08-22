/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.shindig.servlet;

import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Michael Young
 */
public class GuiceServletContextListener extends BasePortalLifecycle
	implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		_guiceServletContextListener.contextDestroyed(
			servletContextEvent);
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		_initializedServletContextEvent = servletContextEvent;

		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
	}

	@Override
	protected void doPortalInit() throws Exception {
		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			_guiceServletContextListener.contextInitialized(
				_initializedServletContextEvent);
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	private ServletContextListener _guiceServletContextListener =
		new org.apache.shindig.common.servlet.GuiceServletContextListener();
	private ServletContextEvent _initializedServletContextEvent;

}