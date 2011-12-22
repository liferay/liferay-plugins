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

package com.liferay.knowledgebase.admin.asset;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
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

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Peter Shin
 */
public class KBArticleAssetRenderer extends BaseAssetRenderer {

	public KBArticleAssetRenderer(KBArticle kbArticle) {
		_kbArticle = kbArticle;
	}

	public long getClassPK() {
		return _kbArticle.getClassPK();
	}

	public long getGroupId() {
		return _kbArticle.getGroupId();
	}

	public String getSummary(Locale locale) {
		return HtmlUtil.stripHtml(_kbArticle.getContent());
	}

	public String getTitle(Locale locale) {
		return _kbArticle.getTitle();
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest),
			PortletKeys.KNOWLEDGE_BASE_ADMIN, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("jspPage", "/admin/edit_article.jsp");
		portletURL.setParameter(
			"resourcePrimKey", String.valueOf(_kbArticle.getResourcePrimKey()));

		return portletURL;
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return KnowledgeBaseUtil.getKBArticleURL(
			themeDisplay.getPlid(), _kbArticle.getResourcePrimKey(),
			_kbArticle.getStatus(), themeDisplay.getPortalURL(), false);
	}

	public long getUserId() {
		return _kbArticle.getUserId();
	}

	public String getUuid() {
		return _kbArticle.getUuid();
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		return KBArticlePermission.contains(
			permissionChecker, _kbArticle, ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		return KBArticlePermission.contains(
			permissionChecker, _kbArticle, ActionKeys.VIEW);
	}

	@Override
	public boolean isPrintable() {
		return true;
	}

	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse,
		String template) {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute(
				WebKeys.KNOWLEDGE_BASE_KB_ARTICLE, _kbArticle);

			return "/admin/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/trees/page.png";
	}

	private KBArticle _kbArticle;

}