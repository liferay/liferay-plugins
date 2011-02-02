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

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.Comment;
import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.CommentLocalServiceUtil;
import com.liferay.knowledgebase.service.TemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.service.permission.TemplatePermission;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.ControlPanelEntry;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String className = activity.getClassName();

		if (className.equals(Article.class.getName())) {
			return doInterpretArticle(activity, themeDisplay);
		}
		else if (className.equals(Comment.class.getName())) {
			return doInterpretComment(activity, themeDisplay);
		}
		else if (className.equals(Template.class.getName())) {
			return doInterpretTemplate(activity, themeDisplay);
		}

		return null;
	}

	protected SocialActivityFeedEntry doInterpretArticle(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		Article article = ArticleLocalServiceUtil.getLatestArticle(
			activity.getClassPK(), WorkflowConstants.STATUS_APPROVED);

		if (!ArticlePermission.contains(
				permissionChecker, article, ActionKeys.VIEW)) {

			return null;
		}

		// Link

		String link = KnowledgeBaseUtil.getArticleURL(
			themeDisplay.getPlid(), article.getResourcePrimKey(),
			themeDisplay.getPortalURL());

		// Title

		String key = StringPool.BLANK;

		if (activity.getType() == AdminActivityKeys.ADD_ARTICLE) {
			key = "activity-knowledge-base-admin-add-article";
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_ARTICLE) {
			key = "activity-knowledge-base-admin-update-article";
		}

		String title = getTitle(
			activity, key, article.getTitle(), link, themeDisplay);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected SocialActivityFeedEntry doInterpretComment(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		Comment comment = CommentLocalServiceUtil.getComment(
			activity.getClassPK());

		Article article = null;
		Template template = null;

		String className = comment.getClassName();

		if (className.equals(Article.class.getName())) {
			article = ArticleLocalServiceUtil.getLatestArticle(
				comment.getClassPK(), WorkflowConstants.STATUS_APPROVED);
		}
		else if (className.equals(Template.class.getName())) {
			template = TemplateLocalServiceUtil.getTemplate(
				comment.getClassPK());
		}

		// Link

		String link = StringPool.BLANK;

		if (article != null) {
			link = KnowledgeBaseUtil.getArticleURL(
				themeDisplay.getPlid(), article.getResourcePrimKey(),
				themeDisplay.getPortalURL());
		}
		else if (template != null) {
			link = getTemplateURL(
				template.getGroupId(), template.getTemplateId(), themeDisplay);
		}

		// Title

		String key = StringPool.BLANK;

		if (activity.getType() == AdminActivityKeys.ADD_COMMENT) {
			key = "activity-knowledge-base-admin-add-comment";
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_COMMENT) {
			key = "activity-knowledge-base-admin-update-comment";
		}

		String content = StringPool.BLANK;

		if (article != null) {
			content = article.getTitle();
		}
		else if (template != null) {
			content = template.getTitle();
		}

		String title = getTitle(activity, key, content, link, themeDisplay);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected SocialActivityFeedEntry doInterpretTemplate(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		Template template = TemplateLocalServiceUtil.getTemplate(
			activity.getClassPK());

		if (!TemplatePermission.contains(
				permissionChecker, template, ActionKeys.VIEW)) {

			return null;
		}

		// Link

		String link = getTemplateURL(
			template.getGroupId(), template.getTemplateId(), themeDisplay);

		// Title

		String key = StringPool.BLANK;

		if (activity.getType() == AdminActivityKeys.ADD_TEMPLATE) {
			key = "activity-knowledge-base-admin-add-template";
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_TEMPLATE) {
			key = "activity-knowledge-base-admin-update-template";
		}

		String title = getTitle(
			activity, key, template.getTitle(), link, themeDisplay);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected String getTemplateURL(
			long groupId, long templateId, ThemeDisplay themeDisplay)
		throws Exception {

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), PortletKeys.KNOWLEDGE_BASE_ADMIN);

		ControlPanelEntry controlPanelEntry =
			portlet.getControlPanelEntryInstance();

		if (controlPanelEntry == null) {
			controlPanelEntry = (ControlPanelEntry)PortalClassInvoker.invoke(
				false, "com.liferay.portlet.DefaultControlPanelEntryFactory",
				"getInstance", new String[0]);
		}

		String controlPanelEntryCategory =
			portlet.getControlPanelEntryCategory();

		if (!controlPanelEntry.isVisible(
				portlet, controlPanelEntryCategory, themeDisplay)) {

			return StringPool.BLANK;
		}

		Map<String, String[]> params = new HashMap<String, String[]>();

		String namespace = PortalUtil.getPortletNamespace(
			PortletKeys.KNOWLEDGE_BASE_ADMIN);

		params.put(
			namespace + "jspPage", new String[] {"/admin/view_template.jsp"});
		params.put(
			namespace + "templateId",
			new String[] {String.valueOf(templateId)});

		return PortalUtil.getControlPanelFullURL(
			groupId, PortletKeys.KNOWLEDGE_BASE_ADMIN, params);
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
		Article.class.getName(), Comment.class.getName(),
		Template.class.getName()
	};

}