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

package com.liferay.twitter.social;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.twitter.model.Feed;

/**
 * @author Brian Wing Shun Chan
 */
public class TwitterActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		User creatorUser = UserLocalServiceUtil.getUserById(
			activity.getUserId());

		JSONObject extraData = JSONFactoryUtil.createJSONObject(
			activity.getExtraData());

		StringBundler sb = new StringBundler();

		sb.append("<a href=\"http://twitter.com/");
		sb.append(HtmlUtil.escapeURL(creatorUser.getContact().getTwitterSn()));
		sb.append("/statuses/");
		sb.append(activity.getClassPK());
		sb.append("\" target=\"_blank\">");
		sb.append(HtmlUtil.escape(extraData.getString("text")));
		sb.append("</a>");

		return sb.toString();
	}

	@Override
	protected String getLink(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		return StringPool.BLANK;
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		User creatorUser = UserLocalServiceUtil.getUserById(
			activity.getUserId());

		StringBundler sb = new StringBundler(8);

		sb.append("<a href=\"");
		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);
		sb.append(HtmlUtil.escapeURL(creatorUser.getScreenName()));
		sb.append("/profile\">");
		sb.append(creatorUserName);
		sb.append("</a>");

		String creatorUserNameURL = sb.toString();

		return new Object[] {creatorUserNameURL};
	}

	@Override
	protected String getTitlePattern(String groupName, SocialActivity activity)
		throws Exception {

		return "activity-twitter-add-status";
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ThemeDisplay themeDisplay)
	throws Exception {

		return true;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		Feed.class.getName()
	};

}