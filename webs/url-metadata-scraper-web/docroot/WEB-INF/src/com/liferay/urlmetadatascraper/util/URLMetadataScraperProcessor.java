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

package com.liferay.urlmetadatascraper.util;

import java.awt.image.BufferedImage;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class URLMetadataScraperProcessor {

	public String getURLMetadataJSON(String url, String userAgent)
		throws Exception {

		JSONObject jsonObject = new JSONObject();

		Document document = null;

		String protocol =
			HttpUtil.getProtocol(url) + HttpUtil.PROTOCOL_DELIMITER;

		try {
			url = protocol + HttpUtil.removeProtocol(url);

			Connection connection = Jsoup.connect(url);

			if (Validator.isNull(userAgent)) {
				userAgent = _USER_AGENT_DEFAULT;
			}

			connection.userAgent(userAgent);

			document = connection.get();
		}
		catch (Exception e) {
			jsonObject.put("success", false);

			return jsonObject.toString();
		}

		String title = getContent(document, _SELECTORS_TITLE);

		if (Validator.isNull(title)) {
			jsonObject.put("success", false);

			return jsonObject.toString();
		}

		jsonObject.put(
			"description",
			StringUtil.shorten(
				getContent(document, _SELECTORS_DESCRIPTION),
				_DESCRIPTION_LENGTH_MAXIMUM));
		jsonObject.put(
			"imageURLs", getImageURLs(document, protocol, userAgent));
		jsonObject.put("videoURL", getContent(document, _SELECTORS_VIDEO_URL_));

		String domain = "";

		int pos = url.indexOf('?');

		if (pos != -1) {
			domain = HttpUtil.getDomain(url.substring(0, pos));
		}
		else {
			domain = HttpUtil.getDomain(url);
		}

		jsonObject.put("shortURL", domain.toLowerCase());

		jsonObject.put("success", true);
		jsonObject.put("title", title);
		jsonObject.put("url", url);

		return jsonObject.toString();
	}

	protected String getContent(Document document, String[] selectors) {
		String content = "";

		for (String selector : selectors) {
			Elements elements = document.select(selector);

			if (selector.equals("title")) {
				content = elements.html();
			}
			else {
				content = elements.attr("content");
			}

			if (Validator.isNotNull(content)) {
				return content;
			}
		}

		return "";
	}

	protected List<String> getImageURLs(
			Document document, String protocol, String userAgent)
		throws Exception {

		List<String> imageURLs = new ArrayList<String>();

		String imageURL = getContent(document, _SELECTORS_IMAGE);

		if (isValidImageURL(imageURL, protocol, userAgent)) {
			imageURLs.add(imageURL);
		}

		Elements imageElements = document.select("img");

		for (Element imageElement : imageElements) {
			imageURL = imageElement.absUrl("src");

			if (isValidImageElement(imageElement) &&
				isValidImageURL(imageURL, protocol, userAgent) &&
				!imageURLs.contains(imageURL)) {

				imageURLs.add(imageURL);

				if (imageURLs.size() >= _IMAGE_URLS_MAXIMUM) {
					break;
				}
			}
		}

		return imageURLs;
	}

	protected boolean isValidImageElement(Element imageElement)
		throws Exception {

		int height = GetterUtil.getInteger(imageElement.attr("height"));

		if ((height > 0) && (height < _IMAGE_DIMENSION_MINIMUM)) {
			return false;
		}

		int width = GetterUtil.getInteger(imageElement.attr("width"));

		if ((width > 0) && (width < _IMAGE_DIMENSION_MINIMUM)) {
			return false;
		}

		if ((height > 0) && (width > 0) &&
			((height * width) < _IMAGE_AREA_MINIMUM)) {

			return false;
		}

		return true;
	}

	protected boolean isValidImageURL(
			String imageURL, String protocol, String userAgent)
		throws Exception {

		if (Validator.isNull(imageURL)) {
			return false;
		}

		if (imageURL.startsWith("//")) {
			imageURL = imageURL.replaceFirst("//", protocol);
		}

		try {
			URL url = new URL(imageURL);

			HttpURLConnection httpURLConnection =
				(HttpURLConnection)url.openConnection();

			httpURLConnection.setRequestProperty("User-Agent", userAgent);

			BufferedImage bufferedImage = ImageIO.read(
				httpURLConnection.getInputStream());

			if (bufferedImage == null) {
				return false;
			}

			int height = bufferedImage.getHeight();
			int width = bufferedImage.getWidth();

			if (((height * width) >= _IMAGE_AREA_MINIMUM) &&
				(height >= _IMAGE_DIMENSION_MINIMUM) &&
				(width >= _IMAGE_DIMENSION_MINIMUM)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	private static final int _DESCRIPTION_LENGTH_MAXIMUM = 300;

	private static final int _IMAGE_AREA_MINIMUM = 1000;

	private static final int _IMAGE_DIMENSION_MINIMUM = 80;

	private static final int _IMAGE_URLS_MAXIMUM = 10;

	private static final String[] _SELECTORS_DESCRIPTION = {
		"meta[property=og:description]", "meta[name=og:description]",
		"meta[name=description]"
	};

	private static final String[] _SELECTORS_IMAGE = {
		"meta[property=og:image]", "meta[name=og:image]"
	};

	private static final String[] _SELECTORS_TITLE = {
		"meta[property=og:title]", "meta[name=og:title]", "meta[name=title]",
		"title", "meta[property=og:site_name]"
	};

	private static final String[] _SELECTORS_VIDEO_URL_ = {
		"meta[property=og:video:]", "meta[property=og:video:url]",
		"meta[property=og:video:secure_url]", "meta[name=twitter:player]"
	};

	private static final String _USER_AGENT_DEFAULT =
		"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 " +
			"Firefox/40.0";

}