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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Michael Young
 */
public class GuiceServletContextListener extends BasePortalLifecycle
	implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		_destroyedServletContextEvent = servletContextEvent;

		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		_initializedServletContextEvent = servletContextEvent;

		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		_guiceServletContextListener.contextDestroyed(
			_destroyedServletContextEvent);
	}

	@Override
	protected void doPortalInit() throws Exception {
		_guiceServletContextListener.contextInitialized(
			_initializedServletContextEvent);
	}

	private ServletContextEvent _destroyedServletContextEvent;

	private ServletContextEvent _initializedServletContextEvent;

	private ServletContextListener _guiceServletContextListener =
		new org.apache.shindig.common.servlet.GuiceServletContextListener();

}