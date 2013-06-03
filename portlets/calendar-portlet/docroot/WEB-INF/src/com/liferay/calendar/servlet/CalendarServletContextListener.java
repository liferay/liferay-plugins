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
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.StringBundler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Marcellus Tavares
 */
public class CalendarServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		MessageBusUtil.unregisterMessageListener(
			DestinationNames.HOT_DEPLOY, _messageListener);
	}

	@Override
	protected void doPortalInit() throws Exception {
		_messageListener = new HotDeployMessageListener(
			ClpSerializer.getServletContextName()) {

			@Override
			protected void onDeploy(Message message) throws Exception {
				if (!PortletPropsValues.CALENDAR_SYNC_CALEVENTS_ON_STARTUP) {
					return;
				}

				StopWatch stopWatch = null;

				if (_log.isInfoEnabled()) {
					stopWatch = new StopWatch();

					stopWatch.start();
				}

				CalendarImporterLocalServiceUtil.importCalEvents();

				if (_log.isInfoEnabled()) {
					StringBundler sb = new StringBundler(5);

					sb.append("Calendar events synchronization takes ");
					sb.append(stopWatch.getTime() + " ms. Set the property ");
					sb.append("\"calendar.sync.calevents.on.startup\" ");
					sb.append("to \"false\" to disable calendar events ");
					sb.append("synchronization.");

					_log.info(sb.toString());
				}
			}

		};

		MessageBusUtil.registerMessageListener(
			DestinationNames.HOT_DEPLOY, _messageListener);
	}

	private static Log _log = LogFactoryUtil.getLog(
		CalendarServletContextListener.class);

	private MessageListener _messageListener;

}