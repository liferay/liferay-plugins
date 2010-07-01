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

package com.liferay.portal.workflow.kaleo.manager.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.workflow.kaleo.manager.PortalKaleoManager;

/**
 * <a href="KaleoDeploymentEventMessageListener.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Michael C. Han
 */
public class KaleoDeploymentEventMessageListener implements MessageListener {

	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	public void setPortalKaleoManager(PortalKaleoManager portalKaleoManager) {
		_portalKaleoManager = portalKaleoManager;
	}

	public void setServletContextName(String servletContextName) {
		_servletContextName = servletContextName;
	}

	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = (String)message.get("servletContextName");

		if (!command.equals("deploy") ||
			!_servletContextName.equals(servletContextName)) {

			return;
		}

		_portalKaleoManager.deployKaleoDefaults();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoDeploymentEventMessageListener.class);

	private PortalKaleoManager _portalKaleoManager;
	private String _servletContextName;

}