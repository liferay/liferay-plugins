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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.spi.BeanManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

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

		if (beanManager == null) {

			try {
				Context initialContext = new InitialContext();

				try {
					beanManager = (BeanManager)initialContext.lookup(
						_BEAN_MANAGER_JNDI_NAME1);
				}
				catch (NameNotFoundException e) {
					beanManager = (BeanManager)initialContext.lookup(
						_BEAN_MANAGER_JNDI_NAME2);
				}
			}
			catch (NamingException e) {
				_log.log(Level.SEVERE, e.getMessage(), e);
			}
		}

		if (beanManager == null) {
			_log.log(Level.SEVERE, "Unable to get CDI BeanManager instance");
		}

		CDIBeanManagerUtil.setBeanManager(beanManager);
	}

	private static final String _ATTRIBUTE_NAME_BEAN_MANAGER =
		"org.jboss.weld.environment.servlet.javax.enterprise.inject.spi." +
			"BeanManager";

	private static final String _BEAN_MANAGER_JNDI_NAME1 =
		"java:comp/BeanManager";

	private static final String _BEAN_MANAGER_JNDI_NAME2 =
		"java:comp/env/BeanManager";

	private static final Logger _log = Logger.getLogger(
		CDIContextListener.class.getName());

}