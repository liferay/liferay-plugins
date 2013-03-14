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

package com.liferay.socialcoding.svn.social;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.socialcoding.model.SVNRepository;
import com.liferay.socialcoding.model.SVNRevision;
import com.liferay.socialcoding.service.SVNRevisionLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String link = getLink(activity, themeDisplay);

		SVNRevision svnRevision = SVNRevisionLocalServiceUtil.getSVNRevision(
			activity.getClassPK());

		return wrapLink(link, HtmlUtil.escape(svnRevision.getComments()));
	}

	@Override
	protected String getLink(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		SVNRevision svnRevision = SVNRevisionLocalServiceUtil.getSVNRevision(
			activity.getClassPK());

		return svnRevision.getWebRevisionNumberURL();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ThemeDisplay themeDisplay)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != SVNActivityKeys.ADD_REVISION) {
			return new Object[0];
		}

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		SVNRevision svnRevision = SVNRevisionLocalServiceUtil.getSVNRevision(
			activity.getClassPK());

		SVNRepository svnRepository = svnRevision.getSVNRepository();

		return new Object[] {
			creatorUserName, String.valueOf(svnRevision.getRevisionNumber()),
			svnRepository.getShortURL()
		};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == SVNActivityKeys.ADD_REVISION) {
			return "activity-social-coding-svn-add-revision";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ThemeDisplay themeDisplay) {

		return true;
	}

	private static final String[] _CLASS_NAMES = {SVNRevision.class.getName()};

}