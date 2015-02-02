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

package com.liferay.knowledgebase.admin.util;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.SubscriptionSender;

import java.util.List;
import java.util.Locale;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminSubscriptionSender extends SubscriptionSender {

	public AdminSubscriptionSender(
		KBArticle kbArticle, ServiceContext serviceContext) {

		_kbArticle = kbArticle;
		_serviceContext = serviceContext;
	}

	@Override
	protected void deleteSubscription(Subscription subscription)
		throws Exception {

		// KB article subscription

		if (subscription.getClassPK() == _kbArticle.getResourcePrimKey()) {
			KBArticleLocalServiceUtil.unsubscribeKBArticle(
				subscription.getUserId(), _kbArticle.getResourcePrimKey());
		}

		// Group subscription

		if (subscription.getClassPK() == _kbArticle.getGroupId()) {
			KBArticleLocalServiceUtil.unsubscribeGroupKBArticles(
				subscription.getUserId(), _kbArticle.getGroupId());
		}
	}

	protected String getEmailKBArticleAttachments(Locale locale)
		throws Exception {

		List<FileEntry> attachmentsFileEntries =
			_kbArticle.getAttachmentsFileEntries();

		if (attachmentsFileEntries.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(attachmentsFileEntries.size() * 5);

		for (FileEntry fileEntry : attachmentsFileEntries) {
			sb.append(fileEntry.getTitle());
			sb.append(" (");
			sb.append(
				TextFormatter.formatStorageSize(fileEntry.getSize(), locale));
			sb.append(")");
			sb.append("<br />");
		}

		return sb.toString();
	}

	@Override
	protected boolean hasPermission(
			Subscription subscription, String inferredClassName,
			long inferredClassPK, User user)
		throws Exception {

		String name = PrincipalThreadLocal.getName();

		PermissionChecker contextPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PrincipalThreadLocal.setName(user.getUserId());

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			return KBArticlePermission.contains(
				permissionChecker, _kbArticle, ActionKeys.VIEW);
		}
		finally {
			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(
				contextPermissionChecker);
		}
	}

	@Override
	protected String replaceContent(String content, Locale locale)
		throws Exception {

		String kbArticleAttachments = getEmailKBArticleAttachments(locale);
		String kbArticleURL = KnowledgeBaseUtil.getKBArticleURL(
			_serviceContext.getPlid(), _kbArticle.getResourcePrimKey(),
			_kbArticle.getStatus(), _serviceContext.getPortalURL(), false);
		String kbArticleVersion = LanguageUtil.format(
			locale, "version-x", String.valueOf(_kbArticle.getVersion()),
			false);
		String categoryTitle = LanguageUtil.get(locale, "category.kb");

		setContextAttribute("[$ARTICLE_ATTACHMENTS$]", kbArticleAttachments);
		setContextAttribute("[$ARTICLE_TITLE$]", _kbArticle.getTitle());
		setContextAttribute("[$ARTICLE_URL$]", kbArticleURL);
		setContextAttribute("[$ARTICLE_VERSION$]", kbArticleVersion);
		setContextAttribute("[$CATEGORY_TITLE$]", categoryTitle);

		return super.replaceContent(content, locale);
	}

	private KBArticle _kbArticle;
	private ServiceContext _serviceContext;

}