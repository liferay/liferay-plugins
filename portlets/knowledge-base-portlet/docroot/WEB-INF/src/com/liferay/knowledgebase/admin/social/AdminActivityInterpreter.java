/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String className = activity.getClassName();

		if (className.equals(KBArticle.class.getName())) {
			return doInterpretKBArticle(activity, themeDisplay);
		}
		else if (className.equals(KBComment.class.getName())) {
			return doInterpretKBComment(activity, themeDisplay);
		}
		else if (className.equals(KBTemplate.class.getName())) {
			return doInterpretKBTemplate(activity, themeDisplay);
		}

		return null;
	}

	protected SocialActivityFeedEntry doInterpretKBArticle(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
			activity.getClassPK(), WorkflowConstants.STATUS_APPROVED);

		if (!KBArticlePermission.contains(
				permissionChecker, kbArticle, ActionKeys.VIEW)) {

			return null;
		}

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		// Link

		String link = KnowledgeBaseUtil.getKBArticleURL(
			themeDisplay.getPlid(), kbArticle.getResourcePrimKey(),
			kbArticle.getStatus(), themeDisplay.getPortalURL(), false);

		// Title

		String titlePattern = null;

		if (activity.getType() == AdminActivityKeys.ADD_KB_ARTICLE) {
			if (Validator.isNull(groupName)) {
				titlePattern = "activity-knowledge-base-admin-add-kb-article";
			}
			else {
				titlePattern =
					"activity-knowledge-base-admin-add-kb-article-in";
			}
		}
		else if (activity.getType() == AdminActivityKeys.MOVE_KB_ARTICLE) {
			if (Validator.isNull(groupName)) {
				titlePattern = "activity-knowledge-base-admin-move-kb-article";
			}
			else {
				titlePattern =
					"activity-knowledge-base-admin-move-kb-article-in";
			}
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_KB_ARTICLE) {
			if (Validator.isNull(groupName)) {
				titlePattern =
					"activity-knowledge-base-admin-update-kb-article";
			}
			else {
				titlePattern =
					"activity-knowledge-base-admin-update-kb-article-in";
			}
		}

		String articleTitle = getValue(
			activity.getExtraData(), "title", kbArticle.getTitle());

		Object[] titleArguments = {
			creatorUserName, wrapLink(link, articleTitle), groupName
		};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected SocialActivityFeedEntry doInterpretKBComment(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		KBComment kbComment = KBCommentLocalServiceUtil.getKBComment(
			activity.getClassPK());

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		KBArticle kbArticle = null;
		KBTemplate kbTemplate = null;

		String className = kbComment.getClassName();

		if (className.equals(KBArticle.class.getName())) {
			kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				kbComment.getClassPK(), WorkflowConstants.STATUS_APPROVED);
		}
		else if (className.equals(KBTemplate.class.getName())) {
			kbTemplate = KBTemplateLocalServiceUtil.getKBTemplate(
				kbComment.getClassPK());
		}

		// Link

		String link = StringPool.BLANK;

		if (kbArticle != null) {
			link = KnowledgeBaseUtil.getKBArticleURL(
				themeDisplay.getPlid(), kbArticle.getResourcePrimKey(),
				kbArticle.getStatus(), themeDisplay.getPortalURL(), false);
		}

		// Title

		String titlePattern = null;

		if (activity.getType() == AdminActivityKeys.ADD_KB_COMMENT) {
			if (Validator.isNull(groupName)) {
				titlePattern = "activity-knowledge-base-admin-add-kb-comment";
			}
			else {
				titlePattern =
					"activity-knowledge-base-admin-add-kb-comment-in";
			}
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_KB_COMMENT) {
			if (Validator.isNull(groupName)) {
				titlePattern =
					"activity-knowledge-base-admin-update-kb-comment";
			}
			else {
				titlePattern =
					"activity-knowledge-base-admin-update-kb-comment-in";
			}
		}

		String entityTitle = null;

		if (kbArticle != null) {
			entityTitle = getValue(
				activity.getExtraData(), "title", kbArticle.getTitle());
		}
		else if (kbTemplate != null) {
			entityTitle = getValue(
				activity.getExtraData(), "title", kbTemplate.getTitle());
		}

		Object[] titleArguments = {
			creatorUserName, wrapLink(link, entityTitle), groupName
		};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected SocialActivityFeedEntry doInterpretKBTemplate(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		KBTemplate kbTemplate = KBTemplateLocalServiceUtil.getKBTemplate(
			activity.getClassPK());

		if (!KBTemplatePermission.contains(
				permissionChecker, kbTemplate, ActionKeys.VIEW)) {

			return null;
		}

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		// Link

		String link = StringPool.BLANK;

		// Title

		String titlePattern = null;

		if (activity.getType() == AdminActivityKeys.ADD_KB_TEMPLATE) {
			if (Validator.isNull(groupName)) {
				titlePattern = "activity-knowledge-base-admin-add-kb-template";
			}
			else {
				titlePattern =
					"activity-knowledge-base-admin-add-kb-template-in";
			}
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_KB_TEMPLATE) {
			if (Validator.isNull(groupName)) {
				titlePattern =
					"activity-knowledge-base-admin-update-kb-template";
			}
			else {
				titlePattern =
					"activity-knowledge-base-admin-update-kb-template-in";
			}
		}

		String articleTitle = getValue(
			activity.getExtraData(), "title", kbTemplate.getTitle());

		Object[] titleArguments = {creatorUserName, articleTitle, groupName};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		KBArticle.class.getName(), KBComment.class.getName(),
		KBTemplate.class.getName()
	};

}