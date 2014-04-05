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

package com.liferay.compat.portal.kernel.util;

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class HttpUtil extends com.liferay.portal.kernel.util.HttpUtil {

	public static String removePathParameters(String uri) {
		if (Validator.isNull(uri)) {
			return uri;
		}

		int pos = uri.indexOf(StringPool.SEMICOLON);

		if (pos == -1) {
			return uri;
		}

		String[] uriParts = StringUtil.split(
			uri.substring(1), StringPool.SLASH);

		StringBundler sb = new StringBundler(uriParts.length * 2);

		for (String uriPart : uriParts) {
			pos = uriPart.indexOf(StringPool.SEMICOLON);

			if (pos == -1) {
				sb.append(StringPool.SLASH);
				sb.append(uriPart);
			}
			else {
				sb.append(StringPool.SLASH);
				sb.append(uriPart.substring(0, pos));
			}
		}

		return sb.toString();
	}

	public static byte[] URLtoByteArray(String location) throws IOException {
		Http.Options options = new Http.Options();

		Map<String, String> headers = new HashMap<String, String>();

		headers.put(HttpHeaders.CONTENT_TYPE, StringPool.BLANK);

		options.setHeaders(headers);

		options.setLocation(location);

		return URLtoByteArray(options);
	}

	public static byte[] URLtoByteArray(String location, boolean post)
		throws IOException {

		if (!post) {
			return URLtoByteArray(location);
		}

		return com.liferay.portal.kernel.util.HttpUtil.URLtoByteArray(
			location, post);
	}

	public static String URLtoString(String location) throws IOException {
		return new String(URLtoByteArray(location));
	}

	public static String URLtoString(String location, boolean post)
		throws IOException {

		return new String(URLtoByteArray(location, post));
	}

}