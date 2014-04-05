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

package com.liferay.knowledgebase.admin.social;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBCommentLocalServiceUtil;
import com.liferay.knowledgebase.service.KBTemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.service.permission.KBTemplatePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getEntryTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String title = StringPool.BLANK;

		String className = activity.getClassName();

		if (className.equals(KBArticle.class.getName())) {
			KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				activity.getClassPK(), WorkflowConstants.STATUS_APPROVED);

			title = kbArticle.getTitle();
		}
		else if (className.equals(KBComment.class.getName())) {
			KBComment kbComment = KBCommentLocalServiceUtil.getKBComment(
				activity.getClassPK());

			String kbCommentClassName = kbComment.getClassName();

			if (kbCommentClassName.equals(KBArticle.class.getName())) {
				KBArticle kbArticle =
					KBArticleLocalServiceUtil.getLatestKBArticle(
						kbComment.getClassPK(),
						WorkflowConstants.STATUS_APPROVED);

				title = kbArticle.getTitle();
			}
			else if (kbCommentClassName.equals(KBTemplate.class.getName())) {
				KBTemplate kbTemplate =
					KBTemplateLocalServiceUtil.getKBTemplate(
						kbComment.getClassPK());

				title = kbTemplate.getTitle();
			}
		}
		else if (className.equals(KBTemplate.class.getName())) {
			KBTemplate kbTemplate = KBTemplateLocalServiceUtil.getKBTemplate(
				activity.getClassPK());

			title = kbTemplate.getTitle();
		}

		return getJSONValue(activity.getExtraData(), "title", title);
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String className = activity.getClassName();

		if (className.equals(KBArticle.class.getName())) {
			KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				activity.getClassPK(), WorkflowConstants.STATUS_APPROVED);

			return KnowledgeBaseUtil.getKBArticleURL(
				serviceContext.getPlid(), kbArticle.getResourcePrimKey(),
				kbArticle.getStatus(), serviceContext.getPortalURL(), false);
		}
		else if (className.equals(KBComment.class.getName())) {
			KBComment kbComment = KBCommentLocalServiceUtil.getKBComment(
				activity.getClassPK());

			String kbCommentClassName = kbComment.getClassName();

			if (kbCommentClassName.equals(KBArticle.class.getName())) {
				KBArticle kbArticle =
					KBArticleLocalServiceUtil.getLatestKBArticle(
						activity.getClassPK(),
						WorkflowConstants.STATUS_APPROVED);

				return KnowledgeBaseUtil.getKBArticleURL(
					serviceContext.getPlid(), kbArticle.getResourcePrimKey(),
					kbArticle.getStatus(), serviceContext.getPortalURL(),
					false);
			}
		}

		return StringPool.BLANK;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		String className = activity.getClassName();

		if (className.equals(KBArticle.class.getName())) {
			if (activity.getType() == AdminActivityKeys.ADD_KB_ARTICLE) {
				if (Validator.isNull(groupName)) {
					return "activity-knowledge-base-admin-add-kb-article";
				}
				else {
					return "activity-knowledge-base-admin-add-kb-article-in";
				}
			}
			else if (activity.getType() == AdminActivityKeys.MOVE_KB_ARTICLE) {
				if (Validator.isNull(groupName)) {
					return "activity-knowledge-base-admin-move-kb-article";
				}
				else {
					return "activity-knowledge-base-admin-move-kb-article-in";
				}
			}
			else if (activity.getType() ==
						AdminActivityKeys.UPDATE_KB_ARTICLE) {

				if (Validator.isNull(groupName)) {
					return "activity-knowledge-base-admin-update-kb-article";
				}
				else {
					return "activity-knowledge-base-admin-update-kb-article-in";
				}
			}
		}
		else if (className.equals(KBComment.class.getName())) {
			if (activity.getType() == AdminActivityKeys.ADD_KB_COMMENT) {
				if (Validator.isNull(groupName)) {
					return "activity-knowledge-base-admin-add-kb-comment";
				}
				else {
					return "activity-knowledge-base-admin-add-kb-comment-in";
				}
			}
			else if (activity.getType() ==
						AdminActivityKeys.UPDATE_KB_COMMENT) {

				if (Validator.isNull(groupName)) {
					return "activity-knowledge-base-admin-update-kb-comment";
				}
				else {
					return "activity-knowledge-base-admin-update-kb-comment-in";
				}
			}
		}
		else if (className.equals(KBTemplate.class.getName())) {
			if (activity.getType() == AdminActivityKeys.ADD_KB_TEMPLATE) {
				if (Validator.isNull(groupName)) {
					return "activity-knowledge-base-admin-add-kb-template";
				}
				else {
					return "activity-knowledge-base-admin-add-kb-template-in";
				}
			}
			else if (activity.getType() ==
						AdminActivityKeys.UPDATE_KB_TEMPLATE) {

				if (Validator.isNull(groupName)) {
					return "activity-knowledge-base-admin-update-kb-template";
				}
				else {
					return
						"activity-knowledge-base-admin-update-kb-template-in";
				}
			}
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		String className = activity.getClassName();

		if (className.equals(KBArticle.class.getName())) {
			KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				activity.getClassPK(), WorkflowConstants.STATUS_APPROVED);

			return KBArticlePermission.contains(
				permissionChecker, kbArticle, ActionKeys.VIEW);
		}
		else if (className.equals(KBComment.class.getName())) {
			return true;
		}
		else if (className.equals(KBTemplate.class.getName())) {
			KBTemplate kbTemplate = KBTemplateLocalServiceUtil.getKBTemplate(
				activity.getClassPK());

			return KBTemplatePermission.contains(
				permissionChecker, kbTemplate, ActionKeys.VIEW);
		}

		return false;
	}

	private static final String[] _CLASS_NAMES = {
		KBArticle.class.getName(), KBComment.class.getName(),
		KBTemplate.class.getName()
	};

}