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

package com.liferay.rtl.util;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Eduardo Lundgren
 * @author Eduardo Garcia
 * @see com.liferay.portal.util.AggregateUtil
 */
public class AggregateUtil {

	public static String updateRelativeURLs(String content, String baseURL) {
		content = StringUtil.replace(
			content, _CSS_PATH_TYPES, _CSS_PATH_PLACEHOLDERS);

		content = StringUtil.replace(
			content,
			new String[] {
				"[$RELATIVE_1$]", "[$RELATIVE_2$]", "[$RELATIVE_3$]"
			},
			new String[] {
				"url('" + baseURL, "url(\"" + baseURL, "url(" + baseURL
			});

		content = StringUtil.replace(
			content, _CSS_PATH_PLACEHOLDERS, _CSS_PATH_TYPES);

		return content;
	}

	private AggregateUtil() {
	}

	private static final String[] _CSS_PATH_PLACEHOLDERS = new String[] {
		"[$EMPTY_1$]", "[$EMPTY_2$]", "[$EMPTY_3$]", "[$TOKEN_1$]",
		"[$TOKEN_2$]", "[$TOKEN_3$]", "[$ABSOLUTE_1$]", "[$ABSOLUTE_2$]",
		"[$ABSOLUTE_3$]", "[$ABSOLUTE_4$]", "[$ABSOLUTE_5$]", "[$ABSOLUTE_6$]",
		"[$ABSOLUTE_7$]", "[$ABSOLUTE_8$]", "[$ABSOLUTE_9$]", "[$RELATIVE_1$]",
		"[$RELATIVE_2$]", "[$RELATIVE_3$]"
	};

	private static final String[] _CSS_PATH_TYPES = new String[] {
		"url('')", "url(\"\")", "url()", "url('@theme_image_path@",
		"url(\"@", "url(@", "url('http://", "url(\"http://", "url(http://",
		"url('https://", "url(\"https://", "url(https://", "url('/", "url(\"/",
		"url(/", "url('", "url(\"", "url("
	};

}