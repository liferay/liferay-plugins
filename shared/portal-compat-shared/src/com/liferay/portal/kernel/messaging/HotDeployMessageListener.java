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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class HotDeployMessageListener extends BaseMessageListener {

	public HotDeployMessageListener(String servletContextName) {
		_servletContextName = servletContextName;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String servletContextName = GetterUtil.getString(
			message.getString("servletContextName"));

		if (!servletContextName.equals(_servletContextName)) {
			return;
		}

		String command = GetterUtil.getString(message.getString("command"));

		if (command.equals("deploy")) {
			onDeploy();
		}
		else if (command.equals("undeploy")) {
			onUndeploy();
		}
	}

	protected void onDeploy() throws Exception {
	}

	protected void onUndeploy() throws Exception {
	}

	private String _servletContextName;

}