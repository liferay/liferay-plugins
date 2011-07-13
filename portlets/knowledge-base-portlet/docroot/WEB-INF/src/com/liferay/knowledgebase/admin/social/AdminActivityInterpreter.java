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
import com.liferay.portal.kernel.util.HtmlUtil;
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

		// Link

		String link = KnowledgeBaseUtil.getKBArticleURL(
			themeDisplay.getPlid(), kbArticle.getResourcePrimKey(),
			kbArticle.getStatus(), themeDisplay.getPortalURL(), false);

		// Title

		String key = StringPool.BLANK;

		if (activity.getType() == AdminActivityKeys.ADD_KB_ARTICLE) {
			key = "activity-knowledge-base-admin-add-kb-article";
		}
		else if (activity.getType() == AdminActivityKeys.MOVE_KB_ARTICLE) {
			key = "activity-knowledge-base-admin-move-kb-article";
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_KB_ARTICLE) {
			key = "activity-knowledge-base-admin-update-kb-article";
		}

		String title = getTitle(
			activity, key, kbArticle.getTitle(), link, themeDisplay);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected SocialActivityFeedEntry doInterpretKBComment(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		KBComment kbComment = KBCommentLocalServiceUtil.getKBComment(
			activity.getClassPK());

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

		String key = StringPool.BLANK;

		if (activity.getType() == AdminActivityKeys.ADD_KB_COMMENT) {
			key = "activity-knowledge-base-admin-add-kb-comment";
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_KB_COMMENT) {
			key = "activity-knowledge-base-admin-update-kb-comment";
		}

		String content = StringPool.BLANK;

		if (kbArticle != null) {
			content = kbArticle.getTitle();
		}
		else if (kbTemplate != null) {
			content = kbTemplate.getTitle();
		}

		String title = getTitle(activity, key, content, link, themeDisplay);

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

		// Link

		String link = StringPool.BLANK;

		// Title

		String key = StringPool.BLANK;

		if (activity.getType() == AdminActivityKeys.ADD_KB_TEMPLATE) {
			key = "activity-knowledge-base-admin-add-kb-template";
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_KB_TEMPLATE) {
			key = "activity-knowledge-base-admin-update-kb-template";
		}

		String title = getTitle(
			activity, key, kbTemplate.getTitle(), link, themeDisplay);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected String getTitle(
		SocialActivity activity, String key, String content, String link,
		ThemeDisplay themeDisplay) {

		String userName = getUserName(activity.getUserId(), themeDisplay);

		String text = HtmlUtil.escape(cleanContent(content));

		if (Validator.isNotNull(link)) {
			text = wrapLink(link, text);
		}

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String pattern = key;

		if (Validator.isNotNull(groupName)) {
			pattern += "-in";
		}

		return themeDisplay.translate(
			pattern, new Object[] {userName, text, groupName});
	}

	private static final String[] _CLASS_NAMES = new String[] {
		KBArticle.class.getName(), KBComment.class.getName(),
		KBTemplate.class.getName()
	};

}