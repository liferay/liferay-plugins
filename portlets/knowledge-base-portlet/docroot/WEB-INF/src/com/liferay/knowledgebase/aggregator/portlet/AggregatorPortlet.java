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

package com.liferay.knowledgebase.aggregator.portlet;

import com.liferay.knowledgebase.admin.portlet.AdminPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.WindowState;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AggregatorPortlet extends AdminPortlet {

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