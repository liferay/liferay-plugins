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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 *
 */
public class PortalInitThread extends Thread {

	public void run() {
		try {

			// Wait 4 seconds before initializing consumer portlets in case the
			// consumer and producer are the same machine

			Thread.sleep(4000);

			WSRPConsumerPortletLocalServiceUtil.initWSRPConsumerPortlets();
		}
		catch (InterruptedException ie) {
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PortalInitThread.class);

}