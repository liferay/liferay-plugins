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

package com.liferay.knowledgebase.util;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.DiffHtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="KnowledgeBaseUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KnowledgeBaseUtil {

	public static String getArticleDiff(
			long resourcePrimKey, int sourceVersion, int targetVersion,
			String parameter, String portalURL)
		throws Exception {

		if (sourceVersion == targetVersion) {
			Article article = ArticleLocalServiceUtil.getArticle(
				resourcePrimKey, targetVersion);

			Object object = BeanPropertiesUtil.getObject(article, parameter);

			return String.valueOf(object);
		}

		Article sourceArticle = ArticleLocalServiceUtil.getArticle(
			resourcePrimKey, sourceVersion);
		Article targetArticle = ArticleLocalServiceUtil.getArticle(
			resourcePrimKey, targetVersion);

		Object sourceObject = BeanPropertiesUtil.getObject(
			sourceArticle, parameter);
		Object targetObject = BeanPropertiesUtil.getObject(
			targetArticle, parameter);

		String sourceHtml = String.valueOf(sourceObject);
		String targetHtml = String.valueOf(targetObject);

		return getDiff(sourceHtml, targetHtml, portalURL);
	}

	public static String getArticleDiff(
			long resourcePrimKey, int version, String parameter,
			String portalURL)
		throws Exception {

		int sourceVersion = version;
		int targetVersion = version;

		if (sourceVersion != ArticleConstants.DEFAULT_VERSION) {
			sourceVersion = sourceVersion - 1;
		}

		return getArticleDiff(
			resourcePrimKey, sourceVersion, targetVersion, parameter,
			portalURL);
	}

	public static List<Article> getArticles(
			long[] resourcePrimKeys, int start, int end,
			boolean checkPermission)
		throws Exception {

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			start = 0;
			end = resourcePrimKeys.length;
		}

		if ((resourcePrimKeys.length == 0) || ((end - start) <= 0)) {
			return new ArrayList<Article>();
		}

		Long[] selResourcePrimKeys = new Long[end - start];

		for (int i = start; (i < end) && (i < resourcePrimKeys.length); i++) {
			selResourcePrimKeys[i - start] = resourcePrimKeys[i];
		}

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("resourcePrimKey", selResourcePrimKeys);

		List<Article> unsortedArticles = null;

		if (checkPermission) {
			unsortedArticles = ArticleServiceUtil.getArticles(
				params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}
		else {
			unsortedArticles = ArticleLocalServiceUtil.getArticles(
				params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}

		unsortedArticles = ListUtil.copy(unsortedArticles);

		List<Article> articles = new ArrayList<Article>();

		for (Long resourcePrimKey : selResourcePrimKeys) {
			for (int i = 0; i < unsortedArticles.size(); i++) {
				Article article = unsortedArticles.get(i);

				if (article.getResourcePrimKey() == resourcePrimKey) {
					articles.add(article);
					unsortedArticles.remove(article);

					break;
				}
			}
		}

		return articles;
	}

	protected static String getDiff(
			String sourceHtml, String targetHtml, String portalURL)
		throws Exception {

		String diff = DiffHtmlUtil.diff(
			new UnsyncStringReader(sourceHtml),
			new UnsyncStringReader(targetHtml));

		diff = StringUtil.replace(
			diff,
			new String[] {
				"changeType=\"diff-added-image\"",
				"changeType=\"diff-changed-image\"",
				"changeType=\"diff-removed-image\"",
				"class=\"diff-html-added\"",
				"class=\"diff-html-changed\"",
				"class=\"diff-html-removed\""
			},
			new String[] {
				"style=\"border: 10px solid #CFC;\"",
				"style=\"border: 10px solid #C6C6FD;\"",
				"style=\"border: 10px solid #FDC6C6;\"",
				"style=\"background-color: #CFC;\"",
				"style=\"background-color: #C6C6FD\"",
				"style=\"background-color: #FDC6C6; " +
					"text-decoration: line-through;\""
			});

		if (Validator.isNotNull(portalURL)) {
			diff = StringUtil.replace(
				diff,
				new String[] {
					"href=\"/",
					"src=\"/"
				},
				new String[] {
					"href=\"" + portalURL + "/",
					"src=\"" + portalURL + "/"
				});
		}

		int i = diff.indexOf("<img ");

		while (i != -1) {
			String oldImg = diff.substring(i, diff.indexOf("/>", i) + 2);

			int x = oldImg.indexOf("style=\"");
			int y = oldImg.indexOf("style=\"", x + 7);
			int z = oldImg.indexOf(StringPool.QUOTE, y + 7);

			if (y != -1) {
				String style = oldImg.substring(y, z + 1);

				String newImg = StringUtil.replace(
					oldImg,
					new String[] {
						style,
						"style=\""
					},
					new String[] {
						StringPool.BLANK,
						style.substring(0, style.length() - 1)
					});

				diff = StringUtil.replace(diff, oldImg, newImg);
			}

			i = diff.indexOf("<img ", i + 5);
		}

		return diff;
	}

}