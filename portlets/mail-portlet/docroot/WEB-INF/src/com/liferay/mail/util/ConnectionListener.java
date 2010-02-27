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

package com.liferay.mail.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.mail.event.ConnectionEvent;

/**
 * <a href="ConnectionListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 *
 */
public class ConnectionListener implements javax.mail.event.ConnectionListener {

	public ConnectionListener(String service) {
		super();

		_service = service;
	}

	public void closed(ConnectionEvent event) {
		if (_log.isDebugEnabled()) {
			long uptime = (System.currentTimeMillis() - _startTime) / 1000;

			_log.debug(
				"The " + _service + " service has been closed after " +
					uptime + "secs of uptime.");
		}
	}

	public void disconnected(ConnectionEvent event) {
		if (_log.isDebugEnabled()) {
			long uptime = (System.currentTimeMillis() - _startTime) / 1000;

			_log.debug(
				"The " + _service + " service has been disconnected after " +
					uptime + "secs of uptime.");
		}
	}

	public void opened(ConnectionEvent event) {
		_startTime = System.currentTimeMillis();

		if (_log.isDebugEnabled()) {
			_log.debug("The " + _service + " service has been opened");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ConnectionListener.class);

	private String _service;
	private long _startTime;

}