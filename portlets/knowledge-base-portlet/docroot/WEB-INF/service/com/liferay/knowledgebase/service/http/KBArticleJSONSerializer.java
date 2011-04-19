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

package com.liferay.knowledgebase.service.http;

import com.liferay.knowledgebase.model.KBArticle;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class KBArticleJSONSerializer {
	public static JSONObject toJSONObject(KBArticle model) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("uuid", model.getUuid());
		jsonObject.put("kbArticleId", model.getKbArticleId());
		jsonObject.put("resourcePrimKey", model.getResourcePrimKey());
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
		jsonObject.put("rootResourcePrimKey", model.getRootResourcePrimKey());
		jsonObject.put("parentResourcePrimKey", model.getParentResourcePrimKey());
		jsonObject.put("version", model.getVersion());
		jsonObject.put("title", model.getTitle());
		jsonObject.put("content", model.getContent());
		jsonObject.put("description", model.getDescription());
		jsonObject.put("kbTemplateId", model.getKbTemplateId());
		jsonObject.put("priority", model.getPriority());
		jsonObject.put("viewCount", model.getViewCount());
		jsonObject.put("latest", model.getLatest());
		jsonObject.put("main", model.getMain());
		jsonObject.put("status", model.getStatus());
		jsonObject.put("statusByUserId", model.getStatusByUserId());
		jsonObject.put("statusByUserName", model.getStatusByUserName());

		Date statusDate = model.getStatusDate();

		String statusDateJSON = StringPool.BLANK;

		if (statusDate != null) {
			statusDateJSON = String.valueOf(statusDate.getTime());
		}

		jsonObject.put("statusDate", statusDateJSON);

		return jsonObject;
	}

	public static JSONArray toJSONArray(
		com.liferay.knowledgebase.model.KBArticle[] models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (KBArticle model : models) {
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}

	public static JSONArray toJSONArray(
		com.liferay.knowledgebase.model.KBArticle[][] models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (KBArticle[] model : models) {
			jsonArray.put(toJSONArray(model));
		}

		return jsonArray;
	}

	public static JSONArray toJSONArray(
		List<com.liferay.knowledgebase.model.KBArticle> models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (KBArticle model : models) {
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}
}