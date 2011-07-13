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
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.PortletPropsValues;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DiffHtmlUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;

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

	public static String[] escapeSections(String[] sections) {
		if (Validator.isNull(sections)) {
			return new String[0];
		}

		sections = ArrayUtil.clone(sections);

		for (int i = 0; i < sections.length; i++) {
			sections[i] = StringPool.UNDERLINE.concat(sections[i]).concat(
				StringPool.UNDERLINE);
		}

		return sections;
	}

	public static String getEmailFromAddress(PortletPreferences preferences) {
		return preferences.getValue(
			"emailFromAddress", PortletPropsValues.ADMIN_EMAIL_FROM_ADDRESS);
	}

	public static String getEmailFromName(PortletPreferences preferences) {
		return preferences.getValue(
			"emailFromName", PortletPropsValues.ADMIN_EMAIL_FROM_NAME);
	}

	public static String getEmailKBArticleAddedBody(
		PortletPreferences preferences) {

		String emailKBArticleAddedBody = preferences.getValue(
			"emailKBArticleAddedBody", StringPool.BLANK);

		if (Validator.isNotNull(emailKBArticleAddedBody)) {
			return emailKBArticleAddedBody;
		}

		return ContentUtil.get(
			PortletPropsValues.ADMIN_EMAIL_KB_ARTICLE_ADDED_BODY);
	}

	public static boolean getEmailKBArticleAddedEnabled(
		PortletPreferences preferences) {

		String emailKBArticleAddedEnabled = preferences.getValue(
			"emailKBArticleAddedEnabled", StringPool.BLANK);

		if (Validator.isNotNull(emailKBArticleAddedEnabled)) {
			return GetterUtil.getBoolean(emailKBArticleAddedEnabled);
		}

		return PortletPropsValues.ADMIN_EMAIL_KB_ARTICLE_ADDED_ENABLED;
	}

	public static String getEmailKBArticleAddedSubject(
		PortletPreferences preferences) {

		String emailKBArticleAddedSubject = preferences.getValue(
			"emailKBArticleAddedSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailKBArticleAddedSubject)) {
			return emailKBArticleAddedSubject;
		}

		return ContentUtil.get(
			PortletPropsValues.ADMIN_EMAIL_KB_ARTICLE_ADDED_SUBJECT);
	}

	public static String getEmailKBArticleUpdatedBody(
		PortletPreferences preferences) {

		String emailKBArticleUpdatedBody = preferences.getValue(
			"emailKBArticleUpdatedBody", StringPool.BLANK);

		if (Validator.isNotNull(emailKBArticleUpdatedBody)) {
			return emailKBArticleUpdatedBody;
		}

		return ContentUtil.get(
			PortletPropsValues.ADMIN_EMAIL_KB_ARTICLE_UPDATED_BODY);
	}

	public static boolean getEmailKBArticleUpdatedEnabled(
		PortletPreferences preferences) {

		String emailKBArticleUpdatedEnabled = preferences.getValue(
			"emailKBArticleUpdatedEnabled", StringPool.BLANK);

		if (Validator.isNotNull(emailKBArticleUpdatedEnabled)) {
			return GetterUtil.getBoolean(emailKBArticleUpdatedEnabled);
		}

		return PortletPropsValues.ADMIN_EMAIL_KB_ARTICLE_UPDATED_ENABLED;
	}

	public static String getEmailKBArticleUpdatedSubject(
		PortletPreferences preferences) {

		String emailKBArticleUpdatedSubject = preferences.getValue(
			"emailKBArticleUpdatedSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailKBArticleUpdatedSubject)) {
			return emailKBArticleUpdatedSubject;
		}

		return ContentUtil.get(
			PortletPropsValues.ADMIN_EMAIL_KB_ARTICLE_UPDATED_SUBJECT);
	}

	public static String getKBArticleDiff(
			long resourcePrimKey, int sourceVersion, int targetVersion,
			String param)
		throws Exception {

		if (sourceVersion < KBArticleConstants.DEFAULT_VERSION) {
			sourceVersion = KBArticleConstants.DEFAULT_VERSION;
		}

		if (sourceVersion == targetVersion) {
			KBArticle kbArticle = KBArticleLocalServiceUtil.getKBArticle(
				resourcePrimKey, targetVersion);

			return BeanPropertiesUtil.getString(kbArticle, param);
		}

		KBArticle sourceKBArticle = KBArticleLocalServiceUtil.getKBArticle(
			resourcePrimKey, sourceVersion);
		KBArticle targetKBArticle = KBArticleLocalServiceUtil.getKBArticle(
			resourcePrimKey, targetVersion);

		String sourceHtml = BeanPropertiesUtil.getString(
			sourceKBArticle, param);
		String targetHtml = BeanPropertiesUtil.getString(
			targetKBArticle, param);

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

	public static String[] unescapeSections(String sections) {
		String[] sectionsArray = StringUtil.split(sections);

		for (int i = 0; i < sectionsArray.length; i++) {
			String section = sectionsArray[i];

			if (StringUtil.startsWith(section, StringPool.UNDERLINE) &&
				StringUtil.endsWith(section, StringPool.UNDERLINE)) {

				sectionsArray[i] = section.substring(1, section.length() - 1);
			}
		}

		return sectionsArray;
	}

}