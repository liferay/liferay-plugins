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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.KBCommentContentException;
import com.liferay.knowledgebase.admin.social.AdminActivityKeys;
import com.liferay.knowledgebase.admin.util.AdminSubscriptionSender;
import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBCommentConstants;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBTemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.base.KBCommentLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.KBCommentCreateDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.ratings.model.RatingsEntry;

import java.text.DateFormat;

import java.util.Date;
import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 */
public class KBCommentLocalServiceImpl extends KBCommentLocalServiceBaseImpl {

	@Override
	public KBComment addKBComment(
			long userId, long classNameId, long classPK, String content,
			int userRating, ServiceContext serviceContext)
		throws PortalException {

		// KB comment

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(content);

		long kbCommentId = counterLocalService.increment();

		KBComment kbComment = kbCommentPersistence.create(kbCommentId);

		kbComment.setUuid(serviceContext.getUuid());
		kbComment.setGroupId(groupId);
		kbComment.setCompanyId(user.getCompanyId());
		kbComment.setUserId(user.getUserId());
		kbComment.setUserName(user.getFullName());
		kbComment.setCreateDate(serviceContext.getCreateDate(now));
		kbComment.setModifiedDate(serviceContext.getModifiedDate(now));
		kbComment.setClassNameId(classNameId);
		kbComment.setClassPK(classPK);
		kbComment.setContent(content);
		kbComment.setUserRating(userRating);
		kbComment.setStatus(KBCommentConstants.STATUS_NEW);

		kbCommentPersistence.update(kbComment);

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		putTitle(extraDataJSONObject, kbComment);

		socialActivityLocalService.addActivity(
			userId, kbComment.getGroupId(), KBComment.class.getName(),
			kbCommentId, AdminActivityKeys.ADD_KB_COMMENT,
			extraDataJSONObject.toString(), 0);

		// Subscriptions

		notifySubscribers(userId, kbComment, serviceContext);

		return kbComment;
	}

	@Override
	public KBComment addKBComment(
			long userId, long classNameId, long classPK, String content,
			ServiceContext serviceContext)
		throws PortalException {

		int userRating = getUserRating(userId, classNameId, classPK);

		return addKBComment(
			userId, classNameId, classPK, content, userRating, serviceContext);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public KBComment deleteKBComment(KBComment kbComment)
		throws PortalException {

		// KB comment

		kbCommentPersistence.remove(kbComment);

		// Social

		socialActivityLocalService.deleteActivities(
			KBComment.class.getName(), kbComment.getKbCommentId());

		return kbComment;
	}

	@Override
	public KBComment deleteKBComment(long kbCommentId) throws PortalException {
		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		return kbCommentLocalService.deleteKBComment(kbComment);
	}

	@Override
	public void deleteKBComments(String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		List<KBComment> kbComments = kbCommentPersistence.findByC_C(
			classNameId, classPK);

		for (KBComment kbComment : kbComments) {
			kbCommentLocalService.deleteKBComment(kbComment);
		}
	}

	@Override
	public KBComment getKBComment(long userId, String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.findByU_C_C_Last(
			userId, classNameId, classPK, new KBCommentCreateDateComparator());
	}

	@Override
	public List<KBComment> getKBComments(
		long groupId, int status, int start, int end) {

		return kbCommentPersistence.findByG_S(groupId, status, start, end);
	}

	@Override
	public List<KBComment> getKBComments(
		long userId, String className, long classPK, int start, int end,
		OrderByComparator<KBComment> orderByComparator) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.findByU_C_C(
			userId, classNameId, classPK, start, end, orderByComparator);
	}

	@Override
	public List<KBComment> getKBComments(
		String className, long classPK, int status, int start, int end) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.findByC_C_S(
			classNameId, classPK, status, start, end,
			new KBCommentCreateDateComparator());
	}

	@Override
	public List<KBComment> getKBComments(
		String className, long classPK, int start, int end,
		OrderByComparator orderByComparator) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	@Override
	public List<KBComment> getKBComments(
		String className, long classPK, int[] status, int start, int end) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.findByC_C_S(
			classNameId, classPK, status, start, end,
			new KBCommentCreateDateComparator());
	}

	@Override
	public int getKBCommentsCount(long groupId, int status) {
		return kbCommentPersistence.countByG_S(groupId, status);
	}

	@Override
	public int getKBCommentsCount(long userId, String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.countByU_C_C(userId, classNameId, classPK);
	}

	@Override
	public int getKBCommentsCount(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.countByC_C(classNameId, classPK);
	}

	@Override
	public int getKBCommentsCount(String className, long classPK, int status) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.countByC_C_S(classNameId, classPK, status);
	}

	@Override
	public int getKBCommentsCount(
		String className, long classPK, int[] status) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.countByC_C_S(classNameId, classPK, status);
	}

	@Override
	public KBComment updateKBComment(
			long kbCommentId, long classNameId, long classPK, String content,
			int userRating, int status, ServiceContext serviceContext)
		throws PortalException {

		// KB comment

		validate(content);

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		kbComment.setModifiedDate(serviceContext.getModifiedDate(null));
		kbComment.setClassNameId(classNameId);
		kbComment.setClassPK(classPK);
		kbComment.setContent(content);
		kbComment.setUserRating(userRating);
		kbComment.setStatus(status);

		kbCommentPersistence.update(kbComment);

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		putTitle(extraDataJSONObject, kbComment);

		socialActivityLocalService.addActivity(
			kbComment.getUserId(), kbComment.getGroupId(),
			KBComment.class.getName(), kbCommentId,
			AdminActivityKeys.UPDATE_KB_COMMENT, extraDataJSONObject.toString(),
			0);

		return kbComment;
	}

	@Override
	public KBComment updateKBComment(
			long kbCommentId, long classNameId, long classPK, String content,
			int status, ServiceContext serviceContext)
		throws PortalException {

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		return updateKBComment(
			kbCommentId, classNameId, classPK, content,
			kbComment.getUserRating(), status, serviceContext);
	}

	public KBComment updateStatus(
			long userId, long kbCommentId, int status,
			ServiceContext serviceContext)
		throws PortalException {

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		kbComment.setStatus(status);

		kbCommentPersistence.update(kbComment);

		notifySubscribers(userId, kbComment, serviceContext);

		return kbComment;
	}

	protected int getUserRating(long userId, long classNameId, long classPK)
		throws PortalException {

		ClassName className = classNameLocalService.getClassName(classNameId);

		RatingsEntry ratingsEntry = ratingsEntryLocalService.fetchEntry(
			userId, className.getValue(), classPK);

		if (ratingsEntry == null) {
			return KBCommentConstants.USER_RATING_NONE;
		}

		if (ratingsEntry.getScore() > 0) {
			return KBCommentConstants.USER_RATING_LIKE;
		}

		return KBCommentConstants.USER_RATING_DISLIKE;
	}

	protected void notifySubscribers(
			long userId, KBComment kbComment, ServiceContext serviceContext)
		throws PortalException {

		PortletPreferences preferences =
			portletPreferencesLocalService.getPreferences(
				kbComment.getCompanyId(), kbComment.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP,
				PortletKeys.PREFS_PLID_SHARED, PortletKeys.KNOWLEDGE_BASE_ADMIN,
				null);

		if (!AdminUtil.isSuggestionStatusChangeNotificationEnabled(
				kbComment.getStatus(), preferences)) {

			return;
		}

		String fromName = AdminUtil.getEmailFromName(
			preferences, serviceContext.getCompanyId());
		String fromAddress = AdminUtil.getEmailFromAddress(
			preferences, kbComment.getCompanyId());

		String subject =
			AdminUtil.getEmailKBArticleSuggestionNotificationSubject(
				kbComment.getStatus(), preferences);
		String body = AdminUtil.getEmailKBArticleSuggestionNotificationBody(
			kbComment.getStatus(), preferences);

		KBArticle kbArticle = kbArticleLocalService.getLatestKBArticle(
			kbComment.getClassPK(), WorkflowConstants.STATUS_APPROVED);

		String kbArticleContent = StringUtil.replace(
			kbArticle.getContent(), new String[] {"href=\"/", "src=\"/"},
			new String[] {
				"href=\"" + serviceContext.getPortalURL() + "/",
				"src=\"" + serviceContext.getPortalURL() + "/"
			});

		SubscriptionSender subscriptionSender = new AdminSubscriptionSender(
			kbArticle, serviceContext);

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(kbArticle.getCompanyId());
		subscriptionSender.setContextAttribute(
			"[$ARTICLE_CONTENT$]", kbArticleContent, false);
		subscriptionSender.setContextAttribute(
			"[$COMMENT_CONTENT$]", kbComment.getContent(), false);
		subscriptionSender.setContextAttribute(
			"[$COMMENT_CREATE_DATE$]",
			getFormattedKBCommentCreateDate(kbComment, serviceContext), false);
		subscriptionSender.setContextCreatorUserPrefix("ARTICLE");
		subscriptionSender.setCreatorUserId(kbArticle.getUserId());
		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("kb_article", kbArticle.getKbArticleId());
		subscriptionSender.setPortletId(serviceContext.getPortletId());
		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setScopeGroupId(kbArticle.getGroupId());
		subscriptionSender.setSubject(subject);

		User user = userLocalService.getUser(kbComment.getUserId());

		subscriptionSender.addRuntimeSubscribers(
			user.getEmailAddress(), user.getFullName());

		subscriptionSender.flushNotificationsAsync();
	}

	protected void putTitle(JSONObject jsonObject, KBComment kbComment) {
		KBArticle kbArticle = null;
		KBTemplate kbTemplate = null;

		String className = kbComment.getClassName();

		try {
			if (className.equals(KBArticle.class.getName())) {
				kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
					kbComment.getClassPK(), WorkflowConstants.STATUS_APPROVED);

				jsonObject.put("title", kbArticle.getTitle());
			}
			else if (className.equals(KBTemplate.class.getName())) {
				kbTemplate = KBTemplateLocalServiceUtil.getKBTemplate(
					kbComment.getClassPK());

				jsonObject.put("title", kbTemplate.getTitle());
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	protected void validate(String content) throws PortalException {
		if (Validator.isNull(content)) {
			throw new KBCommentContentException();
		}
	}

	private String getFormattedKBCommentCreateDate(
		KBComment kbComment, ServiceContext serviceContext) {

		DateFormat dateFormat = DateFormatFactoryUtil.getDate(
			serviceContext.getLocale());

		return dateFormat.format(kbComment.getCreateDate());
	}

	private static Log _log = LogFactoryUtil.getLog(
		KBCommentLocalServiceImpl.class);

}