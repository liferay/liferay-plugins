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

package com.liferay.sampleservicebuilder.service.http;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.sampleservicebuilder.model.Foo;

import java.util.Date;
import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class FooJSONSerializer {
	public static JSONObject toJSONObject(Foo model) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("fooId", model.getFooId());
		jsonObj.put("companyId", model.getCompanyId());
		jsonObj.put("userId", model.getUserId());
		jsonObj.put("userName", model.getUserName());

		Date createDate = model.getCreateDate();

		String createDateJSON = StringPool.BLANK;

		if (createDate != null) {
			createDateJSON = String.valueOf(createDate.getTime());
		}

		jsonObj.put("createDate", createDateJSON);

		Date modifiedDate = model.getModifiedDate();

		String modifiedDateJSON = StringPool.BLANK;

		if (modifiedDate != null) {
			modifiedDateJSON = String.valueOf(modifiedDate.getTime());
		}

		jsonObj.put("modifiedDate", modifiedDateJSON);
		jsonObj.put("field1", model.getField1());
		jsonObj.put("field2", model.getField2());
		jsonObj.put("field3", model.getField3());

		Date field4 = model.getField4();

		String field4JSON = StringPool.BLANK;

		if (field4 != null) {
			field4JSON = String.valueOf(field4.getTime());
		}

		jsonObj.put("field4", field4JSON);
		jsonObj.put("field5", model.getField5());

		return jsonObj;
	}

	public static JSONArray toJSONArray(
		com.liferay.sampleservicebuilder.model.Foo[] models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Foo model : models) {
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}

	public static JSONArray toJSONArray(
		com.liferay.sampleservicebuilder.model.Foo[][] models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Foo[] model : models) {
			jsonArray.put(toJSONArray(model));
		}

		return jsonArray;
	}

	public static JSONArray toJSONArray(
		List<com.liferay.sampleservicebuilder.model.Foo> models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Foo model : models) {
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}
}