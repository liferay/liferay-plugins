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

package com.liferay.knowledgebase.search.portlet;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.NoSuchCommentException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.portlet.BaseKBPortlet;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.NoSuchSubscriptionException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class SearchPortlet extends BaseKBPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			int status = getStatus(renderRequest);

			renderRequest.setAttribute(WebKeys.KNOWLEDGE_BASE_STATUS, status);

			KBArticle kbArticle = null;

			long resourcePrimKey = ParamUtil.getLong(
				renderRequest, "resourcePrimKey");

			if (resourcePrimKey > 0) {
				kbArticle = KBArticleServiceUtil.getLatestKBArticle(
					resourcePrimKey, status);
			}

			renderRequest.setAttribute(
				WebKeys.KNOWLEDGE_BASE_KB_ARTICLE, kbArticle);
		}
		catch (Exception e) {
			if (e instanceof NoSuchArticleException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String mvcPath = ParamUtil.getString(
			renderRequest, "mvcPath", viewTemplate);

		long assetCategoryId = ParamUtil.getLong(renderRequest, "categoryId");
		String assetTagName = ParamUtil.getString(renderRequest, "tag");

		if ((mvcPath.equals(viewTemplate) && (assetCategoryId > 0)) ||
			(mvcPath.equals(viewTemplate) &&
			 Validator.isNotNull(assetTagName))) {

			String path = templatePath + "view_prp_articles.jsp";

			include(path, renderRequest, renderResponse);
		}
		else if (SessionErrors.contains(
					renderRequest, NoSuchArticleException.class.getName()) ||
				 SessionErrors.contains(
					 renderRequest, NoSuchCommentException.class.getName()) ||
				 SessionErrors.contains(
					 renderRequest,
					 NoSuchSubscriptionException.class.getName()) ||
				 SessionErrors.contains(
					 renderRequest, PrincipalException.class.getName())) {

			include(templatePath + "error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected int getStatus(RenderRequest renderRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return WorkflowConstants.STATUS_APPROVED;
		}

		String value = renderRequest.getParameter("status");
		int status = GetterUtil.getInteger(value);

		if ((value != null) && (status == WorkflowConstants.STATUS_APPROVED)) {
			return WorkflowConstants.STATUS_APPROVED;
		}

		long resourcePrimKey = ParamUtil.getLong(
			renderRequest, "resourcePrimKey");

		if (resourcePrimKey == 0) {
			return WorkflowConstants.STATUS_APPROVED;
		}

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (KBArticlePermission.contains(
				permissionChecker, resourcePrimKey, ActionKeys.UPDATE)) {

			return ParamUtil.getInteger(
				renderRequest, "status", WorkflowConstants.STATUS_ANY);
		}

		return WorkflowConstants.STATUS_APPROVED;
	}

}