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

package com.liferay.alloy.mvc;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;

import java.io.IOException;

import java.lang.reflect.Method;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Johnny Duong
 */
public class AlloyAPIRequestProcessor {

	public static void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Map<String, BaseAlloyControllerImpl> alloyControllers)
		throws Exception {

		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

		jsonResponse.put("success", false);

		String controller = ParamUtil.getString(actionRequest, "controller");

		BaseAlloyControllerImpl baseAlloyControllerImpl = alloyControllers.get(
			controller);

		if (baseAlloyControllerImpl == null) {
			throw new Exception("Unable to find controller " + controller);
		}

		String action = ParamUtil.getString(actionRequest, "action");

		try {
			Method method = baseAlloyControllerImpl.getMethod(action);

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				actionRequest);

			baseAlloyControllerImpl.setRequest(request);

			baseAlloyControllerImpl.initFormat();
			baseAlloyControllerImpl.initPortletVariables();

			method.invoke(baseAlloyControllerImpl);

			String JSONData = GetterUtil.getString(
				request.getAttribute("JSONData"));

			jsonResponse.put("data", JSONData);
			jsonResponse.put("success", true);
		}
		catch (Exception e) {
			jsonResponse.put(
				"error", "An unexpected exception occurred: " + e.getMessage());

			jsonResponse.put("stacktrace", StackTraceUtil.getStackTrace(e));
		}

		writeJSON(actionRequest, actionResponse, jsonResponse);
	}

	protected static void writeJSON(
			PortletRequest portletRequest, ActionResponse actionResponse,
			Object json)
		throws IOException {

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		response.setContentType(ContentTypes.APPLICATION_JSON);

		ServletResponseUtil.write(response, json.toString());

		response.flushBuffer();
	}

}