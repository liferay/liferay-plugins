/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.messaging;

import com.liferay.calendar.service.CalendarImporterLocalServiceUtil;
import com.liferay.calendar.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.StringBundler;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Marcellus Tavares
 */
public class CalendarHotDeployMessageListener extends HotDeployMessageListener {

	public CalendarHotDeployMessageListener(String... servletContextNames) {
		super(servletContextNames);
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		if (!PortletPropsValues.CALENDAR_SYNC_CALEVENTS_ON_STARTUP) {
			return;
		}

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		CalendarImporterLocalServiceUtil.importCalEvents();
		CalendarImporterLocalServiceUtil.importRolePermisions();

		if (_log.isInfoEnabled()) {
			StringBundler sb = new StringBundler(6);

			sb.append("Calendar events synchronization takes ");
			sb.append(stopWatch.getTime());
			sb.append(" ms. Set the property ");
			sb.append("\"calendar.sync.calevents.on.startup\" ");
			sb.append("to \"false\" to disable calendar events ");
			sb.append("synchronization.");

			_log.info(sb.toString());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CalendarHotDeployMessageListener.class);

}