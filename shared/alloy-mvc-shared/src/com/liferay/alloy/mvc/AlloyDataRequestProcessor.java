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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;
import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Ethan Bustad
 */
public class AlloyDataRequestProcessor {

	public static void process(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Map<String, BaseAlloyControllerImpl> alloyControllers)
		throws Exception {

		String jsonString = null;

		String controller = ParamUtil.getString(actionRequest, "controller");

		BaseAlloyControllerImpl baseAlloyControllerImpl = alloyControllers.get(
			controller);

		if (baseAlloyControllerImpl == null) {
			throw new Exception("Unable to find controller " + controller);
		}

		String action = ParamUtil.getString(actionRequest, "action");

		try {
			if (action.equals("custom")) {
				Class<?> clazz = BaseAlloyControllerImpl.class;

				Method method = clazz.getDeclaredMethod(
					"processDataRequest", new Class<?>[] {ActionRequest.class});

				jsonString =
					(String)ServiceBeanMethodInvocationFactoryUtil.proceed(
						baseAlloyControllerImpl, clazz, method,
						new Object[] {actionRequest},
						new String[] {"transactionAdvice"});
			}
			else if (action.equals("dynamicQuery")) {
				jsonString = executeDynamicQuery(
					baseAlloyControllerImpl, actionRequest);
			}
			else if (action.equals("search")) {
				jsonString = executeSearch(
					baseAlloyControllerImpl, actionRequest);
			}
		}
		catch (Exception e) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String message = e.getMessage();

			if (Validator.isNull(message)) {
				message = baseAlloyControllerImpl.translate(
					"an-unexpected-error-occurred");
			}

			jsonObject.put("message", message);

			jsonObject.put("stacktrace", StackTraceUtil.getStackTrace(e));
			jsonObject.put("success", false);

			jsonString = jsonObject.toString();
		}

		if (jsonString != null) {
			writeJSON(actionRequest, actionResponse, jsonString);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				jsonString);

			if (jsonObject.getBoolean("success")) {
				AlloyNotificationEventHelper alloyNotificationEventHelper =
					baseAlloyControllerImpl.alloyNotificationEventHelper;

				alloyNotificationEventHelper.addUserNotificationEvents(
					PortalUtil.getHttpServletRequest(actionRequest), controller,
					action,
					jsonObject.getJSONObject(
						"alloyNotificationEventHelperPayloadJSONObject"));
			}
		}
	}

	protected static String executeDynamicQuery(
			BaseAlloyControllerImpl baseAlloyControllerImpl,
			ActionRequest actionRequest)
		throws Exception {

		if (baseAlloyControllerImpl.permissioned) {
			AlloyPermission.check(
				baseAlloyControllerImpl.themeDisplay,
				baseAlloyControllerImpl.controllerPath, "index");
		}

		AlloyServiceInvoker alloyServiceInvoker =
			baseAlloyControllerImpl.alloyServiceInvoker;

		List<Object> properties = JSONFactoryUtil.looseDeserialize(
			ParamUtil.getString(actionRequest, "properties"), ArrayList.class);
		int start = ParamUtil.getInteger(
			actionRequest, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(actionRequest, "end", QueryUtil.ALL_POS);

		List<BaseModel<?>> baseModels = alloyServiceInvoker.executeDynamicQuery(
			properties.toArray(), start, end);

		return JSONFactoryUtil.looseSerialize(baseModels);
	}

	protected static String executeSearch(
			BaseAlloyControllerImpl baseAlloyControllerImpl,
			ActionRequest actionRequest)
		throws Exception {

		if (baseAlloyControllerImpl.permissioned) {
			AlloyPermission.check(
				baseAlloyControllerImpl.themeDisplay,
				baseAlloyControllerImpl.controllerPath, "index");
		}

		Map<String, Serializable> attributes = null;

		String attributesString = ParamUtil.getString(
			actionRequest, "attributes");

		if (Validator.isNotNull(attributesString)) {
			attributes = JSONFactoryUtil.looseDeserialize(
				attributesString, HashMap.class);
		}

		String keywords = ParamUtil.getString(actionRequest, "keywords");

		Sort[] sorts = null;

		String sortsString = ParamUtil.getString(actionRequest, "sorts");

		if (Validator.isNotNull(sortsString)) {
			Map<String, Boolean> sortsMap = JSONFactoryUtil.looseDeserialize(
				sortsString, LinkedHashMap.class);

			sorts = new Sort[sortsMap.size()];

			int i = 0;

			for (Map.Entry<String, Boolean> entry : sortsMap.entrySet()) {
				sorts[i++] = new Sort(entry.getKey(), entry.getValue());
			}
		}

		AlloySearchResult alloySearchResult = baseAlloyControllerImpl.search(
			PortalUtil.getHttpServletRequest(actionRequest), actionRequest,
			attributes, keywords, sorts);

		List<BaseModel<?>> baseModels = alloySearchResult.getBaseModels();

		return JSONFactoryUtil.looseSerialize(baseModels);
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