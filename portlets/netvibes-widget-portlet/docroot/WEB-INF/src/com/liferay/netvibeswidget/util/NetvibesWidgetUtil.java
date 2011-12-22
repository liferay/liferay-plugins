/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.netvibeswidget.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julio Camarero
 * @author Peter Shin
 */
public class NetvibesWidgetUtil {

	public static String[][] getCategories() throws Exception {
		String jsonString = HttpUtil.URLtoString(HtmlUtil.stripComments(
			"http://api.eco.netvibes.com/categories?format=json"));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonString);

		JSONArray jsonArray = jsonObject.getJSONArray("categories");

		if (jsonArray == null) {
			return new String[0][2];
		}

		String[][] categories = new String[jsonArray.length()][2];

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject curJSONObject = jsonArray.getJSONObject(i);

			categories[i][0] = String.valueOf(curJSONObject.getInt("id"));
			categories[i][1] = curJSONObject.getString("label");
		}

		return categories;
	}

	public static Object[] getWidgets(
			String query, String sort, int category, String region, int page,
			int limit)
		throws Exception {

		StringBuilder sb = new StringBuilder();

		sb.append("http://api.eco.netvibes.com/search?category=");
		sb.append(category);
		sb.append("&format=json&limit=");
		sb.append(limit);
		sb.append("&page=");
		sb.append(page);
		sb.append("&query=");
		sb.append(HttpUtil.encodeURL(query));
		sb.append("&region=");
		sb.append(HttpUtil.encodeURL(region));
		sb.append("&sort=");
		sb.append(HttpUtil.encodeURL(sort));
		sb.append("&thumbheight=48&thumbwidth=64&type=uwa");

		String jsonString = HttpUtil.URLtoString(
			HtmlUtil.stripComments(sb.toString()));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonString);

		JSONArray jsonArray = jsonObject.getJSONArray("items");

		List<Object[]> results = new ArrayList<Object[]>();
		int total = 0;

		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject curJSONObject = jsonArray.getJSONObject(i);

				String link = curJSONObject.getString("link");
				String title = curJSONObject.getString("title");
				String description = curJSONObject.getString("description");
				String thumbnail = curJSONObject.getString("thumbnail");

				if (Validator.isNull(link) || Validator.isNull(title)) {
					continue;
				}

				results.add(new Object[] {link, title, description, thumbnail});
			}

			total = jsonObject.getInt("total");
		}

		return new Object[] {results, total};
	}

}