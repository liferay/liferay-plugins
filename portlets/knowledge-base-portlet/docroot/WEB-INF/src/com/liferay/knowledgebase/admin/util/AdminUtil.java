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

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.DiffHtmlUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.ControlPanelEntry;
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

import java.util.Map;

import javax.portlet.PortletPreferences;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.OutputDocument;
import net.htmlparser.jericho.Source;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminUtil {

	public static String getArticleDiff(
			long resourcePrimKey, int sourceVersion, int targetVersion,
			String param)
		throws Exception {

		if (sourceVersion < ArticleConstants.DEFAULT_VERSION) {
			sourceVersion = ArticleConstants.DEFAULT_VERSION;
		}

		if (sourceVersion == targetVersion) {
			Article article = ArticleLocalServiceUtil.getArticle(
				resourcePrimKey, targetVersion);

			return BeanPropertiesUtil.getString(article, param);
		}

		Article sourceArticle = ArticleLocalServiceUtil.getArticle(
			resourcePrimKey, sourceVersion);
		Article targetArticle = ArticleLocalServiceUtil.getArticle(
			resourcePrimKey, targetVersion);

		String sourceHtml = BeanPropertiesUtil.getString(sourceArticle, param);
		String targetHtml = BeanPropertiesUtil.getString(targetArticle, param);

		String diff = DiffHtmlUtil.diff(
			new UnsyncStringReader(sourceHtml),
			new UnsyncStringReader(targetHtml));

		Source source = new Source(diff);

		OutputDocument outputDocument = new OutputDocument(source);

		for (Element element : source.getAllElements()) {
			StringBundler sb = new StringBundler(4);

			Attributes attributes = element.getAttributes();

			Attribute changeTypeAttribute = attributes.get("changeType");

			if (changeTypeAttribute != null) {
				String changeTypeValue = changeTypeAttribute.getValue();

				if (changeTypeValue.contains("diff-added-image")) {
					sb.append("border: 10px solid #CFC; ");
				}
				else if (changeTypeValue.contains("diff-changed-image")) {
					sb.append("border: 10px solid #C6C6FD; ");
				}
				else if (changeTypeValue.contains("diff-removed-image")) {
					sb.append("border: 10px solid #FDC6C6; ");
				}
			}

			Attribute classAttribute = attributes.get("class");

			if (classAttribute != null) {
				String classValue = classAttribute.getValue();

				if (classValue.contains("diff-html-added")) {
					sb.append("background-color: #CFC; ");
				}
				else if (classValue.contains("diff-html-changed")) {
					sb.append("background-color: #C6C6FD; ");
				}
				else if (classValue.contains("diff-html-removed")) {
					sb.append("background-color: #FDC6C6; ");
					sb.append("text-decoration: line-through; ");
				}
			}

			if (Validator.isNull(sb.toString())) {
				continue;
			}

			Attribute styleAttribute = attributes.get("style");

			if (styleAttribute != null) {
				sb.append(GetterUtil.getString(styleAttribute.getValue()));
			}

			Map<String, String> map = outputDocument.replace(attributes, false);

			map.put("style", sb.toString());
		}

		return outputDocument.toString();
	}

	public static String getEmailArticleAddedBody(
		PortletPreferences preferences) {

		String emailArticleAddedBody = preferences.getValue(
			"emailArticleAddedBody", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleAddedBody)) {
			return emailArticleAddedBody;
		}

		return ContentUtil.get(
			PortletProps.get("admin.email.article.added.body"));
	}

	public static boolean getEmailArticleAddedEnabled(
		PortletPreferences preferences) {

		String emailArticleAddedEnabled = preferences.getValue(
			"emailArticleAddedEnabled", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleAddedEnabled)) {
			return GetterUtil.getBoolean(emailArticleAddedEnabled);
		}

		return GetterUtil.getBoolean(
			PortletProps.get("admin.email.article.added.enabled"));
	}

	public static String getEmailArticleAddedSubject(
		PortletPreferences preferences) {

		String emailArticleAddedSubject = preferences.getValue(
			"emailArticleAddedSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleAddedSubject)) {
			return emailArticleAddedSubject;
		}

		return ContentUtil.get(
			PortletProps.get("admin.email.article.added.subject"));
	}

	public static String getEmailArticleUpdatedBody(
		PortletPreferences preferences) {

		String emailArticleUpdatedBody = preferences.getValue(
			"emailArticleUpdatedBody", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleUpdatedBody)) {
			return emailArticleUpdatedBody;
		}

		return ContentUtil.get(
			PortletProps.get("admin.email.article.updated.body"));
	}

	public static boolean getEmailArticleUpdatedEnabled(
		PortletPreferences preferences) {

		String emailArticleUpdatedEnabled = preferences.getValue(
			"emailArticleUpdatedEnabled", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleUpdatedEnabled)) {
			return GetterUtil.getBoolean(emailArticleUpdatedEnabled);
		}

		return GetterUtil.getBoolean(
			PortletProps.get("admin.email.article.updated.enabled"));
	}

	public static String getEmailArticleUpdatedSubject(
		PortletPreferences preferences) {

		String emailArticleUpdatedSubject = preferences.getValue(
			"emailArticleUpdatedSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailArticleUpdatedSubject)) {
			return emailArticleUpdatedSubject;
		}

		return ContentUtil.get(
			PortletProps.get("admin.email.article.updated.subject"));
	}

	public static String getEmailFromAddress(PortletPreferences preferences) {
		return preferences.getValue(
			"emailFromAddress", PortletProps.get("admin.email.from.address"));
	}

	public static String getEmailFromName(PortletPreferences preferences) {
		return preferences.getValue(
			"emailFromName", PortletProps.get("admin.email.from.name"));
	}

	public static boolean isVisible(ThemeDisplay themeDisplay)
		throws Exception {

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), PortletKeys.KNOWLEDGE_BASE_ADMIN);

		ControlPanelEntry controlPanelEntry =
			portlet.getControlPanelEntryInstance();

		if (controlPanelEntry == null) {
			controlPanelEntry = (ControlPanelEntry)PortalClassInvoker.invoke(
				false, _getInstanceMethodKey);
		}

		return controlPanelEntry.isVisible(
			portlet, portlet.getControlPanelEntryCategory(), themeDisplay);
	}

	private static final String _CLASS_NAME =
		"com.liferay.portlet.DefaultControlPanelEntryFactory";

	private static MethodKey _getInstanceMethodKey = new MethodKey(
		_CLASS_NAME, "getInstance");

}