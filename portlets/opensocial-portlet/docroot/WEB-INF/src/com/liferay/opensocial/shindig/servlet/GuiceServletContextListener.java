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

package com.liferay.opensocial.shindig.servlet;

import com.liferay.opensocial.service.ClpSerializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.GetterUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Michael Young
 */
public class GuiceServletContextListener extends BasePortalLifecycle
	implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();

		_guiceServletContextListener.contextDestroyed(servletContextEvent);
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		_initializedServletContextEvent = servletContextEvent;

		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		MessageBusUtil.unregisterMessageListener(
			DestinationNames.HOT_DEPLOY, _messageListener);
	}

	@Override
	protected void doPortalInit() throws Exception {
		_messageListener = new MessageListener() {

			@Override
			public void receive(Message message)
				throws MessageListenerException {

				String servletContextName = GetterUtil.getString(
					message.getString("servletContextName"));

				String command = GetterUtil.getString(
					message.getString("command"));

				if (command.equals("deploy") &&
					ClpSerializer.getServletContextName().equals(
						servletContextName)) {

					try {
						initialize();
					}
					catch (Exception e) {
						_log.error("Unable to initialize open-social", e);

					}
				}
			}

		};

		MessageBusUtil.registerMessageListener(
			DestinationNames.HOT_DEPLOY, _messageListener);
	}

	protected void initialize() throws Exception {
		ClassLoader portletClassLoader =
			PortletClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portletClassLoader) {
				currentThread.setContextClassLoader(portletClassLoader);
			}

			_guiceServletContextListener.contextInitialized(
				_initializedServletContextEvent);
		}
		finally {
			if (contextClassLoader != portletClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		GuiceServletContextListener.class);

	private ServletContextListener _guiceServletContextListener =
		new org.apache.shindig.common.servlet.GuiceServletContextListener();
	private ServletContextEvent _initializedServletContextEvent;
	private MessageListener _messageListener;

}