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
import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBCommentConstants;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBTemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.base.KBCommentLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.comparator.KBCommentCreateDateComparator;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackRegistryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 */
public class KBCommentLocalServiceImpl extends KBCommentLocalServiceBaseImpl {

	@Override
	public KBComment addKBComment(
			long userId, long classNameId, long classPK, String content,
			boolean helpful, ServiceContext serviceContext)
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
		kbComment.setHelpful(helpful);
		kbComment.setStatus(KBCommentConstants.STATUS_PENDING);

		kbCommentPersistence.update(kbComment);

		// Feedback notifications

		notifyFeedbackUser(
			kbComment, KBCommentConstants.STATUS_NONE, serviceContext);

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		putTitle(extraDataJSONObject, kbComment);

		socialActivityLocalService.addActivity(
			userId, kbComment.getGroupId(), KBComment.class.getName(),
			kbCommentId, AdminActivityKeys.ADD_KB_COMMENT,
			extraDataJSONObject.toString(), 0);

		return kbComment;
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

		return kbCommentFinder.findByG_S(groupId, status, start, end);
	}

	@Override
	public List<KBComment> getKBComments(
		String className, long classPK, int start, int end,
		OrderByComparator<KBComment> orderByComparator) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.findByU_C_C(
			userId, classNameId, classPK, start, end, orderByComparator);
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

	public int getKBCommentsCount(long userId, String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.countByU_C_C(userId, classNameId, classPK);
	}

	@Override
	public int getKBCommentsCount(long groupId, int status) {
		return kbCommentFinder.countByG_S(groupId, status);
	}

	@Override
	public int getKBCommentsCount(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return kbCommentPersistence.countByC_C(classNameId, classPK);
	}

	@Override
	public KBComment updateKBComment(
			long kbCommentId, long classNameId, long classPK, String content,
			boolean helpful, int status, ServiceContext serviceContext)
		throws PortalException {

		// KB comment

		validate(content);

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		kbComment.setModifiedDate(serviceContext.getModifiedDate(null));
		kbComment.setClassNameId(classNameId);
		kbComment.setClassPK(classPK);
		kbComment.setContent(content);
		kbComment.setHelpful(helpful);
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

	public KBComment updateStatus(
			long kbCommentId, int status, ServiceContext serviceContext)
		throws PortalException {

		KBComment kbComment = kbCommentPersistence.findByPrimaryKey(
			kbCommentId);

		int previousStatus = kbComment.getStatus();

		kbComment.setStatus(status);

		KBComment updatedKBComment = updateKBComment(kbComment);

		notifyFeedbackUser(updatedKBComment, previousStatus, serviceContext);

		return updatedKBComment;
	}

	protected void notifyFeedbackUser(
			KBComment kbComment, int previousStatus,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			int status = kbComment.getStatus();

			if (AdminUtil.isBackwardsStatusTransition(previousStatus, status)) {
				return;
			}

			PortletPreferences preferences =
				portletPreferencesLocalService.getPreferences(
					kbComment.getCompanyId(), kbComment.getGroupId(),
					PortletKeys.PREFS_OWNER_TYPE_GROUP,
					PortletKeys.PREFS_PLID_SHARED,
					PortletKeys.KNOWLEDGE_BASE_ADMIN, null);

			if (!AdminUtil.isFeedbackStatusChangeNotificationEnabled(
					status, preferences)) {

				return;
			}

			User user = userLocalService.getUser(kbComment.getUserId());

			String fromName = AdminUtil.getEmailFromName(
				preferences, serviceContext.getCompanyId());
			String fromAddress = AdminUtil.getEmailFromAddress(
				preferences, kbComment.getCompanyId());

			InternetAddress from = new InternetAddress(fromAddress, fromName);
			InternetAddress to = new InternetAddress(user.getEmailAddress());

			String subject =
				AdminUtil.getEmailKBArticleFeedbackNotificationSubject(
					status, preferences);

			String body = AdminUtil.getEmailKBArticleFeedbackNotificationBody(
				status, preferences);

			KBArticle kbArticle = kbArticleLocalService.getLatestKBArticle(
				kbComment.getClassPK(), WorkflowConstants.STATUS_APPROVED);

			String processedSubject = replaceContent(
				subject, kbArticle, kbComment, serviceContext);

			String processedBody = replaceContent(
				body, kbArticle, kbComment, serviceContext);

			final MailMessage mailMessage = new MailMessage(
				from, to, processedSubject, processedBody, false);

			TransactionCommitCallbackRegistryUtil.registerCallback(
				new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						MailServiceUtil.sendEmail(mailMessage);

						return null;
					}

				});
		}
		catch (UnsupportedEncodingException uee) {
			throw new SystemException(uee);
		}
		catch (AddressException ae) {
			throw new PortalException(ae);
		}
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

	protected String replaceContent(
		String content, KBArticle kbArticle, KBComment kbComment,
		ServiceContext serviceContext) {

		String kbArticleURL = KnowledgeBaseUtil.getKBArticleURL(
			serviceContext.getPlid(), kbArticle.getResourcePrimKey(),
			kbArticle.getStatus(), serviceContext.getPortalURL(), false);

		return StringUtil.replace(
			content,
			new String[] {
				"[$ARTICLE_CONTENT$]", "[$ARTICLE_TITLE$]", "[$ARTICLE_URL$]",
				"[$COMMENT_CONTENT$]", "[$TO_NAME$]"
			},
			new String[] {
				kbArticle.getContent(), kbArticle.getTitle(), kbArticleURL,
				kbComment.getContent(), kbComment.getUserName()
			}
		);
	}

	protected void validate(String content) throws PortalException {
		if (Validator.isNull(content)) {
			throw new KBCommentContentException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		KBCommentLocalServiceImpl.class);

}