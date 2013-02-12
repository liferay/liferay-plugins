/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.microblogs.social;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.service.permission.MicroblogsEntryPermission;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

/**
 * @author Jonathan Lee
 */
public class MicroblogsActivityInterpreter
	extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(
				activity.getClassPK());

		if (!MicroblogsEntryPermission.contains(
				permissionChecker, microblogsEntry, ActionKeys.VIEW)) {

			return null;
		}

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), themeDisplay);

		// Link

		String link = StringPool.BLANK;

		// Title

		StringBundler sb = new StringBundler(5);

		if (activity.getReceiverUserId() > 0) {
			if (microblogsEntry.getType() ==
					MicroblogsEntryConstants.TYPE_REPLY) {

				sb.append("@");
				sb.append(receiverUserName);
				sb.append(": ");
			}
			else if (microblogsEntry.getType() ==
						MicroblogsEntryConstants.TYPE_REPOST) {

				sb.append(themeDisplay.translate("reposted-from"));
				sb.append(" ");
				sb.append(receiverUserName);
				sb.append(": ");
			}
		}

		sb.append(HtmlUtil.escape(microblogsEntry.getContent()));

		String title = sb.toString();

		// Body

		String body = creatorUserName;

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		MicroblogsEntry.class.getName()
	};

}