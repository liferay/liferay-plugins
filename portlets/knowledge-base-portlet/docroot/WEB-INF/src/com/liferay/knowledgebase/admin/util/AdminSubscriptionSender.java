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
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

		ArticleLocalServiceUtil.unsubscribeAllPortlets(
			subscription.getCompanyId(), subscription.getSubscriptionId());
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
				_article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			sb.append(FileUtil.getShortFileName(fileName));
			sb.append(" (");
			sb.append(TextFormatter.formatKB(kb, locale));
			sb.append("k)");
			sb.append("<br />");
		}

		return sb.toString();
	}

	protected boolean hasPermission(Subscription subscription, User user)
		throws Exception {

		PrincipalThreadLocal.setName(user.getUserId());

		try {
			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user, true);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			return ArticlePermission.contains(
				permissionChecker, _article, ActionKeys.VIEW);
		}
		finally {
			PrincipalThreadLocal.setName(null);

			PermissionThreadLocal.setPermissionChecker(null);
		}
	}

	protected void notifySubscriber(Subscription subscription)
		throws Exception {

		_context.put("subscription", subscription);

		super.notifySubscriber(subscription);

		_context.remove("subscription");
	}

	protected String replaceContent(String content, Locale locale)
		throws Exception {

		String articleAttachments = getEmailArticleAttachments(locale);
		String articleVersion = LanguageUtil.format(
			locale, "version-x", String.valueOf(_article.getVersion()));
		String categoryTitle = LanguageUtil.get(locale, "category.kb");

		content = StringUtil.replace(
			content,
			new String[] {
				"[$ARTICLE_ATTACHMENTS$]",
				"[$ARTICLE_VERSION$]",
				"[$CATEGORY_TITLE$]"
			},
			new String[] {
				articleAttachments,
				articleVersion,
				categoryTitle
			});

		Subscription subscription = (Subscription)_context.get("subscription");

		if (subscription == null) {
			return super.replaceContent(content, locale);
		}

		String[] portletPrimKeys = ExpandoValueLocalServiceUtil.getData(
			subscription.getCompanyId(), Subscription.class.getName(), "KB",
			"portletPrimKeys", subscription.getSubscriptionId(), new String[0]);
		String portletId = ArticleConstants.getPortletId(portletPrimKeys[0]);

		String articleURL = KnowledgeBaseUtil.getArticleURL(
			portletId, _article.getResourcePrimKey(), _portalURL);
		String portletTitle = PortalUtil.getPortletTitle(portletId, locale);

		content = StringUtil.replace(
			content,
			new String[] {
				"[$ARTICLE_URL$]",
				"[$PORTLET_NAME$]"
			},
			new String[] {
				articleURL,
				portletTitle
			});

		return super.replaceContent(content, locale);
	}

	private Article _article;
	private Map<String, Object> _context = new HashMap<String, Object>();
	private String _portalURL;

}