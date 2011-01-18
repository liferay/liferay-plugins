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

import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.SubscriptionSender;

import java.util.Locale;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminSubscriptionSender extends SubscriptionSender {

	public AdminSubscriptionSender(Article article, String portalURL) {
		_article = article;
		_portalURL = portalURL;
	}

	protected void deleteSubscription(Subscription subscription)
		throws Exception {

		// Article subscription

		if (subscription.getClassPK() == _article.getResourcePrimKey()) {
			ArticleLocalServiceUtil.unsubscribeArticle(
				subscription.getUserId(), _article.getResourcePrimKey());
		}

		// Group subscription

		if (subscription.getClassPK() == _article.getGroupId()) {
			ArticleLocalServiceUtil.unsubscribeGroupArticles(
				subscription.getUserId(), _article.getGroupId());
		}
	}

	protected String getEmailArticleAttachments(Locale locale)
		throws Exception {

		String[] fileNames = _article.getAttachmentsFileNames();

		if (fileNames.length <= 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(fileNames.length * 5);

		for (String fileName : fileNames) {
			long kb = DLLocalServiceUtil.getFileSize(
				companyId, CompanyConstants.SYSTEM, fileName);

			sb.append(FileUtil.getShortFileName(fileName));
			sb.append(" (");
			sb.append(TextFormatter.formatKB(kb, locale));
			sb.append("k)");
			sb.append("<br />");
		}

		return sb.toString();
	}

	protected String getEmailArticleURL(long resourcePrimKey) throws Exception {
		long plid = LayoutLocalServiceUtil.getDefaultPlid(
			_article.getGroupId());

		return KnowledgeBaseUtil.getArticleURL(
			plid, resourcePrimKey, _portalURL);
	}

	protected boolean hasPermission(Subscription subscription, User user)
		throws Exception {

		String contextName = PrincipalThreadLocal.getName();

		PermissionChecker contextPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PrincipalThreadLocal.setName(user.getUserId());

		try {
			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user, true);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			return ArticlePermission.contains(
				permissionChecker, _article, ActionKeys.VIEW);
		}
		finally {
			PrincipalThreadLocal.setName(contextName);

			PermissionThreadLocal.setPermissionChecker(
				contextPermissionChecker);
		}
	}

	protected String replaceContent(String content, Locale locale)
		throws Exception {

		String articleAttachments = getEmailArticleAttachments(locale);
		String articleURL = getEmailArticleURL(_article.getResourcePrimKey());
		String articleVersion = LanguageUtil.format(
			locale, "version-x", String.valueOf(_article.getVersion()));
		String categoryTitle = LanguageUtil.get(locale, "category.kb");

		setContextAttribute("[$ARTICLE_ATTACHMENTS$]", articleAttachments);
		setContextAttribute("[$ARTICLE_URL$]", articleURL);
		setContextAttribute("[$ARTICLE_VERSION$]", articleVersion);
		setContextAttribute("[$CATEGORY_TITLE$]", categoryTitle);

		return super.replaceContent(content, locale);
	}

	private Article _article;
	private String _portalURL;

}