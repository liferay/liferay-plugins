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

package com.liferay.socialnetworking.friends.social;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

/**
 * @author Brian Wing Shun Chan
 */
public class FriendsActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), themeDisplay);

		User creatorUser = UserLocalServiceUtil.getUserById(
			activity.getUserId());
		User receiverUser = UserLocalServiceUtil.getUserById(
			activity.getReceiverUserId());

		int activityType = activity.getType();

		// Link

		StringBuilder sb = new StringBuilder();

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);
		sb.append(HtmlUtil.escapeURL(creatorUser.getScreenName()));
		sb.append("/friends");

		String link = sb.toString();

		// Title

		String title = StringPool.BLANK;

		if (activityType == FriendsActivityKeys.ADD_FRIEND) {
			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getPortalURL());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(StringPool.SLASH);
			sb.append(HtmlUtil.escapeURL(creatorUser.getScreenName()));
			sb.append("/profile\">");
			sb.append(creatorUserName);
			sb.append("</a>");

			String creatorUserNameURL = sb.toString();

			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getPortalURL());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(StringPool.SLASH);
			sb.append(HtmlUtil.escapeURL(receiverUser.getScreenName()));
			sb.append("/profile\">");
			sb.append(receiverUserName);
			sb.append("</a>");

			String receiverUserNameURL = sb.toString();

			title = themeDisplay.translate(
				"activity-social-networking-summary-add-friend",
				new Object[] {creatorUserNameURL, receiverUserNameURL});
		}

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		User.class.getName()
	};

}