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

package com.liferay.testmisc.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Brian Wing Shun Chan
 * @see    com.liferay.testmisc.servlet.TestPortletSessionListenerLoader
 */
public class TestPortletSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession ses = event.getSession();

		if (_log.isInfoEnabled()) {
			_log.info("Created session " + ses.getId());
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession ses = event.getSession();

		if (_log.isInfoEnabled()) {
			_log.info("Destroyed session " + ses.getId());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		TestPortletSessionListener.class);

}