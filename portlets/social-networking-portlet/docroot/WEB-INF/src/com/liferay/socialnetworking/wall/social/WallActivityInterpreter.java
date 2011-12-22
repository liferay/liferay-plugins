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

package com.liferay.socialnetworking.wall.social;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.socialnetworking.model.WallEntry;
import com.liferay.socialnetworking.service.WallEntryLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class WallActivityInterpreter extends BaseSocialActivityInterpreter {

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

		User receiverUser = UserLocalServiceUtil.getUserById(
			activity.getReceiverUserId());

		if (!SocialRelationLocalServiceUtil.hasRelation(
				themeDisplay.getUserId(), activity.getReceiverUserId(),
				SocialRelationConstants.TYPE_BI_FRIEND) &&
			(themeDisplay.getUserId() != activity.getReceiverUserId())) {

			return null;
		}

		int activityType = activity.getType();

		// Link

		WallEntry wallEntry = WallEntryLocalServiceUtil.getWallEntry(
			activity.getClassPK());

		String link =
			themeDisplay.getPortalURL() +
				themeDisplay.getPathFriendlyURLPublic() + StringPool.SLASH +
					HtmlUtil.escapeURL(receiverUser.getScreenName()) +
						"/profile/-/wall/" + activity.getClassPK();

		// Title

		String title = StringPool.BLANK;

		if (activityType == WallActivityKeys.ADD_ENTRY) {
			title = themeDisplay.translate(
				"activity-social-networking-wall-add-entry",
				new Object[] {creatorUserName, receiverUserName});
		}

		// Body

		StringBuilder sb = new StringBuilder();

		sb.append("<a href=\"");
		sb.append(link);
		sb.append("\">");
		sb.append(HtmlUtil.escape(cleanContent(wallEntry.getComments())));
		sb.append("</a>");

		String body = sb.toString();

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		WallEntry.class.getName()
	};

}