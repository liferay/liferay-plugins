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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;
import com.liferay.tasks.model.TasksEntry;

/**
 * @author Matthew Kong
 */
public class TasksActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivitySet activitySet = null;

			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if ((activity.getType() == _ACTIVITY_KEY_ADD_ENTRY) ||
				(activity.getType() == _ACTIVITY_KEY_REOPEN_ENTRY) ||
				(activity.getType() == _ACTIVITY_KEY_RESOLVE_ENTRY)) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getUserActivitySet(
						activity.getGroupId(), activity.getUserId(),
						activity.getClassNameId(), activity.getType());
			}
			else if (activity.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet)) {
				return activitySet.getActivitySetId();
			}
		}
		catch (Exception e) {
		}

		return 0;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return getBody(
			activity.getClassName(), activity.getClassPK(), serviceContext);
	}

	@Override
	protected String getBody(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
			return getBody(
				activitySet.getClassName(), activitySet.getClassPK(),
				serviceContext);
		}

		return super.getBody(activitySet, serviceContext);
	}

	protected String getBody(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(getPageTitle(className, classPK, serviceContext));
		sb.append("</div><div class=\"tasks-entry-content\">");

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		sb.append(
			StringUtil.shorten(
				assetRenderer.getSummary(serviceContext.getLocale()), 200));

		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		if ((activity.getReceiverUserId() <= 0) ||
			(activity.getUserId() == activity.getReceiverUserId())) {

			return null;
		}

		String receiverUserName = getUserName(
			activity.getReceiverUserId(), serviceContext);

		return new Object[] {receiverUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		String titlePattern = null;

		if (activity.getType() == _ACTIVITY_KEY_ADD_ENTRY) {
			titlePattern = "created-a-new-task";
		}
		else if (activity.getType() == _ACTIVITY_KEY_REOPEN_ENTRY) {
			titlePattern = "reopened-a-task";
		}
		else if (activity.getType() == _ACTIVITY_KEY_RESOLVE_ENTRY) {
			titlePattern = "resolved-a-task";
		}
		else if (activity.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
			titlePattern = "updated-a-task";
		}
		else {
			return StringPool.BLANK;
		}

		if ((activity.getReceiverUserId() > 0) &&
			(activity.getUserId() != activity.getReceiverUserId())) {

			titlePattern = titlePattern.concat("-for");
		}

		return titlePattern;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivitySet activitySet) {

		if (activitySet.getType() == _ACTIVITY_KEY_ADD_ENTRY) {
			return "created-x-new-tasks";
		}
		else if (activitySet.getType() == _ACTIVITY_KEY_REOPEN_ENTRY) {
			return "reopened-x-tasks";
		}
		else if (activitySet.getType() == _ACTIVITY_KEY_RESOLVE_ENTRY) {
			return "resolved-x-tasks";
		}
		else if (activitySet.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
			return "made-x-updates-to-a-task";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ServiceContext serviceContext) {

		return true;
	}

	/**
	 * {@link com.liferay.tasks.social.TasksActivityKeys#ADD_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_ADD_ENTRY = 1;

	/**
	 * {@link com.liferay.tasks.social.TasksActivityKeys#REOPEN_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_REOPEN_ENTRY = 4;

	/**
	 * {@link com.liferay.tasks.social.TasksActivityKeys#RESOLVE_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_RESOLVE_ENTRY = 3;

	/**
	 * {@link com.liferay.tasks.social.TasksActivityKeys#UPDATE_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_UPDATE_ENTRY = 2;

	private static final String[] _CLASS_NAMES = {TasksEntry.class.getName()};

}