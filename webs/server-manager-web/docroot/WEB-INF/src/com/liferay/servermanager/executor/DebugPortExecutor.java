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

package com.liferay.servermanager.executor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.servermanager.util.JSONKeys;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class DebugPortExecutor extends BaseExecutor {

	@Override
	public void executeRead(
		HttpServletRequest request, JSONObject responseJSONObject,
		Queue<String> arguments) {

		String debugPort = getDebugPort();

		if (debugPort == null) {
			responseJSONObject.put(
				JSONKeys.ERROR, "Server was not started in debug mode");
			responseJSONObject.put(JSONKeys.STATUS, 1);
		}
		else {
			responseJSONObject.put(JSONKeys.OUTPUT, debugPort);
		}
	}

	protected String getDebugPort() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

		List<String> inputArguments = runtimeMXBean.getInputArguments();

		if (inputArguments == null) {
			return null;
		}

		for (String inputArgument : inputArguments) {
			if (!inputArgument.contains("transport=dt_socket")) {
				continue;
			}

			Matcher matcher = _pattern.matcher(inputArgument);

			if (matcher.find()) {
				return matcher.group(1);
			}
		}

		return null;
	}

	private Pattern _pattern = Pattern.compile("address=(\\d+)");

}