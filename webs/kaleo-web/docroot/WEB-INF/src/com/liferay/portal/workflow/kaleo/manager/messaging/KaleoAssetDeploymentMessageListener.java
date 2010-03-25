/*
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
 * <a href="KaleoAssetDeploymentMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class KaleoAssetDeploymentMessageListener implements MessageListener {
	public void receive(Message message) {

		String assetClassName = (String)message.get("ASSET_CLASS_NAME");

		try {
			_portalKaleoManager.deployDefaultDefinitionLink(assetClassName);
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to deploy default definitions for: " +
						  assetClassName, e);
			}
		}
	}

	public void setPortalKaleoManager(PortalKaleoManager portalKaleoManager) {
		_portalKaleoManager = portalKaleoManager;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoAssetDeploymentMessageListener.class);
	
	private PortalKaleoManager _portalKaleoManager;
}
