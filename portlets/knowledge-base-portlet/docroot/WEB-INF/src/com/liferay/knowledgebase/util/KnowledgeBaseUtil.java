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

import com.liferay.knowledgebase.model.Article;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.UniqueList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static Long[][] getParams(Long[] params) {
		if ((params == null) || (params.length == 0)) {
			return null;
		}

		if (params.length <= _SQL_DATA_MAX_PARAMETERS) {
			return new Long[][] {new Long[0], params};
		}

		return new Long[][] {
			ArrayUtil.subset(params, _SQL_DATA_MAX_PARAMETERS, params.length),
			ArrayUtil.subset(params, 0, _SQL_DATA_MAX_PARAMETERS)
		};
	}

	public static List<Article> sort(
		long[] resourcePrimKeys, List<Article> articles) {

		Map<Long, Article> map = new HashMap<Long, Article>();

		for (Article article : articles) {
			map.put(article.getResourcePrimKey(), article);
		}

		articles.clear();

		for (long resourcePrimKey : resourcePrimKeys) {
			if (map.containsKey(resourcePrimKey)) {
				articles.add(map.get(resourcePrimKey));
			}
		}

		return articles;
	}

	public static String[] splitKeywords(String keywords) {
		List<String> keywordsList = new UniqueList<String>();

		StringBundler sb = new StringBundler();

		for (char c : keywords.toCharArray()) {
			if (Character.isWhitespace(c)) {
				if (sb.length() > 0) {
					keywordsList.add(sb.toString());

					sb = new StringBundler();
				}
			}
			else if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			}
			else {
				return new String[] {keywords};
			}
		}

		if (sb.length() > 0) {
			keywordsList.add(sb.toString());
		}

		return StringUtil.split(StringUtil.merge(keywordsList));
	}

	private static final int _SQL_DATA_MAX_PARAMETERS =
		GetterUtil.getInteger(PropsUtil.get(PropsKeys.SQL_DATA_MAX_PARAMETERS));

}