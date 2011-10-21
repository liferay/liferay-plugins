/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.mobile.device.wurfl.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.mobile.device.wurfl.WURFLHolderImpl;
import com.liferay.portal.mobile.device.wurfl.WURFLKnownDevices;

/**
 * @author Michael C. Han
 */
public class WURFLDeploymentMessageListener extends BaseMessageListener {

	public void setServletContextName(String servletContextName) {
		_servletContextName = servletContextName;
	}

	public void setWurflHolderImpl(WURFLHolderImpl wurflHolderImpl) {
		_wurflHolderImpl = wurflHolderImpl;
	}

	public void setWurflKnownDevices(WURFLKnownDevices wurflKnownDevices) {
		_wurflKnownDevices = wurflKnownDevices;
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

		_wurflHolderImpl.initialize();
		_wurflKnownDevices.initialize();
	}

	private String _servletContextName;
	private WURFLHolderImpl _wurflHolderImpl;
	private WURFLKnownDevices _wurflKnownDevices;

}