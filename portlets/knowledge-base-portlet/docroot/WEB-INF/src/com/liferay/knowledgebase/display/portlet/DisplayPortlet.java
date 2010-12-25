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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;

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

	protected int getStatus(PortletRequest portletRequest) {
		return WorkflowConstants.STATUS_APPROVED;
	}

	protected boolean isPRPRedirect(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			PortletSession portletSession = renderRequest.getPortletSession();

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long categoryId = ParamUtil.getLong(renderRequest, "categoryId");
			String tag = ParamUtil.getString(renderRequest, "tag");

			if ((categoryId <= 0) && Validator.isNull(tag)) {
				portletSession.removeAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);
				portletSession.removeAttribute(
					WebKeys.KNOWLEDGE_BASE_CATEGORY_ID);
				portletSession.removeAttribute(WebKeys.KNOWLEDGE_BASE_TAG);

				return false;
			}

			Article oldArticle = (Article)portletSession.getAttribute(
				WebKeys.KNOWLEDGE_BASE_ARTICLE);
			long oldCategoryId = GetterUtil.getLong(
				(Long)portletSession.getAttribute(
					WebKeys.KNOWLEDGE_BASE_CATEGORY_ID));
			String oldTag = GetterUtil.getString(
				(String)portletSession.getAttribute(
					WebKeys.KNOWLEDGE_BASE_TAG));

			String portletId = PortalUtil.getPortletId(renderRequest);

			Article article = KnowledgeBaseUtil.getDisplayArticle(
				themeDisplay.getPlid(), portletId, categoryId, tag,
				themeDisplay.getPermissionChecker());

			portletSession.setAttribute(
				WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
			portletSession.setAttribute(
				WebKeys.KNOWLEDGE_BASE_CATEGORY_ID, categoryId);
			portletSession.setAttribute(WebKeys.KNOWLEDGE_BASE_TAG, tag);

			if ((article == null) || !article.equals(oldArticle) ||
				(categoryId != oldCategoryId) || !tag.equals(oldTag)) {

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

		if (actionName.equals("deleteComment") ||
			actionName.equals("subscribe") ||
			actionName.equals("unsubscribe") ||
			actionName.equals("updateComment")) {

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