/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.jbpm.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class WorkflowContextUtil {

	public static String convertToJSON(
		Map<String, Serializable> workflowContext) {

		if (workflowContext == null) {
			return StringPool.BLANK;
		}

		return JSONFactoryUtil.serialize(workflowContext);
	}

	public static Map<String, Serializable> convertToMap(
		Map<String, Object> variables) {

		if (variables == null) {
			return new HashMap<String, Serializable>();
		}

		Map<String, Serializable> workflowContext =
			new HashMap<String, Serializable>();

		for (Map.Entry<String, Object> entry : variables.entrySet()) {
			workflowContext.put(entry.getKey(), (Serializable)entry.getValue());
		}

		return workflowContext;
	}

}