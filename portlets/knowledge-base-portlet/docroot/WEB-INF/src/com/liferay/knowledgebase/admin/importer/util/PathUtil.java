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

package com.liferay.knowledgebase.admin.importer.util;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Adolfo Pérez
 */
public class PathUtil {

	public static String getFileName(String path) {
		if (Validator.isNull(path) || path.endsWith(StringPool.SLASH)) {
			return StringPool.BLANK;
		}

		int i = path.lastIndexOf(CharPool.SLASH);

		if (i == -1) {
			return StringPool.BLANK;
		}

		return path.substring(i);
	}

	public static String getParent(String path) {
		path = StringUtil.trimTrailing(path, CharPool.SLASH);

		if (Validator.isNull(path)) {
			return StringPool.SLASH;
		}

		int i = path.lastIndexOf(CharPool.SLASH);

		if (i == -1) {
			return StringPool.SLASH;
		}

		return path.substring(0, i);
	}

}