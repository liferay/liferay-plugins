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

package com.liferay.calendar.servlet;

import com.liferay.calendar.service.CalendarImporterLocalServiceUtil;
import com.liferay.calendar.service.ClpSerializer;
import com.liferay.calendar.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.BasePortalLifecycle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Marcellus Tavares
 */
public class CalendarServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		MessageBusUtil.unregisterMessageListener(
			DestinationNames.HOT_DEPLOY, _hotDeployMessageListener);
	}

	@Override
	protected void doPortalInit() throws Exception {
		_hotDeployMessageListener = new HotDeployMessageListener(
			ClpSerializer.getServletContextName()) {

			@Override
			protected void onDeploy() throws Exception {
				if (PortletPropsValues.CALENDAR_SYNC_CALEVENTS_ON_STARTUP) {
					StopWatch stopWatch = null;

					if (_log.isInfoEnabled()) {
						stopWatch = new StopWatch();

						stopWatch.start();
					}

					CalendarImporterLocalServiceUtil.importCalEvents();

					if (_log.isInfoEnabled()) {
						_log.info(
							"Calendar events synchronization takes " +
								stopWatch.getTime() + " ms. Please set the " +
									"calendar.sync.calevents.on.startup " +
										"property to false.");
					}
				}
			}
		};

		MessageBusUtil.registerMessageListener(
			DestinationNames.HOT_DEPLOY, _hotDeployMessageListener);
	}

	private static Log _log = LogFactoryUtil.getLog(
		CalendarServletContextListener.class);

	private MessageListener _hotDeployMessageListener;

}