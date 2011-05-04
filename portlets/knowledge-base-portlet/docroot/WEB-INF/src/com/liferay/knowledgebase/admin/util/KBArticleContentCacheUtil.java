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

package com.liferay.knowledgebase.admin.util;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.model.KBTemplateParser;
import com.liferay.knowledgebase.util.PortletPropsKeys;
import com.liferay.knowledgebase.util.PortletPropsValues;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.ContentUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Peter Shin
 */
public class KBArticleContentCacheUtil {

	public static final String CACHE_NAME =
		KBArticleContentCacheUtil.class.getName();

	public static void clearCache() {
		_portalCache.removeAll();
	}

	public static String getContent(HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		KBArticle kbArticle = (KBArticle)request.getAttribute(
			WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

		KBTemplate kbTemplate = kbArticle.getKBTemplate();

		if (kbTemplate == null) {
			return kbArticle.getContent();
		}

		String key = _encodeKey(
			themeDisplay.getScopeGroupId(), kbArticle, kbTemplate);

		String content = null;

		if (kbTemplate.isCacheable()) {
			content = (String)_portalCache.get(key);
		}

		if (content != null) {
			return content;
		}

		KBTemplateParser kbTemplateParser = AdminUtil.getKBTemplateParser(
			kbTemplate.getEngineType());

		try {
			content = kbTemplateParser.transform(request);
		}
		catch (Exception e) {
			return _processException(kbArticle, kbTemplate, e, request);
		}

		if (kbTemplate.isCacheable()) {
			_portalCache.put(key, content);
		}

		return content;
	}

	private static String _encodeKey(
		long groupId, KBArticle kbArticle, KBTemplate kbTemplate) {

		Date kbArticleModifiedDate = kbArticle.getModifiedDate();
		Date kbTemplateModifiedDate = kbTemplate.getModifiedDate();

		StringBundler sb = new StringBundler(11);

		sb.append(CACHE_NAME);
		sb.append(StringPool.POUND);
		sb.append(StringUtil.toHexString(groupId));
		sb.append(StringPool.POUND);
		sb.append(StringUtil.toHexString(kbArticle.getKbArticleId()));
		sb.append(StringPool.POUND);
		sb.append(StringUtil.toHexString(kbArticleModifiedDate.getTime()));
		sb.append(StringPool.POUND);
		sb.append(StringUtil.toHexString(kbTemplate.getKbTemplateId()));
		sb.append(StringPool.POUND);
		sb.append(StringUtil.toHexString(kbTemplateModifiedDate.getTime()));

		return sb.toString();
	}

	private static String _processException(
		KBArticle kbArticle, KBTemplate kbTemplate, Exception e,
		HttpServletRequest request) {

		String key = null;
		String value = null;

		if (kbTemplate.isFreeMarker()) {
			key = PortletPropsKeys.ADMIN_KB_TEMPLATE_ERROR_FREEMARKER;
			value = PortletPropsValues.ADMIN_KB_TEMPLATE_ERROR_FREEMARKER;
		}
		else if (kbTemplate.isVelocity()) {
			key = PortletPropsKeys.ADMIN_KB_TEMPLATE_ERROR_VELOCITY;
			value = PortletPropsValues.ADMIN_KB_TEMPLATE_ERROR_VELOCITY;
		}

		String exception = e.getMessage();

		if (Validator.isNull(exception)) {
			exception = e.getClass().getName();
		}

		Map<String, Object> variables = new HashMap<String, Object>();

		variables.put("exception", exception);
		variables.put("kbTemplate", kbTemplate);

		try {
			KBTemplateParser kbTemplateParser = AdminUtil.getKBTemplateParser(
				kbTemplate.getEngineType());

			return kbTemplateParser.transform(
				key, ContentUtil.get(value), variables, request);
		}
		catch (Exception e1) {
			return kbArticle.getContent();
		}
	}

	private static PortalCache _portalCache = MultiVMPoolUtil.getCache(
		CACHE_NAME);

}