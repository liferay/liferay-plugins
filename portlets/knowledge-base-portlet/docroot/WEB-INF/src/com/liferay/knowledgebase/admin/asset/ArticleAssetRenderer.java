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

package com.liferay.knowledgebase.admin.asset;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Peter Shin
 */
public class ArticleAssetRenderer extends BaseAssetRenderer {

	public ArticleAssetRenderer(Article article) {
		_article = article;
	}

	public long getClassPK() {
		return _article.getResourcePrimKey();
	}

	public long getGroupId() {
		return _article.getGroupId();
	}

	public String getSummary(Locale locale) {
		return HtmlUtil.stripHtml(_article.getContent());
	}

	public String getTitle(Locale locale) {
		return _article.getTitle();
	}

	public PortletURL getURLEdit(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		PortletURL portletURL = liferayPortletResponse.createRenderURL(
			PortletKeys.KNOWLEDGE_BASE_ADMIN);

		portletURL.setParameter("jspPage", "/admin/edit_article.jsp");
		portletURL.setParameter(
			"resourcePrimKey", String.valueOf(_article.getResourcePrimKey()));

		return portletURL;
	}

	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return KnowledgeBaseUtil.getArticleURL(
			themeDisplay.getPlid(), _article.getResourcePrimKey(),
			themeDisplay.getPortalURL(), false);
	}

	public long getUserId() {
		return _article.getUserId();
	}

	public String getUuid() {
		return _article.getUuid();
	}

	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		return ArticlePermission.contains(
			permissionChecker, _article, ActionKeys.UPDATE);
	}

	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		return ArticlePermission.contains(
			permissionChecker, _article, ActionKeys.VIEW);
	}

	public boolean isPrintable() {
		return true;
	}

	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String template)
		throws Exception {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute(
				WebKeys.KNOWLEDGE_BASE_ARTICLE, _article);

			return "/admin/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/trees/page.png";
	}

	private Article _article;

}