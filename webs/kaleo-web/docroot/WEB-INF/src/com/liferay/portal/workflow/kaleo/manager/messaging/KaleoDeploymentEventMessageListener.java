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

package com.liferay.portal.workflow.kaleo.manager.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.workflow.kaleo.manager.PortalKaleoManager;

/**
 * @author Michael C. Han
 */
public class KaleoDeploymentEventMessageListener extends BaseMessageListener {

	public void setPortalKaleoManager(PortalKaleoManager portalKaleoManager) {
		_portalKaleoManager = portalKaleoManager;
	}

	public void setServletContextName(String servletContextName) {
		_servletContextName = servletContextName;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (!command.equals("deploy")) {
			return;
		}

		String servletContextName = (String)message.get("servletContextName");

		if (!_servletContextName.equals(servletContextName)) {
			return;
		}

		_portalKaleoManager.deployKaleoDefaults();
	}

	private PortalKaleoManager _portalKaleoManager;
	private String _servletContextName;

}