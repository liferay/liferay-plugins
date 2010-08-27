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

package com.liferay.knowledgebase.display.portlet;

import com.liferay.knowledgebase.admin.portlet.AdminPortlet;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class DisplayPortlet extends AdminPortlet {

	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (isPRPRedirect(renderRequest, renderResponse)) {
			include(jspPath + "view.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected boolean isPRPRedirect(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				renderRequest);

			HttpSession session = request.getSession();

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long assetCategoryId = ParamUtil.getLong(
				renderRequest, "categoryId");
			String assetTagName = ParamUtil.getString(renderRequest, "tag");

			if ((assetCategoryId <= 0) && Validator.isNull(assetTagName)) {
				return false;
			}

			Article displayArticle = (Article)session.getAttribute(
				WebKeys.KNOWLEDGE_BASE_DISPLAY_ARTICLE);

			String portletId = PortalUtil.getPortletId(renderRequest);

			Article article = KnowledgeBaseUtil.getDisplayArticle(
				themeDisplay.getPlid(), portletId, assetCategoryId,
				assetTagName, themeDisplay.getPermissionChecker());

			session.setAttribute(
				WebKeys.KNOWLEDGE_BASE_DISPLAY_ARTICLE, article);

			if ((article == null) || !article.equals(displayArticle)) {
				return true;
			}

			return false;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected boolean isProcessActionRequest(ActionRequest actionRequest) {
		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("subscribe") ||
			actionName.equals("unsubscribe")) {

			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isServeRSSMaximized(ResourceRequest resourceRequest) {
		return _SERVE_RSS_MAXIMIZED;
	}

	private static final boolean _SERVE_RSS_MAXIMIZED = false;

}