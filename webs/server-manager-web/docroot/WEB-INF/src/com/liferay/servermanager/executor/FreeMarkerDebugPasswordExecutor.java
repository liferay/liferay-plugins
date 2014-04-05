/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cindy Li
 */
public class FreeMarkerDebugPasswordExecutor extends BaseExecutor {

	@Override
	public void executeRead(
		HttpServletRequest request, JSONObject responseJSONObject,
		Queue<String> arguments) {

		String freeMarkerDebugPassword = System.getProperty(
			"freemarker.debug.password");

		if (freeMarkerDebugPassword == null) {
			responseJSONObject.put(
				JSONKeys.ERROR, "FreeMarker debugger is not enabled");
			responseJSONObject.put(JSONKeys.STATUS, 1);
		}
		else {
			responseJSONObject.put(JSONKeys.OUTPUT, freeMarkerDebugPassword);
		}
	}

}