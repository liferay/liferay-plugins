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

package com.liferay.portal.workflow.kaleo.runtime.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class ExecutionContextUtil {

	public static String convert(ExecutionContext executionContext) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		if (kaleoInstanceToken != null) {
			jsonObject.put(
				"kaleoInstanceTokenId",
				kaleoInstanceToken.getKaleoInstanceTokenId());
		}

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		if (kaleoTaskInstanceToken != null) {
			jsonObject.put(
				"kaleoTaskInstanceTokenId",
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
		}

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();

		jsonObject.put(
			"workflowContext", WorkflowContextUtil.convert(workflowContext));

		ServiceContext serviceContext = executionContext.getServiceContext();

		jsonObject.put(
			"serviceContext", JSONFactoryUtil.serialize(serviceContext));

		jsonObject.put("transitionName", executionContext.getTransitionName());

		return jsonObject.toString();
	}

	public static ExecutionContext convert(String json) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

		KaleoInstanceToken kaleoInstanceToken = null;

		long kaleoInstanceTokenId = jsonObject.getLong("kaleoInstanceTokenId");

		if (kaleoInstanceTokenId > 0) {
			kaleoInstanceToken =
				KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceToken(
					kaleoInstanceTokenId);
		}

		KaleoTaskInstanceToken kaleoTaskInstanceToken = null;

		long kaleoTaskInstanceTokenId = jsonObject.getLong(
			"kaleoTaskInstanceTokenId");

		if (kaleoTaskInstanceTokenId > 0) {
			kaleoTaskInstanceToken =
				KaleoTaskInstanceTokenLocalServiceUtil.
					getKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
		}

		Map<String, Serializable> workflowContext = WorkflowContextUtil.convert(
			jsonObject.getString("workflowContext"));

		ServiceContext serviceContext =
			(ServiceContext)JSONFactoryUtil.deserialize(
				jsonObject.getString("serviceContext"));

		return new ExecutionContext(
			kaleoInstanceToken, kaleoTaskInstanceToken, workflowContext,
			serviceContext);
	}

}