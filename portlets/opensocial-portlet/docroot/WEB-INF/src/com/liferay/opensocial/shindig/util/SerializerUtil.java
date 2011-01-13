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

package com.liferay.opensocial.shindig.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Map;

import org.apache.shindig.social.core.model.AddressImpl;
import org.apache.shindig.social.opensocial.model.Address;

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
			Map<String, Serializable> map, Object bean, Object[] fields) {

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

			if (fieldName.equals("location")) {
				JSONObject addressJSONObject = null;

				try {
					addressJSONObject = JSONFactoryUtil.createJSONObject(
						value);
				}
				catch (Exception e) {
				}

				if (addressJSONObject != null) {
					Address address = new AddressImpl();

					SerializerUtil.copyProperties(
						addressJSONObject, address, _ADDRESS_FIELDS);

					BeanPropertiesUtil.setProperty(bean, fieldName, address);
				}
			}
			else {
				BeanPropertiesUtil.setProperty(bean, fieldName, value);
			}
		}
	}

	public static void copyProperty(
			Map<String, Serializable> map, Object bean, Object field) {

		String fieldName = field.toString();

		if (map.containsKey(fieldName)) {
			String value = (String)map.get(fieldName);

			if (fieldName.equals("location")) {
				JSONObject addressJSONObject = null;

				try {
					addressJSONObject = JSONFactoryUtil.createJSONObject(
						value);
				}
				catch (Exception e) {
				}

				if (addressJSONObject != null) {
					Address address = new AddressImpl();

					SerializerUtil.copyProperties(
						addressJSONObject, address, _ADDRESS_FIELDS);

					BeanPropertiesUtil.setProperty(bean, fieldName, address);
				}
			}
			else {
				BeanPropertiesUtil.setProperty(bean, fieldName, value);
			}
		}
	}

	public static void copyProperty(
			Object bean, JSONObject jsonObject, Object field) {

		String fieldName = field.toString();

		Object value = BeanPropertiesUtil.getObject(bean, fieldName);

		if (Validator.isNotNull(value)) {
			if (fieldName.equals("location")) {
				JSONObject addressJSONObject =
					JSONFactoryUtil.createJSONObject();

				SerializerUtil.copyProperties(
					value, addressJSONObject, _ADDRESS_FIELDS);

				jsonObject.put(fieldName, addressJSONObject.toString());
			}
			else {
				jsonObject.put(fieldName, (String)value);
			}
		}
	}

	public static void copyProperty(
			Object bean, Map<String, Serializable> map, Object field) {

		String fieldName = field.toString();

		Object value = BeanPropertiesUtil.getObject(bean, fieldName);

		if (Validator.isNotNull(value)) {
			if (fieldName.equals("location")) {
				JSONObject addressJSONObject =
					JSONFactoryUtil.createJSONObject();

				SerializerUtil.copyProperties(
					value, addressJSONObject, _ADDRESS_FIELDS);

				map.put(fieldName, addressJSONObject.toString());
			}
			else {
				map.put(fieldName, (String)value);
			}
		}
	}

	private static final Address.Field[] _ADDRESS_FIELDS = {
		Address.Field.COUNTRY, Address.Field.FORMATTED,
		Address.Field.LATITUDE, Address.Field.LOCALITY,
		Address.Field.LONGITUDE, Address.Field.POSTAL_CODE,
		Address.Field.PRIMARY, Address.Field.REGION,
		Address.Field.STREET_ADDRESS, Address.Field.TYPE
	};

}