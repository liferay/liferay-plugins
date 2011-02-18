/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.util;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KnowledgeBaseUtil {

	public static String getArticleURL(
		long plid, long resourcePrimKey, String portalURL, boolean maximized) {

		StringBundler sb = new StringBundler(15);

		sb.append(portalURL);
		sb.append(PortalUtil.getPathMain());
		sb.append("/portal/knowledge_base/find_article");
		sb.append(StringPool.QUESTION);
		sb.append("plid");
		sb.append(StringPool.EQUAL);
		sb.append(HttpUtil.encodeURL(String.valueOf(plid)));
		sb.append(StringPool.AMPERSAND);
		sb.append("resourcePrimKey");
		sb.append(StringPool.EQUAL);
		sb.append(HttpUtil.encodeURL(String.valueOf(resourcePrimKey)));
		sb.append(StringPool.AMPERSAND);
		sb.append("maximized");
		sb.append(StringPool.EQUAL);
		sb.append(HttpUtil.encodeURL(String.valueOf(maximized)));

		return sb.toString();
	}

}