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

package com.liferay.so.activities.hook.social;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.service.permission.MicroblogsEntryPermission;
import com.liferay.microblogs.util.MicroblogsUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;

import java.util.List;

/**
 * @author Matthew Kong
 */
public class MicroblogsActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	public void updateActivitySet(long activityId)
		throws PortalException, SystemException {

		SocialActivity activity =
			SocialActivityLocalServiceUtil.fetchSocialActivity(activityId);

		if ((activity == null) || (activity.getActivitySetId() > 0)) {
			return;
		}

		long activitySetId = getActivitySetId(activityId);

		if (activitySetId > 0) {
			SocialActivitySetLocalServiceUtil.incrementActivityCount(
				activitySetId, activityId);
		}
		else {
			SocialActivitySet activitySet =
				SocialActivitySetLocalServiceUtil.addActivitySet(activityId);

			if (activity.getType() == _ACTIVITY_KEY_REPLY_ENTRY) {
				MicroblogsEntry microblogsEntry =
					MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(
						activity.getClassPK());

				if (microblogsEntry == null) {
					return;
				}

				activitySet.setClassPK(
					microblogsEntry.getReceiverMicroblogsEntryId());

				SocialActivitySetLocalServiceUtil.updateSocialActivitySet(
					activitySet);
			}
		}
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		List<SocialActivity> activities =
			SocialActivityLocalServiceUtil.getActivitySetActivities(
				activitySet.getActivitySetId(), 0, 1);

		if (!activities.isEmpty()) {
			SocialActivity activity = activities.get(0);

			return doInterpret(activity, serviceContext);
		}

		return null;
	}

	@Override
	protected String getTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(8);

		sb.append(
			getTitle(
				activity.getGroupId(), activity.getUserId(),
				activity.getCreateDate(), serviceContext));
		sb.append("<div class=\"activity-action\">");

		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(
				activity.getClassPK());

		String receiverUserName = getUserName(
			activity.getReceiverUserId(), serviceContext);

		if (activity.getReceiverUserId() > 0) {
			if (microblogsEntry.getType() == _ACTIVITY_KEY_REPLY_ENTRY) {
				sb.append("@");
				sb.append(receiverUserName);
				sb.append(": ");
			}
			else if (microblogsEntry.getType() == _ACTIVITY_KEY_REPOST_ENTRY) {
				sb.append(serviceContext.translate("reposted-from"));
				sb.append(" ");
				sb.append(receiverUserName);
				sb.append(": ");
			}
		}

		sb.append(
			MicroblogsUtil.getTaggedContent(microblogsEntry, serviceContext));
		sb.append("</div>");

		return sb.toString();
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(
				activity.getClassPK());

		return MicroblogsEntryPermission.contains(
			permissionChecker, microblogsEntry, ActionKeys.VIEW);
	}

	/**
	 * {@link
	 * com.liferay.microblogs.microblogs.social.MicroblogsActivityKeys#REPLY_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_REPLY_ENTRY = 3;

	/**
	 * {@link
	 * com.liferay.microblogs.microblogs.social.MicroblogsActivityKeys#REPOST_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_REPOST_ENTRY = 2;

	private static final String[] _CLASS_NAMES =
		{MicroblogsEntry.class.getName()};

}