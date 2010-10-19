/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Michael C. Han
 */
public class UUIDUtil {
	public static String fromSafeUuid(String safeUuid) {
		String uuid =
			StringUtil.replace(safeUuid, _UNDERSCORE, _DASH);

		return uuid;
	}

	public static String toSafeUuid(String uuid) {
		String safeName =
			StringUtil.replace(uuid, _DASH, _UNDERSCORE);

		return safeName;
	}

	private static final String[] _DASH = new String[] {StringPool.DASH};
	private static final String[] _UNDERSCORE =
		new String[] {StringPool.UNDERLINE + StringPool.UNDERLINE};
}
