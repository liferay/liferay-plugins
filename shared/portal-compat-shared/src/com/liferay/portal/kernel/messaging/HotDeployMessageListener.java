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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Collections;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class HotDeployMessageListener extends BaseMessageListener {

	public HotDeployMessageListener() {
		this((String[])null);
	}

	public HotDeployMessageListener(String... servletContextNames) {
		if (servletContextNames == null) {
			_servletContextNames = Collections.emptySet();
		}
		else {
			_servletContextNames = SetUtil.fromArray(servletContextNames);
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String servletContextName = GetterUtil.getString(
			message.getString("servletContextName"));

		if (!_servletContextNames.isEmpty() &&
			!_servletContextNames.contains(servletContextName)) {

			return;
		}

		String command = GetterUtil.getString(message.getString("command"));

		if (command.equals("deploy")) {
			onDeploy(message);
		}
		else if (command.equals("undeploy")) {
			onUndeploy(message);
		}
	}

	protected void onDeploy(Message message) throws Exception {
	}

	protected void onUndeploy(Message message) throws Exception {
	}

	private Set<String> _servletContextNames;

}