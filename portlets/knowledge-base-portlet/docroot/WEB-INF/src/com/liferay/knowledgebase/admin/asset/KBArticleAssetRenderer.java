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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

/**
 * @author Peter Shin
 */
public class KBArticleAssetRenderer extends BaseAssetRenderer {

	public KBArticleAssetRenderer(KBArticle kbArticle) {
		_kbArticle = kbArticle;
	}

	@Override
	public String getClassName() {
		return KBArticle.class.getName();
	}

	@Override
	public long getClassPK() {
		return _kbArticle.getClassPK();
	}

	@Override
	public long getGroupId() {
		return _kbArticle.getGroupId();
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		String summary = _kbArticle.getDescription();

		if (Validator.isNull(summary)) {
			summary = StringUtil.shorten(
				HtmlUtil.extractText(_kbArticle.getContent()), 200);
		}

		return summary;
	}

	@Override
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

		portletURL.setParameter("mvcPath", "/admin/edit_article.jsp");
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

	@Override
	public long getUserId() {
		return _kbArticle.getUserId();
	}

	@Override
	public String getUserName() {
		return _kbArticle.getUserName();
	}

	@Override
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

	@Override
	public String render(
		PortletRequest portletRequest, PortletResponse portletResponse,
		String template) {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			portletRequest.setAttribute(
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