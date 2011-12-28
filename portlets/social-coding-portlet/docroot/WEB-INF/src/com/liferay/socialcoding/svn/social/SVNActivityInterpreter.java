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

package com.liferay.socialcoding.svn.social;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
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
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		int activityType = activity.getType();

		// Link

		SVNRevision svnRevision = SVNRevisionLocalServiceUtil.getSVNRevision(
			activity.getClassPK());

		String link = svnRevision.getWebRevisionNumberURL();

		// Title

		SVNRepository svnRepository = svnRevision.getSVNRepository();

		String title = StringPool.BLANK;

		if (activityType == SVNActivityKeys.ADD_REVISION) {
			title = themeDisplay.translate(
				"activity-social-coding-svn-add-revision",
				new Object[] {
					creatorUserName,
					String.valueOf(svnRevision.getRevisionNumber()),
					svnRepository.getShortURL()
				});
		}

		// Body

		StringBuilder sb = new StringBuilder();

		sb.append("<a href=\"");
		sb.append(link);
		sb.append("\" target=\"_blank\">");
		sb.append(HtmlUtil.escape(svnRevision.getComments()));
		sb.append("</a>");

		String body = sb.toString();

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		SVNRevision.class.getName()
	};

}