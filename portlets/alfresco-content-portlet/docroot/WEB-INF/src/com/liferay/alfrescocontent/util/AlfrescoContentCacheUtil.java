/**
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

package com.liferay.alfrescocontent.util;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.RenderResponse;

/**
 * <a href="AlfrescoContentCacheUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jerry Niu
 *
 */
public class AlfrescoContentCacheUtil {

	public static final String CACHE_NAME =
		AlfrescoContentCacheUtil.class.getName();

	public static void clearCache() {
		_cache.removeAll();
	}

	public static String getContent(
			String userId, String password, String uuid, String path,
			boolean maximizeLinks, RenderResponse renderResponse)
		throws Exception {

		if (Validator.isNull(uuid)) {
			return null;
		}

		String key = _encodeKey(uuid);

		String content = (String)MultiVMPoolUtil.get(_cache, key);

		if (content == null) {
			try {
				content = AlfrescoContentUtil.getContent(
					userId, password, uuid, path, maximizeLinks,
					renderResponse);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e.getMessage());
				}
			}

			if (content != null) {
				MultiVMPoolUtil.put(_cache, key, content);
			}
		}

		return content;
	}

	private static String _encodeKey(String key) {
		StringBuilder sb = new StringBuilder();

		sb.append(CACHE_NAME);
		sb.append(StringPool.POUND);
		sb.append(key);

		return sb.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(
		AlfrescoContentCacheUtil.class);

	private static PortalCache _cache = MultiVMPoolUtil.getCache(CACHE_NAME);

}