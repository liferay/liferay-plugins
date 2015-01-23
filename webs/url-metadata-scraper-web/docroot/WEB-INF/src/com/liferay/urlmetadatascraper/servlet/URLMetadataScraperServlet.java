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

import com.liferay.urlmetadatascraper.util.HttpUtil;
import com.liferay.urlmetadatascraper.util.StringUtil;
import com.liferay.urlmetadatascraper.util.Validator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class URLMetadataScraperServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String url = request.getParameter("url");

		JSONObject jsonObject = getURLMetadataJSONObject(url);

		PrintWriter printWriter = response.getWriter();

		printWriter.write(jsonObject.toString());

		printWriter.close();
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
		String imageURL = "";

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

		JSONObject jsonObject = new JSONObject();

		url =
			HttpUtil.getProtocol(url) + HttpUtil.PROTOCOL_DELIMITER +
				HttpUtil.getDomain(url);

		Document document = Jsoup.connect(url).get();

		try {
			jsonObject.put("description", getDescription(document));

			jsonObject.put("imageURL", getImageURL(document, url));

			int pos = url.indexOf('?');

			String domain = "";

			if (pos != -1) {
				domain = HttpUtil.getDomain(url.substring(0, pos));
			}
			else {
				domain = HttpUtil.getDomain(url);
			}

			jsonObject.put("shortURL", domain.toUpperCase());

			jsonObject.put("title", getTitle(document));
		}
		catch (JSONException jse) {
		}

		return jsonObject;
	}

}