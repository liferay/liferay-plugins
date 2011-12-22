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

package com.liferay.socialcoding.jira.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAPortlet extends MVCPortlet {

	public void updateJira(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getScopeGroupId());

		User user = null;

		if (group.isUser()) {
			user = UserLocalServiceUtil.getUserById(group.getClassPK());
		}
		else {
			return;
		}

		if (!UserPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), user.getUserId(),
				ActionKeys.UPDATE)) {

			return;
		}

		String jiraUserId = ParamUtil.getString(actionRequest, "jiraUserId");

		try {
			ExpandoValueLocalServiceUtil.addValue(
				themeDisplay.getCompanyId(), User.class.getName(), "SC",
				"jiraUserId", user.getUserId(), jiraUserId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JIRAPortlet.class);

}