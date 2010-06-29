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

package com.liferay.knowledgebase.base.action;

import com.liferay.portal.kernel.portlet.BaseConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="BaseAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class BaseAction extends BaseConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("display-settings")) {
			updateDisplaySettings(actionRequest, preferences);
		}
		else if (tabs2.equals("rss")) {
			updateRSS(actionRequest, preferences);
		}
		else if (tabs2.equals("selection-method")) {
			updateSelectionMethod(actionRequest, preferences);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			preferences.store();

			SessionMessages.add(
				actionRequest, portletConfig.getPortletName() + ".doConfigure");
		}
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return getJspPath() + "configuration.jsp";
	}

	protected String getJspPath() {
		return _JSP_PATH;
	}

	protected void postProcessDisplaySettings(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {
	}

	protected void postProcessRSS(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {
	}

	protected void postProcessSelectionMethod(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {
	}

	protected void updateDisplaySettings(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String childArticlesDisplayStyle = ParamUtil.getString(
			actionRequest, "childArticlesDisplayStyle");
		boolean enableArticleComments = ParamUtil.getBoolean(
			actionRequest, "enableArticleComments");
		boolean enableArticleCommentRatings = ParamUtil.getBoolean(
			actionRequest, "enableArticleCommentRatings");

		preferences.setValue(
			"child-articles-display-style", childArticlesDisplayStyle);
		preferences.setValue(
			"enable-article-comments", String.valueOf(enableArticleComments));
		preferences.setValue(
			"enable-article-comment-ratings",
			String.valueOf(enableArticleCommentRatings));

		postProcessDisplaySettings(actionRequest, preferences);
	}

	protected void updateRSS(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		int rssDelta = ParamUtil.getInteger(actionRequest, "rssDelta");
		String rssDisplayStyle = ParamUtil.getString(
			actionRequest, "rssDisplayStyle");
		String rssFormat = ParamUtil.getString(actionRequest, "rssFormat");

		preferences.setValue("rss-delta", String.valueOf(rssDelta));
		preferences.setValue("rss-display-style", rssDisplayStyle);
		preferences.setValue("rss-format", rssFormat);

		postProcessRSS(actionRequest, preferences);
	}

	protected void updateSelectionMethod(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String selectionMethod = ParamUtil.getString(
			actionRequest, "selectionMethod");
		long[] scopeGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "scopeGroupIds"), 0L);
		long[] resourcePrimKeys = StringUtil.split(
			ParamUtil.getString(actionRequest, "resourcePrimKeys"), 0L);

		preferences.setValue("selection-method", selectionMethod);
		preferences.setValues(
			"scope-group-ids", ArrayUtil.toStringArray(scopeGroupIds));
		preferences.setValues(
			"resource-prim-keys", ArrayUtil.toStringArray(resourcePrimKeys));

		postProcessSelectionMethod(actionRequest, preferences);
	}

	private static final String _JSP_PATH = StringPool.BLANK;

}