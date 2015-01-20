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

package com.liferay.urlmetadatascraper.servlet;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Matthew Kong
 */
public class URLMetadataScraperServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String url = request.getParameter("url");

		JSONObject jsonObject = getURLMetadataJSONObject(url);

		ServletResponseUtil.write(
			response, JSONFactoryUtil.looseSerialize(jsonObject));
	}

	protected String getDescription(Document document) {
		Elements descriptionElement = document.select(
			"meta[property=og:description]");

		if (descriptionElement.size() <= 0) {
			descriptionElement = document.select("meta[name=description]");
		}

		String description = descriptionElement.attr("content");

		return StringUtil.shorten(description, 200);
	}

	protected String getImageURL(Document document, String baseURL) {
		String imageURL = StringPool.BLANK;

		Elements imageElement = document.select("meta[property=og:image]");

		if (imageElement.size() <= 0) {
			imageElement = document.select("img");

			imageURL = imageElement.get(0).attr("src");
		}
		else {
			imageURL = imageElement.attr("content");
		}

		if (!HttpUtil.hasDomain(imageURL)) {
			imageURL = baseURL + imageURL;
		}

		return imageURL;
	}

	protected String getTitle(Document document) {
		Elements titleElement = document.select("meta[property=og:title]");

		String title = titleElement.attr("content");

		if (Validator.isNull(title)) {
			titleElement = document.select("meta[name=title]");

			title = titleElement.attr("content");
		}

		if (Validator.isNull(title)) {
			titleElement = document.select("title");

			title = titleElement.html();
		}

		if (Validator.isNull(title)) {
			titleElement = document.select("meta[property=og:site_name]");

			title = titleElement.attr("content");
		}

		return StringUtil.shorten(title, 200);
	}

	protected JSONObject getURLMetadataJSONObject(String url)
		throws IOException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Http.Options options = new Http.Options();

		int pos = url.indexOf(CharPool.QUESTION);

		if (pos != -1) {
			options.setBody(
				url.substring(pos + 1),
				ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED,
				StringPool.UTF8);
			options.setLocation(url.substring(0, pos));
		}
		else {
			options.setLocation(url);
		}

		String html = HttpUtil.URLtoString(options);

		Document document = Jsoup.parse(html);

		jsonObject.put("description", getDescription(document));

		StringBundler sb = new StringBundler(4);

		sb.append(HttpUtil.getProtocol(url));
		sb.append(StringPool.COLON);
		sb.append(StringPool.DOUBLE_SLASH);
		sb.append(HttpUtil.getDomain(url));

		jsonObject.put("imageURL", getImageURL(document, sb.toString()));

		String domain = HttpUtil.getDomain(options.getLocation());

		jsonObject.put("shortURL", StringUtil.toUpperCase(domain));

		jsonObject.put("title", getTitle(document));

		return jsonObject;
	}

}