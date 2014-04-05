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

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class MapUtil extends com.liferay.portal.kernel.util.MapUtil {

	public static boolean isEmpty(Map<?, ?> map) {
		if ((map == null) || map.isEmpty()) {
			return true;
		}

		return false;
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

}