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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
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
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != MembersActivityKeys.ADD_MEMBER) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(5);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);

		User creatorUser = UserLocalServiceUtil.getUserById(
			activity.getUserId());

		sb.append(HtmlUtil.escapeURL(creatorUser.getScreenName()));

		sb.append("/profile");

		return sb.toString();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != MembersActivityKeys.ADD_MEMBER) {
			return new Object[0];
		}

		String creatorUserName = getUserName(
			activity.getUserId(), serviceContext);

		String creatorUserNameURL = wrapLink(
			getLink(activity, serviceContext), creatorUserName);

		StringBundler sb = new StringBundler(4);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathFriendlyURLPublic());

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(activity.getClassPK());

		Group group = organization.getGroup();

		sb.append(group.getFriendlyURL());

		sb.append("/profile");

		String organizationNameURL = wrapLink(
			sb.toString(), HtmlUtil.escape(organization.getName()));

		return new Object[] {creatorUserNameURL, organizationNameURL};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == MembersActivityKeys.ADD_MEMBER) {
			return "activity-social-networking-summary-join-organization";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ServiceContext serviceContext) {

		return true;
	}

	private static final String[] _CLASS_NAMES = {Organization.class.getName()};

}