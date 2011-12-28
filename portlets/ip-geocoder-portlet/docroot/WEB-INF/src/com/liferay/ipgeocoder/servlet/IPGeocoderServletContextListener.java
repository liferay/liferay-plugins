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

package com.liferay.ipgeocoder.servlet;

import com.liferay.ipgeocoder.messaging.IPGeocoderMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.ParallelDestination;
import com.liferay.portal.kernel.util.BasePortalLifecycle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 */
public class IPGeocoderServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() {
		_ipGeocoderDestination.unregister(_ipGeocoderMessageListener);

		MessageBusUtil.removeDestination(_ipGeocoderDestination.getName());

		MessageBusUtil.removeDestination(DestinationNames.IP_GEOCODER_RESPONSE);
	}

	@Override
	protected void doPortalInit() {
		_ipGeocoderDestination = new ParallelDestination(
			DestinationNames.IP_GEOCODER);

		MessageBusUtil.addDestination(_ipGeocoderDestination);

		_ipGeocoderMessageListener = new IPGeocoderMessageListener();

		_ipGeocoderDestination.register(_ipGeocoderMessageListener);

		Destination ipGeocoderResponseDestination = new ParallelDestination(
			DestinationNames.IP_GEOCODER_RESPONSE);

		MessageBusUtil.addDestination(ipGeocoderResponseDestination);
	}

	private Destination _ipGeocoderDestination;
	private MessageListener _ipGeocoderMessageListener;

}