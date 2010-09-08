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

package com.liferay.knowledgebase.list.portlet;

import com.liferay.knowledgebase.admin.portlet.AdminPortlet;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.WindowState;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ListPortlet extends AdminPortlet {

	public void subscribe(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		String portletId = PortalUtil.getPortletId(actionRequest);

		if (resourcePrimKey <= 0) {
			ArticleLocalServiceUtil.subscribe(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				themeDisplay.getScopeGroupId(), portletId);
		}
		else {
			ArticleServiceUtil.subscribeArticle(portletId, resourcePrimKey);
		}
	}

	public void unsubscribe(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		String portletId = PortalUtil.getPortletId(actionRequest);

		if (resourcePrimKey <= 0) {
			ArticleLocalServiceUtil.unsubscribe(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				themeDisplay.getScopeGroupId(), portletId);
		}
		else {
			ArticleServiceUtil.unsubscribeArticle(
				themeDisplay.getCompanyId(), resourcePrimKey);
		}
	}

	protected int getStatus(PortletRequest portletRequest) {
		return WorkflowConstants.STATUS_APPROVED;
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
		PortletPreferences preferences = resourceRequest.getPreferences();

		String articleWindowState = preferences.getValue(
			"article-window-state", WindowState.MAXIMIZED.toString());

		if (articleWindowState.equals(WindowState.MAXIMIZED.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

}