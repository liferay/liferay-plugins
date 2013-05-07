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

import javax.enterprise.inject.spi.BeanManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Neil Griffin
 */
@WebListener
public class CDIContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();

		BeanManager beanManager = (BeanManager)servletContext.getAttribute(
			BeanManager.class.getName());

		if (beanManager == null) {
			beanManager = (BeanManager)servletContext.getAttribute(
				_ATTRIBUTE_NAME_BEAN_MANAGER);
		}

		CDIBeanManagerUtil.setBeanManager(beanManager);
	}

	private static final String _ATTRIBUTE_NAME_BEAN_MANAGER =
		"org.jboss.weld.environment.servlet.javax.enterprise.inject.spi." +
			"BeanManager";

}