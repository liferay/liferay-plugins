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

	public String getURLMetadataJSON(String url) throws Exception {
		JSONObject jsonObject = new JSONObject();

		Document document = null;

		try {
			url =
				HttpUtil.getProtocol(url) + HttpUtil.PROTOCOL_DELIMITER +
					HttpUtil.removeProtocol(url);

			Connection connection = Jsoup.connect(url);

			document = connection.get();
		}
		catch (Exception e) {
			jsonObject.put("success", false);

			return jsonObject.toString();
		}

		String title = getTitle(document);

		if (Validator.isNull(title)) {
			jsonObject.put("success", false);

			return jsonObject.toString();
		}

		jsonObject.put("description", getDescription(document));
		jsonObject.put("imageURLs", getValidImageURLs(document));

		String domain = "";

		int pos = url.indexOf('?');

		if (pos != -1) {
			domain = HttpUtil.getDomain(url.substring(0, pos));
		}
		else {
			domain = HttpUtil.getDomain(url);
		}

		jsonObject.put("shortURL", domain.toUpperCase());

		jsonObject.put("success", true);
		jsonObject.put("title", title);
		jsonObject.put("url", url);

		return jsonObject.toString();
	}

	protected String getDescription(Document document) {
		Elements descriptionElements = document.select(
			"meta[property=og:description]");

		if (descriptionElements.isEmpty()) {
			descriptionElements = document.select("meta[name=description]");
		}

		String description = descriptionElements.attr("content");

		return StringUtil.shorten(description, 200);
	}

	protected List<String> getImageURLs(Document document) throws Exception {
		List<String> imageURLs = new ArrayList<String>();

		Elements imageElements = document.select("meta[property=og:image]");

		if (!imageElements.isEmpty()) {
			String imageURL = imageElements.attr("content");

			imageURLs.add(imageURL);
		}

		imageElements = document.select("img");

		if (!imageElements.isEmpty()) {
			for (Element imageElement : imageElements) {
				String imageURL = imageElement.absUrl("src");

				if (Validator.isNotNull(imageURL)) {
					imageURLs.add(imageURL);
				}
			}
		}

		return imageURLs;
	}

	protected String getTitle(Document document) {
		Elements titleElements = document.select("meta[property=og:title]");

		String title = titleElements.attr("content");

		if (Validator.isNull(title)) {
			titleElements = document.select("meta[name=title]");

			title = titleElements.attr("content");
		}

		if (Validator.isNull(title)) {
			titleElements = document.select("title");

			title = titleElements.html();
		}

		if (Validator.isNull(title)) {
			titleElements = document.select("meta[property=og:site_name]");

			title = titleElements.attr("content");
		}

		return StringUtil.shorten(title, 200);
	}

	protected List<String> getValidImageURLs(Document document)
		throws Exception {

		List<String> validImageURLs = new ArrayList<String>();

		for (String imageURL : getImageURLs(document)) {
			URL url = new URL(imageURL);

			if (url == null) {
				continue;
			}

			BufferedImage bufferedImage = ImageIO.read(url);

			if (bufferedImage == null) {
				continue;
			}

			if ((bufferedImage.getWidth() >= _MINIMUM_IMAGE_SIZE) &&
				(bufferedImage.getHeight() >= _MINIMUM_IMAGE_SIZE)) {

				validImageURLs.add(imageURL);
			}

			if (validImageURLs.size() >= 10) {
				break;
			}
		}

		return validImageURLs;
	}

	private static int _MINIMUM_IMAGE_SIZE = 120;

}