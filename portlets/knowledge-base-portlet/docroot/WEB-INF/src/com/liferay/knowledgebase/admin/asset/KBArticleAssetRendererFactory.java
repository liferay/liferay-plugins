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

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Peter Shin
 */
public class KBArticleAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String CLASS_NAME = KBArticle.class.getName();

	public static final String TYPE = "article";

	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		KBArticle kbArticle = null;

		if (type == TYPE_LATEST_APPROVED) {
			kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				classPK, WorkflowConstants.STATUS_APPROVED);

			return new KBArticleAssetRenderer(kbArticle);
		}

		try {
			kbArticle = KBArticleLocalServiceUtil.getKBArticle(classPK);
		}
		catch (NoSuchArticleException nsae) {
			kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				classPK, WorkflowConstants.STATUS_ANY);
		}

		return new KBArticleAssetRenderer(kbArticle);
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	public String getType() {
		return TYPE;
	}

	@Override
	public PortletURL getURLAdd(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException, SystemException {

		HttpServletRequest request =
			liferayPortletRequest.getHttpServletRequest();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!AdminPermission.contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), ActionKeys.ADD_KB_ARTICLE)) {

			return null;
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, PortletKeys.KNOWLEDGE_BASE_ADMIN,
			getControlPanelPlid(themeDisplay), PortletRequest.RENDER_PHASE);

		portletURL.setParameter("jspPage", "/admin/edit_article.jsp");

		return portletURL;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return KBArticlePermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/trees/page.png";
	}

}