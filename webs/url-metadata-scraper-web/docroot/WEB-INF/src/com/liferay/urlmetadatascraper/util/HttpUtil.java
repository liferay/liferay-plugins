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

/**
 * @author Evan Thibodeau
 */
public class HttpUtil {

	public static final String HTTP = "http";

	public static final String HTTP_WITH_SLASH = "http://";

	public static final String HTTPS_WITH_SLASH = "https://";

	public static final String PROTOCOL_DELIMITER = "://";

	public static final char SLASH = '/';

	public static String getDomain(String url) {
		if (Validator.isNull(url)) {
			return url;
		}

		url = removeProtocol(url);

		int pos = url.indexOf(SLASH);

		if (pos != -1) {
			return url.substring(0, pos);
		}

		return url;
	}

	public static String getProtocol(String url) {
		if (Validator.isNull(url)) {
			return url;
		}

		int pos = url.indexOf(PROTOCOL_DELIMITER);

		if (pos != -1) {
			return url.substring(0, pos);
		}

		return HTTP;
	}

	public static String removeProtocol(String url) {
		if (Validator.isNull(url)) {
			return url;
		}

		if (url.startsWith(HTTP_WITH_SLASH)) {
			return url.substring(HTTP_WITH_SLASH.length());
		}
		else if (url.startsWith(HTTPS_WITH_SLASH)) {
			return url.substring(HTTPS_WITH_SLASH.length());
		}
		else {
			return url;
		}
	}

}