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

package com.liferay.knowledgebase.admin.social;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.TemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.service.permission.TemplatePermission;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

/**
 * <a href="AdminActivityInterpreter.java.html"><b><i>View Source</i></b></a>
 *
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
			activity.getClassPK());

		if (!ArticlePermission.contains(
				permissionChecker, article, ActionKeys.VIEW)) {

			return null;
		}

		// Link

		String link = StringPool.BLANK;

		long plid = PortalUtil.getPlidFromPortletId(
			article.getGroupId(), PortletKeys.KNOWLEDGE_BASE_ADMIN);

		if (plid != LayoutConstants.DEFAULT_PLID) {
			if (PortletPermissionUtil.contains(
					permissionChecker, plid, PortletKeys.KNOWLEDGE_BASE_ADMIN,
					ActionKeys.VIEW)) {

				String layoutFullURL = PortalUtil.getLayoutFullURL(
					LayoutLocalServiceUtil.getLayout(plid), themeDisplay);

				link = KnowledgeBaseUtil.getArticleURL(
					PortletKeys.KNOWLEDGE_BASE_ADMIN,
					article.getResourcePrimKey(), layoutFullURL, false);
			}
		}

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

		String link = StringPool.BLANK;

		long plid = PortalUtil.getPlidFromPortletId(
			template.getGroupId(), PortletKeys.KNOWLEDGE_BASE_ADMIN);

		if (plid != LayoutConstants.DEFAULT_PLID) {
			if (PortletPermissionUtil.contains(
					permissionChecker, plid, PortletKeys.KNOWLEDGE_BASE_ADMIN,
					ActionKeys.VIEW)) {

				String layoutFullURL = PortalUtil.getLayoutFullURL(
					LayoutLocalServiceUtil.getLayout(plid), themeDisplay);
				String namespace = PortalUtil.getPortletNamespace(
					PortletKeys.KNOWLEDGE_BASE_ADMIN);

				link = HttpUtil.setParameter(
					layoutFullURL, "p_p_id", PortletKeys.KNOWLEDGE_BASE_ADMIN);
				link = HttpUtil.setParameter(
					link, namespace + "jspPage", "/admin/view_template.jsp");
				link = HttpUtil.setParameter(
					link, namespace + "templateId", template.getTemplateId());
			}
		}

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
		Article.class.getName(), Template.class.getName()
	};

}