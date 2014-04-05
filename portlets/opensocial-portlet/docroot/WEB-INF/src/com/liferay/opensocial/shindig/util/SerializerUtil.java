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

package com.liferay.opensocial.shindig.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shindig.social.core.model.AddressImpl;
import org.apache.shindig.social.opensocial.model.Address;

/**
 * @author Brian Wing Shun Chan
 */
public class SerializerUtil {

	public static void copyProperties(
			JSONObject jsonObject, Object bean, Object[] fields)
		throws JSONException {

		for (Object field : fields) {
			copyProperty(jsonObject, bean, field);
		}
	}

	public static void copyProperties(
			Map<String, Serializable> map, Object bean, Object[] fields)
		throws JSONException {

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
			JSONObject jsonObject, Object bean, Object field)
		throws JSONException {

		String fieldName = field.toString();

		if (jsonObject.has(fieldName)) {
			String value = jsonObject.getString(fieldName);

			setBeanProperty(bean, fieldName, value);
		}
	}

	public static void copyProperty(
			Map<String, Serializable> map, Object bean, Object field)
		throws JSONException {

		String fieldName = field.toString();

		if (map.containsKey(fieldName)) {
			String value = (String)map.get(fieldName);

			setBeanProperty(bean, fieldName, value);
		}
	}

	public static void copyProperty(
		Object bean, JSONObject jsonObject, Object field) {

		String fieldName = field.toString();

		String value = getBeanProperty(bean, fieldName);

		if (value != null) {
			jsonObject.put(fieldName, value);
		}
	}

	public static void copyProperty(
		Object bean, Map<String, Serializable> map, Object field) {

		String fieldName = field.toString();

		String value = getBeanProperty(bean, fieldName);

		if (value != null) {
			map.put(fieldName, value);
		}
	}

	public static Map<String, Serializable> toExpandoAttributes(
			Object bean, Object[] fields, long companyId, String className)
		throws PortalException {

		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			companyId, className);

		for (Object field : fields) {
			String fieldName = field.toString();

			String value = BeanPropertiesUtil.getString(bean, fieldName);

			if ((value != null) && !expandoBridge.hasAttribute(fieldName)) {
				expandoBridge.addAttribute(fieldName);
			}
		}

		Map<String, Serializable> expandoBridgeAttributes =
			new LinkedHashMap<String, Serializable>();

		copyProperties(bean, expandoBridgeAttributes, fields);

		return expandoBridgeAttributes;
	}

	protected static String getBeanProperty(Object bean, String fieldName) {
		Object value = BeanPropertiesUtil.getObject(bean, fieldName);

		if (value == null) {
			return null;
		}

		if (fieldName.equals("location")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			copyProperties(value, jsonObject, _ADDRESS_FIELDS);

			return jsonObject.toString();
		}
		else {
			return (String)value;
		}
	}

	protected static void setBeanProperty(
			Object bean, String fieldName, String value)
		throws JSONException {

		if (Validator.isNull(value)) {
			return;
		}

		if (fieldName.equals("location")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(value);

			Address address = new AddressImpl();

			copyProperties(jsonObject, address, _ADDRESS_FIELDS);

			BeanPropertiesUtil.setProperty(bean, fieldName, address);
		}
		else {
			BeanPropertiesUtil.setProperty(bean, fieldName, value);
		}
	}

	private static final Address.Field[] _ADDRESS_FIELDS = {
		Address.Field.COUNTRY, Address.Field.FORMATTED, Address.Field.LATITUDE,
		Address.Field.LOCALITY, Address.Field.LONGITUDE,
		Address.Field.POSTAL_CODE, Address.Field.PRIMARY, Address.Field.REGION,
		Address.Field.STREET_ADDRESS, Address.Field.TYPE
	};

}