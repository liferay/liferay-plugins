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

package com.liferay.socialnetworking.wall.portlet;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.socialnetworking.model.WallEntry;
import com.liferay.socialnetworking.service.WallEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class WallPortlet extends MVCPortlet {

	public void addWallEntry(
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

		if ((themeDisplay.getUserId() != user.getUserId()) &&
			(!SocialRelationLocalServiceUtil.hasRelation(
				themeDisplay.getUserId(), user.getUserId(),
				SocialRelationConstants.TYPE_BI_FRIEND))) {

			return;
		}

		String comments = ParamUtil.getString(actionRequest, "comments");

		WallEntryLocalServiceUtil.addWallEntry(
			themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
			comments, themeDisplay);
	}

	public void deleteWallEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long wallEntryId = ParamUtil.getLong(actionRequest, "wallEntryId");

		WallEntry wallEntry = null;

		try {
			wallEntry = WallEntryLocalServiceUtil.getWallEntry(wallEntryId);
		}
		catch (Exception e) {
			return;
		}

		if (wallEntry.getGroupId() != themeDisplay.getScopeGroupId()) {
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

		try {
			WallEntryLocalServiceUtil.deleteWallEntry(wallEntryId);
		}
		catch (Exception e) {
		}
	}

}