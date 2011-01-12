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

package com.liferay.opensocial.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SerializerUtil {

	public static void copyProperties(
			JSONObject jsonObject, Object bean, Object[] fields) {

		for (Object field : fields) {
			copyProperty(jsonObject, bean, field);
		}
	}

	public static void copyProperties(
			Map<String, Serializable> map, Object bean,
			Object[] fields) {

		for (Object field : fields) {
			copyProperty(map, bean, field);
		}
	}

	public static void copyProperties(
			Object bean, JSONObject jsonObject, Object[] fields) {

		for (Object field : fields) {
			copyProperty(bean, jsonObject, field);
		}
	}

	public static void copyProperties(
			Object bean, Map<String, Serializable> map, Object[] fields) {

		for (Object field : fields) {
			copyProperty(bean, map, field);
		}
	}

	public static void copyProperty(
			JSONObject jsonObject, Object bean, Object field) {

		String fieldName = field.toString();

		if (jsonObject.has(fieldName)) {
			String value = jsonObject.getString(fieldName);

			BeanPropertiesUtil.setProperty(bean, fieldName, value);
		}
	}

	public static void copyProperty(
			Map<String, Serializable> map, Object bean, Object field) {

		String fieldName = field.toString();

		if (map.containsKey(fieldName)) {
			String value = (String)map.get(fieldName);

			BeanPropertiesUtil.setProperty(bean, fieldName, value);
		}
	}

	public static void copyProperty(
			Object bean, JSONObject jsonObject, Object field) {

		String fieldName = field.toString();

		String value = BeanPropertiesUtil.getString(bean, fieldName);

		if (Validator.isNotNull(value)) {
			jsonObject.put(fieldName, value);
		}
	}

	public static void copyProperty(
			Object bean, Map<String, Serializable> map, Object field) {

		String fieldName = field.toString();

		String value = BeanPropertiesUtil.getString(bean, fieldName);

		if (Validator.isNotNull(value)) {
			map.put(fieldName, value);
		}
	}

}