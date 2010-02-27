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

package com.liferay.mail.servlet;

import com.liferay.mail.messaging.MailMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.ParallelDestination;
import com.liferay.portal.kernel.util.PortalInitable;
import com.liferay.portal.kernel.util.PortalInitableUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <a href="MailServletContextListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 * @author Deepak Gothe
 *
 */
public class MailServletContextListener
	implements PortalInitable, ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		_mailDestination.unregister(_mailMessageListener);

		MessageBusUtil.removeDestination(_mailDestination.getName());
	}

	public void contextInitialized(ServletContextEvent event) {
		PortalInitableUtil.init(this);
	}

	public void portalInit() {
		_mailDestination = new ParallelDestination(
			DestinationNames.MAIL_SYNCHRONIZER);

		MessageBusUtil.addDestination(_mailDestination);

		_mailMessageListener = new MailMessageListener();

		_mailDestination.register(_mailMessageListener);
	}

	private Destination _mailDestination;
	private MessageListener _mailMessageListener;

}