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

package com.liferay.socialnetworking.members.social;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;

/**
 * @author Brian Wing Shun Chan
 */
public class MembersActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getLink(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != MembersActivityKeys.ADD_MEMBER) {
			return StringPool.BLANK;
		}

		User creatorUser = UserLocalServiceUtil.getUserById(
			activity.getUserId());

		StringBundler sb = new StringBundler(5);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);
		sb.append(HtmlUtil.escapeURL(creatorUser.getScreenName()));
		sb.append("/profile");

		return sb.toString();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ThemeDisplay themeDisplay)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != MembersActivityKeys.ADD_MEMBER) {
			return new Object[0];
		}

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		StringBundler sb = new StringBundler(5);

		sb.append("<a href=\"");
		sb.append(link);
		sb.append("\">");
		sb.append(creatorUserName);
		sb.append("</a>");

		String creatorUserNameURL = sb.toString();

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(activity.getClassPK());

		Group group = organization.getGroup();

		sb = new StringBundler(7);

		sb.append("<a href=\"");
		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(group.getFriendlyURL());
		sb.append("/profile\">");
		sb.append(HtmlUtil.escape(organization.getName()));
		sb.append("</a>");

		String organizationNameURL = sb.toString();

		return new Object[] {creatorUserNameURL, organizationNameURL};
	}

	@Override
	protected String getTitlePattern(String groupName, SocialActivity activity)
		throws Exception {

		int activityType = activity.getType();

		if (activityType == MembersActivityKeys.ADD_MEMBER) {
			return "activity-social-networking-summary-join-organization";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ThemeDisplay themeDisplay)
		throws Exception {

		return true;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		Organization.class.getName()
	};

}