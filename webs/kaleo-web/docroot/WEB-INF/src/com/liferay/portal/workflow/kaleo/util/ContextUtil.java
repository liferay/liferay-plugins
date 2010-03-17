/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Collections;
import java.util.Map;

/**
 * <a href="ContextUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class ContextUtil {

	public static String convert(Map<String, Serializable> context) {
		if (context == null) {
			return null;
		}

		return JSONFactoryUtil.serialize(context);
	}

	public static Map<String, Serializable> convert(String json) {
		if (Validator.isNull(json)) {
			return Collections.EMPTY_MAP;
		}

		return (Map<String, Serializable>)JSONFactoryUtil.deserialize(json);
	}

}