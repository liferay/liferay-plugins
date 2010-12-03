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
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("uuid", model.getUuid());
		jsonObject.put("fooId", model.getFooId());
		jsonObject.put("groupId", model.getGroupId());
		jsonObject.put("companyId", model.getCompanyId());
		jsonObject.put("userId", model.getUserId());
		jsonObject.put("userName", model.getUserName());

		Date createDate = model.getCreateDate();

		String createDateJSON = StringPool.BLANK;

		if (createDate != null) {
			createDateJSON = String.valueOf(createDate.getTime());
		}

		jsonObject.put("createDate", createDateJSON);

		Date modifiedDate = model.getModifiedDate();

		String modifiedDateJSON = StringPool.BLANK;

		if (modifiedDate != null) {
			modifiedDateJSON = String.valueOf(modifiedDate.getTime());
		}

		jsonObject.put("modifiedDate", modifiedDateJSON);
		jsonObject.put("field1", model.getField1());
		jsonObject.put("field2", model.getField2());
		jsonObject.put("field3", model.getField3());

		Date field4 = model.getField4();

		String field4JSON = StringPool.BLANK;

		if (field4 != null) {
			field4JSON = String.valueOf(field4.getTime());
		}

		jsonObject.put("field4", field4JSON);
		jsonObject.put("field5", model.getField5());

		return jsonObject;
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