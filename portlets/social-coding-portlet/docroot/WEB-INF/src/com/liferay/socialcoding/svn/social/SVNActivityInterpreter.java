/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.socialcoding.model.SVNRepository;
import com.liferay.socialcoding.model.SVNRevision;
import com.liferay.socialcoding.service.SVNRevisionLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String link = getLink(activity, serviceContext);

		SVNRevision svnRevision = SVNRevisionLocalServiceUtil.getSVNRevision(
			activity.getClassPK());

		return wrapLink(link, HtmlUtil.escape(svnRevision.getComments()));
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		SVNRevision svnRevision = SVNRevisionLocalServiceUtil.getSVNRevision(
			activity.getClassPK());

		return svnRevision.getWebRevisionNumberURL();
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return _resourceBundleLoader;
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != SVNActivityKeys.ADD_REVISION) {
			return new Object[0];
		}

		String creatorUserName = getUserName(
			activity.getUserId(), serviceContext);

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
		String actionId, ServiceContext serviceContext) {

		return true;
	}

	private static final String[] _CLASS_NAMES = {SVNRevision.class.getName()};

	private final ResourceBundleLoader _resourceBundleLoader =
		new ClassResourceBundleLoader(
			"content.Language", SVNActivityInterpreter.class);

}