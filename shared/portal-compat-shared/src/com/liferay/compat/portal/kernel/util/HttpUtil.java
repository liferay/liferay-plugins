/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class HttpUtil extends com.liferay.portal.kernel.util.HttpUtil {

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