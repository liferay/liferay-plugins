/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.friends.social;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;

/**
 * @author Brian Wing Shun Chan
 */
public class FriendsActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getLink(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);

		User creatorUser = UserLocalServiceUtil.getUserById(
			activity.getUserId());

		sb.append(HtmlUtil.escapeURL(creatorUser.getScreenName()));

		return sb.toString();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ThemeDisplay themeDisplay)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != FriendsActivityKeys.ADD_FRIEND) {
			return new Object[0];
		}

		StringBundler sb = new StringBundler(5);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);

		User creatorUser = UserLocalServiceUtil.getUserById(
			activity.getUserId());

		sb.append(HtmlUtil.escapeURL(creatorUser.getScreenName()));

		sb.append("/profile");

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		String creatorUserNameURL = wrapLink(sb.toString(), creatorUserName);

		sb = new StringBundler(5);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);

		User receiverUser = UserLocalServiceUtil.getUserById(
			activity.getReceiverUserId());

		sb.append(HtmlUtil.escapeURL(receiverUser.getScreenName()));

		sb.append("/profile");

		String receiverUserName = getUserName(
			activity.getReceiverUserId(), themeDisplay);

		String receiverUserNameURL = wrapLink(sb.toString(), receiverUserName);

		return new Object[] {creatorUserNameURL, receiverUserNameURL};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == FriendsActivityKeys.ADD_FRIEND) {
			return "activity-social-networking-summary-add-friend";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ThemeDisplay themeDisplay) {

		return true;
	}

	private static final String[] _CLASS_NAMES = {User.class.getName()};

}