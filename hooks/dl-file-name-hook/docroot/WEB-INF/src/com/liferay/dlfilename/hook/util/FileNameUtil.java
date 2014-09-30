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

package com.liferay.dlfilename.hook.util;

import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCache;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;

/**
 * @author Preston Crary
 */
public class FileNameUtil {

	public static final String DISPLAY_NAME = "DLDisplayName";

	public static boolean isThreadLocalEnabled(String key) {
		ThreadLocalCache<Boolean> threadLocalCache =
			ThreadLocalCacheManager.getThreadLocalCache(Lifecycle.REQUEST, key);

		Boolean enabled = threadLocalCache.get("isEnabled");

		if (enabled == null) {
			return false;
		}

		return enabled;
	}

}