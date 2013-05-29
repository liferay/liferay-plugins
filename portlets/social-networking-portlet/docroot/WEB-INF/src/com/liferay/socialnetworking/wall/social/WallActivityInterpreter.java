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

package com.liferay.socialnetworking.wall.social;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.socialnetworking.model.WallEntry;
import com.liferay.socialnetworking.service.WallEntryLocalServiceUtil;
import com.liferay.socialnetworking.util.WallUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Berentey
 */
public class WallActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		WallEntry wallEntry = WallEntryLocalServiceUtil.getWallEntry(
			activity.getClassPK());

		String comments = getJSONValue(
			activity.getExtraData(), "comments", wallEntry.getComments());

		String link = getLink(activity, serviceContext);

		return wrapLink(link, comments);
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		User receiverUser = UserLocalServiceUtil.getUserById(
			activity.getReceiverUserId());

		String wallLayoutFriendlyURL = WallUtil.getWallLayoutFriendlyURL(
			receiverUser);

		return WallUtil.getWallLink(
			receiverUser, wallLayoutFriendlyURL,
			String.valueOf(activity.getClassPK()),
			serviceContext.getThemeDisplay());
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != WallActivityKeys.ADD_ENTRY) {
			return new Object[0];
		}

		String creatorUserName = getUserName(
			activity.getUserId(), serviceContext);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), serviceContext);

		return new Object[] {creatorUserName, receiverUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == WallActivityKeys.ADD_ENTRY) {
			return "activity-social-networking-wall-add-entry";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		if (!SocialRelationLocalServiceUtil.hasRelation(
				serviceContext.getUserId(), activity.getReceiverUserId(),
				SocialRelationConstants.TYPE_BI_FRIEND) &&
			(serviceContext.getUserId() != activity.getReceiverUserId())) {

			return false;
		}

		return true;
	}

	private static final String[] _CLASS_NAMES = {WallEntry.class.getName()};

}