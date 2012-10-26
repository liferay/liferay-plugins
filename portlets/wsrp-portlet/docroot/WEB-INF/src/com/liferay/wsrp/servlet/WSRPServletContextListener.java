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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.wsrp.messaging.HotDeployMessageListener;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

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
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		_destination.unregister(_hotDeployMessageListener);

		MessageBusUtil.removeDestination(DestinationNames.HOT_DEPLOY);

		WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlets();
	}

	@Override
	protected void doPortalInit() {
		_destination = new SerialDestination();

		_destination.setName(DestinationNames.HOT_DEPLOY);

		_destination.afterPropertiesSet();

		MessageBusUtil.addDestination(_destination);

		_hotDeployMessageListener = new HotDeployMessageListener();

		_destination.register(_hotDeployMessageListener);
	}

	private SerialDestination _destination;
	private MessageListener _hotDeployMessageListener;

}