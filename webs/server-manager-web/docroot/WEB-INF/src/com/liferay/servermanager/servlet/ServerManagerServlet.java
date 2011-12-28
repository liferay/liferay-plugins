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

package com.liferay.servermanager.servlet;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.servermanager.executor.Executor;
import com.liferay.servermanager.executor.RootExecutor;
import com.liferay.servermanager.util.JSONKeys;

import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jonathan Potter
 */
public class ServerManagerServlet extends HttpServlet {

	protected void execute(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		Executor executor = _executor;

		while (true) {
			Executor nextExecutor = executor.getNextExecutor(arguments);

			if (nextExecutor == null) {
				break;
			}

			executor = nextExecutor;
		}

		String method = request.getMethod();

		if (method.equalsIgnoreCase(HttpMethods.DELETE)) {
			executor.executeDelete(request, responseJSONObject, arguments);
		}
		else if (method.equalsIgnoreCase(HttpMethods.GET)) {
			executor.executeRead(request, responseJSONObject, arguments);
		}
		else if (method.equalsIgnoreCase(HttpMethods.POST)) {
			executor.executeCreate(request, responseJSONObject, arguments);
		}
		else if (method.equalsIgnoreCase(HttpMethods.PUT)) {
			executor.executeUpdate(request, responseJSONObject, arguments);
		}
	}

	protected boolean isValidUser(HttpServletRequest request) {
		try {
			User user = PortalUtil.getUser(request);

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user, false);

			if (permissionChecker.isOmniadmin()) {
				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		if (!isValidUser(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			return;
		}

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject();

		responseJSONObject.put(JSONKeys.ERROR, StringPool.BLANK);
		responseJSONObject.put(JSONKeys.OUTPUT, StringPool.BLANK);
		responseJSONObject.put(JSONKeys.STATUS, 0);

		try {
			Queue<String> arguments = new LinkedList<String>();

			String path = request.getPathInfo();

			path = path.toLowerCase();

			if (path.startsWith(StringPool.SLASH)) {
				path = path.substring(1);
			}

			String[] pathParts = StringUtil.split(path, StringPool.SLASH);

			for (String pathPart : pathParts) {
				arguments.add(pathPart);
			}

			execute(request, responseJSONObject, arguments);
		}
		catch (Exception e) {
			responseJSONObject.put(
				JSONKeys.ERROR, StackTraceUtil.getStackTrace(e));
			responseJSONObject.put(JSONKeys.STATUS, 1);
		}

		String format = ParamUtil.getString(request, "format");

		if (format.equals("raw")) {
			response.setContentType(ContentTypes.TEXT_PLAIN);

			String outputStream = responseJSONObject.getString(JSONKeys.OUTPUT);

			ServletResponseUtil.write(response, outputStream);
		}
		else {
			response.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(response, responseJSONObject.toString());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ServerManagerServlet.class);

	private Executor _executor = new RootExecutor();

}