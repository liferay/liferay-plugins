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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

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
				Context context = new InitialContext();

				try {
					beanManager = (BeanManager)context.lookup(
						_BEAN_MANAGER_JNDI_NAME_1);
				}
				catch (NameNotFoundException nnfe) {
					beanManager = (BeanManager)context.lookup(
						_BEAN_MANAGER_JNDI_NAME_2);
				}
			}
			catch (NamingException ne) {
				_log.error(ne, ne);
			}
		}

		if (beanManager == null) {
			_log.error("Unable to get CDI bean manager");
		}

		CDIBeanManagerUtil.setBeanManager(beanManager);
	}

	private static final String _ATTRIBUTE_NAME_BEAN_MANAGER =
		"org.jboss.weld.environment.servlet.javax.enterprise.inject.spi." +
			"BeanManager";

	private static final String _BEAN_MANAGER_JNDI_NAME_1 =
		"java:comp/BeanManager";

	private static final String _BEAN_MANAGER_JNDI_NAME_2 =
		"java:comp/env/BeanManager";

	private static Log _log = LogFactoryUtil.getLog(CDIContextListener.class);

}