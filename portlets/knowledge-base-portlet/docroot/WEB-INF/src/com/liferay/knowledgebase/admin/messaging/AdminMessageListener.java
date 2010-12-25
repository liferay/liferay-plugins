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

package com.liferay.knowledgebase.admin.messaging;

import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.TextFormatter;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.mail.internet.InternetAddress;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminMessageListener extends BaseMessageListener {

	public void doReceive(Message message) throws Exception {
		long companyId = message.getLong("companyId");
		long groupId = message.getLong("groupId");
		long userId = message.getLong("userId");
		long resourcePrimKey = message.getLong("resourcePrimKey");
		String portalURL = message.getString("portalURL");
		String fromName = message.getString("fromName");
		String fromAddress = message.getString("fromAddress");
		String subject = message.getString("subject");
		String body = message.getString("body");
		String replyToAddress = message.getString("replyToAddress");
		String mailId = message.getString("mailId");
		boolean htmlFormat = message.getBoolean("htmlFormat");

		if (_log.isInfoEnabled()) {
			_log.info(
				"Sending notifications for {mailId=" + mailId +
					", resourcePrimKey=" + resourcePrimKey + "}");
		}

		Article article = ArticleLocalServiceUtil.getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		SubscriptionSender subscriptionSender = new AdminSubscriptionSender(
			article, portalURL);

		subscriptionSender.setCompanyId(companyId);
		subscriptionSender.setUserId(userId);
		subscriptionSender.setGroupId(groupId);
		subscriptionSender.setFrom(fromName, fromAddress);
		subscriptionSender.setSubject(subject);
		subscriptionSender.setBody(body);
		subscriptionSender.setReplyToAddress(replyToAddress);
		subscriptionSender.setMailId(mailId);
		subscriptionSender.setHtmlFormat(htmlFormat);

		subscriptionSender.notifyPersistedSubscribers(
			Article.class.getName(), groupId);
		subscriptionSender.notifyPersistedSubscribers(
			Article.class.getName(), resourcePrimKey);

		while (article.getParentResourcePrimKey() !=
					ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			article = ArticleLocalServiceUtil.getLatestArticle(
				article.getParentResourcePrimKey(),
				WorkflowConstants.STATUS_APPROVED);

			subscriptionSender.notifyPersistedSubscribers(
				Article.class.getName(), article.getResourcePrimKey());
		}

		if (_log.isInfoEnabled()) {
			_log.info("Finished sending notifications");
		}
	}

	protected String getEmailArticleAttachments(Article article, Locale locale)
		throws Exception {

		String[] fileNames = article.getAttachmentsFileNames();

		if (fileNames.length <= 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(fileNames.length * 5);

		for (String fileName : fileNames) {
			long kb = DLLocalServiceUtil.getFileSize(
				article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			sb.append(FileUtil.getShortFileName(fileName));
			sb.append(" (");
			sb.append(TextFormatter.formatKB(kb, locale));
			sb.append("k)");
			sb.append("<br />");
		}

		return sb.toString();
	}

	protected void sendEmail(
			long userId, long groupId, long resourcePrimKey, String portalURL,
			String fromName, String fromAddress, String subject, String body,
			List<Subscription> subscriptions, Set<Long> sent,
			String replyToAddress, String mailId, boolean htmlFormat)
		throws Exception {

		Article article = ArticleLocalServiceUtil.getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		for (Subscription subscription : subscriptions) {
			long subscribedUserId = subscription.getUserId();

			if (sent.contains(subscribedUserId)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Do not send a duplicate email to user " +
							subscribedUserId);
				}

				continue;
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Add user " + subscribedUserId +
							" to the list of users who have received an email");
				}

				sent.add(subscribedUserId);
			}

			User user = null;

			try {
				user = UserLocalServiceUtil.getUserById(subscribedUserId);
			}
			catch (NoSuchUserException nsue) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Subscription " + subscription.getSubscriptionId() +
							" is stale and will be deleted");
				}

				ArticleLocalServiceUtil.unsubscribeAllPortlets(
					subscription.getCompanyId(),
					subscription.getSubscriptionId());

				continue;
			}

			if (!user.isActive()) {
				continue;
			}

			Group group = GroupLocalServiceUtil.getGroup(groupId);

			int type = group.getType();

			if (!GroupLocalServiceUtil.hasUserGroup(
					subscribedUserId, groupId) &&
				(type != GroupConstants.TYPE_COMMUNITY_OPEN)) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Subscription " + subscription.getSubscriptionId() +
							" is stale and will be deleted");
				}

				ArticleLocalServiceUtil.unsubscribeAllPortlets(
					subscription.getCompanyId(),
					subscription.getSubscriptionId());

				continue;
			}

			PrincipalThreadLocal.setName(user.getUserId());

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user, true);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			try {
				if (!ArticlePermission.contains(
						permissionChecker, resourcePrimKey, ActionKeys.VIEW)) {

					if (_log.isInfoEnabled()) {
						_log.info(
							"User " + user.getUserId() +
								" does not have view permission");
					}

					continue;
				}
			}
			finally {
				PrincipalThreadLocal.setName(null);
				PermissionThreadLocal.setPermissionChecker(null);
			}

			String[] portletIds = ExpandoValueLocalServiceUtil.getData(
				user.getCompanyId(), Subscription.class.getName(), "KB",
				"portletIds", subscription.getSubscriptionId(), new String[0]);

			String articleURL = null;

			for (String portletId : portletIds) {
				articleURL = KnowledgeBaseUtil.getArticleURL(
					portletId, resourcePrimKey, portalURL);

				if (Validator.isNotNull(articleURL)) {
					break;
				}

				if (_log.isInfoEnabled()) {
					_log.info(
						"Portlet " + portletId + " does not exist or does " +
							"not contain article " + resourcePrimKey);

					ArticleLocalServiceUtil.unsubscribe(
						subscription.getCompanyId(), userId, portletId,
						subscription.getClassPK());
				}
			}

			if (Validator.isNull(articleURL)) {
				continue;
			}

			String categoryTitle = LanguageUtil.get(
				user.getLocale(), "category.kb");
			String portletName = PortalUtil.getPortletTitle(
				PortletKeys.KNOWLEDGE_BASE_ADMIN, user.getLocale());

			String curFromName = StringUtil.replace(
				fromName,
				new String[] {
					"[$CATEGORY_TITLE$]",
					"[$PORTLET_NAME$]"
				},
				new String[] {
					categoryTitle,
					portletName
				});

			String curFromAddress = StringUtil.replace(
				fromAddress,
				new String[] {
					"[$CATEGORY_TITLE$]",
					"[$PORTLET_NAME$]"
				},
				new String[] {
					categoryTitle,
					portletName
				});

			String articleAttachments = getEmailArticleAttachments(
				article, user.getLocale());
			String articleVersion = LanguageUtil.format(
				user.getLocale(), "version-x",
				String.valueOf(article.getVersion()), false);

			String curSubject = StringUtil.replace(
				subject,
				new String[] {
					"[$ARTICLE_ATTACHMENTS$]",
					"[$ARTICLE_URL$]",
					"[$ARTICLE_VERSION$]",
					"[$CATEGORY_TITLE$]",
					"[$PORTLET_NAME$]",
					"[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					articleAttachments,
					articleURL,
					articleVersion,
					categoryTitle,
					portletName,
					user.getEmailAddress(),
					user.getFullName()
				});

			String curBody = StringUtil.replace(
				body,
				new String[] {
					"[$ARTICLE_ATTACHMENTS$]",
					"[$ARTICLE_URL$]",
					"[$ARTICLE_VERSION$]",
					"[$CATEGORY_TITLE$]",
					"[$PORTLET_NAME$]",
					"[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					articleAttachments,
					articleURL,
					articleVersion,
					categoryTitle,
					portletName,
					user.getEmailAddress(),
					user.getFullName()
				});

			try {
				InternetAddress from = new InternetAddress(
					curFromAddress, curFromName);

				InternetAddress to = new InternetAddress(
					user.getEmailAddress(), user.getFullName());

				InternetAddress replyTo = new InternetAddress(
					replyToAddress, replyToAddress);

				MailMessage message = new MailMessage(
					from, to, curSubject, curBody, htmlFormat);

				message.setReplyTo(new InternetAddress[] {replyTo});
				message.setMessageId(mailId);

				MailServiceUtil.sendEmail(message);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AdminMessageListener.class);

	private class AdminSubscriptionSender extends SubscriptionSender {

		public AdminSubscriptionSender(Article article, String portalURL) {
			_article = article;
			_portalURL = portalURL;
		}

		protected void deleteSubscription(Subscription subscription)
			throws Exception {

			ArticleLocalServiceUtil.unsubscribeAllPortlets(
				subscription.getCompanyId(),
				subscription.getSubscriptionId());
		}

		protected boolean hasPermission(Subscription subscription, User user)
			throws Exception {

			PrincipalThreadLocal.setName(user.getUserId());

			PermissionChecker permissionChecker = null;

			try {
				permissionChecker = PermissionCheckerFactoryUtil.create(
					user, true);

				PermissionThreadLocal.setPermissionChecker(permissionChecker);

				if (!ArticlePermission.contains(
						permissionChecker, _article.getResourcePrimKey(),
						ActionKeys.VIEW)) {

					return false;
				}
			}
			finally {
				PrincipalThreadLocal.setName(null);

				PermissionThreadLocal.setPermissionChecker(null);
			}

			String[] portletIds = ExpandoValueLocalServiceUtil.getData(
				user.getCompanyId(), Subscription.class.getName(), "KB",
				"portletIds", subscription.getSubscriptionId(), new String[0]);

			for (String portletId : portletIds) {
				String articleURL = KnowledgeBaseUtil.getArticleURL(
					portletId, _article.getResourcePrimKey(), _portalURL);

				if (Validator.isNotNull(articleURL)) {
					context.put("articleURL", articleURL);

					return true;
				}

				if (_log.isInfoEnabled()) {
					_log.info(
						"Portlet " + portletId + " does not exist or does " +
							"not contain article " +
								_article.getResourcePrimKey());
				}

				ArticleLocalServiceUtil.unsubscribe(
					subscription.getCompanyId(), userId, portletId,
					subscription.getClassPK());
			}

			return false;
		}

		protected void processMailMessage(
				MailMessage mailMessage, Locale locale)
			throws Exception {

			super.processMailMessage(mailMessage, locale);

			InternetAddress from = mailMessage.getFrom();

			String categoryTitle = LanguageUtil.get(locale, "category.kb");
			String portletName = PortalUtil.getPortletTitle(
				PortletKeys.KNOWLEDGE_BASE_ADMIN, locale);

			String processedFromAddress = StringUtil.replace(
				from.getAddress(),
				new String[] {
					"[$CATEGORY_TITLE$]",
					"[$PORTLET_NAME$]"
				},
				new String[] {
					categoryTitle,
					portletName
				});

			String processedFromName = StringUtil.replace(
				from.getPersonal(),
				new String[] {
					"[$CATEGORY_TITLE$]",
					"[$PORTLET_NAME$]"
				},
				new String[] {
					categoryTitle,
					portletName
				});

			InternetAddress processedFrom = new InternetAddress(
				processedFromAddress, processedFromName);

			mailMessage.setFrom(processedFrom);

			String articleAttachments = getEmailArticleAttachments(
				_article, locale);
			String articleURL = (String)context.get("articleURL");
			String articleVersion = LanguageUtil.format(
				locale, "version-x", String.valueOf(_article.getVersion()),
				false);

			String processedSubject = StringUtil.replace(
				subject,
				new String[] {
					"[$ARTICLE_ATTACHMENTS$]",
					"[$ARTICLE_URL$]",
					"[$ARTICLE_VERSION$]",
					"[$CATEGORY_TITLE$]",
					"[$PORTLET_NAME$]"
				},
				new String[] {
					articleAttachments,
					articleURL,
					articleVersion,
					categoryTitle,
					portletName
				});

			mailMessage.setSubject(processedSubject);

			String processedBody = StringUtil.replace(
				body,
				new String[] {
					"[$ARTICLE_ATTACHMENTS$]",
					"[$ARTICLE_URL$]",
					"[$ARTICLE_VERSION$]",
					"[$CATEGORY_TITLE$]",
					"[$PORTLET_NAME$]"
				},
				new String[] {
					articleAttachments,
					articleURL,
					articleVersion,
					categoryTitle,
					portletName
				});

			mailMessage.setBody(processedBody);
		}

		private Article _article;
		private String _portalURL;

	};

}